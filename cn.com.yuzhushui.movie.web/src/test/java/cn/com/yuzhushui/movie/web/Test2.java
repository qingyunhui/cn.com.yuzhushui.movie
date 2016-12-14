package cn.com.yuzhushui.movie.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import cn.com.yuzhushui.movie.sys.biz.entity.SysData;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月14日下午9:29:30
 **/
public class Test2 {
	
	public static void main(String[] args){
		/*for(int i=0;i<10;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					for(int k=0;k<100;k++){
						System.out.println("thread.id="+Thread.currentThread().getId()+",thread.name="+Thread.currentThread().getName()+"，k="+k);
					}
				}
			}).start();
		}*/
		
		List<SysData> datas= initData(10000);
		try {
			String str=Test2.list2Str(datas, 100);
			System.out.println("str:"+str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	protected static List<SysData> initData(int count){
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
	
	
	public static String list2Str(final List<SysData> list, final int nThreads) throws Exception {
		if (list == null || list.isEmpty()) {
			return null;
		}
		int len = 0;
		for (SysData str : list) {
			len += str.getId();
		}
		StringBuffer ret = new StringBuffer(len);

		final int size = list.size();
		ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
		List<Future<String>> futures = new ArrayList<Future<String>>(nThreads);
		
		try {
			for (int i = 0; i < nThreads; i++) {
				final int j = i;
				Callable<String> task = new Callable<String>() {
					@Override
					public String call() throws Exception {
						int len = 0;
						for (int n = size / nThreads * j; n < size / nThreads * (j + 1); n++) {
							len += list.get(n).getId().toString().length();
						}
						StringBuffer sb = new StringBuffer(len);
						for (int n = size / nThreads * j; n < size / nThreads * (j + 1); n++) {
							sb.append(list.get(n).getId().toString());
						}
						return sb.toString();
					}
				};
				futures.add(executorService.submit(task));
			}
			for (Future<String> future : futures) {
				ret.append(future.get());
			}
		} finally {
			executorService.shutdown();
		}
		return ret.toString();
	}
}
