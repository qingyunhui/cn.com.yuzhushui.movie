package cn.com.yuzhushui.movie.robot.web.action;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import qing.yun.hui.common.struct.juhe.robot.RobotResponse;
import qing.yun.hui.common.utils.api.ApiUtil;
import cn.com.yuzhushui.movie.common.base.ResponseData;
import cn.com.yuzhushui.movie.constant.MovieConstant;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月16日下午3:15:37
 **/
@Controller
@RequestMapping(RobotAction.ACTION_PATH)
public class RobotAction {

protected Logger logger=LoggerFactory.getLogger(RobotAction.class);
	
	protected static final String ACTION_PATH = "/robot";
	
	//机器人问答
	@RequestMapping(value="/answers")
	public ModelAndView answers() {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/answers");
		return modelView;
	}
	
	@RequestMapping(value="robotAnswers.json", method={RequestMethod.POST})
	@ResponseBody
	public ResponseData robotAnswers(HttpServletRequest request,String content) {
		ResponseData rd=new ResponseData();
		logger.info("问:"+content);
		RobotResponse robotResponse= ApiUtil.callRobotResponse(content, "json", "get");
		if(null!=robotResponse && "100000".equals(robotResponse.getCode())){
			rd.setMsg(robotResponse.getText());
			rd.addData(MovieConstant.SUCCESS_CODE, 10000);
		}else{
			rd.setMsg("机器小蜜，出现故障啦!");
		}
		logger.info("回答:"+JSONObject.toJSONString(robotResponse));
		return rd;
	}
}
