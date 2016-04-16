package com.deloitte.tms.vat.salesinvoice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

@Entity
@Table(name=TempTmsCrvatInvoiceReqL.TABLE)
public class TempTmsCrvatInvoiceReqL  {

	public static final String TABLE = "TEMP_TMS_CRVAT_INVOICE_REQ_L";
	public static final String SEQ = TABLE;

    @Id
	/*@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
*/
    @Column(name="UUID", length=36)
	@ModelProperty(comment="主键id")
	private String id;

    @Column(name="INVOICE_REQL_ID", length=36)
	@ModelProperty(comment="申请单行id")
	private String invoiceReqlId;

    @Column(name="INVOICE_TRX_ID", length=36)
	@ModelProperty(comment="交易明细id")
	private String invoiceTrxId;

    @Column(name="INVOICE_REQH_ID", length=36)
	@ModelProperty(comment="申请单头id")
	private String invoiceReqhId;

    @Column(name="OPERATOR_USER", length=100)
	@ModelProperty(comment="操作人")
	private String operatorUser;

    @Column(name="ORG_CODE", length=100)
	@ModelProperty(comment="组织机构代码")
	private String orgCode;

    @Column(name="ORG_NAME", length=100)
	@ModelProperty(comment="组织机构名称")
	private String orgName;

    @Column(name="TAX_TRXTYPE_NAME", length=256)
	@ModelProperty(comment="涉税交易类型名称")
	private String taxTrxtypeName;

    @Column(name="TAX_TRXTYPE_CODE", length=256)
	@ModelProperty(comment="涉税交易类型编码")
	private String taxTrxtypeCode;

    @Column(name="LEGAL_ENTITY_NAME", length=256)
	@ModelProperty(comment="销方单位名称")
	private String legalEntityName;

    @Column(name="LEGAL_ENTITY_CODE", length=256)
	@ModelProperty(comment="销方单位识别号")
	private String legalEntityCode;

    @Column(name="TRX_NUMBER", length=256)
	@ModelProperty(comment="交易流水号")
	private String trxNumber;

    @Column(name="TRX_DATE", length=256)
	@ModelProperty(comment="交易日期")
	private String trxDate;

    @Column(name="INVENTORY_ITEM_NUMBER", length=256)
	@ModelProperty(comment="商品及服务编码")
	private String inventoryItemNumber;

    @Column(name="INVENTORY_ITEM_DESCRIPTION", length=256)
	@ModelProperty(comment="商品及服务名称")
	private String inventoryItemDescription;

    @Column(name="INVOICE_AMOUNT", length=256)
	@ModelProperty(comment="合计金额")
	private String invoiceAmount;

    @Column(name="TAX_RATE", length=256)
	@ModelProperty(comment="税率")
	private String taxRate;

    @Column(name="USERFUL_AMOUNT", length=256)
	@ModelProperty(comment="可开票金额")
	private String userfulAmount;

    @Column(name="USERED_AMOUNT", length=256)
	@ModelProperty(comment="已开票金额")
	private String useredAmount;
    
    @Column(name="IS_EXITS_CUSTOMER", length=1)
   	@ModelProperty(comment="是否无主交易开票申请")
   	private String isExitsCustomer;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInvoiceReqlId() {
		return invoiceReqlId;
	}

	public void setInvoiceReqlId(String invoiceReqlId) {
		this.invoiceReqlId = invoiceReqlId;
	}

	public String getInvoiceTrxId() {
		return invoiceTrxId;
	}

	public void setInvoiceTrxId(String invoiceTrxId) {
		this.invoiceTrxId = invoiceTrxId;
	}

	public String getInvoiceReqhId() {
		return invoiceReqhId;
	}

	public void setInvoiceReqhId(String invoiceReqhId) {
		this.invoiceReqhId = invoiceReqhId;
	}

	public String getOperatorUser() {
		return operatorUser;
	}

	public void setOperatorUser(String operatorUser) {
		this.operatorUser = operatorUser;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getTaxTrxtypeName() {
		return taxTrxtypeName;
	}

	public void setTaxTrxtypeName(String taxTrxtypeName) {
		this.taxTrxtypeName = taxTrxtypeName;
	}

	public String getTaxTrxtypeCode() {
		return taxTrxtypeCode;
	}

	public void setTaxTrxtypeCode(String taxTrxtypeCode) {
		this.taxTrxtypeCode = taxTrxtypeCode;
	}

	public String getLegalEntityName() {
		return legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		this.legalEntityName = legalEntityName;
	}

	public String getLegalEntityCode() {
		return legalEntityCode;
	}

	public void setLegalEntityCode(String legalEntityCode) {
		this.legalEntityCode = legalEntityCode;
	}

	public String getTrxNumber() {
		return trxNumber;
	}

	public void setTrxNumber(String trxNumber) {
		this.trxNumber = trxNumber;
	}

	public String getTrxDate() {
		return trxDate;
	}

	public void setTrxDate(String trxDate) {
		this.trxDate = trxDate;
	}

	public String getInventoryItemNumber() {
		return inventoryItemNumber;
	}

	public void setInventoryItemNumber(String inventoryItemNumber) {
		this.inventoryItemNumber = inventoryItemNumber;
	}

	public String getInventoryItemDescription() {
		return inventoryItemDescription;
	}

	public void setInventoryItemDescription(String inventoryItemDescription) {
		this.inventoryItemDescription = inventoryItemDescription;
	}

	public String getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(String invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public String getUserfulAmount() {
		return userfulAmount;
	}

	public void setUserfulAmount(String userfulAmount) {
		this.userfulAmount = userfulAmount;
	}

	public String getUseredAmount() {
		return useredAmount;
	}

	public void setUseredAmount(String useredAmount) {
		this.useredAmount = useredAmount;
	}

	public String getIsExitsCustomer() {
		return isExitsCustomer;
	}

	public void setIsExitsCustomer(String isExitsCustomer) {
		this.isExitsCustomer = isExitsCustomer;
	}
	

}



