
package com.deloitte.tms.base.masterdata.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.base.taxsetting.model.Items;
import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;


@Entity
@Table(name = Client.TABLE)
@ModelProperty(comment = "客户关系")
public class Client extends BaseEntity {
	/**
	 * 
	 */
	public static final String TABLE = TablePreDef.TMS_BA_TAG+"CLIENT";
	public static final String SEQ = TABLE;
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2IdGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "CLIENT_ID",  length = 36)
	String id;
	
	@Column(name = "CLIENT_CODE",length=255,nullable=false)
	@ModelProperty(comment = "客户关系代码")
	String clientCode;
	
	@Column(name = "CLIENT_NAME",length=255,nullable=false)
	@ModelProperty(comment = "客户关系名称")
	String clientName;
	
	@Column(name = "CLIENT_ENTITY_NUM",length=36,nullable=false)
	@ModelProperty(comment = "纳税识别号")
	String clientEntityNum ;
	
	@Column(name = "CLIENT_TYPE",length=36,nullable=false)
	@ModelProperty(comment = "客户群体")
	String clientType;
	
	@Column(name = "ENTITY_TYPE",length=36,nullable=false)
	@ModelProperty(comment = "纳税人类型")
	String entityType;
	
	@Column(name = "IS_INV",length=1,nullable=false)
	@ModelProperty(comment = "是否开具发票 Y N")
	String isInv;
	
	@Column(name = "CONTACT_PHONE",nullable=false)
	@ModelProperty(comment = "联系电话")
	long contactPhone;
	
	@Column(name = "BANK",length=36,nullable=false)
	@ModelProperty(comment = "开户行")
	String bank;
	
	@Column(name = "BANK_NUM",nullable=false)
	@ModelProperty(comment = "开户账号")
	long bankNum;
	
	@Column(name = "COMP_ADDR",length=225,nullable=false)
	@ModelProperty(comment = "公司地址")
	String compAddr;
	
	@Column(name = "COMP_PHONE",nullable=false)
	@ModelProperty(comment = "公司电话")
	long compPhone;
	
	@Column(name = "CLIENT_DATE",nullable=false)
	@ModelProperty(comment = "登记日期")
	Date clientDate;
	
	@Column(name = "IS_USED",length=1,nullable=false)
	@ModelProperty(comment = "是有效 Y N")
	String isUsed;
	
	
	@Column(name = "S_DATE")
	@ModelProperty(comment = "开始时间")
	Date sDate;
	
	@Column(name = "E_DATE")
	@ModelProperty(comment = "结束时间")
	Date eDate;
	
	@Column(name = "CONTACT_NAME",length=255)
	@ModelProperty(comment = "联系人名称")
	String contactName;
	
	@Column(name = "BILLING_PERIOD",length=1)
	@ModelProperty(comment = "开票周期（M：月,S：季度）")
	String billingPeriod;
	
	
	@Column(name = "RECIPIENT_NAME",length=225)
	@ModelProperty(comment = "收件人姓名")
	String recipientName;
	
	@Column(name = "RECIPIENT_ADDR",length=225)
	@ModelProperty(comment = "收件人地址")
	String recipientAddr;
	
	@Column(name = "RECIPIENT_PHONE",length=36)
	@ModelProperty(comment = "收件人电话")
	String recipientPhone;
	
	@Column(name = "RECIPIENT_COMP",length=225)
	@ModelProperty(comment = "收件人公司")
	String recipientComp;
	
	@Column(name = "ZIP_CODE",length=10)
	@ModelProperty(comment = "邮政编码")
	String zipCode;
	
	@OneToMany
	List<ClientSec> clientSecs;



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

	public List<ClientSec> getClientSecs() {
		return clientSecs;
	}

	public void setClientSecs(List<ClientSec> clientSecs) {
		this.clientSecs = clientSecs;
	}
	
	
	
}
