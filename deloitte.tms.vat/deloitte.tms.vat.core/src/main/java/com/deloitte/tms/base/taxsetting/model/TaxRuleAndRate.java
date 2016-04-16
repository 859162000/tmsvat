package com.deloitte.tms.base.taxsetting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

@Entity
@Table(name = TaxRuleAndRate.TABLE)
@ModelProperty(comment = "税种定义")
public class TaxRuleAndRate extends BaseEntity{
	public static final String TABLE = TablePreDef.TMS_BA_TAX_TAG+"RULES_H";
	public static final String SEQ = TABLE;
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2IdGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "RULES_H_ID",  length = 36)
	protected String id;
	
	@Column(name = "ITEM_ID",length=255)
	@ModelProperty(comment = "税目编号")
	String itemId;
	
	@Column(name = "TAX_ENTITY",length=255)
	@ModelProperty(comment = "纳税主体")
	String taxEntity;
	
	@Column(name = "TAX_COMPENY",length=255)
	@ModelProperty(comment = "纳税机关")
	String taxCompany;
	
	@Column(name = "TAX_PLACE",length=255)
	@ModelProperty(comment = "纳税主体")
	String taxPlace;
	
	@Column(name = "ORG_ID",length=255)
	@ModelProperty(comment = "所属组织")
	String orgId;
	
	@Column(name = "CATEGORY_ID",length=255)
	@ModelProperty(comment = "税种")
	String categoryId;
	
	@Column(name = "RULES_L_ID",length=255)
	@ModelProperty(comment = "税则行信息")
	String rules;
	
	@Column(name = "TAX_ID",length=255)
	@ModelProperty(comment = "税率")
	String taxId;

	@Column(name = "TAX_VAL",length=255)
	@ModelProperty(comment = "税额")
	String taxVal;
	
	@Column(name = "IS_FREE_VAL",length=255)
	@ModelProperty(comment = "起征点")
	String isFreeVal;
	
	@Column(name = "FREE_VAL",length=255)
	@ModelProperty(comment = "免税额")
	String freeVal;
	
	@Column(name = "IN_UP_VAL",length=255)
	@ModelProperty(comment = "上限")
	String inUpal;
	
	@Column(name = "IN_LOW_VAL",length=255)
	@ModelProperty(comment = "下限")
	String inLowVal;
	
	@Column(name = "ITEM_TAX_RULES",length=255)
	@ModelProperty(comment = "计税规则")
	String itemTaxRules;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getTaxEntity() {
		return taxEntity;
	}

	public void setTaxEntity(String taxEntity) {
		this.taxEntity = taxEntity;
	}

	public String getTaxCompany() {
		return taxCompany;
	}

	public void setTaxCompany(String taxCompany) {
		this.taxCompany = taxCompany;
	}

	public String getTaxPlace() {
		return taxPlace;
	}

	public void setTaxPlace(String taxPlace) {
		this.taxPlace = taxPlace;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getTaxVal() {
		return taxVal;
	}

	public void setTaxVal(String taxVal) {
		this.taxVal = taxVal;
	}

	public String getIsFreeVal() {
		return isFreeVal;
	}

	public void setIsFreeVal(String isFreeVal) {
		this.isFreeVal = isFreeVal;
	}

	public String getFreeVal() {
		return freeVal;
	}

	public void setFreeVal(String freeVal) {
		this.freeVal = freeVal;
	}

	public String getInUpal() {
		return inUpal;
	}

	public void setInUpal(String inUpal) {
		this.inUpal = inUpal;
	}

	public String getInLowVal() {
		return inLowVal;
	}

	public void setInLowVal(String inLowVal) {
		this.inLowVal = inLowVal;
	}

	public String getItemTaxRules() {
		return itemTaxRules;
	}

	public void setItemTaxRules(String itemTaxRules) {
		this.itemTaxRules = itemTaxRules;
	}

	
}
