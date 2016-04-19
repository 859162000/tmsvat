package com.deloitte.tms.base.taxsetting.service;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.base.taxsetting.model.TaxRuleAndRateInParam;
import com.deloitte.tms.base.taxsetting.model.TmsMdContract;
import com.deloitte.tms.base.taxsetting.model.TmsMdContractInParam;
import com.deloitte.tms.base.taxsetting.model.TmsMdProjectInParam;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;

public interface TmsMdContractService extends IService{
	public static final String BEAN_ID="tmsMdContractService";
	
	public DaoPage findTmsMdContractByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<TaxRuleAndRateInParam> findTmsMdContractByParams(Map params);
	
	public TmsMdContract getContractById(String id);
	
	public TmsMdContract getContractByNumber(String contractNumber);

	public void deleteContractById(String id);

	public TmsMdContract convertTmsMdContractInParamToEntity(TmsMdContractInParam inParam);

	public void removeTmsMdContracts(String contractId);
	
}

