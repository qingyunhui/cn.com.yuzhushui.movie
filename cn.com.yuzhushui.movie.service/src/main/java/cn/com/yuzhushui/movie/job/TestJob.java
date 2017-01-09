package cn.com.yuzhushui.movie.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.com.yuzhushui.schedule.job.core.Job;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年1月9日上午11:07:31
 **/
@Component
public class TestJob implements Job{
	
	private Logger logger=LoggerFactory.getLogger(TestJob.class);

	@Override
	public String execute(String parameters) {

		logger.info("*************** ====== ***************");
		logger.info("*************** 测试job ***************");
		logger.info("*************** ====== ***************");
		
		return "success@TestJob";
	}
}
