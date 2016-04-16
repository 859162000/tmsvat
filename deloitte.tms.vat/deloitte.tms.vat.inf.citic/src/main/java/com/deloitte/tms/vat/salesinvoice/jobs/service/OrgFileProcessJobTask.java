
/**    
 * Copyright (C),Deloitte
 * @FileName: OrgJobTask.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.task  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月24日 上午12:49:12  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.jobs.service;

import java.util.List;

import com.deloitte.tms.vat.salesinvoice.jobs.model.BaseLegalEntityInf;

/**  
 * 组织机构关联
 * @author stonshi
 * @create 2016年3月24日 上午12:49:12 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public interface OrgFileProcessJobTask {

	public static final String BEAN_ID="OrgFileProcessJobTask";
	
	/**
	 * 从TMS_CRVAT_TRX_INF表中分离数据到交易池
	 * @param tmsCrvatTrxInf
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public int executeProcessPreOrg(List<BaseLegalEntityInf> legalEntityInfs);
}
