package com.deloitte.tms.pl.core.hibernate.identifier;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.enhanced.TableGenerator;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

public class Ling2IdGenerator extends TableGenerator{
	
	Type oritype;
	
	public static final String STRATEGY_NAME = "com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator";
	
	public void configure(Type type, Properties params, Dialect dialect) throws MappingException {
		params.put(TABLE_PARAM, "TBL_SEQUENCE");
		params.put(VALUE_COLUMN_PARAM, "value");
		params.put(SEGMENT_COLUMN_PARAM, "type");
		params.put(SEGMENT_VALUE_PARAM, params.get("pkColumnValue"));
		oritype=type;
		super.configure(new LongType(), params, dialect);
	}
	public synchronized Serializable generate(final SessionImplementor session, Object obj) {
		Serializable value=super.generate(session, obj);
		if(oritype.getReturnedClass()==String.class){
			return value.toString();
		}
		return value;
	}
}
