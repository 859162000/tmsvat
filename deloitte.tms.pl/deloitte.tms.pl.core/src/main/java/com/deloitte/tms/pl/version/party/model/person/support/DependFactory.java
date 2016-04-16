package com.deloitte.tms.pl.version.party.model.person.support;


public class DependFactory {

	public static Depend create(String type, Object value) {
		int month = 0;
		return create(type, value, month);
	}
	
	public static Depend create(String type, Object value, int month) {
		return create(type, value, Depend.CUSTOM, month);
	}
	
	public static Depend create(String type, Object value, String status) {
		int month = 0;
		return create(type, value, status, month);
	}
	
	public static Depend create(String type, Object value, String status, int month) {
		Depend depend = new Depend();
		depend.setStatus(status);
		depend.setCode(type);
		depend.setValue(value);
		depend.setMonth(month);
		return depend;
	}
	
	public static CompositeDepend createComposite(String code, Object value) {
		CompositeDepend depend = new CompositeDepend();
		depend.setValue(value);
		depend.setCode(code);
		return depend;
	}
	
}
