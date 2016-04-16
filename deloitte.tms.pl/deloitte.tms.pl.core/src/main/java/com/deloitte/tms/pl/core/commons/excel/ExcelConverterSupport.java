package com.deloitte.tms.pl.core.commons.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import com.deloitte.tms.pl.core.commons.excel.support.RowConvertCallback;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@SuppressWarnings("unchecked")
public class ExcelConverterSupport implements ExcelConverterTemplate {
	
	public Collection convert(String filePath, RowConvertCallback action) throws BusinessException {
		Collection retval = new ArrayList();
		try {
			filePath = ClassLoader.getSystemResource("").getPath() + "persist3.xls";
			InputStream inputStream = new FileInputStream(filePath);
			Workbook workBook = Workbook.getWorkbook(inputStream);
			for (Sheet sheet : workBook.getSheets()) {
				int rowNum = sheet.getRows();
				
				for (int i = 1; i < rowNum; i++) {
					Cell[] cells = sheet.getRow(i);
					Object pojo = action.convert(cells);
					if (pojo != null) {
						retval.add(pojo);
					}
				}
			}
			
//			filePath = "/home" + filePath;
//			System.out.println("ffffffffffffffffffffffff: "     + filePath);
//			InputStream inputStream = new FileInputStream(filePath);
//
//			Workbook workBook = Workbook.getWorkbook(inputStream);
//			
//			for (Sheet sheet : workBook.getSheets()) {
//				int rowNum = sheet.getRows();
//				
//				for (int i = 1; i < rowNum; i++) {
//					Cell[] cells = sheet.getRow(i);
//					Object pojo = action.convert(cells);
//					if (pojo != null) {
//						retval.add(pojo);
//					}
//				}
//			}
		} catch (FileNotFoundException e) {
			throw new BusinessException("EXCEL 文件解析出错 ", e);
		} catch (BiffException e) {
			throw new BusinessException("EXCEL 文件解析出错！", e);
		} catch (IOException e) {
			throw new BusinessException("EXCEL 文件解析出错！", e);
		}
		return retval;
	}

}
