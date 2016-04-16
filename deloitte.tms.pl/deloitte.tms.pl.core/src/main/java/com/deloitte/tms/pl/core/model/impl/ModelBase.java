package com.deloitte.tms.pl.core.model.impl;

import java.util.Date;

import com.deloitte.tms.pl.core.model.IModelBase;

public abstract class ModelBase implements IModelBase, java.io.Serializable {
	// Constructors

	protected Long id;

	protected Date firstInsert;

	protected Date lastModified;

	protected String insertMan;

	protected String updateMan;

	protected String dataOwner;
	protected String dataStatu;

	public String getDataStatu() {
		return dataStatu;
	}

	public void setDataStatu(String dataStatu) {
		this.dataStatu = dataStatu;
	}

	public Date getFirstInsert() {
		return firstInsert;
	}

	public void setFirstInsert(Date firstInsert) {
		this.firstInsert = firstInsert;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getInsertMan() {
		return insertMan;
	}

	public void setInsertMan(String insertMan) {
		this.insertMan = insertMan;
	}

	public String getUpdateMan() {
		return updateMan;
	}

	public void setUpdateMan(String updateMan) {
		this.updateMan = updateMan;
	}

	public String getDataOwner() {
		return dataOwner;
	}

	public void setDataOwner(String dataOwner) {
		this.dataOwner = dataOwner;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = (Long) id;
	}
}
