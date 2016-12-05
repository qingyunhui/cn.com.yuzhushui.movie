package cn.com.yuzhushui.movie.common.Interceptor;

import java.lang.reflect.Method;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import qing.yun.hui.common.annotations.RepeatSubmitAnno;
import qing.yun.hui.common.constants.Constant;

/***
 ** @category 表单重复提交拦截器...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月5日上午10:31:01
 **/
public class RepeatSubmitInterceptor extends HandlerInterceptorAdapter {
	
	Logger logger=LoggerFactory.getLogger(RepeatSubmitInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		if(handler instanceof HandlerMethod){
			HandlerMethod hm=(HandlerMethod)handler;
			Method method=hm.getMethod();
			RepeatSubmitAnno rsa=method.getAnnotation(RepeatSubmitAnno.class);
			if(null!=rsa){
				boolean save=rsa.save();
				if(save){
					//生成一个token唯一标识码、存储到session中。
					String token=UUID.randomUUID().toString();
					request.getSession().setAttribute(Constant.TOKEN, token);
				}
				boolean remove=rsa.remove();
				if(remove){
					if(isRepeatSubmit(request)){
						logger.error("==========>请不要重复提交表单。<==========");
						return false;
					}
					request.getSession().removeAttribute(Constant.TOKEN);
				}
			}
			return true;
		}else{
			return super.preHandle(request, response, handler);
		}
	}
	
	 /**
	  * <p>校验是否重复提交表单</p>
	  * @param request
	  * @return true or false
	  * */
	 protected boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession().getAttribute(Constant.TOKEN);
        if (null==serverToken) {
            return true;
        }
        String clinetToken = request.getParameter(Constant.TOKEN);
        if (null==clinetToken) {
            return true;
        }
        if (!serverToken.equals(clinetToken)) {
            return true;
        }
        return false;
    }
	
}
