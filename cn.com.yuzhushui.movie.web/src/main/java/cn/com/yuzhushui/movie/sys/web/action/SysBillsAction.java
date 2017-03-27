package cn.com.yuzhushui.movie.sys.web.action;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;

import cn.com.yuzhushui.movie.common.util.SessionUtil;
import cn.com.yuzhushui.movie.constant.MovieConstant;
import cn.com.yuzhushui.movie.enums.CapitalPoolEnum;
import cn.com.yuzhushui.movie.enums.CapitalPoolEnum.CapitalPool;
import cn.com.yuzhushui.movie.enums.SysBillsEnum;
import cn.com.yuzhushui.movie.struct.CapitalPoolStruct;
import cn.com.yuzhushui.movie.sys.biz.entity.SysAccount;
import cn.com.yuzhushui.movie.sys.biz.entity.SysBills;
import cn.com.yuzhushui.movie.sys.biz.entity.SysUser;
import cn.com.yuzhushui.movie.sys.biz.service.SysAccountService;
import cn.com.yuzhushui.movie.sys.biz.service.SysBillsService;
import cn.com.yuzhushui.movie.sys.biz.service.SysFundPoolService;
import cn.com.yuzhushui.movie.sys.biz.service.SysUserService;
import qing.yun.hui.common.annotations.ActionAnno;
import qing.yun.hui.common.utils.BeanUtil;
import qing.yun.hui.common.utils.DateUtil;
import qing.yun.hui.common.utils.EnumUtil;
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
	
	@Autowired
	SysFundPoolService sysFundPoolService;
	
	//一般用于重定向用
	protected static final String ACTION_PATH="/sys/sysBills";
	
	
	/**
	 * <p>账单列表，仅查询借款人or出借人与自己有关的所有账单。</p>
	 * */
	@RequestMapping("list")
	@ActionAnno(action="访问个人账单")
	public ModelAndView list(String messages) {
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
			modelAndView.addObject(MovieConstant.MESSAGES_INFO,messages);
		} catch (Exception e) {
			logger.error("系统异常，异常原因:{}",new Object[]{JSONObject.toJSONString(e)});
			modelAndView.addObject(MovieConstant.MESSAGES_INFO,e.getMessage());
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * <p>账单详情</p>
	 * */
	@ActionAnno(action="访问个人账单详情")
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
	@ActionAnno(action="提交审核")
	public ModelAndView doAudit(Integer id,RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:/sys/sysBills/list.htm");
		try {
			if(StringUtil.isEmpty(id)){
				logger.error("===========>主键id不能为null!");
				redirectAttributes.addAttribute(MovieConstant.MESSAGES_INFO,"主键id不能为null！");
				return modelAndView;
			}
			SysBills sysBills= sysBillsService.query(id);
			if(null==sysBills) {
				redirectAttributes.addAttribute(MovieConstant.MESSAGES_INFO,"未查询到id="+id+"的账单。");
				logger.error("===========>未查询到id={}的账单。!",new Object[]{id});
				return modelAndView;
			}
			if(sysBills.getStatus().intValue()==SysBillsEnum.Status.AUDIT_PASS.getValue()){
				logger.error("===========>审核通过的账单不能重复审核!");
				redirectAttributes.addAttribute(MovieConstant.MESSAGES_INFO,"审核通过的账单不能重复审核!");
				return modelAndView;
			}
			SysAccount account=SessionUtil.getSysAccount();
			if(!sysBills.getLenderId().equals(account.getAccountId())){
				logger.error("===========>id={}的账单,你没有权限审核!",new Object[]{id});
				redirectAttributes.addAttribute(MovieConstant.MESSAGES_INFO,"你没有权限审核!");
				return modelAndView;
			}
			sysBills.setStatus(SysBillsEnum.Status.AUDIT_PASS.getValue());
			
			int count=sysBillsService.update(sysBills);
			
			logger.info("==============>id={}的账单，审核"+(count>0?"成功":"失败")+"。",new Object[]{id});
			String debtorSubject="您于："+DateUtil.dateToString(sysBills.getCtime(),DateUtil.YYYY_MM_DD_HH_MM_SS)+" 申请的账单已经由相关人员审核通过。";
			StringBuffer sb=new StringBuffer();
			sb.append("<p>").append("<span>").append(sysBills.getSubject()).append("</span>").append("</p>");
			//关键字，转换
			String keywords=EnumUtil.getNameByValue("cn.com.yuzhushui.movie.enums.SysBillsEnum$Keyword",sysBills.getKeyword()+"");
			sb.append("<p>").append("<span>").append(keywords+""+sysBills.getMoney()+"元人民币").append("</span>").append("</p>");
			sb.append("<p>").append("<span>").append(sysBills.getContent()).append("</span>").append("</p>");
			
			sb.append("<br/>");
			sb.append("<p>").append("<span>").append("审核状态：").append(SysBillsEnum.Status.AUDIT_PASS.getName()).append("</span>").append("</p>");
			sb.append("<br/>");
			sb.append("审核通过时间：").append(DateUtil.getStringDate(DateUtil.YYYY_MM_DD_HH_MM_SS));
			
			String lenderEmial=sysAccountService.query(sysBills.getLenderId()).getEmail();	//出借人
			
			String debtorEmail=sysAccountService.query(sysBills.getDebtorId()).getEmail();//预支人
			//这里分二次发送，是为了防止服务器把其看做是垃圾邮件...
			MailTool.sendMail(debtorSubject, sb.toString(), new String[]{debtorEmail});
			String lenderSubject=sysBills.getDebtor()+"于"+DateUtil.dateToString(sysBills.getCtime(),DateUtil.YYYY_MM_DD_HH_MM_SS)+"申请的账单已审核通过.";
			MailTool.sendMail(lenderSubject, sb.toString(), new String[]{lenderEmial});	 
			redirectAttributes.addAttribute(MovieConstant.MESSAGES_INFO,"账单审核成功啦！"); 
		} catch (Exception e) {
			logger.error("=================>账单.id={}，审核失败，失败原因:{}",new Object[]{id,JSONObject.toJSONString(e)});
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value = "add")
	@ActionAnno(action="访问新增账单")
	public ModelAndView add(SysBills bills) {
		ModelAndView modelAndView = new ModelAndView(getActionPath() + "/add");
		modelAndView.addObject(MovieConstant.ENTITY, bills);
		//出借人-管理员qing.yunhui
		SysUser debtorUser=sysUserService.queryByAccountId(MovieConstant.accountId);
		modelAndView.addObject("debtorUser", debtorUser);
		CapitalPoolStruct struct= sysFundPoolService.getTotalBalance(SessionUtil.getSysAccount().getAccountId());
		StringBuffer sb=new StringBuffer();
		if(CapitalPool.NOT_AVAILABLE_POOL.getValue()==struct.getCapitalPool().getValue()){
			//无可用资金池(资金池还未创建)
			sb.append("您还未在平台，充值哦...");
		}else if(CapitalPool.INSUFFICIENT_POOL_BALANCE.getValue()==struct.getCapitalPool().getValue()){
			//资金池充值余额不足（须要补充）
			sb.append("余额不足："+CapitalPoolEnum.EARLY_WARNING_MONEY+"元，请尽快充值.");
		}else if(CapitalPool.OVERDRAFT_POOL_BALANCE.getValue()==struct.getCapitalPool().getValue()){
			//已透支（须要尽快充值.）
			sb.append("当前已透支："+struct.getTotalBalance()+"元，请速速充值...");
		}else{
			//资金池余额充足
			sb.append("当前可用余额为："+struct.getTotalBalance()+"元人民币.");
		}
		modelAndView.addObject(MovieConstant.MESSAGES_INFO, sb.toString());
		return modelAndView;
	}
	
	@RequestMapping(value = "doAdd")
	@ActionAnno(action="提交个人账单")
	public ModelAndView doAdd(SysBills sysBills,RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:/sys/sysBills/list.htm");
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
			String debtorSubject="您于："+DateUtil.getStringDate(DateUtil.YYYY_MM_DD_HH_MM_SS)+" 申请的账单已经申请成功，请耐心等待相关出借人员审核。";
			String lenderSubject=user.getName()+"于："+DateUtil.getStringDate(DateUtil.YYYY_MM_DD_HH_MM_SS)+" 申请 "+sysBills.getMoney()+"元账单，请尽快审核.";
			StringBuffer sb=new StringBuffer();
			sb.append("<p>").append("<span>").append(sysBills.getSubject()).append("</span>").append("</p>");
			//关键字，转换
			String keywords=EnumUtil.getNameByValue("cn.com.yuzhushui.movie.enums.SysBillsEnum$Keyword",sysBills.getKeyword()+"");
			sb.append("<p>").append("<span>").append(keywords+""+sysBills.getMoney()+"元人民币。").append("</span>").append("</p>");
			sb.append("<p>").append("<span>").append(sysBills.getContent()).append("</span>").append("</p>");
			
			sb.append("<br/>");
			sb.append("<p>").append("<span>").append("审核状态：").append(EnumUtil.getNameByValue(SysBillsEnum.Status.class, sysBills.getStatus())).append("</span>").append("</p>");
			
			String lenderEmial=sysAccountService.query(sysBills.getLenderId()).getEmail();
			String debtorEmail=sysAccountService.query(sysBills.getDebtorId()).getEmail();
			MailTool.sendMail(debtorSubject, sb.toString(), new String[]{debtorEmail});
			MailTool.sendMail(lenderSubject, sb.toString(), new String[]{lenderEmial});
			redirectAttributes.addAttribute(MovieConstant.MESSAGES_INFO,"账单申请成功啦！");
		} catch (Exception e) {
			logger.error("=================>账单申请失败，失败原因:{}",new Object[]{JSONObject.toJSONString(e)});
			redirectAttributes.addAttribute(MovieConstant.MESSAGES_INFO,"账单申请失败！，失败原因:"+JSONObject.toJSONString(e));
		}
		return modelAndView;
	}
	
	public String getActionPath() {
		return ACTION_PATH;
	}
}