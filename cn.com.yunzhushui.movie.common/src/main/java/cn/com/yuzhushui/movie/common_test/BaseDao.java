package cn.com.yuzhushui.movie.common_test;

import java.util.List;
import java.util.Map;
/**
 * 
 * @Description: 定义一些基础的数据库操作  
 * @ClassName: BaseDao  
 * @author zhou.xy
 * @date 2015年12月24日 下午5:40:14  
 *  
 * @param <Model>
 * @param <KEY_TYPE>
 */
public interface BaseDao<Model, KEY_TYPE> {
	
	/**
	 * 单条新增
	 * @param model待操作的对象
	 * @return int 受影响的行数
	 * **/
	int insertOne(Model model);
	
	/**
	 * 批量新增
	 * @param models 待操作的对象集合
	 * @return int 受影响的行数
	 * */
	int insertBatch(List<Model> models);
	
	/**
	 * 单条更新
	 * @param model 待操作的对象
	 * @return int 受影响的行数
	 * */
	int update(Model model);
	
	/**
	 * 批量更新
	 * @param models 待操作的对象集合
	 * @return int 受影响的行数
	 * */
	int updateBatch(List<Model> models);
	
	/**
	 * 根据给定id删除
	 * @param id 
	 * @return int 受影响的行数
	 * */
	int delete(KEY_TYPE id);
	
	/**
	 * 根据给定ids列表删除
	 * @param ids 
	 * @return int 受影响的行数
	 * */
	int deleteBatch(List<KEY_TYPE> ids);
	
	/**
	 * 根据给定id查询对应model
	 * @param id
	 * @return Model 
	 * */
	Model queryById(KEY_TYPE id);
	
	/**
	 * 根据给定参数查询
	 * @param map
	 * @return List<Model> 
	 * */
	List<Model> query(Map<String, Object> map);
}
