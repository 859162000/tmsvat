package com.deloitte.tms.base.taxsetting.model;

public class TmsMdInventoryItemsInParam extends TmsMdInventoryItems {
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getInventoryCategoryName() {
		return inventoryCategoryName;
	}
	public void setInventoryCategoryName(String inventoryCategoryName) {
		this.inventoryCategoryName = inventoryCategoryName;
	}
	public String getTaxItemsName() {
		return taxItemsName;
	}
	public void setTaxItemsName(String taxItemsName) {
		this.taxItemsName = taxItemsName;
	}
	private String orgName;
	private String inventoryCategoryName;
	private String taxItemsName;

}
