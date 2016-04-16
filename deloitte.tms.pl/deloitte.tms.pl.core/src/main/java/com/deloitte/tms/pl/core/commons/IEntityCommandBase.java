package com.deloitte.tms.pl.core.commons;

import java.io.Serializable;
import java.util.Collection;

public interface IEntityCommandBase {
	/**
	 * @param entity
	 */
	void save( Object entity );

	/**
	 * @param entities
	 */
	void saveAll( Collection entities );
	void remove(Object entity);
	/**
	 * @param entities
	 */
	void removeAll( Collection entities );
	public void update(Object entity);
	public void update( Object entity, Boolean isSaveNull );
	/**
	 * @param entities
	 */
	void updateAll( Collection entities );
	void updateAll(Collection entities, Boolean isSaveNull );
	Object findById(Class clazz,Serializable id);
	/**
	 * @param entity
	 */
	void saveOrUpdate( Object entity );

	/**
	 * @param entities
	 */
	void saveOrUpdateAll( Collection entities );
}
