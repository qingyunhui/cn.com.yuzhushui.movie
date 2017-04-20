package cn.com.yuzhushui.movie.test.web.action;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import qing.yun.hui.activemq.producer.DefaultProducers;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年4月20日下午3:37:53
 **/
@Controller
@RequestMapping(TestAction.ACTION_PATH)
public class TestAction {

	protected Logger logger=LoggerFactory.getLogger(TestAction.class);
	protected static final String ACTION_PATH = "/test";
	
	@Autowired
	private DefaultProducers defaultProducers;
	
	@RequestMapping(value="/activeMq")
	public ModelAndView show() {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/activeMq");
		String msg="hello word:"+new Random().nextInt(10000);
		defaultProducers.sendMessage(msg);
		logger.info("***********发送的消息："+msg);
		return modelView;
	} 
	
}
