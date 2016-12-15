/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月15日下午11:34:52
 **/
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future; 

/** 
* Java线程：有返回值的线程 
* 
* @author Administrator 2009-11-5 0:41:50 
*/ 
public class MyThreads4 { 
        public static void main(String[] args) throws ExecutionException, InterruptedException { 
                //创建一个线程池 
                ExecutorService pool = Executors.newFixedThreadPool(2); 
                //创建两个有返回值的任务 
                Callable<Object> c1 = new MyCallables4("A"); 
                Callable<Object> c2 = new MyCallables4("B"); 
                //执行任务并获取Future对象 
                Future<Object> f1 = pool.submit(c1); 
                Future<Object> f2 = pool.submit(c2); 
                //从Future对象上获取任务的返回值，并输出到控制台 
                System.out.println(">>>"+f1.get().toString()); 
                System.out.println(">>>"+f2.get().toString()); 
                //关闭线程池 
                pool.shutdown(); 
        } 
} 

class MyCallables4 implements Callable<Object>{ 
	
        private String oid; 
        MyCallables4(String oid) { 
            this.oid = oid; 
        } 
        @Override 
        public Object call() throws Exception { 
            return oid+"任务返回的内容"; 
        } 
}