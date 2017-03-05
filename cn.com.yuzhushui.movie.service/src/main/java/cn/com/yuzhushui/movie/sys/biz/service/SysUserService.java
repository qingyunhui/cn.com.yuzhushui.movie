package cn.com.yuzhushui.movie.sys.biz.service;

import cn.com.yuzhushui.movie.common.base.BaseService;

import cn.com.yuzhushui.movie.sys.biz.entity.SysUser;
/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-23 00:05:04
 * @history
 */
public interface SysUserService extends BaseService<SysUser,Integer>{
    
	/**
	 * 根据账号查询用户
	 * */
	public SysUser queryByAccountId(Integer accountId);
	
}