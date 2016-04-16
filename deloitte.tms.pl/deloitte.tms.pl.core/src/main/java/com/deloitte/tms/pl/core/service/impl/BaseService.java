/**
 * @(#) BaseService.java
 */

package com.deloitte.tms.pl.core.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;


public abstract class BaseService<T> implements IService<T>
{
	public void batchExecute(Map datasetMap) {
		getDao().batchExecute(datasetMap);
	}


	public void remove(Object entity) {
		
		getDao().remove(entity);
	}

	public void removeAll(Collection entities) {
		
		getDao().removeAll(entities);
	}

	public void save(Object entity) {
		
		getDao().save(entity);
	}

	public void saveAll(Collection entities) {
		
		getDao().saveAll(entities);
	}

	public void update(Object entity) {
		
		getDao().update(entity);
	}
	public void update(Object entity, Boolean isSaveNull ) {
		
		getDao().update(entity,isSaveNull);
	}
	public void updateAll(Collection entities, Boolean isSaveNull ) {
		
		getDao().updateAll(entities,isSaveNull);
	}
	public void updateAll(Collection entities) {
		
		getDao().updateAll(entities);
	}
	public DaoPage pageBy(final DetachedCriteria detachedCriteria,
			final int pageIndex, final int pageSize)
	{
		return getDao().pageBy(detachedCriteria, pageIndex, pageSize);
	}
	public List findBy(final DetachedCriteria criteria)
	{
		return getDao().findBy(criteria);
	}
	public Object findById(Class clazz, Serializable id) {
		return getDao().get(clazz, id);
	}
	/**
	 * @param entity
	 */
	public void saveOrUpdate( Object entity )
	{
		getDao().saveOrUpdate(entity);
	}

	/**
	 * @param entities
	 */
	public void  saveOrUpdateAll( Collection entities )
	{
		getDao().saveOrUpdateAll(entities);
	}

	@Override
	public List findBy(StringBuffer hql, Map params, Integer pageIndex,
			Integer pageSize) {
		return getDao().findBy(hql, params, pageIndex, pageSize);
	}

	@Override
	public List findBy(StringBuffer hql, Map params) {
		return getDao().findBy(hql, params);
	}

	@Override
	public Long countBy(StringBuffer hql, Map params) {
		return getDao().countBy(hql, params);
	}

	@Override
	public Object get(Class clazz, Serializable id) {
		return getDao().get(clazz, id);
	}
	
	public Date getDatabaseServerDate(){
		return getDao().getDatabaseServerDate();
	}
}
