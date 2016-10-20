/*package cn.com.yuzhushui.movie.biz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.yuzhushui.smiles8.common.bean.WebPager;
import cn.com.yuzhushui.movie.biz.dao.SysUserMapping;

import java.util.List;
import java.util.Map;

import cn.com.yuzhushui.movie.biz.entity.SysUser;
import cn.com.yuzhushui.movie.biz.service.SysUserService;

*//**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-10-19 23:42:51
 * @history
 *//*
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService{
      
      @Autowired
      private SysUserMapping sysUserMapping;
    
      public int addSysUser(SysUser sysUser){
          return sysUserMapping.insert(sysUser);
      }
      
      public int addSysUserBatch(List<SysUser> sysUsers) {
    	  return sysUserMapping.insertBatch(sysUsers);
      }
      
      public int delSysUser(java.lang.Integer id){
    	  return sysUserMapping.delete(id);
      }
      
      public int updateSysUser(SysUser sysUser){
    	  return sysUserMapping.update(sysUser);
      }
      
      public int updateSysUserSelective(SysUser sysUser){
    	  return sysUserMapping.updateSelective(sysUser);
      }
     
      public WebPager queryPage(Map<String, Object> map) {
          WebPager webPager = new WebPager();
          webPager.init(map);
		
          webPager.setList(sysUserMapping.queryPage(map));
          webPager.paging(webPager.getPageNo(), webPager.getPageSize(), sysUserMapping.queryCount(map));
		
          return webPager;
      }
      
      public SysUser getSysUserById(java.lang.Integer id){
          return sysUserMapping.getById(id);
      }
      
      public int delSysUser(List<java.lang.Integer> ids){
		return sysUserMapping.deleteBatch(ids);
      }
}*/