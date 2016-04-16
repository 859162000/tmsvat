
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

import com.aisino.kp.WebServiceLocator;
import com.aisino.kp.WebServicePortType;
import com.deloitte.tms.pl.core.commons.utils.FileUtils;
import com.deloitte.tms.pl.core.commons.utils.ObjectToXMLUtils;
import com.deloitte.tms.vat.inf.taxinfo.aisino.enums.FPZLEnums;
import com.deloitte.tms.vat.inf.taxinfo.aisino.enums.HSBZEnums;
import com.deloitte.tms.vat.inf.taxinfo.aisino.enums.KPBZEnums;
import com.deloitte.tms.vat.inf.taxinfo.aisino.enums.QDBZEnums;
import com.deloitte.tms.vat.inf.taxinfo.aisino.service.AisinoServerTaxObjectSerializeImpl;
import com.deloitte.tms.vat.inf.taxinfo.aisino.service.AisinoTaxObjectSerializeImpl;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssue;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueDetail;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueHead;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.InvoicePrint;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.InvoicePrintRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.queryinventory.QueryInventory;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.queryinventory.QueryInventoryRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceserver.distributeInvoice.DistributeInvoiceRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceserver.queryStock.QueryStockRequest;
import com.deloitte.tms.vat.inf.taxinfo.service.TaxObjectSerialize;


/**  
 * @author bo.wang
 * @create 2016年4月8日 下午3:32:47 
 */

