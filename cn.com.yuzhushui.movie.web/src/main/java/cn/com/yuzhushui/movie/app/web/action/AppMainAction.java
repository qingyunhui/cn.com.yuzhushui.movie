package cn.com.yuzhushui.movie.app.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.yuzhushui.movie.cache.ShardedJedisCached;
import cn.com.yuzhushui.movie.common.bean.LogParameter;
import cn.com.yuzhushui.movie.common.bean.SessionInfo;
import cn.com.yuzhushui.movie.constant.MovieConstant;
import cn.com.yuzhushui.movie.enums.SysAccountEnum;
import cn.com.yuzhushui.movie.sys.biz.entity.SysAccount;
import cn.com.yuzhushui.movie.sys.biz.entity.SysUser;
import cn.com.yuzhushui.movie.sys.biz.service.SysAccountService;
import cn.com.yuzhushui.movie.sys.biz.service.SysUserService;
import qing.yun.hui.common.utils.CookieUtil;
import qing.yun.hui.common.utils.MD5Util;
import qing.yun.hui.common.utils.StringUtil;
import qing.yun.hui.common.utils.ValidateUtil;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月15日下午10:43:34
 **/

@Controller
@RequestMapping(AppMainAction.ACTION_PATH)
public class AppMainAction {
	
	protected Logger logger=LoggerFactory.getLogger(AppMainAction.class);
	
	protected static final String ACTION_PATH = "/app/appMain";
	
	public static final String SHOWED_INTRODUCE = "showed_introduce";
	
	@Autowired
	private ShardedJedisCached shardedJedisCached;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysAccountService sysAccountService;
	
	/**引导页*/
	@RequestMapping(value = "/introduce")
	public ModelAndView introduce(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		/***
		 * @1.如果用户未注册则进入引导页...
		 * @2.用户已注册，判断用户是否是一个月内免登陆(数据库有标识字段)、查询redis缓存中是否存在这个标识，
		 *    @2.1如果用户是一个月内免登陆、且还在有效期、则跳转到主页面。
		 *    @2.2如果用户不是一个月内免登陆、则跳转到登陆页面。
		 * */
		ModelAndView modelView = null;
		SessionInfo sessioninfo = (SessionInfo) session.getAttribute(MovieConstant.SESSION_INFO);
		if (sessioninfo != null) {
			modelView = new ModelAndView("redirect:/main/myMain.htm");
			return modelView;
		}
		if("true".equals(CookieUtil.getCookieByKey(request, SHOWED_INTRODUCE))) {
			modelView = new ModelAndView("redirect:" + ACTION_PATH + "/login.htm");// 进入登录页面(自动登录考虑页面的功能)
		} else {
			modelView = new ModelAndView(ACTION_PATH + "/introduce");// 进入引导页面
		}
		return modelView;
	}
	
	/**进入登陆页面**/
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/login");
		return modelView;
	}
	
	/**登陆*/
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public ModelAndView doLogin(LogParameter logParam,String phoneOrEmail, HttpSession session,RedirectAttributes attributes) {
		ModelAndView modeView = new ModelAndView("redirect:" + ACTION_PATH + "/login.htm");
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtil.isEmpty(phoneOrEmail)){
			modeView.addObject(MovieConstant.MESSAGES,"请输入手机号/邮箱!");
			return modeView;
		}
		boolean mobilePhone=ValidateUtil.isMobile(phoneOrEmail);
		if(mobilePhone){
			map.put("mobilePhone", logParam.getAccounts());
		}/*else if(){}{
			map.put("email", logParam.getAccounts());
		}else{
			map.put("account", logParam.getAccounts());
		}*/
		map.put("loginPassword", MD5Util.getMD5Encryption(logParam.getPasswords()));
		List<SysAccount> accounts=sysAccountService.query(map);
		if (accounts.size() == 1) {
			SysAccount account = accounts.get(0);
			Map<String,Object> userMap=new HashMap<String, Object>();
			userMap.put("accountId", account.getAccountId());
			List<SysUser> users=sysUserService.query(userMap);
			if(users.size()==1){
				if(account.getStatus()!=SysAccountEnum.STATUS.AUDIT_SUCCESS.getValue()){
					attributes.addFlashAttribute(MovieConstant.MESSAGES, "登录失败，用户已被禁用或者没有注册！");
				}else{
					SysUser sysUser=users.get(0);
					SessionInfo sessionInfo = new SessionInfo();
					sessionInfo.setSysUser(sysUser);
					sessionInfo.setObject(account);
					session.setAttribute(MovieConstant.SESSION_INFO, sessionInfo);// 登录成功，获取用户信息，并存入session
					modeView = new ModelAndView("redirect:/main/myMain.htm");
				}
			}else{
				attributes.addFlashAttribute(MovieConstant.MESSAGES, "登录失败，用户名或密码错误！");
			}
		} else {
			attributes.addFlashAttribute(MovieConstant.MESSAGES, "登录失败，用户名或密码错误！");
		}
		return modeView;
	}
}
