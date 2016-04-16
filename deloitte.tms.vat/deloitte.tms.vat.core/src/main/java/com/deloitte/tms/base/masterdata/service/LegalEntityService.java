package com.deloitte.tms.base.masterdata.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;

/**
 * @since 2013-1-24
 * @author Jacky.gao
 */
public interface LegalEntityService extends IService{
	
	public static final String BEAN_ID="legalEntityService";
	
	public DaoPage loadPageTaxDepts(Class cla, Map params, int pageIndex, int pageSize);
	
	/**
	 * 
	 * @param entity
	 */
	public void saveTaxOrgWithNeedInfo(TmsMdLegalEntity entity);
	
	/**
	 * 
	 * @param ids
	 * @param cla
	 */
	public void delById(ArrayList<String> ids, Class cla);
	
	/**
	 * 
	 * @param entity
	 */
	public void updateTaxOrgWithNeedInfo(TmsMdLegalEntity entity);

	public DaoPage loadPageTaxOrg(Class cla, int pageIndex, int pageSize);

	public List<TmsMdLegalEntity> listAll();

	public List<TmsMdLegalEntity> searchLegal(HashMap<String, Object> para);
}
