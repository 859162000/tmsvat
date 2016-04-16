package com.deloitte.tms.pl.security.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

/**
 * @since 2016-3-14
 * @author bo.wang
 */
@Entity
@Table(name=ComponentDefinition.TABLE)
public class ComponentDefinition extends BaseEntity{
	
	public static final String TABLE=TablePreDef.BASEPRE+"COMPONENT";
	public static final String SEQ=TABLE;

	@Id
	@Column(name="COMPONENT_ID",length=36)
	String id;
	
	@ModelProperty(comment="页面元素id")
	@Column(name="COMPONENT_code",length=60,nullable=false)
	String componentId;
	
	@ModelProperty(comment="描述")
	@Column(name="DESCRIPTION",length=120)
	String desc;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComponentId() {
		return componentId;
	}

	public void setComponentId(String componentId) {
		this.componentId = componentId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
