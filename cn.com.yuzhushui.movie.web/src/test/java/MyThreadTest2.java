
/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月15日上午10:00:43
 **/
public class MyThreadTest2 {

	public static void main(String[] args){
		int threadCount=5;
		for(int i=0;i<threadCount;i++){
			YouThread thread=new YouThread(i,threadCount);
			Thread td=new Thread(thread);
			td.setName("线程："+i);
			td.start();
		}
	}
}


class YouThread implements Runnable{

	int current;	//当前线程（第几个线程）
	int threadCount;//总线程数量
	
	public YouThread(){}
	
	public YouThread(int current,int threadCount){
		this.current=current;
		this.threadCount=threadCount;
	}
	
	@Override
	public void run() {
		new YouPrint().println(current, threadCount);
	}
}

class YouPrint{
	
	//业务处理..
	public void println(int current,int threadCount){
		for(int i=current;i<100;i=i+threadCount){
			System.out.println(Thread.currentThread().getName()+"，我是"+i+"条华丽的尾巴.");
		}
	}
}