package cn.com.yuzhushui.movie.web;

import java.util.ArrayList;
import java.util.List;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月29日上午10:05:57
 **/
public class MyTest4 {
	
	public static void main(String[] args) throws Exception{

		int count=50;
		List<String> list=initList(count);
//		println(list);
		
		for(int i=0;i<list.size();i++){
			if(i%2==0){
				System.out.println("异常i="+i);
				throw new Exception();
			}
			System.out.println("正常i="+i);
		}
		
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
