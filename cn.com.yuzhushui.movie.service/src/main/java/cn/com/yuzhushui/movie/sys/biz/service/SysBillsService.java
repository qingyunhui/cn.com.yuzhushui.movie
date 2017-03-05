package cn.com.yuzhushui.movie.sys.biz.service;

import cn.com.yuzhushui.movie.common.base.BaseService;
import cn.com.yuzhushui.movie.sys.biz.entity.SysBills;


/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-03-01 11:24:43
 * @history
 */
public interface SysBillsService extends BaseService<SysBills,Integer>{
	
	/**<!-- 统计借款人历史支出总额 -->*/
	public Long getTotalMoneyByDebtorId(Integer debtorId);
    
}