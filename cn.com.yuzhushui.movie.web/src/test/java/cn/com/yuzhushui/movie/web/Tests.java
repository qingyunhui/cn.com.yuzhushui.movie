package cn.com.yuzhushui.movie.web;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年12月19日下午10:16:11
 **/
public class Tests {

	public static void main(String[] args) {
	
		final int totalThread=4;
		for(int i=0;i<totalThread;i++){
			final int thread=i;
			new Thread(new Runnable() {
				public void run() {
					String threadName="线程【"+Thread.currentThread().getName()+"】";
					for(int count=thread;count<100;count+=totalThread){
						System.out.println("count:"+count);
					}
					System.out.println(threadName+"::>_<::~>_<~+");
				}
			}).start();
		}
	}
}