package cn.com.yuzhushui.movie.sys.web.action;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.com.yuzhushui.movie.common.util.SessionUtil;
import cn.com.yuzhushui.movie.constant.MovieConstant;
import cn.com.yuzhushui.movie.enums.SysBillsEnum;
import cn.com.yuzhushui.movie.sys.biz.entity.SysAccount;
import cn.com.yuzhushui.movie.sys.biz.entity.SysBills;
import cn.com.yuzhushui.movie.sys.biz.entity.SysUser;
import cn.com.yuzhushui.movie.sys.biz.service.SysAccountService;
import cn.com.yuzhushui.movie.sys.biz.service.SysBillsService;
import cn.com.yuzhushui.movie.sys.biz.service.SysUserService;
import qing.yun.hui.common.utils.BeanUtil;
import qing.yun.hui.common.utils.StringUtil;
import qing.yun.hui.mailtool.MailTool;


/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-03-01 11:24:43
 * @history
 */
@Controller
@RequestMapping(SysBillsAction.ACTION_PATH)
public class SysBillsAction{
	
	protected Logger logger=LoggerFactory.getLogger(SysBillsAction.class);
	
	@Autowired
	SysBillsService sysBillsService;
	
	@Autowired
	SysAccountService sysAccountService;
	
	@Autowired
	SysUserService sysUserService;
	
