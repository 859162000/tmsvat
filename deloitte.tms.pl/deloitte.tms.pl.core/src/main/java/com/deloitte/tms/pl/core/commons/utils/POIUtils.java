package com.deloitte.tms.pl.core.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.Region;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class POIUtils {
	public static void main(String[] args) {
		String fromfile="d:\\work\\Desktop\\tr\\限额扣除计算模板_20141118_linda.xlsx";
		String tofile="d:\\work\\Desktop\\tr\\result.xlsx";
		try {
			FileInputStream tin=new FileInputStream(fromfile);
			Workbook wb=new XSSFWorkbook(tin);
			for(int i=0;i<wb.getNumberOfSheets();i++){
				Sheet sheet=wb.getSheetAt(i);
				for (Iterator rowIt = sheet.rowIterator(); rowIt.hasNext();) {  
					XSSFRow tmpRow = (XSSFRow) rowIt.next();  
		            for (Iterator cellIt = tmpRow.cellIterator(); cellIt.hasNext();) {  
		                XSSFCell tmpCell = (XSSFCell) cellIt.next();  
		                // 不同数据类型处理  
		                int cellType = tmpCell.getCellType();  
		                if (cellType == XSSFCell.CELL_TYPE_STRING) {  
		                	tmpCell.setCellValue("1111");  
	                    }   
		            }  
		        }  
			}
			File targetfile=new File(tofile);
			FileOutputStream outputStream=new FileOutputStream(targetfile);
			wb.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	/**
//	 * 把一个excel中的cellstyletable复制到另一个excel，这里会报错，不能用这种方法，不明白呀？？？？？
//	 * @param fromBook
//	 * @param toBook
//	 */
//	public static void copyBookCellStyle(HSSFWorkbook fromBook,HSSFWorkbook toBook){
//		for(short i=0;i<fromBook.getNumCellStyles();i++){
//			HSSFCellStyle fromStyle=fromBook.getCellStyleAt(i);
//			HSSFCellStyle toStyle=toBook.getCellStyleAt(i);
//			if(toStyle==null){
//				toStyle=toBook.createCellStyle();
//			}
//			copyCellStyle(fromStyle,toStyle);
//		}
//	}
	/**
	 * 复制一个单元格样式到目的单元格样式
	 * @param fromStyle
	 * @param toStyle
	 */
	public static void copyCellStyle(HSSFCellStyle fromStyle,
			HSSFCellStyle toStyle) {
		toStyle.setAlignment(fromStyle.getAlignment());
		//边框和边框颜色
		toStyle.setBorderBottom(fromStyle.getBorderBottom());
		toStyle.setBorderLeft(fromStyle.getBorderLeft());
		toStyle.setBorderRight(fromStyle.getBorderRight());
		toStyle.setBorderTop(fromStyle.getBorderTop());
		toStyle.setTopBorderColor(fromStyle.getTopBorderColor());
		toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());
		toStyle.setRightBorderColor(fromStyle.getRightBorderColor());
		toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor());
		
		//背景和前景
		toStyle.setFillBackgroundColor(fromStyle.getFillBackgroundColor());
		toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());
		
		toStyle.setDataFormat(fromStyle.getDataFormat());
		toStyle.setFillPattern(fromStyle.getFillPattern());
//		toStyle.setFont(fromStyle.getFont(null));
		toStyle.setHidden(fromStyle.getHidden());
		toStyle.setIndention(fromStyle.getIndention());//首行缩进
		toStyle.setLocked(fromStyle.getLocked());
		toStyle.setRotation(fromStyle.getRotation());//旋转
		toStyle.setVerticalAlignment(fromStyle.getVerticalAlignment());
		toStyle.setWrapText(fromStyle.getWrapText());
		
	}
	/**
	 * Sheet复制
	 * @param fromSheet
	 * @param toSheet
	 * @param copyValueFlag
	 */
	public static void copySheet(HSSFWorkbook wb,HSSFSheet fromSheet, HSSFSheet toSheet,
			boolean copyValueFlag) {
		//合并区域处理
		mergerRegion(fromSheet, toSheet);
		for (Iterator rowIt = fromSheet.rowIterator(); rowIt.hasNext();) {
			HSSFRow tmpRow = (HSSFRow) rowIt.next();
			HSSFRow newRow = toSheet.createRow(tmpRow.getRowNum());
			//行复制
			copyRow(wb,tmpRow,newRow,copyValueFlag);
		}
	}
	/**
	 * 行复制功能
	 * @param fromRow
	 * @param toRow
	 */
	public static void copyRow(HSSFWorkbook wb,HSSFRow fromRow,HSSFRow toRow,boolean copyValueFlag){
		for (Iterator cellIt = fromRow.cellIterator(); cellIt.hasNext();) {
			HSSFCell tmpCell = (HSSFCell) cellIt.next();
			HSSFCell newCell = toRow.createCell(tmpCell.getCellNum());
			copyCell(wb,tmpCell, newCell, copyValueFlag);
		}
	}
	/**
	* 复制原有sheet的合并单元格到新创建的sheet
	* 
	* @param sheetCreat 新创建sheet
	* @param sheet      原有的sheet
	*/
	public static void mergerRegion(HSSFSheet fromSheet, HSSFSheet toSheet) {
	   int sheetMergerCount = fromSheet.getNumMergedRegions();
	   for (int i = 0; i < sheetMergerCount; i++) {
	    Region mergedRegionAt = fromSheet.getMergedRegionAt(i);
	    toSheet.addMergedRegion(mergedRegionAt);
	   }
	}
	/**
	 * 复制单元格
	 * 
	 * @param srcCell
	 * @param distCell
	 * @param copyValueFlag
	 *            true则连同cell的内容一起复制
	 */
	public static void copyCell(HSSFWorkbook wb,HSSFCell srcCell, HSSFCell distCell,
			boolean copyValueFlag) {
		HSSFCellStyle newstyle=wb.createCellStyle();
		copyCellStyle(srcCell.getCellStyle(), newstyle);
//		distCell.setEncoding(srcCell.getEncoding());
		//样式
		distCell.setCellStyle(newstyle);
		//评论
		if (srcCell.getCellComment() != null) {
			distCell.setCellComment(srcCell.getCellComment());
		}
		// 不同数据类型处理
		int srcCellType = srcCell.getCellType();
		distCell.setCellType(srcCellType);
		if (copyValueFlag) {
			if (srcCellType == HSSFCell.CELL_TYPE_NUMERIC) {
				if (HSSFDateUtil.isCellDateFormatted(srcCell)) {
					distCell.setCellValue(srcCell.getDateCellValue());
				} else {
					distCell.setCellValue(srcCell.getNumericCellValue());
				}
			} else if (srcCellType == HSSFCell.CELL_TYPE_STRING) {
				distCell.setCellValue(srcCell.getRichStringCellValue());
			} else if (srcCellType == HSSFCell.CELL_TYPE_BLANK) {
				// nothing21
			} else if (srcCellType == HSSFCell.CELL_TYPE_BOOLEAN) {
				distCell.setCellValue(srcCell.getBooleanCellValue());
			} else if (srcCellType == HSSFCell.CELL_TYPE_ERROR) {
				distCell.setCellErrorValue(srcCell.getErrorCellValue());
			} else if (srcCellType == HSSFCell.CELL_TYPE_FORMULA) {
				distCell.setCellFormula(srcCell.getCellFormula());
			} else { // nothing29
			}
		}
	}

	public boolean Print() {
		try {
			int intPage = 0;
			FileInputStream ExcelFile = new FileInputStream("hoge.xls ");
			POIFSFileSystem fs = new POIFSFileSystem(ExcelFile);
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			HSSFSheet newSheet = workbook.getSheetAt(0);
			// 复制源page 不支持图片和JExcelApi
			workbook.cloneSheet(intPage);
			// 设定sheet名
			workbook.setSheetName(intPage, String.valueOf(intPage + 1));
			// 选择目标sheet
			workbook.getSheetAt(intPage).setSelected(true);
			newSheet = workbook.getSheetAt(intPage);
			// 合并单元格
			setMerge(newSheet);
			// 设定单元格值
			setCellValue(newSheet, 1, 2, "ほげ ");
			// 删除模板页
			workbook.removeSheetAt(intPage + 1);
			// 写入新文件
			FileOutputStream out = new FileOutputStream("hogenew.xls ");
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 合并单元格
	private void setMerge(HSSFSheet newSheet) {
		// 标题Row1Col2到Row1Col4合并
		newSheet.addMergedRegion(new Region(1 - 1, (short) (2 - 1), 1 - 1,
				(short) (4 - 1)));
	}

	// 设定值以下函数为重载
	private void setCellValue(HSSFSheet newSheet, int Row, int Col, String value) {
		if (value == null) {
			value = "   ";
		}
//		newSheet.getRow(Row - 1).getCell((short) (Col - 1))
//				.setEncoding(HSSFCell.ENCODING_UTF_16); // 日本語用
		newSheet.getRow(Row - 1).getCell((short) (Col - 1)).setCellValue(value);
	}

	private void setCellValue(HSSFSheet newSheet, int Row, int Col, int value) {
		newSheet.getRow(Row - 1).getCell((short) (Col - 1)).setCellValue(value);
	}

	private void setCellValue(HSSFSheet newSheet, int Row, int Col, long value) {
		newSheet.getRow(Row - 1).getCell((short) (Col - 1)).setCellValue(value);
	}

	private void setCellValue(HSSFSheet newSheet, int Row, int Col, float value) {
		newSheet.getRow(Row - 1).getCell((short) (Col - 1)).setCellValue(value);
	}
}