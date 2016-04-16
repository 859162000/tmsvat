
/**    
 * Copyright (C),Deloitte
 * @FileName: BaseOrgInParam.java  
 * @Package: com.deloitte.tms.base.masterdata.model  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月17日 下午3:40:02  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.base.masterdata.model;


/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author stonshi
 * @create 2016年3月17日 下午3:40:02 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class BaseOrgInParam extends BaseOrg {
	private String orgId;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}
