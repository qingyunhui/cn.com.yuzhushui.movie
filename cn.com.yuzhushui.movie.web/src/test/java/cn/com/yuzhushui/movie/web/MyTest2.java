package cn.com.yuzhushui.movie.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月29日上午10:09:40
 **/
public class MyTest2 {
	public static void main(String[] args) {
        long millis1 = System.currentTimeMillis();
        int threadNum = 5; // 测试用的线程数目
        String fileName = "F:/test/test2.txt"; // 文件名
        for (int i = 0; i < threadNum; i++) {
            MyThread myThread = new MyThread(i, threadNum, fileName);
            Thread tempThread = new Thread(myThread);
            tempThread.setName("线程" + i);
            tempThread.start();
        }
        long millis2 = System.currentTimeMillis();
        System.out.println(millis2 - millis1);  //大约1-2ms
    }
}

/**
 * 自定义线程
 * */
class MyThread implements Runnable {
    private int i; // 第几个线程
    private int threadNum; // 总共创建了几个线程
    private String fileName;  //文件名
 
    public MyThread(int i, int threadNum, String fileName) {
        this.i = i;
        this.threadNum = threadNum;
        this.fileName = fileName;
    }
 
    public void run() {
        new MyPrint().print(i, threadNum, fileName);
    }
}
/**业务处理*/
class MyPrint {
    private BufferedWriter writer;
    public void print(int x, int threadNum, String fileName) {
    	File file=new File(fileName);
        try {
            writer = new BufferedWriter(new FileWriter(file, true));  //如果文件已存在则在后面追加
            for (int i = x; i <= 100000; i = i + threadNum) {
                String temp = Thread.currentThread().getName() + ": " + i+ "----------------------------------我是一条华丽的小尾巴";
                writer.write(temp);
                writer.newLine();
                writer.flush();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
