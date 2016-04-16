package com.deloitte.tms.pl.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.commons.utils.ConverterUtils;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2UUIDGenerator;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

@Entity
@Table(name = ByteContent.TABLE)
@ModelProperty(comment = "大对象文件表_信息模板")
public class ByteContent extends BaseEntity{
	
	public static final String TABLE=TablePreDef.BASEPRE+"ByteContent";
	public static final String SEQ=TABLE;
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2UUIDGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "id",  length = 36)
	String id;
	
	@ModelProperty(comment="分类")
	@Column(name = "groupId",length=120)	
	String groupId;
	
	@ModelProperty(comment="关联Id")
	@Column(name = "relationId",length=36)	
	String relationId;
	
	@Lob
	@Column(name="content",length=1024000)
	byte[] content;
	
	@Transient
	String content_str;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getContent_str() {
		return ConverterUtils.getString(content);
	}

	public void setContent_str(String content_str) {
		this.content_str = content_str;
		if(content_str!=null){
			this.content=ConverterUtils.getByte(content_str);
		}		
	}
	
	
}
