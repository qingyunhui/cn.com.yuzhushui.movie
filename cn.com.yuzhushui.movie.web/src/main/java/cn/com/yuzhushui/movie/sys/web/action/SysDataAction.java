package cn.com.yuzhushui.movie.sys.web.action;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.com.yuzhushui.movie.common.base.BaseAction;
import cn.com.yuzhushui.movie.common.base.BaseService;
import cn.com.yuzhushui.movie.sys.biz.entity.SysData;
import cn.com.yuzhushui.movie.sys.biz.service.SysDataService;
import cn.com.yuzhushui.movie.sys.web.vo.SysDataForm;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-20 15:37:52
 * @history
 */
@Controller
@RequestMapping(SysDataAction.ACTION_PATH)
public class SysDataAction extends BaseAction<SysData, SysDataForm, Integer>{
	
	protected int initCount=1003;
	
	protected int defaultCount=100;
	
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
	
	/**
	 * <p>分页插入</p>
	 * <p>有返回值的多线程处理、实现Callabler接口<p>
	 * **/
	@RequestMapping(value = "callableByBatchAdd", method = { RequestMethod.POST,RequestMethod.GET })
	public String callableByBatchAdd() {
		final List<SysData> datas=initData(initCount);
		final int totalPage=getTotalPageCount(datas.size(), defaultCount);
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
	 * <p>分页插入</p>
	 * <p>多线程处理、实现Runnable或者继承Thread类<p>
	 * **/
	@RequestMapping(value = "threadByBatchAdd", method = { RequestMethod.POST,RequestMethod.GET })
	public String threadByBatchAdd() {
		final List<SysData> datas=initData(initCount);
		final int totalPage=getTotalPageCount(datas.size(), defaultCount);
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
		List<SysData> datas=initData(100);
		Long startTime=System.currentTimeMillis();
		int defaultCount=10;
		try {
			int totalPage=getTotalPageCount(datas.size(), defaultCount);
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
		List<SysData> datas=initData(20000);
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
	 * 获取总页数
	 * @param totalCount 总记条数
	 * @param defaultCount 默认一次性处理defaultCount条记录
	 * @return 总页数
	 * */
	protected static int getTotalPageCount(int totalCount,int defaultCount){
		int totalSize=totalCount;
		int mod=-1;
		int pageCount=0;
		mod = totalSize % defaultCount;
		if (mod != 0) {
			pageCount = (totalSize / defaultCount) + 1;
		} else {
			pageCount = (totalSize / defaultCount);
		}
		return pageCount;
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
	 * @param totalThread 总线程数(总页数)
	 * @param defaultCount 默认处理条数
	 * */
	protected Boolean addBatch(List<SysData> datas,int curThread,int totalThread,int defaultCount){
		if(null==datas||datas.size()==0) return Boolean.FALSE;
		Boolean isSuccess=Boolean.TRUE;
		int count=0;
		for(int i=curThread;i<totalThread;i+=totalThread){
			List<SysData> insertDatas=null;
			logger.info("=====线程"+Thread.currentThread().getName()+"=====第"+i+"页==========");
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
				if(i%3==0){
					logger.info("*********线程."+i+"，e."+(1/0));
				}
				count+=sysDataService.add(insertDatas);
				logger.info("=============第"+i+"页处理成功，处理的条数为:"+insertDatas.size()+"条，处理成功的条数为:"+count+"条。==============");
			} catch (Exception e) {
				isSuccess=Boolean.FALSE;
			}
		}
		return isSuccess;
	}
}

//有返回值的多线程-
class CallableHandl implements Callable<Boolean>{
	
	int curThread;	//当前线程【第几个线程】
	int totalThread;//线程总数;
	public CallableHandl(){}
	
	public CallableHandl(int curThread,int totalThread){
		this.totalThread=totalThread;
		this.curThread=curThread;
	}
	@Override
	public Boolean call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}

