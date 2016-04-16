package com.deloitte.tms.pl.core.commons.utils;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

public class ExcelToHtmlFilesUtils {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		FileInputStream inp;
		try {
//			String testfile="d:\\work\\Desktop\\TR\\工作底稿\\营业外支出底稿.xlsm";
//			String testfile="d:\\work\\Desktop\\TR\\工作底稿\\Checklist 0926.xlsx";
//			String testfile="d:\\work\\Desktop\\TR\\工作底稿\\模板-汇总-CN.xls";
			String testfile="d:\\work\\Desktop\\TR\\工作底稿\\Copy of FY201405C TR by level by service line by office-V5.xlsx";
			String storefolder="d:\\work\\Desktop\\TR\\test";
			inp = new FileInputStream(testfile);
			HSSFWorkbook workbook = new HSSFWorkbook(inp);
			for (int s = 0; s < workbook.getNumberOfSheets(); s++) {
				HSSFSheet sheet = workbook.getSheetAt(s);
	            String content=ExcelToHtmlFilesUtils.ConvertToHtml(sheet,workbook);
	            System.out.println(content);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	/**
	 * Excel Sheet转换为html
	 * @param sheet
	 * @param workbook 
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private static String ConvertToHtml(HSSFSheet sheet, HSSFWorkbook workbook) throws UnsupportedEncodingException {
		int row = sheet.getLastRowNum();
		HSSFRow _row = sheet.getRow(0);
		int col = _row.getLastCellNum();
		System.out.println(row);
		System.out.println(col);
		String[][][] tdinfo = new String[row][col][2];
		Map style = new HashMap();
		// 合并单元格
		for(int i=0;i<sheet.getNumMergedRegions();i++){
			Region m = sheet.getMergedRegionAt(i);
			int rs = m.getRowFrom();
			int re = m.getRowTo();
			int cs = m.getColumnFrom();
			int ce = m.getColumnTo();
			tdinfo[rs][cs][0] = "";
			if(re > rs){
				tdinfo[rs][cs][0] += " rowspan='"+(re-rs+1)+"'";
			}
			if(ce>cs){
				tdinfo[rs][cs][0] += " colspan='"+(ce-cs+1)+"'";
			}
			for(int x=rs;x<=re;x++){
				for(int y=cs;y<=ce;y++){
					if(x!=rs || y!=cs){
						tdinfo[x][y] = null;
					}
				}
			}
		}
		float[] width = new float[col];
		int widthsum = 0;
		int max = 0;
		// 列宽
		for(int i=0;i<col;i++){
			width[i] = sheet.getColumnWidth((short) i);
			if(width[i]>=width[max]){
				max = i;
			}
			widthsum += width[i];
		}
		// 最宽的一列不指定宽度
		width[max] = 0;
		// 设置单元格内容
		for(int i=0;i<row;i++){
			_row = sheet.getRow(i);
			for(int j=0;j<col;j++){
				if(tdinfo[i][j] == null){
					continue;
				}
				HSSFCell cell = _row.getCell((short) j);
				if(cell != null){
					HSSFCellStyle s = cell.getCellStyle();
					if(tdinfo[i][j][0] == null){
						tdinfo[i][j][0] = "";
					}
					// 设置单元格的样式
					tdinfo[i][j][0] += " class='"+getCssByStyle(s,workbook,style)+"'";
					// 设置单元格的值
					if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
						tdinfo[i][j][1] = cell.getStringCellValue();
					}else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
						tdinfo[i][j][1] = cell.getNumericCellValue()+"";
					}
				}else{
					tdinfo[i][j] = null;
				}
			}
		}
				
		StringBuffer br = new StringBuffer();
		br.append("<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>");
		br.append("<head><meta http-equiv=Content-Type content='text/html; charset=utf-8'><meta name=ProgId content=Excel.Sheet>");
		br.append("<style>");
		Iterator it = style.values().iterator();
		while(it.hasNext()){
			String[] css = (String[])it.next();
			br.append(css[1]);
		}
		br.append("</style></head><body>");
		br.append("<table cellspacing='0' cellpadding='0' style='border-collapse:collapse;'>");
		// 设置单元格的宽度
		for(int i=0;i<col;i++){
			if(i != max){
				br.append("<col width='"+Math.rint(width[i]/widthsum*100)+"%'>");
			}else{
				br.append("<col>");
			}
		}
		for(int i=0;i<row;i++){
			br.append("<tr>");
			for(int j=0;j<col;j++){
				if(tdinfo[i][j] != null){
					if(tdinfo[i][j][0] == null){
						tdinfo[i][j][0] = "";
					}
					if(tdinfo[i][j][1]==null){
						tdinfo[i][j][1] = " ";
					}
					br.append("<td "+tdinfo[i][j][0]+">"+tdinfo[i][j][1]+"</td>");
				}
			}
			br.append("</tr>");
		}
		br.append("</table></body></html>");
		return br.toString();
	}
	private static String getCssByStyle(HSSFCellStyle s, HSSFWorkbook workbook,Map style) {
		if(style.containsKey(s)){
			String[] css = (String[])style.get(s);
			return css[0];
		}else{
			String[] css = new String[2];
			css[0] = "c"+style.size();
			StringBuffer cssinfo = new StringBuffer();
			// 文字对齐方式
			switch(s.getAlignment()){
				case HSSFCellStyle.ALIGN_CENTER:
					cssinfo.append("text-align:center;");break;
				case HSSFCellStyle.ALIGN_LEFT:
					cssinfo.append("text-align:left;");break;
				case HSSFCellStyle.ALIGN_RIGHT:
					cssinfo.append("text-align:right;");break;
			}
			// 背景色
			cssinfo.append("background-color:"+getColor(s.getFillForegroundColor())+";");
			// 设置边框
			cssinfo.append("border-top:"+s.getBorderTop()+"px solid #000000;");
			cssinfo.append("border-left:"+s.getBorderLeft()+"px solid #000000;");
			cssinfo.append("border-right:"+s.getBorderRight()+"px solid #000000;");
			cssinfo.append("border-bottom:"+s.getBorderBottom()+"px solid #000000;");
			// 设置字体
			HSSFFont font = workbook.getFontAt(s.getFontIndex());
			cssinfo.append("font-size:"+font.getFontHeightInPoints()+"pt;");
			if(HSSFFont.BOLDWEIGHT_BOLD == font.getBoldweight()){
				cssinfo.append("font-weight: bold;");
			}
			cssinfo.append("font-family: "+font.getFontName()+";");
			if(font.getItalic()){
				cssinfo.append("font-style: italic;");
			}
			String fontcolor = getColor(font.getColor());{
				if(fontcolor.trim().length() > 0){
					cssinfo.append("color: "+fontcolor+";");
				}
			}
			css[1] = "."+css[0]+"{"+cssinfo.toString()+"}";
			style.put(s, css);
			return css[0];
		}
	}
	/**
	 * 取RGB颜色
	 * @param c
	 * @return
	 */
	private static String getColor(short c){
		if(c<8||c>63){
			return "";
		}
		String color = ((HSSFColor)HSSFColor.getIndexHash().get(new Integer(c))).getHexString();
		String[] cs = color.split(":");
		color = "#";
		for(int j=0;j<cs.length;j++){
			if(cs[j].length()==1){
				color+=cs[j]+cs[j];
			}else if(cs[j].length()==4){
				color+=cs[j].substring(2);
			}else{
				color+=cs[j];
			}
		}
		return color;
	}
	

}
