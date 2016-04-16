package com.deloitte.tms.base.taxsetting.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

@Entity
@Table(name = TaxCategory.TABLE)
@ModelProperty(comment = "税种定义")
public class TaxCategory extends BaseEntity implements Serializable{
	//public static final String TABLE = TablePreDef.TMS_BA_TAX_TAG+"CATEGORY";
	public static final String TABLE = "TMS_BA_TAX_CATEGORY";
	public static final String SEQ = TABLE;
	
	@Id
	@Column(name = "category_id",  length = 36)
	protected String id;
	
	
//	//@OneToMany(mappedBy = "CATEGORY")
//	@Cascade(CascadeType.REFRESH)
//	List<Items> items; 
	
//	public List<Items> getItems() {
//		return items;
//	}
//
//	public void setItems(List<Items> items) {
//		this.items = items;
//	}

	@Column(name = "category_code",length=255)
	@ModelProperty(comment = "税种编号")
	String categoryCode;
	
	@Column(name = "CATEGORY_NAME",length=255)
	@ModelProperty(comment = "税种名称")
	String categoryName;
	
	@Column(name = "ORG_ID",length=255)
	@ModelProperty(comment = "所属组织")
	String orgId;
	
	@Column(name = "CATEGORY_STATE",length=255)
	@ModelProperty(comment = "国地税")
	String categoryState;
	
	@Column(name = "CATEGORY_TYPE",length=255)
	@ModelProperty(comment = "税种类型")
	String categoryType;
	
	@Column(name = "IS_USED",length=255)
	@ModelProperty(comment = "是否启用")
	String isUsed;
	

	@Column(name = "COMMENTS",length=255)
	@ModelProperty(comment = "税种编号")
	String comments;
	
	@Column(name = "S_DATE",length=255)
	@ModelProperty(comment = "税种编号")
	String sDate;
	
	@Column(name = "E_DATE",length=255)
	@ModelProperty(comment = "税种编号")
	String eDate;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getCategoryState() {
		return categoryState;
	}

	public void setCategoryState(String categoryState) {
		this.categoryState = categoryState;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
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

	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	public String geteDate() {
		return eDate;
	}

	public void seteDate(String eDate) {
		this.eDate = eDate;
	}
	
}
