package com.deloitte.tms.pl.workflow.utils;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import com.deloitte.tms.pl.workflow.env.EnvironmentProvider;

/**
 * @author Jacky.gao
 * @since 2013年9月17日
 */
@Component(EnvironmentUtils.BEAN_ID)
public class EnvironmentUtils implements ApplicationContextAware{
	public static final String BEAN_ID="uflo.environmentUtils";
	private EnvironmentProvider provider;
	private static EnvironmentUtils environment;
	private ApplicationContext applicationContext;
	public static EnvironmentUtils getEnvironment(){
		return environment;
	}
	public String getTempFileStorePath(){
		return provider.getTempFileStorePath();
	}
	public SessionFactory getSessionFactory(){
		return provider.getSessionFactory();
	}
	
	public PlatformTransactionManager getPlatformTransactionManager(){
		return provider.getPlatformTransactionManager();
	}
	
	public String getCategoryId(){
		return provider.getCategoryId();
	}
	
	public EnvironmentProvider getEnvironmentProvider(){
		return provider;
	}
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext=applicationContext;
		Collection<EnvironmentProvider> providers=applicationContext.getBeansOfType(EnvironmentProvider.class).values();
		if(providers.size()==0){
			throw new RuntimeException("You must be implementation "+EnvironmentProvider.class.getName()+" interface when use uflo!");
		}
		provider=providers.iterator().next();
		environment=this;
	}
}
