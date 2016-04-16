package com.deloitte.tms.pl.system.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;

@Entity
@Table(name = UserOperation.TABLE)
@ModelProperty(comment = "用户操作记录",des="用户操作记录")
public class UserOperation {
	
	public static final String TABLE = TablePreDef.BASEPRE+"UserOperation";
	public static final String SEQ = TABLE;
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2IdGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy=GenerationType.TABLE,generator=SEQ+"_GENERATOR")
	@Column(name = "id")
	private Long id;
	
	@ModelProperty(comment="类型")
	@Column(name="groupId")
	String groupId;

	@ModelProperty(comment="主键")
	@Column(name="relationId")
	String relationId;
	
	@ModelProperty(comment="操作人")
	@Column(name="operator",length=50)
	String operator;
	
	@ModelProperty(comment="操作")
	@Column(name="operatoration",length=50)
	String operatoration;
	
	@ModelProperty(comment="描述")
	@Column(name="des",length=50)
	String des;
	
	@ModelProperty(comment="操作时间")
	@Column(name="operationDate")
	Date operationDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatoration() {
		return operatoration;
	}

	public void setOperatoration(String operatoration) {
		this.operatoration = operatoration;
	}

	public Date getOperationDate() {
		return operationDate;
	}

	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	

}
