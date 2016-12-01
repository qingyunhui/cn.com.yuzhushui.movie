package cn.com.yuzhushui.movie.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月29日上午10:05:57
 **/
public class MyTest {

	 public static void main(String[] args) {
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
	    }
}
