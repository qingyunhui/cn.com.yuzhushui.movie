package cn.com.yuzhushui.movie.web;

import java.util.concurrent.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * 有返回值的线程
 */
@SuppressWarnings("rawtypes")
public class BeginTest {
    public static void main(String[] args) {
        
    	ExecutorService exec = Executors.newCachedThreadPool();//工头
    	ArrayList<Future<String>> results = new ArrayList<Future<String>>();//
    	for(int i = 0 ; i < 10 ;i++){
    	    results.add(exec.submit(new Task()));//submit返回一个Future，代表了即将要返回的结果
    	}
         
    	
        System.out.println("所有任务执行完毕");
    }
}
class Task implements Callable<String>{
    @Override
    public String call() throws Exception {
        return "sss";
    }
}