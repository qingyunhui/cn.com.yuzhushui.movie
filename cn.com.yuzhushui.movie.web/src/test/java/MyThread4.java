/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月15日上午10:34:21
 **/
public class MyThread4 {

	public static void main(String[] args){
		int totalThread=5;
		for(int i=0;i<totalThread;i++){
			Thread4 threadClz=new Thread4(i,totalThread);
			threadClz.setName("线程."+i);
			threadClz.start();
		}
	}
}

class Thread4 extends Thread{
	int curThread;
	int totalThread;
	public Thread4(){}
	public Thread4(int curThread,int totalThread){
		this.curThread=curThread;
		this.totalThread=totalThread;
	}
	@Override
	public void run(){
		new HandlWord4().println(curThread, totalThread);
	}
}

class HandlWord4{
	public void println(int curThread,int totalThread){
		for(int i=curThread;i<100;i+=totalThread){
			String threadName=Thread.currentThread().getName();
			System.out.println(threadName+"，"+"你是一条华丽的尾巴.。"+i);
		}
	}
}