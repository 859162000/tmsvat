package com.ling2.dataimport.processor.excelprocessor;

import java.util.List;

import com.ling2.dataimport.model.ExcelModelDetail;

public interface ImportModelDetailProvider {
	List<ExcelModelDetail> getExcelModelDetails(String excelModelId);
}
