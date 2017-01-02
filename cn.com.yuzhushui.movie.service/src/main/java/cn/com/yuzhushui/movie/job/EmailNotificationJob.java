package cn.com.yuzhushui.movie.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.com.yuzhushui.schedule.job.core.Job;
import qing.yun.hui.common.utils.StringUtil;

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
		
		if(StringUtil.isEmpty(parameters)) {
			logger.error("===========>调用EmailNotificationJob.参数不能为null。");
			return "fail";
		}
		logger.info("=================>success.........");
		return "success。";
	}	
}
