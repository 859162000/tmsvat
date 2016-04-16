package com.deloitte.tms.pl.autoproject.model;

import java.util.List;

import com.deloitte.tms.pl.core.commons.annotation.enums.EditType;


/**
 * Wrapper class over PersistentClass used in hbm2java and hbm2doc tool
 * @author max
 * @author <a href="mailto:abhayani@jboss.org">Amit Bhayani</a>
 *
 */
public interface POJOClass extends ImportContext {

	String getQualifiedDeclarationName();

	String getDeclarationName();

	String getDeclarationNameFirstLetterLower();

	String getPackageName();

	String getShortName();

	String getModelPackageName();

	public List<PojoField> getPojoFields();
	
	public List<PojoField> getFilterdPojoFields();
	
	public String getComment();
	
	public EditType getEditType();
	
	public void setEditType(EditType editType);
	
	public Boolean getIsIntro();
	
	public String getClassName();
	
	public String getRealPackageAndClassPath();
}
