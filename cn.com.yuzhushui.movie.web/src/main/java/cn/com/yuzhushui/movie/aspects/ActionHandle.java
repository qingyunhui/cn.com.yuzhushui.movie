package cn.com.yuzhushui.movie.aspects;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import qing.yun.hui.common.annotations.ActionAnno;
import qing.yun.hui.common.utils.DateUtil;
import qing.yun.hui.common.utils.StringUtil;
import cn.com.yuzhushui.movie.common.util.SessionUtil;
import cn.com.yuzhushui.movie.enums.SysWarningEnum;
import cn.com.yuzhushui.movie.sys.biz.entity.SysUser;
import cn.com.yuzhushui.movie.sys.biz.entity.SysWarning;
import cn.com.yuzhushui.movie.sys.biz.service.SysWarningService;

import com.alibaba.fastjson.JSONObject;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月22日上午9:26:14
 **/
@Component
@Aspect
public class ActionHandle implements InitializingBean{

	Logger logger=org.slf4j.LoggerFactory.getLogger(ActionHandle.class);
	
	@Autowired
	private SysWarningService sysWarningService;

	public ActionHandle() {}
	
	/**切入点*/
	@Pointcut("@annotation(qing.yun.hui.common.annotations.ActionAnno)")//指定类
	public void executeActionPointcut() {
		logger.info("*************executeActionPointcut()方法执行...");
	}
	
	@AfterReturning("executeActionPointcut()")  
    public void doAfter(){  
		logger.info("ActionHandle.后置通知：joinPoint");  
    }  
      
    @AfterThrowing("executeActionPointcut()")  
    public void doAfterThrow(){  
    	logger.info("ActionHandle.例外通知");  
    }  
    
    // 调用的方法出现异常才会调用这个方法  
    @AfterThrowing(value="executeActionPointcut()",throwing = "e")  
    public void doAfterThrowing(Exception e) {  
    	logger.info("异常通知-->" + e);  
    }  
	
    @Around("executeActionPointcut()")  
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{  
        System.out.println("ActionHandle.进入环绕通知");  
        Object object = pjp.proceed();//执行该方法返回结果(返回值)
        Object[] args= pjp.getArgs();//参数
        Object target=pjp.getTarget();
        MethodSignature signatures=(MethodSignature)pjp.getSignature();
//        Class<?> clz=signatures.getReturnType();//返回值类型...
        String signatururesName=signatures.getName();//方法名 
        String targetName=target.getClass().getName();//类名:SysDataServiceImpl
        Class<?> targetClz=Class.forName(targetName);
        Method[] methods= targetClz.getMethods();
        SysUser sysUser= SessionUtil.getSysUser();
        String operator=null==sysUser?null:sysUser.getName();
        for(Method method:methods){
        	String methodName=method.getName();
        	if(methodName.equals(signatururesName)){
        		ActionAnno anno= method.getAnnotation(ActionAnno.class);
        		if(null!=anno){
        			try {
        				SysWarning entity=new SysWarning();
        				entity.setWarningDate(new Date());
        				entity.setAction(anno.action());
        				entity.setMethodName(methodName);
        				entity.setReturnValue(JSONObject.toJSONString(object));
        				entity.setArgs(JSONObject.toJSONString(args));
        				entity.setOperator(operator);
        				entity.setAnnotations(String.valueOf(anno));
        				entity.setIp(StringUtil.getIPAddress());
        				entity.setStatus(SysWarningEnum.Status.UN_NOTIFIED.getValue());
        				sysWarningService.add(entity);
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("==============>sysWarningService.add(entity) is error.{}",new Object[]{JSONObject.toJSONString(e)});
					}
        			logger.info("service 执行时间："+DateUtil.dateToString(new Date(), DateUtil.YYYY_MM_DD_HH_MM_SS)+"，执行方法："+methodName+"，执行动作："+anno.action()+".");
        		}
        	}
        }
        return object;  
    }  
    
    // 调用的方法出现异常才会调用这个方法  
   /* @AfterThrowing( throwing = "e")  
    public void doAfterThrowing(Exception e) {  
        System.out.println("异常通知-->" + e);  
    }  
  */
	/*@SuppressWarnings("rawtypes")
	public static List<SysOperationLog> getAnnOperationLogs(List<Object> objects,ProceedingJoinPoint joinPoint)throws Exception {
		System.err.println("around." + joinPoint.getTarget().getClass() + "对象上用"+joinPoint.getKind()+","+joinPoint.getArgs()+","+joinPoint.getClass()+","+joinPoint.getTarget()+","+joinPoint.getSignature().getName() + "方法进行对 '");
		List<SysOperationLog> logs=new ArrayList<SysOperationLog>();
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] tmpClzs = method.getParameterTypes();
				if (tmpClzs.length == arguments.length) {
					OperationLog annLog = method.getAnnotation(OperationLog.class);
					if (null!=annLog) {
						String clzName=annLog.clz().getSimpleName();
						boolean implment=isImplement(annLog.clz(), IOperationLog.class);
						if(implment){
							List<?> list=JSON.parseArray(arguments[0].toString(), annLog.clz());
							GenericManager manager = SessionUtil.getBean(StringTool.toLowerCaseFirstOne(clzName)+ "ManagerImpl");
							Object tmp=annLog.clz().newInstance();
							for(Object o:list){
								BeanUtils.copyProperties(o, tmp);
								IOperationLog op=(IOperationLog)tmp;
								SysOperationLog log=new SysOperationLog();
								log.setTableCode(op.getId()+"");
								log.setSysId(annLog.sysId());
								log.setOperateType(Integer.parseInt(annLog.operateType().getKey().toString()));
								log.setOperation(annLog.operation());
								logs.add(log);
								Object obj=manager.getById(op.getId());
								objects.add(obj);
							}
							System.err.println(manager.getClass());
						}
					}
					break;
				}
			}
		}
		return logs;
	}*/
	
	/**
	 * 判断某个类是否实现了某个接口
	 * @param implementClass 接口实现类
	 * @param interfaceClass 接口
	 * @return boolean
	 * */
	public static boolean isImplement(Class<?> implementClass, Class<?> interfaceClass){
		Class<?>[] clazzs = implementClass.getInterfaces();
		for (Class<?> clazz : clazzs) {
			if(clazz ==interfaceClass ){
				return true;
			}
		}
		return false;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info("------------->Action.afterPropertiesSet........");  
		
	}
	
}
