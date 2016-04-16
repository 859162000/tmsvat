package com.deloitte.tms.pl.version.party.model.person.support;

import java.util.Collection;
import java.util.HashSet;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.enums.ErrorCode;

public class CompositeDepend {
	
	private String code;
	
	private Object value;

	private Collection<Depend> depends = new HashSet<Depend>();

	public Collection<Depend> getDepends() {
		return depends;
	}

	public void setDepends(Collection<Depend> depends) {
		this.depends = depends;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public void addDepend(String code, Object value) throws BusinessException {
		Depend depend = DependFactory.create(code, value);
		if (!depends.contains(depend)) {
			this.depends.add(depend);
			return;
		}
		if(!findDepend(code).getValue().equals(value)){
			throw new BusinessException(ErrorCode.DATA, this + " 存在多条同类型依赖 依赖类型：" + depend.getCode());
		}
	}
	
	public void addDepends(Collection<Depend> depends) {
		this.depends.addAll(depends);
	}

	/**
	 * 通过依赖类型获取本月的数据依赖
	 * 
	 * @param type
	 * @return
	 * @author dada
	 */
	public Depend findDepend(String type) {
		int month = 0;
		return findDepend(type, month);
	}
	
	/**
	 * 通过依赖类型获取指定月、指定类型的数据依赖
	 * 
	 * @param type
	 * @param month
	 * @return
	 * @author dada
	 */
	public Depend findDepend(String type, int month) {
		for (Depend depend : depends) {
			if (depend.getCode().equals(type) && depend.getMonth() == month) {
				return depend;
			}
		}
		return null;
	}
		
}
