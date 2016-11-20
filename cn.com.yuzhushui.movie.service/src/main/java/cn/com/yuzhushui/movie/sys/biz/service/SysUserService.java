package cn.com.yuzhushui.movie.sys.biz.service;

import cn.com.yuzhushui.movie.common.base.BaseService;
import cn.com.yuzhushui.movie.common.bean.SysUser;
/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-18 11:19:22
 * @history
 */
public interface SysUserService extends BaseService<SysUser,Integer>{
	
	/***
	 * <p>新增</p>
	 * @param sysUser 待新增的对象
	 * @return int 受影响的行数
	 * */
	public int addTest(SysUser sysUser);
    
}