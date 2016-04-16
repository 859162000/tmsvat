package com.deloitte.tms.pl.core.commons.utils.reflect.filter;

import java.beans.PropertyDescriptor;

public interface PropertyFilter {

	public boolean isPermit(PropertyDescriptor property, Object object);
	
}
