package cn.com.yuzhushui.movie.sys.web.action;


import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.yuzhushui.movie.cache.ShardedJedisCached;
import cn.com.yuzhushui.movie.common.base.BaseAction;
import cn.com.yuzhushui.movie.common.base.BaseService;
import cn.com.yuzhushui.movie.sys.biz.entity.SysUser;
import cn.com.yuzhushui.movie.sys.biz.service.SysUserService;
import cn.com.yuzhushui.movie.sys.web.vo.SysUserForm;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-18 11:19:22
 * @history
 */
@Controller
@RequestMapping(SysUserAction.ACTION_PATH)
public class SysUserAction extends BaseAction<SysUser, SysUserForm, Integer>{
	
	protected Logger logger=LoggerFactory.getLogger(SysUserAction.class);
	
	@Autowired
	private ShardedJedisCached shardedJedisCached;
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private BaseService<SysUser,Integer> baseService;
	
	//一般用于重定向用
	protected static final String ACTION_PATH="/sys/sysUser";
	
	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
	
	@RequestMapping(value = "doAddTest", method = { RequestMethod.POST,RequestMethod.GET })
	public String doAddTest() {
		logger.info("=====================调用baseService.add()=====================");
		SysUser user=new SysUser();
		int r=new Random().nextInt(200);
		user.setAccountId(1+r);
		user.setName(""+r);
		user.setAge(28+r);
		user.setSex(1);
		user.setMobilephone("186653006"+r);
		user.setEmail("28067216"+r);
		user.setCtime(new Date());
		user.setCreater(""+r);
		user.setCreaterId(2222);
		user.setEtime(new Date());
		user.setEditor("张三");
		user.setEditorId(22);
		user.setDeleted(0);
		int count=0;
		try {
			count=baseService.add(user);
		} catch (Exception e) {
			logger.error("************数据库操作异常，异常原因，{}",new Object[]{e});
		}
		logger.info("受影响的行数:"+count+"条。");
		logger.info("=====================调用baseService，end=====================");
		return ACTION_PATH+"/list";
	} 
	
	@RequestMapping(value = "doAddTest2", method = { RequestMethod.POST,RequestMethod.GET })
	public String doAddTest2() {
		logger.info("=====================调用sysUserService.addTest()=====================");
		SysUser user=new SysUser();
		int r=new Random().nextInt(200);
		user.setAccountId(1+r);
		user.setName(""+r);
		user.setAge(28+r);
		user.setSex(1);
		user.setMobilephone("186653006"+r);
		user.setEmail("28067216"+r);
		user.setCtime(new Date());
		user.setCreater(""+r);
		user.setCreaterId(2222);
		user.setEtime(new Date());
		user.setEditor("张三");
		user.setEditorId(22);
		user.setDeleted(0);
		int count=0;
		try {
			sysUserService.addTest(user);
		} catch (Exception e) {
			logger.error("************数据库操作异常，异常原因，{}",new Object[]{e});
		}
		logger.info("受影响的行数:"+count+"条。");
		logger.info("=====================调用sysUserService.end=====================");
		return ACTION_PATH+"/list";
	} 
}