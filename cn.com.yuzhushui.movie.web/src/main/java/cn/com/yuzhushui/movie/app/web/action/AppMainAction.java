package cn.com.yuzhushui.movie.app.web.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;

import cn.com.yuzhushui.movie.cache.ShardedJedisCached;
import cn.com.yuzhushui.movie.common.base.ResponseData;
import cn.com.yuzhushui.movie.common.bean.LogParameter;
import cn.com.yuzhushui.movie.common.bean.SessionInfo;
import cn.com.yuzhushui.movie.common.util.SessionUtil;
import cn.com.yuzhushui.movie.constant.MovieConstant;
import cn.com.yuzhushui.movie.enums.LoginType;
import cn.com.yuzhushui.movie.enums.SysAccountEnum;
import cn.com.yuzhushui.movie.sys.biz.entity.SysAccount;
import cn.com.yuzhushui.movie.sys.biz.entity.SysUser;
import cn.com.yuzhushui.movie.sys.biz.service.SysAccountService;
import cn.com.yuzhushui.movie.sys.biz.service.SysUserService;
import qing.yun.hui.common.utils.CookieUtil;
import qing.yun.hui.common.utils.DateUtil;
import qing.yun.hui.common.utils.EnumUtil;
import qing.yun.hui.common.utils.GenerateRuleUtil;
import qing.yun.hui.common.utils.MD5Util;
import qing.yun.hui.common.utils.StringUtil;
import qing.yun.hui.common.utils.ValidateUtil;
import qing.yun.hui.mailtool.MailTool;

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
	
	//邮箱验证码有效时间为10分钟
	private static final int expireSecond=10*60;
	
	private static final String SEND_MAIL_KEY="send_mail_key";
	
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
		 *    @2.0判断登陆次数是否>N次
		 *    @2.1如果用户是一个月内免登陆、且还在有效期、则跳转到主页面。
		 *    @2.2如果用户不是一个月内免登陆、则跳转到登陆页面。
		 * */
		ModelAndView modelView = new ModelAndView(ACTION_PATH+"/introduce");
		String sessionId=CookieUtil.getCookieValueByName(request, MovieConstant.SESSION_INFO);
		if (StringUtil.isEmpty(sessionId)) {
			return modelView;
		}
		String sessionInfoJson=shardedJedisCached.get(sessionId);
		SessionInfo sessInfoInfo=JSONObject.parseObject(sessionInfoJson, SessionInfo.class);
		if(null!=sessInfoInfo){
			modelView.setViewName("redirect:"+ACTION_PATH+"/myMain.htm");
			return modelView;
		}
		modelView.setViewName("redirect:"+ACTION_PATH+"/login.htm");
		return modelView;
	}
	
	/**进入个人首页（第一个页面）**/
	@RequestMapping(value = "/myMain")
	public ModelAndView myMain(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/myMain");
		return modelView;
	}
	
	/**进入个人首页（第一个页面）**/
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/index");
		return modelView;
	}
	
	/**商品分类（第二个页面）*/
	@RequestMapping(value = "/goodsCategory")
	public ModelAndView goodsCategory(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/goodsCategory");
		return modelView;
	}
	
	/**购物车（第三个页面）*/
	@RequestMapping(value = "/shoppingCart")
	public ModelAndView shoppingCart(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/shoppingCart");
		return modelView;
	}
	
	/**个人中心（第四个页面）*/
	@RequestMapping(value = "/myself")
	public ModelAndView myself(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/myself");
		return modelView;
	}
	
	/**进入登陆页面**/
	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/login");
		
		SysUser user=SessionUtil.getSysUser();
		if(null!=user){
			modelView.setViewName("redirect:"+ACTION_PATH+"/myMain.htm");
			return modelView;
		}
		
		return modelView;
	}
	
	@RequestMapping(value = "/register")
	public ModelAndView register(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/register");
		return modelView;
	}
	
	/**
	 * <p>判断登陆方式[0:账号登陆、1:手机号、2:邮箱]</p>
	 * @return 登陆方式
	 * */
	protected Integer loginType(String identity){
		if(ValidateUtil.isEmail(identity)) return LoginType.EMAIL_TYPE.getValue();
		if(ValidateUtil.isMobile(identity)) return LoginType.MOBILEPHONE_TYPE.getValue();
		return LoginType.ACCOUNT_TYPE.getValue();
	}
	
	/**登陆*/
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public ModelAndView doLogin(HttpServletRequest request,HttpServletResponse response, HttpSession session,LogParameter logParam,RedirectAttributes attributes) {
		ModelAndView modeView = new ModelAndView("redirect:" + ACTION_PATH + "/login.htm");
		Map<String, Object> map = new HashMap<String, Object>();
		
		SysUser user=SessionUtil.getSysUser();
		if(null!=user){
			modeView.setViewName("redirect:"+ACTION_PATH+"/myMain.htm");
			return modeView;
		}
		
		//@1.非空校验
		if(StringUtil.isEmpty(logParam.getAccounts())){
			String message="请输入手机号/邮箱/账号!";
			modeView.addObject(MovieConstant.MESSAGES_INFO,message);
			logger.error("===========>"+message+"<===========");
			return modeView;
		}
		if(StringUtil.isEmpty(logParam.getPasswords())){
			String message="请输入登陆密码!";
			modeView.addObject(MovieConstant.MESSAGES_INFO,message);
			logger.error("===========>"+message+"<===========");
			return modeView;
		}
		
		//@2.用户登陆密码输入错误次数校验
		String userLogCount=logParam.getAccounts()+"_"+MovieConstant.MESSAGES_INFO;//当前用户登陆次数
		String logCountStr=shardedJedisCached.get(userLogCount);
		if(!StringUtil.isEmpty(logCountStr)){
			int logCount=Integer.parseInt(logCountStr);
			if(logCount > MovieConstant.LOGON_COUNTS){
				StringBuilder sb=new StringBuilder();
				sb.append("密码输入错误超过");
				sb.append(MovieConstant.LOGON_COUNTS);
				sb.append("次，请");
				sb.append(MovieConstant.LOCK_TIME);
				sb.append("分钟后重试。");
				modeView.addObject(MovieConstant.MESSAGES_INFO,sb.toString());
				logger.error("===========>"+sb.toString()+"<===========");
				return modeView;
			}
		}
		//@3.登陆类型校验
		Integer loginType=loginType(logParam.getAccounts());
		if(loginType.equals(LoginType.MOBILEPHONE_TYPE)){
			map.put("mobilephone", logParam.getAccounts());
		}else if(loginType.equals(LoginType.EMAIL_TYPE)){
			map.put("email", logParam.getAccounts());
		}else{
			map.put("account", logParam.getAccounts());
		}
		
		logger.error("===========>登陆类型为:{}<===========",new Object[]{EnumUtil.getNameByValue(LoginType.class, loginType.intValue())});
		
		map.put("password", MD5Util.getMD5Encryption(logParam.getPasswords()));
		//@4.判断数据库中是否存在该用户
		List<SysAccount> accounts=sysAccountService.query(map);
		if (accounts.size() == 1) {
			SysAccount account = accounts.get(0);
			Map<String,Object> userMap=new HashMap<String, Object>();
			userMap.put("accountId", account.getAccountId());
			List<SysUser> users=sysUserService.query(userMap);
			if(users.size()==1){
				if(account.getStatus().intValue()==SysAccountEnum.STATUS.AUDIT_SUCCESS.getValue()){
					//登陆成功-把用户存储到cookie中。
					modeView.setViewName("redirect:"+ACTION_PATH+"/myMain.htm");
					SysUser sysUser=users.get(0);
					SessionInfo sessionInfo = new SessionInfo();
					sessionInfo.setSysUser(sysUser);
					sessionInfo.setSysAccount(account);
//					session.setAttribute(MovieConstant.SESSION_INFO, sessionInfo);// 登录成功，获取用户信息，并存入session
					String sessionId = GenerateRuleUtil.generateUnique(MovieConstant.JOINT, MovieConstant.PROJECT_NAME);
					//设置cookie有效期
					CookieUtil.setCookie(request, response, MovieConstant.SESSION_INFO, sessionId, MovieConstant.DOMAIN, MovieConstant.ROOT_PATH,MovieConstant.COOKIE_VALIDITY_TIME);
					shardedJedisCached.set(sessionId, sessionInfo, MovieConstant.COOKIE_VALIDITY_TIME);
					logger.error("登录成功，用户信息将记录到Cookie中且存储到Shiro中!");
					return modeView;
				}else{
					String enumName=EnumUtil.getNameByValue(SysAccountEnum.STATUS.class, account.getStatus());
					logger.error("登录失败，该用户已"+enumName+"!");
					attributes.addFlashAttribute(MovieConstant.MESSAGES_INFO, "登录失败，该用户已"+enumName+"!");
					return modeView;
				}
			}else{
				attributes.addFlashAttribute(MovieConstant.MESSAGES_INFO, "登录失败，用户名或密码错误！");
				logger.error("登录失败，accountId={}在SysUser中不存在。",new Object[]{account.getAccountId()});
			}
		} else {
			attributes.addFlashAttribute(MovieConstant.MESSAGES_INFO, "登录失败，用户名或密码错误！");
			logger.error("登录失败，用户名或密码错误！");
		}
		return modeView;
	}
	
	/**找回密码页面*/
	@RequestMapping(value = "/findPassword")
	public ModelAndView findPassword(String messages) {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/findPassword");
		modelView.addObject(MovieConstant.MESSAGES_INFO, messages);
		return modelView;
	}
	
	
	//TODO and page
	/**修改密码页面*/  
	@RequestMapping(value = "/updatePassword")
	public ModelAndView updatePassword(String messages) {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/updatePassword");
		modelView.addObject(MovieConstant.MESSAGES_INFO, messages);
		return modelView;
	}
	
	/**获取验证码页面*/
	@RequestMapping(value="getCode.json", method={RequestMethod.POST})
	@ResponseBody
	public ResponseData getCode(HttpServletRequest request,String account,String email) {
		ResponseData rd=new ResponseData();
		if(StringUtil.isEmpty(account,email)){
			logger.error("==========>邮箱，账号不能为空。");
			rd.setMsg("邮箱，账号不能为空。");
			return rd;
		}
		String key=SEND_MAIL_KEY+"_"+account+"_"+email;
		String send_mail_key=shardedJedisCached.get(key);
		if(!StringUtil.isEmpty(send_mail_key)){
			rd.setMsg("10分钟内，只能发送一封邮件。");
			return rd;
		}
		SysAccount sysAccount=sysAccountService.queryByAccount(account);
		if(null==sysAccount){
			logger.error("==========>账号：{}，在数据库中不存在。",new Object[]{account});
			rd.setMsg("账号:"+account+"，不存在。");
			return rd;
		}
		if(!email.equals(sysAccount.getEmail())){
			logger.error("==========>输入的邮箱:{},与您注册时的邮箱:{}不一致。",new Object[]{email,sysAccount.getEmail()});
			rd.setMsg("输入的邮箱与注册时的邮箱不一致。");
			return rd;
		}
		//开始发送邮箱
		try {
			String subject="找回密码-获取验证码";
			StringBuffer sb=new StringBuffer();
			sb.append("<div>");
			String code=getRandom(10000);
			String path=request.getContextPath();//项目名
			int port=request.getServerPort();//端口
			String serverName=request.getServerName();//域名 or localhost
			String URL_Address=serverName+":"+port+path+ACTION_PATH+"/updatePassword.htm";
			sb.append("您于：").append(DateUtil.getStringDate(DateUtil.YYYY_MM_DD_HH_MM_SS)).append("申请的密码找回，验证码为:").append(code);
			sb.append("</div>");
			sb.append("<a ").append("href=").append("'").append(URL_Address).append("'").append(">");
			sb.append("请单击此处连接进行修改密码。");
			sb.append("</a>");
			sb.append("<span>").append("如果浏览器打不开，请复制该URL到你的浏览器打开。").append("</span>");
			MailTool.sendMail(subject, sb.toString(), new String[]{email});
			rd.setMsg("邮件发送成功，请登陆邮箱查看.");
			rd.addData("account", account);
			rd.addData("url","app/appMain/updatePassword.htm");
			rd.addData("email", email);
			rd.addData("success_code", 10000);
			shardedJedisCached.set(key, code, expireSecond);
		} catch (Exception e) {
			logger.error("============>邮件发送失败，失败原因:{}。",new Object[]{JSONObject.toJSONString(e)});
			rd.setMsg(e.getMessage());
		}
		return rd;
	}
	
	public static String getRandom(int maxNumber){
		Random random=new Random();
		int result=random.nextInt(maxNumber);
		int length=String.valueOf(maxNumber).length();
		int resultlength=String.valueOf(result).length();
		String[] hexs={"A", "b", "C", "d", "E", "f","g","h","i","j","k","m","x","y","z","v","u","w" ,"p","q","n"};
		StringBuffer sbs=new StringBuffer();
		if(resultlength<length){
			int count=length-resultlength;
			String resultStr=String.valueOf(result);
			String[] chars=new String[length];
			for(int i=0;i<resultStr.length();i++){
				chars[i]=resultStr.charAt(i)+"";
			}
			for(int i=0;i<count;i++){
				String str=hexs[random.nextInt(hexs.length-1)];
				chars[resultlength+i]=str;
			}
			List<String> list=new ArrayList<String>(Arrays.asList(chars));
			for(int i=0;i<chars.length;i++){
				getValue(random, list, sbs);
			}
		}else{
			sbs.append(result);
		}
		return sbs.toString();
	}
	
	public static void getValue(Random random,List<String> list,StringBuffer sbs){
		int number= list.size();
		int c=random.nextInt(number);
		String value=list.get(c);
		sbs.append(value);
		list.remove(c);
	}
}
