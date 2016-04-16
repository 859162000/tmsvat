package com.deloitte.tms.pl.workflow.model.variable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.hibernate.Session;
import org.springframework.util.Assert;
import org.springframework.util.SerializationUtils;

import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.Blob;
import com.deloitte.tms.pl.workflow.utils.IDGenerator;

/**
 * @author Jacky.gao
 * @since 2013年8月2日
 */
@Entity
@DiscriminatorValue("Blob")
public class BlobVariable extends Variable {
	@Column(name="BLOB_ID_")
	private long blobId;
	
	@Transient
	private Object obj;
	
	@Transient
	private Blob blob;
	
	public BlobVariable(){}
	public BlobVariable(Object value,Context context){
		Blob lob=new Blob(value);
		long id=IDGenerator.getInstance().nextId();
		lob.setId(id);
		setBlobId(id);
		Session session=context.getSession();
		session.save(lob);
	}
	@Override
	public Object getValue() {
		return obj;
	}
	
	public Blob getBlob(){
		return blob;
	}
	
	public void initValue(Context context){
		Assert.notNull(context);
		Session session=context.getSession();
		blob=(Blob)session.get(Blob.class,blobId);
		obj=SerializationUtils.deserialize(blob.getBlobValue());
	}
	
	public long getBlobId() {
		return blobId;
	}

	public void setBlobId(long blobId) {
		this.blobId = blobId;
	}
	@Override
	public VariableType getType() {
		return VariableType.Blob;
	}
}
