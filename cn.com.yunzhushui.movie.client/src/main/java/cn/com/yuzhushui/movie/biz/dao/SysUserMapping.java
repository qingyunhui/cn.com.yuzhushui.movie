
package cn.com.yuzhushui.movie.biz.dao;

import java.util.List;
import java.util.Map;
import cn.com.yuzhushui.movie.biz.entity.SysUser;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-10-19 23:42:51
 * @history
 */
public interface SysUserMapping {
	/** 
     * 新增SysUser
     * @param sysUser SysUser
     * @create: 2016-10-19 23:42:51
     * @return int
     * @history:
     */
    public int insert(SysUser sysUser);
    
    /** 
     * 批量新增SysUser
     * @param sysUsers SysUsers
     * @create: 2016-10-19 23:42:51
     * @return int
     * @history:
     */
    public int insertBatch(List<SysUser> sysUsers);
    
    /** 
     * 根据id删除SysUser
     * @param id 主键
     * @return int
     * @create: 2016-10-19 23:42:51
     * @history:
     */
    public int delete(java.lang.Integer id);
    
    /** 
     * 根据id批量删除SysUser
     * @param id 主键
     * @return int
     * @create: 2016-10-19 23:42:51
     * @history:
     */
    public int deleteBatch(List<java.lang.Integer> ids);
    
	/** 
     * 更新SysUser中所有字段
     * @param sysUser {className}实体，条件属性必须设置
     * @return int
     * @create: 2016-10-19 23:42:51
     * @history:
     */
    public int update(SysUser sysUser);
    
    /** 
     * 更新SysUser中不为null的字段，必须设置主键，否则不生效
     * @param sysUser {className}实体，条件属性必须设置
     * @return int
     * @create: 2016-10-19 23:42:51
     * @history: 
     */
    public int updateSelective(SysUser sysUser);
    
    /** 
     * 根据id获取SysUser
     * @param id 主键
     * @return SysUser
     * @create: 2016-10-19 23:42:51
     * @history:
     */
    public SysUser getById(java.lang.Integer id);
    
    /** 
     * 根据条件查询全部SysUser列表
     * @param map
     * @return List<SysUser>
     * @create: 2016-10-19 23:42:51
     * @history:
     */
	public List<SysUser> queryAll(Map<String, Object> map);
	
	/** 
     * 根据条件分页查询数据
     * @param map Map<String, Object>
     * @return List<SysUser>
     * @create: 2016-10-19 23:42:51
     * @history:
     */
	public List<SysUser> queryPage(Map<String, Object> map);
	
	/** 
     * 统计符合条件的数据的数量
     * @param map Map<String, Object>
     * @return int
     * @create: 2016-10-19 23:42:51
     * @history:
     */
	public int queryCount(Map<String, Object> map);
	
}
