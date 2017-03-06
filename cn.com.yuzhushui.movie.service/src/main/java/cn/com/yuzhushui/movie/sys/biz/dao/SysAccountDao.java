
package cn.com.yuzhushui.movie.sys.biz.dao;

import cn.com.yuzhushui.movie.common.base.BaseDao;
import cn.com.yuzhushui.movie.sys.biz.entity.SysAccount;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-23 00:05:03
 * @history
 */
public interface SysAccountDao extends BaseDao<SysAccount,Integer>{
	
	/**
	 * 根据账号查询账户
	 * */
	public SysAccount queryByAccount(String account);
	
}
