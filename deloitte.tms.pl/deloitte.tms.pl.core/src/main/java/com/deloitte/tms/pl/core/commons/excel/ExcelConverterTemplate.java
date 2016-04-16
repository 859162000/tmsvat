package com.deloitte.tms.pl.core.commons.excel;

import java.util.Collection;

import com.deloitte.tms.pl.core.commons.excel.support.RowConvertCallback;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;

@SuppressWarnings("unchecked")
public interface ExcelConverterTemplate {

	public Collection convert(String filePath, RowConvertCallback action) throws BusinessException;
	
}
