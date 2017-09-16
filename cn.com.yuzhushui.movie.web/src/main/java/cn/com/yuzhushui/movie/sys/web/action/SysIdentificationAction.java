package cn.com.yuzhushui.movie.sys.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.yuzhushui.movie.cache.ShardedJedisCached;
import cn.com.yuzhushui.movie.common.base.APIService;
import cn.com.yuzhushui.movie.common.base.ResponseData;
import cn.com.yuzhushui.movie.common.bean.SessionInfo;
import cn.com.yuzhushui.movie.common.util.SessionUtil;
import cn.com.yuzhushui.movie.constant.MovieConstant;
import cn.com.yuzhushui.movie.enums.SysUserEnum;
import cn.com.yuzhushui.movie.sys.biz.entity.SysUser;
import cn.com.yuzhushui.movie.sys.biz.service.SysUserService;
import qing.yun.hui.common.annotations.ActionAnno;
import qing.yun.hui.common.enums.Sex;
import qing.yun.hui.common.struct.juhe.idcard.IdCardResponse;
import qing.yun.hui.common.utils.EnumUtil;
import qing.yun.hui.common.utils.StringUtil;
import qing.yun.hui.common.utils.ValidateUtil;

/***
 ** @category 身份认证...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年9月15日下午9:49:56
 **/
@Controller
@RequestMapping(SysIdentificationAction.ACTION_PATH)
public class SysIdentificationAction {
	
	Logger logger=LoggerFactory.getLogger(getClass());
	
	protected static final String ACTION_PATH="/sys/sysUser/";

	@Autowired
	ShardedJedisCached shardedJedisCached;
	
	@Autowired
	private APIService apiService;
	
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping(value = "certification", method = { RequestMethod.POST,RequestMethod.GET })
	@ActionAnno(action="身份认证")
	public ModelAndView certification() {
		ModelAndView modelAndView=new ModelAndView();
		SysUser user=SessionUtil.getSysUser();
		//如果已经认证了，直接跳转到个人信息详情页面
		if(SysUserEnum.State.SUCCESS_CERTIFICATION.getValue()==user.getState().intValue()){
			return certificationInfo();
		}
		modelAndView.addObject(MovieConstant.ENTITY, user);
		modelAndView.setViewName(ACTION_PATH+"certification");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "certificationInfo", method = { RequestMethod.POST,RequestMethod.GET })
	@ActionAnno(action="个人信息展示")
	public ModelAndView certificationInfo() {
		ModelAndView modelAndView=new ModelAndView();
		SysUser user=SessionUtil.getSysUser();
		if(SysUserEnum.State.SUCCESS_CERTIFICATION.getValue()!=user.getState().intValue()){
			return certification();
		}
		//如果已经认证了，直接跳转到个人信息详情页面
		modelAndView.addObject(MovieConstant.ENTITY, user);
		modelAndView.setViewName(ACTION_PATH+"certificationInfo");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "doCertification.json", method = { RequestMethod.POST,RequestMethod.GET })
	@ActionAnno(action="身份认证-提交")
	@ResponseBody
	public ResponseData doCertification(String IDCard,String name) {
		ResponseData responseData=new ResponseData();
		SysUser user=SessionUtil.getSysUser();
		if(Integer.valueOf(SysUserEnum.State.SUCCESS_CERTIFICATION.getValue()).equals(user.getState())){
			responseData.setMsg("认证成功.");
			logger.info("---------------->用户已是认证成功的用户.无需再次认证.");
			responseData.addData(MovieConstant.SUCCESS_CODE, 10000);
			responseData.addData("url","app/appMain/myMain.htm");
			return responseData;
		}
		if(StringUtil.isEmpty(IDCard,name)){
			logger.error("==========>身份证:{}、姓名:{}，不能为空.",new Object[]{IDCard,name});
			responseData.setMsg("邮箱不能为空。");
			return responseData;
		}
		if(!ValidateUtil.isIDCard(IDCard)){
			logger.error("==========>身份证号{}输入有误.",new Object[]{IDCard});
			responseData.setMsg("请输入正确的身份证号.");
			return responseData;
		}
		if(!ValidateUtil.isName(name)){
			logger.error("==========>姓名输入有误.",new Object[]{name});
			responseData.setMsg("请输入正确姓名.");
			return responseData;
		}
		Integer userId=user.getUserId();
		String key="ID_CARD_CERTIFICATION"+"_"+userId+"_"+IDCard;
		String id_card_certification_key=shardedJedisCached.get(key);
		if(!StringUtil.isEmpty(id_card_certification_key)){
			logger.info("================>身份证认证太频繁，key={}.",new Object[]{key});
			responseData.setMsg("身份认证太频繁.");
			responseData.addData(MovieConstant.SUCCESS_CODE, 30);
			return responseData;
		}
		shardedJedisCached.set(key, "YES",20);
		IdCardResponse card= apiService.callIdCardResponse(IDCard);
		if(null==card){
			logger.info("您输入的身份证号{}有误,请重新输入.",new Object[]{IDCard});
			responseData.setMsg("您输入的身份证号有误,请重新输入.");
			return responseData;
		}
		SysUser updateUser=new SysUser();
		updateUser.setUserId(userId);
		updateUser.setArea(card.getArea());
		updateUser.setName(name);
		updateUser.setIdcard(IDCard);
		updateUser.setState(SysUserEnum.State.SUCCESS_CERTIFICATION.getValue());
		String sex=EnumUtil.getCodeByName(Sex.class, card.getSex());
		updateUser.setSex(null==sex?null:Integer.parseInt(sex));
		updateUser.setAge(getAge(card.getBirthday()));
		sysUserService.update(updateUser);
		logger.info("========>身份证认证成功啦.");
		responseData.addData(MovieConstant.SUCCESS_CODE, 10000);
		responseData.setMsg("认证成功.");
		responseData.addData("url","app/appMain/myMain.htm");
		//认证成功后、实时更新redis缓存
		SessionInfo sessionInfo= SessionUtil.getSessionInfo();
		BeanUtils.copyProperties(updateUser,user);
		sessionInfo.setSysUser(user);
		shardedJedisCached.set(SessionUtil.getSessionId(), sessionInfo, MovieConstant.COOKIE_VALIDITY_TIME);
		return responseData;
	}
	
	@SuppressWarnings("deprecation")
	private int getAge(String birthday){
		int age=0;
		try {
			Date date = new SimpleDateFormat("yyyy年MM月dd日").parse(birthday);
			age = (new Date().getYear() - date.getYear());
		} catch (Exception e) {
			logger.error("",e);
		}
		return age;
	}
	
}
