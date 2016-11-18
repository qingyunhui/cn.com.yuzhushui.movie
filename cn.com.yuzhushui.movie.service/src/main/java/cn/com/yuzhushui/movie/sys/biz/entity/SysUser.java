package cn.com.yuzhushui.movie.sys.biz.entity;

import java.util.Date;

import cn.com.yuzhushui.movie.common.base.BaseModel;

/**
 * @author qing.yunhui 
 * @Since 2011-2016
 * @create 2016-11-18 11:19:22
 * @history
 */
public class SysUser extends BaseModel<Integer>{
	
	//alias
	public static final String TABLE_ALIAS = "SysUser";
	
	//columns START
	/**
	 * @Fields id:id
	 */
	private Integer id;
	
	/**
	 * @Fields accountId:account_id
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
	 * @Fields officeAddr:办公地址
	 */
	private String officeAddr;
	
	/**
	 * @Fields ctime:创建时间
	 */
	private Date ctime;
	
	/**
	 * @Fields creater:创建人
	 */
	private String creater;
	
	/**
	 * @Fields createrId:创建人ID
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
	 * @Fields editorId:修改人_id
	 */
	private Integer editorId;
	
	/**
	 * @Fields comments:备注
	 */
	private String comments;
	
	/**
	 * @Fields deleted:删除标识
	 */
	private Integer deleted;
	
	//columns END

	public SysUser(){
	}

	public SysUser(Integer id){
		this.id = id;
	}

	
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	public void setAccountId(Integer accountId){
		this.accountId = accountId;
	}
	
	public Integer getAccountId(){
		return accountId;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setSex(Integer sex){
		this.sex = sex;
	}
	
	public Integer getSex(){
		return sex;
	}
	
	public void setAge(Integer age){
		this.age = age;
	}
	
	public Integer getAge(){
		return age;
	}
	
	public void setTelephone(String telephone){
		this.telephone = telephone;
	}
	
	public String getTelephone(){
		return telephone;
	}
	
	public void setMobilephone(String mobilephone){
		this.mobilephone = mobilephone;
	}
	
	public String getMobilephone(){
		return mobilephone;
	}
	
	public void setJob(Integer job){
		this.job = job;
	}
	
	public Integer getJob(){
		return job;
	}
	
	public void setQq(Integer qq){
		this.qq = qq;
	}
	
	public Integer getQq(){
		return qq;
	}
	
	public void setWechat(String wechat){
		this.wechat = wechat;
	}
	
	public String getWechat(){
		return wechat;
	}
	
	public void setMicroblog(String microblog){
		this.microblog = microblog;
	}
	
	public String getMicroblog(){
		return microblog;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setOfficeAddr(String officeAddr){
		this.officeAddr = officeAddr;
	}
	
	public String getOfficeAddr(){
		return officeAddr;
	}
	
	public void setCtime(Date ctime){
		this.ctime = ctime;
	}
	
	public Date getCtime(){
		return ctime;
	}
	
	public void setCreater(String creater){
		this.creater = creater;
	}
	
	public String getCreater(){
		return creater;
	}
	
	public void setCreaterId(Integer createrId){
		this.createrId = createrId;
	}
	
	public Integer getCreaterId(){
		return createrId;
	}
	
	public void setEtime(Date etime){
		this.etime = etime;
	}
	
	public Date getEtime(){
		return etime;
	}
	
	public void setEditor(String editor){
		this.editor = editor;
	}
	
	public String getEditor(){
		return editor;
	}
	
	public void setEditorId(Integer editorId){
		this.editorId = editorId;
	}
	
	public Integer getEditorId(){
		return editorId;
	}
	
	public void setComments(String comments){
		this.comments = comments;
	}
	
	public String getComments(){
		return comments;
	}
	
	public void setDeleted(Integer deleted){
		this.deleted = deleted;
	}
	
	public Integer getDeleted(){
		return deleted;
	}

}