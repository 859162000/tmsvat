package com.deloitte.tms.pl.core.commons.utils;

import org.hibernate.SessionFactory;

public class SessionFactoryUtils {
	private static SessionFactory sessionFactory;
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
