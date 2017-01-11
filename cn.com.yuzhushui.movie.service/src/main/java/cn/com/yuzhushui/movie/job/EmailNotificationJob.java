package cn.com.yuzhushui.movie.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import qing.yun.hui.common.struct.weather.ResponseData;
import qing.yun.hui.common.struct.weather.WeatherUtil;
import qing.yun.hui.common.struct.weather.WetherConstant;
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
	
	/*
	245634919@qq.com
	360576481@qq.com
	405098982@qq.com
	475843190@qq.com
	596561014@qq.com
	625174370@qq.com
	627699563@qq.com
	863864033@qq.com
	908006406@qq.com
	1066778175@qq.com
	1094093249@qq.com
	1191276440@qq.com
	1298101599@qq.com
	1427971840@qq.com
	1443211662@qq.com
	1451847709@qq.com
	3233574897@qq.com
	*/
	
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
		String httpUrl=WetherConstant.httpUrl;
		String location="杭州";
		String output="xml";
		String ak=WetherConstant.ak;
		String subject =location+"未来四天，天气预报状态。";
		String content =null;
		try {
			content=WeatherUtil.callBaiduWeatherByResponse(httpUrl,location,output,ak);
			String[] emails = new String[] { "qingyunhui@zuozh.com","627699563@qq.com","280672161@qq.com"};
			MailTool.sendTextMail(null,subject,content,emails);
		} catch (Exception e) {
			logger.error("=======>邮件发送失败，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
		}
	}
	
	public static void main(String[] args){
		EmailNotificationJob job=new EmailNotificationJob();
		job.sendEmail();
	}
}
