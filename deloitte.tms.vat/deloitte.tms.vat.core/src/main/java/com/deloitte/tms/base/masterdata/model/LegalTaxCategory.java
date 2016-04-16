
package com.deloitte.tms.base.masterdata.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.base.taxsetting.model.Items;
import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;


@Entity
@Table(name = LegalTaxCategory.TABLE)
@ModelProperty(comment = "纳税主体与汇缴(税种)关系")
public class LegalTaxCategory extends BaseEntity {
	/**
	 * 
	 */
	public static final String TABLE = TablePreDef.TMS_MD_TAG+"LEGAL_TAX_CATEGORY";
	public static final String SEQ = TABLE;
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2IdGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "LEGAL_TAX_CATEGORY_ID",  length = 36)
	String id;
	
	@Column(name = "LEGAL_ENTITY_ID",length=36,nullable=false)
	@ModelProperty(comment = "纳税主体ID")
	String legalEntityId;
	
	@Column(name = "TAX_CATEGORY_ID",length=36,nullable=false)
	@ModelProperty(comment = "税种ID")
	String taxCategoryId;
	
	@Column(name = "VERSION_NO",length=36,nullable=false)
	@ModelProperty(comment = "版本号")
	String versionNo ;
	
	@Column(name = "PARENT_ID",length=36,nullable=false)
	@ModelProperty(comment = "当前父结点")
	String parentId;

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

	public String getTaxCategoryId() {
		return taxCategoryId;
	}

	public void setTaxCategoryId(String taxCategoryId) {
		this.taxCategoryId = taxCategoryId;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	
}
