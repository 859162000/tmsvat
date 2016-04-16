package com.deloitte.tms.pl.business.service.rule.handler.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.deloitte.tms.pl.business.service.rule.handler.IHandler;
import com.deloitte.tms.pl.business.service.rule.model.IBusiness;
import com.deloitte.tms.pl.business.service.rule.param.IParameter;
import com.deloitte.tms.pl.business.service.rule.param.IParameterConverterLoader;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;

/**
 * 
* @ClassName: BaseDbLoaderHandlerImpl
* @Description: 从数据库加载handler所需参数
* @author bo.wang
* @date 2013-3-26 上午10:23:53
*
 */
public abstract class BaseHandlerImpl implements IHandler{
	Set<IParameter> parameters;
	Map<String,Object> parameterMap;
	IParameterConverterLoader parameterConverterLoader=SpringUtil.getBean(IParameterConverterLoader.BUSINESS_PARAM_CONVERTER_LOADER);
	IBusiness business;
	public Set<IParameter> getParameters() {
		return parameters;
	}

	public void setParameters(Set<IParameter> parameters) {
		this.parameters = parameters;
	}

	public Map<String, Object> getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(Map<String, Object> parameterMap) {
		this.parameterMap = parameterMap;
	}
	public BaseHandlerImpl(IBusiness business)
	{
		this.business=business;
		loadParams();
		buildParams();
		buildHandler();
	}

	@Override
	public void loadParams() {
		parameters=business.getBusinessParamsSet().getParameters();
	}
	@Override
	public void buildParams() {
		parameterMap=new HashMap<String, Object>();
		for(IParameter parameter:parameters)
		{
			Object value=parameterConverterLoader.converter(parameter);
			parameterMap.put(parameter.getParamcode(),value);
		}
	}
}
