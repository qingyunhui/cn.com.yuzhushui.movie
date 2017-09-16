package cn.com.yuzhushui.movie.common.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import qing.yun.hui.common.utils.CookieUtil;
import qing.yun.hui.common.utils.StringUtil;
import cn.com.yuzhushui.movie.cache.ShardedJedisCached;
import cn.com.yuzhushui.movie.common.bean.SessionInfo;
import cn.com.yuzhushui.movie.constant.MovieConstant;
import cn.com.yuzhushui.movie.sys.biz.entity.SysAccount;
import cn.com.yuzhushui.movie.sys.biz.entity.SysUser;

import com.alibaba.fastjson.JSONObject;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月20日下午9:23:03
 **/
public class SessionUtil {

	private static Logger logger=LoggerFactory.getLogger(SessionUtil.class);
	
	private static ServletRequestAttributes getRequestAttr() {
		return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	}

	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttr= getRequestAttr();
		if(null==requestAttr) return null;
		return requestAttr.getRequest();
	}

	public static HttpServletResponse getResponse() {
		ServletRequestAttributes requestAttr= getRequestAttr();
		if(null==requestAttr) return null;
		return requestAttr.getResponse();
	}
	
	public static String getSessionId(){
		return CookieUtil.getCookieValueByName(getRequest(), MovieConstant.SESSION_INFO);
	}
	
	public static SessionInfo getSessionInfo(){
		String sessionId=CookieUtil.getCookieValueByName(getRequest(), MovieConstant.SESSION_INFO);
		logger.info("===========>sessionId={}<===========",new Object[]{sessionId});
		if(StringUtil.isEmpty(sessionId)) return null;
		ShardedJedisCached shardedJedisCached = getBeanOfType(ShardedJedisCached.class);
		String sessionJson= shardedJedisCached.get(sessionId);
		try {
			return JSONObject.parseObject(sessionJson, SessionInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
//		return (SessionInfo)getSession().getAttribute(MovieConstant.SESSION_INFO);
	}
	
	public static SysUser getSysUser() {
		SessionInfo sessionInfo= getSessionInfo();
		if(null==sessionInfo) return null;
		SysUser appUserInfo=sessionInfo.getSysUser();
		return appUserInfo;
	}
	
	public static SysAccount getSysAccount(){
		SessionInfo sessionInfo= getSessionInfo();
		if(null==sessionInfo) return null;
		SysAccount sysAccount=sessionInfo.getSysAccount();
		return sysAccount;
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
