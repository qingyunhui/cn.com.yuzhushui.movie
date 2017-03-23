package cn.com.yuzhushui.movie.aspects;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import qing.yun.hui.common.annotations.WarningAnno;
import qing.yun.hui.common.utils.DateUtil;

import com.alibaba.fastjson.JSONObject;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月20日下午5:13:52
 **/
@Component
@Aspect
public class WarningHandle implements InitializingBean{
	
	Logger logger=org.slf4j.LoggerFactory.getLogger(WarningHandle.class);


	public WarningHandle() {
		logger.info("*************WarningHandle()方法执行*************");
	}
	
	/**切入点*/
	@Pointcut("@annotation(qing.yun.hui.common.annotations.WarningAnno)")//指定类
	public void executePointcut() {
		System.err.println("*************WarningHandle.executePointcut()方法执行...");
	}
	
	
	@AfterReturning("executePointcut()")  
    public void doAfter(){  
        System.out.println("WarningHandle.后置通知：joinPoint");  
    }  
      
    @AfterThrowing("executePointcut()")  
    public void doAfterThrow(){  
        System.out.println("WarningHandle.例外通知");  
    }  
    
    // 调用的方法出现异常才会调用这个方法  
   /* @AfterThrowing( throwing = "e")  
    public void doAfterThrowing(Exception e) {  
        System.out.println("异常通知-->" + e);  
    }  */
    
  /*WarningHandle.进入环绕通知
    [2017-03-23 10:17:35] [INFO] [cn.com.yuzhushui.movie.aspects.WarningHandle] - ******MethodSignature:{"declaringType":"cn.com.yuzhushui.movie.sys.biz.service.impl.SysDataServiceImpl","declaringTypeName":"cn.com.yuzhushui.movie.sys.biz.service.impl.SysDataServiceImpl","exceptionTypes":[],"method":{"accessible":false,"annotations":[{}],"bridge":false,"declaringClass":"cn.com.yuzhushui.movie.sys.biz.service.impl.SysDataServiceImpl","exceptionTypes":[],"genericExceptionTypes":[],"genericParameterTypes":["cn.com.yuzhushui.movie.sys.biz.entity.SysData"],"genericReturnType":"cn.com.yuzhushui.movie.common.base.ResponseData","modifiers":1,"name":"updateSysData","parameterAnnotations":[[]],"parameterTypes":["cn.com.yuzhushui.movie.sys.biz.entity.SysData"],"returnType":"cn.com.yuzhushui.movie.common.base.ResponseData","synthetic":false,"typeParameters":[],"varArgs":false},"modifiers":1,"name":"updateSysData","parameterNames":["data"],"parameterTypes":["cn.com.yuzhushui.movie.sys.biz.entity.SysData"],"returnType":"cn.com.yuzhushui.movie.common.base.ResponseData"}
    [2017-03-23 10:17:35] [INFO] [cn.com.yuzhushui.movie.aspects.WarningHandle] - WarningHandle.退出方法,object={"data":{"success":200},"msg":"更新成功.","responseStatus":"SUCCESS","status":"SUCCESS"},args=[{"comment":"傻子：5","creater":"张三0","createrId":0,"ctime":1490232784000,"dataId":1,"id":1,"loseEfficacyDate":1490232784000,"targetId":0,"targetTable":"testTable0"}],kind=method-execution,signature={"declaringType":"cn.com.yuzhushui.movie.sys.biz.service.impl.SysDataServiceImpl","declaringTypeName":"cn.com.yuzhushui.movie.sys.biz.service.impl.SysDataServiceImpl","exceptionTypes":[],"method":{"accessible":false,"annotations":[{}],"bridge":false,"declaringClass":"cn.com.yuzhushui.movie.sys.biz.service.impl.SysDataServiceImpl","exceptionTypes":[],"genericExceptionTypes":[],"genericParameterTypes":["cn.com.yuzhushui.movie.sys.biz.entity.SysData"],"genericReturnType":"cn.com.yuzhushui.movie.common.base.ResponseData","modifiers":1,"name":"updateSysData","parameterAnnotations":[[]],"parameterTypes":["cn.com.yuzhushui.movie.sys.biz.entity.SysData"],"returnType":"cn.com.yuzhushui.movie.common.base.ResponseData","synthetic":false,"typeParameters":[],"varArgs":false},"modifiers":1,"name":"updateSysData","parameterNames":["data"],"parameterTypes":["cn.com.yuzhushui.movie.sys.biz.entity.SysData"],"returnType":"cn.com.yuzhushui.movie.common.base.ResponseData"},target={},thiss={}
    [2017-03-23 10:17:35] [INFO] [cn.com.yuzhushui.movie.aspects.WarningHandle] - around.class cn.com.yuzhushui.movie.sys.biz.service.impl.SysDataServiceImpl对象上用method-execution,[Ljava.lang.Object;@702e630c,class org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint,cn.com.yuzhushui.movie.sys.biz.service.impl.SysDataServiceImpl@3621dafb,updateSysData方法进行对 '
    [2017-03-23 10:17:35] [INFO] [cn.com.yuzhushui.movie.aspects.WarningHandle] - methodName:updateSysData,arguments:[{"comment":"傻子：5","creater":"张三0","createrId":0,"ctime":1490232784000,"dataId":1,"id":1,"loseEfficacyDate":1490232784000,"targetId":0,"targetTable":"testTable0"}]
    [2017-03-23 10:17:35] [INFO] [cn.com.yuzhushui.movie.aspects.WarningHandle] - methodName:"updateSysData"
    [2017-03-23 10:17:35] [INFO] [cn.com.yuzhushui.movie.aspects.WarningHandle] - service 执行时间：2017-03-23 10:17:35，执行方法：updateSysData，执行动作：更新..
    [2017-03-23 10:17:35] [INFO] [cn.com.yuzhushui.movie.aspects.WarningHandle] - ------------->WarningHandle.@After........
    WarningHandle.后置通知：joinPoint
    [2017-03-23 10:17:35] [INFO] [cn.com.yuzhushui.movie.sys.web.action.SysDataAction] - ●﹏●第5条处理结束...?﹏?，处理结果：{"data":{"success":200},"msg":"更新成功.","responseStatus":"SUCCESS","status":"SUCCESS"}
   */
      
