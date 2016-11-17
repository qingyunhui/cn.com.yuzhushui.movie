package cn.com.yuzhushui.movie.common.base;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.github.pagehelper.PageInfo;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月17日下午1:27:34
 **/
@WebService
public interface BaseService<MODEL, KEY_TYPE>{

	public int add(MODEL model);

	public int add(List<MODEL> models);

	public int update(MODEL model);

	public int update(List<MODEL> models);

	public int delete(KEY_TYPE id);

	public int delete(List<KEY_TYPE> ids);

	public MODEL query(KEY_TYPE id);

	public List<MODEL> query(Map<String, Object> map);

	public PageInfo<MODEL> queryPage();
	
}
