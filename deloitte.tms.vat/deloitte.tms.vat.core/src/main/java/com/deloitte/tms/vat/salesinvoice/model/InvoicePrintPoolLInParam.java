
/**    
 * Copyright (C),Deloitte
 * @FileName: InvoicePrintPoolH.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.model  
 * @Description: //模块目的、功能描述  
 * @Author liazhou  
 * @Date 2016年3月17日 下午12:12:48  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;


/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author liazhou
 * @create 2016年3月17日 下午12:12:48 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class InvoicePrintPoolLInParam extends InvoicePrintPoolL {
	/**
	 * 明细表列表
	 */
	List<InvoicePrintPoolD> invoicePrintPoolDList = new ArrayList<InvoicePrintPoolD>();
	
	String invoiceCategory;
	
	List<TmsCrvatInvoicePreLInParam> invoicePreLInParamList = new ArrayList<TmsCrvatInvoicePreLInParam>();
	
	
	
	
	public List<TmsCrvatInvoicePreLInParam> getInvoicePreLInParamList() {
		return invoicePreLInParamList;
	}

	public void setInvoicePreLInParamList(
			List<TmsCrvatInvoicePreLInParam> invoicePreLInParamList) {
		this.invoicePreLInParamList = invoicePreLInParamList;
	}

	public List<InvoicePrintPoolD> getInvoicePrintPoolDList() {
		return invoicePrintPoolDList;
	}

	public void setInvoicePrintPoolDList(
			List<InvoicePrintPoolD> invoicePrintPoolDList) {
		this.invoicePrintPoolDList = invoicePrintPoolDList;
	}

	public String getInvoiceCategory() {
		return invoiceCategory;
	}

	public void setInvoiceCategory(String invoiceCategory) {
		this.invoiceCategory = invoiceCategory;
	}


	
}
