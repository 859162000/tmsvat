package com.deloitte.tms.pl.security.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;
import com.deloitte.tms.pl.core.model.impl.BaseRelation;
import com.deloitte.tms.pl.security.utils.LittleUtils;

/**
 * 用户与组织关系
 * 
 * @author bo.wang
 * @create 2016年3月15日 下午2:32:04 
 * @version 2.0.0
 */
@Entity
@Table(name=UserDept.TABLE)
public class UserDept extends BaseRelation{
	
	public static final String TABLE = TablePreDef.BASEPRE+"USER_ORG";
	public static final String SEQ = TABLE;	
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name="USER_ORG_ID",length=60)
	String id;
	
	@Column(name="USERNAME",length=60,nullable=false)
	String username;
	
	@Column(name="ORG_ID",length=60,nullable=false)
	String deptId;
	
	@Column(name="SORT_ORDER")
	Integer sortOrder;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	public UserDept(){
		super();
	}
	
	public UserDept(String username, String orgId){
		this.username=username;
		this.deptId=orgId;
		this.setFlag(LittleUtils.one);
		
	}
}
