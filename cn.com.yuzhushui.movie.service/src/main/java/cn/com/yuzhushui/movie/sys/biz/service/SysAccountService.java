package cn.com.yuzhushui.movie.sys.biz.service;

import cn.com.yuzhushui.movie.common.base.BaseService;
import cn.com.yuzhushui.movie.sys.biz.entity.SysAccount;
/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-23 00:05:03
 * @history
 */
public interface SysAccountService extends BaseService<SysAccount,Integer>{
    
	/**
	 * 根据账号查询账户
	 * */
	public SysAccount queryByAccount(String account);
	
}