public class InvoiceIssueInfTest {
	
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
	 * 测试发票打印错误信息
	 * @throws Exception
	 */
	@Test
	public void testServerConnect() throws Exception{
		InvoicePrintRequest printRequest=new InvoicePrintRequest();
		for(int i=0;i<1;i++){
			InvoicePrint print=new InvoicePrint();
			printRequest.addInvoicePrint(print);
		}
		String result=ObjectToXMLUtils.formatXML(aisinoTaxObjectSerialize.outObject(printRequest));
		System.out.println(result);
		WebServicePortType service = new WebServiceLocator().getWebServiceHttpPort(new java.net.URL(serviceUrl));
		String response = service.printInvoice(result);
		response=ObjectToXMLUtils.formatXML(response);
		String compare=FileUtils.getFileContentByClassLocation(InvoiceIssueInfTest.class,"printInvoice_erro.xml");
		compare=ObjectToXMLUtils.formatXML(compare);
		System.out.println(response);
		assertEquals(response,compare);
	}
	@Test
	public void testIssueInvoice() throws Exception{
		
		InvoiceIssueRequest request=new InvoiceIssueRequest();
		List<InvoiceIssue> prints=new ArrayList<InvoiceIssue>();
		for(int i=0;i<5;i++){
			InvoiceIssue print=new InvoiceIssue();
			InvoiceIssueHead head=new InvoiceIssueHead();
			head.setInvoiceType(FPZLEnums.zp.getValue());//发票种类	字符	2	是 //0 专票 2普票 11货运票 12机动车票
			head.setPurchaserName("ling");//购方名称	字符	100	是
			head.setPurchaserTaxNo("123456789");//购方税号	字符	20	是
			head.setPurchaserTel("18001846866");//购方地址电话	字符	100	否	
			head.setPurchaserBankNo("21213213");//购方银行账户	字符	100	否
			head.setBark("1111");//备注	字符	230	否	
			head.setReciver("ling");//收款人	字符	8	是	8个字节，4个汉字
			head.setChecker("ling");//复核人	字符	8	是	8个字节，4个汉字
			head.setDrawer("ling");//开票人	字符	8	是	8个字节，4个汉字
			head.setSalesBankNo("111");//销方银行账户	字符	100	否	
			head.setSalesBankTel("1111");//销方地址电话	字符	100	否
			head.setIsDetail(QDBZEnums.open.getValue());//QDBZ	清单标志	字符	2	是	固定值 0：不开具清单 1：开具清单
			head.setSalesDocNo("1234567");// XSDJBH	销售单据编号	字符	100	否	
			head.setInvoiceMethod(KPBZEnums.open.getValue());// KPBZ	开票标志	字符	2	是	固定值 0：开票 1：校验 2：空白作废下一张发票
			print.setHead(head);
			for(int j=0;j<5;j++){
				InvoiceIssueDetail detail=new InvoiceIssueDetail();
				detail.setCommodityName("test"+j);//SPMC	商品名称	字符	92	是	
				detail.setIsTax(HSBZEnums.exclude.getValue());//HSBZ	含税标志	字符	2	是	固定值 0：不含税 1：含税
				detail.setTaxRate(0.06);//SLV	税率	数值	10,6	是	
				detail.setInvoiceAmount(50000.00);//JE	金额	数值	16,2	是	
				detail.setUnitPrice(5000.00);//DJ	单价	数值	36,15	否	
				detail.setMeasurementUnit("test");//JLDW	计量单位	字符	22	否	
				detail.setSpecification("test");//GGXH	规格型号	字符	40	否	
				detail.setTaxAmount(3000.00);//SE	税额	数值	16,2	是	
				detail.setQuantity(10l);//SL	数量	数值	36,15	否
				print.addDetail(detail);
				//商品单价、数量和金额不符合计算关系
				//商品单价、数量和金额不符合计算关系
				//购买方纳税人识别号长度不正确三证合一后 6-20位
			}	
			prints.add(print);
		}
		request.setRecord(prints);
		
		String result=ObjectToXMLUtils.formatXML(aisinoTaxObjectSerialize.outObject(request));
		System.out.println(result);
		WebServicePortType service = new WebServiceLocator().getWebServiceHttpPort(new java.net.URL(serviceUrl));
		String response = service.issueInvoice(result);
		response=ObjectToXMLUtils.formatXML(response);
		String compare=FileUtils.getFileContentByClassLocation(InvoiceIssueInfTest.class,"printInvoice_erro.xml");
		compare=ObjectToXMLUtils.formatXML(compare);
		System.out.println(response);
//		assertEquals(response,compare);
	}
	/**
	 * 发票打印 可以多个
	 * 这里不校验发票是否打印了也不校验发票放得对不对
	 * @throws Exception
	 */
	@Test
	public void testPrintInvoice() throws Exception{		
		
		InvoicePrintRequest request=new InvoicePrintRequest();
		for(int i=0;i<1;i++){
			InvoicePrint print=new InvoicePrint();
			print.setInvoiceType(FPZLEnums.zp.getValue());
			print.setInvoiceCode("1100123530");
			print.setInvoiceNo("00076381");
			request.addInvoicePrint(print);
		}
		
		String result=ObjectToXMLUtils.formatXML(aisinoTaxObjectSerialize.outObject(request));
		System.out.println(result);
		WebServicePortType service = new WebServiceLocator().getWebServiceHttpPort(new java.net.URL(serviceUrl));
		String response = service.printInvoice(result);
		response=ObjectToXMLUtils.formatXML(response);
		String compare=FileUtils.getFileContentByClassLocation(InvoiceIssueInfTest.class,"printInvoice_erro.xml");
		compare=ObjectToXMLUtils.formatXML(compare);
		System.out.println(response);
//		assertEquals(response,compare);
	}
	/**
	 * 获取库存信息 查询终端某类别或多个类别的发票库存
	 * @throws Exception
	 */
	@Test
	public void testQueryInventoryRequest() throws Exception{		
		
		QueryInventoryRequest request=new QueryInventoryRequest();
		QueryInventory zp=new QueryInventory();
		zp.setFpzl(FPZLEnums.zp.getValue());
		request.addQueryInventory(zp);
		
		QueryInventory pp=new QueryInventory();
		pp.setFpzl(FPZLEnums.pp.getValue());
		request.addQueryInventory(pp);
		
		QueryInventory erro=new QueryInventory();
		erro.setFpzl("99");
		request.addQueryInventory(erro);
		
		String result=ObjectToXMLUtils.formatXML(aisinoTaxObjectSerialize.outObject(request));
		System.out.println(result);
		WebServicePortType service = new WebServiceLocator().getWebServiceHttpPort(new java.net.URL(serviceUrl));
		String response = service.queryInventory(result);
		response=ObjectToXMLUtils.formatXML(response);
		String compare=FileUtils.getFileContentByClassLocation(InvoiceIssueInfTest.class,"printInvoice_erro.xml");
		compare=ObjectToXMLUtils.formatXML(compare);
		System.out.println(response);
//		assertEquals(response,compare);
	}
	/**
	 * 开票服务器发票卷库存查询
	 * @throws Exception
	 */
	@Test
	public void testQueryStockRequest() throws Exception{		
		
		QueryStockRequest request=new QueryStockRequest();
		request.setIp("192.168.33.224");
		request.setPort("8080");//服务器web端口
		request.setNsrsbh("110101201603098");
		request.setKpfwqh("0");//非主 1,2,3排,发盘已经定了.
		request.setKpdh("0");
		//开票点号,就是key的号码 比如我的是6
		
		String result=ObjectToXMLUtils.formatXML(aisinoServerTaxObjectSerialize.outObject(request));
		System.out.println(result);
		WebServicePortType service = new WebServiceLocator().getWebServiceHttpPort(new java.net.URL(serviceUrl));
		String response = service.queryStock(result);
		response=ObjectToXMLUtils.formatXML(response);
		String compare=FileUtils.getFileContentByClassLocation(InvoiceIssueInfTest.class,"printInvoice_erro.xml");
		compare=ObjectToXMLUtils.formatXML(compare);
		System.out.println(response);
//		assertEquals(response,compare);
	}
	/**
	 * 开票服务器发票分配
	 * @throws Exception
	 */
	@Test
	public void testDistributeInvoiceRequest() throws Exception{		
		
		DistributeInvoiceRequest request=new DistributeInvoiceRequest();
		request.setIp("192.168.33.224");
		request.setPort("8080");//服务器web端口
		request.setTaxPayerNo("110101201603098");
		request.setServerCode("0");//非主 1,2,3排,发盘已经定了.
		request.setKeyNo("6");//开票点号,就是key的号码 比如我的是6
		request.setInoviceType(FPZLEnums.zp.getValue());//0-增值税专用发票； 2-增值税普通发票； 11-货物运输业增值税专用发票； 12-机动车销售统一发票
		request.setInvoiceCode("1100123530");
//		request.setStartNo("00077980");
		request.setStartNo("00077982");//分了2段 00077980+2=00077980,00077981
											//00077982+4=
		request.setInvoiceCount(4);
		
		String result=ObjectToXMLUtils.formatXML(aisinoServerTaxObjectSerialize.outObject(request));
		System.out.println(result);
		WebServicePortType service = new WebServiceLocator().getWebServiceHttpPort(new java.net.URL(serviceUrl));
		String response = service.distributeInvoice(result);
		response=ObjectToXMLUtils.formatXML(response);
		String compare=FileUtils.getFileContentByClassLocation(InvoiceIssueInfTest.class,"printInvoice_erro.xml");
		compare=ObjectToXMLUtils.formatXML(compare);
		System.out.println(response);
//		assertEquals(response,compare);
	}
	/**
	 * 开票服务器发票退回
	 * @throws Exception
	 */
	@Test
	public void testReturnInvoiceRequestRequest() throws Exception{		
		
		DistributeInvoiceRequest request=new DistributeInvoiceRequest();
		request.setIp("192.168.33.224");
		request.setPort("8080");//服务器web端口
		request.setTaxPayerNo("110101201603098");
		request.setServerCode("0");//非主 1,2,3排,发盘已经定了.
		request.setKeyNo("6");//开票点号,就是key的号码 比如我的是6
		request.setInoviceType(FPZLEnums.zp.getValue());//0-增值税专用发票； 2-增值税普通发票； 11-货物运输业增值税专用发票； 12-机动车销售统一发票
		request.setInvoiceCode("1100123530");
		request.setStartNo("00077980");
//		request.setStartNo("00077982");//分了2段 00077980+2=00077980,00077981
											//00077982+4=
		request.setInvoiceCount(2);
		//00077980+4 测试交叉
		
		String result=ObjectToXMLUtils.formatXML(aisinoServerTaxObjectSerialize.outObject(request));
		System.out.println(result);
		WebServicePortType service = new WebServiceLocator().getWebServiceHttpPort(new java.net.URL(serviceUrl));
		
		String response = service.returnInvoice(result);
		
		response=ObjectToXMLUtils.formatXML(response);
		String compare=FileUtils.getFileContentByClassLocation(InvoiceIssueInfTest.class,"printInvoice_erro.xml");
		compare=ObjectToXMLUtils.formatXML(compare);
		System.out.println(response);
//		assertEquals(response,compare);
	}
}
