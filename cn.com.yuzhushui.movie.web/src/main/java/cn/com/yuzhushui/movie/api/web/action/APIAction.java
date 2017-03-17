package cn.com.yuzhushui.movie.api.web.action;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import qing.yun.hui.common.struct.baidu.BaiduConstant;
import qing.yun.hui.common.struct.baidu.weather.WeatherResponse;
import qing.yun.hui.common.struct.juhe.JuheEnum;
import qing.yun.hui.common.struct.juhe.bus.busline.BuslineResponse;
import qing.yun.hui.common.struct.juhe.bus.buslong.BusLongResponse;
import qing.yun.hui.common.struct.juhe.idcard.IdCardResponse;
import qing.yun.hui.common.struct.juhe.news.NewsTopResponse;
import qing.yun.hui.common.struct.juhe.phone.mobile.MobileResponse;
import qing.yun.hui.common.struct.juhe.phone.telephone.CallerIDTelephoneResponse;
import qing.yun.hui.common.struct.juhe.video.searching.VideoSearchingResponse;
import qing.yun.hui.common.utils.EnumUtil;
import qing.yun.hui.common.utils.StringUtil;
import qing.yun.hui.common.utils.api.ApiUtil;
import qing.yun.hui.common.utils.api.WeatherUtil;
import cn.com.yuzhushui.movie.common.base.ResponseData;
import cn.com.yuzhushui.movie.constant.MovieConstant;

import com.alibaba.fastjson.JSONObject;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月16日下午9:05:48
 **/
@Controller
@RequestMapping(APIAction.ACTION_PATH)
public class APIAction {
	
	protected Logger logger=LoggerFactory.getLogger(APIAction.class);
	protected static final String ACTION_PATH = "/api";
	
	//机器人问答
	@RequestMapping(value="/show")
	public ModelAndView show() {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/show");
		return modelView;
	}
	
	//身份证查询
	@RequestMapping(value="/idCard")
	public ModelAndView IDCard() {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/idCard");
		return modelView;
	}
	
	@RequestMapping(value="getIdCard.json", method={RequestMethod.POST})
	@ResponseBody
	public ResponseData getIdCard(HttpServletRequest request,String cardno) {
		ResponseData rd=new ResponseData();
		if(StringUtil.isEmpty(cardno)){
			rd.setMsg("请输入要查询的身份证号...");
			return rd;
		}
		try {
			IdCardResponse card= ApiUtil.callIdCardResponse(cardno, MovieConstant.JSON, MovieConstant.GET);
			if(null==card){
				rd.setMsg("未查询到身份证号："+cardno+"，的信息.");
				return rd;
			}
			rd.addData(MovieConstant.SUCCESS_CODE, 10000);
			rd.addData(MovieConstant.ENTITY, card);
			logger.info("=======>查询到的身份证号："+cardno+"，的信息为："+JSONObject.toJSONString(cardno));
		} catch (Exception e) {
			rd.setMsg(e.getMessage());
		}
		return rd;
	}
	
	//手机固话来电显示IDTelephone
	@RequestMapping(value="/callerIdTelephone")
	public ModelAndView callerIdTelephone() {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/callerIdTelephone");
		return modelView;
	}
	
	@RequestMapping(value="getCallerIdTelephone.json", method={RequestMethod.POST})
	@ResponseBody
	public ResponseData getCallerIdTelephone(HttpServletRequest request,String tel) {
		ResponseData rd=new ResponseData();
		if(StringUtil.isEmpty(tel)){
			rd.setMsg("请输入要查询的手机固话...");
			return rd;
		}
		try {
			CallerIDTelephoneResponse callerIDTelephone= ApiUtil.callCallerIDTelephoneResponse(tel, MovieConstant.JSON, MovieConstant.GET);
			if(null==callerIDTelephone){
				rd.setMsg("未查询到手机固话："+tel+"，的信息.");
				return rd;
			}
			rd.addData(MovieConstant.SUCCESS_CODE, 10000);
			rd.addData(MovieConstant.ENTITY, callerIDTelephone);
			logger.info("=======>查询到的手机固话："+tel+"，的信息为："+JSONObject.toJSONString(tel));
		} catch (Exception e) {
			rd.setMsg(e.getMessage());
		}
		return rd;
	}
	
	//手机号归属地Mobile
	@RequestMapping(value="/mobile")
	public ModelAndView mobile() {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/mobile");
		return modelView;
	}
	
	@RequestMapping(value="getMobile.json", method={RequestMethod.POST})
	@ResponseBody
	public ResponseData getMobile(HttpServletRequest request,String mobile) {
		ResponseData rd=new ResponseData();
		if(StringUtil.isEmpty(mobile)){
			rd.setMsg("请输入要查询的手机号...");
			return rd;
		}
		try {
			MobileResponse mobileResponse=ApiUtil.callMobileResponse(mobile, MovieConstant.JSON, MovieConstant.GET);
			if(null==mobileResponse){
				rd.setMsg("未查询到手机号："+mobile+"，的信息.");
				return rd;
			}
			rd.addData(MovieConstant.SUCCESS_CODE, 10000);
			rd.addData(MovieConstant.ENTITY, mobileResponse);
			logger.info("=======>查询到的手机号："+mobile+"，的信息为："+JSONObject.toJSONString(mobile));
		} catch (Exception e) {
			rd.setMsg(e.getMessage());
		}
		return rd;
	}
	
	//全国公交及路径规划查询
	@RequestMapping(value="/busline")
	public ModelAndView busline() {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/busline");
		return modelView;
	}
	
