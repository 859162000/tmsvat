
/**    
 * Copyright (C),Deloitte
 * @FileName: XmlUtils.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.socket  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月29日 下午1:53:08  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.vat.salesinvoice.jobs.socket;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.base.masterdata.model.CustomerSite;
import com.deloitte.tms.vat.salesinvoice.fileutils.JobDateUtils;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsMdCustomersInf;


/**  
 * 解析XML文件
 * @author stonshi
 * @create 2016年3月29日 下午1:53:08 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class XmlUtils {
	
	/** 
	 * XML转为实体
	 * @param returnMsgGbk
	 * @param fullPath 
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@SuppressWarnings("unchecked")
	public static Customer extractXmlToEntity(String returnMsgGbk, String fullPath) {
		System.out.println("$$$$$$$$$$$$$$$$$$$$extractXmlToEntity%%%%%%%%%%%%%%%%%");
		System.out.println("$$$$$$$$$$$$$$$$$$$$returnMsgGbk"+returnMsgGbk+"%%%%%%%%%%%%%%%%%");
		System.out.println("$$$$$$$$$$$$$$$$$$$$fullPath"+fullPath+"%%%%%%%%%%%%%%%%%");
		SAXBuilder builder = new SAXBuilder();
		File file = new File(fullPath);
		//List<Customer> customerList = new ArrayList<Customer>();
		Customer customer = new Customer();
		try {
			Document doc = builder.build(file);
			Element root = doc.getRootElement();
			
			List<Element> list = root.getChildren("LIST");
			for(Element listElement:list) {
				 List<Element> rows = listElement.getChildren("ROWS");
				 for(Element rowsElement:rows) {
					 customer.setCustomerNumber(rowsElement.getChildText("stdmsgcode"));//客户号
					 customer.setCustomerName(rowsElement.getChildText("std400cunm"));//客户名称
					 customer.setCustLegalEntityType(rowsElement.getChildText("std400taxp"));//纳税人类型
					 customer.setIsInvoiceProviding(rowsElement.getChildText("std400ivfg"));//是否需要开具增值税专用发票
					 customer.setCustRegistrationCode(rowsElement.getChildText("std400txid"));//纳税人识别号
					 customer.setCustDepositBankName(rowsElement.getChildText("std400ocnm"));//开户行名称
					 customer.setCustDepositBankAccountNum(rowsElement.getChildText("std400ac01"));//账号1
					 customer.setCustRegistrationAddress(rowsElement.getChildText("std400addr"));//地址
					 customer.setContactPhone(rowsElement.getChildText("std400telp"));//电话号码
					 customer.setContactName(rowsElement.getChildText("std400rlnm"));//联系人
					 customer.setContactPhone(rowsElement.getChildText("std400coph"));//联系人办公电话
					 //customer.(rowsElement.getChildText("std400coml"));//联系人邮箱
					 customer.setPrintPeriodName(rowsElement.getChildText("std400ivfq"));//发票打印频率
					 customer.setGsnRegistrationNumber(rowsElement.getChildText("std400uscc"));//统一社会信用代码
					 customer.setStartDate(JobDateUtils.dateParse(rowsElement.getChildText("std400mddt")));//变更日期
					 customer.setAttribute1(rowsElement.getChildText("std400spf6"));//备用6
					 customer.setAttribute1(rowsElement.getChildText("std400spf7"));//备用7
					 customer.setAttribute1(rowsElement.getChildText("std400spf8"));//备用8
					 customer.setCreatedBy(rowsElement.getChildText("std400crus"));//建立用户
					 customer.setBizOrgCode(rowsElement.getChildText("std400crdt"));//建立机构
					 customer.setCreateDate(JobDateUtils.dateParse(rowsElement.getChildText("stdmsgcode")));//建立日期
					 customer.setModifiedBy(rowsElement.getChildText("std400upus"));//更新用户
					 customer.setBizOrgCode(rowsElement.getChildText("std400upbr"));//更新机构
					 customer.setModifiedDate(JobDateUtils.dateParse(rowsElement.getChildText("std400updt")));//更新日期
					// customerList.add(customer);
				 }
			}
			
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return customer;
	}
	
	/*public static List<Customer> extractXmlToCCustomer(String returnMsgGbk, String fullPath) {
		SAXBuilder builder = new SAXBuilder();
		File file = new File(fullPath);
		List<Customer> customerList = new ArrayList<Customer>();
		try {
			Document doc = builder.build(file);
			Element root = doc.getRootElement();
			List<Element> list = root.getChildren("vats");
		}
		return null;
		
	}*/
	
	public static List<Customer> extractXACPXmlToEntity(String returnMsgGbk,String xmlPath) {
		SAXBuilder builder = new SAXBuilder();
		File file = new File(xmlPath);
		List<Customer> listCustomer = new ArrayList<Customer>();
		List<CustomerSite> listCustomerSite = new ArrayList<CustomerSite>();
		try {
			Document doc = builder.build(file);
			Element root = doc.getRootElement();
			List<Element> list = root.getChildren("vats");
			
			for(Element listElement:list) {
				//String successFlag = listElement.getChildText("is_success");
				 Customer customer = new Customer();
				 customer.setSourceCustomerId(listElement.getChildText("customer_id"));//客户ID
				 customer.setCustomerName(listElement.getChildText("taxpayer_name"));//纳税人名称
				// customer.set(listElement.getChildText("taxpayer_type"));税率
				 customer.setCustomerType(listElement.getChildText("customer_type"));//客户类型,1-个人客户,2-单位客户
				 customer.setCustLegalEntityType(listElement.getChildText("taxpayer_type"));//纳税人类型
				 customer.setCustRegistrationNumber(listElement.getChildText("taxpayer_id"));//纳税人识别号
				 customer.setCustDepositBankName(listElement.getChildText("bank_no"));//纳税人开户行名称
				 customer.setCustDepositBankAccountNum(listElement.getChildText("account_no"));//纳税人账号
				 customer.setCustRegistrationAddress(listElement.getChildText("company_address"));
				 customer.setContactPhone(listElement.getChildText("company_tel"));
				// customer.setCustomerName(listElement.getChildText("invoice_rule"));
				 customer.setPrintPeriodName(listElement.getChildText("invoice_print_frequency"));//发票打印频率
				 customer.setInvoicingType(listElement.getChildText("invoice_type"));
				 
				 CustomerSite tmsMdCustSite = new CustomerSite();
				 tmsMdCustSite.setSourceCustomerId(listElement.getChildText("customer_id"));
				 tmsMdCustSite.setIsDefault("0");
				 tmsMdCustSite.setEnabledFlag("0");
				 tmsMdCustSite.setRecipientName(listElement.getChildText("recipient_name"));
				 tmsMdCustSite.setRecipientAddr(listElement.getChildText("recipient_address"));
				 tmsMdCustSite.setRecipientPhone(listElement.getChildText("recipient_tel"));
				 tmsMdCustSite.setRecipientComp(listElement.getChildText("recipient_company"));
				 
				 listCustomer.add(customer);
				 listCustomerSite.add(tmsMdCustSite);
				 
			}
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listCustomer;
		
	}
}
