package com.deloitte.tms.pl.security.model.impl;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;
import com.deloitte.tms.pl.security.model.SecurityDept;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.utils.LittleUtils;

/**
 * 
 * @author ling2
 *
 */
@Entity
@Table(name=DefaultDept.TABLE)
public class DefaultDept extends BaseEntity implements SecurityDept {
		
	
	/**  
	 * serialVersionUID  
	 */  
	
	private static final long serialVersionUID = 1L;
	public static final String TABLE = TablePreDef.BASEPRE+"ORG";
	public static final String SEQ = TABLE;

	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name="ORG_ID",length=36)
	String id;
	
	@Column(name="ORG_CODE",length=100)
	@ModelProperty(comment="组织代码")
	String orgCode;

	@Column(name="ORG_NAME",length=120)
	@ModelProperty(comment="组织名称")
	String orgName;

	@Column(name="ORG_TYPE",length=120)
	@ModelProperty(comment="组织类型")
	String orgType;

	@Column(name="DESCRIPTION",length=120)
	@ModelProperty(comment="描述")
	String des;

	@Column(name="PARENT_ID",length=36)
	@ModelProperty(comment="父结点ID")
	String parentId;
	
	@Column(name="PARENTNAME",length=36)
	@ModelProperty(comment="父结点名称")
	String parentName;

	@Column(name="IS_VIRTUAL")
	@ModelProperty(comment="是否虚拟组织,实体单位=true,虚拟单位=false")
	Integer virtual;

	@Transient
	SecurityDept parent;
	@Transient
	List<SecurityUser> users;
	@Transient
	List<SecurityDept> children;
	
	public DefaultDept(){
		
	}
	
	public DefaultDept(String deptId){
		this.id=deptId;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public SecurityDept getParent() {
		return parent;
	}

	public void setParent(SecurityDept parent) {
		this.parent = parent;
	}

	public List<SecurityUser> getUsers() {
		return users;
	}

	public void setUsers(List<SecurityUser> users) {
		this.users = users;
	}

	public List<SecurityDept> getChildren() {
		return children;
	}

	public void setChildren(List<SecurityDept> children) {
		this.children = children;
	}
	

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Integer getVirtual() {
		return virtual;
	}

	public void setVirtual(Integer virtual) {
		this.virtual = virtual;
	}
	
	@Override
	public String toString() {
		return "DefaultDept [id=" + id + ", orgCode=" + orgCode + ", orgName="
				+ orgName + ", orgType=" + orgType + ", des=" + des
				+ ", parentId=" + parentId + ", parentName=" + parentName
				+ ", virtual=" + virtual + ", parent=" + parent + ", users="
				+ users + ", children=" + children + "]";
	}
	
	
	public DefaultDept(DefaultDeptInUser defaultDeptInUser){
		
		this.id=defaultDeptInUser.id;
		this.orgCode=defaultDeptInUser.orgCode;
		this.des=defaultDeptInUser.des;
		this.orgName=defaultDeptInUser.orgName;
		this.parentId=defaultDeptInUser.parentId;
		
		this.virtual=defaultDeptInUser.virtual;
		this.orgType=defaultDeptInUser.orgType;
		this.setFlag(LittleUtils.one);
		System.out.println(" DefaultDept() + id:"+id+"parentId:"+parentId+";getFlag"+this.getFlag());
/*		id
		orgCode
		des
		orgName
		parentId
		virtual
		bizOrgCode --------> no use as aop will handle this field
		orgType*/
	}
}
