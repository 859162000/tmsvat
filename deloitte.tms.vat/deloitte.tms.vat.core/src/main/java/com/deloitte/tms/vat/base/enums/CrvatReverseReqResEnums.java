package com.deloitte.tms.vat.base.enums;

import java.io.Serializable;

/**
 * 
 * 红字申请结果枚举
 * @author Li Fangran
 * @create April 11, 2016 2:04:38 PM 
 * @version 1.0.0
 */
public enum CrvatReverseReqResEnums implements Serializable{
	succeeded("保存成功！","crvatreversereqres.succeeded"), 
	applied("此发票已经被红申，请刷新界面重试！","crvatreversereqres.applied"),
	errerformnumber("错误的红字信息表编号,请重新申请！","crvatreversereqres.errerformnumber");
	
	private String value;
	private String text;

	private CrvatReverseReqResEnums(String value,String text) {
		this.value = value;
		this.text = text;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	


}