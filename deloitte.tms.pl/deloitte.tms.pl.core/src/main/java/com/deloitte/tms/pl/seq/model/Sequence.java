package com.deloitte.tms.pl.seq.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.deloitte.tms.pl.core.commons.constant.TablePreDef;
@Entity
@Table(name = Sequence.TABLE)
public class Sequence {
	
	public static final String TABLE = TablePreDef.BASEPRE+"Sequence";
	public static final String SEQ = TABLE;
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")       
    @GenericGenerator(name = "paymentableGenerator", strategy = "assigned")  
	@Column(name = "TYPE",length=150)
	private String type;
	
	@Column(name = "value",length=16)
	private Long value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
	
	
	
}
