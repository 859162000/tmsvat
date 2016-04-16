package com.ling2.dataimport.parse;

import com.ling2.dataimport.processor.excelprocessor.impl.ClassExcelProcessor;
import com.ling2.dataimport.processor.excelprocessor.impl.DefaultExcelProcessor;

/**
 * @author matt.yao@bstek.com
 * @since 2.0
 */
public interface ExcelParserCache {
	
	public static final String DEFAULT_SPRING_EXCEL_PROCESSOR = DefaultExcelProcessor.BEAN_ID;
	public static final String CLASS_SPRING_EXCEL_PROCESSOR = ClassExcelProcessor.BEAN_ID;
	public static final String EXCEL_DATA_CACHE_KEY = "excel_cache";
	
	public void init();
	public String getExcelCacheKey();
	public void saveExcelData2Cache();
	public Object getCacheExcelData();
	public void clearCacheExcelData();
}
