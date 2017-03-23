package cn.com.yuzhushui.movie.robot.web.action;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import qing.yun.hui.common.annotations.ActionAnno;
import qing.yun.hui.common.struct.juhe.robot.RobotResponse;
import cn.com.yuzhushui.movie.common.base.APIService;
import cn.com.yuzhushui.movie.common.base.ResponseData;
import cn.com.yuzhushui.movie.constant.MovieConstant;

import com.alibaba.fastjson.JSONObject;

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
	
	@Autowired
	private APIService apiService;
	
	//机器人问答
	@RequestMapping(value="/answers")
	@ActionAnno(action="访问机器人问答")
	public ModelAndView answers() {
		ModelAndView modelView = new ModelAndView(ACTION_PATH + "/answers");
		return modelView;
	}
	
	@RequestMapping(value="robotAnswers.json", method={RequestMethod.POST})
	@ResponseBody
	public ResponseData robotAnswers(HttpServletRequest request,String content) {
		ResponseData rd=new ResponseData();
		logger.info("问:"+content);
		RobotResponse robotResponse= apiService.callRobotResponse(content);
		if(null!=robotResponse && "100000".equals(robotResponse.getCode())){
			rd.setMsg(robotResponse.getText());
			rd.addData(MovieConstant.SUCCESS_CODE, 10000);
		}else{
			rd.setMsg(getRandomAnswers());
		}
		logger.info("回答:"+JSONObject.toJSONString(robotResponse));
		return rd;
	}
	
	protected String getRandomAnswers(){
		String[] strs=new String[]{"机器小蜜回答不了你的提问...","中国人，请说中国话...","您提的问题太难啦，快换个提问...","我是来打酱油滴...","小蜜现在很忙，不能回答你的提问...","么么哒...","环淫，大环淫...","哈哈 ，你是王八蛋..."};
		Random random=new Random();
		return strs[random.nextInt(strs.length)];
	}
	
}
