package com.deloitte.tms.pl.autoproject.model;




public class VOPojo extends DefaultPojo{

	public static final String VO="VO";
	
	public VOPojo(Class class1) {
		super(class1);
	}
	
	@Override
	public String getDeclarationName() {
		String simplename =defaultClass.getSimpleName();
		if(simplename.endsWith(VO)){
			simplename=simplename.replace("VO", "");
		}
		return simplename;
	}
	@Override
	public String getPackageName() {
		return getQualifiedDeclarationName();
	}
	

}
