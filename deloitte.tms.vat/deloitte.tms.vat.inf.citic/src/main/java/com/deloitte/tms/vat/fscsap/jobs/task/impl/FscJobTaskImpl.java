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
import com.deloitte.tms.pl.security.utils.LittleUtils;
import com.deloitte.tms.vat.fscsap.ftputils.ReadFTPFile;
import com.deloitte.tms.vat.fscsap.ftputils.WriteFTPFile;
import com.deloitte.tms.vat.fscsap.jobs.task.FscJobTask;
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
@Component(FscJobTask.BEAN_ID)
public class FscJobTaskImpl implements FscJobTask {

	public static Logger logger = LoggerFactory
			.getLogger(FscJobTaskImpl.class);
	

	public static String filePathSep="/";
	public static String sep="^";
	public static String extraSep="\\^";
	
	public Properties properties;
	
	public int ftpPort = 21;
	public String ftpUserName = "";
	public String ftpPassword = "";
	public String ftpHost = "";
	public String ftpPath = "";
	public String writeTempFielPath = "";
	public StringBuffer inputFileMainName;
	
	public String inputDataFile="";
	public String inputOKFile="";
	
	public String ftpDatSuf="";
	public String ftpOKSuf="";
	public String ftpFileSplit="";
	//public HashMap<String, Object> inputMap = new HashMap<String, Object>();
	
	public ArrayList<HashMap<String, Object>> inputRecordsMap = new ArrayList<HashMap<String, Object>>();
	
	public static String fpztY="Y";
	public static String fpztN="N";
	public static String fpztO="1";
	public static String fpxt1="FSC";
	public static String fpxt2="SAP";
	
	public static String fpztP="P";
	public static String fpztF="F";
	
	public int fscCount=0;
	
	public static ArrayList<String> inputData = new ArrayList<String>();
	
	static{
		inputData.add("attribute1"); //验证日期   提交日期 同输出接口>验证日期
		inputData.add("invoiceCode"); //发票代号
		inputData.add("invoiceNumber"); //发票票号
		inputData.add("venderName"); //纳税人名称
		inputData.add("venderRegistrationNumber"); //纳税人识别号
		inputData.add("invoicingDate"); //开票日期  Date
		//inputData.add("enteredAmount+vatAmount"); //含税金额 要转换成 净额
		
		inputData.add("attribute6"); //attribute6 存 含税金额  就不用转换成 净额
		
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
		
		String strMonth=String.valueOf(nmonth);
		if(nmonth<10){
			strMonth="0"+strMonth;
		}
		
		int ndate = d.getDate();
		String strDate=String.valueOf(ndate);
		if(ndate<10){
			strDate="0"+strDate;
		}
		
		
		sb.append(properties.getProperty("ftp.inputfile.prefix", "FSC_")); 
		sb.append(nyear).append(strMonth).append(strDate);
		sb.append(properties.getProperty("ftp.inputokfile.sufix", "_I"));
		
		}catch(Exception e){
			System.out.println("step 1.8: getInputFileMainName() little exception ...");
			e.printStackTrace();
		}
		
		
		inputFileMainName=sb;
		String tempMain=sb.toString();
		this.inputDataFile=tempMain+this.ftpDatSuf;
		this.inputOKFile=tempMain+this.ftpOKSuf;
		System.out.println("---------tempMain:" +tempMain+";inputDataFile: "+inputDataFile+";inputOKFile:"+inputOKFile);
		
