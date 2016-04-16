package com.deloitte.tms.vat.inf.taxinfo.service.impl;

import java.net.MalformedURLException;

import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;

import org.springframework.stereotype.Component;

import sajt.webservice.ws.service.SajtIssueInvoiceServiceLocator;
import sajt.webservice.ws.service.SajtIssueInvoiceServicePortType;
import sajt.webservice.ws.service.xsd.SajtIssueInvoiceResponse;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.ObjectToXMLUtils;
import com.deloitte.tms.pl.flow.utils.FlowHelper;
import com.deloitte.tms.vat.inf.taxinfo.aisino.constant.AisinoResponseCodeDef;
import com.deloitte.tms.vat.inf.taxinfo.aisino.service.AisinoSHTaxObjectSerializeImpl;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssue;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueDetail;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueResponse;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueResult;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.InvoicePrint;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.InvoicePrintRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.InvoicePrintResult;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.PrintInvoiceResponse;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceserver.distributeInvoice.DistributeInvoiceRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceserver.distributeInvoice.DistributeInvoiceResponse;
import com.deloitte.tms.vat.inf.taxinfo.service.ProcessTaxInterface;
import com.deloitte.tms.vat.inf.taxinfo.service.TaxInterface;
import com.deloitte.tms.vat.inf.taxinfo.service.TaxObjectSerialize;

@Component(TaxInterface.BEAN_ID)
public class TaxInterfaceImpl implements TaxInterface{
	
	@Resource(name=ProcessTaxInterface.BEAN_ID)
	ProcessTaxInterface processTaxInterface;
	
	static String serviceUrl="http://10.245.5.107:8080/axis2/services/SajtIssueInvoiceService";
	static TaxObjectSerialize aisinoTaxObjectSerialize;
	static SajtIssueInvoiceServicePortType service = null;
	static{
		aisinoTaxObjectSerialize = new AisinoSHTaxObjectSerializeImpl();
		try {
			service = new SajtIssueInvoiceServiceLocator().getSajtIssueInvoiceServiceHttpSoap11Endpoint(new java.net.URL(serviceUrl));
		} catch (Exception e) {
			throw new BusinessException("链接航信接口失败！！");
		} 
	}
	
	@Override
	public InvoiceIssueResponse processIssueInvoice(InvoiceIssueRequest request) {
		AssertHelper.notEmpty_assert(request, "请求数据不能为空");
		
		//request.check();
		InvoiceIssueResponse response=new InvoiceIssueResponse();
		InvoiceIssueResult result=new InvoiceIssueResult();
		for(InvoiceIssue issue:request.getRecord()){
			//红冲需要传税额
			if("2".equals(issue.getHead().getDoctype())){
				Double totalAmout=0.0;
				for(InvoiceIssueDetail detail:issue.getDetails()){
					totalAmout=totalAmout+detail.getTaxAmount();
				}
				result.setInvoiceAmount(totalAmout);
			}
		}	
		
		genInvoiceCodeAndNo(request,result);
		response.addResult(result);		
		processTaxInterface.processIssueInvoiceResponse(request,response);
		return response;
	}
	
	@Override
	public PrintInvoiceResponse processPrintInvoice(InvoicePrintRequest request) {
		AssertHelper.notEmpty_assert(request, "打印内容不能为空");
		request.check();
		PrintInvoiceResponse response=new PrintInvoiceResponse();
		for(InvoicePrint print:request.getRecords()){
			InvoicePrintResult result=new InvoicePrintResult();
			result.setErroCode(AisinoResponseCodeDef.PRINT_SITE_SUCESS);
			result.setInvoiceCode(print.getInvoiceCode());
			result.setInvoiceNo(print.getInvoiceNo());
			result.setInvoiceType(print.getInvoiceType());
			printInvoice(print.getInvoiceCode(),print.getInvoiceNo(),Integer.parseInt(print.getInvoiceType()));
			//printInvoice(request);
			
			response.addResult(result);
		}
		processTaxInterface.processPrintInvoiceResponse(request,response);
		
		return response;
	}



