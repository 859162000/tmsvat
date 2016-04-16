package com.deloitte.tms.base.masterdata.model;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

public class ClientInParam {
	String id;
	String clientCode;
	String clientName;
	String clientEntityNum ;
	String clientType;
	String entityType;
	String isInv;
	long contactPhone;
	String bank;
	long bankNum;
	String compAddr;
	long compPhone;
	Date clientDate;
	String isUsed;
	Date sDate;
	Date eDate;
	String contactName;
	String billingPeriod;
	String recipientName;
	String recipientAddr;
	String recipientPhone;
	String recipientComp;
	String zipCode;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientEntityNum() {
		return clientEntityNum;
	}
	public void setClientEntityNum(String clientEntityNum) {
		this.clientEntityNum = clientEntityNum;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public String getIsInv() {
		return isInv;
	}
	public void setIsInv(String isInv) {
		this.isInv = isInv;
	}
	public long getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(long contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public long getBankNum() {
		return bankNum;
	}
	public void setBankNum(long bankNum) {
		this.bankNum = bankNum;
	}
	public String getCompAddr() {
		return compAddr;
	}
	public void setCompAddr(String compAddr) {
		this.compAddr = compAddr;
	}
	public long getCompPhone() {
		return compPhone;
	}
	public void setCompPhone(long compPhone) {
		this.compPhone = compPhone;
	}
	public Date getClientDate() {
		return clientDate;
	}
	public void setClientDate(Date clientDate) {
		this.clientDate = clientDate;
	}
	public String getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	public Date getsDate() {
		return sDate;
	}
	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}
	public Date geteDate() {
		return eDate;
	}
	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getBillingPeriod() {
		return billingPeriod;
	}
	public void setBillingPeriod(String billingPeriod) {
		this.billingPeriod = billingPeriod;
	}
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	public String getRecipientAddr() {
		return recipientAddr;
	}
	public void setRecipientAddr(String recipientAddr) {
		this.recipientAddr = recipientAddr;
	}
	public String getRecipientPhone() {
		return recipientPhone;
	}
	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}
	public String getRecipientComp() {
		return recipientComp;
	}
	public void setRecipientComp(String recipientComp) {
		this.recipientComp = recipientComp;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
	
}
