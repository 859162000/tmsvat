package com.deloitte.tms.base.masterdata.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

@Entity
@Table(name = ClientSec.TABLE)
@ModelProperty(comment = "客户关系授权")
public class ClientSec extends BaseEntity {
	public static final String TABLE = TablePreDef.TMS_BA_TAG+"CLIENT_SEC";
	public static final String SEQ = TABLE;
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2IdGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "CLIENT_SEC_ID",  length = 36)
	String id;
	
	@Column(name = "CLIENT_ID",length=36,nullable=false)
	@ModelProperty(comment = "客户关系id")
	String clientId;
	
	@Column(name = "ORG_ID",length=36,nullable=false)
	@ModelProperty(comment = "所属组织")
	String orgId;
	
	@Column(name = "ENTITY_ID",length=36,nullable=false)
	@ModelProperty(comment = "纳税主体")
	String entityId;
	
	@Column(name = "S_DATE")
	@ModelProperty(comment = "开始时间")
	Date sDate;
	
	@Column(name = "E_DATE")
	@ModelProperty(comment = "结束时间")
	Date eDate;
	
	@ManyToOne
	Client client;

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public Date getsDate() {
		return sDate;
	}

	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}

	public Date geteDate() {
		return eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	
	
}
