package com.deloitte.tms.base.taxsetting.model;

public class TmsMdOrgBsnStructuresInParam extends TmsMdOrgBsnStructures {


	private String orgName;
	
	private String orgCode;
	
	private String accdflexStructuresCode;
	
	private String accdflexStructuresDescription; 
	
	private String bsnflexStructuresCode;
	
	private String bsnflexStructuresDescription;
	
    private String legalEntityCode;
	
    private String legalEntityName;;

    


	public String getLegalEntityCode() {
		return legalEntityCode;
	}

	public void setLegalEntityCode(String legalEntityCode) {
		this.legalEntityCode = legalEntityCode;
	}

	public String getLegalEntityName() {
		return legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		this.legalEntityName = legalEntityName;
	}

	public String getAccdflexStructuresCode() {
		return accdflexStructuresCode;
	}

	public void setAccdflexStructuresCode(String accdflexStructuresCode) {
		this.accdflexStructuresCode = accdflexStructuresCode;
	}

	public String getAccdflexStructuresDescription() {
		return accdflexStructuresDescription;
	}

	public void setAccdflexStructuresDescription(
			String accdflexStructuresDescription) {
		this.accdflexStructuresDescription = accdflexStructuresDescription;
	}

	public String getBsnflexStructuresCode() {
		return bsnflexStructuresCode;
	}

	public void setBsnflexStructuresCode(String bsnflexStructuresCode) {
		this.bsnflexStructuresCode = bsnflexStructuresCode;
	}

	public String getBsnflexStructuresDescription() {
		return bsnflexStructuresDescription;
	}

	public void setBsnflexStructuresDescription(String bsnflexStructuresDescription) {
		this.bsnflexStructuresDescription = bsnflexStructuresDescription;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	
	
	
	
}
