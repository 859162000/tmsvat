package com.deloitte.tms.pl.business.service;

import com.deloitte.tms.pl.business.service.rule.logic.ILogic;
import com.deloitte.tms.pl.business.service.rule.logic.ILogicLoader;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;

/**
* @Description: TODO(这里用一句话描述这个类的作用)
* @author bo.wang
* @date 2013-3-26 下午12:33:08
* 
*/ 
public class BusinessUtils {
	public static ILogicLoader logicLoader= SpringUtil.getBean(ILogicLoader.DEFAULT_LOGIC_LOADER_DEFALUT);
	/**
	* @Description: 获取到一个逻辑模块
	* @param @param logicCode
	* @param @return
	* @return ILogic
	* @throws
	 */
	public static ILogic getLogic(String logicCode)
	{
		ILogic logic= logicLoader.loadLogic(logicCode);
		return logic;
	}
	/**
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param logicCode
	* @param @param entity
	* @return void
	* @throws
	 */
	public static void processLogic(String logicCode,Object entity)
	{
		ILogic logic=getLogic(logicCode);
		logic.execute(entity);
	}
	/** 
	* @Description: 处理一个业务逻辑
	* @param @param businessCode
	* @param @param entity
	* @return void
	* @throws 
	*/ 
	public static void processBusiness(String businessCode,Object entity)
	{
		
	}
	/** 
	* @Description: 处理一个业务逻辑
	* @param @param logicCode
	* @param @param entity
	* @return void
	* @throws 
	*/ 
	public static void processModule(String logicCode,Object entity)
	{
		
	}
}
