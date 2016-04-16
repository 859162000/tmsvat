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
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ling2.cache.ApplicationCache;
import com.ling2.core.commons.exception.BusinessException;
import com.ling2.core.commons.utils.AssertHelper;
import com.ling2.core.commons.utils.SpringUtil;
import com.ling2.core.context.utils.ContextUtils;
import com.ling2.dataimport.constant.ProcessTypeDef;
import com.ling2.dataimport.model.CellWrapper;
import com.ling2.dataimport.model.ExcelDataWrapper;
import com.ling2.dataimport.model.ExcelModel;
import com.ling2.dataimport.model.ExcelModelDetail;
import com.ling2.dataimport.model.GeneratePkStrategry;
import com.ling2.dataimport.model.ImportResult;
import com.ling2.dataimport.model.RowWrapper;
import com.ling2.dataimport.parse.ExcelParserCache;
import com.ling2.dataimport.processor.excelprocessor.ImportModelDetailProvider;
import com.ling2.dataimport.processor.importprocessor.ImportProcessor;
import com.ling2.dataimport.service.IExcelModelService;

/**
 * Excel解析处理
 * 
 * @author matt.yao@bstek.com
 * @since 2.0
 */
@Service(DefaultExcelParser.BEAN_ID)
public class DefaultExcelParser extends AbstractExcelParser implements ExcelParserCache {
	
	public static final String BEAN_ID = "excelParser";
	
	public static final String EXCEL_DATA_CACHE_KEY = "_excel_cache";

	@Resource(name = IExcelModelService.BEAN_ID)
	public IExcelModelService excelModelService;

	@Resource(name = ApplicationCache.BEAN_ID)
	public ApplicationCache applicationCache;
	
	ExcelDataWrapper excelDataWrapper = new ExcelDataWrapper();
	
	public void init(){
		excelDataWrapper=new ExcelDataWrapper();
		clearCacheExcelData();
	}

