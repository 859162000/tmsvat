package com.deloitte.tms.pl.business.service.rule.handler;

import java.util.Map;

/**
 * 
* @ClassName: IHandler
* @Description: 处理程序
* @author bo.wang
* @date 2013-3-26 上午10:20:56
*
 */
public interface IHandler {
	Map<String, Object> getParameterMap();
	void loadParams();
	void buildParams();
	void buildHandler();
	Object execute(Object entity);
}
