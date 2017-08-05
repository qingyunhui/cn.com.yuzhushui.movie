package cn.com.yuzhushui.movie.sys.biz.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.yuzhushui.movie.common.base.BaseServiceImpl;
import cn.com.yuzhushui.movie.sys.biz.dao.SysBillsDao;
import cn.com.yuzhushui.movie.sys.biz.entity.SysBills;
import cn.com.yuzhushui.movie.sys.biz.service.SysBillsService;
import qing.yun.hui.common.utils.BeanUtil;
import qing.yun.hui.common.utils.StringUtil;



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
	public Long getTotalMoneyByDebtorIdWithStatus(Integer debtorId,Integer status) {
		if(StringUtil.isEmpty(debtorId,status)) return 0L;
		SysBills bills=new SysBills();
		bills.setDebtorId(debtorId);
		bills.setStatus(status);
		Map<String,Object> map=BeanUtil.pojoToMap(bills);
		return sysBillsDao.getTotalMoneyByDebtorIdWithStatus(map);
	}

	@Override
	public Boolean getByBillsByAuditUnPass(Integer debtorId, Integer status) {
		SysBills model=new SysBills();
		model.setDebtorId(debtorId);
		model.setDeleted(0);
		model.setStatus(status);
		int count=sysBillsDao.queryCount(BeanUtil.pojoToMap(model));
		return count>0;
	}
      
}