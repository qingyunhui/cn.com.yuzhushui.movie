/*package cn.com.guangduo.iface.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import cn.com.guangduo.common.bean.Constant;
import cn.com.guangduo.iface.common.base.BaseController;
import cn.com.guangduo.iface.form.EmpForm;
import cn.com.guangduo.iface.po.Emp;
import cn.com.guangduo.iface.po.EmpEquipmentRel;
import cn.com.guangduo.iface.service.IEmpEquipmentRelService;

@Controller
@RequestMapping(EmpController.ACTION_PATH)
@SessionAttributes({Constant.MY_SESSION_INFO})
public class EmpController extends BaseController<Emp, EmpForm, Integer> {
	protected static final String ACTION_PATH="/emp";
	
	@Autowired
	private IEmpEquipmentRelService empEquipmentRelService;
	
	@Override
	public void doAddBefore(EmpForm empForm,BindingResult result) {
		//TODO 校验同一个设备下是否有相同的设备序号
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deleted", 0);
		map.put("pin", empForm.getPin());
		List<EmpEquipmentRel> curEmpRels=empEquipmentRelService.query(map);
		if(null!=curEmpRels &&curEmpRels.size()>0){
			result.rejectValue("pin", "设备内序列号已存在，请更换。","设备内序列号已存在，请更换!");
		}
	}
	
	@Override
	public void doAddAfter(Emp emp) {
		EmpEquipmentRel empRel=new EmpEquipmentRel();
		empRel.setEmpId(emp.getEmpId());
		empRel.setBatchEquipmentRelId(emp.getBatchEquipmentRelId());
		empRel.setCheckingDay(emp.getCheckingDay());
		empRel.setPin(emp.getPin());
		empEquipmentRelService.add(empRel);
	}
	
	@Override
	public void doUpdateAfter(Emp emp) {
		EmpEquipmentRel empRel=new EmpEquipmentRel();
		empRel.setEmpEquipmentRelId(emp.getEmpEquipmentRelId());
		empRel.setCheckingDay(emp.getCheckingDay());
		empEquipmentRelService.update(empRel);
	}
	
	*//**
	 * 根据给定设备Id查询对应设备下的所有人员...
	 * @param empEquipmentRelId 
	 * *//*
	@RequestMapping(value = "queryEmpByBatchEquipmentRelId")
	public ModelAndView queryEmpByBatchEquipmentRelId(EmpForm empForm) {
		ModelAndView modelAndView = new ModelAndView(getActionPath() + "/d_emp_listByBatchEquipmentRelId");
		modelAndView.addObject("empForm",empForm);
		return modelAndView;
	}
	
	
	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
}*/