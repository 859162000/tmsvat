package com.deloitte.tms.vat.salesinvoice.jobs.service;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsCrvatInvReqBatInf;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsCrvatInvReqHImpl;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvReqBatchesLInParam;

public interface TmsCrvatInvReqBatchesJobService extends IService{
	public Map<String,List<TmsCrvatInvReqBatchesLInParam>> analyzeTmsCrvatInvReqBatchesParam();

	public List<TmsCrvatInvReqHImpl> analyzeTmsCrvatInvReqHInf();

}
