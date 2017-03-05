package cn.com.yuzhushui.movie.sys.biz.service;

import java.util.List;

import cn.com.yuzhushui.movie.common.base.BaseService;
import cn.com.yuzhushui.movie.sys.biz.entity.SysFundPool;
/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-03-02 22:50:29
 * @history
 */
public interface SysFundPoolService extends BaseService<SysFundPool,Integer>{
	
	public List<SysFundPool> queryFundPoolsByAccountId(java.lang.Integer id);
	
	/***统计用户历史充值总额 **/
    public Long getTotalGoldByAccountId(Integer accountId);
    
}