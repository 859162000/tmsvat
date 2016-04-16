package com.deloitte.tms.pl.workflow.model.variable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Character")
public class CharacterVariable extends Variable {
	@Column(name="CHARACTER_VALUE_")
	private Character characterValue;

	public CharacterVariable(){}
	
	public CharacterVariable(Character value){
		this.characterValue=value;
	}
	@Override
	public Object getValue() {
		return characterValue;
	}

	public Character getCharacterValue() {
		return characterValue;
	}

	public void setCharacterValue(Character characterValue) {
		this.characterValue = characterValue;
	}
	@Override
	public VariableType getType() {
		return VariableType.Character;
	}
}
