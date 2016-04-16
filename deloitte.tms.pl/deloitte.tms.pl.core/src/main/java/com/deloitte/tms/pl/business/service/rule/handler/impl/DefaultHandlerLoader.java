package com.deloitte.tms.pl.business.service.rule.handler.impl;

import com.deloitte.tms.pl.business.service.rule.handler.IHandler;
import com.deloitte.tms.pl.business.service.rule.handler.IHandlerLoader;
import com.deloitte.tms.pl.business.service.rule.model.IBusiness;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;

public class DefaultHandlerLoader implements IHandlerLoader{

	@Override
	public IHandler loadHandler(IBusiness business) {
		if(IBusiness.BUSINESS_HANDLE_BEAN.equals(business.getImplementation()))
		{
			return new BeanHandlerImpl(business);
		}else {
			throw new BusinessException("未定义的处理方式");
		}
	}

}
