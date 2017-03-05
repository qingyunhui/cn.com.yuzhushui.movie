package cn.com.yuzhushui.movie.sys.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.yuzhushui.movie.common.base.BaseServiceImpl;
import cn.com.yuzhushui.movie.sys.biz.dao.SysBillsDao;
import cn.com.yuzhushui.movie.sys.biz.entity.SysBills;
import cn.com.yuzhushui.movie.sys.biz.service.SysBillsService;



/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-03-01 11:24:43
 * @history
 */
@Service("sysBillsService")
public class SysBillsServiceImpl extends BaseServiceImpl<SysBills,Integer> implements SysBillsService{

	@Autowired
	private SysBillsDao sysBillsDao;
	
	@Override
	public Long getTotalMoneyByDebtorId(Integer debtorId) {
		return sysBillsDao.getTotalMoneyByDebtorId(debtorId);
	}
      
}