package cn.com.yuzhushui.movie.common.base;

import org.springframework.stereotype.Service;

import cn.com.yuzhushui.movie.common.util.SessionUtil;
import cn.com.yuzhushui.movie.sys.biz.entity.SysUser;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月20日下午9:51:33
 **/
@Service
public class EditorServiceImpl implements PluginAfterService{

	@SuppressWarnings("rawtypes")
	@Override
	public void process(BaseModel baseModel) {
		if(baseModel instanceof EditorInfo) {
			EditorInfo editor = (EditorInfo) baseModel;
			SysUser sysUser = SessionUtil.getSysUser();
			editor.setEditor(sysUser.getName());
			editor.setEditorId(sysUser.getId());
		}
	}
}