	public void parser(String excelModelId, int startRow, int endRow, InputStream in,Map parameter) throws Exception {
		init();
		excelDataWrapper.setExcelModelId(excelModelId);
		excelDataWrapper.setParameter(parameter);
		ExcelModel excelModel = excelModelService.findExcelModelById(excelModelId);
		List<ExcelModelDetail> excelModelDetails;
		if(excelModel==null)
		{
			throw new BusinessException("导入方案没有定义");
		}
		String modelDetailProvider=excelModel.getModelDetailProvider();
		if(AssertHelper.empty(modelDetailProvider)){
			excelModelDetails = excelModelService.findExcelModelDetailByModelId(excelModelId);
		}else{
			ImportModelDetailProvider provider=SpringUtil.getBean(modelDetailProvider);
			if(provider!=null){
				excelModelDetails = provider.getExcelModelDetails(excelModelId);
			}else{
				throw new BusinessException("导入方案中定义的modelDetailProvider没有找到:"+modelDetailProvider);
			}
		}
		excelModel.setListExcelModelDetail(excelModelDetails);
		String primarykey = excelModel.getPrimaryKey();
		Workbook workbook = this.createWorkbook(in);
		Sheet sheet = null;
		if (!StringUtils.hasText(excelModel.getExcelSheetName())) {
			sheet = workbook.getSheetAt(0);
		} else {
			sheet = workbook.getSheet(excelModel.getExcelSheetName());
		}
		if (sheet == null) {
			throw new RuntimeException("上传的excel没有解析到合法的sheet值！");
		}
		if (startRow == 0 || startRow < sheet.getFirstRowNum() + 1) {
			startRow = sheet.getFirstRowNum() + 1;
		}
		if (endRow == 0 || endRow > sheet.getLastRowNum() + 1) {
			endRow = sheet.getLastRowNum() + 1;
		}
		if (endRow > DefaultExcelParser.MAX_EXCEL_ROW) {
			endRow = DefaultExcelParser.MAX_EXCEL_ROW;
		}
		Collection<RowWrapper> rowWrapperCollection = new ArrayList<RowWrapper>();
		RowWrapper rowWrapper;
		for (int i = startRow - 1; i <= endRow - 1; i++) {
			Row row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			rowWrapper = new RowWrapper();
			rowWrapper.setTableLabel(excelModel.getTableLabel());
			rowWrapper.setTableName(excelModel.getTableName());
			rowWrapper.setRow(row.getRowNum() + 1);

			List<CellWrapper> cellWrapperList = new ArrayList<CellWrapper>();
			CellWrapper cellWrapper;
			for (ExcelModelDetail excelModelDetail : excelModelDetails) {
				//列支持只预览,不显示
				if(excelModelDetail.isProcess())
					{
					cellWrapper = new CellWrapper();
					int excelColumn = excelModelDetail.getExcelColumn();
					if (excelColumn == 0) {
						throw new RuntimeException("上传的excel工作表" + sheet.getSheetName() + "第" + excelColumn + "列不存在！");
					}
					String excelColumnTableName = excelModelDetail.getTableColumn();
					Cell cell = row.getCell(excelColumn - 1);
					if (cell == null) {
						cellWrapper.setColumn(excelColumn);
					} else {
						cellWrapper.setColumn(cell.getColumnIndex() + 1);
					}
					cellWrapper.setColumnName(excelModelDetail.getTableColumn());
					cellWrapper.setName(excelModelDetail.getName());
					cellWrapper.setValid(true);
					this.intercepterCellValue(cell, cellWrapper, excelModelDetail.getInterceptor());
					if (StringUtils.hasText(primarykey)) {
						if (primarykey.toLowerCase().equals(excelColumnTableName.toLowerCase()) && excelModel.getPrimaryKeyType().equals(GeneratePkStrategry.ASSIGNED.name())) {
							cellWrapper.setIsPrimaryKey(true);
							Object obj = cellWrapper.getValue();
							if (obj == null) {
								cellWrapper.setValue("<font color=\"red\">用户自定义主键不能为空!</font>");
								cellWrapper.setValid(false);
							}
						}
					} else {
						cellWrapper.setIsPrimaryKey(false);
					}
					cellWrapperList.add(cellWrapper);
				}
			}
			rowWrapper.setCellWrappers(cellWrapperList);
			rowWrapper.setValid(true);
			for (CellWrapper cw : cellWrapperList) {
				Boolean flag = cw.isValid();
				if (!flag) {
					rowWrapper.setValid(false);
					break;
				}
			}
			rowWrapperCollection.add(rowWrapper);
		}
		excelDataWrapper.setExcelModel(excelModel);
		excelDataWrapper.setRowWrappers(rowWrapperCollection);
		excelDataWrapper.setValidate(true);
		for (RowWrapper row : rowWrapperCollection) {
			if (row.getCellWrappers().size() == 0) {
				//如果没有一个选择需要处理的话这里会报错
//				excelDataWrapper.setValidate(false);
			} else {
				Boolean flag = row.isValid();
				if (!flag) {
					excelDataWrapper.setValidate(false);
					break;
				}
			}
		}
		String excelDataWrapperProcessor;
		//CLASSPROCESS修改excelDataWrapper的处理程序,保留内部处理程序
		if(ProcessTypeDef.CLASSPROCESS.equals(excelModel.getProcessorType()))
		{
			excelDataWrapperProcessor=CLASS_SPRING_EXCEL_PROCESSOR;
			ImportProcessor importProcessor=SpringUtil.getBean(excelModel.getProcessor());
			ImportResult datas=importProcessor.getExcelData(excelDataWrapper);
			importProcessor.saveImportData2Cache(datas);
		}else{
			//数据库处理方式
			if (!StringUtils.hasText(excelModel.getProcessor()) && StringUtils.hasText(excelModel.getDatasourceName())) {
				excelModel.setProcessor(this.getDefaultProcessor());
				excelDataWrapperProcessor=this.getDefaultProcessor();
			}			
			else{//自定义处理方式
				excelDataWrapperProcessor=excelModel.getProcessor();
			}
		}
		excelDataWrapper.setProcessor(excelDataWrapperProcessor);
		saveExcelData2Cache();
	}

	public String getDefaultProcessor() {
		return DefaultExcelParser.DEFAULT_SPRING_EXCEL_PROCESSOR;
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
		cache.putTemporaryCacheObject(getExcelCacheKey(), excelDataWrapper);
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
}
