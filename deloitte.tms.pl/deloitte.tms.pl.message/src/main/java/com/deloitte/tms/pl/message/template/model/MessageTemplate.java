package com.deloitte.tms.pl.message.template.model;

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
@Table(name = MessageTemplate.TABLE)
@ModelProperty(comment = "信息模板")
public class MessageTemplate {
	public static final String TABLE=TablePreDef.MESSAGE+"MessageTemplate";
	public static final String SEQ=TABLE;
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2IdGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "id",  length = 16)
	Long id;
	
	@ModelProperty(comment="名称")
	@Column(name = "name",length=50)
	String name;
	
	@ModelProperty(comment="分类")
	@Column(name = "groupId",length=16)	
	String groupId;
	
	@ModelProperty(comment="关联Id")
	@Column(name = "relationId",length=16)	
	String relationId;
	
	@ModelProperty(comment="父级编号")
	@Column(name = "parentId")
	Long parentId;
	
	@ModelProperty(comment="是否默认")
	@Column(name = "isdefault")	
	Boolean isdefault;
	
	@ModelProperty(comment="流程状态",des="如果有流程,这里表示流程状态")
	@Column(name = "status",length=16)
	String status;
	
	@ModelProperty(comment="模板文本类型",des="html,txt")
	@Column(name = "processType",length=16)
	String processType;
	
	@ModelProperty(comment="是否启用",des="如果开启禁用功能,这个字段标识是否启用,默认为1")
	@Column(name = "enable",length=16)
	Boolean enable;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Boolean getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(Boolean isdefault) {
		this.isdefault = isdefault;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}	
	
}
