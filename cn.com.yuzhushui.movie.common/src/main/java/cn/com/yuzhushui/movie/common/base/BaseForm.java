package cn.com.yuzhushui.movie.common.base;
/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月17日下午10:24:29
 **/
public abstract class BaseForm<KEY_TYPE> {

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
