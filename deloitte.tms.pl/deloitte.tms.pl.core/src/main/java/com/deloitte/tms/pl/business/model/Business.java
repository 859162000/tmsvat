package com.deloitte.tms.pl.business.model;

import java.util.ArrayList;
import java.util.List;

import com.deloitte.tms.pl.business.service.rule.model.IBusiness;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

public class Business extends BaseEntity implements IBusiness{
	
	protected Business parent;
	/**逻辑名称**/
	protected String label;
	/**内部命名,便于迁移**/
	protected String code;
	/**实现方式bean/class等*/
	protected String implementation;
	/**图标地址*/
	protected String icon;
	
	protected String description;
	
	protected boolean enabled;
	/**类别分组还是具体实现*/
	protected String type;
	
	/** 冗余字段 */
	protected String iconUrl;
	
	protected boolean hasChild;
	
	protected boolean select;
	
	protected String path;
	
	protected Integer sortOrder;
	
	private List<Business> subBusinesss = new ArrayList<Business>();
	
	private BusinessParamsset businessParamsSet;
	protected Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = (Long) id;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public BusinessParamsset getBusinessParamsSet() {
		return businessParamsSet;
	}

	public void setBusinessParamsSet(BusinessParamsset businessParamsSet) {
		this.businessParamsSet = businessParamsSet;
	}

	public String getImplementation() {
		return implementation;
	}

	public void setImplementation(String implementation) {
		this.implementation = implementation;
	}

	public Business getParent() {
		return parent;
	}

	public void setParent(Business parent) {
		this.parent = parent;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public List<Business> getSubBusinesss() {
		return subBusinesss;
	}
	public void setSubBusinesss(List<Business> subBusinesss) {
		this.subBusinesss = subBusinesss;
	}
	
	public void addSubBusiness(Business business) {
		this.subBusinesss.add(business);
	}
	
	public void removeSubBusiness(Business business) {
		this.subBusinesss.remove(business);
	}
	
	public void removeSubBusinessById(Long businessId) {
		Business remove = null;
		for (Business subBusiness : this.subBusinesss) {
			if (businessId.equals(subBusiness.getId())) {
				remove = subBusiness;
				break;
			}
		}
		removeSubBusiness(remove);
	}
	
	public void addSubBusiness(Business business, int index) {
		this.subBusinesss.add(index, business);
	}
	
	public void initHasChild() {
		if (!this.subBusinesss.isEmpty()) {
			this.hasChild = true;
		}
		for (Business subBusiness : this.subBusinesss) {
			subBusiness.initHasChild();
		}
	}
	public String toString() {
		return label;
	}

	@Override
	public List<IBusiness> getChilds() {
		List<IBusiness> temps = new ArrayList<IBusiness>();
		for(IBusiness business:subBusinesss)
		{
			temps.add(business);
		}
		return temps;
	}
}
