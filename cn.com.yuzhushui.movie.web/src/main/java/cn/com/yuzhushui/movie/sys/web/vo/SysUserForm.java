package cn.com.yuzhushui.movie.sys.web.vo;

import java.util.Date;

import cn.com.yuzhushui.movie.common.base.BaseForm;
import lombok.Getter;
import lombok.Setter;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-18 11:19:22
 * @history
 */
@Getter
@Setter
public class SysUserForm extends BaseForm<Integer> {

	//columns START
	
	/**
	 * @Fields id:id
	 */
	private Integer id;
	
	/**
	 * @Fields account_id:account_id
	 */
	private Integer accountId;
	
	/**
	 * @Fields name:姓名
	 */
	private String name;
	
	/**
	 * @Fields sex:性别(0.男、1.女、2.其它)
	 */
	private Integer sex;
	
	/**
	 * @Fields age:年龄
	 */
	private Integer age;
	
	/**
	 * @Fields telephone:联系电话
	 */
	private String telephone;
	
	/**
	 * @Fields mobilephone:移动电话
	 */
	private String mobilephone;
	
	/**
	 * @Fields job:职务(1.IT、2.农业、3.其它)
	 */
	private Integer job;
	
	/**
	 * @Fields qq:QQ
	 */
	private Integer qq;
	
	/**
	 * @Fields wechat:微信
	 */
	private String wechat;
	
	/**
	 * @Fields microblog:微博
	 */
	private String microblog;
	
	/**
	 * @Fields email:电子邮箱
	 */
	private String email;
	
	/**
	 * @Fields office_addr:办公地址
	 */
	private String officeAddr;
	
	/**
	 * @Fields state 状态(0:未认证、1.认证中、2.认证失败、3.已认证)
	 */
	private Integer state;
	
	/**
	 * @Fields idcard 身份证号
	 */
	private String idcard;
	
	/**
	 * @Fields area 地区(用户居住地区、一般跟身份证上一致)
	 */
	private String area;
	
	/**
	 * @Fields ctime:创建时间
	 */
	private Date ctime;
	
	/**
	 * @Fields creater:创建人
	 */
	private String creater;
	
	/**
	 * @Fields creater_id:创建人ID
	 */
	private Integer createrId;
	
	/**
	 * @Fields etime:修改时间
	 */
	private Date etime;
	
	/**
	 * @Fields editor:修改人
	 */
	private String editor;
	
	/**
	 * @Fields editor_id:修改人_id
	 */
	private Integer editorId;
	
	/**
	 * @Fields comments:备注
	 */
	private String comments;
	//columns END

}