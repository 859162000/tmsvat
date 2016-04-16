package com.deloitte.tms.base.taxsetting.dao;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.base.taxsetting.model.TmsMdProject;
/**
 * Home object for domain model class TaxRuleAndRate.
 * @see com.deloitte.tms.base.taxsetting.model
 * @author Gao Di
 */
public interface TmsMdProjectDao extends IDao<TmsMdProject>{
	public static final String BEAN_ID="tmsMdProjectDao";
	public DaoPage findTmsMdProjectByParams(Map params, Integer pageIndex,Integer pageSize);
	public List<TmsMdProject> findTmsMdProjectByParams(Map params);
	
	public void removeTmsMdProjects(String projectId);
}

