package com.deloitte.tms.pl.core.model;

import java.util.Date;

public interface IBaseRelation {
	
	public Date getEffectDate();
	
	public void setEffectDate(Date effectDate);

	public Date getQuitDate() ;

	public void setQuitDate(Date quitDate);
	
//	public Date getEstDate();
//
//	public void setEstDate(Date estDate);

	/**
	 * 在指定日期中判断关系是否有效
	 * 
	 * @param date
	 * @return
	 * @author dada
	 */
	public boolean isEffect(Date date);
	
	/**
	 * 判断关系现在是否有效
	 * 
	 * @see #isEffect(Date)
	 * @return
	 * @author dada
	 */
	public boolean isEffect();
}
