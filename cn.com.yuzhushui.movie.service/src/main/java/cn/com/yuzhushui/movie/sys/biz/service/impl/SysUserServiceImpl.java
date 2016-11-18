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
 * @create 2016-11-18 11:19:22
 * @history
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser,Integer> implements SysUserService{

	@Autowired
	private SysUserDao sysUserDao;
	
	@Override
	public int addTest(SysUser sysUser) {
		int count=0;
		
		count=sysUserDao.insert(sysUser);
		System.out.println("=======>count:"+count);
		int tmp=1/0;
		System.out.println("=======>tmp:"+tmp);
		
		/*try {
			count=sysUserDao.insert(sysUser);
			System.out.println("=======>count:"+count);
			int tmp=1/0;
			System.out.println("=======>tmp:"+tmp);
		} catch (Exception e) {
			throw new RuntimeException();
		}*/
		return count;
	}
}