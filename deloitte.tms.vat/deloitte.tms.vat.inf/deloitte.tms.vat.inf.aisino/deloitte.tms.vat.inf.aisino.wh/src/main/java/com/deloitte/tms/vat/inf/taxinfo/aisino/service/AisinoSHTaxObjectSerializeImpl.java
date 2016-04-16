package com.deloitte.tms.vat.inf.taxinfo.aisino.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.XmlUtils;
import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSHReturnSerializeProperty;
import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSHSerializeProperty;
import com.deloitte.tms.vat.inf.taxinfo.service.impl.BaseTaxObjectSerializeImpl;

public class AisinoSHTaxObjectSerializeImpl extends BaseTaxObjectSerializeImpl{
	@Override
	public void check(Object object) {
		
	}
	@Override
	public Object responseToObject(String xml, Class type) {
		Document document = XmlUtils.getDocument(xml); 
		Element rootElement=XmlUtils.getRootElement(document, "siiscmd");
		Object result=parseComplexObject(rootElement,type);
		return result;
	}
	@Override
	public String outObject(Object object) {
		check(object);
		StringBuilder result=new StringBuilder();
		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		result.append("<siiscmd xmlns=\"http://www.aisino.sh.cn\">");
		outComplexObject(object,result);
		result.append("</siiscmd>");
		return result.toString();
	}
	/**
	 * 获取序列化字段名称
	 * @param field
	 * @return
	 */
	protected String getFiledAnnotationName(Field field) {
		Annotation fieldAnnotations[]=field.getAnnotations();
		for(Annotation annotation:fieldAnnotations)
		{
			if(annotation instanceof AisinoSHSerializeProperty)
			{
				AisinoSHSerializeProperty aisinoProperty=(AisinoSHSerializeProperty)annotation;
				if(AssertHelper.notEmpty(aisinoProperty.serializeName())){
					String name=aisinoProperty.serializeName();
					return name;
				}
				break;
			}
		}
		return null;
	}
	/**
	 * 获取序列化字段名称
	 * @param field
	 * @return
	 */
	protected String getReturnFiledAnnotationName(Field field) {
		Annotation fieldAnnotations[]=field.getAnnotations();
		for(Annotation annotation:fieldAnnotations)
		{
			if(annotation instanceof AisinoSHReturnSerializeProperty)
			{
				AisinoSHReturnSerializeProperty aisinoProperty=(AisinoSHReturnSerializeProperty)annotation;
				if(AssertHelper.notEmpty(aisinoProperty.serializeName())){
					String name=aisinoProperty.serializeName();
					return name;
				}
				break;
			}
			if(annotation instanceof AisinoSHSerializeProperty)
			{
				AisinoSHSerializeProperty aisinoProperty=(AisinoSHSerializeProperty)annotation;
				if(AssertHelper.notEmpty(aisinoProperty.serializeName())){
					String name=aisinoProperty.serializeName();
					return name;
				}
				break;
			}
		}
		return null;
	}
	/**
	 * 获取集合对象,子对象的序列化名称
	 * @param field
	 * @return
	 */
	protected String getFiledItemAnnotationName(Field field) {
		if(field!=null){
			Annotation fieldAnnotations[]=field.getAnnotations();
			for(Annotation annotation:fieldAnnotations)
			{
				if(annotation instanceof AisinoSHSerializeProperty)
				{
					AisinoSHSerializeProperty aisinoProperty=(AisinoSHSerializeProperty)annotation;
					if(AssertHelper.notEmpty(aisinoProperty.itemSerializeName())){
						return aisinoProperty.itemSerializeName();
					}
					break;
				}
			}
			//没有注解表示不输出
//			if(name==null){
//				name=field.getName();
//			}
		}
		return null;
	}
	/**
	 * 获取集合对象,子对象的序列化名称
	 * @param field
	 * @return
	 */
	protected String getReturnFiledItemAnnotationName(Field field) {
		if(field!=null){
			Annotation fieldAnnotations[]=field.getAnnotations();
			for(Annotation annotation:fieldAnnotations)
			{
				if(annotation instanceof AisinoSHReturnSerializeProperty)
				{
					AisinoSHReturnSerializeProperty aisinoProperty=(AisinoSHReturnSerializeProperty)annotation;
					if(AssertHelper.notEmpty(aisinoProperty.itemSerializeName())){
						return aisinoProperty.itemSerializeName();
					}
					break;
				}
			}
			for(Annotation annotation:fieldAnnotations)
			{
				if(annotation instanceof AisinoSHSerializeProperty)
				{
					AisinoSHSerializeProperty aisinoProperty=(AisinoSHSerializeProperty)annotation;
					if(AssertHelper.notEmpty(aisinoProperty.itemSerializeName())){
						return aisinoProperty.itemSerializeName();
					}
					break;
				}
			}
			//没有注解表示不输出
//			if(name==null){
//				name=field.getName();
//			}
		}
		return null;
	}
	protected boolean getIsValueFormart(Field field){
		if(field!=null){
			Annotation fieldAnnotations[]=field.getAnnotations();
			for(Annotation annotation:fieldAnnotations)
			{
				if(annotation instanceof AisinoSHSerializeProperty)
				{
					AisinoSHSerializeProperty aisinoProperty=(AisinoSHSerializeProperty)annotation;
					return aisinoProperty.valueFormat();
				}
			}
		}
		return false;
	}
}
