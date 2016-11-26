package cn.com.yuzhushui.movie.common.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.com.yuzhushui.movie.common.bean.SessionInfo;
import cn.com.yuzhushui.movie.constant.MovieConstant;
import lombok.Getter;
import lombok.Setter;
import qing.yun.hui.common.constants.Symbol;
import qing.yun.hui.common.utils.StringUtil;

/***
 ** @category 拦截器
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月24日下午11:33:33
 **/
@Getter
@Setter
public class SessionInterceptor extends HandlerInterceptorAdapter{
	
	private String[] noInterceptors;	//不须要拦截的url
	
	private String[] registers;		   //须要注册的url
	
	private String[] logins;		 //须要登陆的url
	
	@Value("#{properties['loginPath']}")
	private String loginPath;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		String curUrl = request.getServletPath();
		if(null==noInterceptors) return super.preHandle(request, response, handler);
		curUrl=StringUtil.truncateBothCharact(curUrl, Symbol.SLASH);
		for(String url:noInterceptors){
			if(curUrl.trim().equals(url.trim())){
				return super.preHandle(request, response, handler);
			}
		}
		String contextPath=request.getServletContext().getContextPath();
		SessionInfo seesionInfo=(SessionInfo)request.getSession().getAttribute(MovieConstant.SESSION_INFO);
		if(null==seesionInfo){
			String appLoginPath=null;
			if(!loginPath.startsWith(Symbol.SLASH)){
				appLoginPath=contextPath+Symbol.SLASH+loginPath;
			}else{
				appLoginPath=contextPath+loginPath;
			}
			response.sendRedirect(appLoginPath);
			return false;
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)throws Exception {
		System.out.println("****************==postHandle==****************");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception {
		System.out.println("****************==afterCompletion==****************");
	}
	
}
