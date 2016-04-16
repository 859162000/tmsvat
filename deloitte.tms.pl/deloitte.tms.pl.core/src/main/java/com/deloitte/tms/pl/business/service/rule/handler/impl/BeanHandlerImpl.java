package com.deloitte.tms.pl.business.service.rule.handler.impl;

import com.deloitte.tms.pl.business.service.rule.model.IBusiness;
import com.deloitte.tms.pl.business.service.rule.resolver.IResolver;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;

public class BeanHandlerImpl extends BaseHandlerImpl{
	public static final String BEANPARAM="BEANPARAM";

	public BeanHandlerImpl(IBusiness business) {
		super(business);
	}

	@Override
	public void buildHandler() {
		
	}
	@Override
	public Object execute(Object entity) {
		IResolver resolver= SpringUtil.getBean((String) parameterMap.get(BEANPARAM));
		return resolver.execute(entity);
	}
}
