package cn.com.yuzhushui.movie.common_test;

/**
 * 
 * 定义Model的一些基础信息
 * 
 * @ClassName: BaseModel
 * @author zhou.xy
 * @date 2016年1月4日 下午1:32:14
 * 
 * @param <KEY_TYPE>
 */
public abstract class BaseModel<KEY_TYPE> {
	@SuppressWarnings("unused")
	private KEY_TYPE id;

	// TODO 一些额外的信息

	public abstract KEY_TYPE getId();

}
