package cn.com.yuzhushui.movie.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import qing.yun.hui.mailtool.MailTool;
import cn.com.yuzhushui.schedule.job.core.Job;

import com.alibaba.fastjson.JSONObject;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月2日下午6:13:51
 **/
@Component
public class EmailNotificationJob implements Job{

	Logger logger=LoggerFactory.getLogger(EmailNotificationJob.class);
	
	public String execute(String parameters) {
		
		logger.info("==================hello word==================");
		logger.info("==================>>execute successEmailNotificationJob<==================");
		logger.info("==================good by.==================");
		//开始发送邮件
		sendEmail();
		logger.info("=================>success.........");
		return "success。";
	}	
	
	private void sendEmail(){
		try {
			MailTool.sendTextMail(null,"Hello word!","说点什么呢？",new String[]{"280672161@qq.com"});
		} catch (Exception e) {
			logger.error("=======>邮件发送失败，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
		}
	}
}
