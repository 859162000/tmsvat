
/**    
 * Copyright (C),Deloitte
 * @FileName: InvoiceReqLInParam.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.model  
 * @Description: //模块目的、功能描述  
 * @Author sqing  
 * @Date 2016年3月16日 下午10:43:34  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.model;


/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author sqing
 * @create 2016年3月16日 下午10:43:34 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class InvoiceReqLInParam extends InvoiceReqL{
	/**
	 * 税率
	 */
	private String taxRate;

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}
	/**
	 * 涉税交易类型
	 */
	private String taxTrxTypeName;
	/**
	 * 涉税交易编码
	 */
	private String taxTrxTypeCode;
	
	
	public String getTaxTrxTypeCode() {
		return taxTrxTypeCode;
	}

	public void setTaxTrxTypeCode(String taxTrxTypeCode) {
		this.taxTrxTypeCode = taxTrxTypeCode;
	}

	public String getTaxTrxTypeName() {
		return taxTrxTypeName;
	}

	public void setTaxTrxTypeName(String taxTrxTypeName) {
		this.taxTrxTypeName = taxTrxTypeName;
	}
	private String orgName;

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * 可开票金额
	 */
	private String userfulAmount;

	public String getUserfulAmount() {
		return userfulAmount;
	}

	public void setUserfulAmount(String userfulAmount) {
		this.userfulAmount = userfulAmount;
	}
	/**
	 * 已开票金额
	 */
	private String usedAmount;

	public String getUsedAmount() {
		return usedAmount;
	}

	public void setUsedAmount(String usedAmount) {
		this.usedAmount = usedAmount;
	}
	/**
	 * 组织编码
	 */
	private String orgCode;

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	/**
	 * 交易流水号
	 */
	private String trxNumber;

	/**
	 * 交易日期
	 * @return
	 */
	private String trxDate;
	public String getTrxNumber() {
		return trxNumber;
	}

	public void setTrxNumber(String trxNumber) {
		this.trxNumber = trxNumber;
	}

	public String getTrxDate() {
		return trxDate;
	}

	public void setTrxDate(String trxDate) {
		this.trxDate = trxDate;
	}
	
	private String trxid;

	public String getTrxid() {
		return trxid;
	}

	public void setTrxid(String trxid) {
		this.trxid = trxid;
	}
	
}
