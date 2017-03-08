package cn.com.yuzhushui.movie.sys.biz.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.yuzhushui.movie.common.base.BaseServiceImpl;
import cn.com.yuzhushui.movie.enums.SysAccountEnum;
import cn.com.yuzhushui.movie.enums.SysAccountEnum.Exist;
import cn.com.yuzhushui.movie.sys.biz.dao.SysAccountDao;
import cn.com.yuzhushui.movie.sys.biz.dao.SysUserDao;
import cn.com.yuzhushui.movie.sys.biz.entity.SysAccount;
import cn.com.yuzhushui.movie.sys.biz.entity.SysUser;
import cn.com.yuzhushui.movie.sys.biz.service.SysAccountService;
import qing.yun.hui.common.utils.MD5Util;

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
	
	@Autowired
	private SysUserDao sysUserDao;
	
	protected Logger logger=LoggerFactory.getLogger(SysAccountServiceImpl.class);
	
	@Override
	public SysAccount queryByAccount(String account) {
		return sysAccountDao.queryByAccount(account);
	}

	@Override
	public SysAccount queryByEmail(String email) {
		return sysAccountDao.queryByEmail(email);
	}

	@Override
	public boolean saveSysAccountWithSysUser(SysAccount sysAccount) {
		Boolean success=Boolean.FALSE;
		if(null==sysAccount)  return success;
		Exist exist=isExist(sysAccount.getEmail(), sysAccount.getEmail());
		if(Exist.UN_EXIST.getValue()!=exist.getValue()) return success;
		String password=MD5Util.getMD5Encryption(sysAccount.getPassword());
		sysAccount.setPassword(password);
		sysAccount.setComments("********注册********");
		sysAccount.setStatus(SysAccountEnum.STATUS.AUDIT_SUCCESS.getValue());
		int count=sysAccountDao.insert(sysAccount);
		if(count>0){
			SysUser user=new SysUser();
			user.setAccountId(sysAccount.getAccountId());
			user.setEmail(sysAccount.getEmail());
			user.setEmail(sysAccount.getComments());
			count=sysUserDao.insert(user);
			success=true;
		}
		return success;
	}

	@Override
	public Exist isExist(String email, String account) {
		SysAccount sysAccount=sysAccountDao.queryByEmail(email);
		if(null!=sysAccount){
			logger.error("==========>{}邮箱已注册，请更换其它邮箱。",new Object[]{email});
			return Exist.EMAIL_EXIST;
		}
		SysAccount tmpAccount=sysAccountDao.queryByAccount(account);
		if(null!=tmpAccount){
			logger.error("==========>{}账号已注册，请更换其它账号。",new Object[]{account});
			return Exist.ACCOUNT_EXIST;
		}
		return Exist.UN_EXIST;
	}
      
}