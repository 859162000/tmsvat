package com.deloitte.tms.vat.inf.taxinfo.service.impl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.XmlUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSerializeProperty;
import com.deloitte.tms.vat.inf.taxinfo.service.TaxObjectSerialize;

public abstract class BaseTaxObjectSerializeImpl implements TaxObjectSerialize {
	/**
	 * 输出复杂对象
	 * @param object
	 * @param result
	 */
	protected void outComplexObject(Object object,StringBuilder result){
		Collection<Field> filds=ReflectUtils.getAllFileds(object.getClass(),true);
		for(Field field:filds){			
			outputField(object,field,result);			
		}
	}
	/**
	 * 输出字段
	 * @param object
	 * @param field
	 * @param result
	 */
	private void outputField(Object object,Field field,StringBuilder result){
		try {
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			Object fieldValue=field.get(object);
			if(ReflectUtils.isSimpleValue(fieldValue)){
				String name=getFiledAnnotationName(field);
				if(name!=null){
					begFiled(name, result);
					if(AssertHelper.notEmpty(fieldValue)){
						if(getIsValueFormart(field)){
							result.append("<value>");
						}
						result.append(fieldValue);
						if(getIsValueFormart(field)){
							result.append("</value>");
						}
					}
					endFiled(name,result);
				}
			}else {//集合都输出,有注解就输出集合的开始结束,没有注解就是自身循环
				String filedName=null;
				filedName = getFiledAnnotationName(field);
				String itemname = getFiledItemAnnotationName(field);
				if(filedName!=null||itemname!=null){
					if(filedName!=null){
						begFiled(filedName, result);
					}					
					if (fieldValue instanceof Collection<?>) {						
						outCollect((Collection)fieldValue,result,itemname);
					}else{			
						outComplexObject(fieldValue, result);
					}
					if(filedName!=null){
						endFiled(filedName, result);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 输出集合信息
	 * @param collection
	 * @param result
	 * @param name 集合单项外嵌别名
	 */
	protected void outCollect(Collection collection,StringBuilder result,String name) {
		for(Object entity:(Collection<?>)collection){
			if(AssertHelper.notEmpty(name)){
				begFiled(name, result);
			}
			outComplexObject(entity,result);
			if(AssertHelper.notEmpty(name)){
				endFiled(name, result);
			}
		}
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
			if(annotation instanceof AisinoSerializeProperty)
			{
				AisinoSerializeProperty aisinoProperty=(AisinoSerializeProperty)annotation;
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
		String name=null;
		if(field!=null){
			Annotation fieldAnnotations[]=field.getAnnotations();
			for(Annotation annotation:fieldAnnotations)
			{
				if(annotation instanceof AisinoSerializeProperty)
				{
					AisinoSerializeProperty aisinoProperty=(AisinoSerializeProperty)annotation;
					if(AssertHelper.notEmpty(aisinoProperty.itemSerializeName())){
						name=aisinoProperty.itemSerializeName();
					}
					break;
				}
			}
			//没有注解表示不输出
//			if(name==null){
//				name=field.getName();
//			}
		}
		return name;
	}
	protected String getReturnFiledAnnotationName(Field field) {
		return getFiledAnnotationName(field);
	}
	protected String getReturnFiledItemAnnotationName(Field field) {
		return getFiledItemAnnotationName(field);
	}
	protected boolean getIsValueFormart(Field field){
		return false;
	}
	private void begFiled(String name,StringBuilder result){
		result.append("<"+name+">");
	}
	private void endFiled(String name,StringBuilder result){
		result.append("</"+name+">");
	}
	/**
	 * 将rootElement解析为复杂对象
	 * @param rootElement
	 * @param type
	 * @return
	 */
	public Object parseComplexObject(Element rootElement,Class type){
		Object result;
		try {
			result = type.newInstance();
		} catch (Exception e1) {
			throw new BusinessException("转换的类必须有一个默认不带参数的构造函数");
		}
		Collection<Field> filds=ReflectUtils.getAllFileds(type,true);
		for(Field field:filds){			
			try {							
				String name=getReturnFiledAnnotationName(field);
				String itemName=getReturnFiledItemAnnotationName(field);
				if(AssertHelper.notEmpty(name)||AssertHelper.notEmpty(itemName)){
					if (!field.isAccessible()) {
						field.setAccessible(true);
					}					
					if(ReflectUtils.isSimpleType(field.getType())){
						Object value=parseChildElementFromRoot(rootElement,field);
						field.set(result, value);
					} else if (ReflectUtils.isCollectionType(field.getType())) {
						Collection collection = parseList(rootElement, field);
						field.set(result, collection);
					}else {
						Object value=parseComplexObject(rootElement,field.getType());
						field.set(result, value);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		return result;
	}
	/**
	 * 将rootElement解析为List对象
	 * @param rootElement
	 * @param field
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Collection parseList(Element rootElement, Field field) {
		Collection collection = new ArrayList<Object>();
		String rootName=getReturnFiledItemAnnotationName(field);
		Class targetType=ReflectUtils.getParameterizedType(field);
		AssertHelper.notEmpty_assert(targetType, "集合对象必须制定<>内内容");
		NodeList nodeList=XmlUtils.getChildElementsByName(rootElement, rootName);//sendbackinvrecorditem
		for(int i = 0; i< nodeList.getLength() ; i ++){
			Element childNode = (Element)nodeList.item(i);
			Object entity=parseComplexObject(childNode,targetType);
			collection.add(entity);
		}
		return collection;
	}
	/**
	 * 从rootNode解析field内容
	 * @param rootNode
	 * @param field
	 * @return
	 */
	private Object parseChildElementFromRoot(Element rootNode,Field field){
		String name=getReturnFiledAnnotationName(field);
		Element childNode=XmlUtils.getChildElementByName(rootNode, name);
		if(childNode==null){
			return null;
		}
		return parseSimpleElement(childNode, field);
	}
	/**
	 * 获取一个简单对象
	 * @param node
	 * @param field
	 * @return
	 */
	private Object parseSimpleElement(Element node,Field field){
		if(node==null){
			return null;
		}
		if(field==null){
			throw new BusinessException("field 不能为空");
		}
		Object result=ReflectUtils.parse(node.getTextContent(), field.getType());
		return result;
	}
//	@Override
	public String outCollect(Collection collection, String name) {
		StringBuilder result=new StringBuilder();
		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		result.append("<service>");
//		result.append("<sid/>");
		result.append("<ip/>");
		result.append("<port/>");
		result.append("<data>");
		outCollect(collection,result,name);
		result.append("</data>");
		result.append("</service>");
		return result.toString();
	}

}
