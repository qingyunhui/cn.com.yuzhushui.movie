package cn.com.yuzhushui.movie.common;
/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年5月11日下午3:50:18
 **/
public abstract class BaseModel<KEY_TYPE> {
	
	@SuppressWarnings("unused")
	private KEY_TYPE id;
	
	public abstract KEY_TYPE getId();
	
}
