package cn.com.yuzhushui.movie.common.util;

import java.util.ArrayList;
import java.util.Iterator;
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

import cn.com.yuzhushui.movie.common.bean.SessionInfo;
import cn.com.yuzhushui.movie.constant.MovieConstant;
import cn.com.yuzhushui.movie.sys.biz.entity.SysUser;
import qing.yun.hui.common.utils.StringUtil;

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
	
	public static SessionInfo getSessionInfo(){
		return (SessionInfo)getSession().getAttribute(MovieConstant.SESSION_INFO);
	}
	
	public static SysUser getSysUser() {
		SysUser appUserInfo=getSessionInfo().getSysUser();
		return appUserInfo;
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
		Iterator<String> iterator=beansOfType.keySet().iterator();
		while(iterator.hasNext()){
			String key=iterator.next();
			if(StringUtil.isEmpty(key)) continue;
			if(!key.endsWith("Impl"))continue;
			T bean =beansOfType.get(key);
			/*
			 * T t =beansOfType.get(key);
				System.out.println("key:"+key+",t:"+t);
				key:createrServiceImpl,t:cn.com.yuzhushui.movie.common.base.CreaterServiceImpl@11959727
				key:editorServiceImpl,t:cn.com.yuzhushui.movie.common.base.EditorServiceImpl@73f68a75
				key:pluginService,t:org.apache.ibatis.binding.MapperProxy@3efc42cd
			*/
			beans.add(bean);
		}
		/*for (T bean : beansOfType.values()) {
			beans.add(bean);
		}*/
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