	//一般用于重定向用
	protected static final String ACTION_PATH="/sys/sysBills";
	
	
	/**
	 * <p>账单列表，仅查询借款人or出借人与自己有关的所有账单。</p>
	 * */
	@RequestMapping(value = "list")
	public ModelAndView list() {
		ModelAndView modelAndView =new ModelAndView(getActionPath() + "/list");
		try {
			SysUser user=SessionUtil.getSysUser();
			SysBills bills=new SysBills();
			//查询-借款人or出借人的所有账单
			bills.setDebtorId(user.getAccountId());
			bills.setLenderId(user.getAccountId());
			bills.setQueryDebtorIdWithlenderId("true");
			Map<String,Object>map=BeanUtil.pojoToMap(bills);
			List<SysBills> sysBillsList=sysBillsService.query(map);
			logger.info("==============>查询到的账单有:{}条。",new Object[]{sysBillsList.size()});
			modelAndView.addObject(MovieConstant.ENTITYS, sysBillsList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * <p>账单详情</p>
	 * */
	@RequestMapping(value = "detail")
	public ModelAndView detail(Integer id) {
		ModelAndView modelAndView = new ModelAndView(getActionPath() + "/detail");
		
		if(StringUtil.isEmpty(id)){
			logger.error("===========>主键id不能为null!");
			modelAndView.setViewName("redirect:/sys/sysBills/list.htm");
			return modelAndView;
		}
		SysBills sysBills= sysBillsService.query(id);
		if(null==sysBills) {
			logger.error("===========>未查询到id={}的账单。!",new Object[]{id});
			modelAndView.setViewName("redirect:/sys/sysBills/list.htm");
			return modelAndView;
		}
		Boolean isShow=false;
		if(sysBills.getStatus().equals(SysBillsEnum.Status.AUDIT_WAIT.getValue())){
			//如果账单是待处理状态的，即可进行审核。
			//贷款人与出借人所看到的页面，处理方式各不相同。
			SysAccount account=SessionUtil.getSysAccount();
			if(account.getAccountId().equals(sysBills.getLenderId())){
				//如果登陆人是出借人那么，是可进行审核的。
				isShow=true;
			}
		}
		sysBills.setIsShow(isShow?"":"none");
		modelAndView.addObject(MovieConstant.ENTITY, sysBills);
		return modelAndView;
	}
	
	/**
	 * <p>只能审核，账单的出借人是对应自己的</p>
	 * */
	@RequestMapping(value = "doAudit")
	public ModelAndView doAudit(Integer id) {
		ModelAndView modelAndView = new ModelAndView(getActionPath() + "/detail");
		try {
			if(StringUtil.isEmpty(id)){
				logger.error("===========>主键id不能为null!");
				modelAndView.setViewName("redirect:/sys/sysBills/list.htm");
				return modelAndView;
			}
			SysBills sysBills= sysBillsService.query(id);
			if(null==sysBills) {
				logger.error("===========>未查询到id={}的账单。!",new Object[]{id});
				modelAndView.setViewName("redirect:/sys/sysBills/list.htm");
				return modelAndView;
			}
			if(sysBills.getStatus().intValue()==SysBillsEnum.Status.AUDIT_PASS.getValue()){
				logger.error("===========>审核通过的账单不能重复审核。!");
				modelAndView.setViewName("redirect:/sys/sysBills/list.htm");
				return modelAndView;
			}
			SysAccount account=SessionUtil.getSysAccount();
			if(!sysBills.getLenderId().equals(account.getAccountId())){
				logger.error("===========>id={}的账单,你没有权限审核!",new Object[]{id});
				modelAndView.setViewName("redirect:/sys/sysBills/list.htm");
				return modelAndView;
			}
			sysBills.setStatus(SysBillsEnum.Status.AUDIT_PASS.getValue());
			
			int count=sysBillsService.update(sysBills);
			
			logger.info("==============>id={}的账单，审核"+(count>0?"成功":"失败")+"。",new Object[]{id});
			String subject="账单审核通过啦。";
			String content=sysBills.getContent();
			String lenderEmial=sysAccountService.query(sysBills.getLenderId()).getEmail();
			String debtorEmail=sysAccountService.query(sysBills.getDebtorId()).getEmail();
			String[] sendEmails=new String[]{lenderEmial,debtorEmail};
			MailTool.sendMail(subject, content, sendEmails);
			modelAndView.addObject(MovieConstant.ENTITY, sysBills);
		} catch (Exception e) {
			logger.error("=================>账单.id={}，审核失败，失败原因:{}",new Object[]{id,JSONObject.toJSONString(e)});
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value = "add")
	public ModelAndView add(SysBills bills) {
		ModelAndView modelAndView = new ModelAndView(getActionPath() + "/add");
		modelAndView.addObject(MovieConstant.ENTITY, bills);
		//出借人-管理员qing.yunhui
		SysUser debtorUser=sysUserService.queryByAccountId(MovieConstant.accountId);
		modelAndView.addObject("debtorUser", debtorUser);
		return modelAndView;
	}
	
	/**
	 * <p>只能审核，账单的出借人是对应自己的</p>
	 * */
	@RequestMapping(value = "doAdd")
	public ModelAndView doAdd(SysBills sysBills) {
		ModelAndView modelAndView = new ModelAndView("redirect"+ACTION_PATH+"/list.htm");
		try {
			if(null==sysBills){
				logger.error("===========>账单不能为null!");
				return modelAndView;
			}
			//借款人
			SysUser user=SessionUtil.getSysUser();
			sysBills.setStatus(SysBillsEnum.Status.AUDIT_WAIT.getValue());
			sysBills.setDebtor(user.getName());
			sysBills.setDebtorId(user.getAccountId());
			//出借人
			SysUser debtorUser=sysUserService.queryByAccountId(MovieConstant.accountId);
			sysBills.setLender(debtorUser.getName());
			sysBills.setLenderId(debtorUser.getAccountId());
			sysBills.setCreater(user.getName());
			sysBills.setCreaterId(user.getAccountId());
			sysBills.setComments("借款条【借款证明】");
			int count=sysBillsService.add(sysBills);
			logger.info("==============>账单，申请"+(count>0?"成功":"失败")+"。");
			String subject="账单审核通过啦。";
			String content=sysBills.getContent();
			String lenderEmial=sysAccountService.query(sysBills.getLenderId()).getEmail();
			String debtorEmail=sysAccountService.query(sysBills.getDebtorId()).getEmail();
			String[] sendEmails=new String[]{lenderEmial,debtorEmail};
			MailTool.sendMail(subject, content, sendEmails);
			modelAndView.addObject(MovieConstant.ENTITY, sysBills);
		} catch (Exception e) {
			logger.error("=================>账单申请失败，失败原因:{}",new Object[]{JSONObject.toJSONString(e)});
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	public String getActionPath() {
		return ACTION_PATH;
	}
}