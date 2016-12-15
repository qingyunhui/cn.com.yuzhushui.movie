/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月15日下午10:58:37
 **/
public class MyTest {

	public static  void main(String[] args){
		int totalThread=5;
		for(int i=0;i<totalThread;i++){
			MyThread1 mythread=new MyThread1(i,totalThread);
			Thread thread=new Thread(mythread);
			thread.setName("线程."+i+"正在运行...");
			thread.start();
		}
	}
	
}

class MyThread1 implements Runnable{
	int curThread;
	int totalThread;
	
	public MyThread1(){}
	
	public MyThread1(int curThread,int totalThread){
		this.curThread=curThread;
		this.totalThread=totalThread;
	}
	
	@Override
	public void run() {
		new MyWorkHanld1().workRun(curThread, totalThread);
	}
}

class MyWorkHanld1{
	
	public void workRun(int curThread,int totalThread){
		for(int i=curThread;i<1000;i+=totalThread){
			String threadName=Thread.currentThread().getName();
			System.out.println(threadName+"，"+i+".");
		}
	}
	
}