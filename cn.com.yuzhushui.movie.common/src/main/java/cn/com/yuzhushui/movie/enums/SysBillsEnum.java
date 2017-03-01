package cn.com.yuzhushui.movie.enums;

import lombok.Getter;
import qing.yun.hui.common.enums.ICommonEnum;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月1日下午1:51:05
 **/
public class SysBillsEnum {
	
	@Getter
	public enum Keyword implements ICommonEnum{
		//关键字(0.借,1.寄,2.欠,3.其它)
		BORROW(0,"借"),
		SEND(1,"寄"),
		OWE(2,"欠"),
		OTHER(3,"其它");
		
		private final int value;
	    private final String name;
	    
	    private Keyword(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }

		@Override
		public String getCode() {
			return String.valueOf(value);
		}
	}
	
	@Getter
	public enum Status implements ICommonEnum{
		//状态：0.待审核，1.审核不通过，2.审核通过
		AUDIT_WAIT(0,"待审核"),
		AUDIT_UN_PASS(1,"审核不通过"),
		AUDIT_PASS(2,"审核通过");
		
		private final int value;
	    private final String name;
	    
	    private Status(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }

		@Override
		public String getCode() {
			return String.valueOf(value);
		}
	}
	
	@Getter
	public enum TradeType implements ICommonEnum{
		//交易类型:0.现金,1.支付宝,2.微信,3.QQ,4.银行卡,5.其它
		CASH(0,"现金"),
		ALIPAY(1,"支付宝"),
		WECHAT(2,"微信"),
		QQ(3,"QQ"),
		BANK_CARD(4,"银行卡"),
		OTHER(5,"其它 ");
		
		private final int value;
	    private final String name;
	    
	    private TradeType(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }

		@Override
		public String getCode() {
			return String.valueOf(value);
		}
	}
	
}
