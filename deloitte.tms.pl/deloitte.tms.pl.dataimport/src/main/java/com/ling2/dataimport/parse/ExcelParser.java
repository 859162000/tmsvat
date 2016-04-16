package com.ling2.dataimport.parse;

import java.io.InputStream;
import java.util.Map;

/**
 * @author matt.yao@bstek.com
 * @since 2.0
 */
public interface ExcelParser {	
	public void parser(String excelModelId, int startRow, int endRow, InputStream in,Map parameter) throws Exception;
	public int processParserdExcelData() throws Exception ;
}
