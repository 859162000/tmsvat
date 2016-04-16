
/**    
 * Copyright (C),Deloitte
 * @FileName: InvoicePrintTest.java  
 * @Package: ling3.deloitte.tms.inf.aisino  
 * @Description: //模块目的、功能描述  
 * @Author bo.wang  
 * @Date 2016年4月8日 下午3:32:47  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.inf.taxinfo.aisino;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.deloitte.tms.pl.core.commons.utils.FileUtils;
import com.deloitte.tms.pl.core.commons.utils.ObjectToXMLUtils;
import com.deloitte.tms.vat.inf.taxinfo.aisino.service.AisinoServerTaxObjectSerializeImpl;
import com.deloitte.tms.vat.inf.taxinfo.aisino.service.AisinoTaxObjectSerializeImpl;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssue;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueDetail;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueHead;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.InvoicePrint;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.InvoicePrintRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceserver.distributeInvoice.DistributeInvoiceRequest;
import com.deloitte.tms.vat.inf.taxinfo.service.TaxObjectSerialize;


/**  
 * @author bo.wang
 * @create 2016年4月8日 下午3:32:47 
 */

public class InvoiceBaseTest {
	
	TaxObjectSerialize aisinoTaxObjectSerialize;
	TaxObjectSerialize aisinoServerTaxObjectSerialize;
	
	String serviceUrl;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		aisinoTaxObjectSerialize=new AisinoTaxObjectSerializeImpl();
		aisinoServerTaxObjectSerialize=new AisinoServerTaxObjectSerializeImpl();
		serviceUrl="http://192.168.33.201:7033/TaxPBserver/kpservices/KpWebService";
//		serviceUrl="http://192.168.56.180:8080/TaxPBKPServer/kpservices/KpWebService";
	}

	@After
	public void tearDown() throws Exception {
		
	}
	/**
	 * 测试简单的对象
	 * @throws Exception
	 */
	@Test
	public void testSimpleObject() throws Exception {
		InvoiceIssueDetail detail=new InvoiceIssueDetail();
		String result=ObjectToXMLUtils.formatXML(aisinoTaxObjectSerialize.outObject(detail));
		System.out.println(result);
		String compare=FileUtils.getFileContentByClassLocation(InvoiceBaseTest.class,"testObject.xml");
		compare=ObjectToXMLUtils.formatXML(compare);
		assertEquals(result,compare);
	}
	/**
	 * 测试集合对象
	 * @throws Exception
	 */
	@Test
	public void testCollection() throws Exception {
		List<InvoiceIssueDetail> details=new ArrayList<InvoiceIssueDetail>();
		for(int j=0;j<2;j++){
			InvoiceIssueDetail detail=new InvoiceIssueDetail();
			details.add(detail);
		}
		String result=ObjectToXMLUtils.formatXML(new AisinoTaxObjectSerializeImpl().outCollect(details, "record"));
		System.out.println(result);
		String compare=FileUtils.getFileContentByClassLocation(InvoiceBaseTest.class,"testCollection.xml");
		compare=ObjectToXMLUtils.formatXML(compare);
		assertEquals(result,compare);
	}
	/**
	 * 测试复杂对象
	 * @throws Exception
	 */
	@Test
	public void outComplexObject() throws Exception {
		InvoiceIssue print=new InvoiceIssue();
		InvoiceIssueHead head=new InvoiceIssueHead();
		print.setHead(head);
		for(int j=0;j<2;j++){
			InvoiceIssueDetail detail=new InvoiceIssueDetail();
			print.addDetail(detail);
		}		
		String result=ObjectToXMLUtils.formatXML(aisinoTaxObjectSerialize.outObject(print));
		System.out.println(result);
		String compare=FileUtils.getFileContentByClassLocation(InvoiceBaseTest.class,"outComplex.xml");
		compare=ObjectToXMLUtils.formatXML(compare);
		assertEquals(result,compare);
	}
	/**
	 * 测试带集合的复杂对象
	 * @throws Exception
	 */
	@Test
	public void testCollectionComplexObject() throws Exception {
		List<InvoiceIssue> prints=new ArrayList<InvoiceIssue>();
		for(int i=0;i<2;i++){
			InvoiceIssue print=new InvoiceIssue();
			InvoiceIssueHead head=new InvoiceIssueHead();
			print.setHead(head);
			for(int j=0;j<2;j++){
				InvoiceIssueDetail detail=new InvoiceIssueDetail();
				print.addDetail(detail);
			}	
			prints.add(print);
		}
		String result=ObjectToXMLUtils.formatXML(new AisinoTaxObjectSerializeImpl().outCollect(prints, "record"));
		System.out.println(result);
		String compare=FileUtils.getFileContentByClassLocation(InvoiceBaseTest.class,"collectionComplex_withoutrequest.xml");
		compare=ObjectToXMLUtils.formatXML(compare);
		assertEquals(result,compare);
	}
	/**
	 * 测试发票开具对象输出
	 * @throws Exception
	 */
	@Test
	public void testIssueRequest() throws Exception {
		InvoiceIssueRequest request=new InvoiceIssueRequest();
		List<InvoiceIssue> prints=new ArrayList<InvoiceIssue>();
		for(int i=0;i<2;i++){
			InvoiceIssue print=new InvoiceIssue();
			InvoiceIssueHead head=new InvoiceIssueHead();
			print.setHead(head);
			for(int j=0;j<2;j++){
				InvoiceIssueDetail detail=new InvoiceIssueDetail();
				print.addDetail(detail);
			}	
			prints.add(print);
		}
		request.setRecord(prints);
		String result=ObjectToXMLUtils.formatXML(aisinoTaxObjectSerialize.outObject(request));
		System.out.println(result);
		String compare=FileUtils.getFileContentByClassLocation(InvoiceBaseTest.class,"collectionComplex.xml");
		compare=ObjectToXMLUtils.formatXML(compare);
		assertEquals(result,compare);
	}
	/**
	 * 测试发票打印对象输出
	 * @throws Exception
	 */
	@Test
	public void testPrintRequest() throws Exception {
		InvoicePrintRequest printRequest=new InvoicePrintRequest();
		for(int i=0;i<10;i++){
			InvoicePrint print=new InvoicePrint();
			printRequest.addInvoicePrint(print);
		}
		String result=ObjectToXMLUtils.formatXML(aisinoTaxObjectSerialize.outObject(printRequest));
		System.out.println(result);
		String compare=FileUtils.getFileContentByClassLocation(InvoiceBaseTest.class,"invoiceprint.xml");
		compare=ObjectToXMLUtils.formatXML(compare);
		assertEquals(result,compare);
	}
	/**
	 * 测试发票打印对象输出
	 * @throws Exception
	 */
	@Test
	public void testDistributeInvoice() throws Exception {
		DistributeInvoiceRequest request=new DistributeInvoiceRequest();
		String result=ObjectToXMLUtils.formatXML(aisinoServerTaxObjectSerialize.outObject(request));
		System.out.println(result);
		String compare=FileUtils.getFileContentByClassLocation(InvoiceBaseTest.class,"distributeInvoice.xml");
		compare=ObjectToXMLUtils.formatXML(compare);
		assertEquals(result,compare);
	}
}
