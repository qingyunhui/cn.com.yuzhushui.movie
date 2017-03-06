package cn.com.yuzhushui.movie.common;

import com.alibaba.fastjson.JSONObject;

import qing.yun.hui.common.utils.EnumUtil;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2017年3月2日下午3:33:55
 **/
public class Test {
	
	public static void main(String[] args){
		String clz="cn.com.yuzhushui.movie.enums.SysBillsEnum$Keyword";
		Object[] objs=EnumUtil.getEnumValues(clz);
		System.out.println("json:"+EnumUtil.getEnumToJSON(clz));
		System.out.println("jsonArray:"+JSONObject.toJSON(objs));
		for(Object obj:objs){
			System.out.println(obj.toString());
		}
	}

}
