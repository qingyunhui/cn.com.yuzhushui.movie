package cn.com.yuzhushui.movie.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.com.yuzhushui.movie.common.bean.SysUser;
import qing.yun.hui.common.constants.Constant;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月20日下午9:23:03
 **/
public class SessionUtil {
	
	private static ServletRequestAttributes getRequestAttr() {
		return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	}

	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	public static HttpServletRequest getRequest() {
		return getRequestAttr().getRequest();
	}

	public static HttpServletResponse getResponse() {
		return getRequestAttr().getResponse();
	}

	public static SysUser getSysUser() {
		SysUser sysUser=(SysUser)getSession().getAttribute(Constant.USER_INFO_SESSION);
		return sysUser;
	}

	/**
	 * 根据类型返回spring管理的所有该类型的bean
	 * 
	 * @Title: getBeansOfType
	 * @param clazz
	 * @return List<T>
	 * @throws
	 */
	public static <T> List<T> getBeansOfType(Class<T> clazz) {
		Map<String, T> beansOfType = getApplication().getBeansOfType(clazz);
		
		List<T> beans = new ArrayList<T>();
		for (T bean : beansOfType.values()) {
			beans.add(bean);
		}
		return beans;
	}

	public static <T> T getBeanOfType(Class<T> clazz) {
		return getApplication().getBean(clazz);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T) getApplication().getBean(name);
	}

	private static WebApplicationContext getApplication() {
		ServletContext servletContext = SessionUtil.getSession().getServletContext();
		return WebApplicationContextUtils.getWebApplicationContext(servletContext);
	}

}
