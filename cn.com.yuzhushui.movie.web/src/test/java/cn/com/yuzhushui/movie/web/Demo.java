package cn.com.yuzhushui.movie.web;

import java.util.ArrayList;
import java.util.List;

public class Demo {
	public static void main(String[] rags) {
		List<String> list=initData(10000);
		MyThreads mt = new MyThreads(list);
		for (int i = 0; i < mt.list.size(); i++) {
			new Thread(mt).start();
		}

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

class MyThreads implements Runnable{
	List<String> list;
	
	public MyThreads(){}
	
	public MyThreads(List<String> list){
		this.list=list;
	}
	
	int init = 0;
	public void run(){
		System.out.println(list.get(init));
	}
}