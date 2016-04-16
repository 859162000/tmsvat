package com.ling2.dataimport.parse.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.Date;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.ling2.core.commons.utils.SpringUtil;
import com.ling2.dataimport.exception.InterceptorException;
import com.ling2.dataimport.interceptor.ICellDataInterceptor;
import com.ling2.dataimport.model.CellWrapper;
import com.ling2.dataimport.model.ExcelDataWrapper;
import com.ling2.dataimport.parse.ExcelParser;
import com.ling2.dataimport.processor.excelprocessor.IExcelProcessor;
import com.ling2.dataimport.service.IExcelModelService;

/**
 * @author matt.yao@bstek.com
 * @since 2.0
 */
public abstract class AbstractExcelParser implements ExcelParser {	
	
	/**
	 * excel支持的最大行数65535行
	 */
	public static final int MAX_EXCEL_ROW = 65535;
	/**
	 * excel支持的最大列数255列
	 */
	public static final int MAX_EXCEL_COLUMN = 255;
	
	public abstract IExcelModelService getExcelModelService();
	
	public Workbook createWorkbook(InputStream in) throws IOException, InvalidFormatException {
		Assert.notNull(in, "参数 InputStream 不能为空！");
		if (!in.markSupported()) {
			in = new PushbackInputStream(in, 8);
		}
		if (POIFSFileSystem.hasPOIFSHeader(in)) {
			return new HSSFWorkbook(in);
		}
		if (POIXMLDocument.hasOOXMLHeader(in)) {
			return new XSSFWorkbook(OPCPackage.open(in));
		}
		throw new RuntimeException("上传的不是正确的Excel格式文件，不能创建工作薄");
	}

	public void intercepterCellValue(Cell cell, CellWrapper cellWrapper, String interceptor) throws Exception {
		Object value=getCellValue(cell);
		cellWrapper.setValue(value);
//		logger.debug("before interceptor column number[" + cellWrapper.getColumn() + "] cell value[" + cellWrapper.getValue() + "] cell intercepter[" + interceptor + "]");
		if (StringUtils.hasText(interceptor)) {
			Object obj = null;
			try {
				obj = this.fireCellDataIntercepter(interceptor, cellWrapper.getValue());
			} catch (InterceptorException ex) {
				obj = ex.getMessage();
				cellWrapper.setValid(false);
			}
			if (cellWrapper.isValid()) {
				cellWrapper.setValue(obj);
			} else {
				cellWrapper.setValue("<font color=\"red\">" + obj + "</font>");
			}
		}
//		logger.debug("after interceptor  cell value[" + cellWrapper.getValue() + "]");

	}
	public Object getCellValue(Cell cell){
		if (cell == null) {
			return null;
		} else {
			int cellType = cell.getCellType();
			switch (cellType) {
			case Cell.CELL_TYPE_BLANK:
				return null;
			case Cell.CELL_TYPE_BOOLEAN:
				return Boolean.valueOf(cell.getBooleanCellValue());
			case Cell.CELL_TYPE_ERROR:
				return cell.getErrorCellValue();
			case Cell.CELL_TYPE_FORMULA:
				return cell.getCellFormula();
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					return date;
				} else {
					return cell.getNumericCellValue();
				}
			case Cell.CELL_TYPE_STRING:
				return cell.getStringCellValue();
			}
		}
		return null;
	}
	private Object fireCellDataIntercepter(String interceptor, Object cellValue) throws Exception {
		if (StringUtils.hasText(interceptor)) {
			if (SpringUtil.getContext().containsBean(interceptor)) {
				Object bean = SpringUtil.getContext().getBean(interceptor);
				if (bean instanceof ICellDataInterceptor) {
					ICellDataInterceptor cellDataInterceptor = (ICellDataInterceptor) bean;
					Object value = cellDataInterceptor.execute(cellValue);
					return value;
				} else {
					throw new RuntimeException("Spring Bean【" + interceptor + "】必须实现接口 com.ling2.importexcel.intercepter.ICellDataIntercepter");
				}
			} else {
				throw new RuntimeException("Spring Bean【" + interceptor + "】不存在！");
			}
		}
		return cellValue;
	}

	public int fireProcessorInterceptor(String interceptor, Object value) throws Exception {
		if (StringUtils.hasText(interceptor)) {
			if (SpringUtil.getContext().containsBean(interceptor)) {
				Object bean = SpringUtil.getContext().getBean(interceptor);
				if (bean instanceof IExcelProcessor && value instanceof ExcelDataWrapper) {
					IExcelProcessor processor = (IExcelProcessor) bean;
					ExcelDataWrapper excelDataWrapper = (ExcelDataWrapper) value;
					return processor.execute(excelDataWrapper);
				} else {
					throw new RuntimeException("Spring Bean【" + interceptor + "】必须实现接口 com.ling2.importexcel.processor.Processor");
				}
			} else {
				throw new RuntimeException("Spring Bean【" + interceptor + "】不存在！");
			}
		}
		return 0;
	}
}
