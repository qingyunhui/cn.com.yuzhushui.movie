package cn.com.yuzhushui.movie.sys.web.action;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import qing.yun.hui.common.utils.BeanUtil;
import qing.yun.hui.common.utils.StringUtil;
import qing.yun.hui.mailtool.MailTool;
import cn.com.yuzhushui.movie.common.base.BaseAction;
import cn.com.yuzhushui.movie.common.util.SessionUtil;
import cn.com.yuzhushui.movie.constant.MovieConstant;
import cn.com.yuzhushui.movie.enums.SysBillsEnum;
import cn.com.yuzhushui.movie.sys.biz.entity.SysAccount;
import cn.com.yuzhushui.movie.sys.biz.entity.SysBills;
import cn.com.yuzhushui.movie.sys.biz.entity.SysUser;
import cn.com.yuzhushui.movie.sys.biz.service.SysBillsService;
import cn.com.yuzhushui.movie.sys.biz.service.SysUserService;
import cn.com.yuzhushui.movie.sys.web.vo.SysBillsForm;


/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-03-01 11:24:43
 * @history
 */
@Controller
@RequestMapping(SysBillsAction.ACTION_PATH)
public class SysBillsAction extends BaseAction<SysBills, SysBillsForm, Integer>{
	
	protected Logger logger=LoggerFactory.getLogger(SysBillsAction.class);
	
	@Autowired
	SysBillsService sysBillsService;
	
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
		modelAndView.addObject(MovieConstant.ENTITY, sysBills);
		return modelAndView;
	}
	
	/**
	 * <p>只能审核，账单的出借人是对应自己的</p>
	 * */
	@RequestMapping(value = "doAudit")
	public ModelAndView doAudit(Integer id) {
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
		
		String subject="";
		String content="";
		String lenderEmial=sysUserService.query(sysBills.getLenderId()).getEmail();
		String debtorEmail=sysUserService.query(sysBills.getDebtorId()).getEmail();
		String[] sendEmails=new String[]{lenderEmial,debtorEmail};
		try {
			MailTool.sendMail(subject, content, sendEmails);
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.addObject(MovieConstant.ENTITY, sysBills);
		return modelAndView;
	}
	
	
	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
}