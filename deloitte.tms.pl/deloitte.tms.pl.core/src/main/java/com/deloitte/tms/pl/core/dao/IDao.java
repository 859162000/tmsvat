/**
 * @(#) Dao.java
 */

package com.deloitte.tms.pl.core.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.deloitte.tms.pl.core.commons.support.DaoPage;

public interface IDao<T> {
	public static final String BEAN_ID_SIMPLE="baseDaoSimple";
	public static final String BEAN_ID_BASE="baseDao";
	public List find( String queryString );
	/**
	 * @param entity
	 */
	void save(Object entity);

	/**
	 * @param entities
	 */
	void saveAll(Collection entities);

	void remove(Object entity);

	/**
	 * @param entities
	 */
	void removeAll(Collection entities);
	void removeById(Class clazz, Serializable id);
	void removeDirectly(Object entity);

	/**
	 * @param entities
	 */
	void removeAllDirectly(Collection entities);
	void removeByIdDirectly(Class clazz, Serializable id);
	void update(Object entity);
	public void update( Object entity, Boolean isSaveNull );
	/**
	 * @param entities
	 */
	void updateAll(Collection entities);
	void updateAll(Collection entities, Boolean isSaveNull );
	
	public void saveOrUpdate(Object entity);
	public void saveOrUpdateAll(final Collection entities);
	/**
	 * @param datasetMap
	 */
	void batchExecute(Map datasetMap);
	public void excuteProduce( final String sql, final String[] values );
	public Integer executeProduce(final String sql, final Map values);
	public Integer executeHqlProduce(String sql, Map values);
	Object get(Class clazz, Serializable id);

	Object load(Class clazz, Serializable id);
	
	DaoPage pageBy(final DetachedCriteria detachedCriteria,final int pageIndex, final int pageSize);
	List findBy(final DetachedCriteria criteria);
	public boolean isUnique(final Object entity, final String[] propertyNames);
	public int queryForInt(String hql,Map<String,Object> parameterMap);
	List findBy(StringBuffer hql,Map params,Integer pageIndex,Integer pageSize);
	
	List findBy(StringBuffer hql,Map params);
	
	Long countBy(StringBuffer hql,Map params);
	
	public Date getDatabaseServerDate();
}
