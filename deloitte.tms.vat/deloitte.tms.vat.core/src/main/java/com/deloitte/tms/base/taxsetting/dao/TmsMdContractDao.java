package com.deloitte.tms.base.taxsetting.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.vat.salesinvoice.dao.InvoiceAbolishDao;
import com.deloitte.tms.base.taxsetting.model.TmsMdContract;

public interface TmsMdContractDao extends IDao<TmsMdContract>{
	public static final String BEAN_ID="tmsMdContractDao";
	
	public DaoPage findTmsMdContractByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<TmsMdContract> findTmsMdContractByParams(Map params);
	
	public TmsMdContract getContractById(String id);


	public void removeTmsMdContractById(String contractId);
}

