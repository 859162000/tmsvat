package com.deloitte.tms.base.taxsetting.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;
@Entity
@Table(name = Items.TABLE)
@ModelProperty(comment = "税目集合")
public class Items extends BaseEntity{
	public static final String TABLE = TablePreDef.TMS_BA_TAX_TAG+"ITEMS";
	public static final String SEQ = TABLE;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id") 
	TaxCategory tcg;
	
	
	public TaxCategory getTcg() {
		return tcg;
	}

	public void setTcg(TaxCategory tcg) {
		this.tcg = tcg;
	}*/

	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "TAX_ITEM_ID",  length = 36)
	protected String id;
	
	@Column(name = "TAX_CATEGORY_ID",length=36)
	@ModelProperty(comment = "税种ID")
	String categoryId;
	
	@Column(name = "TAX_ITEM_CODE",length=255)
	@ModelProperty(comment = "税目代码")
	String itemCode;
	
	@Column(name = "TAX_ITEM_NAME",length=255)
	@ModelProperty(comment = "税目名称")
	String itemName;

	@Column(name = "ORG_ID",length=36)
	@ModelProperty(comment = "所属组织")
	String orgId;
	
	@Column(name = "TAX_SETTING_RULES",length=36)
	@ModelProperty(comment = "计税规则")
	String itemTaxRules;
	
	@Column(name = "ENABLED_FLAG",length=1)
	@ModelProperty(comment = "Y 启用 N 不启用")
	String isUsed;
	
	@Column(name = "COMMENTS",length=2000)
	@ModelProperty(comment = "备注")
	String comments;
	
	@Column(name = "ADAPT_INDUSTRY_CODE",length=36)
	@ModelProperty(comment = "适用行业")
	String adaptIndustryId;
	
	@Column(name = "START_DATE")
	@ModelProperty(comment = "开始日期")
	Date sDate;
	
	@Column(name = "END_DATE")
	@ModelProperty(comment = "结束日期")
	Date eDate;
	
	@Column(name = "DESCRIPTION",length=36)
	@ModelProperty(comment = "计税依据")
	String itemTaxBasis;
	
	@Column(name = "TAX_SETTING_METHOD",length=36)
	@ModelProperty(comment = "计税方法")
	String itemTaxMethod;
	
	public String getItemTaxBasis() {
		return itemTaxBasis;
	}

	public void setItemTaxBasis(String itemTaxBasis) {
		this.itemTaxBasis = itemTaxBasis;
	}

	public String getItemTaxMethod() {
		return itemTaxMethod;
	}

	public void setItemTaxMethod(String itemTaxMethod) {
		this.itemTaxMethod = itemTaxMethod;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}


	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getItemTaxRules() {
		return itemTaxRules;
	}

	public void setItemTaxRules(String itemTaxRules) {
		this.itemTaxRules = itemTaxRules;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAdaptIndustryId() {
		return adaptIndustryId;
	}

	public void setAdaptIndustryId(String adaptIndustryId) {
		this.adaptIndustryId = adaptIndustryId;
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
	
}
