package com.deloitte.tms.pl.business.service.rule.param.impl;

import java.util.HashMap;
import java.util.Map;

import com.deloitte.tms.pl.business.service.rule.param.IParameter;
import com.deloitte.tms.pl.business.service.rule.param.IParameterConverter;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;

public class BaseParameterConverterLoaderImpl implements IParameterConverter{
	Map<String, IParameterConverter> converterMap=new HashMap<String, IParameterConverter>();
	
	public Map<String, IParameterConverter> getConverterMap() {
		return converterMap;
	}

	public void setConverterMap(Map<String, IParameterConverter> converterMap) {
		this.converterMap = converterMap;
	}

	@Override
	public Object converter(IParameter parameter) {
		IParameterConverter converter=converterMap.get(parameter.getType());
		if(converter==null)
		{
			throw new BusinessException("类型为"+parameter.getType()+"的转换方式未定义");
		}
		return converter.converter(parameter);
	}

}
