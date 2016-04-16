
/**
 * ClassName:ExcelReader.java
 * Author: wenbin.ji
 * CreateTime: Jan 28, 2011 11:16:29 AM
 * Description:Excel数据读取工具类，POI实现，兼容Excel2003，及Excel2007
 **/
package com.deloitte.tms.vat.salesinvoice.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
  Workbook wb = null;//工作薄
  List<String[]> dataList = new ArrayList<String[]>();//数据集合
  /**
   * 路径初始化
   * @param path
   */
  public ExcelReader(String path){//得到路径
    try {
      InputStream inp = new FileInputStream(path);//转化成文件流
      wb = WorkbookFactory.create(inp);//读取表格
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }  catch (IOException e) {
      e.printStackTrace();
    } catch (InvalidFormatException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
  }  
 /**
  * 文件流初始化
  * @param inp
  */
  public ExcelReader(InputStream inp){
	 try {
			wb = WorkbookFactory.create(inp);//读取工作薄
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
  }
  
  /**
   * 取Excel所有数据，包含header
   * @return  List<String[]>
   */
 public List<String[]> getAllData(){
	int numberSheet = wb.getNumberOfSheets();//得到所有工作表数量
	for(int i = 0;i<numberSheet;i++){//循环所有工作表
    Sheet sheet = wb.getSheetAt(i);//得到工作表
    int columnNum = 0;
    if(sheet.getRow(i)!=null){//判断工作表内容是否为空
        columnNum = sheet.getRow(i).getLastCellNum()-sheet.getRow(i).getFirstCellNum();//判断单元格数量
    }
    if(columnNum>0){//单元格数量大于0
    	int rowsNumber =sheet.getLastRowNum()+1;//行数
      for(int z = 1;z<rowsNumber;z++){ //循环取得一个工作表所有行
    	  Row row = sheet.getRow(z);
          String[] singleRow = new String[columnNum];//创建存放数据的数组长度为单元格
          int n = 0;
          int count = 0;
          for(int x=0;x<columnNum;x++){//循环取得单元格内容
             Cell cell = row.getCell(x);
             switch(cell.getCellType()){
               case Cell.CELL_TYPE_BLANK://判断是否为空白
            	   
            	   if(x==0||x==3||x==4||x==5){//判断必填项是否为空
            		   dataList.removeAll(dataList);//如果为空清空数据集合
            		   String erro = "请检查第"+(z+1)+"行,"+"第"+(x+1)+"列是否为空白";//提示信息
            		   dataList.add(new String[]{erro});//添加提示信息
            		   return dataList;//结束方法
            	   }
            	   
                 singleRow[n] = "";
                 break;
               case Cell.CELL_TYPE_BOOLEAN://判断单元格值是否为布尔
                 singleRow[n] = Boolean.toString(cell.getBooleanCellValue());
                 break;
                
               case Cell.CELL_TYPE_NUMERIC://判断单元格值是否为数值     
                 if(DateUtil.isCellDateFormatted(cell)){//判断是否为日期
                   singleRow[n] = String.valueOf(cell.getDateCellValue());
                 }else{ 
                   cell.setCellType(Cell.CELL_TYPE_STRING);//判断是否为String
                   String temp = cell.getStringCellValue();
                   //判断是否包含小数点，如果不含小数点，则以字符串读取，如果含小数点，则转换为Double类型的字符串
                
                   if(temp.indexOf(".")>-1){     
                     singleRow[n] = String.valueOf(new Double(temp)).trim();
                     if(x==5){//
                    	   count=2;  
                       }
                   }else{
                     singleRow[n] = temp.trim();
                     if(x==4){//判断第五列是否为数字
                  	   count=1;
                     }
                     if(x==5){//
                  	   count=2;  
                     }
                   }
                   
                 }
                 break;
               case Cell.CELL_TYPE_STRING://字符串
                 singleRow[n] = cell.getStringCellValue().trim();
                 break;
               case Cell.CELL_TYPE_ERROR://读取错误
                 singleRow[n] = "";
                 break;  
               case Cell.CELL_TYPE_FORMULA://公式类型
                 cell.setCellType(Cell.CELL_TYPE_STRING);
                 singleRow[n] = cell.getStringCellValue();
                 if(singleRow[n]!=null){
                   singleRow[n] = singleRow[n].replaceAll("#N/A","").trim();
                 }
                 break;  
               default://默认
                 singleRow[n] = "";
                 break;
             }
             if(x==4&&count!=1){
            	 dataList.removeAll(dataList);//如果为空清空数据集合
      		   String erro = "请检查第"+(z+1)+"行,"+"第"+(x+1)+"列是否为数字且不允许有小数点";//提示信息
      		   dataList.add(new String[]{erro});//添加提示信息
      		   return dataList;//结束方法 
             }
             if(x==5&&count!=2){
            	 dataList.removeAll(dataList);//如果为空清空数据集合
      		   String erro = "请检查第"+(z+1)+"行,"+"第"+(x+1)+"列是否为数字";//提示信息
      		   dataList.add(new String[]{erro});//添加提示信息
      		   return dataList;//结束方法 
             }
             n++;
        } 
          dataList.add(singleRow);
      }
    }
    
	}
    return dataList;
  }  
 
 
 
 
 
 
 
 
  /**
   * 返回Excel最大行index值，实际行数要加1
   * @return
   */
  public int getRowNum(int sheetIndex){
    Sheet sheet = wb.getSheetAt(sheetIndex);
    return sheet.getLastRowNum();
  }
  
  /**
   * 返回数据的列数
   * @return 
   */
  public int getColumnNum(int sheetIndex){
    Sheet sheet = wb.getSheetAt(sheetIndex);
    Row row = sheet.getRow(0);
    if(row!=null&&row.getLastCellNum()>0){
       return row.getLastCellNum();
    }
    return 0;
  }
  
  /**
   * 获取某一行数据
   * @param rowIndex 计数从0开始，rowIndex为0代表header行
   * @return
   */
    public String[] getRowData(int sheetIndex,int rowIndex){
      String[] dataArray = null;
      if(rowIndex>this.getColumnNum(sheetIndex)){
        return dataArray;
      }else{
        dataArray = new String[this.getColumnNum(sheetIndex)];
        return this.dataList.get(rowIndex);
      }
      
    }
  
  /**
   * 获取某一列数据
   * @param colIndex
   * @return
   */
  public String[] getColumnData(int sheetIndex,int colIndex){
    String[] dataArray = null;
    if(colIndex>this.getColumnNum(sheetIndex)){
      return dataArray;
    }else{   
      if(this.dataList!=null&&this.dataList.size()>0){
        dataArray = new String[this.getRowNum(sheetIndex)+1];
        int index = 0;
        for(String[] rowData:dataList){
          if(rowData!=null){
             dataArray[index] = rowData[colIndex];
             index++;
          }
        }
      }
    }
    return dataArray;
    
  }
 }
