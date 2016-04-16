package com.deloitte.tms.base.taxsetting.model;

public class TmsMdTrxCatBsnStrInParam extends TmsMdTrxCatBsnStr {
	
	//纳税主体名称
	private String legalEntityName;
	//业务组合名称
	private String bsnCombinationName;
	//组织名称
	private String orgName;
	//涉税交易类型枚举
	private String taxTrxTypeName;
	
	
	

	public String getTaxTrxTypeName() {
		return taxTrxTypeName;
	}

	public void setTaxTrxTypeName(String taxTrxTypeName) {
		this.taxTrxTypeName = taxTrxTypeName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getBsnCombinationName() {
		return bsnCombinationName;
	}

	public void setBsnCombinationName(String bsnCombinationName) {
		this.bsnCombinationName = bsnCombinationName;
	}

	public String getLegalEntityName() {
		return legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		this.legalEntityName = legalEntityName;
	}
	
	

}
