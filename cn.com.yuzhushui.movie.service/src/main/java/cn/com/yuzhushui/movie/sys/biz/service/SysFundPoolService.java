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
	
	/**
	 * <p>统计用户历史充值总额</p>
     * @param accountId
     * @return 用户历史充值金额
	 **/
    public Long getTotalGoldByAccountId(Integer accountId);
    
    /**
     * @category 可用余额=资金池-预支金额
     * <p>查询用户可用户余额</p>
     * @param accountId
     * @return 可用余额
     * */
    public Long getTotalBalance(Integer accountId);
    
}