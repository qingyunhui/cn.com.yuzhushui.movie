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
	
	/**
	 * 根据给定借款人Id 及 状态查询 历史支出总额
	 * @param debtorId 借款人id
	 * @param status  状态
	 * */
	public Long getTotalMoneyByDebtorIdWithStatus(Integer debtorId,Integer status);
	
	/**
	 * <p>根据给定条件查询未审核的账单</p>
	 * @param debtorId
	 * @param status
	 * @return Boolean
	 * */
	public Boolean getByBillsByAuditUnPass(Integer debtorId,Integer status);
    
}