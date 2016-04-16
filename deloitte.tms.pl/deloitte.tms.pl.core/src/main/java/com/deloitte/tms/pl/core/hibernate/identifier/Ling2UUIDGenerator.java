package com.deloitte.tms.pl.core.hibernate.identifier;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class Ling2UUIDGenerator implements IdentifierGenerator{
	
	public static final String STRATEGY_NAME = "com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator";

	@Override
	public Serializable generate(SessionImplementor session, Object object)
			throws HibernateException {
		return UUID.randomUUID().toString();
	}
	
}