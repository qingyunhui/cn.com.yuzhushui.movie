package cn.com.yunzhushui.movie.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月8日下午11:12:55
 **/

public class BeginTest {

	private ApplicationContext context;
	
	@Before
	public void before(){           
		 System.out.println("============befor==================");
		 context = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml"});
	}
	
	@Test
	public void test(){
		System.out.println("====================success====================");
	}
}
