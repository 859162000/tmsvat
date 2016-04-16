
/**    
 * Copyright (C),Deloitte
 * @FileName: InvoiceReqTypeEnums.java  
 * @Package: com.deloitte.tms.vat.base.enums  
 * @Description: //模块目的、功能描述  
 * @Author sqing  
 * @Date 2016年3月31日 下午4:54:43  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.base.enums;


/**  
 *〈一句话功能简述〉
 * 申请开票类型
 * @author sqing
 * @create 2016年3月31日 下午4:54:43 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public enum InvoiceReqTypeEnums {
	COUNTER("1","invoiceReqType.counter"), //1=柜台
	SPECIAL_NO_CONTRACT("2","invoiceReqType.special.no.contract"), //2=特殊-无合同
	AUTO("3","invoiceReqType.auto"), //3=自动
	SPECIAL_CONTRACT("4","invoiceReqType.special.with.contract");//4=特殊-有合同
	private InvoiceReqTypeEnums(String value,String text) {
		this.value = value;
		this.text = text;
	}
	private String value;
	private String text;
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
