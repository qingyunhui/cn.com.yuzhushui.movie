package cn.com.yuzhushui.movie.struct;

import java.util.List;
import java.util.concurrent.Future;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月16日下午11:22:11
 **/
@Getter
@Setter
public class CallableDataResult {
	
	private int totalThread;
	
	private int totalSuccess;
	
	private int totalFail;
	
	private List<Future<Boolean>> futures;

}
