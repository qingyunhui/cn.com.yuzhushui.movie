package cn.com.liangdian.diaoyu.common.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年5月11日下午3:49:31
 **/
public class BaseServiceImpl<MODEL extends BaseModel<KEY_TYPE>, KEY_TYPE> implements BaseService<MODEL, KEY_TYPE>{

	@Autowired
	SqlSessionFactory sessionFactory;
	
	@Autowired
	BaseDao<MODEL, KEY_TYPE> baseDao;
	
	private final BaseDao<MODEL, KEY_TYPE> getBaseDao() {
		return baseDao;
	}
	
	@Override
	public int add(MODEL model) {
		return getBaseDao().insert(model);
	}
	
	@Override
	public int add(List<MODEL> models) {
		return getBaseDao().insertBatch(models);
	}
	
	@Override
	public int update(MODEL model) {
		return getBaseDao().update(model);
	}
	
	@Override
	public int update(List<MODEL> models) {
		return getBaseDao().updateBatch(models);
	}
	
	@Override
	public int delete(KEY_TYPE id) {
		return getBaseDao().delete(id);
	}
	
	@Override
	public int delete(List<KEY_TYPE> ids) {
		return getBaseDao().deleteBatch(ids);
	}
	
	@Override
	public MODEL query(KEY_TYPE id) {
		return getBaseDao().queryById(id);
	}
	
	@Override
	public List<MODEL> query(Map<String, Object> map) {
		return getBaseDao().query(map);
	}

	@Override
	public PageInfo<MODEL> queryPage(BaseQuery query) {
		PageHelper.startPage(query.getPageNum(), query.getPageSize(), query.getOrderBy());
		return new PageInfo<MODEL>(getBaseDao().query(query.getQueryData()));
	}
}
