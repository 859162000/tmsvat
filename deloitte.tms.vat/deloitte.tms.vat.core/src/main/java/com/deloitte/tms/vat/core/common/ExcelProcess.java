package com.deloitte.tms.vat.core.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;



public class ExcelProcess {
	
  public Integer sheetIndexDefault=0;
	
  public Workbook importWorkbook = null;
  
  
  public List<String[]> dataList = new ArrayList<String[]>();
  
  public static ArrayList<String> excelHeaderFieldsByDb = new ArrayList<String>();
  
  public static ArrayList<String> excelHeaderFields = new ArrayList<String>();
  
  static {
	 // excelHeaderFields.add("发票系统");  / 不是导入的
	  excelHeaderFields.add("验证日期");
	  excelHeaderFields.add("发票代号");
	  excelHeaderFields.add("发票票号");
	  excelHeaderFields.add("纳税人识别号");
	  excelHeaderFields.add("开票日期");
	//含税金额 改成 净额  excelHeaderFields.add("含税金额");
	 // excelHeaderFields.add("净额");
	  
	  excelHeaderFields.add("含税金额"); //attribute6
	  
	  excelHeaderFields.add("税金");
	  excelHeaderFields.add("纳税人名称");
	  excelHeaderFields.add("公司代码");
	  excelHeaderFields.add("发票验证人");
	 // excelHeaderFields.add("发票状态");   / 不是导入的
	 // excelHeaderFields.add("认证失败原因");   / 不是导入的
	  
	  //-----------------------------
	  

		 excelHeaderFieldsByDb.add("attribute1"); //验证日期
		 excelHeaderFieldsByDb.add("invoiceCode"); //发票代号
		 excelHeaderFieldsByDb.add("invoiceNumber"); //发票票号
		 excelHeaderFieldsByDb.add("venderRegistrationNumber"); //纳税人识别号
		 excelHeaderFieldsByDb.add("invoicingDate"); //开票日期
		 //excelHeaderFieldsByDb.add("vatAmount + enteredAmount");enteredAmount
		 
		 //excelHeaderFieldsByDb.add("enteredAmount");//含税金额(enteredAmount+vatAmount) 改成 净额(enteredAmount)
		 
		 excelHeaderFieldsByDb.add("attribute6");//含税金额 attribute6
		 
		 excelHeaderFieldsByDb.add("vatAmount"); //税金
		 excelHeaderFieldsByDb.add("venderName"); //纳税人名称
		 excelHeaderFieldsByDb.add("attribute3"); //公司代码
		 excelHeaderFieldsByDb.add("attribute4"); //发票验证人
		 //excelHeaderFieldsByDb.add("invoiceAuthenticationStatus");
			
		 /**
			 *  static {
		 // excelHeaderFields.add("发票系统"); attribute2     / 不是导入的
		   excelHeaderFields.add("验证日期");   attribute1      / Date
		  excelHeaderFields.add("发票代号");  invoiceCode
		  excelHeaderFields.add("发票票号");   invoiceNumber
		  excelHeaderFields.add("纳税人识别号"); venderRegistrationNumber
		  excelHeaderFields.add("开票日期");    invoicingDate   /  Date
		  excelHeaderFields.add("含税金额");      vatAmount + enteredAmount
		  excelHeaderFields.add("税金");           vatAmount
		  excelHeaderFields.add("纳税人名称");         venderName
		  excelHeaderFields.add("公司代码");             attribute3
		  excelHeaderFields.add("发票验证人");           attribute4  
		  excelHeaderFields.add("发票状态");           invoiceAuthenticationStatus  / 不是导入的
		 // list.add("认证失败原因");                  attribute5 / 不是导入的
	  }
			 */
  }
  
 
	 static{}
  
  
  
