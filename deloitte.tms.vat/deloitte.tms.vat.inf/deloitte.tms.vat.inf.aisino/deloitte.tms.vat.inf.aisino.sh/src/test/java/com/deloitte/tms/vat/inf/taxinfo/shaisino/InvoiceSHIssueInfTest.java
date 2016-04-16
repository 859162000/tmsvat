
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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import com.deloitte.tms.pl.core.commons.utils.ObjectToXMLUtils;
import com.deloitte.tms.vat.inf.taxinfo.aisino.enums.FPZLEnums;
import com.deloitte.tms.vat.inf.taxinfo.aisino.enums.HSBZEnums;
import com.deloitte.tms.vat.inf.taxinfo.aisino.enums.KPBZEnums;
import com.deloitte.tms.vat.inf.taxinfo.aisino.enums.QDBZEnums;
import com.deloitte.tms.vat.inf.taxinfo.aisino.service.AisinoSHTaxObjectSerializeImpl;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssue;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueDetail;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueHead;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.InvoicePrint;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.InvoicePrintRequest;
import com.deloitte.tms.vat.inf.taxinfo.service.TaxObjectSerialize;


/**  
 * @author bo.wang
 * @create 2016年4月8日 下午3:32:47 
 */

public class InvoiceSHIssueInfTest {
	
	TaxObjectSerialize aisinoTaxObjectSerialize;
	
	String serviceUrl;
	String  machine_tax_id;
	String machine_id;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		aisinoTaxObjectSerialize=new AisinoSHTaxObjectSerializeImpl();
		serviceUrl="http://10.245.5.106:8080/axis2/services/SajtIssueInvoiceService";
		machine_tax_id="420100999999336";
		machine_id="0"; 
//		serviceUrl="http://192.168.56.180:8080/TaxPBKPServer/kpservices/KpWebService";
	}

	@After
	public void tearDown() throws Exception {
		
	}
	@Test
	public void testIssueInvoice() throws Exception{
		
		InvoiceIssueRequest request=new InvoiceIssueRequest();
		List<InvoiceIssue> prints=new ArrayList<InvoiceIssue>();
		for(int i=0;i<5;i++){
			InvoiceIssue print=new InvoiceIssue();
			InvoiceIssueHead head=new InvoiceIssueHead();
			head.setMachineTaxNo(machine_tax_id);//指定的开票机税号
			head.setMachineNo(machine_id);
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
		
		System.out.println("报文已经产生,请自己掉上海航信接口,请记录下调试过程中产生的返回报文,这个很重要");
		System.out.println("报文已经产生,请自己掉上海航信接口,请记录下调试过程中产生的返回报文,这个很重要");
		System.out.println("报文已经产生,请自己掉上海航信接口,请记录下调试过程中产生的返回报文,这个很重要");
		
//		SajtIssueInvoiceServiceStub stub=new SajtIssueInvoiceServiceStub(serviceUrl);
//		SaveDocument sd=new SaveDocument();
//		sd.setCommand(result);
//		SaveDocumentResponse response = stub.saveDocument(sd);
//		//S成功，F失败  推送信息
//		if(StringUtils.endsWithIgnoreCase(response.get_return().getStatus(), "S")){
//			String returnMsg = response.get_return().getCmdMessage();
			 
//			String str_code = getINVInformation(returnMsg,"<binvcode>","</binvcode>");
//			String str_num = getINVInformation(returnMsg,"<binvnr>","</binvnr>");
//			
//			String pcommand=GeneratPrintCommand(str_code,str_num,0);
//			SaveDocument sd2=new SaveDocument();
//			sd2.setCommand(pcommand);
//			response = stub.saveDocument(sd2);
//			//打印发票成功
//			if(StringUtils.endsWithIgnoreCase(response.get_return().getStatus(), "S")){
//				return 0;
//			}else{
//				return 1;
//			}
//		}else{
//			//错误信息
//		}
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
		
		System.out.println("报文已经产生,请自己掉上海航信接口,请记录下调试过程中产生的返回报文,这个很重要");
		System.out.println("报文已经产生,请自己掉上海航信接口,请记录下调试过程中产生的返回报文,这个很重要");
		System.out.println("报文已经产生,请自己掉上海航信接口,请记录下调试过程中产生的返回报文,这个很重要");
		
//		WebServicePortType service = new WebServiceLocator().getWebServiceHttpPort(new java.net.URL(serviceUrl));
//		String response = service.printInvoice(result);
//		response=ObjectToXMLUtils.formatXML(response);
//		String compare=FileUtils.getFileContentByClassLocation(InvoiceIssueInfTest.class,"printInvoice_erro.xml");
//		compare=ObjectToXMLUtils.formatXML(compare);
//		System.out.println(response);
//		assertEquals(response,compare);
	}
}
