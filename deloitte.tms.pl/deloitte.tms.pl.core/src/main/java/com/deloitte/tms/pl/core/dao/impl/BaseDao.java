/**
 * @(#) BaseDao.java
 */

package com.deloitte.tms.pl.core.dao.impl;

import java.io.Serializable;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.stereotype.Repository;

import com.deloitte.tms.pl.core.commons.constant.TableColnumDef;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.dao.EntityOperation;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;


/**
* <p>Title: BaseDao.java</p>
* <p>Description:</p>
* <p>Company: newtouch</p>
* @author 102010cncger@gmail.com
* @date 2012-9-19
* @version 1.0
*/
//@Repository(BaseDao.BEAN_ID)
public  class BaseDao<T> extends BaseDaoSimple<T>{
	Log log=LogFactory.getLog(BaseDao.class);
	
	public static final String BEAN_ID="baseDao";
	Collection<EntityOperation> entiryOperations;
	public Collection<EntityOperation> getEntiryOperations() {
		if(entiryOperations==null)
		{
			entiryOperations=SpringUtil.getBeansOfType(EntityOperation.class);
		}
		return entiryOperations;
	}
	/**
	 * @param entity
	 */
	public void save( Object entity ) {

		for(EntityOperation entiryOperation:getEntiryOperations())
		{
			entiryOperation.beforeSave(entity);
		}
		getSession().save(entity);
		for(EntityOperation entiryOperation:getEntiryOperations())
		{
			entiryOperation.afterSave(entity);
		}
		
	}

	public void saveOrUpdate(Object entity)
	{	
		try {
			ClassMetadata classMetadata = getSessionFactory().getClassMetadata(entity.getClass());
			String identiferPropertyName = classMetadata.getIdentifierPropertyName();
			Serializable identiferValue = (Serializable) ReflectUtils.getFieldContent(entity, identiferPropertyName);
			if (identiferValue == null) {
				for(EntityOperation entiryOperation:getEntiryOperations())
				{
					entiryOperation.beforeSave(entity);
				}
			} else {
				for(EntityOperation entiryOperation:getEntiryOperations())
				{
					entiryOperation.beforeUpdate(entity);
				}
			}
			getSession().saveOrUpdate(entity);
			if (identiferValue == null) {
				for(EntityOperation entiryOperation:getEntiryOperations())
				{
					entiryOperation.afterSave(entity);
				}
			} else {
				for(EntityOperation entiryOperation:getEntiryOperations())
				{
					entiryOperation.afterUpdate(entity);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("批量更新反射异常");
		}	
	}
	public void saveOrUpdateAll(final Collection entities)
	{
		for (Object entity : entities) {
			saveOrUpdate(entity);
		}
	}
	/**
	 * @param entities
	 */
	@SuppressWarnings("unchecked")
	public void saveAll( final Collection entities ) {
		for(EntityOperation entiryOperation:getEntiryOperations())
		{
			for (Object entity : entities) {
				entiryOperation.beforeSave(entity);
			}			
		}
		for (Object object : entities) {
			getSession().save(object);
		}
		for(EntityOperation entiryOperation:getEntiryOperations())
		{
			for (Object entity : entities) {
				entiryOperation.afterSave(entity);
			}			
		}
	}

	/**
	 * @param entity
	 * @param isSaveNull 是否将属性中的null值覆盖数据库的值
	 */
	public void update( Object entity, Boolean isSaveNull ) {
		if (isSaveNull) {
			for(EntityOperation entiryOperation:getEntiryOperations())
			{
				entiryOperation.beforeUpdate(entity);
			}
			getSession().saveOrUpdate(entity);
			for(EntityOperation entiryOperation:getEntiryOperations())
			{
				entiryOperation.afterUpdate(entity);
			}
		} else {
			try {
				ClassMetadata classMetadata = getSessionFactory().getClassMetadata(entity.getClass());
				String identiferPropertyName = classMetadata.getIdentifierPropertyName();
				Serializable identiferValue = (Serializable) ReflectUtils.getFieldContent(entity, identiferPropertyName);				
				if (identiferValue == null) {
					for(EntityOperation entiryOperation:getEntiryOperations())
					{
						entiryOperation.beforeSave(entity);
					}
					getSession().save(entity);
					for(EntityOperation entiryOperation:getEntiryOperations())
					{
						entiryOperation.afterSave(entity);
					}
				} else {
					Object persistentObject = getSession().get(
							entity.getClass(), identiferValue);
					ReflectUtils.replaceNullProperty(persistentObject, entity);
					for(EntityOperation entiryOperation:getEntiryOperations())
					{
						entiryOperation.beforeUpdate(entity);
					}
					getSession().saveOrUpdate(persistentObject);
					for(EntityOperation entiryOperation:getEntiryOperations())
					{
						entiryOperation.afterUpdate(entity);
					}
				}
			} catch (Exception e) {
				throw new RuntimeException("批量更新反射异常");
			}
		}
	}

	/**
	 * @param entity
	 */
	public void remove( Object entity ) {
		for(EntityOperation entiryOperation:getEntiryOperations())
		{
			entiryOperation.beforeDelete(entity);
		}
		log.debug("德勤数据规范需要,如果是BaseEntity的子类删除都执行更新操作,如果需要物理删除,请通过springutils获得BaseDaoSimple的实例进行删除");
		if(entity instanceof BaseEntity){
			BaseEntity baseEntity=(BaseEntity) entity;
			baseEntity=(BaseEntity) get(entity.getClass(),baseEntity.getId());
			baseEntity.setFlag(TableColnumDef.FLAG_DISABLED);
			getSession().update(entity);
		}else {
			getSession().delete(entity);
		}
		
		for(EntityOperation entiryOperation:getEntiryOperations())
		{
			entiryOperation.afterDelete(entity);
		}
	}
}