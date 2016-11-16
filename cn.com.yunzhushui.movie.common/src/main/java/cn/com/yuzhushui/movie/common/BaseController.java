package cn.com.liangdian.diaoyu.common.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.pagehelper.PageInfo;

import cn.com.liangdian.diaoyu.common.bean.ResponseData;
import cn.com.liangdian.diaoyu.constant.Constant;
import cn.com.liangdian.diaoyu.enums.Message;
import cn.com.liangdian.diaoyu.util.EnumUtil;
import cn.com.liangdian.diaoyu.util.StringUtil;
import cn.com.liangdian.diaoyu.util.validata.ValidataUtil;

/**
 * @Description: BaseController定义一些基本的action
 * @param <MODEL> BEAN对象
 * @param <FORM>  表单对象
 * @param <KEY_TYPE> 表主键类型
 */
public abstract class BaseController<MODEL extends BaseModel<KEY_TYPE>, FORM extends BaseForm<KEY_TYPE>, KEY_TYPE extends Serializable> {
	
	@Autowired
	BaseService<MODEL, KEY_TYPE> baseService;
	
	@Autowired
	private HttpServletRequest request;

	public BaseService<MODEL, KEY_TYPE> getBaseService() {
		return baseService;
	}

	/**
	 * @Description: 列表页面 
	 * @param request
	 */
	@RequestMapping(value = "list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView(getActionPath() + "/list");
		return modelAndView;
	}
	
	/**
	 * 分页 查询
	 * @param request
	 */
	@RequestMapping(value="doList.json", method={RequestMethod.POST})
	@ResponseBody
	public PageInfo<MODEL> doList() {
		BaseQuery query = initDataByBaseQuery();
		PageInfo<MODEL> queryPage = baseService.queryPage(query);
		return queryPage;
	}
	
