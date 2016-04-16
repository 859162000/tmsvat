package com.deloitte.tms.pl.workflow.model.variable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("Float")
public class FloatVariable extends Variable {
	@Column(name="FLOAT_VALUE_")
	private float floatValue;

	public FloatVariable(){}
	
	public FloatVariable(float value){
		this.floatValue=value;
	}
	
	@Override
	public Object getValue() {
		return floatValue;
	}

	public float getFloatValue() {
		return floatValue;
	}

	public void setFloatValue(float floatValue) {
		this.floatValue = floatValue;
	}
	@Override
	public VariableType getType() {
		return VariableType.Float;
	}
}
