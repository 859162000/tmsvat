
/**    
 * Copyright (C),Deloitte
 * @FileName: CustomerInParam.java  
 * @Package: com.deloitte.tms.base.masterdata.model  
 * @Description: //模块目的、功能描述  
 * @Author liazhou  
 * @Date 2016年3月16日 上午10:44:47  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
*/  

package com.deloitte.tms.base.masterdata.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;




/**  
 *〈一句话功能简述〉
 * 功能详细描述
 * @author liazhou
 * @create 2016年3月16日 上午10:44:47 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class CustomerInParam{
	
	private String id;
	
	private String custDepositBankAccountNum;

	private String custDepositBankName;

	private String custDepositBankNumber;

	private String custRegistrationCode;
	
	private String isInvoiceProviding;
	
	private String isInvoiceProvidingName;
	
	private Date custRegistrationDate;
	
	private String custRegistrationNumber;

	private String customerName;

	private String customerNumber;

	private String customerType;
	private String customerTypeName; 

	
	private String enabledFlag;

	private String custLegalEntityType;
	private String custLegalEntityTypeName;

	private String custRegistrationAddress;
	
	private String contactName;
	
	private String contactPhone;
	

	private String gsnRegistrationNumber;

	private String invoicingType;
	
	private Date startDate;
	
	private Date endDate;
	
	private String isAppointInvoice;
	
	private Date appointEndDate;

	private String appointIntervalUomCode;

	private String appointInvoiceCategory;

	private String appointInvoiceOrgCode;

	private String appointRunInterval;

	private Date appointStartDate;


	private String billingPeriod;


	private String printPeriodName;

	private String reqInvoiceRange;

	private String sourceCode;

	private String sourceCustomerCode;

	private String sourceCustomerId;

	private String sourceCustomerName;

	private String sourceCustomerType;
	
	private List<CustBankAccountInParam> accoutlist=new ArrayList<CustBankAccountInParam>();
	
	private List<CustomerSiteInParam> siteList= new ArrayList<CustomerSiteInParam>();

	public List<CustBankAccountInParam> getAccoutlist() {
		return accoutlist;
	}

	public void setAccoutlist(List<CustBankAccountInParam> accoutlist) {
		this.accoutlist = accoutlist;
	}

	public List<CustomerSiteInParam> getSiteList() {
		return siteList;
	}

	public void setSiteList(List<CustomerSiteInParam> siteList) {
		this.siteList = siteList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustLegalEntityTypeName() {
		return custLegalEntityTypeName;
	}

	public void setCustLegalEntityTypeName(String custLegalEntityTypeName) {
		this.custLegalEntityTypeName = custLegalEntityTypeName;
	}

	public String getCustDepositBankAccountNum() {
		return custDepositBankAccountNum;
	}

	public String getIsInvoiceProvidingName() {
		return isInvoiceProvidingName;
	}

	public void setIsInvoiceProvidingName(String isInvoiceProvidingName) {
		this.isInvoiceProvidingName = isInvoiceProvidingName;
	}

	public void setCustDepositBankAccountNum(String custDepositBankAccountNum) {
		this.custDepositBankAccountNum = custDepositBankAccountNum;
	}

	public String getCustDepositBankName() {
		return custDepositBankName;
	}

	public void setCustDepositBankName(String custDepositBankName) {
		this.custDepositBankName = custDepositBankName;
	}

	public String getCustDepositBankNumber() {
		return custDepositBankNumber;
	}

	public void setCustDepositBankNumber(String custDepositBankNumber) {
		this.custDepositBankNumber = custDepositBankNumber;
	}

	public String getCustRegistrationCode() {
		return custRegistrationCode;
	}

	public void setCustRegistrationCode(String custRegistrationCode) {
		this.custRegistrationCode = custRegistrationCode;
	}

	public String getIsInvoiceProviding() {
		return isInvoiceProviding;
	}

	public void setIsInvoiceProviding(String isInvoiceProviding) {
		this.isInvoiceProviding = isInvoiceProviding;
	}

	public Date getCustRegistrationDate() {
		return custRegistrationDate;
	}

	public void setCustRegistrationDate(Date custRegistrationDate) {
		this.custRegistrationDate = custRegistrationDate;
	}

	public String getCustRegistrationNumber() {
		return custRegistrationNumber;
	}

	public void setCustRegistrationNumber(String custRegistrationNumber) {
		this.custRegistrationNumber = custRegistrationNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	public String getCustLegalEntityType() {
		return custLegalEntityType;
	}

	public void setCustLegalEntityType(String custLegalEntityType) {
		this.custLegalEntityType = custLegalEntityType;
	}

	public String getCustRegistrationAddress() {
		return custRegistrationAddress;
	}

	public void setCustRegistrationAddress(String custRegistrationAddress) {
		this.custRegistrationAddress = custRegistrationAddress;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getGsnRegistrationNumber() {
		return gsnRegistrationNumber;
	}

	public void setGsnRegistrationNumber(String gsnRegistrationNumber) {
		this.gsnRegistrationNumber = gsnRegistrationNumber;
	}

	public String getInvoicingType() {
		return invoicingType;
	}

	public void setInvoicingType(String invoicingType) {
		this.invoicingType = invoicingType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getIsAppointInvoice() {
		return isAppointInvoice;
	}

	public void setIsAppointInvoice(String isAppointInvoice) {
		this.isAppointInvoice = isAppointInvoice;
	}

	public Date getAppointEndDate() {
		return appointEndDate;
	}

	public void setAppointEndDate(Date appointEndDate) {
		this.appointEndDate = appointEndDate;
	}

	public String getAppointIntervalUomCode() {
		return appointIntervalUomCode;
	}

	public void setAppointIntervalUomCode(String appointIntervalUomCode) {
		this.appointIntervalUomCode = appointIntervalUomCode;
	}

	public String getAppointInvoiceCategory() {
		return appointInvoiceCategory;
	}

	public void setAppointInvoiceCategory(String appointInvoiceCategory) {
		this.appointInvoiceCategory = appointInvoiceCategory;
	}

	public String getAppointInvoiceOrgCode() {
		return appointInvoiceOrgCode;
	}

	public void setAppointInvoiceOrgCode(String appointInvoiceOrgCode) {
		this.appointInvoiceOrgCode = appointInvoiceOrgCode;
	}

	public String getAppointRunInterval() {
		return appointRunInterval;
	}

	public void setAppointRunInterval(String appointRunInterval) {
		this.appointRunInterval = appointRunInterval;
	}

	public Date getAppointStartDate() {
		return appointStartDate;
	}

	public void setAppointStartDate(Date appointStartDate) {
		this.appointStartDate = appointStartDate;
	}

	public String getBillingPeriod() {
		return billingPeriod;
	}

	public void setBillingPeriod(String billingPeriod) {
		this.billingPeriod = billingPeriod;
	}

	public String getPrintPeriodName() {
		return printPeriodName;
	}

	public void setPrintPeriodName(String printPeriodName) {
		this.printPeriodName = printPeriodName;
	}

	public String getReqInvoiceRange() {
		return reqInvoiceRange;
	}

	public void setReqInvoiceRange(String reqInvoiceRange) {
		this.reqInvoiceRange = reqInvoiceRange;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getSourceCustomerCode() {
		return sourceCustomerCode;
	}

	public void setSourceCustomerCode(String sourceCustomerCode) {
		this.sourceCustomerCode = sourceCustomerCode;
	}

	public String getSourceCustomerId() {
		return sourceCustomerId;
	}

	public void setSourceCustomerId(String sourceCustomerId) {
		this.sourceCustomerId = sourceCustomerId;
	}

	public String getSourceCustomerName() {
		return sourceCustomerName;
	}

	public void setSourceCustomerName(String sourceCustomerName) {
		this.sourceCustomerName = sourceCustomerName;
	}

	public String getSourceCustomerType() {
		return sourceCustomerType;
	}

	public void setSourceCustomerType(String sourceCustomerType) {
		this.sourceCustomerType = sourceCustomerType;
	}

	public String getCustomerTypeName() {
		return customerTypeName;
	}

	public void setCustomerTypeName(String customerTypeName) {
		this.customerTypeName = customerTypeName;
	}

	

}
