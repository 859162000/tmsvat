package com.deloitte.tms.base.masterdata.model;

import java.io.Serializable;

//public class TmsMdLegalEnablePrintInParam extends TmsMdLegalEnablePrint implements Serializable{
public class TmsMdLegalEnablePrintInParam  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TmsMdLegalEnablePrint tmsMdLegalEnablePrint;
	
	public TmsMdLegalEnablePrint getTmsMdLegalEnablePrint() {
		return tmsMdLegalEnablePrint;
	}

	public void setTmsMdLegalEnablePrint(TmsMdLegalEnablePrint tmsMdLegalEnablePrint) {
		this.tmsMdLegalEnablePrint = tmsMdLegalEnablePrint;
	}

	private String legalEntityName;
	
	
	public String getLegalEntityName() {
		return legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		this.legalEntityName = legalEntityName;
	}
	
	public TmsMdLegalEnablePrintInParam(){
		
	}
	
	public TmsMdLegalEnablePrintInParam(TmsMdLegalEnablePrint org, String legalEntityName ){
		
		this.setLegalEntityName(legalEntityName);
		
		this.setTmsMdLegalEnablePrint(org);
		
	}
	

}
