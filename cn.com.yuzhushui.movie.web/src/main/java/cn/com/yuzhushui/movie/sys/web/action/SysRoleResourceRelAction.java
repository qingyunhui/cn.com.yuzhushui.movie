package cn.com.yuzhushui.movie.sys.web.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.yuzhushui.movie.sys.biz.entity.SysRoleResourceRel;
import cn.com.yuzhushui.movie.sys.web.vo.SysRoleResourceRelForm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.yuzhushui.movie.common.base.BaseAction;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 15:55:04
 * @history
 */
@Controller
@RequestMapping(SysRoleResourceRelAction.ACTION_PATH)
public class SysRoleResourceRelAction extends BaseAction<SysRoleResourceRel, SysRoleResourceRelForm, Integer>{
	
	protected Logger logger=LoggerFactory.getLogger(SysRoleResourceRelAction.class);
	
	//一般用于重定向用
	protected static final String ACTION_PATH="/sys/sysRoleResourceRel";
	
	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
	
}