package com.deloitte.tms.pl.core.dao;

public interface EntityOperation {
	Boolean beforeSave(Object entity);
	void afterSave(Object entity);
	Boolean beforeUpdate(Object entity);
	void afterUpdate(Object entity);
	Boolean beforeDelete(Object entity);
	void afterDelete(Object entity);
}
