package cn.com.guangduo.iface.service;

import org.springframework.stereotype.Service;

import cn.com.guangduo.iface.common.base.BaseService;
import cn.com.guangduo.iface.po.Emp;

@Service
public class EmpService extends BaseService<Emp, Integer> implements IEmpService {
	// 扩展接口实现
    
}