package com.deloitte.tms.pl.core.dao.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.deloitte.tms.pl.core.dao.IDao;

public class DaoUtils implements ApplicationContextAware{
	public static ApplicationContext applicationContext;
	public static IDao getSimpleDao()
	{
		
		return (IDao) applicationContext.getBean(IDao.BEAN_ID_SIMPLE);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;
	}
}
