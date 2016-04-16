package com.ling2.dataimport.processor.excelprocessor.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ling2.core.commons.exception.BusinessException;
import com.ling2.core.commons.utils.AssertHelper;
import com.ling2.core.commons.utils.SpringUtil;
import com.ling2.dataimport.model.ExcelDataWrapper;
import com.ling2.dataimport.model.ExcelModel;
import com.ling2.dataimport.processor.excelprocessor.IExcelProcessor;
import com.ling2.dataimport.processor.importprocessor.ImportProcessor;
import com.ling2.dataimport.service.IExcelModelService;
@Service(ClassExcelProcessor.BEAN_ID)
public class ClassExcelProcessor implements IExcelProcessor{
	public static final String BEAN_ID = "classProcessor";
	@Resource(name = IExcelModelService.BEAN_ID)
	public IExcelModelService excelModelService;
	@Override
	public String getName() {
		return "spring bean处理类";
	}

	@Override
	public int execute(Object cachedata) throws Exception {
		ExcelDataWrapper excelDataWrapper=(ExcelDataWrapper)cachedata;
		ExcelModel excelModel = excelDataWrapper.getExcelModel();
		String processor=excelModel.getProcessor();
		AssertHelper.notEmpty_assert(processor, "class导入方案没设置处理bean");
		ImportProcessor importProcessor=SpringUtil.getBean(processor);
		if(importProcessor==null)
		{
			throw new BusinessException("class导入方案设置的处理bean没有找到");
		}
		Integer saveNum=importProcessor.savePreviewData();
		return saveNum;
	}
}
