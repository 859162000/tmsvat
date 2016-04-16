package com.deloitte.tms.pl.core.model.impl;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.model.IBaseRelation;

/**
 * 关系类型基础类
 * 
 * @author bo.wang
 * @create 2016年3月15日 下午2:02:37 
 * @version 2.0.0
 */
@MappedSuperclass
public abstract class BaseRelation extends BaseEntity implements IBaseRelation{

	@Column(name="START_DATE")
	@ModelProperty(comment="有效日期")
	Date effectDate;
	
	@Column(name="END_DATE")
	@ModelProperty(comment="失效日期")
	Date quitDate;

//	@Column(name="EST_DATE")
//	@ModelProperty(comment="创建日期")
//	Date estDate;

	public Date getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}

	public Date getQuitDate() {
		return quitDate;
	}

	public void setQuitDate(Date quitDate) {
		this.quitDate = quitDate;
	}
	
//	public Date getEstDate() {
//		return estDate;
//	}
//
//	public void setEstDate(Date estDate) {
//		this.estDate = estDate;
//	}

	/**
	 * 在指定日期中判断关系是否有效
	 * 
	 * @param date
	 * @return
	 * @author dada
	 */
	public boolean isEffect(Date date) {
		java.util.Date dt2 = new java.sql.Timestamp(date.getTime());
		boolean retval = false;
		if (effectDate != null
				&& effectDate.compareTo(dt2) <= 0
				&& (quitDate == null || quitDate.compareTo(dt2) > 0)) {
			retval = true;
		}
		return retval;
	}
	
	/**
	 * 判断关系现在是否有效
	 * 
	 * @see #isEffect(Date)
	 * @return
	 * @author dada
	 */
	public boolean isEffect() {
		Date date = new Date();
		return isEffect(date);
	}
}
