package cn.com.yuzhushui.movie.common.base;

import java.util.HashMap;
import java.util.Map;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月17日下午10:38:45
 **/
public class ResponseData {

	private ResponseStatus status = null;
	private String msg = null;
	private Map<String, Object> data = null;
	
	public ResponseData() {
		this("请求成功");
	}
	
	public ResponseData(String msg) {
		this(ResponseStatus.SUCCESS,msg);
	}
	public ResponseData(ResponseStatus status, String msg) {
		this(status,msg,new HashMap<String, Object>());
	}
	public ResponseData(ResponseStatus status, String msg,
			Map<String, Object> data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	
	public void put(String key, Object value) {
		data.put(key, value);
	}
	
	/**
	 * 链式操作
	 * @param key
	 * @param value
	 * @return
	 */
	public ResponseData addData(String key, Object value) {
		data.put(key, value);
		return this;
	}
	public int getStatus() {
		return status.getValue();
	}
	
	public ResponseStatus getResponseStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
}
