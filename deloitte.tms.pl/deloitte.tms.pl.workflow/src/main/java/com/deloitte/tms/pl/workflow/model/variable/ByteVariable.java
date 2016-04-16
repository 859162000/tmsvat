package com.deloitte.tms.pl.workflow.model.variable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Byte")
public class ByteVariable extends Variable {
	@Column(name="BYTE_VALUE_")
	private Byte byteValue;

	public ByteVariable(){}
	
	public ByteVariable(byte value){
		this.byteValue=value;
	}
	@Override
	public Object getValue() {
		return byteValue;
	}

	public int getByteValue() {
		return byteValue;
	}

	public void setByteValue(Byte byteValue) {
		this.byteValue = byteValue;
	}
	@Override
	public VariableType getType() {
		return VariableType.Byte;
	}
}
