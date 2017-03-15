package cn.com.yuzhushui.movie.struct;

import lombok.Data;
import cn.com.yuzhushui.movie.enums.CapitalPoolEnum;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月15日上午9:47:22
 **/
@Data
public class CapitalPoolStruct {
	
	private CapitalPoolEnum.CapitalPool capitalPool;
	
	//可用余额
	private Long totalBalance;

}
