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
		//关键字(0.借,1.寄,2.欠,3.拿,4.支)
		BORROW(0,"借","background:#8bc34a"),
		/*SEND(1,"寄","background:#cddc39"),*/
		OWE(1,"欠","background:#ffc107"),
		TAKE(2,"拿","background:#ff9800"),
		PAY(3,"预支","background:#ffeb3b");
		
		private final int value;
	    private final String name;
	    
	    private final String code;
	    
	    private Keyword(int value, String name,String code) {
	        this.value = value;
	        this.name = name;
	        this.code=code;
	    }

		@Override
		public String getCode() {
			return code;
		}
	}
	
	@Getter
	public enum Status implements ICommonEnum{
		//状态：0.待审核，1.审核不通过，2.审核通过
		AUDIT_WAIT(0,"待审核","background:#e91e63"),
		AUDIT_UN_PASS(1,"审核不通过","background:#f44336"),
		AUDIT_PASS(2,"审核通过","background:#4caf50");
		
		private final int value;
	    private final String name;
	    private final String code;
	    
	    private Status(int value, String name,String code) {
	        this.value = value;
	        this.name = name;
	        this.code=code;
	    }

		@Override
		public String getCode() {
			return code;
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
