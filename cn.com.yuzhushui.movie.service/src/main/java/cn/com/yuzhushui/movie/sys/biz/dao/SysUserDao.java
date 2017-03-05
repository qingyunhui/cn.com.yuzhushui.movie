
package cn.com.yuzhushui.movie.sys.biz.dao;

import cn.com.yuzhushui.movie.common.base.BaseDao;
import cn.com.yuzhushui.movie.sys.biz.entity.SysUser;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-23 00:05:04
 * @history
 */
public interface SysUserDao extends BaseDao<SysUser,Integer>{
	
	/**
	 * 根据账号查询用户
	 * */
	public SysUser queryByAccountId(Integer accountId);
	
}
