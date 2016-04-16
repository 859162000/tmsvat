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
@Table(name=TablePreDef.WORKFLOW+"HIS_BLOB")
public class HistoryBlob {
	@Id
	@Column(name="ID_")
	private long id;
	
	@Lob
	@Column(name="BLOB_VALUE_",length=1024000)
	private byte[] blobValue;

	public HistoryBlob(){}
	public HistoryBlob(Object obj){
		byte[] b=SerializationUtils.serialize(obj);
		setBlobValue(b);
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte[] getBlobValue() {
		return blobValue;
	}

	public void setBlobValue(byte[] blobValue) {
		this.blobValue = blobValue;
	}
}
