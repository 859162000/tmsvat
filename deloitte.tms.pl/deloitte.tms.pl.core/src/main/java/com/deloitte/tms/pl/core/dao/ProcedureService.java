package com.deloitte.tms.pl.core.dao;

import java.util.Map;

/**
 * 存储过程调用服务接口
 * @author Jake.Wang@bstek.com
 * @since Nov 29, 2012
 *
 */
public interface ProcedureService {
	/**
	 * 调用带参数的存储过程
	 * @param procedureCall 存储过程调用字符串
	 * @param argument 存储过程调用参数
	 * @return 调用后的结果
	 */
	Map<Integer,Object> invoke(String procedureCall,ProcedureArgument argument);
	/**
	 * 调用无参存储过程
	 * @param procedureCall 存储过程调用字符串
	 * @return 调用后的结果
	 */
	Map<Integer,Object> invoke(String procedureCall);
	/**
	 * 调用带参数，指定数据源中存储过程
	 * @param procedureCall 存储过程调用字符串
	 * @param argument 存储过程调用参数
	 * @param dataSourceName 数据源名称
	 * @return 调用后的结果
	 */
	Map<Integer,Object> invoke(String procedureCall,ProcedureArgument argument,String dataSourceName);
	/**
	 * 调用无参的指定数据源中存储过程
	 * @param procedureCall 存储过程调用字符串
	 * @param dataSourceName 数据源名称
	 * @return 调用后的结果
	 */
	Map<Integer,Object> invoke(String procedureCall,String dataSourceName);
}
