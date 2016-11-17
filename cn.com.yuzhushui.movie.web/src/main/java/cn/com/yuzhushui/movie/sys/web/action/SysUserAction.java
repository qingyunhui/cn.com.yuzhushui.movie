package cn.com.yuzhushui.movie.sys.web.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.yuzhushui.movie.cache.ShardedJedisCached;
import cn.com.yuzhushui.movie.common.base.BaseAction;
import cn.com.yuzhushui.movie.sys.web.vo.SysUser;
import cn.com.yuzhushui.movie.sys.web.vo.SysUserForm;

/**
 * @author qing.yunhui 
 * @Since 2011-2015
 * @create 2015-11-21 13:00:46
 * @history
 */
@Controller
@RequestMapping(SysUserAction.ACTION_PATH)
public class SysUserAction extends BaseAction<SysUser, SysUserForm, Integer>{
	
	protected static final String ACTION_PATH="/sys/sysUser";
	
	protected Logger logger=LoggerFactory.getLogger(SysUserAction.class);
	
	@Autowired
	private ShardedJedisCached shardedJedisCached;

	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
}