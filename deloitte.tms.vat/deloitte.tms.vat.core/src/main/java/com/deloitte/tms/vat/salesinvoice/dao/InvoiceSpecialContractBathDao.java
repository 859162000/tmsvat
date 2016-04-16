package com.deloitte.tms.vat.salesinvoice.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.deloitte.tms.base.masterdata.model.BaseOrg;
import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.base.masterdata.model.TmsMdEquipment;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEquipment;
import com.deloitte.tms.base.masterdata.model.TmsMdOrgLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdTaxTrxType;
import com.deloitte.tms.base.taxsetting.model.TmsMdContract;
import com.deloitte.tms.base.taxsetting.model.TmsMdInventoryItems;
import com.deloitte.tms.base.taxsetting.model.TmsMdTrxAffirmSetting;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvReqBatchesL;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvReqBatchesLInParam;

public interface InvoiceSpecialContractBathDao extends IDao<TmsCrvatInvReqBatchesLInParam>{

	List<Customer> findCustomer(String string,String string1);

	List<TmsMdContract> findTmsMdContract(String string,String string1);

	List<com.deloitte.tms.base.taxsetting.model.TmsMdProject> findTmsMdProject(
			String string,String string1);

	List<TmsMdTaxTrxType> findTmsMdTaxTrxType(String string,String string1);

	List<TmsMdTrxAffirmSetting> findTmsMdTrxAffirmSetting(String id,
			String orgid);

	TmsMdOrgLegalEntity getRegistrationNumber(String validNo);

	List<TmsMdLegalEntity> getListTmsMdLegalEntity(String legalEntityId);

	TmsMdLegalEquipment getTmsMdEquipment(String legalEntityId);

	TmsMdEquipment getTmsMEquipment(String equipmentId);

	BaseOrg getOrg(String orgId);

	DaoPage findInvoiceReqAll(Map<String, Object> map, Integer pageNumber,
			Integer pageSize);

	DaoPage findTmsCrvatInvReqBatchesL(Map<String, Object> map, int pageNumber,
			int pageSize);

	List<TmsMdTrxAffirmSetting> findTmsMdTrxAffirmSetting(
			String trxAffirmSettingId);

	List<TmsCrvatInvReqBatchesL> findByTmsCrvatInvReqBatchesLId(String id);

}
