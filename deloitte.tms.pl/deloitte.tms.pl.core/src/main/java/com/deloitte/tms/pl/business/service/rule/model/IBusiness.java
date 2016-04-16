package com.deloitte.tms.pl.business.service.rule.model;

import java.util.List;

import com.deloitte.tms.pl.business.service.rule.param.IParameterSet;

public interface IBusiness {
	public final static String ROOT = "SYSTEM";
	public final static String BUSINESS_PROJECT = "BUSINESS_PROJECT";
	public final static String BUSINESS_MODULE = "BUSINESS_MODULE";
	public final static String BUSINESS_BUSINESS = "BUSINESS_BUSINESS";
	public final static String BUSINESS_LOGIC="BUSINESS_LOGIC";
	public final static String BUSINESS_HANDLE="BUSINESS_HANDLE";
	public final static String BUSINESS_HANDLE_BEAN="BUSINESS_HANDLE_BEAN";
	public final static String BUSINESS_HANDLE_CLASS="BUSINESS_HANDLE_CLASS";
	public String getImplementation();
	public String getType();
	public List<IBusiness> getChilds();
	IParameterSet getBusinessParamsSet();
}
