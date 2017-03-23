package cn.com.yuzhushui.movie.sys.biz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import qing.yun.hui.common.annotations.WarningAnno;
import cn.com.yuzhushui.movie.common.base.BaseServiceImpl;
import cn.com.yuzhushui.movie.common.base.ResponseData;
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
	
	public int update(SysData data){
		return sysDataDao.update(data);
	}
	
	@WarningAnno(theme="更新.",returnType=ResponseData.class)
	@Override
	public ResponseData updateSysData(SysData data) {
		ResponseData rd=new ResponseData();
		if(null==data) {
			rd.setMsg("********更新失败.");
			return rd;
		}
		int count=sysDataDao.update(data);
		Map<String,Object> map=new HashMap<String,Object>();
		if(count>0){
			map.put("success", 200);
			rd.setMsg("更新成功.");
		}else{
			map.put("error", 404);
			rd.setMsg("更新失败.");
		}
		rd.setData(map);
		return rd;
	}
}