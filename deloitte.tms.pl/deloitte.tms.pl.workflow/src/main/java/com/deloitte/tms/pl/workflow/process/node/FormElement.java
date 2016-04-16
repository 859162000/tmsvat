package com.deloitte.tms.pl.workflow.process.node;

import java.util.List;

import com.deloitte.tms.pl.workflow.process.security.Authority;

/**
 * @author Jacky.gao
 * @since 2013年9月25日
 */
public class FormElement implements java.io.Serializable{
	private static final long serialVersionUID = 2304388144156396115L;
	private String name;
	private String caption;
	private String dataType;
	private String editorType;
	private Authority authority;
	private boolean required;
	private String defaultValue;
	private List<Mapping> mappings;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getEditorType() {
		return editorType;
	}
	public void setEditorType(String editorType) {
		this.editorType = editorType;
	}
	public Authority getAuthority() {
		return authority;
	}
	public void setAuthority(Authority authority) {
		this.authority = authority;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public List<Mapping> getMappings() {
		return mappings;
	}
	public void setMappings(List<Mapping> mappings) {
		this.mappings = mappings;
	}
}
