
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
	
	/***查询用户历史资金池充值记录集 **/
    public List<SysFundPool> queryFundPoolsByAccountId(java.lang.Integer id);
    
    /***统计用户历史充值总额 **/
    public Long getTotalGoldByAccountId(Integer accountId);
    
	
}