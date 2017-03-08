package cn.com.yuzhushui.movie.sys.biz.service;

import cn.com.yuzhushui.movie.common.base.BaseService;
import cn.com.yuzhushui.movie.enums.SysAccountEnum;
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
	
	/**
	 * 根据邮箱号查询账户
	 * */
	public SysAccount queryByEmail(String email);
	
	/**
	 * 注册时、SysAccount、SysUser表都要插入数据。
	 * @param sysAccount
	 * @return boolean
	 * */
	public boolean saveSysAccountWithSysUser(SysAccount sysAccount);
	
	/**
	 * <p>查看邮箱、账号是否已经被其它用户注册了</p>
	 * @param email
	 * @param account
	 * @return SysAccountEnum.Exist
	 * */
	public SysAccountEnum.Exist isExist(String email,String account);
}