    @Around("executePointcut()")  
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable{  
        System.out.println("WarningHandle.进入环绕通知");  
        Object object = pjp.proceed();//执行该方法返回结果(返回值)
        Object[] args= pjp.getArgs();
        String kind=pjp.getKind();
        Signature signature= pjp.getSignature();
        Object target=pjp.getTarget();
        Object thiss=pjp.getThis();
        MethodSignature signatures=(MethodSignature)pjp.getSignature();
        signatures.getReturnType();//返回值类型...
        logger.info("******MethodSignature:{}",new Object[]{JSONObject.toJSONString(signatures)});
        logger.info("WarningHandle.退出方法,object={},args={},kind={},signature={},target={},thiss={}",new Object[]{JSONObject.toJSONString(object),JSONObject.toJSONString(args),kind,JSONObject.toJSONString(signature),JSONObject.toJSONString(target),JSONObject.toJSONString(thiss)});  
        return object;  
    }  
	
    @After(value="executePointcut()")
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
					WarningAnno wanno=method.getAnnotation(WarningAnno.class);
					if(null!=wanno){
						logger.info("service 执行时间："+DateUtil.dateToString(new Date(), DateUtil.YYYY_MM_DD_HH_MM_SS)+"，执行方法："+methodName+"，执行动作："+wanno.theme()+".");
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		logger.info("------------->WarningHandle.@After........");  
    }  
    
    @Override
	public void afterPropertiesSet() throws Exception {
    	logger.info("------------->WarningHandle.afterPropertiesSet........");  
	}
  
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
	
}
