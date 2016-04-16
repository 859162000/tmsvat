package com.ling2.dataimport.processor.excelprocessor;

import com.ling2.dataimport.model.ExcelDataWrapper;

/**
 * 对要导入的Excel内容的处理类
 * 
 * @author matt.yao@bstek.com
 * @since 2.0
 */
public interface IExcelProcessor {
	public String getName();

	/**
	 * 执行处理Excel内容的方法
	 * 
	 * @param excelDataWrapper
	 *            一个包装了Excel信息的集合
	 * @throws Exception
	 */
	public int execute(Object cachedata) throws Exception;
}
