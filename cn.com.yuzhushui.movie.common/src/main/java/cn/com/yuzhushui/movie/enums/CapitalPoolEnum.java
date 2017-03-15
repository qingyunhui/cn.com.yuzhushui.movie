package cn.com.yuzhushui.movie.enums;

import lombok.Getter;
import qing.yun.hui.common.enums.ICommonEnum;

/***
 ** @category 资金池
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月15日上午9:24:44
 **/
public class CapitalPoolEnum {
	
	/**资金池预警的值:1000元*/
	public static final Integer EARLY_WARNING_MONEY=1000;
	
	@Getter
	public enum CapitalPool implements ICommonEnum{
		//资金池（0.无可用资金池，1.资金池充值金额不足，2.资金池金额充足，3.资金池已透支.）
		NOT_AVAILABLE_POOL(0,"无可用资金池"),
		INSUFFICIENT_POOL_BALANCE(1,"资金池充值余额不足"),
		SUFFICIENT_POOL_BALANCE(2,"资金池余额充足"),
		OVERDRAFT_POOL_BALANCE(3,"资金池已透支");
		
		private final int value;
	    private final String name;
	    
	    private CapitalPool(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }

		@Override
		public String getCode() {
			return String.valueOf(value);
		}
	}

}
