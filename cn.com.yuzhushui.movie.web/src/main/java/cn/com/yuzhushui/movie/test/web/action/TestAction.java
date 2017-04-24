package cn.com.yuzhushui.movie.test.web.action;

import java.util.Random;

import javax.jms.Destination;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import qing.yun.hui.activemq.consumer.DefaultQueueConsumers;
import qing.yun.hui.activemq.consumer.DefaultTopicConsumers;
import qing.yun.hui.activemq.producer.DefaultQueueProducers;
import qing.yun.hui.activemq.producer.DefaultTopicProducers;

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
	private DefaultQueueProducers defaultQueueProducers;
	
	@Autowired
	private DefaultTopicProducers defaultTopicProducers;
	
	@Autowired
	private DefaultQueueConsumers defaultQueueConsumers;
	
	@Autowired
	private DefaultTopicConsumers defaultTopicConsumers;
	
	@Autowired
	private Destination topicDestination ;
	
	@RequestMapping(value="/sendMqMsg")
	public ModelAndView show() {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/activeMq");
		
		String msg="hello word:"+new Random().nextInt(10000);
		
		//向queue送消息 
		defaultQueueProducers.sendMessage(msg);
		
		//向topic发送消息
		defaultTopicProducers.sendMessage(msg);
		return modelView;
	}
	
	@RequestMapping(value="/getMqMsg")
	public ModelAndView getMqMsg() {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/activeMq");
		
		try {
			
			TextMessage topic= defaultTopicConsumers.receive();
			
			TextMessage queue= defaultQueueConsumers.receive();
			
			if(null!=topic){
				logger.info("topic中拿到的消息："+topic.getText());
			}else{
				logger.info("******topic中暂无消息***********");
			}

			if(null!=queue){
				logger.info("queue中拿到的消息："+queue.getText());
			}else{
				logger.info("******topic中暂无消息***********");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelView;
	}
	
	
}
