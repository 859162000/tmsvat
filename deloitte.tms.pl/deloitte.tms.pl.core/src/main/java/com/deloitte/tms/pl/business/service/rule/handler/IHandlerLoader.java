package com.deloitte.tms.pl.business.service.rule.handler;

import com.deloitte.tms.pl.business.service.rule.model.IBusiness;

public interface IHandlerLoader {
	String BUSINESS_HANDLER_LOADER_DEFAULT="BUSINESS_HANDLER_LOADER_DEFAULT";
	IHandler loadHandler(IBusiness business);
}
