package cn.com.yuzhushui.movie.common.base;

import org.springframework.stereotype.Service;

import cn.com.yuzhushui.movie.common.bean.SysUser;
import cn.com.yuzhushui.movie.common.util.SessionUtil;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月20日下午9:09:43
 **/
@Service
public class CreaterServiceImpl implements PluginService{

	@SuppressWarnings("rawtypes")
	@Override
	public void process(BaseModel baseModel) {
		if(baseModel instanceof CreaterService) {
			CreaterService creater = (CreaterService) baseModel;
			SysUser sysUser = SessionUtil.getSysUser();
			creater.setCreater(sysUser.getName());
			creater.setCreaterId(sysUser.getId());
		}
	}
}
