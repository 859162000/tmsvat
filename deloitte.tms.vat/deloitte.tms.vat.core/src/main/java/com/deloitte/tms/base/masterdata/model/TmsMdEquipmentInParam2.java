package com.deloitte.tms.base.masterdata.model;

import java.io.Serializable;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;

public class TmsMdEquipmentInParam2  implements Serializable{

	
	
	/**  
	 * serialVersionUID  
	 */  
	
	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> defaultMap;
	public Map<String, Object> getDefaultMap() {
		return defaultMap;
	}
	public void setDefaultMap(Map<String, Object> defaultMap) {
		this.defaultMap = defaultMap;
	}

	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	private String equipmentName;
	private String equipmentCode;
	private String equipmentType;
	private String isDefault;
	private String parentEquipmentName;
	private String equipmentIp;
	
	public String getEquipmentIp() {
		return equipmentIp;
	}
	public void setEquipmentIp(String equipmentIp) {
		this.equipmentIp = equipmentIp;
	}
	public String getParentEquipmentName() {
		return parentEquipmentName;
	}
	public void setParentEquipmentName(String parentEquipmentName) {
		this.parentEquipmentName = parentEquipmentName;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getEquipmentCode() {
		return equipmentCode;
	}
	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}
	public String getEquipmentType() {
		return equipmentType;
	}
	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	
	public TmsMdEquipmentInParam2() {
		super();
		
	}
	
	public TmsMdEquipmentInParam2(String isDefault, TmsMdEquipment equ) {
		
		
		this.id=equ.getId();
		this.isDefault=isDefault;
		this.equipmentName=equ.getEquipmentName();
		this.equipmentCode=equ.getEquipmentCode();
		this.equipmentType=equ.getEquipmentType();
		this.equipmentIp  =equ.getEquipmentIp();
		
		String parentEquipmentName="";
		
		this.parentEquipmentName= parentEquipmentName;
	}
	
	public TmsMdEquipmentInParam2(TmsMdEquipment tmsMdEquipment){
		/**
		 * equipmentName
equipmentCode
equipmentType
equipmentIp
		 */
		String parentEquipmentName="";
		
		this.id=tmsMdEquipment.getId();
		this.parentEquipmentName= parentEquipmentName;
		this.equipmentName=tmsMdEquipment.getEquipmentName();
		this.equipmentCode=tmsMdEquipment.getEquipmentCode();
		this.equipmentType=tmsMdEquipment.getEquipmentType();
		this.equipmentIp  =tmsMdEquipment.getEquipmentIp();
		
	}
	
	
	
}
