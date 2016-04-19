package com.deloitte.tms.base.masterdata.model;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Where;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;
import com.deloitte.tms.pl.security.utils.LittleUtils;

@Entity
@Table(name=TmsMdEquipment.TABLE)
@ModelProperty(comment = "设备/打印机表")
public class TmsMdEquipment extends BaseEntity implements Serializable {
	
	public static final String TABLE = TablePreDef.TMS_MD_TAG+"EQUIPMENT";
	public static final String SEQ = TABLE;

	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name="EQUIPMENT_ID")
	private String id;

	@Column(name="EQUIPMENT_CODE")
	private String equipmentCode;

	@Temporal(TemporalType.DATE)
	@Column(name="EQUIPMENT_ENTER_DATE")
	private Date equipmentEnterDate;

	@Column(name="EQUIPMENT_IP")
	private String equipmentIp;

	@Column(name="EQUIPMENT_MANAGER")
	private String equipmentManager;

	@Column(name="EQUIPMENT_NAME")
	private String equipmentName;

	@Column(name="EQUIPMENT_TYPE")
	private String equipmentType;
	
	@Column(name="ENABLED_FLAG")
	private String enabledFlag;	
	
	@Column(name="EQUIPMENT_PORT")
	private String equipmentPort;
	
	@Column(name="EQUIPMENT_SEQ_NO")
	private String equipmentSeqNo;	

	@Column(name="PARENT_EQUIPMENT_ID")
	private String parentEquipmentId;
	/*@ManyToOne
	@JoinColumn(name="PARENT_EQUIPMENT_ID",insertable=false,updatable=false)
	@Cascade(CascadeType.REFRESH)
	TmsMdEquipment parent;*/
	
/*	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@Cascade(CascadeType.REFRESH)
	@Where(clause="DELETED_FLAG=1")
	private List<TmsMdEquipment> tmsMdEquipments;*/
/*	
	public List<TmsMdEquipment> getTmsMdEquipments() {
		return tmsMdEquipments;
	}

	public void setTmsMdEquipments(List<TmsMdEquipment> tmsMdEquipments) {
		this.tmsMdEquipments = tmsMdEquipments;
	}*/

	public String getEquipmentSeqNo() {
		return equipmentSeqNo;
	}

	public void setEquipmentSeqNo(String equipmentSeqNo) {
		this.equipmentSeqNo = equipmentSeqNo;
	}

	public String getEquipmentPort() {
		return equipmentPort;
	}

	public void setEquipmentPort(String equipmentPort) {
		this.equipmentPort = equipmentPort;
	}
	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}

	/*public TmsMdEquipment getParent() {
		return parent;
	}

	public void setParent(TmsMdEquipment parent) {
		this.parent = parent;
	}*/
	
	public TmsMdEquipment() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEquipmentCode() {
		return this.equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public Date getEquipmentEnterDate() {
		return this.equipmentEnterDate;
	}

	public void setEquipmentEnterDate(Date equipmentEnterDate) {
		this.equipmentEnterDate = equipmentEnterDate;
	}

	public String getEquipmentIp() {
		return this.equipmentIp;
	}

	public void setEquipmentIp(String equipmentIp) {
		this.equipmentIp = equipmentIp;
	}

	public String getEquipmentManager() {
		return this.equipmentManager;
	}

	public void setEquipmentManager(String equipmentManager) {
		this.equipmentManager = equipmentManager;
	}

	public String getEquipmentName() {
		return this.equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getEquipmentType() {
		return this.equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}
/*
	public String getInvoiceType() {
		return this.invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
*/
/*	public String getLegalEntityId() {
		return this.legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}*/

	public String getParentEquipmentId() {
		return this.parentEquipmentId;
	}

	public void setParentEquipmentId(String parentEquipmentId) {
		this.parentEquipmentId = parentEquipmentId;
	}
	
	public TmsMdEquipment(Map<String, Object> map){
		
		try{
			System.out.println("TmsMdEquipment contructor...");
		
		Set<String> keys = map.keySet();
		for(String key : keys){
			Object value = map.get(key);
			
			if(value instanceof String){
				
				String tempV=(String)value;
				if(LittleUtils.strEmpty(tempV)){
					tempV="";
				}
				StringBuffer sb = new StringBuffer();
				key.substring(0,1);
				sb.append("set").append(key.substring(0,1).toUpperCase()).append(key.substring(1));
				
				try{
				Method m = this.getClass().getMethod(sb.toString(), String.class);
				
				if(m==null){
					
				}else{
					m.invoke(this, tempV.trim());
				}
				
				
				}catch(Exception x){
					x.printStackTrace();
				}
				
			}
		}
		
		System.out.println("to do ");
		}catch(Exception e){
			e.printStackTrace();		
		}
	}

/*	public TmsMdLegalEquipment getTmsMdLegalEquipment() {
		return this.tmsMdLegalEquipment;
	}

	public void setTmsMdLegalEquipment(TmsMdLegalEquipment tmsMdLegalEquipment) {
		this.tmsMdLegalEquipment = tmsMdLegalEquipment;
	}
*/
}