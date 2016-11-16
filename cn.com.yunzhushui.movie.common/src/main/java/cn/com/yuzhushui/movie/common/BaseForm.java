package cn.com.yuzhushui.movie.common;

import java.io.Serializable;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年5月11日下午3:52:11
 **/
public class BaseForm <KEY_TYPE extends Serializable>{

	private String actionPath;//action路径
	
	private KEY_TYPE uuid;// 生成唯一标识
	
	public KEY_TYPE getUuid() {
		return uuid;
	}

	public void setUuid(KEY_TYPE uuid) {
		this.uuid = uuid;
	}

	public String getActionPath() {
		return actionPath;
	}

	public void setActionPath(String actionPath) {
		this.actionPath = actionPath;
	}
	
}