  		return sb;
	}
	
	public boolean getFscSapConfig(){

		InputStream in = null ;
		try {
		/*	InputStream in = FTPUtil.class.getClassLoader()
					.getResourceAsStream("fscsap.properties");*/
			
			
			in =this.getClass().getResourceAsStream("/config/fsc.properties");
					
			if (in == null) {
				System.out.print("no fond /config/fsc.properties");
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
				ftpOKSuf = properties.getProperty("ftp.ok.sufix", ".OK");
				
				//ftpFileSplit = properties.getProperty("ftp.file.split", "^");
				
				
				//writeTempFielPath = properties.getProperty("writeTempFielPath");
				
			
				//WriteFTPFile write = new WriteFTPFile();
				//ftpPath = ftpPath + "/" + "huawei_220.248.192.200_new1.cfg";
				//write.upload(ftpPath, ftpUserName, ftpPassword, ftpHost, ftpPort, result, writeTempFielPath);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally{
			if(in!=null){
				try{
				in.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		return true;
	}
	
	public boolean readInputOKFile(){
		
		ReadFTPFile read = new ReadFTPFile();
		
		//String tempMainFile=inputFileMainName.toString();
		
		String result = read.readConfigFileForFTP(ftpUserName, ftpPassword, ftpPath, ftpHost, ftpPort, this.inputOKFile);
		System.out.println("Input ok file content:" + result);
		
		if( result.startsWith("error:")){//result is null or '' is ok as no request of fsc, just exist file
			return false;
		}else{
			return true;
		}
		//todo check ok file info
		
	}
	public boolean readInputDataFile(){
		
		try{
			
		inputRecordsMap = new ArrayList<HashMap<String, Object>>();
		ReadFTPFile read = new ReadFTPFile();
		
		String result = read.readConfigFileForFTP(ftpUserName, ftpPassword, ftpPath, ftpHost, ftpPort, this.inputDataFile);
		System.out.println("Input data file:" + result);
		
		if(AssertHelper.empty(result)){
			//means no request today
			
			System.out.println("input data file is empty, no need action follow");
			return false;
		}else if(result.startsWith("error")){
			System.out.println("error happens,maybe input data file no exists or other error");
			return false;
		}else if(result.startsWith("warning")){
			System.out.println("warning happens,means input data file exists, but when read exception happened");
			return false;
		}else{
			
			inputRecordsMap = new ArrayList<HashMap<String, Object>>();
			
			try{
			logger.info("inputRecordsMap newer, should size 0:"+inputRecordsMap.size());
			System.out.println("inputRecordsMap newer, should size 0:"+inputRecordsMap.size());
			}catch(Exception x){
				
			}
			
			String[] allLines = result.split("\n");//split能识别\n?不识别就用其它编码
			
			for(String line : allLines){
			
			
			String[] data=line.split(extraSep);//^  fpdh126^ppph126  \\^
			
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
			
			int mixLen = len < wantLen ? len : wantLen;
			
			for(; i<mixLen; i++){
				
				if (i == 5) { // ("invoicingDate"); //开票日期 Date
					// YYYYMMDD VARCHAR(8)
					String tempD = data[i];
					if (AssertHelper.empty(tempD)) {
						System.out.println("--!!!-------input 开票日期(invoicingDate) is null");
					} else {
						try{
						Date d = new Date();
						int ny = Integer.parseInt(tempD.subSequence(0, 4).toString());
						int nm = Integer.parseInt(tempD.subSequence(4, 6).toString());
						int nd = Integer.parseInt(tempD.subSequence(6, 8).toString());
						
						d.setYear(ny-1900);
						d.setMonth(nm-1);
						d.setDate(nd);
						
						d.setHours(0);
						d.setMinutes(0);
						d.setSeconds(0);
						
						inputMap.put(inputData.get(i), d);
						}catch(Exception e){
							e.printStackTrace();
						}
					}

				}else{
					inputMap.put(inputData.get(i), data[i]);
				}
				
				
			}
			
			//inputMap stand for one line
			inputRecordsMap.add(inputMap);
		}
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
	}catch(Exception exx){
		System.out.println("after read data file to result string ok, extract the resutl string occurs exception");
		exx.printStackTrace();
		return false;
	}
		return true;
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
	public boolean dateEqu8(Date l, Object r){//2016-03-29 14:00:00.0  Tue Mar 29 00:00:00 CST 2016
		
		
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
		
		
			dateByStrl=this.getDay8StrByDate((Date)l);
		
		
		/*	Date ldate=l;
			
			String y =String.valueOf(  (ldate.getYear()+1900) );
			
			String m = ldate.getMonth()+1 > 9 ? String.valueOf( ldate.getMonth()+1 ) : "0"+(String.valueOf( ldate.getMonth()+1 ));
			
			//String d =  String.valueOf(ldate.getDate());//todo 3->03
			
			String d = ldate.getDate() > 9 ? String.valueOf(ldate.getDate()) : "0"+String.valueOf(ldate.getDate());
			
			dateByStrl=y+m+d;*/
		
			
		String	rDateStr="";
		if( (r instanceof String) ){
			//return false;
			
			rDateStr=this.getDay8StrBySt((String)r);
		}else if(r instanceof Date){
			rDateStr=this.getDay8StrByDate((Date)r);
		}
		
		if(  dateByStrl.equalsIgnoreCase(rDateStr)  ){
			
			return true;
		}

		
		return false;
	}
	
	
/*	public void find4ExistByInputList(){
		
		StringBuffer allOutSb = new StringBuffer();
		
		for(HashMap inputMap :  inputRecordsMap ){
			
			//allOutSb.append(find4ExistByInput( inputMap).toString());	
		}
	}*/
	
	public TmsDrvatInvoiceTrxLInParam find4ExistByInput(HashMap inputMap){
		
		List<TmsDrvatInvoiceTrxLInParam> listInpara=null;
		 TmsDrvatInvoiceTrxLInParam record=null;
	try{
	 HashMap<String, Object> params = new HashMap<String, Object>();
		
	 params.put("invoiceCode", inputMap.get("invoiceCode"));
	 params.put("invoiceNumber", inputMap.get("invoiceNumber"));

	 logger.info("invoiceCode:"+params.get("invoiceCode")+";invoiceNumber:"+params.get("invoiceCode"));
	 System.out.println("invoiceCode:"+params.get("invoiceCode")+";invoiceNumber:"+params.get("invoiceCode"));
	 
		 listInpara = this.tmsDrvatInvoiceTrxHService.findTmsDrvatInvoiceTrxLByParams2(params);
		 
		 if(AssertHelper.empty(listInpara) || listInpara.size()<1){
			 //todo, write empty output file
			 
			 System.out.println("invoiceCode & invoiceNumber no match, will return empty default output");
			 return null;
		 }
		 
		 if(listInpara.size() > 1){
			 System.out.println("find4ExistByInput > : result size: "+listInpara.size() +", only 1 is perfect");
			 //return null;
		 }
		 
		 record = listInpara.get(0);
		 
	}catch(Exception e){
		e.printStackTrace();
	
	}
	
		//return new StringBuffer();
		return record;
	}
		 
	/**
	 * 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param record from db result
	 * @param inputMap from request line
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public StringBuffer verifyOut(TmsDrvatInvoiceTrxLInParam record, HashMap inputMap){
			 
		String orgIAStatus = record.getInvoiceAuthenticationStatus();
		if(LittleUtils.strEmpty(orgIAStatus)){
			orgIAStatus="";
		}
		
		//record.setInvoiceAuthenticationStatus(this.fpztY);
		
		record.setInvoiceAuthenticationStatus(orgIAStatus+this.fpztY);
		
		record.setAttribute2(this.fpxt1);
		record.setAttribute5("");
		 
		try{
		 
		 if(this.strEqu(record.getVenderRegistrationNumber(),  inputMap.get("venderRegistrationNumber")   ) ){

	/*	excelHeaderFields.add("发票系统"); attribute2     / 不是导入的
	 * 	  excelHeaderFields.add("发票状态");           invoiceAuthenticationStatus  / 不是导入的
	 // list.add("认证失败原因");                  attribute5 / 不是导入的	 
	 * 
	 * params.put("venderRegistrationNumber", inputMap.get("venderRegistrationNumber"));
			 params.put("invoicingDate", inputMap.get("invoicingDate"));
			 params.put("enteredAmount", inputMap.get("enteredAmount"));
			 params.put("vatAmount", inputMap.get("vatAmount"));*/
		 }else{
			 
			 record.setInvoiceAuthenticationStatus(orgIAStatus+this.fpztN);
			
			 record.setAttribute5("纳税人识别号(venderRegistrationNumber)");
			 
			 //todo  write output file using N format tempalte
			 return null;
		 }
		 
		 
		 if(this.dateEqu8(record.getInvoicingDate(),  inputMap.get("invoicingDate")   )){
			 //invoicingDate=Tue Mar 29 00:00:00 CST 2016
		 }else{
			 record.setInvoiceAuthenticationStatus(orgIAStatus+this.fpztN);
			
			 record.setAttribute5("开票日期(invoicingDate) ");
			 
			 //todo  write output file using N format tempalte
			 return null;
		 }
		 
		 
		// if( (record.getEnteredAmount() + record.getVatAmount()) ==  Double.parseDouble(  (String)inputMap.get("enteredAmount+vatAmount") ) ){
		 
		 if( this.strEqu4Money(record.getAttribute6(), (String)inputMap.get("attribute6") )    ){
			 
		 }else{
			 record.setInvoiceAuthenticationStatus(orgIAStatus+this.fpztN);
			 
			 //record.setAttribute5("含税金额(enteredAmount+vatAmount)");
			 record.setAttribute5("含税金额");
			 //attribute6
			 
			 //todo  write output file using N format tempalte
			 return null;
		 }
		 
		 //if( ( record.getVatAmount()) ==  Double.parseDouble(  (String)inputMap.get("vatAmount") ) ){
			 
		 if( this.strEqu4Money(record.getAttribute7(), (String)inputMap.get("vatAmount") )    ){
		 
		 }else{
			 record.setInvoiceAuthenticationStatus(orgIAStatus+this.fpztN);
			 
			// record.setAttribute5("税金(vatAmount)"); ->record use attribute7 name, inputMap use vatAmount name
			 record.setAttribute5("税金");
			 
			 //todo  write output file using N format tempalte
			 return null;
		 }
		 
		 //todo write Y format template
		 
		 
	}catch(Exception e){
		e.printStackTrace();
	}
		return null;
	
	
}

	public boolean strEqu4Money(String l, String r){	

		
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
		
		int point=l.indexOf(".");
		String llp="";
		String lrp="";
		if(point>-1){
			llp=l.substring(0, point);
			lrp=l.substring(point);
			
			lrp=strNoRZeroAfterPoint(lrp);
		}
		
		String lstr=llp+lrp;
		
		
		
		int rpoint=r.indexOf(".");
		String rlp="";
		String rrp="";
		if(rpoint>-1){
			rlp=l.substring(0, rpoint);
			rrp=l.substring(rpoint);
			
			rrp=strNoRZeroAfterPoint(rrp);
		}
		String rstr=rlp+rrp;
		
		if(lstr.trim().equalsIgnoreCase(rstr.trim())){
			return true;
		}
		
		return false;		
	}
	
	public String strNoRZeroAfterPoint(String org){
		
		//String nowStr="";
		
		if(LittleUtils.strEmpty(org)){
			return "";
		}
		
		int zp = org.lastIndexOf("0");
		
		if(  zp > -1   && zp==org.length()-1    ){
			org=org.substring(0, org.length()-1);
			
			this.strNoRZeroAfterPoint(org);
		}
		
		return org;
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
	
	
	public List<TmsDrvatInvoiceTrxLInParam> findByInput(HashMap inputMap){
		
		List<TmsDrvatInvoiceTrxLInParam> listInpara=null;
	try{	
	 HashMap<String, Object> params = new HashMap<String, Object>();
		
	 params.put("invoiceCode", inputMap.get("invoiceCode"));
	 params.put("invoiceNumber", inputMap.get("invoiceNumber"));
	 params.put("venderRegistrationNumber", inputMap.get("venderRegistrationNumber"));
	 params.put("invoicingDate", inputMap.get("invoicingDate"));
	 params.put("enteredAmount", inputMap.get("enteredAmount"));
	 params.put("vatAmount", inputMap.get("vatAmount"));
		
		/**
		 * inputData.add("invoiceCode"); //发票代号
		inputData.add("invoiceNumber"); //发票票号
		
		inputData.add("venderRegistrationNumber"); //纳税人识别号
		inputData.add("invoicingDate"); //开票日期  Date
		
				---------不用 直接用净额查数据库  inputData.add("enteredAmount+vatAmount"); //含税金额 要转换成 净额
		inputData.add("enteredAmount"); // 净额
		
		-----------------不用 inputData.add("taxRate"); // 税率 //no need as no use	
		
		inputData.add("vatAmount"); //税金

		 */
		
		 listInpara = this.tmsDrvatInvoiceTrxHService.findTmsDrvatInvoiceTrxLByParams2(params);
	}catch(Exception e){
		e.printStackTrace();
	}
		return listInpara;
	}
	
	
	public void writeBack(TmsDrvatInvoiceTrxLInParam one){
		
		try{
		this.tmsDrvatInvoiceTrxHService.writeBackAuthen(one);
		}catch(Exception e){
			e.printStackTrace();
		}
		/**
		 * 		
		record.setInvoiceAuthenticationStatus(this.fpztY);
		record.setAttribute2(this.fpxt1);
		record.setAttribute5("");
		 */
	}
	
	
	public Date getDate8ByStr(String tempD){//yyyymmdd
		
		Date d = new Date();
		int ny = Integer.parseInt(tempD.subSequence(0, 4).toString());
		int nm = Integer.parseInt(tempD.subSequence(4, 6).toString());
		int nd = Integer.parseInt(tempD.subSequence(6, 8).toString());
		
		d.setYear(ny-1900);
		d.setMonth(nm-1);
		d.setDate(nd);
		
		d.setHours(0);
		d.setMinutes(0);
		d.setSeconds(0);
		
		return d;
	}
	
	public String getDay8StrByDate(Date d){
		
		if(d==null){
			return "";
		}
		
		int ny=d.getYear()+1900;
		String nyStr=String.valueOf(ny);
		int nm=d.getMonth()+1;
		String nmStr= nm >9 ? String.valueOf(nm)  : "0"+String.valueOf(nm) ;
		
		int nd=d.getDate();
		String ndStr= nm >9 ? String.valueOf(nd)  : "0"+String.valueOf(nd) ;
		
		
		return nyStr+nmStr+ndStr;
	}
	
public String getDay8StrBySt(String s){
		//Tue Mar 29 00:00:00 CST 2016
		if(s==null || ""==s.trim()){
			return "";
		}
		Date d=null;
		
		try{
		 d = new Date(s);
		 int ny=d.getYear()+1900;
			String nyStr=String.valueOf(ny);
			int nm=d.getMonth()+1;
			String nmStr= nm >9 ? String.valueOf(nm)  : "0"+String.valueOf(nm) ;
			
			int nd=d.getDate();
			String ndStr= nm >9 ? String.valueOf(nd)  : "0"+String.valueOf(nd) ;
			
			
			return nyStr+nmStr+ndStr;
		}catch(Exception e){
			System.out.println("can't parse str to date");
			e.printStackTrace();
			//todo using str to split
		}
		
		return "";
	}
	
	
	
	public StringBuffer generateOutput(){
			
			StringBuffer sb = new StringBuffer();
			
			fscCount=0; //must init to 0
			
			try{
			
			logger.info("now should init fscCount="+fscCount+"; have value of inputRecordsMap:"+inputRecordsMap.size());
			System.out.println("now should init fscCount="+fscCount+"; have value of inputRecordsMap:"+inputRecordsMap.size());
			}catch(Exception exx){
				
			}
		
			for(HashMap map : this.inputRecordsMap){
			
			//List<TmsDrvatInvoiceTrxLInParam> listInPara = find4ExistByInput(map);//this.findByInput(map);
			TmsDrvatInvoiceTrxLInParam one = find4ExistByInput(map);//this.findByInput(map);
			//todo find4ExistByInput > StringBuffer +  now generateOutput
			
			
			if(AssertHelper.empty(one)){
				
				//todo: generate default output even no data match
			}else{
				
				
				//for(TmsDrvatInvoiceTrxLInParam one : listInPara){
					
					sb.append("FSC").append(sep);
					sb.append(  this.getDay8StrBySt( one.getAttribute1())  ).append(sep);//date type FSC^Tue Mar 29 00:00:00 CST 2016^
					sb.append(one.getInvoiceCode()).append(sep);
					sb.append(one.getInvoiceNumber()).append(sep);
					sb.append(one.getVenderRegistrationNumber()).append(sep);
					sb.append(this.getDay8StrByDate( one.getInvoicingDate())   ).append(sep);//FSC^Tue Mar 29 00:00:00 CST 2016^fpdh125^ppph125^nsrsbh125^2016-03-29 14:00:00.0^
					
			/*		sb.append(one.getEnteredAmount() + one.getVatAmount()).append(sep);//FSC^Tue Mar 29 00:00:00 CST 2016^fpdh125^ppph125^nsrsbh125^2016-03-29 14:00:00.0^-89.2^
					sb.append(one.getVatAmount()).append(sep);//税金
					*/
					sb.append(one.getAttribute6()).append(sep);//含税金额       FSC^Tue Mar 29 00:00:00 CST 2016^fpdh125^ppph125^nsrsbh125^2016-03-29 14:00:00.0^-89.2^
					sb.append(one.getAttribute7()).append(sep);//税金 - 暂时用attribute7代替VatAmount
					
					
					sb.append(one.getVenderName()).append(sep);
					sb.append(one.getAttribute3()).append(sep);
					sb.append(one.getAttribute4()).append(sep);//FSC^Tue Mar 29 00:00:00 CST 2016^fpdh125^ppph125^nsrsbh125^2016-03-29 14:00:00.0^-89.2^3s^9999^fpyzr3^
					
					this.verifyOut(one, map);
					/**
					 * 			 
			 record.setInvoiceAuthenticationStatus(this.fpztN);
			 record.setAttribute2(this.fpxt1);
			 record.setAttribute5("纳税人识别号(venderRegistrationNumber)");
					 */
					sb.append(one.getInvoiceAuthenticationStatus()).append(sep);
					sb.append(one.getAttribute5()).append(sep);
					
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
					
					++fscCount;
					System.out.println("---fscCount: "+fscCount);
					
					//todo: using a new thread to do the writeBack operation...
					this.writeBack(one);
			}
			
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
		filePathSb.append(this.filePathSep).append(getOutputFileMainName()).append(".OK");
		
		
		System.out.println("output file path:"+ftpPath);
		write.upload(filePathSb.toString(), ftpUserName, ftpPassword, ftpHost, ftpPort, sb.toString(), writeTempFielPath);
	
	}
	
	
	public StringBuffer getOutputFileMainName(){
		String outMainName = this.getInputFileMainName(properties).toString().replace("_I", "_O");
		
		StringBuffer sb = new StringBuffer(outMainName);
		return sb;
	}
	
	

	public void generateOutputOld(){/*
		
		for(HashMap map : this.inputRecordsMap){
		
		List<TmsDrvatInvoiceTrxLInParam> listInPara = find4ExistByInput(map);//this.findByInput(map);
		//todo find4ExistByInput > StringBuffer +  now generateOutput
		
		
		if(AssertHelper.empty(listInPara)){
			
			//todo: generate default output even no data match
		}else{
			
			StringBuffer sb = new StringBuffer();
			for(TmsDrvatInvoiceTrxLInParam one : listInPara){
				
				sb.append("FSC").append(sep);
				sb.append( one.getAttribute1()).append(sep);
				sb.append(one.getInvoiceCode()).append(sep);
				sb.append(one.getInvoiceNumber()).append(sep);
				sb.append(one.getVenderRegistrationNumber()).append(sep);
				sb.append(one.getInvoicingDate()).append(sep);
				sb.append(one.getEnteredAmount() + one.getVatAmount()).append(sep);
				sb.append(one.getVenderName()).append(sep);
				sb.append(one.getAttribute3()).append(sep);
				sb.append(one.getAttribute4()).append(sep);
				
				//!!!!!!!+ 发票状态  +认证失败原因------------>

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
			// ---认证失败原因
 * 
 
			}
		}
		
	}
	*/}
	
	
	/**
	 * @param filePath
	 * @param fileOutPath
	 * @return
	 * @see com.deloitte.tms.vat.salesinvoice.jobs.service.UserFileProcessJobTask#executeProcessFile(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public int executeProcessFile(String filePath, String fileOutPath) {
		
 		System.out.println("FscSapJobTaskImpl.java > executeProcessFile > ");
		
		if(this.getFscSapConfig()){
			System.out.println("step 1: getFscSapConfig() ok ...");
		}else{
			System.out.println("step 1: getFscSapConfig() maybe little exception occurs ...");
		}
		
		
		this.getInputFileMainName(properties);
		System.out.println("step 2: getInputFileMainName() ok ...");
		
		if(!this.readInputOKFile()){
			
			System.out.println("step 3: input ok file empty /no exists so no querty task need to handle today or read input ok file fail, no need to do following aciton");
			return -1;
		}
		System.out.println("step 3: readInputOKFile() ok ...");
		
		//step 2
	 if(!this.readInputDataFile()){		   
	
		 System.out.println("step 4: input data file empty /no exists so no querty task need to handle today or read input data file fail, no need to do following aciton");
		 return -1;
	 }
	 System.out.println("step 4: readInputDataFile() ok ...");
		
		StringBuffer sb = this.generateOutput();  //step 4(3)
		System.out.println("step 5: generateOutput() ok ...");
		
		System.out.println("step 5.1 will output:"+sb);
		this.writeOutputDataFile(sb);   //step 5
		System.out.println("step 6: writeOutputDataFile() ok ...");
	
		this.writeOutputOKFile(fpxt1, this.fscCount);
		System.out.println("step 7: writeOutputOKFile() ok ...");
		
		return 0;
		
		/*
		 * todo: 
		 * 0. get properties, which will used by below
		 * 1. get ftp file from remtoe ftp
		 * 2. get information of inputfile
		 * 3. search db using getted information
		 * 4. generate output file
		 * 5. put output file on remote server
		 * 
		 * 8. write back all records to DB -> for sap job using
		 * 
		 * sap job:
		 * 6. for daily increase, at 22:00, run search db
		 * 7. generate output file using search result
		 * 
		 * 
		 * 
		 * 
		 */
		
		
		
		
		
		
		/*

		int count = 0;
		int userErrCount = 0;
		int userOrgErrCount = 0;
		String doneFilePath = JobFileUtils.genDoneFile(filePath); // 生成处理完的文件
		String trigFilePath = JobFileUtils.genTriggerFile(filePath);// 生成触发文件
		String lineTxt = null;// 一行字符串定义
		StringBuilder errInfo = new StringBuilder();
		
		File file = new File(filePath);
		
		List<DefaultUser> listDefaultUser = new ArrayList<DefaultUser>();
		List<BaseUserOrg> listBaseUserOrg = new ArrayList<BaseUserOrg>();
		if (!file.isFile() || !file.exists()) {
			throw new BusinessException("原文件异常！");
		}
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(
					file), StringPool.UTF8_ENCODING);
			BufferedReader bufferedReader = new BufferedReader(read);
			FileWriter fw = new FileWriter(new File(doneFilePath));
			BufferedWriter bw = new BufferedWriter(fw);

			LOGGER.debug("**********开始读文件**************");
			DefaultUser defaultUser = new DefaultUser();
			BaseUserOrg baseUserOrg = new BaseUserOrg();
			
			while ((lineTxt = bufferedReader.readLine()) != null) {
				defaultUser = convertToEntity(lineTxt);
				baseUserOrg.setOrgId(defaultUser.getBizOrgCode());
				baseUserOrg.setUsername(defaultUser.getUsername());
				listDefaultUser.add(defaultUser);
				listBaseUserOrg.add(baseUserOrg);
				bw.append(lineTxt + "\n");
				LOGGER.debug("*******************正在处理第" + count
						+ "条*************");
				count++;
			}

			bufferedReader.close();
			read.close();
			bw.close();
			fw.close();
		} catch (Exception e) {
			LOGGER.debug("********文件处理异常" + e.getMessage() + "条************");
		}

		for (BaseUserOrg baseUserOrg : listBaseUserOrg) {
			try {
				baseUserOrgDao.saveOrUpdate(baseUserOrg);
			} catch (Exception e) {
				errInfo.append(e.getMessage());
				errInfo.append("\n");
				userOrgErrCount++;
			}
		}
		
		for (DefaultUser user : listDefaultUser) {
			try {
				defaultUserDao.saveOrUpdate(user);
			} catch (Exception e) {
				errInfo.append(e.getMessage());
				errInfo.append("\n");
				userErrCount++;
			}
		}

		LOGGER.debug("********读文件结束，一共" + count + "条************");

		try {
			FileUtils.moveFile(doneFilePath, fileOutPath);
			FileUtils.moveFile(filePath, fileOutPath);
			FileUtils.deleteFile(trigFilePath);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (userErrCount > 0||userOrgErrCount>0) {
			throw new BusinessException(userErrCount + "个DefaultUser文件处理失败:" + userOrgErrCount + "个BaseUserOrg文件处理失败:"
					+ errInfo.toString());
		}

		return count;
	*/}

	/**
	 * 〈一句话功能简述〉 功能详细描述
	 * 
	 * @param lineTxt
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	private DefaultUser convertToEntity(String lineTxt) {
		String[] splitLineTxt = lineTxt.split("", -1);
		DefaultUser defaultUser = new DefaultUser();
		defaultUser.setUsername(StringUtils.trim(splitLineTxt[1]));
		defaultUser.setUserCode(StringUtils.trim(splitLineTxt[2]));
		defaultUser.setRealName(StringUtils.trim(splitLineTxt[3]));
		defaultUser.setAdministrator(false);
		defaultUser.setEnabled(true);
		defaultUser.setPassword("xxxx");
		defaultUser.setSalt("123");
		defaultUser.setBizOrgCode(StringUtils.trim(splitLineTxt[12]));
		return defaultUser;
	}

}
