package com.deloitte.tms.vat.base.enums;

import java.io.Serializable;

/**
 * 
 *
 * 申请单状态枚举
 * @author bo.wang
 * @create Mar 18, 2016 2:04:38 PM 
 * @version 1.0.0
 */
public enum AppFormStatuEnums implements Serializable{
	DRAFT("10","appformstatus.draft"), //草稿
	DELETED("20","appformstatus.deleted"), //删除
	SUBMITTED("30","appformstatus.submitted"), //提交
	REVOKED("40","appformstatus.revoked"),//撤销
	APPROVED("50","appformstatus.approved"),//已审核
	PREP_FORM_GENERATING("60","appformstatus.prep_form_generating"),//准备单生成中
	PREP_FORM_DONE("70","appformstatus.prep_form_done"),//准备单已生成
	PREP_FORM_ERRO("80","appformstatus.prep_form_erro");//准备单生成错误
	
	private String value;
	private String text;

	private AppFormStatuEnums(String value,String text) {
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

