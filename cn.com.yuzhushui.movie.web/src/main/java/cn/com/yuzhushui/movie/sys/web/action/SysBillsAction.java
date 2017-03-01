package cn.com.yuzhushui.movie.sys.web.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.yuzhushui.movie.common.base.BaseAction;
import cn.com.yuzhushui.movie.sys.biz.entity.SysBills;
import cn.com.yuzhushui.movie.sys.web.vo.SysBillsForm;


/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-03-01 11:24:43
 * @history
 */
@Controller
@RequestMapping(SysBillsAction.ACTION_PATH)
public class SysBillsAction extends BaseAction<SysBills, SysBillsForm, Integer>{
	
	protected Logger logger=LoggerFactory.getLogger(SysBillsAction.class);
	
	//一般用于重定向用
	protected static final String ACTION_PATH="/sys/sysBills";
	
	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
	
}