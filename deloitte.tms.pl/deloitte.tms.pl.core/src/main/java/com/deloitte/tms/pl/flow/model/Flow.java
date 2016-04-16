package com.deloitte.tms.pl.flow.model;

import java.io.Serializable;
import java.math.BigDecimal;

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
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

/**
 * 号码流水号表
 * @author bo.wang
 * 
 */
@Entity
@Table(name=Flow.TABLE)
@ModelProperty(comment="流水号")
public class Flow extends BaseEntity implements Serializable {
	
	public static final String TABLE=TablePreDef.BASEPRE+"Flow";
	public static final String SEQ=TABLE;

	private static final long serialVersionUID = 3496636070186249616L;

	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy=GenerationType.TABLE,generator=SEQ+"_GENERATOR")
	@Column(name="Flow_ID",length=36)
	String id;
	
	@ModelProperty(comment = "流水号类型")
	@Column(name = "flow_Type", length = 150)
	private String flowType;

	@ModelProperty(comment = "前缀")
	@Column(name = "preName", length = 150)
	private String preName;

	@ModelProperty(comment = "当前流水号")
	@Column(name = "flow_No", length = 150)
	private Long flowNo;

	@ModelProperty(comment = "所在组织")
	@Column(name = "org_Id", length = 36)
	private String orgId; // 组织标识
	
	@ModelProperty(comment = "最大长度")
	@Column(name = "maxLength")
	Integer maxLength;
	
	@ModelProperty(comment = "序列最大值")
	@Column(name = "maxValue")
	BigDecimal maxValue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFlowType() {
		return flowType;
	}

	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}

	public String getPreName() {
		return preName;
	}

	public void setPreName(String preName) {
		this.preName = preName;
	}

	public Long getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(Long flowNo) {
		this.flowNo = flowNo;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	public BigDecimal getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(BigDecimal maxValue) {
		this.maxValue = maxValue;
	}

	
}
