package cn.com.yuzhushui.movie.sys.web.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.yuzhushui.movie.sys.biz.entity.SysAttachment;
import cn.com.yuzhushui.movie.sys.web.vo.SysAttachmentForm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.yuzhushui.movie.common.base.BaseAction;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 15:49:55
 * @history
 */
@Controller
@RequestMapping(SysAttachmentAction.ACTION_PATH)
public class SysAttachmentAction extends BaseAction<SysAttachment, SysAttachmentForm, Integer>{
	
	protected Logger logger=LoggerFactory.getLogger(SysAttachmentAction.class);
	
	//一般用于重定向用
	protected static final String ACTION_PATH="/sys/sysAttachment";
	
	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
	
}