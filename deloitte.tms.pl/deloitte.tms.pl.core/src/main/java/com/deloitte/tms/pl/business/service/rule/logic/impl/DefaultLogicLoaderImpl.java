package com.deloitte.tms.pl.business.service.rule.logic.impl;

import com.deloitte.tms.pl.business.service.IBusinessService;
import com.deloitte.tms.pl.business.service.rule.logic.ILogic;
import com.deloitte.tms.pl.business.service.rule.logic.ILogicLoader;
import com.deloitte.tms.pl.business.service.rule.model.IBusiness;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;

public class DefaultLogicLoaderImpl implements ILogicLoader {
	IBusinessService businessService;
	public IBusinessService getBusinessService() {
		return businessService;
	}
	public void setBusinessService(IBusinessService businessService) {
		this.businessService = businessService;
	}
	/** (非 Javadoc) 
	* Title: loadLogic
	* Description:根据logicCode加载业务逻辑
	* @param logicCode
	* @return
	* @see com.deloitte.tms.pl.business.service.rule.logic.ILogicLoader#loadLogic(java.lang.String)
	*/ 
	public ILogic loadLogic(String logicCode) {
		ILogic logic=null;
		IBusiness business=businessService.loadBusinessByCode(logicCode);
		if(business==null)
		{
			throw new BusinessException("相应的处理逻辑不存在");
		}
		if(IBusiness.BUSINESS_LOGIC.equals(business.getType()))
		{
			logic=new BaseLogicImpl(business);
		}else {
			throw new BusinessException("未定义的业务逻辑处理方式");
		}
		return logic;
	}

}
