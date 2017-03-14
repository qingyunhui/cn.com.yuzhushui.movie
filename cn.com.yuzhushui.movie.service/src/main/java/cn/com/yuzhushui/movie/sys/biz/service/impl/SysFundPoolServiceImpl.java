package cn.com.yuzhushui.movie.sys.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.yuzhushui.movie.common.base.BaseServiceImpl;
import cn.com.yuzhushui.movie.sys.biz.dao.SysFundPoolDao;
import cn.com.yuzhushui.movie.sys.biz.entity.SysFundPool;
import cn.com.yuzhushui.movie.sys.biz.service.SysBillsService;
import cn.com.yuzhushui.movie.sys.biz.service.SysFundPoolService;

/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-03-02 22:50:29
 * @history
 */
@Service("sysFundPoolService")
public class SysFundPoolServiceImpl extends BaseServiceImpl<SysFundPool,Integer> implements SysFundPoolService{

	@Autowired
	private SysFundPoolDao sysFundPoolDao;
	
	@Autowired
	private SysBillsService sysBillsService;
	
	@Override
	public List<SysFundPool> queryFundPoolsByAccountId(Integer id) {
		return sysFundPoolDao.queryFundPoolsByAccountId(id);
	}

	@Override
	public Long getTotalGoldByAccountId(Integer accountId) {
		return sysFundPoolDao.getTotalGoldByAccountId(accountId);
	}

	@Override
	public Long getTotalBalance(Integer accountId) {
		Long totalGold=sysFundPoolDao.getTotalGoldByAccountId(accountId);
		if(null==totalGold) return totalGold;
		Long totalMoney=sysBillsService.getTotalMoneyByDebtorId(accountId);
		if(null==totalMoney)totalMoney=0L;
		return totalGold-totalMoney;
	}
}