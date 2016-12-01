
package cn.com.yuzhushui.movie.web;

import lombok.Getter;
import qing.yun.hui.common.enums.ICommonEnum;

/**
 * Description: 机构充值流水
 * 
 * @Date Create on 2016年11月7日
 * @author <a href="mailto:qingyunhui@zuozh.com">qingyunhui</a>
 * @since version1.0 Copyright 2015 ZZJR All Rights Reserved.
 */
public class OrgStreamEnum {
	
	/**充值状态*/
	@Getter
	public enum Status implements ICommonEnum{
		//'0：充值初始化，1：充值成功，2：充值失败'，3：处理中(充值中)
		INIT("0","充值初始化"),
		SUCCESS("1","充值成功"),
		FAIL("2","充值失败"),
		PROCESSED("3","处理中");
	    private String code;

		private String name;

		private Status(String code, String name) {
			this.code = code;
			this.name = name;
		}

		public int getValue() {
			return Integer.parseInt(code);
		}
	}
	
}
