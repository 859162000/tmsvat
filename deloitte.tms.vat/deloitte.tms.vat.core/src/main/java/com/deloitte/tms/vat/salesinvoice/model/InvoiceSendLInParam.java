
/**    
 * Copyright (C),Deloitte
 * @FileName: InvoiceSendLInParam.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.model  
 * @Description: //模块目的、功能描述  
 * @Author tomfang  
 * @Date 2016年3月17日 下午4:01:19  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.model;


/**  
 *InvoiceSendL 前端传参
 * 功能详细描述
 * @author tomfang
 * @create 2016年3月17日 下午4:01:19 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class InvoiceSendLInParam extends InvoiceSendL{
	public String getEntityID() {
		return entityID;
	}

	public void setEntityID(String entityID) {
		this.entityID = entityID;
	}

	private String entityID;
	private String pageNumber;
	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	private String pageSize;
}
