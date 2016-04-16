package com.deloitte.tms.vat.inf.taxinfo.service.impl;

import java.rmi.RemoteException;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Component;

import sajt.webservice.ws.service.SajtIssueInvoiceServiceLocator;
import sajt.webservice.ws.service.SajtIssueInvoiceServicePortType;
import sajt.webservice.ws.service.xsd.SajtIssueInvoiceResponse;

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.ObjectToXMLUtils;
import com.deloitte.tms.vat.inf.taxinfo.aisino.service.AisinoSHTaxObjectSerializeImpl;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueResponse;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueResult;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.InvoicePrintRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.InvoicePrintResult;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.PrintInvoiceResponse;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceserver.distributeInvoice.DistributeInvoiceRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceserver.distributeInvoice.DistributeInvoiceResponse;
import com.deloitte.tms.vat.inf.taxinfo.service.ProcessTaxInterface;
import com.deloitte.tms.vat.inf.taxinfo.service.TaxObjectSerialize;

@Component(value=ProcessTaxInterface.TEST_BEAN_ID)
public class TestProcessTaxInterfaceImpl implements ProcessTaxInterface{
	
	String serviceUrl="http://10.245.5.107:8080/axis2/services/SajtIssueInvoiceService";
	static TaxObjectSerialize aisinoTaxObjectSerialize;
	static String machine_tax_id;
	static String machine_id; 
	static{
		aisinoTaxObjectSerialize = new AisinoSHTaxObjectSerializeImpl();
		machine_tax_id = "420100999999336";
		machine_tax_id="0";
	}
	

	@Override
	public void processIssueInvoiceResponse(InvoiceIssueRequest request,InvoiceIssueResponse response) {
		try{
			
			String invoiceString = ObjectToXMLUtils.formatXML(aisinoTaxObjectSerialize.outObject(request));
			System.out.println(invoiceString);
			SajtIssueInvoiceServicePortType service = new SajtIssueInvoiceServiceLocator().getSajtIssueInvoiceServiceHttpSoap11Endpoint(new java.net.URL(serviceUrl));
			
			SajtIssueInvoiceResponse sajtResponse = service.saveDocument(invoiceString);
			System.out.println(sajtResponse.getStatus());
			
			//打印发票成功
			if( "S".equals(sajtResponse.getStatus())||"s".equals(sajtResponse.getStatus())){
				//获取开具代码和开局号码
				String cmdMessageString = sajtResponse.getCmdMessage();
				String strCode = getINVInformation(cmdMessageString,"<binvcode>","</binvcode>");
				String strNum = getINVInformation(cmdMessageString,"<binvnr>","</binvnr>");
				
				String printCommand = GeneratPrintCommand(strCode,strNum,2);
				System.out.println(printCommand);
				
				sajtResponse = service.saveDocument(printCommand);
				System.out.println(sajtResponse.getStatus());
				if( "S".equals(sajtResponse.getStatus())||"s".equals(sajtResponse.getStatus())){
					System.out.println("发票打印成功！！");
				}else{
					System.out.println("发票打印失败！！"+sajtResponse.getCmdMessage());
				}
				
			}else{
				System.out.println(sajtResponse.getCmdMessage());
				System.out.println("获取code和no失败！！");
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		
		
		/*AssertHelper.notEmpty_assert(response, "返回数据不能为空");
		for(InvoiceIssueResult result:response.getResults()){
			if(result.isIssucess()){//开具成功
				
				
				
			}else{//开具失败,异步处理,不抛出异常
				
			}
		}*/
	}

	@Override
	public void processPrintInvoiceResponse(InvoicePrintRequest request,PrintInvoiceResponse response) {
		AssertHelper.notEmpty_assert(response, "返回数据不能为空");
		for(InvoicePrintResult result:response.getResults()){
			if(result.isIssucess()){//打印成功
				
			}else{//打印失败,异步处理,不抛出异常
				
			}
		}
	}

	@Override
	public void processDistributeInvoiceResponse(DistributeInvoiceRequest request,
			DistributeInvoiceResponse response) {
		AssertHelper.notEmpty_assert(response, "返回数据不能为空");
		AssertHelper.notEmpty_assert(response.getErrorinfo(), "返回数据不能为空");
		if(response.getErrorinfo().isIssucess()){//成功
			
		}else{//失败
			
		}
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
