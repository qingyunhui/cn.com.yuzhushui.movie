package cn.com.yuzhushui.movie.aspects;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import qing.yun.hui.common.annotations.ActionAnno;
import qing.yun.hui.common.utils.DateUtil;

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


	public ActionHandle() {
		logger.info("*************ActionHandle()方法执行*************");
	}
	
	/**切入点*/
	@Pointcut("@annotation(qing.yun.hui.common.annotations.ActionAnno)")//指定类
	public void executeActionPointcut() {
		System.err.println("*************executeActionPointcut()方法执行...");
	}
	
	 // 定义有参数的切入点,参数名称需相同。这里对拦截到的方法只有只有一个String参数的方法才有用  
    /*@Before("anyMethod() && args(userName)")  
    public void doAccessCheck(String userName) {  
        System.out.println("前置通知-->>" + userName);  
    }*/  
  
    // 获取有返回结果的通知  
    @AfterReturning(pointcut = "executeActionPointcut()", returning = "result")  
    public void doAfterReturning(String result) {  
        System.out.println("ActionHandle.后置通知-->>" + result);  
    }  
	
	/*@AfterReturning
	public void doAfterReturning(Object obj){
		logger.info("======================>doAfterReturning.end.result="+JSONObject.toJSONString(obj));
	}*/
  
	
    @After(value="executeActionPointcut()")
    public void doAfter(JoinPoint joinPoint) {  
    	logger.info("around." + joinPoint.getTarget().getClass() + "对象上用"+joinPoint.getKind()+","+joinPoint.getArgs()+","+joinPoint.getClass()+","+joinPoint.getTarget()+","+joinPoint.getSignature().getName() + "方法进行对 '");
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		logger.info("methodName:"+methodName+",arguments:"+JSONObject.toJSONString(arguments));
		Class<?> targetClass;
		try {
			targetClass = Class.forName(targetName);
			Method[] methods = targetClass.getMethods();
			for (Method method : methods) {
				if(method.getName().equals(methodName)){
					logger.info("methodName:"+JSONObject.toJSONString(methodName));
					ActionAnno acAno=method.getAnnotation(ActionAnno.class);
					if(null!=acAno){
						logger.info("action 执行时间："+DateUtil.dateToString(new Date(), DateUtil.YYYY_MM_DD_HH_MM_SS)+"，执行方法："+methodName+"，执行动作："+acAno.name()+".");
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		logger.info("------------->@After........");  
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
