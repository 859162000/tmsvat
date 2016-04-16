package com.deloitte.tms.pl.message.template.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
import com.deloitte.tms.pl.core.hibernate.identifier.Ling2IdGenerator;


@Entity
@Table(name = MessageParameter.TABLE)
@ModelProperty(comment = "信息模板")
public class MessageParameter {
	public static final String TABLE=TablePreDef.MESSAGE+"MessageParameter";
	public static final String SEQ=TABLE;
	
	@Id
	@GenericGenerator(name=SEQ+"_GENERATOR",strategy=Ling2IdGenerator.STRATEGY_NAME,parameters={@Parameter(name="pkColumnValue",value=SEQ)})
	@GeneratedValue(strategy = GenerationType.TABLE, generator = SEQ+"_GENERATOR")
	@Column(name = "id",  length = 16)
	Long id;
	
	@ModelProperty(comment="模板id")
	@Column(name = "messageTemplateId")
	Long messageTemplateId;
	
	@ModelProperty(comment="名称")
	@Column(name = "name",length=50)
	String name;
	
	@ModelProperty(comment="代码",des="即在模板中可用的参数,或model的属性")
	@Column(name = "code",length=50)
	String code;
	
	@ModelProperty(comment="描述")
	@Column(name = "des",length=250)
	String des;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMessageTemplateId() {
		return messageTemplateId;
	}

	public void setMessageTemplateId(Long messageTemplateId) {
		this.messageTemplateId = messageTemplateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
	
}
