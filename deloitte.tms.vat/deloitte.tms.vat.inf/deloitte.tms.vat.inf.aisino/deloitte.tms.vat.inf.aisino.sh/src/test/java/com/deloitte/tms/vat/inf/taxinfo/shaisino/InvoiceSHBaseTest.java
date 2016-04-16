
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

package com.deloitte.tms.vat.inf.taxinfo.shaisino;

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
import com.deloitte.tms.vat.inf.taxinfo.aisino.enums.FPZLEnums;
import com.deloitte.tms.vat.inf.taxinfo.aisino.service.AisinoSHTaxObjectSerializeImpl;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssue;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueDetail;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueHead;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueSHResult;
import com.deloitte.tms.vat.inf.taxinfo.service.TaxObjectSerialize;


/**  
 * @author bo.wang
 * @create 2016年4月8日 下午3:32:47 
 */

public class InvoiceSHBaseTest {
	
	TaxObjectSerialize aisinoTaxObjectSerialize;
//	TaxObjectSerialize aisinoServerTaxObjectSerialize;
	
	String serviceUrl;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		aisinoTaxObjectSerialize=new AisinoSHTaxObjectSerializeImpl();
//		aisinoServerTaxObjectSerialize=new AisinoServerTaxObjectSerializeImpl();
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
		InvoiceIssueRequest request=new InvoiceIssueRequest();
		List<InvoiceIssue> prints=new ArrayList<InvoiceIssue>();
		for(int i=0;i<2;i++){
			InvoiceIssue print=new InvoiceIssue();
			InvoiceIssueHead head=new InvoiceIssueHead();
			head.setInvoiceType(FPZLEnums.pp.getValue());
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
		String compare=FileUtils.getFileContentByClassLocation(InvoiceSHBaseTest.class,"issuebase.xml");
		compare=ObjectToXMLUtils.formatXML(compare);
		assertEquals(result,compare);
	}
	/**
	 * 测试InvoiceIssueResponse对象
	 * @throws Exception
	 */
	@Test
	public void testDistributeInvoiceResponseSucess() throws Exception {
		String xml=FileUtils.getFileContentByClassLocation(InvoiceSHBaseTest.class,"返回报文格式.xml");
		InvoiceIssueSHResult result=(InvoiceIssueSHResult) aisinoTaxObjectSerialize.responseToObject(xml, InvoiceIssueSHResult.class);
//		System.out.println(result.getRecord().get(0).getDetails().get(0).getTaxAmount());
		assertEquals("960.0", result.getRecord().get(0).getDetails().get(0).getTaxAmount().toString());
	}
}
