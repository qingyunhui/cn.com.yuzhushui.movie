package cn.com.yuzhushui.movie.sys.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.yuzhushui.movie.common.base.BaseServiceImpl;
import cn.com.yuzhushui.movie.sys.biz.dao.SysUserDao;
import cn.com.yuzhushui.movie.sys.biz.entity.SysUser;
import cn.com.yuzhushui.movie.sys.biz.service.SysUserService;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-23 00:05:04
 * @history
 */
@Service("sysUserService")
public class SysUserServiceImpl extends BaseServiceImpl<SysUser,Integer> implements SysUserService{

	@Autowired
	private SysUserDao sysUserDao;
	
	@Override
	public SysUser queryByAccountId(Integer accountId) {
		return sysUserDao.queryByAccountId(accountId);
	}
      
}