	@RequestMapping(value="getBusline.json", method={RequestMethod.POST})
	@ResponseBody
	public ResponseData getBusline(HttpServletRequest request,String city ,String bus) {
		ResponseData rd=new ResponseData();
		if(StringUtil.isEmpty(city,bus)){
			rd.setMsg("请输入要查询的城市及公交...");
			return rd;
		}
		try {
			BuslineResponse  response= ApiUtil.callBuslineResponse(city, bus, MovieConstant.JSON, MovieConstant.GET);
			if(null==response){
				rd.setMsg("未查询到【"+city+"，"+bus+"】公交的信息.");
				return rd;
			}
			rd.addData(MovieConstant.SUCCESS_CODE, 10000);
			rd.addData(MovieConstant.ENTITY, response);
			logger.info("=======>查询到的【"+city+"，"+bus+"】的信息为："+JSONObject.toJSONString(response));
		} catch (Exception e) {
			rd.setMsg(e.getMessage());
		}
		return rd;
	}
	
	//长途汽车信息
	@RequestMapping(value="/buslong")
	public ModelAndView buslong() {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/buslong");
		return modelView;
	}
	
	@RequestMapping(value="getBuslong.json", method={RequestMethod.POST})
	@ResponseBody
	public ResponseData getBuslong(HttpServletRequest request,String station) {
		ResponseData rd=new ResponseData();
		if(StringUtil.isEmpty(station)){
			rd.setMsg("请输入要查询的城市...");
			return rd;
		}
		try {
			BusLongResponse response= ApiUtil.callBusLongResponse(station,  MovieConstant.JSON, MovieConstant.GET);
			if(null==response){
				rd.setMsg("未查询到【"+station+"】长途汽车的信息.");
				return rd;
			}
			rd.addData(MovieConstant.SUCCESS_CODE, 10000);
			rd.addData(MovieConstant.ENTITY, response);
			logger.info("=======>查询到的【"+station+"】长途汽车的信息为："+JSONObject.toJSONString(response));
		} catch (Exception e) {
			rd.setMsg(e.getMessage());
		}
		return rd;
	}
	
	//天气
	@RequestMapping(value="/weather")
	public ModelAndView weather() {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/weather");
		return modelView;
	}
	
	@RequestMapping(value="getWeather.json", method={RequestMethod.POST})
	@ResponseBody
	public ResponseData getWeather(HttpServletRequest request,String location ) {
		ResponseData rd=new ResponseData();
		if(StringUtil.isEmpty(location)){
			rd.setMsg("请输入要查询的城市名称或经纬度.");
			return rd;
		}
		try {
			WeatherResponse response=WeatherUtil.callBaiduWeatherByResponseData(BaiduConstant.httpUrl, location , MovieConstant.XML, BaiduConstant.ak);
			if(null==response){
				rd.setMsg("未查询到【"+location+"】天气信息.");
				return rd;
			}
			rd.addData(MovieConstant.SUCCESS_CODE, 10000);
			rd.addData(MovieConstant.ENTITY, response);
			logger.info("=======>查询到的【"+response+"】天气的信息为："+JSONObject.toJSONString(response));
		} catch (Exception e) {
			rd.setMsg(e.getMessage());
		}
		return rd;
	}
	
	//影视搜索
	@RequestMapping(value="/videoSearching")
	public ModelAndView videoSearching() {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/videoSearching");
		return modelView;
	}
	
	@RequestMapping(value="getVideoSearching.json", method={RequestMethod.POST})
	@ResponseBody
	public ResponseData getVideoSearching(HttpServletRequest request,String qname ) {
		ResponseData rd=new ResponseData();
		if(StringUtil.isEmpty(qname)){
			rd.setMsg("请输入要查询的影视名称.");
			return rd;
		}
		try {
			VideoSearchingResponse response= ApiUtil.callVideoSearchingResponse(qname, MovieConstant.JOINT, MovieConstant.GET);
			if(null==response){
				rd.setMsg("未查询到【"+qname+"】影视的信息.");
				return rd;
			}
			rd.addData(MovieConstant.SUCCESS_CODE, 10000);
			rd.addData(MovieConstant.ENTITY, response);
			logger.info("=======>查询到的【"+qname+"】影视的信息为："+JSONObject.toJSONString(response));
		} catch (Exception e) {
			rd.setMsg(e.getMessage());
		}
		return rd;
	}
	
	//新闻头条
	@RequestMapping(value="/todayTop")
	public ModelAndView todayTop() {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/newsTop");
		return modelView;
	}
	
	@RequestMapping(value="getTodayTop.json", method={RequestMethod.POST})
	@ResponseBody
	public ResponseData getTodayTop(HttpServletRequest request,String type ) {
		ResponseData rd=new ResponseData();
		if(StringUtil.isEmpty(type)){
			rd.setMsg("请输入要查询的头条分类名称.");
			return rd;
		}
		try {
			String typeName=EnumUtil.getNameByCode(JuheEnum.NewsTopType.class, type);
			if(StringUtil.isEmpty(typeName)){
				typeName=EnumUtil.getCodeByName(JuheEnum.NewsTopType.class, type);
			}else{
				typeName=EnumUtil.getCodeByName(JuheEnum.NewsTopType.class, typeName);
			}
			if(StringUtil.isEmpty(typeName))typeName=type;
			NewsTopResponse response= ApiUtil.callNewsTopResponse(typeName,MovieConstant.JOINT, MovieConstant.GET);
			if(null==response){
				rd.setMsg("未查询到【"+type+"】头条分类信息.");
				return rd;
			}
			rd.addData(MovieConstant.SUCCESS_CODE, 10000);
			rd.addData(MovieConstant.ENTITY, response);
			logger.info("=======>查询到的【"+type+"】头条分类信息为："+JSONObject.toJSONString(response));
		} catch (Exception e) {
			rd.setMsg(e.getMessage());
		}
		return rd;
	}
}
