package com.deloitte.tms.pl.workflow.env.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import com.deloitte.tms.pl.workflow.env.EnvironmentProvider;
@Component
public class LingEnvironmentProvider implements EnvironmentProvider{

	@Resource
	private SessionFactory sessionFactory;
	@Resource
    private PlatformTransactionManager platformTransactionManager;
    public String getTempFileStorePath() {
        return System.getProperty("java.io.tmpdir");
    }
 
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
 
    public PlatformTransactionManager getPlatformTransactionManager() {
        return platformTransactionManager;
    }
 
    public void setPlatformTransactionManager(
            PlatformTransactionManager platformTransactionManager) {
        this.platformTransactionManager = platformTransactionManager;
    }
    public String getCategoryId() {
        return null;
    }

}
