package com.deloitte.tms.pl.business.service.rule.logic.impl;

import java.util.ArrayList;
import java.util.List;

import com.deloitte.tms.pl.business.service.rule.handler.IHandler;
import com.deloitte.tms.pl.business.service.rule.handler.IHandlerLoader;
import com.deloitte.tms.pl.business.service.rule.logic.ILogic;
import com.deloitte.tms.pl.business.service.rule.model.IBusiness;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;

public class BaseLogicImpl implements ILogic {
	List<IHandler> handlers=new ArrayList<IHandler>();
	IBusiness topbusiness;
	List<IBusiness> resultList=new ArrayList<IBusiness>();
	IHandlerLoader handlerLoader=SpringUtil.getBean(IHandlerLoader.BUSINESS_HANDLER_LOADER_DEFAULT);
	public BaseLogicImpl(IBusiness business)
	{
		topbusiness=business;
		loadHander();
		sort();
	}
	public void loadHander()
	{
		addSubList(resultList,topbusiness);
		for(IBusiness business:resultList)
		{
			//将逻辑链中的可处理逻辑放在handlers中
			if(IBusiness.BUSINESS_HANDLE.equals(business.getType()))
			{
				IHandler handler=handlerLoader.loadHandler(business);
				handlers.add(handler);
			}
		}
	}
	@Override
	public void sort() {
		
	}
	private void addSubList(List<IBusiness> resultList,IBusiness business)
	{
		resultList.add(business);
		List<IBusiness> subList=business.getChilds();
		for(IBusiness subBusiness:subList)
		{
			addSubList(resultList,subBusiness);
		}
	}
	@Override
	public void execute(Object entity) {
		for(IHandler handler:handlers)
		{
			handler.execute(entity);
		}
	}
}
