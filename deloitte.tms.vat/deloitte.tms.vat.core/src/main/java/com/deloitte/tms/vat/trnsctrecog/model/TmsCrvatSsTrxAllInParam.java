
/**    
 * Copyright (C),Deloitte
 * @FileName: TmsCrvatSsTrxAllInParam.java  
 * @Package: com.deloitte.tms.vat.trnsctrecog.model  
 * @Description: //模块目的、功能描述  
 * @Author tomfang  
 * @Date 2016年3月25日 上午11:25:12  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.trnsctrecog.model;


/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author tomfang
 * @create 2016年3月25日 上午11:25:12 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class TmsCrvatSsTrxAllInParam extends TmsCrvatSsTrxAll {
	private String customerCode;
	private String cutomerName;
	private String orgName;
	private String orgCode;
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	private String customerType;
	
	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCutomerName() {
		return cutomerName;
	}

	public void setCutomerName(String cutomerName) {
		this.cutomerName = cutomerName;
	}
	
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
}
