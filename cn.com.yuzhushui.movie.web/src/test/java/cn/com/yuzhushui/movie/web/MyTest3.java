package cn.com.yuzhushui.movie.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

import org.apache.commons.io.output.WriterOutputStream;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月29日下午2:03:16
 **/
public class MyTest3 {

}

class TharedTool extends Thread{
	
	@Override
	public void run(){
		
	}
	
}

class WriteStr{
	
	
	BufferedWriter bw=null;
	
	public void writeBy(String fileName,int thread) throws Exception{
		File file=new File(fileName);
		FileWriter fw=new FileWriter(file);
		bw=new BufferedWriter(fw);
		for(int i=0;i<200000;i++){
			String str="线程:"+thread+"-我是第"+i+"只小密峰。";
			bw.write(str);
		}
	}
	
}
