package cn.com.yuzhushui.movie.sys.web.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.yuzhushui.movie.sys.biz.entity.SysMessage;
import cn.com.yuzhushui.movie.sys.web.vo.SysMessageForm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.yuzhushui.movie.common.base.BaseAction;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 15:51:37
 * @history
 */
@Controller
@RequestMapping(SysMessageAction.ACTION_PATH)
public class SysMessageAction extends BaseAction<SysMessage, SysMessageForm, Integer>{
	
	protected Logger logger=LoggerFactory.getLogger(SysMessageAction.class);
	
	//一般用于重定向用
	protected static final String ACTION_PATH="/sys/sysMessage";
	
	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
	
}