package com.ling2.dataimport.parse.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.StringUtils;

import com.ling2.cache.ApplicationCache;
import com.ling2.core.commons.utils.SpringUtil;
import com.ling2.core.context.utils.ContextUtils;
import com.ling2.dataimport.model.ExcelDataWrapper;
import com.ling2.dataimport.model.GeneratePkStrategry;
import com.ling2.dataimport.parse.ExcelParserCache;
import com.ling2.dataimport.service.IExcelModelService;

/**
 * Excel解析处理
 * 
 * @author matt.yao@bstek.com
 * @since 2.0
 */
public class TwoSheetExcelParser extends AbstractExcelParser implements ExcelParserCache {
	
	public static final String BEAN_ID = "trprojectitemexcelParser";
	
	public static final String EXCEL_DATA_CACHE_KEY = "tr_projectitem_excel_cache";

	@Resource(name = IExcelModelService.BEAN_ID)
	public IExcelModelService excelModelService;

	@Resource(name = ApplicationCache.BEAN_ID)
	public ApplicationCache applicationCache;
	
	Result cachedata = new Result();
	
	public void init(){
		cachedata=new Result();
		clearCacheExcelData();
	}

	public void parser(String excelModelId, int startRow, int endRow, InputStream in,Map parameter) throws Exception {
		init();
		Workbook workbook = this.createWorkbook(in);
		Sheet sheet1 = workbook.getSheetAt(0);
		Sheet sheet2 = workbook.getSheetAt(1);

		if (startRow == 0 || startRow < sheet1.getFirstRowNum() + 1) {
			startRow = sheet1.getFirstRowNum() + 1;
		}
		if (endRow == 0 || endRow > sheet1.getLastRowNum() + 1) {
			endRow = sheet1.getLastRowNum() + 1;
		}
		if (endRow > TwoSheetExcelParser.MAX_EXCEL_ROW) {
			endRow = TwoSheetExcelParser.MAX_EXCEL_ROW;
		}		
		
		for (int i = startRow - 1; i <= endRow - 1; i++) {
			Row row = sheet1.getRow(i);
			if (row == null) {
				continue;
			}
			Cell cell1 = row.getCell(1);
			Object name=getCellValue(cell1);
			Group group=new Group();
			cachedata.getGroups().add(group);
		}
		for (int i = startRow - 1; i <= endRow - 1; i++) {
			Row row = sheet2.getRow(i);
			if (row == null) {
				continue;
			}
			Entity entity=new Entity();
			cachedata.getEntities().add(entity);
		}
		saveExcelData2Cache();
	}

	public String getDefaultProcessor() {
		return TwoSheetExcelParser.DEFAULT_SPRING_EXCEL_PROCESSOR;
	}

	@Override
	public IExcelModelService getExcelModelService() {
		return excelModelService;
	}
	public int processParserdExcelData() throws Exception {
		int i = 0;
		ApplicationCache applicationCache = (ApplicationCache) SpringUtil.getBean(ApplicationCache.BEAN_ID);
		ExcelDataWrapper excelDataWrapper = (ExcelDataWrapper) applicationCache.getTemporaryCacheObject(getExcelCacheKey());
		if (excelDataWrapper != null) {
			String processor = excelDataWrapper.getProcessor();
			if (StringUtils.hasText(processor)) {
				if (excelDataWrapper.getRowWrappers().size() > 0) {
					if (excelDataWrapper.validate) {
						i = fireProcessorInterceptor(processor, excelDataWrapper);
						applicationCache.removeTemporaryCacheObject(getExcelCacheKey());
					} else {
						throw new RuntimeException("导入的Excel数据没有通过验证！");
					}
				} else {
					throw new RuntimeException("导入的Excel数据没有有效的记录！");
				}

			} else {
				throw new RuntimeException("解析的Excel数据没有对应的处理器！");
			}
		} else {
			throw new RuntimeException("缓存数据不存在！");
		}
		return i;
	}

	public String getExcelCacheKey() {
		return ContextUtils.getCurrentUser().getUsername() + EXCEL_DATA_CACHE_KEY;
	}

	public void saveExcelData2Cache() {
		ApplicationCache cache = (ApplicationCache) SpringUtil.getBean(ApplicationCache.BEAN_ID);
		cache.putTemporaryCacheObject(getExcelCacheKey(), cachedata);
	}

	public ExcelDataWrapper getCacheExcelData() {
		ApplicationCache cache = (ApplicationCache) SpringUtil.getBean(ApplicationCache.BEAN_ID);
		return (ExcelDataWrapper) cache.getTemporaryCacheObject(getExcelCacheKey());
	}

	public void clearCacheExcelData() {
		ApplicationCache cache = (ApplicationCache) SpringUtil.getBean(ApplicationCache.BEAN_ID);
		cache.removeTemporaryCacheObject(getExcelCacheKey());
	}

	public Collection<String> getPrimaryTypes() {
		List<String> list = new ArrayList<String>();
		GeneratePkStrategry[] values = GeneratePkStrategry.values();
		for (GeneratePkStrategry g : values) {
			list.add(g.name());
		}
		return list;
	}
	class Result{
		
		List<Group> groups=new ArrayList<TwoSheetExcelParser.Group>();
		List<Entity> entities=new ArrayList<TwoSheetExcelParser.Entity>();
		public List<Group> getGroups() {
			return groups;
		}
		public void setGroups(List<Group> groups) {
			this.groups = groups;
		}
		public List<Entity> getEntities() {
			return entities;
		}
		public void setEntities(List<Entity> entities) {
			this.entities = entities;
		}
		
	}
	class Group{
		
	}
	class Entity{
		
	}
}
