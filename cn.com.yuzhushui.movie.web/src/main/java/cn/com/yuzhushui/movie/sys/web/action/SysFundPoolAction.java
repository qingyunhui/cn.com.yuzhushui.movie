package cn.com.yuzhushui.movie.sys.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.com.yuzhushui.movie.common.base.BaseAction;
import cn.com.yuzhushui.movie.common.util.SessionUtil;
import cn.com.yuzhushui.movie.sys.biz.entity.SysAccount;
import cn.com.yuzhushui.movie.sys.biz.entity.SysFundPool;
import cn.com.yuzhushui.movie.sys.biz.entity.SysMenu;
import cn.com.yuzhushui.movie.sys.biz.service.SysFundPoolService;
import cn.com.yuzhushui.movie.sys.web.vo.SysMenuForm;

/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-03-02 22:50:29
 * @history
 */
@Controller
@RequestMapping(SysFundPoolAction.ACTION_PATH)
public class SysFundPoolAction {
	
	//一般用于重定向用
	protected static final String ACTION_PATH="/sys/sysFundPool";
	
	@Autowired
	private SysFundPoolService sysFundPoolService;

	@RequestMapping(value = "detail")
	public ModelAndView detail() {
		ModelAndView modelAndView = new ModelAndView(getActionPath() + "/detail");
		SysAccount account=SessionUtil.getSysAccount();
		List<SysFundPool> fundPools=sysFundPoolService.queryFundPoolsByAccountId(account.getAccountId());
		
		
		/*modelAndView.addObject(MovieConstant.ENTITY, sysBills);*/
		
		
		return modelAndView;
	}
	
	public String getActionPath() {
		return ACTION_PATH;
	}
	
}