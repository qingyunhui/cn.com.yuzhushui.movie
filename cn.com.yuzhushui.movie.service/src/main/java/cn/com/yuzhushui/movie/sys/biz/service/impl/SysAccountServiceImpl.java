package cn.com.yuzhushui.movie.sys.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.yuzhushui.movie.common.base.BaseServiceImpl;
import cn.com.yuzhushui.movie.sys.biz.dao.SysAccountDao;
import cn.com.yuzhushui.movie.sys.biz.entity.SysAccount;
import cn.com.yuzhushui.movie.sys.biz.service.SysAccountService;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-23 00:05:03
 * @history
 */
@Service("sysAccountService")
public class SysAccountServiceImpl extends BaseServiceImpl<SysAccount,Integer> implements SysAccountService{

	@Autowired
	private SysAccountDao sysAccountDao;
	
	@Override
	public SysAccount queryByAccount(String account) {
		return sysAccountDao.queryByAccount(account);
	}
      
}