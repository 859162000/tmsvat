package com.deloitte.tms.base.masterdata.model;

public class TmsMdEquipmentInParam extends TmsMdEquipment {
	private String parentSeqNo;
	private String parentEquipmentName;

	public String getParentEquipmentName() {
		return parentEquipmentName;
	}

	public void setParentEquipmentName(String parentEquipmentName) {
		this.parentEquipmentName = parentEquipmentName;
	}

	public String getParentSeqNo() {
		return parentSeqNo;
	}

	public void setParentSeqNo(String parentSeqNo) {
		this.parentSeqNo = parentSeqNo;
	}

}
