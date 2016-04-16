
/**    
 * Copyright (C),Deloitte
 * @FileName: TmsCrvatTrxAffirmInParam.java  
 * @Package: com.deloitte.tms.vat.trnsctrecog.model  
 * @Description: //模块目的、功能描述  
 * @Author tomfang  
 * @Date 2016年3月25日 上午11:25:52  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.trnsctrecog.model;


/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author tomfang
 * @create 2016年3月25日 上午11:25:52 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class TmsCrvatTrxAffirmInParam extends TmsCrvatTrxAffirm {
	private String customerName;
	private String customerNumber;
	private String orgCode;
	private String taxTrxTypeCode;
	private String taxTrxTypeName;
	
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerNumber() {
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
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

}
