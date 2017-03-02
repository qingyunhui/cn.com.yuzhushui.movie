package cn.com.yuzhushui.movie.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import qing.yun.hui.common.struct.baidu.BaiduConstant;
import qing.yun.hui.common.utils.api.WeatherUtil;
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
		String httpUrl=BaiduConstant.httpUrl;
		String location="长沙";
		String output="xml";
		String ak=BaiduConstant.ak;
		String subject ="==============>"+location+"未来四天，天气预报状态。==============>";
		String content =null;
		String[] emails=null;
		try {
			content=WeatherUtil.callBaiduWeatherByResponse(httpUrl,location,output,ak);
			emails = new String[] {"280672161@qq.com"};
			boolean success=MailTool.sendTextMail(null,"*********"+subject+"**************",content,emails);
			logger.info("========邮件发送{}.",new Object[]{success?"成功":"失败"});
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("=======>邮件发送失败，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
			MailTool.sendTextMail(null,"邮件发送失败","失败原因，"+e.getMessage()+"，待发送的用户有："+emails,null);
		}
	}
	
	public static void main(String[] args){
		EmailNotificationJob job=new EmailNotificationJob();
		job.sendEmail();
	}
}
