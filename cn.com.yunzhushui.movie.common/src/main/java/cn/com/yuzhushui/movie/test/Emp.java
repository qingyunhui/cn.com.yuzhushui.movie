package cn.com.guangduo.iface.po;

import java.util.Date;

import cn.com.guangduo.iface.common.base.BaseModel;
import cn.com.guangduo.iface.common.base.ICreateInfo;
import cn.com.guangduo.iface.common.base.IUpdateInfo;

/**
 * @author qing.yunhui 
 * @Since 2010-2016
 * @create 2016-02-03 16:25:13
 * @history
 */public class Emp extends BaseModel<Integer> implements ICreateInfo, IUpdateInfo{
	
	//alias
	public static final String TABLE_ALIAS = "Emp";
	
	//columns START
	/**
	 * @Fields empId:用户id
	 */
	private Integer empId;
	
	/**
	 * @Fields companyId:单位id
	 */
	private Integer companyId;
	
	/**
	 * @Fields empName:用户名称
	 */
	private String empName;
	
	/**
	 * @Fields empNumber:用户编号
	 */
	private String empNumber;
	
	/**
	 * @Fields empCardNumber:身份证号码
	 */
	private String empCardNumber;
	
	/**
	 * @Fields empSex:性别
	 */
	private Integer empSex;
	
	/**
	 * @Fields empPhone:手机号
	 */
	private String empPhone;
	
	/**
	 * @Fields empType:人员类别
	 */
	private Integer empType;
	
	/**
	 * @Fields empDegree:人员学历
	 */
	private Integer empDegree;
	
	/**
	 * @Fields isChecking:是否考勤
	 */
	private Integer isChecking;
	
	/**
	 * @Fields checkingStartTime:考勤开始时间
	 */
	private Date checkingStartTime;
	
	/**
	 * @Fields phoneInfo:图像信息
	 */
	private byte[] phoneInfo;
	
	/**
	 * @Fields fingerprintInfo:指纹信息
	 */
	private String fingerprintInfo;
	
	/**
	 * @Fields empStatus:状态（0正常，1离职）
	 */
	private Integer empStatus;
	
	/**
	 * @Fields dimissionTime:离职时间
	 */
	private Date dimissionTime;
	
	/**
	 * @Fields leaveReason:离职原因
	 */
	private String leaveReason;
	
	/**
	 * @Fields ctime:创建时间
	 */
	private Date ctime;
	
	/**
	 * @Fields creater:创建人
	 */
	private String creater;
	
	/**
	 * @Fields createrId:创建人id
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
	 * @Fields editorId:修改人id
	 */
	private Integer editorId;
	
	/**
	 * @Fields deleted:删除标识
	 */
	private Integer deleted;
	
	//columns END
	
	private Integer checkingDay;//月考勤天数
	private String serialNumber;//设备序列号
	private String equipmentName;//设备名称
	private String batchCode;//工程编号
	private String batchName;//工程名称
	private String tradeType;//行业分类
	private String buildCompanyName;//施工单位
	private Integer batchEquipmentRelId;//工程与设备关联id
	
	private Integer empEquipmentRelId;//人员设备关联id
	
	private Integer pin;//设备内序列号;
	private String companyName;

	public Emp(){
	}

	public Emp(Integer empId){
		this.empId = empId;
	}

	
	public void setEmpId(Integer empId){
		this.empId = empId;
	}
	
	public Integer getEmpId(){
		return empId;
	}
	
	public void setCompanyId(Integer companyId){
		this.companyId = companyId;
	}
	
	public Integer getCompanyId(){
		return companyId;
	}
	
	public void setEmpName(String empName){
		this.empName = empName;
	}
	
	public String getEmpName(){
		return empName;
	}
	
	public void setEmpNumber(String empNumber){
		this.empNumber = empNumber;
	}
	
	public String getEmpNumber(){
		return empNumber;
	}
	
	public void setEmpCardNumber(String empCardNumber){
		this.empCardNumber = empCardNumber;
	}
	
	public String getEmpCardNumber(){
		return empCardNumber;
	}
	
	public void setEmpSex(Integer empSex){
		this.empSex = empSex;
	}
	
	public Integer getEmpSex(){
		return empSex;
	}
	
	public void setEmpPhone(String empPhone){
		this.empPhone = empPhone;
	}
	
	public String getEmpPhone(){
		return empPhone;
	}
	
	public void setEmpType(Integer empType){
		this.empType = empType;
	}
	
	public Integer getEmpType(){
		return empType;
	}
	
	public void setEmpDegree(Integer empDegree){
		this.empDegree = empDegree;
	}
	
	public Integer getEmpDegree(){
		return empDegree;
	}
	
	public void setIsChecking(Integer isChecking){
		this.isChecking = isChecking;
	}
	
	public Integer getIsChecking(){
		return isChecking;
	}
	
	public void setCheckingStartTime(Date checkingStartTime){
		this.checkingStartTime = checkingStartTime;
	}
	
	public Date getCheckingStartTime(){
		return checkingStartTime;
	}
	
	public void setPhoneInfo(byte[] phoneInfo){
		this.phoneInfo = phoneInfo;
	}
	
	public byte[] getPhoneInfo(){
		return phoneInfo;
	}
	
	public void setFingerprintInfo(String fingerprintInfo){
		this.fingerprintInfo = fingerprintInfo;
	}
	
	public String getFingerprintInfo(){
		return fingerprintInfo;
	}
	
	public void setEmpStatus(Integer empStatus){
		this.empStatus = empStatus;
	}
	
	public Integer getEmpStatus(){
		return empStatus;
	}
	
	public void setDimissionTime(Date dimissionTime){
		this.dimissionTime = dimissionTime;
	}
	
	public Date getDimissionTime(){
		return dimissionTime;
	}
	
	public void setLeaveReason(String leaveReason){
		this.leaveReason = leaveReason;
	}
	
	public String getLeaveReason(){
		return leaveReason;
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
	
	public void setDeleted(Integer deleted){
		this.deleted = deleted;
	}
	
	public Integer getDeleted(){
		return deleted;
	}

	public Integer getCheckingDay() {
		return checkingDay;
	}

	public void setCheckingDay(Integer checkingDay) {
		this.checkingDay = checkingDay;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getBuildCompanyName() {
		return buildCompanyName;
	}

	public void setBuildCompanyName(String buildCompanyName) {
		this.buildCompanyName = buildCompanyName;
	}

	public Integer getBatchEquipmentRelId() {
		return batchEquipmentRelId;
	}

	public void setBatchEquipmentRelId(Integer batchEquipmentRelId) {
		this.batchEquipmentRelId = batchEquipmentRelId;
	}

	public Integer getPin() {
		return pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getEmpEquipmentRelId() {
		return empEquipmentRelId;
	}

	public void setEmpEquipmentRelId(Integer empEquipmentRelId) {
		this.empEquipmentRelId = empEquipmentRelId;
	}

	@Override
	public Integer getId() {
		return this.empId;
	}

}