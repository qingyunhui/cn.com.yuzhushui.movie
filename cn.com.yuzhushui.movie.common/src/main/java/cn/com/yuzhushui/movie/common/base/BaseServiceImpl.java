package cn.com.yuzhushui.movie.common.base;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月17日下午2:25:54
 **/
public class BaseServiceImpl<MODEL, KEY_TYPE> implements BaseService<MODEL, KEY_TYPE>{

	@Override
	public int add(MODEL model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int add(List<MODEL> models) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(MODEL model) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(List<MODEL> models) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(KEY_TYPE id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(List<KEY_TYPE> ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MODEL query(KEY_TYPE id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MODEL> query(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<MODEL> queryPage() {
		// TODO Auto-generated method stub
		return null;
	}

}
