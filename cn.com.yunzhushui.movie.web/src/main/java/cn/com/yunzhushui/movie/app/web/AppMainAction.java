package cn.com.yunzhushui.movie.app.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	
	protected static final String MODEL_PATH = "/app/appMain";
	
	public static final String SHOWED_INTRODUCE = "showed_introduce";
	
	/**引导页*/
	@RequestMapping(value = "/introduce")
	public ModelAndView introduce(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		ModelAndView modelView = new ModelAndView("redirect:/main/myMain.htm");
		
		/***
		 * @1.如果用户未注册则进入引导页...
		 * @2.用户已注册，判断用户是否是一个月内免登陆(数据库有标识字段)、查询redis缓存中是否存在这个标识，
		 *    @2.1如果用户是一个月内免登陆、且还在有效期、则跳转到主页面。
		 *    @2.2如果用户不是一个月内免登陆、则跳转到登陆页面。
		 * */
		
//		modelView = new ModelAndView("redirect:" + ACTION_PATH + "/login.htm");// 进入登录页面(自动登录考虑页面的功能)
//		modelView = new ModelAndView(ACTION_PATH + "/introduce");// 进入引导页面
		
		return modelView;
	}
}
