package com.deloitte.tms.base.masterdata.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import com.deloitte.tms.pl.core.model.impl.BaseEntity;
import com.deloitte.tms.pl.core.model.impl.BaseRelation;

@Entity
@Table(name=TmsMdLegalEnablePrint.TABLE)
@ModelProperty(comment = "纳税主体与打印点关系表")
public class TmsMdLegalEnablePrint extends BaseRelation implements Serializable {

	public static final String TABLE = TablePreDef.TMS_MD_TAG+"LEGAL_ENABLE_PRINT";
	public static final String SEQ = TABLE;
	
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name="LEGAL_ENABLE_PRINT_ID",length=36)
	private String id;

	@Column(name="DESCRIPTION")
	private String des;

	@ModelProperty(comment="是否打印点")
	@Column(name="IS_ENABLE_PRINT")
	private Boolean isEnablePrint;
	
	@ModelProperty(comment="纳税实体")
	@Column(name="LEGAL_ENTITY_ID",length=36)
	private String legalEntityId;
	
	@Column(name="ENABLED_FLAG")
	private Boolean enabledFlag;

	@ModelProperty(comment="是否打印点")
	@Column(name="PARENT_ID",length=36)
	private String parentId;
	
	
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
	
	public TmsMdLegalEnablePrint(){
		
	}
	public TmsMdLegalEnablePrint(String id) {		
		this.setId(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Boolean getIsEnablePrint() {
		return isEnablePrint;
	}

	public void setIsEnablePrint(Boolean isEnablePrint) {
		this.isEnablePrint = isEnablePrint;
	}

	public String getLegalEntityId() {
		return legalEntityId;
	}

	public void setLegalEntityId(String legalEntityId) {
		this.legalEntityId = legalEntityId;
	}

	public String getParentId() {
		return parentId;
	}
	
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public Boolean getEnabledFlag() {
		return enabledFlag;
	
	}
	public void setEnabledFlag(Boolean enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
	
public TmsMdLegalEnablePrint(Map<String, Object> map){
		
		if(map==null){
			
			return;
		}
		
		//TmsMdUsageLocalLegal tmsMdUsageLocalLegal = new TmsMdUsageLocalLegal();
		
		this.setId((String) map.get("id"));
		
		this.setFlag("1");
		
		this.setParentId((String) map.get("parentId"));
		
		this.setLegalEntityId((String) map.get("legalEntityId"));
		
		
		this.setIsEnablePrint(getBooleanByObject(map.get("isEnablePrint")));
		//this.setIsUsageLocalRegNo(getBooleanByObject(map.get("isUsageLocalRegNo")));
		
		this.setEnabledFlag(getBooleanByObject(map.get("enabledFlag")));
		
		this.setDes((String) map.get("des"));
		
		
		effectDateShow = (String) map.get("effectDateShow");
		
		Date effectDate=getDateByStr(effectDateShow);
		if(AssertHelper.empty(effectDate)){
			
		}else{
			
			this.setEffectDate(effectDate);
		}
		
		
		quitDateShow = (String) map.get("quitDateShow");
		Date quitDate=getDateByStr(quitDateShow);
		
		if(AssertHelper.empty(quitDate)){
			
		}else{
			
			this.setQuitDate(quitDate);
		}
		
		
	}


public static Date getDateByStr(String s){
	
	if(AssertHelper.empty(s)){
		
		return null;
	}
	
	Date d = new Date();
	try{
		
	//from jquery class="easyui-datebox" will return as format MM/dd/yyyy
	SimpleDateFormat dateF = new SimpleDateFormat("MM/dd/yyyy");
	d = dateF.parse(s);
	
	return d;
	}catch(Exception e){
		e.printStackTrace();
		
		return null;
	}
	
	
}

public static Boolean getBooleanByObject(Object s){
	
	if(s instanceof Boolean){
		
		return (Boolean)s;
		
	}else if(s instanceof String){
		
		if(AssertHelper.empty(s)){
			return false;
		}
		
		String temps = (String)s;
		
		if(temps==null || "".endsWith(temps.trim())){
			return false;
		}
		
		if("0".equals(temps.trim())){
			return false;
		}else if("1".equals(temps.trim())){
			return true;
		}
		
		if( "false".equalsIgnoreCase(temps.trim() )   ){
			return false;
		}else{
			return true;
		}
		
	}else if(s instanceof Integer){
		
		Integer tempi = (Integer)s;
		
		if(0==tempi){
			return false;
		}else{
			return true;
		}
	}
	
	return false;
}
	
}