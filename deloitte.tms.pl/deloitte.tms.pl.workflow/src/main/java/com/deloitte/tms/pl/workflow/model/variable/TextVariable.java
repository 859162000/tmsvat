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
 * @since 2013年9月24日
 */
@Entity
@DiscriminatorValue("Text")
public class TextVariable extends Variable {
	@Column(name="BLOB_ID_")
	private long blobId;
	
	@Transient
	private String text;
	
	@Transient
	private Blob blob;

	public TextVariable(){}
	
	public TextVariable(String value,Context context){
		Blob lob=new Blob(value);
		long id=IDGenerator.getInstance().nextId();
		lob.setId(id);
		setBlobId(id);
		Session session=context.getSession();
		session.save(lob);
	}
	
	public void initValue(Context context){
		Assert.notNull(context);
		Session session=context.getSession();
		blob=(Blob)session.get(Blob.class,blobId);
		text=(String)SerializationUtils.deserialize(blob.getBlobValue());
	}
	
	public Blob getBlob(){
		return blob;
	}
	
	@Override
	public String getValue() {
		return text;
	}

	@Override
	public VariableType getType() {
		return VariableType.Text;
	}
	
	public long getBlobId() {
		return blobId;
	}

	public void setBlobId(long blobId) {
		this.blobId = blobId;
	}
}
