package cn.com.yuzhushui.movie.common.Interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;

import cn.com.yuzhushui.movie.cache.ShardedJedisCached;
import cn.com.yuzhushui.movie.common.bean.SessionInfo;
import cn.com.yuzhushui.movie.constant.MovieConstant;
import cn.com.yuzhushui.movie.enums.SysUserEnum;
import lombok.Getter;
import lombok.Setter;
import qing.yun.hui.common.constants.SymbolConstant;
import qing.yun.hui.common.utils.CookieUtil;
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
	
	private String[] identifications; //需要身份认证
	
	/*@Value("#{properties['loginPath']}")*/
	private static final String loginPath="app/appMain/login.htm";
	
	@Autowired
	private ShardedJedisCached shardedJedisCached;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		String curUrl = request.getServletPath();
		if(null==noInterceptors) return super.preHandle(request, response, handler);
		curUrl=StringUtil.truncateBothCharact(curUrl, SymbolConstant.SLASH);
		for(String url:noInterceptors){
			if(curUrl.trim().equals(url.trim())){
				return super.preHandle(request, response, handler);
			}
		}
		
		String sessionId=CookieUtil.getCookieValueByName(request, MovieConstant.SESSION_INFO);
		if(StringUtil.isEmpty(sessionId)){
			String appLoginPath=getLoginPath(request);
			Cookie cookie=CookieUtil.getCookieByName(request, MovieConstant.SESSION_INFO);
			CookieUtil.deleteCookie(request, response, cookie, MovieConstant.DOMAIN, MovieConstant.ROOT_PATH);
			response.sendRedirect(appLoginPath);
			return false;
		}
		
		String sessionInfo= shardedJedisCached.get(sessionId);
		
		if(StringUtil.isEmpty(sessionInfo)){
			//删除cookie中的session
			String appLoginPath=getLoginPath(request);
			Cookie cookie=CookieUtil.getCookieByName(request, MovieConstant.SESSION_INFO);
			CookieUtil.deleteCookie(request, response, cookie, MovieConstant.DOMAIN, MovieConstant.ROOT_PATH);
			response.sendRedirect(appLoginPath);
			return false;
		}
		
		//用户在有操作时，实时更新cookie有效期..
		SessionInfo mySessionInfo=JSONObject.parseObject(sessionInfo, SessionInfo.class);
		if(null!=mySessionInfo){
			//cookie 与 redis有效期保持一致性...
			CookieUtil.setCookie(request, response, MovieConstant.SESSION_INFO, sessionId, MovieConstant.DOMAIN, MovieConstant.ROOT_PATH, MovieConstant.COOKIE_VALIDITY_TIME);
			shardedJedisCached.set(sessionId, sessionInfo, MovieConstant.COOKIE_VALIDITY_TIME);
			boolean identification=identification(curUrl, mySessionInfo.getSysUser().getState());
			if(identification){
				request.setAttribute("AUTHENTICATION_MSG","请先进行身份认证哦.");
				request.setAttribute("AUTHENTICATION_URL", "sys/sysUser/certification.htm");
			}
			return super.preHandle(request, response, handler);
		}
		
		return super.preHandle(request, response, handler);
	}

	/**
	 * <p>根据给定url及用户状态、如果是已认证的则不需要拦截，否则拦截</p>
	 * @param url
	 * @param state
	 * @return boolean
	 * */
	private boolean identification(String url,Integer state){
		Boolean flag=false;
		if(null!=state && state.intValue()==SysUserEnum.State.SUCCESS_CERTIFICATION.getValue()) return flag;
		if(null!=identifications && identifications.length>0){
			for(String sourceUrl:identifications){
				if(!StringUtil.isEmpty(sourceUrl) && url.equals(sourceUrl)){
					return Boolean.TRUE;
				}
			}
		}
		return flag;
	}
	
	/**
	 * <p>获取首页登陆url</p>
	 *@param request
	 *@param loginPath
	 *@return 获取登陆路径 
	 **/
	public String getLoginPath(HttpServletRequest request){
		String appLoginPath=null;
		String contextPath=request.getServletContext().getContextPath();
		if(!loginPath.startsWith(SymbolConstant.SLASH)){
			appLoginPath=contextPath+SymbolConstant.SLASH+loginPath;
		}else{
			appLoginPath=contextPath+loginPath;
		}
		return appLoginPath;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception {
	}
	
}
