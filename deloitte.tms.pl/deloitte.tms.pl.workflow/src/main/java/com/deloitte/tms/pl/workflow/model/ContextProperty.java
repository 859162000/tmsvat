package com.deloitte.tms.pl.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.deloitte.tms.pl.core.commons.constant.TablePreDef;

/**
 * @author Jacky.gao
 * @since 2013年7月30日
 */
@Entity
@Table(name=TablePreDef.WORKFLOW+"CONTEXT_PROPERTY")
public class ContextProperty {
	@Id
	@Column(name="KEY_",length=120)
	private String key;
	@Column(name="VALUE_",length=35)
	private String value;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
