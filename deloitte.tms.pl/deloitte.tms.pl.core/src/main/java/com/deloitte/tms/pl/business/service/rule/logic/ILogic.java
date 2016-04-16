package com.deloitte.tms.pl.business.service.rule.logic;


/**
 * 
* @Description: 业务逻辑
* @author bo.wang
* @date 2013-3-26 上午10:48:16
*
 */
public interface ILogic {
	/**
	* @Description: 装载业务逻辑的处理程序
	* @param 
	* @return void
	* @throws
	 */
	void loadHander();
	/**
	* @Description: 将业务逻辑的处理程序排序
	* @param 
	* @return void
	* @throws
	 */
	void sort();
	/**
	* @Description: 执行业务逻辑
	* @param 
	* @return void
	* @throws
	 */
	void execute(Object entity);
}
