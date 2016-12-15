package cn.com.yuzhushui.movie.sys.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.yuzhushui.movie.common.base.BaseServiceImpl;
import cn.com.yuzhushui.movie.sys.biz.dao.SysDataDao;
import cn.com.yuzhushui.movie.sys.biz.entity.SysData;
import cn.com.yuzhushui.movie.sys.biz.service.SysDataService;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 15:37:52
 * @history
 */
@Service("sysDataService")
public class SysDataServiceImpl extends BaseServiceImpl<SysData,Integer> implements SysDataService{
      
	@Autowired
	private SysDataDao sysDataDao;
	
	public int add(List<SysData> models) {
		int count = sysDataDao.insertBatch(models);
		return count;
	}
	
}