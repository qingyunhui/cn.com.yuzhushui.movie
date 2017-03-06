package cn.com.yuzhushui.movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSONObject;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月6日下午4:03:37
 **/
public class Test {

	public static void main(String[] args){
		int number=10000;
		Random random=new Random();
		int result=random.nextInt(number);
		int length=String.valueOf(number).length();
		int resultlength=String.valueOf(result).length();
		String[] hexs={"A", "b", "C", "d", "E", "f","g","h","i","j","k","m","x","y","z","v","u","w" ,"p","q","n"};
		if(resultlength<length){
			int count=length-resultlength;
			String sb=String.valueOf(result);
			String[] chars=new String[length];
			for(int i=0;i<sb.length();i++){
				chars[i]=sb.charAt(i)+"";
			}
			for(int i=0;i<count;i++){
				String str=hexs[random.nextInt(hexs.length-1)];
				chars[resultlength+i]=str;
			}
			System.out.println("结果："+JSONObject.toJSONString(chars));
			StringBuffer sbs=new StringBuffer();
			List<String> list=new ArrayList<String>(Arrays.asList(chars));
			for(int i=0;i<chars.length;i++){
				getValue(random, list, sbs);
			}
			System.out.println("随机后的结果："+sbs.toString());
		}
	}
	
	public static void getValue(Random random,List<String> list,StringBuffer sbs){
		int number= list.size();
		int c=random.nextInt(number);
		String value=list.get(c);
		sbs.append(value);
		list.remove(c);
	}
}
