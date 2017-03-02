
package cn.com.yuzhushui.movie.sys.biz.dao;

import java.util.List;

import cn.com.yuzhushui.movie.common.base.BaseDao;
import cn.com.yuzhushui.movie.sys.biz.entity.SysFundPool;

/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-03-02 22:50:29
 * @history
 */
public interface SysFundPoolDao extends BaseDao<SysFundPool,Integer>{
	
	
    public List<SysFundPool> queryFundPoolsByAccountId(java.lang.Integer id);
    
	
}