  public void excelReader(String path){
    try {
      InputStream inp = new FileInputStream(path);
      importWorkbook = WorkbookFactory.create(inp);      
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (InvalidFormatException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }  
  
  /**
   * 取Excel所有数据，包含header
   * @return  List<String[]>
   */
 public List<String[]> getAllData(int sheetIndex){
    int columnNum = 0;
    Sheet sheet = importWorkbook.getSheetAt(sheetIndex);
    
    if(sheet.getRow(0)!=null){
        columnNum = sheet.getRow(0).getLastCellNum()-sheet.getRow(0).getFirstCellNum();
    }
    if(columnNum>0){
      for(Row row:sheet){ 
          String[] singleRow = new String[columnNum];
          int n = 0;
          for(int i=0;i<columnNum;i++){
             Cell cell = row.getCell(i, Row.CREATE_NULL_AS_BLANK);
             switch(cell.getCellType()){
               case Cell.CELL_TYPE_BLANK:
                 singleRow[n] = "";
                 break;
               case Cell.CELL_TYPE_BOOLEAN:
                 singleRow[n] = Boolean.toString(cell.getBooleanCellValue());
                 break;
                //数值
               case Cell.CELL_TYPE_NUMERIC:               
                 if(DateUtil.isCellDateFormatted(cell)){
                   singleRow[n] = String.valueOf(cell.getDateCellValue());
                 }else{ 
                   cell.setCellType(Cell.CELL_TYPE_STRING);
                   String temp = cell.getStringCellValue();
                   //判断是否包含小数点，如果不含小数点，则以字符串读取，如果含小数点，则转换为Double类型的字符串
                   if(temp.indexOf(".")>-1){
                     singleRow[n] = String.valueOf(new Double(temp)).trim();
                   }else{
                     singleRow[n] = temp.trim();
                   }
                 }
                 break;
               case Cell.CELL_TYPE_STRING:
                 singleRow[n] = cell.getStringCellValue().trim();
                 break;
               case Cell.CELL_TYPE_ERROR:
                 singleRow[n] = "";
                 break;  
               case Cell.CELL_TYPE_FORMULA:
                 cell.setCellType(Cell.CELL_TYPE_STRING);
                 singleRow[n] = cell.getStringCellValue();
                 if(singleRow[n]!=null){
                   singleRow[n] = singleRow[n].replaceAll("#N/A","").trim();
                 }
                 break;  
               default:
                 singleRow[n] = "";
                 break;
             }
             n++;
          }
          
          if("".equals(singleRow[0])){continue;}//如果第一行为空，跳过
          dataList.add(singleRow);
      }
    }
    
    
    return dataList;
  }
  /**
   * 返回Excel最大行index值，实际行数要加1
   * @return
   */
  public int getRowNum(int sheetIndex){
    Sheet sheet = importWorkbook.getSheetAt(sheetIndex);
    return sheet.getLastRowNum();
  }
  
  /**
   * 返回数据的列数
   * @return 
   */
  public int getColumnNum(int sheetIndex){
    Sheet sheet = importWorkbook.getSheetAt(sheetIndex);
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
  
  
  
  
  
  
  
  
  
  
  public String getFolder(){
	  
	  int result = 0;
	  String path = null;
	  
	  try{
		  
		  FileSystemView fsv = FileSystemView.getFileSystemView(); //注意了，这里重要的一句
		  path = fsv.getHomeDirectory().getPath();
		  
		  System.out.println(path); //得到桌面路径
		  
	  
/*	  JFileChooser fileChooser = new JFileChooser();
	  FileSystemView fsv = FileSystemView.getFileSystemView(); //注意了，这里重要的一句
	  System.out.println(fsv.getHomeDirectory()); //得到桌面路径
	  fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
	  */
	  //fileChooser.setDialogTitle("请选择要存放导出文件的位置...");
	 // fileChooser.setApproveButtonText("确定");
	  //fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	 // result = fileChooser.showOpenDialog(chatFrame);
	  //result = fileChooser.showOpenDialog(null);
	  
	  
	/*  if (JFileChooser.APPROVE_OPTION == result) {
		  
		 
		  
		  file=fileChooser.getSelectedFile();
		  path=file.getPath();
		  System.out.println("file:"+file +";path: "+path);
	   }*/
	  
	  return (path);
	  }catch(Exception e){
		  e.printStackTrace();
	  }
	  
	  if(path==null || "".equals(path.trim())){
		  System.out.println("can't get folder you selected, so will output to c:/ as default.");
		  
		  	  }
	  return "c:/";
  }
  
  
  
  
  
  
  /**
   * for 进项发票导入用的模板
   *〈一句话功能简述〉 
   * 功能详细描述
   * @param fundsType
   * @param tradeDate
   * @param assetsTypeCode
   * @see [相关类/方法]（可选）
   * @since [产品/模块版本] （可选）
   */
  
  //public void outExcelTemplate(String fundsType, Date tradeDate, String assetsTypeCode){
          
	  
	  // * 发票系统	验证日期	发票代号	发票票号	纳税人识别号	开票日期	含税金额	税金	纳税人名称	公司代码	发票验证人	发票状态	认证失败原因

	   
	  
  public String outExcelTemplate(String exportPath){ 
	 
	  System.out.println("start outExcelTemplate()..");
	  		
	        HSSFWorkbook wb = new HSSFWorkbook();  //--->创建了一个excel文件  
	         HSSFSheet sheet = wb.createSheet("进项发票模板");   //--->创建了一个工作簿  
	       // HSSFDataFormat format= wb.createDataFormat();   //--->单元格内容格式  
	  //      sheet.setColumnWidth((short)3, 20* 256);    //---》设置单元格宽度，因为一个单元格宽度定了那么下面多有的单元格高度都确定了所以这个方法是sheet的  
   //     sheet.setColumnWidth((short)4, 20* 256);    //--->第一个参数是指哪个单元格，第二个参数是单元格的宽度  
    //    sheet.setDefaultRowHeight((short)300);    // ---->有得时候你想设置统一单元格的高度，就用这个方法  
	          
	         //样式1  
	       HSSFCellStyle style = wb.createCellStyle(); // 样式对象  
	   //    style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直  
	   //    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平  
	         //设置标题字体格式  
	   //     Font font = wb.createFont();     
	         //设置字体样式   
	  //     font.setFontHeightInPoints((short)20);   //--->设置字体大小  
	  //      font.setFontName("Courier New");   //---》设置字体，是什么类型例如：宋体  
	  //      font.setItalic(true);     //--->设置是否是加粗  
	       // style.setFont(font1);     //--->将字体格式加入到style1中     
	         //style1.setFillForegroundColor(IndexedColors.DARK_YELLOW.getIndex());  
	          //style1.setFillPattern(CellStyle.SOLID_FOREGROUND);设置单元格颜色  
	        style.setWrapText(true);   //设置是否能够换行，能够换行为true  
	     //   style.setBorderBottom((short)1);   //设置下划线，参数是黑线的宽度  
	   //    style.setBorderLeft((short)1);   //设置左边框  
	   //    style.setBorderRight((short)1);   //设置有边框  
	   //    style.setBorderTop((short)1);   //设置下边框  
	       // style4.setDataFormat(format.getFormat("￥#,##0"));    //--->设置为单元格内容为货币格式  
	          
	       //  style5.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00%"));    //--->设置单元格内容为百分数格式  
	           
	           
	      //表格第一行  
	        HSSFRow row1 = sheet.createRow(0);   //--->创建一行  
	         // 四个参数分别是：起始行，起始列，结束行，结束列  
	//----------        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 15));  

	         //row1.setHeightInPoints(25);  
	         
	         
	         
	       //  HSSFCell cell1 = row1.createCell(0);   //--->创建一个单元格  
	           
	        // cell1.setCellStyle(style);  
	     //  cell1.setCellValue("发票系统");
	       
	     
	       
	       int cellIndex=0;
	       ArrayList<HSSFCell> cells = new ArrayList<HSSFCell>();
	      
	       
	       for(String s : excelHeaderFields){
	    	  
	    	   HSSFCell newCell=null;
	    	   newCell = row1.createCell(cellIndex++);   //--->创建一个单元格            
		    
	    	   newCell.setCellValue(s);
	    	   
	    	   cells.add(newCell);
	       }
	       
	       
	       String folderPath="";
	       if(AssertHelper.empty(exportPath)){
	    	   
	    	   folderPath  = this.getFolder();
	    	   folderPath = folderPath + "/Template.xls";
	       }else if(exportPath.contains(".xl")){
	    	   
	    	   folderPath=exportPath;
	    	   System.out.println("path contains excel file:"+folderPath);
	       }else {
	    	   
	    	   if( exportPath.endsWith("\\") || exportPath.endsWith("/") ){
	    		   
	    		   folderPath=exportPath+"Template.xls";
	    	   }else{
	    		   
	    		   folderPath=exportPath+"/Template.xls";
	    	   }
	    	   
	    	   
	    	   System.out.println("path before no contains excel file, add default file name the become:"+folderPath);
	       }
	       
	      // System.out.println("orgional:"+folderPath);
	       
	     // String t1= folderPath.replaceAll("\\", "/");
	      //String t2 = t1.replaceAll("\\", "/");
	       
	     //todo: client select path insert
	          
       FileOutputStream fileOut = null;  
	       try{              
	            fileOut = new FileOutputStream(folderPath);  
	            wb.write(fileOut);
	            
	            fileOut.flush();
	            //fileOut.close();  
	            System.out.print("OK");  
	       }catch(Exception e){
	    	   System.out.println("can't open file stream, will create file firstly");
	           e.printStackTrace();
	           
	           try{
	        	   File f = new File(folderPath);
	        	   f.setWritable(true);
	        	   fileOut = new FileOutputStream(f);  
		            wb.write(fileOut);
		            
		            fileOut.flush();
		            //fileOut.close();  
		            System.out.print("OK");  
	        	   
	           }catch(Exception ex){
	        	   ex.printStackTrace();
	        	   return "访问路径无权限写入文件";
	           }
	           
	        }finally{
	        	
            if(fileOut != null){
                try {  
	                    fileOut.close();  
                } catch (IOException e) {  
	                   // TODO Auto-generated catch block  
	                    e.printStackTrace();  
	                 }  
	            }  
	       }
		

	       return "";
  }
  
  public void importExcelInit(String importExcelName){
	  
	  if(AssertHelper.empty(importExcelName)){
		  System.out.println("importExcel get null importExcelName");
		  return;
	  }
	  
	  this.excelReader(importExcelName);
	  this.getAllData(sheetIndexDefault);
	  
	  
  }
  
  
  
  
  
  public static Date getDateByStr(String s){
		
		if(AssertHelper.empty(s)){
			
			return null;
		}
		
		Date d = new Date();
		try{
			
		//from jquery class="easyui-datebox" will return as format MM/dd/yyyy
		SimpleDateFormat dateF = new SimpleDateFormat("MM/dd/yyyy");
		d = dateF.parse(s);
		
		return d;
		}catch(Exception e){
			e.printStackTrace();
			
			return null;
		}
		
		
	}
  
  
  public static String getStrByDate(Date orgDate){
		
		if(AssertHelper.empty(orgDate)){
			
			return "";
		}
		
	
		try{
			
		//from jquery class="easyui-datebox" will return as format MM/dd/yyyy  2016-03-18 20:16:58.0
	
		/**
		 * 2016-03-18 20:16:58.0
		 * dow mon dd hh:mm:ss zzz yyyy
		 * mon is the month (Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec). 
		 * 
		 */
		String orgDateStr = orgDate.toString();
		
		if(orgDateStr.split(" ").length > 5){
			//dow mon dd hh:mm:ss zzz yyyy
			
			String[] use = orgDateStr.split(" ");
			
			String mon = use[1];
			String dd = use[2];
			String yyyy = use[5];
			StringBuffer sb = new StringBuffer();
			sb.append(mon).append("/").append(dd).append("/").append(yyyy);
			
			return sb.toString();
		}else if(orgDateStr.split("-").length>1 ){
			//2016-03-18 20:16:58.0
			
			String value = orgDateStr.split(" ")[0];
			
			if(AssertHelper.empty(value)){
				return "";
			}else{
				
				String[] ok = value.split("-");
				
				StringBuffer sb = new StringBuffer();
				sb.append(ok[1]).append("/").append(ok[2]).append("/").append(ok[0]);
				
				return sb.toString();
			}
		}
		
		
		
	
		
		
		}catch(Exception e){
			e.printStackTrace();
			
			return "";
		}
		return "";		
		
	}
  
  
  
  public static void main(){
	  
	  ExcelProcess ex = new ExcelProcess();
	  ex.getFolder();
  }
  
 }

