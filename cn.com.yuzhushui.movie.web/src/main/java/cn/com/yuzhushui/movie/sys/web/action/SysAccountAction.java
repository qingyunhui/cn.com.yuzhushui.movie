package cn.com.yuzhushui.movie.sys.web.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.yuzhushui.movie.sys.biz.entity.SysAccount;
import cn.com.yuzhushui.movie.sys.web.vo.SysAccountForm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.yuzhushui.movie.common.base.BaseAction;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 00:16:24
 * @history
 */
@Controller
@RequestMapping(SysAccountAction.ACTION_PATH)
public class SysAccountAction extends BaseAction<SysAccount, SysAccountForm, Integer>{
	
	protected Logger logger=LoggerFactory.getLogger(SysAccountAction.class);
	
	//一般用于重定向用
	protected static final String ACTION_PATH="/sys/sysAccount";
	
	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
	
}