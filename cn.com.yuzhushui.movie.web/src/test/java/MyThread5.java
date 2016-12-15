/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月15日上午10:42:30
 **/
public class MyThread5 {
	
	public static void main(String[] args){
		int totalThread=5;
		new MyThread5().handlWord(totalThread);
	}
	
	public void handlWord(final int totalThread){
		for(int i=0;i<totalThread;i++){
			final int count=i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					print(count, totalThread);
				}
			}).start();
		}
	}
	
	public void print(int curThread,int totalThread){
		for(int i=curThread;i<100;i+=totalThread){
			String threadName=Thread.currentThread().getName();
			System.out.println(threadName+".你是第"+i+"丑小鸭..");
		}
	}
}
