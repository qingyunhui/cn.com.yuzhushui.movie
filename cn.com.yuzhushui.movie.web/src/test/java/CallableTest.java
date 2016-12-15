import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月15日下午11:40:02
 **/
public class CallableTest {

	public static void main(String[] args){
		
		ExecutorService executorService=Executors.newFixedThreadPool(2);//创建二个线程池.
		List<Future<Boolean>> futures = new ArrayList<Future<Boolean>>();//创建有多个返回值的任务.
		int taskSize=5;//线程数量.
		for (int i = 0; i < taskSize; i++) {
			Callable<Boolean> callable = new MyCallableTest(i,taskSize);
			// 执行任务并获取Future对象
			Future<Boolean> future = executorService.submit(callable);
			futures.add(future);
		}
		// 关闭线程池
		executorService.shutdown();
		// 获取所有并发任务的运行结果
		/*for (Future<Boolean> tmp : futures) {
			// 从Future对象上获取任务的返回值，并输出到控制台
			try {
				System.out.println(">>>" + tmp.get());
				System.out.println("执行完成否:"+tmp.isDone());	//TODO isDone() 判断线程是否执行完成..
			} catch (Exception e) {
				System.out.println("=====>"+e);
			}
		}*/
	}
	
}

class MyCallableTest implements Callable<Boolean>{

	int curThread;
	int totalThread;
	
	public MyCallableTest(){}
	
	public MyCallableTest(int curThread,int totalThread){
		this.curThread=curThread;
		this.totalThread=totalThread;
	}
	
	@Override
	public Boolean call() throws Exception {
		boolean returnResult= new CallWorkTest().println(curThread, totalThread);
		return returnResult;
	}
}

class CallWorkTest{
	
	public Boolean println(int curThread,int totalThread){
		for(int i=curThread;i<1000;i+=totalThread){
			String threadName="线程:"+curThread+"启动...";
			System.out.println(threadName+"===>"+i);
		}
		return Boolean.TRUE;
	}
	
}