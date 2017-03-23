package cn.com.yuzhushui.movie.sys.biz.service;

import java.util.List;

import qing.yun.hui.common.annotations.WarningAnno;
import cn.com.yuzhushui.movie.common.base.BaseService;
import cn.com.yuzhushui.movie.common.base.ResponseData;
import cn.com.yuzhushui.movie.sys.biz.entity.SysData;
/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 15:37:52
 * @history
 */
public interface SysDataService extends BaseService<SysData,Integer>{
	
	@WarningAnno(theme="insert sysData interface")
	public int add(List<SysData> models);
	
	public int update(SysData data);
	
	@WarningAnno(theme="更新.")
	ResponseData updateSysData(SysData data);
    
}