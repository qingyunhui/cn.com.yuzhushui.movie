package cn.com.yuzhushui.movie.common.bean;

import cn.com.yuzhushui.movie.sys.biz.entity.SysUser;
import lombok.Getter;
import lombok.Setter;

/***
 ** @Description: 请用一句话来描述
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Oct 27, 2015 9:23:57 AM
 ** @version: V1.0
 ***/
@Getter
@Setter
public class SessionInfo {
	
	private SysUser sysUser;
	
	private Object object;
}