	/**
	 * 
	 * @Description: 新增  
	 * @param form
	 * @return ModelAndView
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "add")
	public ModelAndView add(FORM form) {
		ModelAndView modelAndView = new ModelAndView(getActionPath() + "/add");
		FORM f = null;
		Class<KEY_TYPE> keyType = null;
		try {
			Class<FORM> genericType2 = getGenericType(1);
			f = genericType2.newInstance();
			keyType = getGenericType(2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(!keyType.isAssignableFrom(Integer.class)) {// 当主键不为自增时设置UUID
			form.setUuid((KEY_TYPE) UUID.randomUUID().toString().replace("-", ""));// TODO 
		} 
		modelAndView.addObject(StringUtil.firstLetterConvert(f.getClass(), false), form);
		modelAndView.addObject("validate", ValidataUtil.getValidate(f.getClass()));
		return modelAndView;
	}

	/**
	 * @Description: 提交新增 
	 * @param form
	 * @param result
	 * @param redirectAttributes
	 */
	@RequestMapping(value = "doAdd", method = { RequestMethod.POST })
	public ModelAndView doAdd(@Validated FORM form,BindingResult result,RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView(getActionPath() + "/add");
			modelAndView.addObject(StringUtil.firstLetterConvert(form.getClass(),false), form);
			modelAndView.addObject("validate", ValidataUtil.getValidate(form.getClass()));
			return modelAndView;
		}
		Class<MODEL> modelClass = getGenericType(0);
		MODEL model = null;
		try {
			model = modelClass.newInstance();
			BeanUtils.copyProperties(form, model);
			baseService.add(model);
			redirectAttributes.addFlashAttribute(Constant.MESSAGE,EnumUtil.getEnumToJSON(Message.SUCCESS_ADD));
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(Constant.MESSAGE,EnumUtil.getEnumToJSON(Message.UN_KNOWN_ERROR_ADD));
		}catch(Exception e){
			redirectAttributes.addFlashAttribute(Constant.MESSAGE,EnumUtil.getEnumToJSON(Message.UN_SUCCESS_ADD));
			e.printStackTrace();
		}
		ModelAndView modelAndView = new ModelAndView("redirect:" + (StringUtil.isEmpty(form.getActionPath())?getActionPath():form.getActionPath()) + "/list.htm");
		return modelAndView;
	}
	
	/**
	 * @Description: 更新 
	 * @param id
	 */
	@RequestMapping(value="update")
	public ModelAndView update(KEY_TYPE id) {
		MODEL model = baseService.query(id);
		FORM form = null;
		try {
			Class<FORM> genericType = getGenericType(1);
			form = genericType.newInstance();
			BeanUtils.copyProperties(model, form);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView modelAndView = new ModelAndView(getActionPath() + "/update");
		modelAndView.addObject(StringUtil.firstLetterConvert(form.getClass(),false), form);
		modelAndView.addObject("validate", ValidataUtil.getValidate(form.getClass()));
		return modelAndView;
	}
	
	/**
	 * @Description: 提交更新  
	 * @param form
	 * @param result
	 * @param redirectAttributes
	 */
	@RequestMapping(value = "doUpdate", method = { RequestMethod.POST })
	public ModelAndView doUpdate(@Validated FORM form,BindingResult result, RedirectAttributes redirectAttributes) throws Exception {
		if(result.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView(getActionPath() + "/update");
			modelAndView.addObject(StringUtil.firstLetterConvert(form.getClass(),false), form);
			modelAndView.addObject("validate", ValidataUtil.getValidate(form.getClass()));
			return modelAndView;
		}
		Class<MODEL> modelClass = getGenericType(0);
		MODEL model = modelClass.newInstance();
		BeanUtils.copyProperties(form, model);
		try {
			baseService.update(model);
			redirectAttributes.addFlashAttribute(Constant.MESSAGE,EnumUtil.getEnumToJSON(Message.SUCCESS_UPDATE));
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute(Constant.MESSAGE,EnumUtil.getEnumToJSON(Message.UN_SUCCESS_UPDATE));
			e.printStackTrace();
		}
		ModelAndView modelAndView = new ModelAndView("redirect:" + getActionPath() + "/list.htm");
		return modelAndView;
	}

	/**
	 * @Description: 查看详情 
	 * @param id
	 */
	@RequestMapping(value="detail")
	public ModelAndView detail(KEY_TYPE id) {
		MODEL model = baseService.query(id);
		FORM form = null;
		try {
			Class<FORM> genericType = getGenericType(1);
			form = genericType.newInstance();
			BeanUtils.copyProperties(model, form);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView modelAndView = new ModelAndView(getActionPath() + "/detail");
		modelAndView.addObject(StringUtil.firstLetterConvert(form.getClass(),false), form);
		return modelAndView;
	}

	/**
	 * @Description: 删除 
	 * @param ids
	 * @return ResponseData
	 */
	@RequestMapping(value = "doDelete.json")
	@ResponseBody
	public ResponseData doDelete(KEY_TYPE[] ids) {
		int deleteNum = baseService.delete(Arrays.asList(ids));
		ResponseData rd = new ResponseData();
		rd.setMsg("成功删除【" + deleteNum + "】条数据！");
		rd.addData("ids", ids);
		return rd;
	}

	public abstract String getActionPath();// 获取action路径
	
	/**
	 * 组装查询条件
	 * @param request
	 * @return BaseQuery
	 */
	protected BaseQuery initDataByBaseQuery() {
		BaseQuery query = new BaseQuery();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String key = parameterNames.nextElement();
			String value = request.getParameter(key);
			if(key.startsWith("arr_")) {
				String[] values = request.getParameterValues(key);
				key = key.split("_|\\[")[1];
				query.getQueryData().put(key, values);
				continue;
			}
			switch (key) {
			case "pageNum":
				query.setPageNum(Integer.parseInt(value));
				break;
			case "pageSize":
				query.setPageSize(Integer.parseInt(value));
				break;
			case "orderColumns":
				query.setOrderColumns(value);
				break;
			case "orderDirection":
				query.setOrderDirection(value);
				break;
			default:
				query.getQueryData().put(key, value);
				break;
			}
		}
		query.setOrderBy();// 将排序字段和排序方向拼接
		return query;
	}

	/**
	 * @Description: 获取类型
	 * @return Class<T>
	 */
	@SuppressWarnings({"unchecked" })
	public <T> Class<T> getGenericType(int index) {
		Type genType = getClass().getGenericSuperclass();
		if (genType instanceof ParameterizedType) {
			Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
			return (Class<T>) params[index];
		}
		return null;
	}

	public HttpServletRequest getRequest() {
		return request;
	}
}
