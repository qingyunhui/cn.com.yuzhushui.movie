package cn.com.yuzhushui.movie.sys.biz.service;

import java.util.List;

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
	
	public int add(List<SysData> models);
	
	public int update(SysData data);
	
	ResponseData updateSysData(SysData data);
    
}