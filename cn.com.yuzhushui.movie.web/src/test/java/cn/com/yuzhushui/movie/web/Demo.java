package cn.com.yuzhushui.movie.web;

import java.util.ArrayList;
import java.util.List;

import qing.yun.hui.common.utils.WebUtil;

public class Demo {
	public static void main(String[] rags) {
		
		System.out.println(WebUtil.getTotalPageCount(10, 20));
		
		List<String> datas=initData(100);
		int count=0;
		for(int i=0;i<datas.size();i++){
			count++;
		}
		System.out.println(count==datas.size());
		
		
	/*	List<String> list=initData(10000);
		MyThreads2 mt = new MyThreads2(list);
		for (int i = 0; i < mt.list.size(); i++) {
			new Thread(mt).start();
		}*/

	}
	
	protected static List<String> initData(int count){
		Long startTime=System.currentTimeMillis();
		List<String> datas=new ArrayList<String>();
		for(int i=0;i<count;i++){
			datas.add(i+"");
		}
		Long endTime=System.currentTimeMillis();
		System.out.println(count+"条记录耗时:"+(endTime-startTime)+"毫秒。");
		return datas;
	}
}

class MyThreads2 implements Runnable{
	List<String> list;
	
	public MyThreads2(){}
	
	public MyThreads2(List<String> list){
		this.list=list;
	}
	
	int init = 0;
	public void run(){
		System.out.println(list.get(init));
	}
}