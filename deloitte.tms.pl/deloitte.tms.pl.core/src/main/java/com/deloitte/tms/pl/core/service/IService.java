package com.deloitte.tms.pl.core.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.deloitte.tms.pl.core.commons.IEntityCommandBase;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.dao.IDao;



public interface IService<T> extends IEntityCommandBase {
	
	public IDao getDao();
	/**
	 * @param datasetMap
	 */
	void batchExecute(Map datasetMap );
	
	DaoPage pageBy(final DetachedCriteria detachedCriteria,
			final int pageIndex, final int pageSize);
	
	List findBy(final DetachedCriteria criteria);
	
	List findBy(StringBuffer hql,Map params,Integer pageIndex,Integer pageSize);
	
	List findBy(StringBuffer hql,Map params);
	
	Long countBy(StringBuffer hql,Map params);
	
	public Object get( Class clazz, Serializable id );
	
	public Date getDatabaseServerDate();
	
	
}
