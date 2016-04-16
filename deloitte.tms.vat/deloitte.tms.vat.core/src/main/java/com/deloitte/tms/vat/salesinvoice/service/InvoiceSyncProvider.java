package com.deloitte.tms.vat.salesinvoice.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.vat.salesinvoice.model.InvoicePrintPoolHInParam;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqL;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxD;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPool;

public interface InvoiceSyncProvider {
	/**
	 * jia wei
	 * @param invoiceCode
	 * @param invoiceNo
	 * @return
	 */
	InvoiceTrxD changeInvoiceStatu(String invoiceCode,String invoiceNo, String status);
	/**
	 * zhou liang
	 * @param invoiceCode
	 * @param invoiceNo
	 * @return
	 */
	Boolean exeAbolishInvoiceStatu(String invoiceCode,String invoiceNo);
	/**
	 * 获取发票的使用情况
	 * @param invoiceCode
	 * @param invoiceNo
	 * @return
	 */
	InvoicePrintPoolHInParam getInvoicePrintPoolH(String invoiceCode,String invoiceNo);
	
	/**
	 * 获取申请单行表可开票金额
	 * invoiceReqLId 行id
	 * @return
	 */
	BigDecimal getUserfulAmountByInvoiceReqL(String invoiceReqLId);
	/**
	 * 获取申请单行表可开票金额
	 * invoiceReqLId 行id
	 * @return
	 */
	BigDecimal getUserfulAmountByTrxPoolId(String crvatTrxPoolId);
	/**
	 * 获取申请单行表已开票金额
	 * invoiceReqLId 行id
	 * @return
	 */
	public BigDecimal getUsedAmount(String invoiceReqLId);
	
	/**
	 * 获取发票列表
	 * @param invoiceCode
	 * @param invoiceNumber
	 * @return
	 */
	public List<InvoiceTrxD> getInvoiceTrxDList(String invoiceCode,String invoiceNumber);
}
