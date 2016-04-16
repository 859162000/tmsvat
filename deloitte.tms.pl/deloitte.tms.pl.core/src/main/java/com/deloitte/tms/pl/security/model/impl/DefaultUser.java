package com.deloitte.tms.pl.security.model.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.security.model.SecurityDept;
import com.deloitte.tms.pl.security.model.SecurityGroup;
import com.deloitte.tms.pl.security.model.SecurityPosition;
import com.deloitte.tms.pl.security.model.SecurityRole;

/**
 * 
 * 用户登录信息
 * @author bo.wang
 * @create 2016年3月15日 下午1:41:38 
 * @version 2.0.0
 */
@Entity
@Table(name=DefaultUser.TABLE)
public class DefaultUser extends AbstractUser{
	
	public static final String TABLE =TablePreDef.BASEPRE+"USER";
	public static final String SEQ=TABLE;
	
	@Id
	@Column(name="USERNAME",length=60)
	@ModelProperty(comment="用户名")
	String username;
	
	@Column(name="USER_ID",length=36)
	@ModelProperty(comment="用户编号")
	String userId;	
	
	@ModelProperty(comment="地址")
	@Column(name="ADDRESS",length=120)
	String address;
	
	@ModelProperty(comment="是否超级管理员")
	@Column(name="IS_ADMINISTRATOR",nullable=false)
	boolean administrator=false;
	
	@Column(name="BIRTHDAY")
	@ModelProperty(comment="生日")
	Date birthday;
	
	@Column(name="ENABLED",nullable=false)
	boolean enabled=true;	
	
	@Column(name="MALE",nullable=true)
	Boolean male;
	
	@Column(name="MOBILE",length=20)
	String mobile;
	
	@Column(name="CNAME",length=60,nullable=true)
	String cname;
	
	@Column(name="EMAIL",length=60)
	String email;
	
	@Column(name="ENAME",length=60)
	String ename;
	
	@Column(name="nickname",length=60,nullable=true)
	String nickname;
	
	@Column(name="realName",length=60,nullable=true)
	String realName;
	
	@Column(name="PASSWORD",length=70,nullable=false)
	String password;
	
	@Column(name="SALT",length=10,nullable=false)
	String salt;
	
	@Column(name="remark",length=150,nullable=true)
	String remark;

	@Column(name="USER_CODE",length=100)
	@ModelProperty(comment="员工编号")
	String userCode;
	
	@Column(name="EMP_STATUS",length=30)
	@ModelProperty(comment="员工状态")
	String empStatus;
	
	@Column(name="SOURCE_CODE",length=100)
	@ModelProperty(comment="来源")
	String sourceCode;
	
	@Column(name="SYNC_DTIME")
	@ModelProperty(comment="同步时间")
	Date syncTime;
	
	@Column(name="LOCK_STATUS",length=30)
	@ModelProperty(comment="锁定状态")
	String lockStatus;
	
	@Column(name="LOCK_TIME")
	@ModelProperty(comment="锁定时间")
	Date lockTime;
	
	@Column(name="LAST_LOGIN_TIME")
	@ModelProperty(comment="最后登录时间")
	Date lastLoginTime;
	
	@Transient
	private List<SecurityPosition> positions;
	
	/**
	 * 用于判断IContextHolder initUserDetail方法是否需要在获取用户信息的时候是否执行.解决记住密码的时候用户信息不全的问题
	 */
	@Transient
	private boolean isInit;
	
	@Transient
	private List<SecurityDept> depts;
	
	@Transient
	private List<SecurityGroup> groups;
	
	@Transient
	private List<SecurityRole> roles;	
	@Transient
	private String selectRoles;
	@Transient
	private boolean isnew;
	
	@Transient
	private Map<String, Object> cachedProperties = new LinkedHashMap<String, Object>();
	
	@Column(name="INDENTIFY_TYPE")
	private String indentifyType;
	
	@Column(name="INDENTIFY_CODE")
	private String indentifyCode;
	
	@Column(name="EDUCATION")
	private String education;
	
	@Column(name="ONBOARD_DATE")
	private Date onboardDate;
	
	@Column(name="USER_TYPE")
	private String userType;
	
	@Column(name="USER_AUTH_TYPE")
	private String userAuthType;
	
	@Column(name="USER_SIGNED_FLAG")
	private String userSignedFlag;
	
	@Column(name="SIGN_TERMINAL")
	private String signTerminal;
	
	@Column(name="SIGN_IN_DATE")
	private Date signInDate;
	
	@Column(name="SIGN_IN_TIME")
	private Date signInTime;
	
	@Column(name="SIGN_OUT_DATE")
	private Date signOutDate;
	
	@Column(name="SIGN_OUT_TIME")
	private Date signOutTime;
	
	@Column(name="CREATED_ORG")
	private String createdOrg;
	
	@Column(name="MODIFIED_ORG")
	private String modifiedOrg;
	
