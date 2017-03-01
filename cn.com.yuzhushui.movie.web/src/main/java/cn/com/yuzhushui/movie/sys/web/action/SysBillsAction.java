package cn.com.yuzhushui.movie.sys.web.action;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import qing.yun.hui.common.utils.BeanUtil;
import cn.com.yuzhushui.movie.common.base.BaseAction;
import cn.com.yuzhushui.movie.common.util.SessionUtil;
import cn.com.yuzhushui.movie.constant.MovieConstant;
import cn.com.yuzhushui.movie.sys.biz.entity.SysBills;
import cn.com.yuzhushui.movie.sys.biz.entity.SysUser;
import cn.com.yuzhushui.movie.sys.biz.service.SysBillsService;
import cn.com.yuzhushui.movie.sys.web.vo.SysBillsForm;


/**
 * @author qing.yunhui 
 * @Since 2011-2017
 * @create 2017-03-01 11:24:43
 * @history
 */
@Controller
@RequestMapping(SysBillsAction.ACTION_PATH)
public class SysBillsAction extends BaseAction<SysBills, SysBillsForm, Integer>{
	
	protected Logger logger=LoggerFactory.getLogger(SysBillsAction.class);
	
	@Autowired
	SysBillsService sysBillsService;
	
	//一般用于重定向用
	protected static final String ACTION_PATH="/sys/sysBills";
	
	@RequestMapping(value = "list")
	public ModelAndView list() {
		ModelAndView modelAndView =new ModelAndView(getActionPath() + "/list");
		try {
			SysUser user=SessionUtil.getSysUser();
			SysBills bills=new SysBills();
			//查询-借款人or出借人的所有账单
			bills.setDebtorId(user.getAccountId());
			bills.setLenderId(user.getAccountId());
			bills.setQueryDebtorIdWithlenderId("true");
			Map<String,Object>map=BeanUtil.pojoToMap(bills);
			List<SysBills> sysBillsList=sysBillsService.query(map);
			logger.info("==============>查询到的账单有:{}条。",new Object[]{sysBillsList.size()});
			modelAndView.addObject(MovieConstant.ENTITYS, sysBillsList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
	
}