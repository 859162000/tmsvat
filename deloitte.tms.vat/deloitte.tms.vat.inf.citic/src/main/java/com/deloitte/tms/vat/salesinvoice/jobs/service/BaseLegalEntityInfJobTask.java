
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

import java.util.List;
import java.util.Map;

import com.deloitte.tms.base.masterdata.model.BaseOrg;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEnablePrint;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdOrgLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdUsageLocalLegal;
import com.deloitte.tms.vat.salesinvoice.jobs.model.BaseLegalEntityInf;


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

	public int executeBaseLegalEntityInfDatas(
			List<BaseLegalEntityInf> batchBaseLegalEntityInfs,
			Map<String, TmsMdLegalEntity> tmsMdLegalEntityMap,
			Map<String, BaseOrg> baseOrgMap,
			Map<String , TmsMdOrgLegalEntity> tmsMdOrgLegalEntityMap_LegalEntity,
			Map<String, TmsMdLegalEnablePrint> tmsMdLegalEnablePrintMap,
			Map<String, TmsMdUsageLocalLegal> tmsMdUsageLocalLegalMap);
}
