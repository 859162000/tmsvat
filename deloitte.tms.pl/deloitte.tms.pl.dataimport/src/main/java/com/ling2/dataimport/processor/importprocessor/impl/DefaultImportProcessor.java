package com.ling2.dataimport.processor.importprocessor.impl;

import org.springframework.stereotype.Service;

import com.ling2.dataimport.processor.importprocessor.ImportProcessor;

@Service(ImportProcessor.BEAN_ID)
public class DefaultImportProcessor extends BaseImportProcessor{

	@Override
	public String getName() {
		return "默认处理方式";
	}
}
