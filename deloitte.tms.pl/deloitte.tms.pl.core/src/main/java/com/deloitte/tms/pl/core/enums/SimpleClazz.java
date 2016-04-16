package com.deloitte.tms.pl.core.enums;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


public enum SimpleClazz {

	STRING(String.class),
	
	LONG(Long.class),
	
	INTEGER(Integer.class),
	
	DOUBLE(Double.class),
	
	DATE(Date.class),
	
	TIMESTAMP(Timestamp.class),
	
	BOOLEAN(Boolean.class),
	
	BIGDECIMAL(BigDecimal.class);
	
	Class<?> clazz;

	private SimpleClazz(Class<?> clazz) {
		this.clazz = clazz;
		
	}

	public Class<?> getClazz() {
		return clazz;
	}

}
