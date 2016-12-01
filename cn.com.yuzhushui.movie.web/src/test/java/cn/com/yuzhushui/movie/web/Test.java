package cn.com.yuzhushui.movie.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import qing.yun.hui.common.utils.StringUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import lombok.Getter;
import lombok.Setter;

/***
 ** @category 请用一句话来描述其用途...
 ** @author qing.yunhui
 ** @email: 280672161@qq.com
 ** @createTime: 2016年11月25日上午11:42:30
 **/
public class Test {
	
	public static void main(String[] args){
    	
		List<User> users=getUserList(3);
		show(users);
	
	}
	
	public static void show(List<User> users){
		int count=0;
		for(int i=0;i<users.size();i++){
    		User os= users.get(i);
    		count+=1;
    		if(i==users.size()-1 && count==users.size()){
    			System.out.println("====最后一条====\n"+JSONObject.toJSONString(os));
    			return;
    		}
    		System.out.println(JSONObject.toJSONString(os));
    		
    	}
	}
	
	public static int getStatus(List<User> users){
		
		//充值成功标识:-1:数据库没有记录，'0：充值初始化，1：充值成功，2：充值失败'，3：处理中(充值中)
    	int noExits=0;//不存在的条数(没有充值过)
    	int success=0;//处理中的条数
    	
    	for(int i=0;i<users.size();i++){
    		User os= users.get(i);
    		if(StringUtil.isEmpty(os.getOrgStreamId())){
    			noExits+=1;
    			if(i==users.size()-1 && noExits==users.size()) return -1;	//@1.没有充值记录，那么走，归集户扣款还款操作。  
    			continue;
    		}
    		if(os.getStatus().equals(OrgStreamEnum.Status.PROCESSED.getValue())){
    			//只要有一条处理中的(对于这一条汇总来说，也是处理中..)，那么就由官网进行充值。
    			return OrgStreamEnum.Status.PROCESSED.getValue();
    		}
    		if(os.getStatus().equals(OrgStreamEnum.Status.SUCCESS.getValue())){
    			success+=1;
    			if(i==users.size()-1 && success==users.size()){
    				return OrgStreamEnum.Status.SUCCESS.getValue();	//@3.所有记录都已充值成功。 
    			}
    			continue;
    		}
    	}
      return OrgStreamEnum.Status.PROCESSED.getValue();//这种情况，是n-1条成功了，其中一条没有成功或没有充值的情况下(同样也设置为处理中)..
		
	}
	
	public static List<User> getUserList(int count){
		
		List<User> users=new ArrayList<User>();
		//充值成功标识:-1:数据库没有记录，'0：充值初始化，1：充值成功，2：充值失败'，3：处理中(充值中)
		for(int i=0;i<count;i++){
			int random=new Random().nextInt(4);
			User u=new User((random==3 || random==2)?0:random,"张三"+random,random%2==0?null:1000+random);
			users.add(u);
		}
		System.out.println("================原始数据  begin==================");
		System.out.println(JSONObject.toJSONString(users));
		System.out.println("================原始数据  end==================");
		return users;
	}

}

@Getter
@Setter
class User {
	
	private Integer status;
	
	private String name;
	
	private Integer orgStreamId;
	
	public User(){}
	
	public User(Integer status,String name,Integer orgStreamId){
		this.status=status;
		this.name=name;
		this.orgStreamId=orgStreamId;
	}
}