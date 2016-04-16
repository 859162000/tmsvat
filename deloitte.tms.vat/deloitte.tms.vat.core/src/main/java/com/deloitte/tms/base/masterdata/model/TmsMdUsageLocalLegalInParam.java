package com.deloitte.tms.base.masterdata.model;

import java.io.Serializable;

public class TmsMdUsageLocalLegalInParam implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TmsMdUsageLocalLegal tmsMdUsageLocalLegal;
	
	private String legalEntityName;
		
	private String quitDateShow;
	
	private String effectDateShow;
	
	public String getQuitDateShow() {
		return quitDateShow;
	}

	public void setQuitDateShow(String quitDateShow) {
		this.quitDateShow = quitDateShow;
	}

	public String getEffectDateShow() {
		return effectDateShow;
	}

	public void setEffectDateShow(String effectDateShow) {
		this.effectDateShow = effectDateShow;
	}

	

	
	
	public TmsMdUsageLocalLegalInParam() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TmsMdUsageLocalLegalInParam(
			TmsMdUsageLocalLegal tmsMdUsageLocalLegal, String legalEntityName) {
		
		this.setTmsMdUsageLocalLegal(tmsMdUsageLocalLegal);
		this.setLegalEntityName(legalEntityName);
	}

	public TmsMdUsageLocalLegal getTmsMdUsageLocalLegal() {
		return tmsMdUsageLocalLegal;
	}

	public void setTmsMdUsageLocalLegal(TmsMdUsageLocalLegal tmsMdUsageLocalLegal) {
		this.tmsMdUsageLocalLegal = tmsMdUsageLocalLegal;
	}

	public String getLegalEntityName() {
		return legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		this.legalEntityName = legalEntityName;
	}

	@Override
	public String toString() {
		return "TmsMdUsageLocalLegalInParam [tmsMdUsageLocalLegal="
				+ tmsMdUsageLocalLegal + ", legalEntityName=" + legalEntityName
				+ ", quitDateShow=" + quitDateShow + ", effectDateShow="
				+ effectDateShow + "]";
	}

	
	
	
	
}
