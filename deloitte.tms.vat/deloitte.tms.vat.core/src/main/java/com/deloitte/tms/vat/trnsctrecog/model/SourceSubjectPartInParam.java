
/**    
 * Copyright (C),Deloitte
 * @FileName: TradeAffirmParam.java  
 * @Package: com.deloitte.tms.vat.trade.pm.model  
 * @Description: //模块目的、功能描述  
 * @Author weijia  
 * @Date 2016年3月15日 下午4:52:29  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.trnsctrecog.model;


/**  
 * 交易认定Param
 * 功能详细描述
 * @author weijia
 * @create 2016年3月15日 下午4:52:29 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class SourceSubjectPartInParam {
	String id;
	String sourceSubjectCode;
	String useStatus;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSourceSubjectCode() {
		return sourceSubjectCode;
	}
	public void setSourceSubjectCode(String sourceSubjectCode) {
		this.sourceSubjectCode = sourceSubjectCode;
	}
	public String getUseStatus() {
		return useStatus;
	}
	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	
}
