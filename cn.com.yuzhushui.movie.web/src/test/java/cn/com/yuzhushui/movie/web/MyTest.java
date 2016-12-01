package cn.com.yuzhushui.movie.web;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;

import cn.com.yuzhushui.movie.common.bean.SessionInfo;
import cn.com.yuzhushui.movie.sys.biz.entity.SysAccount;
import cn.com.yuzhushui.movie.sys.biz.entity.SysUser;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月29日上午10:05:57
 **/
public class MyTest {
	
	public static void main(String[] args){
		
		SysUser su=new SysUser();
		
//		"{\"sysUser\":{\"accountId\":1,\"age\":89,\"creater\":\"61\",\"createrId\":2222,\"ctime\":1479651070000,\"deleted\":0,\"editor\":\"张三\",\
//		"editorId\":22,\"email\":\"2806721661\",\"etime\":1479651070000,\"id\":1,\"mobilephone\":\"18665300661\",\"name\":\"61\",\"sex\":1,\"userId\":1}}"
		
		su.setAccountId(1);
		su.setAge(89);
		su.setCtime(new Date());
		su.setCreater("61");
		su.setCreaterId(222);
		su.setDeleted(0);
		su.setEditor("张三");
		su.setEditorId(22);
		su.setEmail("2806721661");
		su.setEtime(new Date());
		su.setMobilephone("18665300661");
		su.setName("51");
		su.setSex(1);
		su.setUserId(1);
//		"{\"sysAccount\":{\"account\":\"admin\",\"accountId\":1,\"creater\":\"admin\",\"createrId\":1,\"ctime\":1479997174000,\"deleted\":0,\"editor\":\"admin\",\"editorId\":1,
//		\"etime\":1479997181000,\"id\":1,\"password\":\"96E79218965Eb72C92A549dd5A330112\",\"status\":2}}"
		
		SessionInfo sif=new SessionInfo();
		sif.setSysUser(su);
		SysAccount sa=new SysAccount();
		sa.setAccount("admin");
		sa.setAccount("1");
		sa.setCreater("admin");
		sa.setCreaterId(1);
		sa.setCtime(new Date());
		sa.setDeleted(0);
		sa.setEditor("admin");
		sa.setEditorId(1);
		sa.setEtime(new Date());
		sa.setPassword("96E79218965Eb72C92A549dd5A330112");
		sa.setStatus(2);
		sif.setSysAccount(sa);
		String jsonInfo=JSONObject.toJSONString(sif);
		
		
		
		System.out.println("jsonInfo:"+jsonInfo);
		
		SessionInfo tmp=JSONObject.parseObject(jsonInfo, SessionInfo.class);
		
		System.out.println("tm:"+tmp);
		
	}

	 /*public static void main(String[] args) {
	        long millis1 = System.currentTimeMillis();
	        String fileName = "F:/test/test.txt"; // 文件名
	        File file=new File(fileName);
	        try {
	            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
	            for(int i=0;i<=100000;i++){
	                String temp = "单线程: " + i+ "----------------------------------我是一条华丽的小尾巴";
	                writer.write(temp);
	                writer.newLine();
	                writer.flush();
	            }
	            writer.close();
	        } catch (IOException e) {      
	            e.printStackTrace();
	        } 
	        long millis2 = System.currentTimeMillis();
	        System.out.println(millis2 - millis1);  //大约162-168ms
	    }*/
}
