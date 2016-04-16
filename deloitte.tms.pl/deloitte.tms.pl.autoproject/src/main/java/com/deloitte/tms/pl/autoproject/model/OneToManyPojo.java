package com.deloitte.tms.pl.autoproject.model;

import java.util.ArrayList;
import java.util.List;

import com.deloitte.tms.pl.autoproject.utils.AutoProjectUtils;

public class OneToManyPojo {
	POJOClass one;
	List<POJOClass> many=new ArrayList<POJOClass>();
	List<POJOClass> ones=new ArrayList<POJOClass>();
	public POJOClass getOne() {
		return one;
	}
	public void setOne(POJOClass one) {
		this.one = one;
	}
	public void setOne(Class class_) {		
		this.one = AutoProjectUtils.revertClass2Pojo(class_);
	}
	public List<POJOClass> getMany() {
		return many;
	}
	public void setMany(List<POJOClass> many) {
		this.many = many;
	}
	public void addMany(POJOClass pojoClass)
	{
		if(many==null)
		{
			many=new ArrayList<POJOClass>();
		}
		many.add(pojoClass);
	}
	public void addMany(Class class1)
	{
		addMany(AutoProjectUtils.revertClass2Pojo(class1));
	}
	public void addOne(POJOClass pojoClass)
	{
		if(ones==null)
		{
			ones=new ArrayList<POJOClass>();
		}
		ones.add(pojoClass);
	}
	public void addOne(Class class1)
	{
		addOne(AutoProjectUtils.revertClass2Pojo(class1));
	}
	public List<POJOClass> getOnes() {
		return ones;
	}
	public void setOnes(List<POJOClass> ones) {
		this.ones = ones;
	}
	
}
