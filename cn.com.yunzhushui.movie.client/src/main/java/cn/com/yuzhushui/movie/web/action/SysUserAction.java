/*package cn.com.yuzhushui.movie.web.action;

import cn.com.yuzhushui.movie.biz.entity.SysUser;
import cn.com.yuzhushui.movie.biz.service.SysUserService;
import cn.com.yuzhushui.movie.web.vo.SysUserVO;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.com.yuzhushui.smiles8.common.bean.Constant;
import cn.com.yuzhushui.smiles8.common.bean.WebPager;
import qing.yun.hui.common.utils.BeanUtil;
import cn.com.yuzhushui.smiles8.common.bean.vo.MySessionInfo;

*//**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-10-19 23:42:51
 * @history
 *//*
@Controller
@RequestMapping(SysUserAction.ACTION_PATH)
@SessionAttributes({Constant.MY_SESSION_INFO})
public class SysUserAction {
	//用于指定jsp页面的相对位置
	//protected static final String MODEL_PATH="/sys/sysUser";
	//一般用于重定向用
	protected static final String ACTION_PATH="/sys/sysUser";
	
	@Autowired
	private SysUserService sysUserService;
	
	*//**
	 * 分页查询
	 * @return
	 *//*
	@RequestMapping(value="list", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView list(SysUserQuery query){
		ModelAndView modelAndView = new ModelAndView(ACTION_PATH+"/list");
		
		Map<String, Object> map = BeanUtil.pojoToMap(query);
		
		WebPager pager = sysUserService.queryPage(map);
		
		modelAndView.addObject(WebPager.PAGE_NAME, pager);
		modelAndView.addObject(Constant.QUERY, query);
		return modelAndView;
	}
	
	*//**
	 * 查询,解决查询刷新问题
	 * @return
	 *//*
	@RequestMapping(value="doList", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView doList(SysUserQuery query,RedirectAttributes redirectAttributes){
		ModelAndView modelAndView = new ModelAndView("redirect:"+ACTION_PATH+"/list.htm");
		redirectAttributes.addFlashAttribute(query);
		return modelAndView;
	}
	
	*//**
	 * 提交删除
	 * @return
	 *//*
	@RequestMapping("doDelete")
	public ModelAndView doDelete(java.lang.Integer[] ids,RedirectAttributes redirectAttributes){
		ModelAndView modelAndView = new ModelAndView("redirect:"+ACTION_PATH+"/list.htm");
		sysUserService.delSysUser(Arrays.asList(ids));
		redirectAttributes.addFlashAttribute("feedBackMsg","删除成功！");
		return modelAndView;
	}
	
	*//**
	 * 详情
	 * @return
	 *//*
	@RequestMapping("detail")
	public ModelAndView detail(java.lang.Integer id){
		ModelAndView modelAndView = new ModelAndView(ACTION_PATH+"/detail");
		
		SysUser sysUser = sysUserService.getSysUserById(id);
		SysUserForm sysUserForm = new SysUserForm();
		
		BeanUtils.copyProperties( sysUser,sysUserForm);
		modelAndView.addObject("sysUserForm", sysUserForm);
		return modelAndView;
	}
	
	*//**
	 * 新增
	 * @return
	 *//*
	@RequestMapping("add")
	public ModelAndView add(){
		ModelAndView modelAndView = new ModelAndView(ACTION_PATH+"/add");
		modelAndView.getModel().put("sysUserForm", new SysUserForm());
		return modelAndView;
	}
	
	*//**
	 * 提交新增
	 * @return
	 *//*
	@RequestMapping(value="doAdd", method = {RequestMethod.POST})
	public ModelAndView doAdd(@Valid SysUserForm sysUserForm, BindingResult result,@ModelAttribute(Constant.MY_SESSION_INFO) MySessionInfo sessionInfo,
			RedirectAttributes redirectAttributes){
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(sysUserForm, sysUser);
		sysUserService.addSysUser(sysUser);
		redirectAttributes.addFlashAttribute("feedBackMsg","保存成功！");
		return new ModelAndView("redirect:"+ACTION_PATH+"/list.htm");
	}
	
	*//**
	 * 编辑
	 * @return
	 *//*
	@RequestMapping("edit")
	public ModelAndView edit(java.lang.Integer id){
		ModelAndView modelAndView = new ModelAndView(ACTION_PATH+"/edit");
		SysUser sysUser = sysUserService.getSysUserById(id);
		SysUserForm sysUserForm = new SysUserForm();
		
		BeanUtils.copyProperties( sysUser,sysUserForm);
		modelAndView.addObject("sysUserForm", sysUserForm);
		return modelAndView;
	}
	*//**
	 * 编辑保存
	 * @return
	 *//*
	@RequestMapping(value="doEdit", method = {RequestMethod.POST})
	public ModelAndView doEdit(@Valid SysUserForm sysUserForm, BindingResult result,@ModelAttribute(Constant.MY_SESSION_INFO) MySessionInfo sessionInfo,
			RedirectAttributes redirectAttributes){
		SysUser sysUser = new SysUser();
		BeanUtils.copyProperties(sysUserForm, sysUser);
		sysUserService.updateSysUserSelective(sysUser);
		redirectAttributes.addFlashAttribute("feedBackMsg","修改成功！");
		return new ModelAndView("redirect:"+ACTION_PATH+"/list.htm");
	}
}*/