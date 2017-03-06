package cn.com.yuzhushui.movie.sys.web.action;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.com.yuzhushui.movie.common.util.SessionUtil;
import cn.com.yuzhushui.movie.constant.MovieConstant;
import cn.com.yuzhushui.movie.sys.biz.entity.SysAccount;
import cn.com.yuzhushui.movie.sys.biz.entity.SysBills;
import cn.com.yuzhushui.movie.sys.biz.entity.SysFundPool;
import cn.com.yuzhushui.movie.sys.biz.service.SysBillsService;
import cn.com.yuzhushui.movie.sys.biz.service.SysFundPoolService;
import qing.yun.hui.common.utils.BeanUtil;

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
	
	@Autowired
	private SysBillsService sysBillsService;
	
	protected Logger logger=LoggerFactory.getLogger(SysFundPoolAction.class);

	@RequestMapping(value = "list")
	public ModelAndView list(String messages) {
		ModelAndView modelAndView = new ModelAndView(getActionPath() + "/list");
		try {
			SysAccount account=SessionUtil.getSysAccount();
			List<SysFundPool> sysfundPools=sysFundPoolService.queryFundPoolsByAccountId(account.getAccountId());
			Long totalGold=sysFundPoolService.getTotalGoldByAccountId(account.getAccountId());
			totalGold=null==totalGold?0L:totalGold;
			modelAndView.addObject("totalGold",totalGold);
			modelAndView.addObject(MovieConstant.ENTITYS, sysfundPools);
			//支出记录
			SysBills bills=new SysBills();
			//查询-借款人or出借人的所有账单
			bills.setDebtorId(account.getAccountId());
			Map<String,Object>map=BeanUtil.pojoToMap(bills);
			List<SysBills> sysBillsList=sysBillsService.query(map);
			Long totalMoney=sysBillsService.getTotalMoneyByDebtorId(account.getAccountId());
			totalMoney=null==totalMoney?0L:totalMoney;
			modelAndView.addObject("totalMoney",totalMoney);//总支出总额。
			modelAndView.addObject("sysBillsList", sysBillsList);
			//计算剩余金额:资金池金额-支出金额
			Long totalSurplus=totalGold-totalMoney;
			modelAndView.addObject("totalSurplus", totalSurplus);
			modelAndView.addObject(MovieConstant.MESSAGES_INFO,messages);
		} catch (Exception e) {
			logger.error("==========>系统处理出现异常，异常原因：{}",new Object[]{JSONObject.toJSONString(e)});
			modelAndView.addObject(MovieConstant.MESSAGES_INFO,e.getMessage());
		}
		return modelAndView;
	}
	
	
	
	public String getActionPath() {
		return ACTION_PATH;
	}
	
}