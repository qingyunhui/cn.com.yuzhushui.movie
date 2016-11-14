package cn.com.yunzhushui.movie.sys.web.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cn.com.yunzhushui.movie.cache.ShardedJedisCached;

/**
 * @author qing.yunhui 
 * @Since 2011-2015
 * @create 2015-11-21 13:00:46
 * @history
 */
@Controller
@RequestMapping(SysUserAction.ACTION_PATH)
public class SysUserAction {
	
	protected static final String ACTION_PATH="/sys/sysUser";
	
	protected Logger logger=LoggerFactory.getLogger(SysUserAction.class);
	
	@Autowired
	private ShardedJedisCached shardedJedisCached;
	
	@RequestMapping(value="list", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView("list");
		
		
		return modelAndView;
	}
}