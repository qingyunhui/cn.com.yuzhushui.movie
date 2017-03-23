package cn.com.yuzhushui.movie.sys.web.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.yuzhushui.movie.common.base.BaseAction;
import cn.com.yuzhushui.movie.sys.biz.entity.SysWarning;
import cn.com.yuzhushui.movie.sys.web.vo.SysWarningForm;

/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-03-23 13:55:30
 * @history
 */
@Controller
@RequestMapping(SysWarningAction.ACTION_PATH)
public class SysWarningAction extends BaseAction<SysWarning, SysWarningForm, Integer>{
	
	protected Logger logger=LoggerFactory.getLogger(SysWarningAction.class);
	
	//一般用于重定向用
	protected static final String ACTION_PATH="/sys/sysWarning";
	
	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
	
}