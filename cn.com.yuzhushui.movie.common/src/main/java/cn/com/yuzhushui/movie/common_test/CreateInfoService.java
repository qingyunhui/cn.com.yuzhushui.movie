/*package cn.com.yuzhushui.movie.common_test;

import org.springframework.stereotype.Component;

import cn.com.guangduo.general.oss.biz.po.SysUser;
import cn.com.guangduo.iface.common.util.SessionUtil;

@Component
public class CreateInfoService implements IPluginBeforeService {

	@SuppressWarnings("rawtypes")
	@Override
	public void process(BaseModel baseModel) {
		if(baseModel instanceof ICreateInfo) {
			ICreateInfo createInfo = (ICreateInfo) baseModel;
			SysUser sysUser = SessionUtil.getSysUser();
			createInfo.setCreater(sysUser.getName());
			createInfo.setCreaterId(sysUser.getUserId());
		}
	}

}
*/