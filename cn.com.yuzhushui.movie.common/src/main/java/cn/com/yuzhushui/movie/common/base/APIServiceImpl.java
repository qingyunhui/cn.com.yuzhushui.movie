package cn.com.yuzhushui.movie.common.base;

import org.springframework.stereotype.Service;

import cn.com.yuzhushui.movie.constant.MovieConstant;
import qing.yun.hui.common.annotations.WarningAnno;
import qing.yun.hui.common.struct.baidu.BaiduConstant;
import qing.yun.hui.common.struct.baidu.weather.WeatherResponse;
import qing.yun.hui.common.struct.juhe.bus.busline.BuslineResponse;
import qing.yun.hui.common.struct.juhe.bus.buslong.BusLongResponse;
import qing.yun.hui.common.struct.juhe.idcard.IdCardResponse;
import qing.yun.hui.common.struct.juhe.news.NewsTopResponse;
import qing.yun.hui.common.struct.juhe.phone.mobile.MobileResponse;
import qing.yun.hui.common.struct.juhe.phone.telephone.CallerIDTelephoneResponse;
import qing.yun.hui.common.struct.juhe.robot.RobotResponse;
import qing.yun.hui.common.struct.juhe.video.searching.VideoSearchingResponse;
import qing.yun.hui.common.utils.api.ApiUtil;
import qing.yun.hui.common.utils.api.WeatherUtil;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月23日下午4:41:46
 **/
@Service("apiService")
public class APIServiceImpl implements APIService{

	@Override
	@WarningAnno(action="机器人问答",returnType=RobotResponse.class)
	public RobotResponse callRobotResponse(String content) {
		return ApiUtil.callRobotResponse(content, MovieConstant.JSON, MovieConstant.GET);
	}

	@Override
	@WarningAnno(action="身份证查询",returnType=IdCardResponse.class)
	public IdCardResponse callIdCardResponse(String cardno) {
		return ApiUtil.callIdCardResponse(cardno, MovieConstant.JSON, MovieConstant.GET);
	}

	@Override
	@WarningAnno(action="手机固话来电显示",returnType=CallerIDTelephoneResponse.class)
	public CallerIDTelephoneResponse callCallerIDTelephoneResponse(String tel) {
		return ApiUtil.callCallerIDTelephoneResponse(tel, MovieConstant.JSON, MovieConstant.GET);
	}

	@Override
	@WarningAnno(action="手机号码归属地",returnType=MobileResponse.class)
	public MobileResponse callMobileResponse(String mobile) {
		return ApiUtil.callMobileResponse(mobile, MovieConstant.JSON, MovieConstant.GET);
	}

	@Override
	@WarningAnno(action="全国公交及路径规划查询",returnType=BuslineResponse.class)
	public BuslineResponse callBuslineResponse(String city, String bus) {
		return ApiUtil.callBuslineResponse(city, bus, MovieConstant.JSON, MovieConstant.GET);
	}

	@Override
	@WarningAnno(action="长途汽车信息",returnType=BusLongResponse.class)
	public BusLongResponse callBusLongResponse(String station) {
		return ApiUtil.callBusLongResponse(station,  MovieConstant.JSON, MovieConstant.GET);
	}

	@Override
	@WarningAnno(action="天气气象预报",returnType=WeatherResponse.class)
	public WeatherResponse callBaiduWeatherByResponseData(String location) {
		return WeatherUtil.callBaiduWeatherByResponseData(BaiduConstant.httpUrl, location , MovieConstant.XML, BaiduConstant.ak);
	}

	@Override
	@WarningAnno(action="影视影讯检索",returnType=VideoSearchingResponse.class)
	public VideoSearchingResponse callVideoSearchingResponse(String qname) {
		return ApiUtil.callVideoSearchingResponse(qname, MovieConstant.JOINT, MovieConstant.GET);
	}

	@Override
	@WarningAnno(action="新闻头条",returnType=NewsTopResponse.class)
	public NewsTopResponse callNewsTopResponse(String typeName) {
		return ApiUtil.callNewsTopResponse(typeName,MovieConstant.JOINT, MovieConstant.GET);
	}

}
