
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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.deloitte.tms.pl.core.commons.utils.FileUtils;
import com.deloitte.tms.pl.core.commons.utils.XmlUtils;
import com.deloitte.tms.vat.inf.taxinfo.aisino.service.AisinoServerTaxObjectSerializeImpl;
import com.deloitte.tms.vat.inf.taxinfo.aisino.service.AisinoTaxObjectSerializeImpl;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueResponse;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueResult;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceserver.distributeInvoice.DistributeInvoiceResponse;


/**  
 * @author bo.wang
 * @create 2016年4月8日 下午3:32:47 
 */

public class InvoiceRevertBaseTest {
	
	AisinoTaxObjectSerializeImpl aisinoTaxObjectSerialize;
	AisinoServerTaxObjectSerializeImpl aisinoServerTaxObjectSerialize;
	
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
		String xml=FileUtils.getFileContentByClassLocation(InvoiceRevertBaseTest.class,"paraseSimple.xml");
		Document document = XmlUtils.getDocument(xml); 
		Element rootElement=XmlUtils.getRootElement(document, "refp");
		InvoiceIssueResult result=(InvoiceIssueResult) aisinoTaxObjectSerialize.parseComplexObject(rootElement, InvoiceIssueResult.class);
		assertEquals("-2",result.getErroCode());
	}
	/**
	 * 测试集合对象
	 * @throws Exception
	 */
	@Test
	public void testCollectionObject() throws Exception {
		String xml=FileUtils.getFileContentByClassLocation(InvoiceRevertBaseTest.class,"paraseSimple.xml");
		Document document = XmlUtils.getDocument(xml); 
		Element rootElement=XmlUtils.getRootElement(document, "err");
		InvoiceIssueResponse result=(InvoiceIssueResponse) aisinoTaxObjectSerialize.parseComplexObject(rootElement, InvoiceIssueResponse.class);
		assertEquals("-2",result.getResults().get(0).getErroCode());
	}
	/**
	 * 测试InvoiceIssueResponse对象
	 * @throws Exception
	 */
	@Test
	public void testInvoiceIssueResponseErro() throws Exception {
		String xml=FileUtils.getFileContentByClassLocation(InvoiceRevertBaseTest.class,"InvoiceIssueResultErro.xml");
		Document document = XmlUtils.getDocument(xml); 
		Element rootElement=XmlUtils.getRootElement(document, "err");
		InvoiceIssueResponse result=(InvoiceIssueResponse) aisinoTaxObjectSerialize.parseComplexObject(rootElement, InvoiceIssueResponse.class);
		assertEquals("4001",result.getResults().get(0).getErroCode());
	}
	@Test
	public void testInvoiceIssueResponseErro1() throws Exception {
		String xml=FileUtils.getFileContentByClassLocation(InvoiceRevertBaseTest.class,"InvoiceIssueResultErro.xml");
		InvoiceIssueResponse result=(InvoiceIssueResponse) aisinoTaxObjectSerialize.responseToObject(xml,InvoiceIssueResponse.class);
		assertEquals("4001",result.getResults().get(0).getErroCode());
	}
	/**
	 * 测试InvoiceIssueResponse对象
	 * @throws Exception
	 */
	@Test
	public void testInvoiceIssueResponseSucess() throws Exception {
		String xml=FileUtils.getFileContentByClassLocation(InvoiceRevertBaseTest.class,"InvoiceIssueResultSucess.xml");
		Document document = XmlUtils.getDocument(xml); 
		Element rootElement=XmlUtils.getRootElement(document, "err");
		InvoiceIssueResponse result=(InvoiceIssueResponse) aisinoTaxObjectSerialize.parseComplexObject(rootElement, InvoiceIssueResponse.class);
		assertEquals("4011",result.getResults().get(0).getErroCode());
		assertEquals(5, result.getResults().size());
	}
	@Test
	public void testInvoiceIssueResponseSucess1() throws Exception {
		String xml=FileUtils.getFileContentByClassLocation(InvoiceRevertBaseTest.class,"InvoiceIssueResultSucess.xml");
		InvoiceIssueResponse result=(InvoiceIssueResponse) aisinoTaxObjectSerialize.responseToObject(xml,InvoiceIssueResponse.class);
		assertEquals("4011",result.getResults().get(0).getErroCode());
		assertEquals(5, result.getResults().size());
	}
	/**
	 * 测试InvoiceIssueResponse对象
	 * @throws Exception
	 */
	@Test
	public void testDistributeInvoiceResponseErro() throws Exception {
		String xml=FileUtils.getFileContentByClassLocation(InvoiceRevertBaseTest.class,"DistributeInvoiceResponseErro.xml");
		DistributeInvoiceResponse result=(DistributeInvoiceResponse) aisinoServerTaxObjectSerialize.responseToObject(xml, DistributeInvoiceResponse.class);
		assertEquals("Q100",result.getCode());
	}
	/**
	 * 测试InvoiceIssueResponse对象
	 * @throws Exception
	 */
	@Test
	public void testDistributeInvoiceResponseSucess() throws Exception {
		String xml=FileUtils.getFileContentByClassLocation(InvoiceRevertBaseTest.class,"DistributeInvoiceResponseSucess.xml");
		DistributeInvoiceResponse result=(DistributeInvoiceResponse) aisinoServerTaxObjectSerialize.responseToObject(xml, DistributeInvoiceResponse.class);
		assertEquals("0000",result.getCode());
	}
}
