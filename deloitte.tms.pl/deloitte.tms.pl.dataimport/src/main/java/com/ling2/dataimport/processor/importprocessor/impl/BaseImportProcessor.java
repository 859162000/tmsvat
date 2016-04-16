package com.ling2.dataimport.processor.importprocessor.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.ling2.cache.ApplicationCache;
import com.ling2.core.commons.exception.BusinessException;
import com.ling2.core.commons.utils.SpringUtil;
import com.ling2.core.commons.utils.reflect.ReflectUtils;
import com.ling2.core.context.utils.ContextUtils;
import com.ling2.dataimport.model.CellWrapper;
import com.ling2.dataimport.model.ExcelDataWrapper;
import com.ling2.dataimport.model.ExcelModel;
import com.ling2.dataimport.model.ImportResult;
import com.ling2.dataimport.model.RowWrapper;
import com.ling2.dataimport.processor.importprocessor.ImportProcessor;
import com.ling2.dataimport.service.IExcelModelService;

public abstract class BaseImportProcessor implements ImportProcessor{
	
	public static final String IMPORT_DATA_CACHE_KEY = "_import_cache";

	public void init() throws Exception {
	}
	public IExcelModelService excelModelService;
	/**
	 * 处理预览的数据以供预览
	 */
	public List loadPreviewData() throws Exception {
		ImportResult result=getImportDataCache();
		return result.getDatas();
	}
	/**
	 * 保存预览数据
	 * @param oldlList 预览界面返回的数据
	 * @throws Exception
	 */
	public Integer savePreviewData(ImportResult records) throws Exception {
//		for(Object object:oldlList)
//		{
//			
//		}
		clearImportDataCache();
		return 0;
	}
	public Integer savePreviewData() throws Exception {
		ImportResult valueList=getImportDataCache();
		return savePreviewData(valueList);
	}
	public ImportResult getExcelData(ExcelDataWrapper excelDataWrapper)
	{
		ImportResult result=new ImportResult();
		result.setParameter(excelDataWrapper.getParameter());
		ExcelModel  excelModel=excelDataWrapper.getExcelModel();
		String calssName=excelModel.getTableName();
		Collection<RowWrapper> rowWrappers=excelDataWrapper.getRowWrappers();
		List excelList=new ArrayList();
		for(RowWrapper rowWrapper:rowWrappers){
			Collection<CellWrapper> cellWrappers = rowWrapper.getCellWrappers();
			Map valueMap=new HashMap();
			for(CellWrapper cellWrapper:cellWrappers){
				String columnName=cellWrapper.getColumnName();
				if(StringUtils.hasText(columnName)){
					Object columnValue=cellWrapper.getValue();
					
					valueMap.put(columnName, columnValue);
				}
			}
			Object newObject;
			try {
				newObject = Class.forName(calssName).newInstance();
				ReflectUtils.setPropertiesWithAutoParse(newObject, valueMap);
				excelList.add(newObject);
			} catch (InstantiationException e) {
				throw new BusinessException("没有相关构造函数");
			} catch (IllegalAccessException e) {
				throw new BusinessException("导入属性配置有误");
			} catch (ClassNotFoundException e) {
				throw new BusinessException("导入属性配置有误,请检查是否有相关类");
			}
				
			
		}
		result.setDatas(excelList);
		return result;
	}
	public String getImportCacheKey() {
		return ContextUtils.getCurrentUser().getUsername() + ImportProcessor.IMPORT_DATA_CACHE_KEY;
	}

	public void saveImportData2Cache(ImportResult result) {
		ApplicationCache cache = (ApplicationCache) SpringUtil.getBean(ApplicationCache.BEAN_ID);
		cache.putTemporaryCacheObject(getImportCacheKey(), result);
	}

	public ImportResult getImportDataCache() {
		ApplicationCache cache = (ApplicationCache) SpringUtil.getBean(ApplicationCache.BEAN_ID);
		return (ImportResult) cache.getTemporaryCacheObject(getImportCacheKey());
	}

	public void clearImportDataCache() {
		ApplicationCache cache = (ApplicationCache) SpringUtil.getBean(ApplicationCache.BEAN_ID);
		cache.removeTemporaryCacheObject(getImportCacheKey());
	}
}
