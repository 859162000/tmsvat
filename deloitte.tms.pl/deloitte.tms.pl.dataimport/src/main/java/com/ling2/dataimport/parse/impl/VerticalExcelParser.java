//package com.ling2.dataimport.parse.impl;
//
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import com.ling2.cache.ApplicationCache;
//import com.ling2.core.commons.exception.BusinessException;
//import com.ling2.core.commons.utils.AssertHelper;
//import com.ling2.core.commons.utils.SpringUtil;
//import com.ling2.dataimport.constant.ProcessTypeDef;
//import com.ling2.dataimport.model.CellWrapper;
//import com.ling2.dataimport.model.ExcelDataWrapper;
//import com.ling2.dataimport.model.ExcelModel;
//import com.ling2.dataimport.model.ExcelModelDetail;
//import com.ling2.dataimport.model.GeneratePkStrategry;
//import com.ling2.dataimport.model.RowWrapper;
//import com.ling2.dataimport.parse.IExcelParser;
//import com.ling2.dataimport.processor.excelprocessor.ImportModelDetailProvider;
//import com.ling2.dataimport.processor.importprocessor.ImportProcessor;
//import com.ling2.dataimport.service.IExcelModelService;
//
///**
// * Excel解析处理
// * 
// * @author matt.yao@bstek.com
// * @since 2.0
// */
////@Service(IExcelParser.BEAN_ID)
//public class VerticalExcelParser extends AbstractExcelParser {
//	/**
//	 * excel支持的最大行数65535行
//	 */
//	public static final int MAX_EXCEL_ROW = 65535;
//	/**
//	 * excel支持的最大列数255列
//	 */
//	public static final int MAX_EXCEL_COLUMN = 255;
//
//	@Resource(name = IExcelModelService.BEAN_ID)
//	public IExcelModelService excelModelService;
//
//	@Resource(name = ApplicationCache.BEAN_ID)
//	public ApplicationCache applicationCache;
//
//	public ExcelDataWrapper parser(String excelModelId, int startRow, int endRow, InputStream in) throws Exception {
//		ExcelDataWrapper excelDataWrapper = new ExcelDataWrapper();
//		excelDataWrapper.setExcelModelId(excelModelId);
//		ExcelModel excelModel = excelModelService.findExcelModelById(excelModelId);
//		List<ExcelModelDetail> excelModelDetails;
//		if(excelModel==null)
//		{
//			throw new BusinessException("导入方案没有定义");
//		}
//		String modelDetailProvider=excelModel.getModelDetailProvider();
//		if(AssertHelper.empty(modelDetailProvider)){
//			excelModelDetails = excelModelService.findExcelModelDetailByModelId(excelModelId);
//		}else{
//			ImportModelDetailProvider provider=SpringUtil.getBean(modelDetailProvider);
//			if(provider!=null){
//				excelModelDetails = provider.getExcelModelDetails(excelModelId);
//			}else{
//				throw new BusinessException("导入方案中定义的modelDetailProvider没有找到:"+modelDetailProvider);
//			}
//		}
//		excelModel.setListExcelModelDetail(excelModelDetails);
//		String primarykey = excelModel.getPrimaryKey();
//		Workbook workbook = this.createWorkbook(in);
//		Sheet sheet = null;
//		if (!StringUtils.hasText(excelModel.getExcelSheetName())) {
//			sheet = workbook.getSheetAt(0);
//		} else {
//			sheet = workbook.getSheet(excelModel.getExcelSheetName());
//		}
//		if (sheet == null) {
//			throw new RuntimeException("上传的excel没有解析到合法的sheet值！");
//		}
//		if (startRow == 0 || startRow < sheet.getFirstRowNum() + 1) {
//			startRow = sheet.getFirstRowNum() + 1;
//		}
//		if (endRow == 0 || endRow > sheet.getLastRowNum() + 1) {
//			endRow = sheet.getLastRowNum() + 1;
//		}
//		if (endRow > VerticalExcelParser.MAX_EXCEL_ROW) {
//			endRow = VerticalExcelParser.MAX_EXCEL_ROW;
//		}
//		Collection<RowWrapper> rowWrapperCollection = new ArrayList<RowWrapper>();
//		RowWrapper rowWrapper;
//		for (ExcelModelDetail detail:excelModelDetails) {
//			//行
//			List<CellWrapper> cellWrapperList = new ArrayList<CellWrapper>();
//			CellWrapper cellWrapper;
//			if(detail.isProcess()){
//				Row row = sheet.getRow(detail.getExcelColumn());
//				if (row == null) {
//					continue;
//				}
//				rowWrapper = new RowWrapper();
//				rowWrapper.setTableLabel(excelModel.getTableLabel());
//				rowWrapper.setTableName(excelModel.getTableName());
//				rowWrapper.setRow(row.getRowNum() + 1);
//				
//				cellWrapper = new CellWrapper();
//				//只取值
//				Integer excelColumn=3;
//				Cell cell = row.getCell(excelColumn - 1);
//				if (cell == null) {
//					cellWrapper.setColumn(excelColumn);
//				} else {
//					cellWrapper.setColumn(cell.getColumnIndex() + 1);
//				}
//				this.intercepterCellValue(cell, cellWrapper, detail.getInterceptor());
//				Object obj = cellWrapper.getValue();
//				if (obj == null) {
//					cellWrapper.setValue("<font color=\"red\">用户自定义主键不能为空!</font>");
//					cellWrapper.setValid(false);
//				}
//				cellWrapperList.add(cellWrapper);
//				rowWrapperCollection.add(rowWrapper);
//			}			
//		}
//		
//		excelDataWrapper.setExcelModel(excelModel);
//		excelDataWrapper.setRowWrappers(rowWrapperCollection);
//		excelDataWrapper.setValidate(true);
//		for (RowWrapper row : rowWrapperCollection) {
//			if (row.getCellWrappers().size() == 0) {
//				//如果没有一个选择需要处理的话这里会报错
////				excelDataWrapper.setValidate(false);
//			} else {
//				Boolean flag = row.isValid();
//				if (!flag) {
//					excelDataWrapper.setValidate(false);
//					break;
//				}
//			}
//		}
//		String excelDataWrapperProcessor;
//		//CLASSPROCESS修改excelDataWrapper的处理程序,保留内部处理程序
//		if(ProcessTypeDef.CLASSPROCESS.equals(excelModel.getProcessorType()))
//		{
//			excelDataWrapperProcessor=CLASS_SPRING_EXCEL_PROCESSOR;
//			ImportProcessor importProcessor=SpringUtil.getBean(excelModel.getProcessor());
//			List datas=importProcessor.getExcelData(excelDataWrapper);
//			importProcessor.saveImportData2Cache(datas);
//		}else{
//			//数据库处理方式
//			if (!StringUtils.hasText(excelModel.getProcessor()) && StringUtils.hasText(excelModel.getDatasourceName())) {
//				excelModel.setProcessor(this.getDefaultProcessor());
//				excelDataWrapperProcessor=this.getDefaultProcessor();
//			}			
//			else{//自定义处理方式
//				excelDataWrapperProcessor=excelModel.getProcessor();
//			}
//		}
//		excelDataWrapper.setProcessor(excelDataWrapperProcessor);
//		return excelDataWrapper;
//	}
//
//	public String getDefaultProcessor() {
//		return VerticalExcelParser.DEFAULT_SPRING_EXCEL_PROCESSOR;
//	}
//
//	@Override
//	public IExcelModelService getExcelModelService() {
//		return excelModelService;
//	}
//}
