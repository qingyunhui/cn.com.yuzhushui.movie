package cn.com.yuzhushui.movie.sys.web.action;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;

import cn.com.yuzhushui.movie.common.base.BaseAction;
import cn.com.yuzhushui.movie.common.base.BaseService;
import cn.com.yuzhushui.movie.sys.biz.entity.SysData;
import cn.com.yuzhushui.movie.sys.biz.service.SysDataService;
import cn.com.yuzhushui.movie.sys.web.vo.SysDataForm;
import qing.yun.hui.common.struct.CallableData;
import qing.yun.hui.common.struct.CallableDataResult;
import qing.yun.hui.common.utils.WebUtil;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 15:37:52
 * @history
 */
@Controller
@RequestMapping(SysDataAction.ACTION_PATH)
public class SysDataAction extends BaseAction<SysData, SysDataForm, Integer>{
	
	protected int initCount=501;//初始化数据条数
	
	protected final int defaultCount=5;//默认处理条数
	
	protected Logger logger=LoggerFactory.getLogger(SysDataAction.class);
	
	@Autowired
	private BaseService<SysData,Integer> baseService;
	
	@Autowired
	private SysDataService sysDataService;
	
	//一般用于重定向用
	protected static final String ACTION_PATH="/sys/sysData";
	
	@Override
	public String getActionPath() {
		return ACTION_PATH;
	}
	
	
	/*
	 * N条线程，第M个线程;
	 * 谢谢，已经解决了，for(int i=M,i<COUNT/N;i+=N)
	 {
	 } 
	按照这个思路，然后在线程中加一个变量来处理，
	那么每个线程就是100 / 3取整后的条数，最后多出的100 - 3 * 33的那一条就加在第3个线程里处理好了 
	可以让每个线程处理自己的，不用都交给最后一个线程*/
	
	/**
	 * <p>开多个线程跑</p>
	 * **/
	@RequestMapping(value = "muiltThreadByAddBatch", method = { RequestMethod.POST,RequestMethod.GET })
	public String muiltThreadByAddBatch() {
		final int totalThread=4;
		logger.info("=====================>开始了哦(*+﹏+*)~ ");
		final List<SysData> datas=initData(initCount);
		for(int thread=0;thread<totalThread;thread++){
			final int curThread=thread;
			new Thread(new Runnable() {
				public void run() {
					 String threadName=Thread.currentThread().getName()+""+curThread;
					 logger.info("==============>{}启动...",new Object[]{threadName});
					 Long startTime=System.currentTimeMillis();
					 int count=0;
					 for(int i=curThread;i<datas.size();i+=totalThread){
						  count+=sysDataService.add(Arrays.asList(datas.get(i)));
						  logger.info("{}线程处理●﹏●第{}条处理成功...◑﹏◐",new Object[]{threadName,i});
					 }
					 logger.info("==============>【{}线程结束】....",new Object[]{threadName});
					 logger.info("=========>处理的有{}条，处理成功的有:{}条。",new Object[]{datas.size(),count});
					 Long endTime=System.currentTimeMillis();
					 Long ends=(endTime-startTime);
					 Long second=(ends/1000);
					 logger.info("=============>{}线程处理耗时：{}毫秒，{}秒，处理成功的有{}条。",new Object[]{threadName,ends,second,count});
				}
			}).start();;
		}
		return "redirect:"+ACTION_PATH+"/list.htm";
	}
	
	/**
	 * <p>开一个线程跑</p>
	 * **/
	@RequestMapping(value = "oneThreadByAddBatch", method = { RequestMethod.POST,RequestMethod.GET })
	public String oneThreadByAddBatch() {
		logger.info("=====================>开始了哦(*+﹏+*)~ ");
		//一个线程处理501条数据，耗时19秒-26秒左右。。
		new Thread(new Runnable() {
			public void run() {
				 logger.info("==============>【主线程开启】....");
				 Long startTime=System.currentTimeMillis();
				 int count=0;
				 List<SysData> datas=initData(initCount);
				 for(int i=0;i<datas.size();i++){
					  count+=sysDataService.add(Arrays.asList(datas.get(i)));
					  logger.info("●﹏●第{}条处理成功...◑﹏◐",new Object[]{count});
				 }
				 logger.info("==============>【主线程结束】....");
				 logger.info("=========>处理的有{}条，处理成功的有:{}条。",new Object[]{datas.size(),count});
				 Long endTime=System.currentTimeMillis();
				 Long ends=(endTime-startTime);
				 Long second=(ends/1000);
				 logger.info("=============>线程处理耗时：{}毫秒，{}秒。",new Object[]{ends,second});
			}
		}).start();
		return "redirect:"+ACTION_PATH+"/list.htm";
	}
	
