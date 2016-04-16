package com.deloitte.tms.pl.autoproject.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.deloitte.tms.pl.core.commons.annotation.AttachMentDropdown;
import com.deloitte.tms.pl.core.commons.annotation.Dropdown;
import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.annotation.enums.EditType;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.model.impl.BaseEntity;

import de.odysseus.el.tree.Tree;



public class DefaultPojo implements POJOClass{
	
	public static final String[] inogeFields={"serialVersionUID","TABLE","SEQ"};
	
	Class defaultClass;
	String comment;
	protected ImportContext importContext;
	List<PojoField> pojoFields;
	List<PojoField> filterdPojoFields;
	Boolean isIntro=false;
	
	EditType editType=EditType.form;
	
	Dropdown dropdown;
	
	private DefaultPojo()
	{
		
	}
	public DefaultPojo(Class class1)
	{
		this.defaultClass=class1;
		importContext = new ImportContextImpl(getPackageName());
		paraClass();
		paraClassFields();
	}
	private void paraClassFields()
	{
		if(pojoFields==null)
		{
			pojoFields=new ArrayList<PojoField>();
		}
		paraClassWithSuper(defaultClass, pojoFields);
	}
	private Boolean isprocessField(Field field){
		String fieldname=field.getName();
		if(fieldname.equals(fieldname.toUpperCase())){
			return false;
		}
		for(String ig:inogeFields){
			if(ig.toUpperCase().equals(fieldname.toUpperCase())){
				return false;
			}
		}
		return true;
	}
	private void paraClassWithSuper(Class defClass,List<PojoField> pojoFields)
	{
		Class paramClass=getParamClass(defClass);
		Collection<Field> fields=ReflectUtils.getAllFileds(paramClass);
		for(Field field:fields)
		{
			if(isprocessField(field)){
				Boolean isNotBase=true;
				if(defClass.getName().equals(BaseEntity.class.getName()))
				{
					isNotBase=false;
				}
				DefaultPojoField pojoField=new DefaultPojoField();
				Boolean issample=ReflectUtils.isSimpleField(field);
				if(issample)
				{
					pojoField.setIsSampleField(true);
					pojoField.setDataType(ReflectUtils.getShortClassName(field));
					if("long".equals(pojoField.getDataType())
							||"int".equals(pojoField.getDataType())
							||"Long".equals(pojoField.getDataType())
							||"Integer".equals(pojoField.getDataType())
							||"double".equals(pojoField.getDataType())
							||"Double".equals(pojoField.getDataType())){
						pojoField.setHtmlType("number");
					}else if("Date".equals(pojoField.getDataType())
							||"DateTime".equals(pojoField.getDataType())){
						pojoField.setHtmlType("date");
					}else if("boolean".equals(pojoField.getDataType())
							||"Boolean".equals(pojoField.getDataType())){
						pojoField.setHtmlType("boolean");
					}
				}
				pojoField.setName(field.getName());
				Annotation annotations[]=field.getAnnotations();
				for(Annotation annotation:annotations)
				{
					if(annotation instanceof ModelProperty)
					{
						ModelProperty temp=(ModelProperty)annotation;
						pojoField.setComment(temp.comment());
						pojoField.setDes(temp.des());
						pojoField.setIsrequired(temp.isrequired()==true?true:false);
						pojoField.setDictCode(AssertHelper.notEmpty(temp.dictCode())?temp.dictCode():null);
					}
					if(annotation instanceof OneToOne)
					{
//						OneToOne temp=(OneToOne)annotation;
						pojoField.setIsOneToOne(true);
					}
//					if(annotation instanceof Id)
//					{
//						isNotBase=false;
//					}
					if(annotation instanceof Dropdown)
					{
						Dropdown _dropdown=(Dropdown)annotation;
						pojoField.setDropdown(_dropdown);
					}
					if(annotation instanceof AttachMentDropdown)
					{
						AttachMentDropdown _dropdown=(AttachMentDropdown)annotation;
						pojoField.setAttachMentDropdown(_dropdown);
					}
				}
				pojoField.setIsNotBaseEntiry(isNotBase);
				pojoFields.add(pojoField);
			}
		}
		filterdPojoFields=new ArrayList<PojoField>();
		Collection<Field> BaseEntityfields=ReflectUtils.getAllFileds(BaseEntity.class);
		boolean base=false;
		for(PojoField pojoField:pojoFields){
			for(Field field:BaseEntityfields){
				if(field.getName().equals(pojoField.getName())){
					base=true;
					break;
				}
			}
			if(!base){
				filterdPojoFields.add(pojoField);
			}
			base=false;
		}
	}
	private void paraClass()
	{
		Annotation[] annotations=defaultClass.getAnnotations();
		for(Annotation annotation:annotations)
		{
			if(annotation instanceof ModelProperty)
			{
				ModelProperty temp=(ModelProperty)annotation;
				comment=temp.comment();
				editType=temp.editType();
				isIntro=temp.isIntro();
			}
		}
	}
	private static Class getParamClass(Class class1) {
		/**
		 * 添加Param级别字段支持
		 */
		Class defClass=class1;
		String className=class1.getName();
		String classNameParam=className+"Param";
		try {
			defClass=Class.forName(classNameParam);
		} catch (ClassNotFoundException e) {
			try {
				String classNameInParam=classNameParam+"InParam";
				defClass=Class.forName(classNameInParam);
			} catch (ClassNotFoundException ee) {
				
			}			
		}
		return defClass;
	}
	@Override
	public String importType(String fqcn) {
		return importContext.importType(fqcn);
	}
	
