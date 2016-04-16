package com.deloitte.tms.pl.core.commons.utils;

import java.util.ArrayList;
import java.util.List;

public class TestModel {
	
	String email;
	String name;
	String sex;
	List<TestModel> childs=new ArrayList<TestModel>();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<TestModel> getChilds() {
		return childs;
	}

	public void setChilds(List<TestModel> childs) {
		this.childs = childs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