	private void printInvoice(String invoiceCode, String invoiceNo, int parseInt) {
		try{
			String printCommand = GeneratPrintCommand(invoiceCode,invoiceNo,parseInt);
			//String printCommand = ObjectToXMLUtils.formatXML(aisinoTaxObjectSerialize.outObject(request));
			System.out.println(printCommand);
			
			SajtIssueInvoiceResponse sajtResponse = service.saveDocument(printCommand);
			System.out.println(sajtResponse.getStatus());
			if( "S".equalsIgnoreCase(sajtResponse.getStatus())){
				System.out.println("发票打印成功！！");
			}else{
				System.out.println("发票打印失败！！"+sajtResponse.getCmdMessage());
			}
		}catch(Exception e){
			throw new BusinessException("打印发票出现异常！！");
		}
	}

	private void printInvoice(InvoicePrintRequest request) {
		
		try{
			//String printCommand = GeneratPrintCommand(invoiceCode,invoiceNo,parseInt);
			String printCommand = ObjectToXMLUtils.formatXML(aisinoTaxObjectSerialize.outObject(request));
			System.out.println(printCommand);
			
			SajtIssueInvoiceResponse sajtResponse = service.saveDocument(printCommand);
			System.out.println(sajtResponse.getStatus());
			if( "S".equalsIgnoreCase(sajtResponse.getStatus())){
				System.out.println("发票打印成功！！");
			}else{
				System.out.println("发票打印失败！！"+sajtResponse.getCmdMessage());
			}
		}catch(Exception e){
			throw new BusinessException("打印发票出现异常！！");
		}
		
	}

	private void genInvoiceCodeAndNo(InvoiceIssueRequest request,InvoiceIssueResult result) {
		try{
			String invoiceString = ObjectToXMLUtils.formatXML(aisinoTaxObjectSerialize.outObject(request));
			System.out.println(invoiceString);
			
			SajtIssueInvoiceResponse sajtResponse = service.saveDocument(invoiceString);
			System.out.println(sajtResponse.getStatus());
			
			//打印发票成功
			if( "S".equalsIgnoreCase(sajtResponse.getStatus()) ){
				//获取开具代码和开局号码
				String cmdMessageString = sajtResponse.getCmdMessage();
				System.out.println(cmdMessageString);
				String strCode = getINVInformation(cmdMessageString,"<binvcode>","</binvcode>");
				String strNum = getINVInformation(cmdMessageString,"<binvnr>","</binvnr>");
				
				result.setInvoiceCode(strCode);
				result.setInvoiceNo(strNum);
				result.setErroCode(AisinoResponseCodeDef.PRINT_SITE_SUCESS);				
				
			}else{
				//System.out.println(sajtResponse.getCmdMessage());
				throw new BusinessException(sajtResponse.getCmdMessage());
			}
			
		}catch(Exception e){
			e.printStackTrace();
			//throw new BusinessException("获取发票代码和发票号码出错");
		}
		
	}


	@Override
	public DistributeInvoiceResponse processDistributeInvoice(
			DistributeInvoiceRequest request) {
		AssertHelper.notEmpty_assert(request, "分发内容不能为空");
		request.check();
		DistributeInvoiceResponse response=new DistributeInvoiceResponse();
		response.setSucess();
		processTaxInterface.processDistributeInvoiceResponse(request,response);
		return response;
	}
	
	public static String getINVInformation(String returnMsg,String search1,String search2) {   

			int start=returnMsg.indexOf(search1);
			int end=returnMsg.indexOf(search2);
			String[] inv_code= returnMsg.substring(start, end).split(">");
			String code=inv_code[1];
			return code;   
	  }   
 
	 public static String GeneratPrintCommand(String code,String num,int inv_kind) {   
		 StringBuffer PrintQuery= new StringBuffer();
		 PrintQuery.append("<?xml version=\"1.0\"?><siiscmd xmlns=\"http://www.aisino.sh.cn\">")
		 			.append("<printinv><key><invkind><value>").append(inv_kind)
		 			.append("</value></invkind><invcode>").append(code)
		 			.append("</invcode><invnr>").append(num)
		 			.append("</invnr></key></printinv></siiscmd>"); 
		 return PrintQuery.toString();   
	 }


}
