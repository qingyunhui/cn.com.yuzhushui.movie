package cn.com.yuzhushui.movie.common;

import java.util.List;
import java.util.Map;

/***
 ** @category 基础DAO...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年5月11日下午3:46:08
 **/
public interface BaseDao<MODEL,KEY_TYPE> {

	/**
	 * <p>新增</p>
	 * @param  model 待操作的对象
	 * @return int 受影响的行数
	 * **/
	int insert(MODEL model);
	
	/**
	 * <p>批量新增</p>
	 * @param models 待操作的对象集合
	 * @return int 受影响的行数
	 * */
	int insertBatch(List<MODEL> models);
	
	/**
	 * <p>单条更新</p>
	 * @param model 待操作的对象
	 * @return int 受影响的行数
	 * */
	int update(MODEL model);
	
	/**
	 * <p>批量更新</p>
	 * @param models 待操作的对象集合
	 * @return int 受影响的行数
	 * */
	int updateBatch(List<MODEL> models);
	
	/**
	 * <p>根据给定id删除</p>
	 * @param id 
	 * @return int 受影响的行数
	 * */
	int delete(KEY_TYPE id);
	
	/**
	 * <p>根据给定ids列表删除</p>
	 * @param ids 
	 * @return int 受影响的行数
	 * */
	int deleteBatch(List<KEY_TYPE> ids);
	
	/**
	 * <p>根据给定id查询对应model</p>
	 * @param id
	 * @return Model 
	 * */
	MODEL queryById(KEY_TYPE id);
	
	/**
	 * <p>根据给定参数查询</p>
	 * @param map查询条件
	 * @return List<Model> 
	 * */
	List<MODEL> query(Map<String, Object> map);
	
	/**
	 * <p>查询总记录数</p>
	 * @param map 查询条件
	 * @return 受影响的行数
	 * */
	int queryCount(Map<String, Object> map);
	
}
