package com.deloitte.tms.base.masterdata.model;

import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseRelation;
import com.deloitte.tms.util.Util;

@Entity
@Table(name=TmsMdLegalEquipment.TABLE)
@ModelProperty(comment="纳税主体与打印终端关系")
public class TmsMdLegalEquipment extends BaseRelation{
	
	public static final String TABLE = TablePreDef.TMS_MD_TAG+"LEGAL_EQUIPMENT";
	public static final String SEQ = TABLE;

	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name="LEGAL_EQUIPMENT_ID",length=36)
	private String id;
	
	@ModelProperty(comment="纳税主体ID")
	@Column(name="LEGAL_ENTITY_ID",length=36)
	private String legalEntityId;
	
	@ModelProperty(comment="终端ID")
	@Column(name="EQUIPMENT_ID",length=36)
	private String equipmentId;

	@ModelProperty(comment="父结点ID")
	@Column(name="PARENT_ID",length=36)
	private String parentId;
	
	@ModelProperty(comment="版本号")
	@Column(name="VERSION_NO")
	private Integer versionNo;
	
	@ModelProperty(comment="是否默认打印终端")
	@Column(name="IS_DEFAULT")
	private String isDefault;
	
	
	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	@Transient
	private String effectDateShow;
	
	@Transient
	private String quitDateShow;
	
	@Transient
	public String getEffectDateShow() {
		return effectDateShow;
	}

	@Transient
	public void setEffectDateShow(String effectDateShow) {
		this.effectDateShow = effectDateShow;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLegalEntityId() {
		return legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}
	
public TmsMdLegalEquipment(Map<String, Object> map){
		
		if(map==null){
			
			return;
		}
		
		//TmsMdUsageLocalLegal tmsMdUsageLocalLegal = new TmsMdUsageLocalLegal();
		
		this.setId((String) map.get("id"));
		
		this.setFlag("1");
		
		this.setParentId((String) map.get("parentId"));
		
		this.setLegalEntityId((String) map.get("legalEntityId"));
		
		
		//this.setIsEnablePrint(Util.getBooleanByObject(map.get("isEnablePrint")));
		//this.setIsUsageLocalRegNo(getBooleanByObject(map.get("isUsageLocalRegNo")));
		
		//this.setEnabledFlag(Util.getBooleanByObject(map.get("enabledFlag")));
		
		//this.setDes((String) map.get("des"));
		
		
		effectDateShow = (String) map.get("effectDateShow");
		
		Date effectDate=Util.getDateByStr(effectDateShow);
		if(AssertHelper.empty(effectDate)){
			
		}else{
			
			this.setEffectDate(effectDate);
		}
		
		
		quitDateShow = (String) map.get("quitDateShow");
		Date quitDate=Util.getDateByStr(quitDateShow);
		
		if(AssertHelper.empty(quitDate)){
			
		}else{
			
			this.setQuitDate(quitDate);
		}
		
		
	}

public TmsMdLegalEquipment(String id2) {
	// TODO Auto-generated constructor stub
	this.setId(id2);
}

public TmsMdLegalEquipment(){
	
}

}