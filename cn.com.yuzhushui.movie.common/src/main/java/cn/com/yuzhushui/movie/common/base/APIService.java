package cn.com.yuzhushui.movie.common.base;

import qing.yun.hui.common.struct.baidu.weather.WeatherResponse;
import qing.yun.hui.common.struct.juhe.bus.busline.BuslineResponse;
import qing.yun.hui.common.struct.juhe.bus.buslong.BusLongResponse;
import qing.yun.hui.common.struct.juhe.idcard.IdCardResponse;
import qing.yun.hui.common.struct.juhe.news.NewsTopResponse;
import qing.yun.hui.common.struct.juhe.phone.mobile.MobileResponse;
import qing.yun.hui.common.struct.juhe.phone.telephone.CallerIDTelephoneResponse;
import qing.yun.hui.common.struct.juhe.robot.RobotResponse;
import qing.yun.hui.common.struct.juhe.video.searching.VideoSearchingResponse;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月23日下午4:35:46
 **/
public interface APIService {
	
	RobotResponse callRobotResponse(String content);

	IdCardResponse callIdCardResponse(String cardno);
	
	CallerIDTelephoneResponse callCallerIDTelephoneResponse(String tel);
	
	MobileResponse callMobileResponse(String mobile);
	
	BuslineResponse callBuslineResponse(String  city, String bus);
	
	BusLongResponse callBusLongResponse(String station);
	
	WeatherResponse callBaiduWeatherByResponseData(String location );
	
	VideoSearchingResponse callVideoSearchingResponse(String qname);
	
	NewsTopResponse callNewsTopResponse(String typeName);
}
