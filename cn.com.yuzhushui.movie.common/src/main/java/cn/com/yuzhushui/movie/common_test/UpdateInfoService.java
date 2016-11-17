/*package cn.com.yuzhushui.movie.common_test;

import org.springframework.stereotype.Component;

import cn.com.guangduo.general.oss.biz.po.SysUser;
import cn.com.guangduo.iface.common.util.SessionUtil;

@Component
public class UpdateInfoService implements IPluginBeforeService {
	
	@SuppressWarnings("rawtypes")
	@Override
	public void process(BaseModel baseModel) {
		if(baseModel instanceof IUpdateInfo) {
			IUpdateInfo updateInfo = (IUpdateInfo) baseModel;
			SysUser sysUser = SessionUtil.getSysUser();
			updateInfo.setEditor(sysUser.getName());
			updateInfo.setEditorId(sysUser.getUserId());
		}
	}

}
*/