	@Override
	public String getQualifiedDeclarationName() {
		return defaultClass.getPackage().getName();
	}

	@Override
	public String getDeclarationName() {
		return defaultClass.getSimpleName();
	}
	@Override
	public String getClassName() {
		String simplename =defaultClass.getSimpleName();
		return simplename;
	}
	@Override
	public String getDeclarationNameFirstLetterLower() {
		String str=getDeclarationName();
		if (str == null || str.length() < 2) {
			return str;
		}
		String firstLetter = str.substring(0, 1).toLowerCase();
		return firstLetter + str.substring(1, str.length());
	}
	
	@Override
	public String getPackageName() {
		return getQualifiedDeclarationName().replace(".model", "");
	}
	
	public String getRealPackageAndClassPath() {
		return getQualifiedDeclarationName()+"."+getShortName();
	}

	@Override
	public String getShortName() {
		return defaultClass.getSimpleName();
	}
	
	@Override
	public String getModelPackageName() {
		return getQualifiedDeclarationName();
	}
	@Override
	public String staticImport(String fqcn, String member) {
		return null;
	}
	@Override
	public String generateImports() {
		return null;
	}
	public Class getDefaultClass() {
		return defaultClass;
	}
	public void setDefaultClass(Class defaultClass) {
		this.defaultClass = defaultClass;
	}
	public ImportContext getImportContext() {
		return importContext;
	}
	public void setImportContext(ImportContext importContext) {
		this.importContext = importContext;
	}
	public List<PojoField> getPojoFields() {
		return pojoFields;
	}
	public void setPojoFields(List<PojoField> pojoFields) {
		this.pojoFields = pojoFields;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public EditType getEditType() {
		return editType;
	}
	public void setEditType(EditType editType) {
		this.editType = editType;
	}
	public Dropdown getDropdown() {
		return dropdown;
	}
	public void setDropdown(Dropdown dropdown) {
		this.dropdown = dropdown;
	}
	public Boolean getIsIntro() {
		return isIntro;
	}
	public void setIsIntro(Boolean isIntro) {
		this.isIntro = isIntro;
	}
	public List<PojoField> getFilterdPojoFields() {
		return filterdPojoFields;
	}
	public void setFilterdPojoFields(List<PojoField> filterdPojoFields) {
		this.filterdPojoFields = filterdPojoFields;
	}

}
