package com.deloitte.tms.pl.business.service.rule.param.impl;

import com.deloitte.tms.pl.business.service.rule.param.IParameter;
import com.deloitte.tms.pl.business.service.rule.param.IParameterConverter;

public class BaseParameterConverter implements IParameterConverter{
	
	@Override
	public Object converter(IParameter parameter) {
		return parameter.getParamvalue()==null?null:parameter.getParamvalue().toString();
	}

}
