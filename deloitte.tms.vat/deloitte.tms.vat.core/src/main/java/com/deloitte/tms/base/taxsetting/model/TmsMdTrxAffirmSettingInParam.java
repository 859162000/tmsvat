package com.deloitte.tms.base.taxsetting.model;

public class TmsMdTrxAffirmSettingInParam extends TmsMdTrxAffirmSetting {
	//组织名称
	private String orgName;
	//涉税交易编码
	private String taxTrxTypeCode;
	//涉税交易名称
	private String taxTrxTypeName;
	//税种
	private String categoryName;
	//税目
	private String itemName;
	//商品及服务编码
	private String inventoryItemNumber;
	//商品及服务名称
	private String inventoryItemDescripton;
	
	public String getInventoryItemNumber() {
		return inventoryItemNumber;
	}
	public void setInventoryItemNumber(String inventoryItemNumber) {
		this.inventoryItemNumber = inventoryItemNumber;
	}
	public String getInventoryItemDescripton() {
		return inventoryItemDescripton;
	}
	public void setInventoryItemDescripton(String inventoryItemDescripton) {
		this.inventoryItemDescripton = inventoryItemDescripton;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getTaxTrxTypeCode() {
		return taxTrxTypeCode;
	}
	public void setTaxTrxTypeCode(String taxTrxTypeCode) {
		this.taxTrxTypeCode = taxTrxTypeCode;
	}
	public String getTaxTrxTypeName() {
		return taxTrxTypeName;
	}
	public void setTaxTrxTypeName(String taxTrxTypeName) {
		this.taxTrxTypeName = taxTrxTypeName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
	

}
