package com.deloitte.tms.pl.version.party.model.person.relation;

import java.util.Date;

import com.deloitte.tms.pl.version.party.model.person.ChaRole;


public class Relation {

	private ChaRole srcRole;
	
	private ChaRole tgtRole;
	
	private String relCode;

	private int months;

	private String comment;
	
	Date beginDate;
	Date endDate;
	
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 关系状态
	 */
	private Object statu;
	
	
	/** 伪劣，于构建关系目标端时使用 **/
	private String tgtRoleId;
	

	public Object getStatu() {
		return statu;
	}

	public void setStatu(Object statu) {
		this.statu = statu;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * 上级
	 * @return
	 */
	public ChaRole getSrcRole() {
		return srcRole;
	}

	/**
	 * 上级
	 * @param srcRole
	 */
	public void setSrcRole(ChaRole srcRole) {
		this.srcRole = srcRole;
	}
	/**
	 * 下级
	 * @return
	 */
	public ChaRole getTgtRole() {
		return tgtRole;
	}
	/**
	 * 下级
	 * @param tgtRole
	 */
	public void setTgtRole(ChaRole tgtRole) {
		this.tgtRole = tgtRole;
	}

	public String getRelCode() {
		return relCode;
	}

	public void setRelCode(String relCode) {
		this.relCode = relCode;
	}

	public int getMonths() {
		return months;
	}

	public void setMonths(int months) {
		this.months = months;
	}

	public String getTgtRoleId() {
		return tgtRoleId;
	}

	public void setTgtRoleId(String tgtRoleId) {
		this.tgtRoleId = tgtRoleId;
	}
	
}
