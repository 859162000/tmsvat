/**    
 * Copyright (C),Deloitte
 * @FileName: Test.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.fileutils  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月23日 下午3:24:22  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.deloitte.tms.vat.salesinvoice.fileutils;

import java.io.File;
import java.io.Serializable;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.deloitte.tms.base.masterdata.model.Customer;

/**
 * 〈一句话功能简述〉 功能详细描述
 * 
 * @author stonshi
 * @create 2016年3月23日 下午3:24:22
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class ExtactCustomerXml {

	public static Customer extractCustomer(String filePath) throws Exception {
		File file = new File(filePath);
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);
		Element root = document.getRootElement();
		Customer customer = getElement(root);
		
		return customer;
		/*System.out.println(customer.getCustomerCode());
		System.out.println(customer.getCustomerName());
		System.out.println(customer.getLegalEntityType());
		System.out.println(customer.getCustRegistrationAddress());
		System.out.println(customer.getContactPhone());
		System.out.println(customer.getModifiedDate());*/

	}

	private static Customer getElement(Element root) {
		Iterator<?> iterator = root.elementIterator();
		Customer customer = new Customer();
		while (iterator.hasNext()) {
			Element element = (Element) iterator.next();
			String name = element.getName();
			String value = element.getTextTrim();
			if ("".equals(value) || value == "") {
				getElement(element);
			} else {
				/*if ("std400cuno".equals(name)) {
					customer.setCustomerCode(value);//客户号
				}*/
				if ("std400cunm".equals(name)) {
					customer.setCustomerName(value);//客户名称
				}
				/*if ("std400taxp".equals(name)) {
					customer.setLegalEntityType(value);//纳税人类型
				}*/
				if ("std400ivfg".equals(name)) {//是否需要开具增值税专用发票
					customer.setIsInvoiceProviding(value);
				}
				if ("std400txid".equals(name)) {
					customer.setCustRegistrationCode(value);//纳税人识别号
				}
				/*if ("std400ocnm".equals(name)) {
					customer.setBankBranchName(value);//开户行名称
				}
				if ("std400ac01".equals(name)) {
					customer.setBankAccountNum(value);//账号1
				}*/
				if ("std400addr".equals(name)) {
					customer.setCustRegistrationAddress(value);//地址
				}
				if ("std400telp".equals(name)) {
					customer.setContactPhone(value);//电话号码
				}
				if ("std400rlnm".equals(name)) {
					customer.setContactName(value);//联系人
				}
				if ("std400coph".equals(name)) {
					customer.setContactPhone(value);//联系人办公电话
				}
				if ("std400coml".equals(name)) {
					//customer.setc  //联系人邮箱
				}
				if ("std400ivfq".equals(name)) {
					//customer  //发票打印频率
				}
				if ("std400uscc".equals(name)) {
					//customer  //统一社会信用代码
				}
				if ("std400mddt".equals(name)) {
					customer.setStartDate(JobDateUtils.dateParse(value));//变更日期
				}
				if ("std400spf6".equals(name)) {
					//customer 备用
				}
				if ("std400spf7".equals(name)) {
					//customer  备用
				}
				if ("std400spf8".equals(name)) {
					//customer  备用
				}
				if ("std400crus".equals(name)) {
					customer.setCreatedBy(value);//建立用户
				}
				if ("std400crbr".equals(name)) {
					customer.setBizOrgCode(value);//建立机构
				}
				if ("std400crdt".equals(name)) {
					customer.setCreateDate(JobDateUtils.dateParse(value));//建立日期
				}
				if ("std400upus".equals(name)) {
					customer.setModifiedBy(value);//更新用户
				}
				if ("std400upbr".equals(name)) {
					//customer. //更新机构
				}
				if ("std400updt".equals(name)) {
					customer.setModifiedDate(JobDateUtils.dateParse(value));//更新日期
				}
			}

		}
		
		return customer;
	}

}
