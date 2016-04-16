package com.deloitte.tms.base.masterdata.model;

import java.io.Serializable;

import javax.persistence.Column;

public class TmsMdLegalEquipmentInParam extends TmsMdLegalEquipment implements
		Serializable {

	/*
	 * @Column(name="EQUIPMENT_ID") private String equipmentId;
	 * 
	 * @Column(name="LEGAL_ENTITY_ID") private String legalEntityId;
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	private String equipmentName;

	private String legalEntityName;

	private String pageNumber;

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	private String pageSize;

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getLegalEntityName() {
		return legalEntityName;
	}

	public void setLegalEntityName(String legalEntityName) {
		this.legalEntityName = legalEntityName;
	}

}
