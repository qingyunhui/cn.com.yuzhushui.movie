package cn.com.yuzhushui.movie.enums;

import lombok.Getter;
import qing.yun.hui.common.enums.ICommonEnum;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年9月15日下午10:59:07
 **/
public class SysUserEnum {

	@Getter
	public enum State implements ICommonEnum{
		//状态(0:未认证、1.认证中、2.认证失败、3.已认证)
		UN_CERTIFICATION(0,"未认证"),
		CERTIFICATION(1,"认证中"),
		FAIL_CERTIFICATION(2,"认证失败"),
		SUCCESS_CERTIFICATION(3,"已认证");
		
		private final int value;
	    private final String name;
	    
	    
	    private State(int value, String name) {
	        this.value = value;
	        this.name = name;
	    }

		@Override
		public String getCode() {
			return String.valueOf(value);
		}
	}
	
}
