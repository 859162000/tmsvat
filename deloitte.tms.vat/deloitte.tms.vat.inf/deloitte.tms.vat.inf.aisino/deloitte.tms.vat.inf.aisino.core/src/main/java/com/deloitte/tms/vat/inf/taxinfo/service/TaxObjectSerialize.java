package com.deloitte.tms.vat.inf.taxinfo.service;




public interface TaxObjectSerialize {
	public String outObject (Object object);
//	public String outCollect (Collection collection,String name);
	public void check(Object object);
	public Object responseToObject(String xml,Class type);
}
