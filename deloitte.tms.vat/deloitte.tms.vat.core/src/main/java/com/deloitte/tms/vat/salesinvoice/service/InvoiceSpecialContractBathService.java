package com.deloitte.tms.vat.salesinvoice.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.deloitte.tms.base.masterdata.model.TmsMdEquipment;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.pl.dictionary.model.DictionaryEntity;

public interface InvoiceSpecialContractBathService extends IService{

	DaoPage findTmsCrvatInvReqBatchesHInParam(List<String[]> list);

	TmsMdLegalEntity getRegistrationNumber(String validNo);

	TmsMdEquipment getTmsMdEquipment(String legalEntityId);

	com.deloitte.tms.base.masterdata.model.BaseOrg getOrg(String orgId);

	DaoPage findInvoiceReqAll(Map<String, Object> map, Integer pageNumber,
			Integer pageSize);

	DaoPage findTmsCrvatInvReqBatchesLInParam(Map<String, Object> map,
			int pageNumber, int pageSize);

	int removeInvoiceReqHs(String ids);

	

}
