package com.deloitte.tms.vat.base.enums;

import java.io.Serializable;

public enum CrvaInvoicePreStatusEnums implements Serializable{
	TOBEAPPROVE("10","crvaInvoicePre.tobeapprove"), //待受理
	APPROVED("20","crvaInvoicePre.approved"), //已受理
	INVOICEGENERATED("30","crvaInvoicePre.invoicegenerated"),//发票已生成
	REVOKED("40","crvaInvoicePre.revoked"),//回退
	PREP_FORM_GENERATING("60","crvaInvoicePre.prep_form_generating"),//发票生成中
	PREP_FORM_ERRO("80","crvaInvoicePre.prep_form_erro");//发票生成错误
	

	
	private String value;
	private String text;

	private CrvaInvoicePreStatusEnums(String value,String text) {
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

