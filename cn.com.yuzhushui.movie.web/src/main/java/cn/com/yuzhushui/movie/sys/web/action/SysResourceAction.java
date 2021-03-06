package cn.com.yuzhushui.movie.sys.web.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.yuzhushui.movie.sys.biz.entity.SysResource;
import cn.com.yuzhushui.movie.sys.web.vo.SysResourceForm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.yuzhushui.movie.common.base.BaseAction;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 15:55:03
 * @history
 */
@Controller
@RequestMapping(SysResourceAction.ACTION_PATH)
public class SysResourceAction extends BaseAction<SysResource, SysResourceForm, Integer>{
	
	protected Logger logger=LoggerFactory.getLogger(SysResourceAction.class);
	
	//一般用于重定向用
	protected static final String ACTION_PATH="/sys/sysResource";
	
	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
	
}