package cn.com.yuzhushui.movie.common.base;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月17日下午2:25:54
 **/
@Service
public class BaseServiceImpl<MODEL, KEY_TYPE> implements BaseService<MODEL, KEY_TYPE>{

	@Autowired
	private BaseDao<MODEL, KEY_TYPE> baseDao;
	
	private final BaseDao<MODEL, KEY_TYPE> getBaseDao() {
		return baseDao;
	}
	
	public int add(MODEL model) {
		//TODO 可以在insert前做些处理，比如记录创建人，创建时间、创建人id等等..
		int count = getBaseDao().insert(model);
		//TODO 也可以在insert之后做一些处理，比如什么呢？
		return count;
	}
	public int add(List<MODEL> models) {
		//TODO 可以在insert前做些处理，比如记录创建人，创建时间、创建人id等等..
		int count = getBaseDao().insertBatch(models);
		//TODO 也可以在insert之后做一些处理，比如什么呢？
		return count;
	}
	public int update(MODEL model) {
		//TODO 可以在insert前做些处理，比如记录创建人，创建时间、创建人id等等..
		int count = getBaseDao().update(model);
		//TODO 也可以在insert之后做一些处理，比如什么呢？
		return count;
	}
	public int update(List<MODEL> models) {
		//TODO 可以在insert前做些处理，比如记录创建人，创建时间、创建人id等等..
		int count = getBaseDao().updateBatch(models);
		//TODO 也可以在insert之后做一些处理，比如什么呢？
		return count;
	}
	
	public int delete(KEY_TYPE id) {
		return getBaseDao().delete(id);
	}
	
	public int delete(List<KEY_TYPE> ids) {
		return getBaseDao().deleteBatch(ids);
	}
	
	public MODEL query(KEY_TYPE id) {
		return getBaseDao().queryById(id);
	}
	
	public List<MODEL> query(Map<String, Object> map) {
		return getBaseDao().query(map);
	}
	
	public PageInfo<MODEL> queryPage(BaseQuery query) {
		PageHelper.startPage(query.getPageNum(), query.getPageSize(), query.getOrderBy());
		return new PageInfo<MODEL>(query(query.getQueryData()));
	}

}
