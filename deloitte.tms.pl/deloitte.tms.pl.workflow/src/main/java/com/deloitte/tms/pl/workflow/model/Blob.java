package com.deloitte.tms.pl.workflow.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.util.SerializationUtils;

import com.deloitte.tms.pl.core.commons.constant.TablePreDef;

/**
 * @author Jacky.gao
 * @since 2013年9月27日
 */
@Entity
@Table(name=TablePreDef.WORKFLOW+"BLOB")
public class Blob {
	@Id
	@Column(name="ID_")
	private long id;
	
	@Column(name="NAME_",length=60)
	private String name;
	
	@Column(name="PROCESS_ID_")
	private long processId;
	
	@Lob
	@Column(name="BLOB_VALUE_",length=1024000)
	private byte[] blobValue;

	public Blob(){}
	public Blob(Object obj){
		byte[] b=SerializationUtils.serialize(obj);
		setBlobValue(b);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getProcessId() {
		return processId;
	}

	public void setProcessId(long processId) {
		this.processId = processId;
	}

	public byte[] getBlobValue() {
		return blobValue;
	}

	public void setBlobValue(byte[] blobValue) {
		this.blobValue = blobValue;
	}
}
