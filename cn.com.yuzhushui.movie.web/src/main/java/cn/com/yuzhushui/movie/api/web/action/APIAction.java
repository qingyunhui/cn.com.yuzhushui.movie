package cn.com.yuzhushui.movie.api.web.action;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.com.yuzhushui.movie.common.base.ResponseData;
import cn.com.yuzhushui.movie.constant.MovieConstant;
import qing.yun.hui.common.struct.juhe.idcard.IdCardResponse;
import qing.yun.hui.common.struct.juhe.phone.mobile.MobileResponse;
import qing.yun.hui.common.struct.juhe.phone.telephone.CallerIDTelephoneResponse;
import qing.yun.hui.common.utils.StringUtil;
import qing.yun.hui.common.utils.api.ApiUtil;

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
}
