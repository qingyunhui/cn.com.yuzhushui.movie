package cn.com.guangduo.iface.form;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import cn.com.guangduo.iface.common.base.BaseForm;

/**
 * @author qing.yunhui 
 * @Since 2010-2016
 * @create 2016-02-03 16:25:13
 * @history
 */public class EmpForm extends BaseForm<Integer> {

	//columns START
	
	/**
	 * @Fields emp_id:用户id
	 */
	private Integer empId;
	
	/**
	 * @Fields company_id:单位id
	 */
	@Range(message = "数值范围不正确")
	private Integer companyId;
	
	/**
	 * @Fields emp_name:用户名称
	 */
	@NotEmpty(message = "请填写用户名称")
	@Length(max = 128, message = "用户名称的长度不能超过{1}")
	private String empName;
	
	/**
	 * @Fields emp_number:用户编号
	 */
	private String empNumber;
	
	/**
	 * @Fields emp_card_number:身份证号码
	 */
	@NotEmpty(message = "请填写身份证号码")
	@Length(max = 32, message = "身份证号码的长度不能超过{1}")
	private String empCardNumber;
	
	/**
	 * @Fields emp_sex:性别
	 */
	@NotNull(message = "请填写性别")
	@Range(message = "数值范围不正确")
	private Integer empSex;
	
	/**
	 * @Fields emp_phone:手机号
	 */
	@NotEmpty(message = "请填写手机号")
	@Length(max = 16, message = "手机号的长度不能超过{1}")
	private String empPhone;
	
	/**
	 * @Fields emp_type:人员类别
	 */
	@NotNull(message = "请填写人员类别")
	@Range(message = "数值范围不正确")
	private Integer empType;
	
	/**
	 * @Fields emp_degree:人员学历
	 */
	@Range(message = "数值范围不正确")
	private Integer empDegree;
	
	/**
	 * @Fields is_checking:是否考勤
	 */
	@Range(message = "数值范围不正确")
	private Integer isChecking;
	
	/**
	 * @Fields checking_start_time:考勤开始时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date checkingStartTime;
	
	/**
	 * @Fields phone_info:图像信息
	 */
	private byte[] phoneInfo;
	
	/**
	 * @Fields fingerprint_info:指纹信息
	 */
	@Length(max = 512, message = "指纹信息的长度不能超过{1}")
	private String fingerprintInfo;
	
	/**
	 * @Fields emp_status:状态（0正常，1离职）
	 */
	@Range(message = "数值范围不正确")
	private Integer empStatus;
	
	/**
	 * @Fields dimission_time:离职时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dimissionTime;
	
	/**
	 * @Fields leave_reason:离职原因
	 */
	@Length(max = 1024, message = "离职原因的长度不能超过{1}")
	private String leaveReason;
	
	/**
	 * @Fields ctime:创建时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date ctime;
	
	/**
	 * @Fields creater:创建人
	 */
	private String creater;
	
	/**
	 * @Fields creater_id:创建人id
	 */
	private Integer createrId;
	
	/**
	 * @Fields etime:修改时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date etime;
	
	/**
	 * @Fields editor:修改人
	 */
	@Length(max = 256, message = "修改人的长度不能超过{1}")
	private String editor;
	
	/**
	 * @Fields editor_id:修改人id
	 */
	@Range(message = "数值范围不正确")
	private Integer editorId;
	//columns END
	
	private Integer checkingDay;//月考勤天数
	private String serialNumber;//设备序列号
	private String equipmentName;//设备名称
	private String batchCode;//工程编号
	private String batchName;//工程名称
	private String tradeType;//行业分类
	private String buildCompanyName;//施工单位
	
	private Integer pin;//设备内序列号
	
	private Integer batchEquipmentRelId;//工程与设备关联id
	private Integer empEquipmentRelId;//人员设备关联id
	
	private String companyName;
	
	@Override
	public void setUuid(Integer uuid) {
		this.empId = uuid;
	}

	public EmpForm(){
	}

	public EmpForm(Integer empId){
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

}