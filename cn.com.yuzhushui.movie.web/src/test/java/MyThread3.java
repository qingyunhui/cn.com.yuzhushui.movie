/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月15日上午10:25:31
 **/
public class MyThread3 {
	
	public static void main(String[] args){
		int totalThreadCount=5;
		for(int i=0;i<totalThreadCount;i++){
			Thread3 threadClz=new Thread3(i,totalThreadCount);
			Thread thread=new Thread(threadClz);
			thread.setName("线程."+i);
			thread.start();
		}
	}
}

class Thread3 implements Runnable{
	int curThread;
	int totalThreadCount;
	public Thread3(){};
	public Thread3(int curThread,int totalThreadCount){
		this.curThread=curThread;
		this.totalThreadCount=totalThreadCount;
	};
	@Override
	public void run() {
		new HandlWork3().println(curThread, totalThreadCount);
	}
}

class HandlWork3{
	public void println(int curThread,int totalThreadCount){
		for(int k=curThread;k<81;k+=totalThreadCount){
			String threadName=Thread.currentThread().getName();
			System.out.println(threadName+"，我是"+k+"条华丽的尾巴.哈哈...");
		}
	}
}