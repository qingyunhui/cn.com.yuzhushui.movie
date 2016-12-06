package cn.com.yuzhushui.movie.web;

import java.util.ArrayList;
import java.util.List;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月29日上午10:05:57
 **/
public class MyTest {
	
	public static void main(String[] args){

		int count=39;
		int defaultCount=10;//默认处理10条数据
		List<String> list=initList(count);
		
		println(list);
		
		int totalPageCount=getTotalPageCount(list, defaultCount);
		
		todoBatch(list, defaultCount, totalPageCount);
	}
	
	public static void todoBatch(List<String> list,int defaultPageSize,int totalPageCount){
		System.out.println("========================开始拆分:========================");
		if(list.size()<=defaultPageSize){
			System.out.println("==========第"+0+"页");
			println(list);
			return;
		}
		
		for(int i=0;i<totalPageCount;i++){
			System.out.println("==========第"+i+"页");
			List<String> newList=null;
			int index=0;
			if(i==0){
				newList=list.subList(i, defaultPageSize);
			}else{
				index=i*defaultPageSize;
				int curCount=(i+1)*defaultPageSize;
				if(i==totalPageCount-1){
					curCount=list.size();
				}
				newList=list.subList(index, curCount);
			}
			println(newList);
		}
		System.out.println("========================拆分结束:========================");
	}
	
	public static void println(List<String> list){
		for(String i:list){
			System.out.println(i);
		}
	}
	
	/**
	 * <p>获取总页数</p>
	 * @param list 
	 * @param defaultCount 默认每次处理的记录条数
	 * @return 总页数
	 * */
	public static int getTotalPageCount(List<String> list,int defaultCount){
		int totalCount=list.size();
		int mod=-1;
		int totalPageCount=0;
		mod = totalCount % defaultCount;
		if (mod != 0) {
			totalPageCount = (totalCount / defaultCount) + 1;
		} else {
			totalPageCount = (totalCount / defaultCount);
		}
		return totalPageCount;
	}
	
	public static List<String> initList(long count){
		
		List<String> logs=new ArrayList<String>();
		
		for(int i=0;i<count;i++){
			logs.add(i+"个");
		}
		
		return logs;
	}

	 /*public static void main(String[] args) {
	        long millis1 = System.currentTimeMillis();
	        String fileName = "F:/test/test.txt"; // 文件名
	        File file=new File(fileName);
	        try {
	            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
	            for(int i=0;i<=100000;i++){
	                String temp = "单线程: " + i+ "----------------------------------我是一条华丽的小尾巴";
	                writer.write(temp);
	                writer.newLine();
	                writer.flush();
	            }
	            writer.close();
	        } catch (IOException e) {      
	            e.printStackTrace();
	        } 
	        long millis2 = System.currentTimeMillis();
	        System.out.println(millis2 - millis1);  //大约162-168ms
	    }*/
}
