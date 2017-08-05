
package cn.com.yuzhushui.movie.sys.biz.dao;

import java.util.Map;

import cn.com.yuzhushui.movie.common.base.BaseDao;
import cn.com.yuzhushui.movie.sys.biz.entity.SysBills;

/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-03-01 11:24:43
 * @history
 */
public interface SysBillsDao extends BaseDao<SysBills,Integer>{
	
	/**<!-- 根据给定借款人Id 及 状态 查询 历史支出总额 -->*/
	public Long getTotalMoneyByDebtorIdWithStatus(Map<String,Object> map);
}
