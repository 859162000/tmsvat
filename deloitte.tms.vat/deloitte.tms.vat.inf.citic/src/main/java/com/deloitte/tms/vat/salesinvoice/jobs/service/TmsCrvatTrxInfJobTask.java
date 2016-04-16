
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

import com.deloitte.tms.base.masterdata.model.BaseOrg;
import com.deloitte.tms.base.masterdata.model.CustBankAccount;
import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.base.masterdata.model.CustomerSite;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdOrgLegalEntity;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsCrvatTrxInf;


/**  
 * 接口表后续操作
 * 从TMS_CRVAT_TRX_INF表中分离数据，数据进入交易池
 * @author stonshi
 * @create 2016年3月19日 下午8:32:01 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public interface TmsCrvatTrxInfJobTask {
	
	public static final String BEAN_ID="TmsCrvatTrxInfJobTask";
	
	/**
	 * 从TMS_CRVAT_TRX_INF表中分离数据到交易池
	 * @param allCustBankAccount 
	 * @param allListSite 
	 * @param tmsCrvatTrxInf
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public int executeTransactionInfDatas(List<TmsCrvatTrxInf> tmsCrvatTrxInfs
			,List<TmsMdLegalEntity> allLegalEntities
			,List<TmsMdOrgLegalEntity> allOrgLegalEntities
			,List<BaseOrg> allOrgs
			,List<Customer> allCustomers, 
			List<CustomerSite> allListSite,
			List<CustBankAccount> allCustBankAccount);
}
