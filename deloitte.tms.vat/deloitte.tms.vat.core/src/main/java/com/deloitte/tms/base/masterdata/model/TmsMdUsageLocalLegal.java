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
import com.deloitte.tms.pl.core.model.impl.BaseRelation;

@Entity
@Table(name=TmsMdUsageLocalLegal.TABLE)
@ModelProperty(comment = "是否采用自身纳税识别号 最终打印发票纳税人")
public class TmsMdUsageLocalLegal extends BaseRelation implements Serializable{
	
	
	/**  
	 * serialVersionUID  
	 */  
	
	private static final long serialVersionUID = 1L;
	public static final String TABLE = TablePreDef.TMS_MD_TAG+"USAGE_LOCAL_LEGAL";//BASE_LEGAL_ENTITY
	public static final String SEQ = TABLE;
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name="USAGE_LOCAL_LEGAL_ID",length=36)
	private String id;
	
	@ModelProperty(comment="纳税主体ID	")
	@Column(name="LEGAL_ENTITY_ID",length=36)
	private String legalEntityId;
	
	@ModelProperty(comment="描述")
	@Column(name="DESCRIPTION",length=36)
	private String des;
	
	@ModelProperty(comment="是否使用自身纳税人证件号打印")
	@Column(name="IS_USAGE_LOCAL_REG_NO",length=36)
	private Boolean isUsageLocalRegNo;
	
	
	@ModelProperty(comment="是否启用")
	@Column(name="ENABLED_FLAG")
	private Boolean enabledFlag;
	
	@ModelProperty(comment="父结点ID")
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

	@Transient
	public String getQuitDateShow() {
		return quitDateShow;
	}

	@Transient
	public void setQuitDateShow(String quitDateShow) {
		this.quitDateShow = quitDateShow;
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

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Boolean getIsUsageLocalRegNo() {
		return isUsageLocalRegNo;
	}

	public void setIsUsageLocalRegNo(Boolean isUsageLocalRegNo) {
		this.isUsageLocalRegNo = isUsageLocalRegNo;
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

	@Override
	public String toString() {
		return "TmsMdUsageLocalLegal [id=" + id + ", legalEntityId="
				+ legalEntityId + ", des=" + des + ", isUsageLocalRegNo="
				+ isUsageLocalRegNo + ", parentId=" + parentId
				+ ", enabledFlag=" + enabledFlag + "]";
	}
	
	public TmsMdUsageLocalLegal(Map<String, Object> map){
		
		if(map==null){
			
			return;
		}
		
		//TmsMdUsageLocalLegal tmsMdUsageLocalLegal = new TmsMdUsageLocalLegal();
		
		this.setId((String) map.get("id"));
		
		this.setFlag("1");
		
		this.setParentId((String) map.get("parentId"));
		//System.out.println("---TmsMdUsageLocalLegal id:"+id+";parentId:"+this.parentId);
		if(AssertHelper.empty(this.parentId)){
			System.out.println("pareentId:"+this.parentId+"; means want to creat root of tree");
		}
		
		this.setLegalEntityId((String) map.get("legalEntityId"));
		
		
		
		this.setIsUsageLocalRegNo(getBooleanByObject(map.get("isUsageLocalRegNo")));
		
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

	public TmsMdUsageLocalLegal() {
		
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