	/**
	 * <p>多线程、然后分页插入</p>
	 * <p>（封装后的多线程）有返回值的多线程处理、实现Callabler接口<p>
	 * **/
	@RequestMapping(value = "callableBatchUpdateDatas", method = { RequestMethod.POST,RequestMethod.GET })
	public String callableBatchUpdateDatas() {
		Map<String,Object> map=new HashMap<String,Object>();
		final List<SysData> datas=sysDataService.query(map);
		final int totalPage=WebUtil.getTotalPageCount(datas.size(), defaultCount);
		Long startTime=System.currentTimeMillis();
		final CallableData<Integer> callData=new CallableData<Integer>();
		for(int i=0;i<totalPage;i++){
			final int curThread=i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						int random=new Random().nextInt(100);
						 System.out.println("random:"+random);
						 int tmp= random%4==0?random/0:0;
						 boolean isTrue=updateBatch(datas, curThread, totalPage, defaultCount);
						 System.out.println("isTrue:"+isTrue+"tmp:"+tmp);
						 if(!isTrue){
							 int totalCount=null==callData.getData()?0:callData.getData().intValue();
							 callData.setData(totalCount+1);
						 }
					} catch (Exception e) {
						int totalCount=null==callData.getData()?0:callData.getData().intValue();
						 callData.setData(totalCount+1);
					}
				}
			}).start();
		}
		
		logger.info("===========dodododo============");
		
		logger.info("===================>总记录条数:{}，总页数：{}页.",new Object[]{datas.size(),totalPage});
		Long endTime=System.currentTimeMillis();
		Long ends=(endTime-startTime);
		Long second=(ends/1000);
		logger.info("{}条记录，共耗时:{}毫秒，{}秒。",new Object[]{datas.size(),ends,second});
		return "redirect:"+ACTION_PATH+"/list.htm";
	}
	
	
	public CallableDataResult<CallableData<Boolean>> getCallableDataResultByUpdate(List<SysData> datas,int totalPage){
		final CallableDataResult<CallableData<Boolean>> callableResult=new CallableDataResult<CallableData<Boolean>>();
		ExecutorService executorService=Executors.newCachedThreadPool();
		List<Future<CallableData<Boolean>>> futures = new ArrayList<Future<CallableData<Boolean>>>();//创建有多个返回值的任务.
		final List<SysData> curDatas=datas;
		callableResult.setTotalThread(totalPage);
		final int curTotalPage=totalPage;
		try {
			for(int i=0;i<totalPage;i++){
				final int curThread=i;
				futures.add(executorService.submit(new Callable<CallableData<Boolean>>() {
					@Override
					public CallableData<Boolean> call() throws Exception {
						CallableData<Boolean> callableData=new CallableData<Boolean>();
						boolean success= updateBatch(curDatas, curThread, curTotalPage,defaultCount);
						callableData.setData(success);
						if(success){
							callableResult.setTotalSuccess(callableResult.getTotalSuccess()+1);
						}else{
							callableResult.setTotalFail(callableResult.getTotalFail()+1);
						}
						logger.info("=============>【线程】"+curThread+"，启动...");
						callableData.setThreadName("=============>【线程】"+curThread);
						return callableData;
					}
				}));
			}
			callableResult.setFutures(futures);
		} catch (Exception e) {
			logger.error(e+"");
		}finally {
			executorService.shutdown();
		}
		return callableResult;
	}
	
	/**
	 * @param datas 待处理的数据集
	 * @param curThread 当前线程（第几个线程）
	 * @param totalThread 总线程数(总页数)
	 * @param defaultCount 默认处理条数
	 * */
	protected Boolean updateBatch(List<SysData> datas,int curThread,int totalThread,int defaultCount){
		if(null==datas||datas.size()==0) return Boolean.FALSE;
		Boolean isSuccess=Boolean.TRUE;
		int count=0;
		for(int i=curThread;i<totalThread;i+=totalThread){
			List<SysData> updateDatas=null;
			int index=0;//索引开始位置..
			if(i==0){
				updateDatas=datas.subList(i, defaultCount);	//第一次循环索引位置从0开始，到defaultCount位置结束.
			}else{
				index=i*defaultCount;//第二次以后的循环以递增的方式处理:i*defaultCount位置开始，到(i+1)*defaultCount位置结束。
				int curCount=(i+1)*defaultCount;
				if(i==totalThread-1){
					curCount=datas.size();
				}
				updateDatas=datas.subList(index, curCount);
			}
			try {
				int tmp=0;
				for(SysData model:updateDatas){
					tmp=tmp+=1;
					model.setComment("霜期工.."+tmp);
					count+=sysDataService.update(model);
				}
				isSuccess=count>0;
				logger.info("=============第"+i+"页处理成功，处理的条数为:"+updateDatas.size()+"条，处理成功的条数为:"+count+"条。==============");
			} catch (Exception e) {
				logger.error("==================>e.",new Object[]{JSONObject.toJSONString(e)});
				isSuccess=Boolean.FALSE;
			}
		}
		return isSuccess;
	}
	
	/**
	 * <p>分页插入</p>
	 * <p>（封装后的多线程）有返回值的多线程处理、实现Callabler接口<p>
	 * **/
	@RequestMapping(value = "callableByBatchAddDatas", method = { RequestMethod.POST,RequestMethod.GET })
	public String callableByBatchAddDatas() {
		List<SysData> datas=initData(initCount);
		int totalPage=WebUtil.getTotalPageCount(datas.size(), defaultCount);
		Long startTime=System.currentTimeMillis();
		CallableDataResult<CallableData<Boolean>> callableResult=getCallableDataResult(datas, totalPage);
		List<Future<CallableData<Boolean>>>  futures= callableResult.getFutures();
		for(Future<CallableData<Boolean>> future:futures){
			try {
				CallableData<Boolean> callableData= future.get();//  ● get()方法用来获取执行结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回
				logger.info(callableData.getThreadName()+"，处理返回值："+callableData.getData());
			} catch (InterruptedException e) {
				logger.error("e.",new Object[]{JSONObject.toJSONString(e)});
			} catch (ExecutionException e) {
				logger.error("e.",new Object[]{JSONObject.toJSONString(e)});
			}
		}
		logger.info("==============>总线程数：{}条，处理成功的有：{}条，处理失败的有：{}条.",new Object[]{callableResult.getTotalThread(),callableResult.getTotalSuccess(),callableResult.getTotalFail()});
		if(callableResult.isAllSuccess()){
			logger.info("===============>所有线程都处理成功....");
		}else if(callableResult.isPartSuccess()){
			logger.info("===============>部分线程线程处理成功，处理成功的线程有：{}条.处理失败的线程有：{}条.",new Object[]{callableResult.getTotalSuccess(),callableResult.getTotalFail()});
		}else{
			logger.info("===============>粗大事了、木有一条线程是处理成功的....草....");
		}
		Long endTime=System.currentTimeMillis();
		Long ends=(endTime-startTime);
		Long second=(ends/1000);
		logger.info("{}条记录，插入数据库共耗时:{}毫秒，{}秒。",new Object[]{datas.size(),ends,second});
		return "redirect:"+ACTION_PATH+"/list.htm";
	}
	
	/**
	 * <p>分页插入</p>
	 * <p>有返回值的多线程处理、实现Callabler接口<p>
	 * **/
	@RequestMapping(value = "callableByBatchAdd", method = { RequestMethod.POST,RequestMethod.GET })
	public String callableByBatchAdd() {
		final List<SysData> datas=initData(initCount);
		final int totalPage=WebUtil.getTotalPageCount(datas.size(), defaultCount);//总页数;
		Long startTime=System.currentTimeMillis();
		ExecutorService executorService=Executors.newCachedThreadPool();//创建二个线程池.
		List<Future<Boolean>> futures = new ArrayList<Future<Boolean>>();//创建有多个返回值的任务.
		try {
			for(int i=0;i<totalPage;i++){
				final int curThread=i;
				futures.add(executorService.submit(new Callable<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						return addBatch(datas, curThread, totalPage, defaultCount);
					}
				}));
			}
			for(Future<Boolean> future:futures){
				System.out.println("任务终止否:"+future.isDone()+",返回值:"+future.get());
			}
		} catch (Exception e) {
			logger.error(e+"");
		}finally {
			executorService.shutdown();
		}
		Long endTime=System.currentTimeMillis();
		Long ends=(endTime-startTime);
		Long second=(ends/1000);
		logger.info("{}条记录，插入数据库共耗时:{}毫秒，{}秒。",new Object[]{datas.size(),ends,second});
		return "redirect:"+ACTION_PATH+"/list.htm";
	}
	
	/**
	 * @param datas 处理的总记录集
	 * @param totalPage 总页 数
	 * @return CallableDataResult
	 * */
	public CallableDataResult<CallableData<Boolean>> getCallableDataResult(List<SysData> datas,int totalPage){
		final CallableDataResult<CallableData<Boolean>> callableResult=new CallableDataResult<CallableData<Boolean>>();
		ExecutorService executorService=Executors.newCachedThreadPool();
		List<Future<CallableData<Boolean>>> futures = new ArrayList<Future<CallableData<Boolean>>>();//创建有多个返回值的任务.
		final List<SysData> curDatas=datas;
		callableResult.setTotalThread(totalPage);
		final int curTotalPage=totalPage;
		try {
			for(int i=0;i<totalPage;i++){
				final int curThread=i;
				futures.add(executorService.submit(new Callable<CallableData<Boolean>>() {
					@Override
					public CallableData<Boolean> call() throws Exception {
						CallableData<Boolean> callableData=new CallableData<Boolean>();
						boolean success= addBatch(curDatas, curThread, curTotalPage,defaultCount);
						callableData.setData(success);
						if(success){
							callableResult.setTotalSuccess(callableResult.getTotalSuccess()+1);
						}else{
							callableResult.setTotalFail(callableResult.getTotalFail()+1);
						}
						logger.info("=============>【线程】"+curThread+"，启动...");
						callableData.setThreadName("=============>【线程】"+curThread);
						return callableData;
					}
				}));
			}
			callableResult.setFutures(futures);
		} catch (Exception e) {
			logger.error(e+"");
		}finally {
			executorService.shutdown();
		}
		return callableResult;
	}
	
	/**
	 * <p>分页插入</p>
	 * <p>多线程处理、实现Runnable或者继承Thread类<p>
	 * **/
	@RequestMapping(value = "threadByBatchAdd", method = { RequestMethod.POST,RequestMethod.GET })
	public String threadByBatchAdd() {
		final List<SysData> datas=initData(initCount);
		final int totalPage=WebUtil.getTotalPageCount(datas.size(), defaultCount);
		Long startTime=System.currentTimeMillis();
		try {
			for(int i=0;i<totalPage;i++){
				final int curThread=i;
				new Thread(new Runnable() {
					@Override
					public void run() {
						addBatch(datas, curThread, totalPage, defaultCount);
					}
				}).start();
			}
		} catch (Exception e) {
			logger.error(e+"");
		}
		Long endTime=System.currentTimeMillis();
		Long ends=(endTime-startTime);
		Long second=(ends/1000);
		logger.info("{}条记录，插入数据库共耗时:{}毫秒，{}秒。",new Object[]{datas.size(),ends,second});
		return "redirect:"+ACTION_PATH+"/list.htm";
	}
	
	/**
	 * <p>分页插入</p>
	 * 对数据量比较大的情况下的处理..
	 * **/
	@RequestMapping(value = "addSysDatas2", method = { RequestMethod.POST,RequestMethod.GET })
	public String addSysDatas2() {
		logger.info("=====================调用baseService.add()=====================");
		int count=0;
		List<SysData> datas=initData(initCount);
		Long startTime=System.currentTimeMillis();
		try {
			int totalPage=WebUtil.getTotalPageCount(datas.size(), defaultCount);
			for(int i=0;i<totalPage;i++){
				List<SysData> insertDatas=null;
				int curPageCount=i+1;//当前页数;
				logger.info("==========第"+curPageCount+"页==========");
				int index=0;//索引开始位置..
				if(i==0){
					insertDatas=datas.subList(i, defaultCount);	//第一次循环索引位置从0开始，到defaultCount位置结束.
				}else{
					index=i*defaultCount;//第二次以后的循环以递增的方式处理:i*defaultCount位置开始，到(i+1)*defaultCount位置结束。
					int curCount=(i+1)*defaultCount;
					if(i==totalPage-1){
						curCount=datas.size();
					}
					insertDatas=datas.subList(index, curCount);
				}
				count+=baseService.add(insertDatas);
				logger.info("=============第"+curPageCount+"页处理成功，处理的条数为:"+insertDatas.size()+"条，处理成功的条数为:"+count+"条。==============");
			}
			Long endTime=System.currentTimeMillis();
			Long ends=(endTime-startTime);
			Long second=(ends/1000);
			logger.info("{}条记录，插入数据库共耗时:{}毫秒，{}秒。成功处理{}条。",new Object[]{datas.size(),ends,second,count});
		} catch (Exception e) {
			logger.error("************数据库操作异常，异常原因，{}",new Object[]{e});
		}
		logger.info("受影响的行数:"+count+"条。");
		logger.info("=====================调用baseService，end=====================");
		return "redirect:"+ACTION_PATH+"/list.htm";
	}
	
	@RequestMapping(value = "addSysDatas", method = { RequestMethod.POST,RequestMethod.GET })
	public String addSysDatas() {
		logger.info("=====================调用baseService.add()=====================");
		int count=0;
		List<SysData> datas=initData(initCount);
		Long startTime=System.currentTimeMillis();
		try {
			count=baseService.add(datas);
			Long endTime=System.currentTimeMillis();
			Long ends=(endTime-startTime);
			Long second=(ends/1000);
			logger.info("{}条记录，插入数据库共耗时:{}毫秒，{}秒。成功处理{}条。",new Object[]{datas.size(),ends,second,count});
		} catch (Exception e) {
			logger.error("************数据库操作异常，异常原因，{}",new Object[]{e});
		}
		logger.info("受影响的行数:"+count+"条。");
		logger.info("=====================调用baseService，end=====================");
		return "redirect:"+ACTION_PATH+"/list.htm";
	} 
	
	/**
	 * <p>初始化count条数据</p>
	 * @param count
	 * @return List<SysData>
	 * */
	protected List<SysData> initData(int count){
		Long startTime=System.currentTimeMillis();
		List<SysData> datas=new ArrayList<SysData>();
		for(int i=0;i<count;i++){
			SysData data=new SysData();
			data.setComment("测试:"+i);
			data.setCreater("张三"+i);
			data.setCreaterId(i);
			data.setDataId(i);
			data.setLoseEfficacyDate(new Date());
			data.setTargetId(i);
			data.setTargetTable("testTable"+i);
			datas.add(data);
		}
		Long endTime=System.currentTimeMillis();
		System.out.println(count+"条记录耗时:"+(endTime-startTime)+"毫秒。");
		return datas;
	}
	
	
	/**
	 * @param datas 待处理的数据集
	 * @param curThread 当前线程（第几个线程）
	 * @param totalThread 总线程数
	 * @param totalPage 总页数
	 * @param defaultCount 默认处理条数
	 * */
	protected Boolean addBatch(List<SysData> datas,int curThread,int totalThread,int totalPage,int defaultCount){
		if(null==datas||datas.size()==0) return Boolean.FALSE;
		Boolean isSuccess=Boolean.TRUE;
		int count=0;
		for(int i=curThread;i<totalThread;i+=totalThread){
			List<SysData> insertDatas=null;
			int index=0;//索引开始位置..
			if(i==0){
				insertDatas=datas.subList(i, defaultCount);	//第一次循环索引位置从0开始，到defaultCount位置结束.
			}else{
				index=i*defaultCount;//第二次以后的循环以递增的方式处理:i*defaultCount位置开始，到(i+1)*defaultCount位置结束。
				int curCount=(i+1)*defaultCount;
				if(i==totalThread-1){
					curCount=datas.size();
				}
				insertDatas=datas.subList(index, curCount);
			}
			try {
				count+=sysDataService.add(insertDatas);
				isSuccess=count>0;
				logger.info("=============第"+i+"页处理成功，处理的条数为:"+insertDatas.size()+"条，处理成功的条数为:"+count+"条。==============");
			} catch (Exception e) {
				isSuccess=Boolean.FALSE;
			}
		}
		return isSuccess;
	}
	
	/**
	 * @param datas 待处理的数据集
	 * @param curThread 当前线程（第几个线程）
	 * @param totalThread 总线程数(总页数)
	 * @param defaultCount 默认处理条数
	 * */
	protected Boolean addBatch(List<SysData> datas,int curThread,int totalThread,int defaultCount){
		if(null==datas||datas.size()==0) return Boolean.FALSE;
		Boolean isSuccess=Boolean.TRUE;
		int count=0;
		for(int i=curThread;i<totalThread;i+=totalThread){
			List<SysData> insertDatas=null;
			int index=0;//索引开始位置..
			if(i==0){
				insertDatas=datas.subList(i, defaultCount);	//第一次循环索引位置从0开始，到defaultCount位置结束.
			}else{
				index=i*defaultCount;//第二次以后的循环以递增的方式处理:i*defaultCount位置开始，到(i+1)*defaultCount位置结束。
				int curCount=(i+1)*defaultCount;
				if(i==totalThread-1){
					curCount=datas.size();
				}
				insertDatas=datas.subList(index, curCount);
			}
			try {
				count+=sysDataService.add(insertDatas);
				isSuccess=count>0;
				logger.info("=============第"+i+"页处理成功，处理的条数为:"+insertDatas.size()+"条，处理成功的条数为:"+count+"条。==============");
			} catch (Exception e) {
				isSuccess=Boolean.FALSE;
			}
		}
		return isSuccess;
	}
}
