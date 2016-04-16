package com.ling2.dataimport.upload;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.bstek.dorado.uploader.DownloadFile;
import com.bstek.dorado.uploader.UploadFile;
import com.bstek.dorado.uploader.annotation.FileProvider;
import com.bstek.dorado.uploader.annotation.FileResolver;
import com.bstek.dorado.uploader.resolver.UploadResolver;
import com.ling2.core.commons.exception.BusinessException;
import com.ling2.core.commons.utils.AssertHelper;
import com.ling2.core.commons.utils.SpringUtil;
import com.ling2.dataimport.model.ExcelModel;
import com.ling2.dataimport.parse.ExcelParser;
import com.ling2.dataimport.parse.impl.DefaultExcelParser;
import com.ling2.dataimport.service.IExcelModelService;

/**
 * 
 * @author matt.yao@bstek.com
 * @since 2.0
 * 
 */
@Component("excelImportUploadResolver")
public class ExcelImportUploadResolver extends UploadResolver{
	@Resource(name = IExcelModelService.BEAN_ID)
	public IExcelModelService excelModelService;
	
	@FileResolver
	public Map<String, String> process(UploadFile file,
			Map<String, Object> parameter) throws Exception {
		Map<String, String> result=new HashMap<String, String>();
		try {
			MultipartFile multipartfile = file.getMultipartFile();
			String name = multipartfile.getOriginalFilename();
			if (name.endsWith(".xlsx") || name.endsWith(".xls")) {
				InputStream input = multipartfile.getInputStream();
				String startRow = (String)parameter.get("startRow");
				String endRow = (String)parameter.get("endRow");
				String excelModelId = (String)parameter.get("excelModelId");
				
				ExcelModel excelModel = excelModelService.findExcelModelById(excelModelId);
				AssertHelper.notEmpty_assert(excelModel, "导入方案"+excelModelId+"没有定义");
				
				String excelParser_name=excelModel.getExcelParser();
				if(AssertHelper.empty(excelParser_name)){
					excelParser_name=DefaultExcelParser.BEAN_ID;
				}				
				ExcelParser excelParser = (ExcelParser) SpringUtil.getBean(excelParser_name);
				AssertHelper.notEmpty_assert(excelParser, "excel解析接口"+excelParser_name+"没有定义");				
				
				excelParser.parser(excelModelId,Integer.valueOf(startRow),Integer.valueOf(endRow), input,parameter);
			} else {
				throw new BusinessException("上传的文件不是excel格式！");
			}
//			result.put("fileid", value)
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("解析出错"+e.getMessage());
		}
		return result;
	}
	@FileProvider
	public DownloadFile download(Map<String, String> parameter)
			throws IOException {
//		String fileName = parameter.get("file");
//		DownloadFile file = getDownloadFile(fileName);
//		return file;
		return null;
	}
}
