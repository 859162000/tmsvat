package com.deloitte.tms.pl.dictionary.model.impl;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Where;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TableColnumDef;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;
import com.deloitte.tms.pl.security.model.impl.DefaultUrl;
import com.deloitte.tms.pl.system.model.SysCategory;

/**
 * 字典表
 */
@Entity
@Table(name=SysCategory.TABLE)
public class BaseCstegory extends BaseEntity {

	public static final String TABLE=TablePreDef.BASEPRE+"category";
	public static final String SEQ=TABLE;
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy=GenerationType.TABLE,generator=SEQ+"_GENERATOR")
	@Column(name="CATEGORY_ID",length=36)
	String id;
	
	@ModelProperty(comment = "名称")
	@Column(name = "Category_name", length = 150)
	String label;
	
	@ModelProperty(comment = "代码")
	@Column(name = "CATEGORY_CODE", length = 150)
	String code;
	
	@ModelProperty(comment = "描述")
	@Column(name = "description", length = 150)
	String description;
	
	@ModelProperty(comment = "是否启用")
	@Column(name = "enabled_flag")
	boolean enabled;
	
	@ModelProperty(comment = "排序")
	@Column(name="SORT_ORDER")
	Integer sortOrder;
	
	@ModelProperty(comment = "级别")
	@Column(name = "Category_level")
	Integer level;	
	
	
	@ModelProperty(comment = "上级代码")
	@Column(name = "parent_Id")
	String parentId;
	@ManyToOne
	@JoinColumn(name="parent_Id",insertable=false,updatable=false)
	@Cascade(CascadeType.REFRESH)
	BaseCstegory parent;

	@OneToMany(mappedBy = "parent")
	@Cascade(CascadeType.REFRESH)
	@OrderBy("SORT_ORDER")
	@Where(clause=TableColnumDef.FLAG_DEF+"=1")
	Collection<BaseCstegory> childs;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public BaseCstegory getParent() {
		return parent;
	}

	public void setParent(BaseCstegory parent) {
		this.parent = parent;
	}

	public Collection<BaseCstegory> getChilds() {
		return childs;
	}

	public void setChilds(Collection<BaseCstegory> childs) {
		this.childs = childs;
	}

	
	
}
