package com.deloitte.tms.vat.base.enums;

import java.io.Serializable;

/**
 * 
 *开票交易池数据状态
 *<100能添加到申请单
 *100-200为申请单预留
 *300-400为准备单预留
 *400-500为发票打印预留
 * @author admin
 * @create Mar 18, 2016 2:37:07 PM 
 * @version 1.0.0
 */
public enum CrvatTaxPoolStatuEnums implements Serializable{
	
	OPEN("10","crvatTaxPoolStatus.open"), //空闲
	
	APPFORM_USED("200","crvatTaxPoolStatus.appform_used"), //申请单占用
	APPFORM_FREE("10","crvatTaxPoolStatus.appform_revoked"), //申请单释放-空闲
	APPFORM_REVOKED("20","crvatTaxPoolStatus.appform_revoked"), //申请单释放-释放
	APPFORM_SUBMITTED("210","crvatTaxPoolStatus.appform_submitted"),//申请单提交
	
	PREP_FORM_USED("300","crvatTaxPoolStatus.prep_form_used"),//准备单占用
	PREP_FORM_APPROVED("310","crvatTaxPoolStatus.prep_form_approved"),//已受理
	PREP_FORM_REVOKED("30","crvatTaxPoolStatus.prep_form_revoked"),//准备单释放_空闲
	
	INVOICE_TOBE_PRINTED("400","crvatTaxPoolStatus.invoice_tobe_printed"),//发票待打印
	INVOICE_PRINTING("410","crvatTaxPoolStatus.invoice_printing"),//发票打印中
	INVOICE_PRINTED("420","crvatTaxPoolStatus.invoice_printed");//已打发票
	
	private String value;
	private String text;

	private CrvatTaxPoolStatuEnums(String value,String text) {
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

