package cn.com.yuzhushui.movie.sys.web.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.yuzhushui.movie.sys.biz.entity.SysData;
import cn.com.yuzhushui.movie.sys.web.vo.SysDataForm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.yuzhushui.movie.common.base.BaseAction;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 15:37:52
 * @history
 */
@Controller
@RequestMapping(SysDataAction.ACTION_PATH)
public class SysDataAction extends BaseAction<SysData, SysDataForm, Integer>{
	
	protected Logger logger=LoggerFactory.getLogger(SysDataAction.class);
	
	//一般用于重定向用
	protected static final String ACTION_PATH="/sys/sysData";
	
	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
	
}