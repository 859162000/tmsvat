/*
 * This file is part of BDF
 * BDF，Bstek Development Framework
 * Copyright 2002-2012, BSTEK
 * Dual licensed under the Bstek Commercial or GPL Version 2 licenses.
 * http://www.bstek.com/
 */
package com.ling2.dataimport.processor.importprocessor;

import java.util.List;

import com.ling2.dataimport.model.ExcelDataWrapper;
import com.ling2.dataimport.model.ImportResult;
/**
 * 对要导入的Excel内容的处理类
 * @author matt.yao@bstek.com
 * @since 1.0
 */
public interface ImportProcessor {
	public static final String BEAN_ID = "defaultImportProcessor";
	public static final String IMPORT_DATA_CACHE_KEY = "_import_cache";
	public String getName();
	/**
	 * 初始化方法，其中的init在每次处理Excel内容过程中只会执行一次，<br>
	 * 可以将一些需要初始化的变量放在这个方法中
	 * @throws Exception
	 */
	void init()throws Exception;
	
	public ImportResult getExcelData(ExcelDataWrapper excelDataWrapper);	
	/**
	 * 执行处理Excel内容的方法
	 * @param excelDataWrapper 一个包装了Excel信息的集合
	 * @throws Exception
	 */
	List loadPreviewData()throws Exception;
	/**
	 * 自定义预览并修改数据再保存
	 * @param excelDataWrapper 一个包装了Excel信息的集合
	 * @throws Exception
	 */
	Integer savePreviewData(ImportResult result)throws Exception;
	/**
	 * 默认预览并保存
	 * @return
	 * @throws Exception
	 */
	Integer savePreviewData() throws Exception;
	
	public void saveImportData2Cache(ImportResult result);
	public ImportResult getImportDataCache();
	public void clearImportDataCache();
}
