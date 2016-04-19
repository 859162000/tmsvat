/**    
 * Copyright (C),Deloitte
 * @FileName: UserFileProcessJobTaskImpl.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.task.impl  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月24日 上午10:11:59  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.deloitte.tms.vat.fscsap.jobs.task.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.StringUtils;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;
import com.deloitte.tms.vat.fscsap.UsefulUtil;
import com.deloitte.tms.vat.fscsap.ftputils.ReadFTPFile;
import com.deloitte.tms.vat.fscsap.ftputils.WriteFTPFile;
import com.deloitte.tms.vat.fscsap.jobs.task.SapJobTask;
import com.deloitte.tms.vat.purchinvoice.model.TmsDrvatInvoiceTrxLInParam;
import com.deloitte.tms.vat.purchinvoice.service.TmsDrvatInvoiceTrxHService;

/**
 * 〈一句话功能简述〉 功能详细描述
 * 
 * @author stonshi
 * @create 2016年3月24日 上午10:11:59
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component(SapJobTask.BEAN_ID)
public class SapJobTaskImpl implements SapJobTask {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SapJobTaskImpl.class);

	public static String filePathSep="/";
	public static String sep="^";
	
	public Properties properties;
	
	public int ftpPort = 21;
	public String ftpUserName = "";
	public String ftpPassword = "";
	public String ftpHost = "";
	public String ftpPath = "";
	public String writeTempFielPath = "";
	public StringBuffer inputFileMainName;
	public String ftpDatSuf="";
	public String ftpOKSuf="";
	public String ftpFileSplit="\\^";
	//public HashMap<String, Object> inputMap = new HashMap<String, Object>();
	
	ArrayList<HashMap<String, Object>> inputRecordsMap = new ArrayList<HashMap<String, Object>>();
	
	public static String fpztY="Y";
	public static String fpztN="N";
	public static String fpztO="1";
	public static String fpxt1="FSC";
	public static String fpxt2="SAP";
	
	public int fscSapCount=0;
	
	public static ArrayList<String> inputData = new ArrayList<String>();
	
	static{
		inputData.add("attribute1"); //验证日期   提交日期 同输出接口>验证日期
		inputData.add("invoiceCode"); //发票代号
		inputData.add("invoiceNumber"); //发票票号
		inputData.add("venderName"); //纳税人名称
		inputData.add("venderRegistrationNumber"); //纳税人识别号
		inputData.add("invoicingDate"); //开票日期  Date
		//inputData.add("enteredAmount+vatAmount"); //含税金额 要转换成 净额
		inputData.add("attribute6"); //含税金额 用attribute1
		inputData.add("enteredAmount"); // 净额
		
		inputData.add("taxRate"); // 税率 //no need as no use	
		
		inputData.add("vatAmount"); //税金
		
		inputData.add("inputPersonName"); //录入人名称 //no need	
	    inputData.add("invoiceStatus"); //发票状态 ‘1’-正在验证 , 初始输入的发票状态全为‘1’  //no need as always 1 when input	
		
		
	}
	
	
	public ArrayList<StringBuffer> outStrList;
	
	@Resource
	private TmsDrvatInvoiceTrxHService tmsDrvatInvoiceTrxHService;
	
	public StringBuffer getInputFileMainName(Properties properties){
		
		StringBuffer sb = new StringBuffer();
		try{
		
		Date d = new Date();
		int nyear = d.getYear()+1900;
		int nmonth = d.getMonth()+1;
		int ndate = d.getDate();
		
		String strMonth=String.valueOf(nmonth);
		if(nmonth<10){
			strMonth="0"+strMonth;
		}
		
		//int ndate = d.getDate();
		String strDate=String.valueOf(ndate);
		if(ndate<10){
			strDate="0"+strDate;
		}
		
		sb.append(properties.getProperty("ftp.inputfile.prefix", "FSC_")); 
		//sb.append(nyear).append(nmonth).append(ndate);
		sb.append(nyear).append(strMonth).append(strDate);
		sb.append(properties.getProperty("ftp.inputokfile.sufix", "_I"));
		
		}catch(Exception e){
			e.printStackTrace();
		}
		inputFileMainName=sb;
		return sb;
	}
	
	public void getFscSapConfig(){

		InputStream in = null ;
		try {		
			
			in =this.getClass().getResourceAsStream("/config/sap.properties");
					
			if (in == null) {
				System.out.print("no fond /config/fscsap.properties");
			} else {
				properties = new Properties();
				properties.load(in);
				ftpUserName = properties.getProperty("ftp.userName");
				ftpPassword = properties.getProperty("ftp.password");
				ftpHost = properties.getProperty("ftp.host");
				ftpPort = Integer.valueOf(properties.getProperty("ftp.port"))
						.intValue();
				ftpPath = properties.getProperty("ftp.path");
				
				ftpDatSuf = properties.getProperty("ftp.dat.sufix", ".Dat");
				ftpOKSuf = properties.getProperty("ftp.dat.sufix", ".OK");
				
				ftpFileSplit = properties.getProperty("ftp.file.split", "\\^");
			
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{

			if(in!=null){
				try{
				in.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}			
		}
	
	}
	
	public boolean readInputOKFile(){
		
		ReadFTPFile read = new ReadFTPFile();
		
		String result = read.readConfigFileForFTP(ftpUserName, ftpPassword, ftpPath, ftpHost, ftpPort, this.inputFileMainName.append(this.ftpOKSuf).toString());
		System.out.println("Input ok file：" + result);
		
		if(AssertHelper.empty(result) || result.startsWith("error:")){
			return false;
		}else{
			return true;
		}
		//todo check ok file info
		
	}
	public void readInputDataFile(){
		
		ReadFTPFile read = new ReadFTPFile();
		
		String result = read.readConfigFileForFTP(ftpUserName, ftpPassword, ftpPath, ftpHost, ftpPort, this.inputFileMainName.append(this.ftpDatSuf).toString());
		System.out.println("Input data file：" + result);
		
		
		inputRecordsMap = new ArrayList<HashMap<String, Object>>();
		
		String[] allLines = result.split("\n");//split能识别\n?不识别就用其它编码
		
		for(String line : allLines){
		
		
		String[] data=line.split(this.ftpFileSplit);
		
		/*ArrayList<String> inputData = new ArrayList<String>();
		inputData.add("attribute1"); //验证日期   提交日期 同输出接口>验证日期
		inputData.add("invoiceCode"); //发票代号
		inputData.add("invoiceNumber"); //发票票号
		inputData.add("venderName"); //纳税人名称
		inputData.add("venderRegistrationNumber"); //纳税人识别号
		inputData.add("invoicingDate"); //开票日期  Date
		inputData.add("enteredAmount+vatAmount"); //含税金额 要转换成 净额
		inputData.add("enteredAmount"); // 净额
		
		inputData.add("taxRate"); // 税率 //no need as no use	
		
		inputData.add("vatAmount"); //税金
		
		inputData.add("inputPersonName"); //录入人名称 //no need	
	    inputData.add("invoiceStatus"); //发票状态 ‘1’-正在验证 , 初始输入的发票状态全为‘1’  //no need as always 1 when input	
		
		*/
	    HashMap<String, Object> inputMap = new HashMap<String, Object>();
		
		int i=0;
		int len = data.length;
		int wantLen = inputData.size();
		if(len!=wantLen){
			System.out.println("--!!!---input data contain fileds length: "+len+"; but expected length is: "+wantLen);
		}
		
		for(; i<len; i++){
			
			if (i == 5) { // ("invoicingDate"); //开票日期 Date
				// YYYYMMDD VARCHAR(8)
				String tempD = data[i];
				if (AssertHelper.empty(tempD)) {
					System.out.println("--!!!-------input 开票日期(invoicingDate) is null");
				} else {
					try{
					Date d = new Date("MM/dd/YYYY");
					int ny = Integer.parseInt(tempD.subSequence(0, 4).toString());
					int nm = Integer.parseInt(tempD.subSequence(4, 6).toString());
					int nd = Integer.parseInt(tempD.subSequence(6, 8).toString());
					
					d.setYear(ny);
					d.setMonth(nm-1);
					d.setDate(nd);
					
					inputMap.put(inputData.get(i), d);
					}catch(Exception e){
						e.printStackTrace();
					}
				}

			}else{
				inputMap.put(inputData.get(i), data[i]);
			}
			
			
		}
		
		
		inputRecordsMap.add(inputMap);
	}
		
		
		
		
		/**
		 * 
		 *
发票系统
提交日期
发票代号
发票票号
纳税人名称
纳税人识别号
开票日期
含税金额
净值
税率
税金
录入人名称
发票状态

		 */
	}
	
	/**
	 * 8 mean , YYYYMMDD 8 
	 * 14 mean, YYYYMMDD HHmmss
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param l
	 * @param r
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public boolean dateEqu8(Date l, Object r){
		
		
		if(AssertHelper.empty(l)  && AssertHelper.empty(r)){
			return true;
		}
		
		if(AssertHelper.empty(l) && !AssertHelper.empty(r)){
			return false;
		}
		
		if(!AssertHelper.empty(l) && AssertHelper.empty(r)){
			return false;
		}
		
		String dateByStrl="";
		//if((l instanceof Date )  ){
			
			Date ldate=l;
			
			String y =String.valueOf(  (ldate.getYear()+1900) );
			
			String m = ldate.getMonth()+1 > 9 ? String.valueOf( ldate.getMonth()+1 ) : "0"+(String.valueOf( ldate.getMonth()+1 ));
			
			String d =  String.valueOf(ldate.getDate());
			
			dateByStrl=y+m+d;
		//}
			
		if( !(r instanceof String) ){
			return false;
		}
		
		if(dateByStrl.equalsIgnoreCase(r.toString())){
			
			return true;
		}

		
		return false;
	}

	
	public TmsDrvatInvoiceTrxLInParam find4ExistByInput(HashMap inputMap){
		
		List<TmsDrvatInvoiceTrxLInParam> listInpara=null;
		 TmsDrvatInvoiceTrxLInParam record=null;
	try{
	 HashMap<String, Object> params = new HashMap<String, Object>();
		
	 params.put("invoiceCode", inputMap.get("invoiceCode"));
	 params.put("invoiceNumber", inputMap.get("invoiceNumber"));

		
		 listInpara = this.tmsDrvatInvoiceTrxHService.findTmsDrvatInvoiceTrxLByParams2(params);
		 
		 if(AssertHelper.empty(listInpara) || listInpara.size()<1){
			 //todo, write empty output file
			 
			 System.out.println("invoiceCode & invoiceNumber no match, will return empty default output");
			 return null;
		 }
		 
		 if(listInpara.size() > 1){
			 System.out.println("sap > find4ExistByInput >  result size: "+listInpara.size() +", only 1 is perfect");
			// return null;
		 }
		 
		 record = listInpara.get(0);
		 
	}catch(Exception e){
		e.printStackTrace();
	
	}
	
		//return new StringBuffer();
		return record;
	}
		 

	
	public boolean strEqu(Object l, Object r){
		
		if(AssertHelper.empty(l)  && AssertHelper.empty(r)){
			return true;
		}
		
		if(AssertHelper.empty(l) && !AssertHelper.empty(r)){
			return false;
		}
		
		if(!AssertHelper.empty(l) && AssertHelper.empty(r)){
			return false;
		}
		
		if((l instanceof String )  && !(r instanceof String)){
			return false;
		}
		
		if(!(l instanceof String )  && (r instanceof String)){
			return false;
		}
		
		String lstr=(String)l;
		String rstr=(String)r;
		
		if(lstr.trim().equalsIgnoreCase(rstr.trim())){
			return true;
		}
		
		return false;
	}
	
	public List<TmsDrvatInvoiceTrxLInParam> findByTime4SAP(Integer hour){
		
		List<TmsDrvatInvoiceTrxLInParam> listInpara=null;
	try{	
	 HashMap<String, Object> params = new HashMap<String, Object>();
		
	 Date d = new Date();
	 Date d2= new Date();
	// d2.setYear(d.getYear());
	// d2.setMonth(d.getMonth()+1);
	 d2.setDate(d.getDate()-1);
	 d2.setHours(hour);
	 d2.setMinutes(0);
	 d2.setSeconds(0);
	 
	 System.out.println("findByTime4SAP now date:"+d+ " ; and last day's 22:00 is :"+ d2);
	 
	 params.put("createDate", d2);
	
		 listInpara = this.tmsDrvatInvoiceTrxHService.findTmsDrvatInvoiceTrxLByDate(params);
	}catch(Exception e){
		e.printStackTrace();
	}
		return listInpara;
	}
	

	
	public StringBuffer generateOutput(){
		
		//todo 22 get from config later
		List<TmsDrvatInvoiceTrxLInParam> list = findByTime4SAP(22);
			
			StringBuffer sb = new StringBuffer();
		
			for(TmsDrvatInvoiceTrxLInParam one : list){
			
		
			if(AssertHelper.empty(one)){
				
				//todo: generate default output even no data match
			}else{
				
		
					String tempFpxt = one.getAttribute2();
					if(AssertHelper.empty(tempFpxt)){
						tempFpxt="DFT";
					}else if(this.fpxt1.equalsIgnoreCase(tempFpxt.trim())){
						tempFpxt=fpxt1;
					}else{
						
					}
					
					sb.append(tempFpxt).append(sep);
					
					
					sb.append(UsefulUtil.getDay8StrBySt( one.getAttribute1())  ).append(sep);
					sb.append(one.getInvoiceCode()).append(sep);
					sb.append(one.getInvoiceNumber()).append(sep);
					sb.append(UsefulUtil.getNotNull(  one.getVenderRegistrationNumber())).append(sep);
					sb.append(   UsefulUtil.getDay8StrByDate( one.getInvoicingDate())   ).append(sep);
					//sb.append(one.getEnteredAmount() + one.getVatAmount()).append(sep);
					
					sb.append(UsefulUtil.getNotNull(one.getAttribute6())  ).append(sep);//含税金额
					
					//sb.append( UsefulUtil.getNotNull( one.getVatAmount()    )   ).append(sep);//税金
					sb.append( UsefulUtil.getNotNull( one.getAttribute7()    )   ).append(sep);//税金
					
					
					sb.append(UsefulUtil.getNotNull( one.getVenderName() )).append(sep);
					sb.append( UsefulUtil.getNotNull( one.getAttribute3() ) ).append(sep);
					sb.append( UsefulUtil.getNotNull( one.getAttribute4()  )).append(sep);
					
			
					/**
					 * 			 
			 record.setInvoiceAuthenticationStatus(this.fpztN);
			 record.setAttribute2(this.fpxt1);
			 record.setAttribute5("纳税人识别号(venderRegistrationNumber)");
					 */
					
					String tempAuth=one.getInvoiceAuthenticationStatus();
					if(this.fpztN.equalsIgnoreCase(tempAuth) || this.fpztY.equalsIgnoreCase(tempAuth)){
						
					}else{
						tempAuth="1";
					}
					
					sb.append(tempAuth).append(sep);
					
					String failReason=one.getAttribute5();
					if(AssertHelper.empty(failReason)){
						failReason="";
					}
					
					sb.append(failReason).append(sep);
					
					sb.append("\n");
					
					//!!!!!!!+ 发票状态  +认证失败原因------------>
	/*
	 * 
	 * 		 excelHeaderFieldsByDb.add("attribute1"); //验证日期
			 excelHeaderFieldsByDb.add("invoiceCode"); //发票代号
			 excelHeaderFieldsByDb.add("invoiceNumber"); //发票票号
			 excelHeaderFieldsByDb.add("venderRegistrationNumber"); //纳税人识别号
			 excelHeaderFieldsByDb.add("invoicingDate"); //开票日期
			 //excelHeaderFieldsByDb.add("vatAmount + enteredAmount");enteredAmount
			 excelHeaderFieldsByDb.add("enteredAmount");//含税金额(enteredAmount+vatAmount) 改成 净额(enteredAmount)
			 excelHeaderFieldsByDb.add("vatAmount"); //税金
			 excelHeaderFieldsByDb.add("venderName"); //纳税人名称
			 excelHeaderFieldsByDb.add("attribute3"); //公司代码
			 excelHeaderFieldsByDb.add("attribute4"); //发票验证人
			 //excelHeaderFieldsByDb.add("invoiceAuthenticationStatus"); //---发票状态
				// attribute5---认证失败原因
	 * 
	 */
				//}
					
					++fscSapCount;
					System.out.println("------fscSapCount"+fscSapCount);
			}
			
		}
			if(list!=null){
				fscSapCount=list.size();
				System.out.println("------fscSapCount=list.size()"+fscSapCount);
			}
			
			return sb;
		}
	
	public void writeOutputDataFile(StringBuffer sb){
		
		WriteFTPFile write = new WriteFTPFile();
		//String ftpPath = ftpPath + "/" + "huawei_220.248.192.200_new1.cfg";
		
		StringBuffer filePathSb = new StringBuffer(ftpPath);
		filePathSb.append(this.filePathSep).append(getOutputFileMainName()).append(".DAT");
		
		
		System.out.println("output file path:"+ftpPath);
		write.upload(filePathSb.toString(), ftpUserName, ftpPassword, ftpHost, ftpPort, sb.toString(), writeTempFielPath);
	
	}
	
	public void writeOutputOKFile(String system, int count){
		
		StringBuffer sb=new StringBuffer();
		sb.append(system).append(sep).append(count);
		
		WriteFTPFile write = new WriteFTPFile();
		//String ftpPath = ftpPath + "/" + "huawei_220.248.192.200_new1.cfg";
		
		StringBuffer filePathSb = new StringBuffer(ftpPath);
		filePathSb.append(this.filePathSep).append(getOutputFileMainName()).append(".OK");////SAPR3_201645_O.OK
		
		
		System.out.println("output file path:"+ftpPath);
		write.upload(filePathSb.toString(), ftpUserName, ftpPassword, ftpHost, ftpPort, sb.toString(), writeTempFielPath);
	
	}
	
	
	public StringBuffer getOutputFileMainName(){
		
		System.out.println("getInputFileMainName StringBuffer:"+this.getInputFileMainName(properties));
		String outMainName = this.getInputFileMainName(properties).toString().replace("_I", "_O");
		
		StringBuffer sb = new StringBuffer(outMainName);
		return sb;
	}
	

	
	
	/**
	 * @param filePath
	 * @param fileOutPath
	 * @return
	 * @see com.deloitte.tms.vat.salesinvoice.jobs.service.UserFileProcessJobTask#executeProcessFile(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public int executeProcessFile(String filePath, String fileOutPath) {
		
		System.out.println("SapJobTaskImpl.java > executeProcessFile > ");
		
		this.getFscSapConfig();
		
		StringBuffer sb = this.generateOutput();  //step 4(3)
		
		this.writeOutputDataFile(sb);
		this.writeOutputOKFile(this.fpxt2, this.fscSapCount);
		
		return 0;
		
		}


}
