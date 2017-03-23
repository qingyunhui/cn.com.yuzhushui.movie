package cn.com.yuzhushui.movie.error.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import qing.yun.hui.common.annotations.ActionAnno;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月5日下午1:52:45
 **/
@Controller
@RequestMapping(ErrorAction.ACTION_PATH)
public class ErrorAction {
	
	protected Logger logger=LoggerFactory.getLogger(ErrorAction.class);
	
	protected static final String ACTION_PATH = "/error";
	
	/**页面未找到**/
	@ActionAnno(action="页面未找到")
	@RequestMapping(value = "/notfind")
	public ModelAndView notfind(HttpServletRequest request,HttpServletResponse response, HttpSession session) {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/notfind");
		return modelView;
	}
	
}
