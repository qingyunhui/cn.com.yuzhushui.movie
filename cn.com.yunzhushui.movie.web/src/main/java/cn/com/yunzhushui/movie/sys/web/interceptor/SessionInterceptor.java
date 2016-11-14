
package cn.com.yunzhushui.movie.sys.web.interceptor;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import qing.yun.hui.common.constants.Constant;

/**
 * Description: 
 * 
 * @Date Create on 2016年11月14日
 * @author <a href="mailto:qingyunhui@zuozh.com">qingyunhui</a>
 * @since version1.0 Copyright 2015 ZZJR All Rights Reserved.
 */
public class SessionInterceptor extends HandlerInterceptorAdapter{
	
	protected Logger logger=LoggerFactory.getLogger(SessionInterceptor.class);
	
	private List<String> interceptPath;	//要拦截的路径;
	
	private Integer openTime;//开放时间
	private Integer closeTime;//关闭时间
	private String interceptURL;//拦截的URL
	
	
	/**
	 * 
	 *  预处理、在preHandle中，可以进行编码、安全控制等处理
	 *  
	 *  */
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		String url=request.getServletPath();    
        if(interceptURL==null || url.matches(interceptURL)){    
            Calendar c=Calendar.getInstance();    
            c.setTime(new Date());    
            int now=c.get(Calendar.HOUR_OF_DAY);    
            if(now<openTime || now>=closeTime){    
                request.setAttribute("msg", "注册开放时间：9：00-12：00");    
                request.getRequestDispatcher("/showMessage.htm").forward(request, response);   
                return false;    
            }    
            return true;    
        }
        for (String path : interceptPath) {
            if (url.startsWith(path.trim())) {
                return super.preHandle(request, response, handler);
            }
        }
        Object obj = request.getSession().getAttribute(Constant.MY_SESSION_INFO);
        if (null == obj) {
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }
        return true;    
		
	}

	/** 后处理、在postHandle中，有机会修改ModelAndView； */
	@Override
	public void postHandle(HttpServletRequest request,HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");
	}

	/** 返回处理、在afterCompletion中，可以根据Exception是否为null判断是否发生了异常，进行日志记录。 **/
	@Override
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object handler, Exception ex)throws Exception {
		
		logger.info("");
		
	}

	public void setInterceptPath(List<String> interceptPath) {
		this.interceptPath = interceptPath;
	}

	public void setOpenTime(Integer openTime) {
		this.openTime = openTime;
	}

	public void setCloseTime(Integer closeTime) {
		this.closeTime = closeTime;
	}

	public void setInterceptURL(String interceptURL) {
		this.interceptURL = interceptURL;
	}

}