	@Column(name="TASK_CHECK_FLAG")
	private String taskCheckFlag;
	
	@Column(name="LOG_CHECK_FLAG")
	private String logCheckFlag;
	
	@Column(name="SESSION_CODE")
	private String sessionCode;
	
	@Column(name="TIMESTAMP")
	private Timestamp timestamp; 
	
	public Map<String, Object> getCachedProperties() {
		return cachedProperties;
	}
	public void setCachedProperties(Map<String, Object> cachedProperties) {
		this.cachedProperties = cachedProperties;
	}
	public DefaultUser(){}
	public DefaultUser(String username){
		this.username=username;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public void setMale(Boolean male) {
		this.male = male;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<SecurityDept> getDepts() {
		return depts;
	}
	public void setDepts(List<SecurityDept> depts) {
		this.depts = depts;
	}
	public List<SecurityPosition> getPositions() {
		return positions;
	}
	public void setPositions(List<SecurityPosition> positions) {
		this.positions = positions;
	}
	public List<SecurityGroup> getGroups() {
		return groups;
	}
	public void setGroups(List<SecurityGroup> groups) {
		this.groups = groups;
	}
	public List<SecurityRole> getRoles() {
		return roles;
	}
	public void setRoles(List<SecurityRole> roles) {
		this.roles = roles;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public boolean isAdministrator() {
		return administrator;
	}
	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}
	public Map<String, Object> getMetadata() {
		return cachedProperties;
	}
	public String getSelectRoles() {
		return selectRoles;
	}
	public void setSelectRoles(String selectRoles) {
		this.selectRoles = selectRoles;
	}
	public Boolean getMale() {
		return male;
	}
	public boolean isInit() {
		return isInit;
	}
	public void setInit(boolean isInit) {
		this.isInit = isInit;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}
	public String getSourceCode() {
		return sourceCode;
	}
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	public Date getSyncTime() {
		return syncTime;
	}
	public void setSyncTime(Date syncTime) {
		this.syncTime = syncTime;
	}
	public String getLockStatus() {
		return lockStatus;
	}
	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}
	public Date getLockTime() {
		return lockTime;
	}
	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	@Override
	public Serializable getId() {
		return username;
	}
	public String toString(){
		return username;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public Date getOnboardDate() {
		return onboardDate;
	}
	public void setOnboardDate(Date onboardDate) {
		this.onboardDate = onboardDate;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserAuthType() {
		return userAuthType;
	}
	public void setUserAuthType(String userAuthType) {
		this.userAuthType = userAuthType;
	}
	public String getUserSignedFlag() {
		return userSignedFlag;
	}
	public void setUserSignedFlag(String userSignedFlag) {
		this.userSignedFlag = userSignedFlag;
	}
	public String getSignTerminal() {
		return signTerminal;
	}
	public void setSignTerminal(String signTerminal) {
		this.signTerminal = signTerminal;
	}
	public Date getSignInDate() {
		return signInDate;
	}
	public void setSignInDate(Date signInDate) {
		this.signInDate = signInDate;
	}
	public Date getSignInTime() {
		return signInTime;
	}
	public void setSignInTime(Date signInTime) {
		this.signInTime = signInTime;
	}
	public Date getSignOutDate() {
		return signOutDate;
	}
	public void setSignOutDate(Date signOutDate) {
		this.signOutDate = signOutDate;
	}
	public Date getSignOutTime() {
		return signOutTime;
	}
	public void setSignOutTime(Date signOutTime) {
		this.signOutTime = signOutTime;
	}
	public String getCreatedOrg() {
		return createdOrg;
	}
	public void setCreatedOrg(String createdOrg) {
		this.createdOrg = createdOrg;
	}
	public String getModifiedOrg() {
		return modifiedOrg;
	}
	public void setModifiedOrg(String modifiedOrg) {
		this.modifiedOrg = modifiedOrg;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public String getIndentifyType() {
		return indentifyType;
	}
	public void setIndentifyType(String indentifyType) {
		this.indentifyType = indentifyType;
	}
	public String getIndentifyCode() {
		return indentifyCode;
	}
	public void setIndentifyCode(String indentifyCode) {
		this.indentifyCode = indentifyCode;
	}
	public String getTaskCheckFlag() {
		return taskCheckFlag;
	}
	public void setTaskCheckFlag(String taskCheckFlag) {
		this.taskCheckFlag = taskCheckFlag;
	}
	public String getLogCheckFlag() {
		return logCheckFlag;
	}
	public void setLogCheckFlag(String logCheckFlag) {
		this.logCheckFlag = logCheckFlag;
	}
	public String getSessionCode() {
		return sessionCode;
	}
	public void setSessionCode(String sessionCode) {
		this.sessionCode = sessionCode;
	}
	public boolean isIsnew() {
		return isnew;
	}
	public void setIsnew(boolean isnew) {
		this.isnew = isnew;
	}
	
	
}
