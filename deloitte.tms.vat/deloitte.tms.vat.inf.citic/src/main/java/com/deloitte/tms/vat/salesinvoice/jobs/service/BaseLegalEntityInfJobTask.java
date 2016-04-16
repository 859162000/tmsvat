
/**    
 * Copyright (C),Deloitte
 * @FileName: TmsCrvatTrxInfJobService.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.service  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月19日 下午8:32:01  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.jobs.service;

import java.util.Map;


/**  
 * 接口表后续操作
 * 从TMS_CRVAT_TRX_INF表中分离数据，数据进入交易池
 * @author stonshi
 * @create 2016年3月19日 下午8:32:01 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public interface BaseLegalEntityInfJobTask {
	
	public static final String BEAN_ID="BaseLegalEntityInfJobTask";
	
	/**
	 * 从BASE_LEGAL_ENTITY_INF表中分离数据到相应表中
	 * @param tmsCrvatTrxInf
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public int executeBaseLegalEntityInfDatas(Map<String,Object> map);
}
