package com.deloitte.tms.pl.jsonresponse.output;
///*
// * This file is part of Dorado 7.x (http://dorado7.bsdn.org).
// * 
// * Copyright (c) 2002-2012 BSTEK Corp. All rights reserved.
// * 
// * This file is dual-licensed under the AGPLv3 (http://www.gnu.org/licenses/agpl-3.0.html) 
// * and BSDN commercial (http://www.bsdn.org/licenses) licenses.
// * 
// * If you are unsure which license is appropriate for your use, please contact the sales department
// * at http://www.bstek.com/contact.
// */
//
//package com.ling2.jsonresponse.output;
//
//import java.io.Writer;
//import java.sql.Time;
//import java.sql.Timestamp;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Date;
//import java.util.Map;
//import java.util.Stack;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import com.ling2.core.commons.support.DaoPage;
//import com.ling2.core.commons.utils.DateUtils;
//import com.ling2.core.commons.utils.reflect.ReflectUtils;
//import com.ling2.jsonresponse.constant.Constants;
//
///**
// * 用于向客户端输出JSON数据的输出器。
// * 
// * @author Benny Bao (mailto:benny.bao@bstek.com)
// * @since Oct 6, 2008
// */
////@Component(BarkDataOutputter.BEA_ID)
//public class BarkDataOutputter implements Outputter, PropertyOutputter {
//	public static final String BEA_ID="dataOutputter";
//	private static final Log logger = LogFactory.getLog(BarkDataOutputter.class);
//	private static final Long ONE_DAY = 1000L * 60 * 60 * 24;
//
//	private boolean evaluateExpression = true;
//	private boolean ignoreEmptyProperty=true;
//	/**
//	 * 对象引用深度,1或者所有
//	 */
//	private Integer looplevel=1;
//	/**
//	 * 决定是否输出非简单属性
//	 */
//	private boolean simplePropertyValueOnly=false;
//	/**
//	 * 决定是否输出非简单属性的时候输出集合属性
//	 */
//	private boolean ignorecollection=true;
//
//	public boolean isEvaluateExpression() {
//		return evaluateExpression;
//	}
//
//	public void setEvaluateExpression(boolean evaluateExpression) {
//		this.evaluateExpression = evaluateExpression;
//	}
//
//	public boolean isIgnoreEmptyProperty() {
//		return ignoreEmptyProperty;
//	}
//
//	public void setIgnoreEmptyProperty(boolean ignoreEmptyProperty) {
//		this.ignoreEmptyProperty = ignoreEmptyProperty;
//	}
//
//	public boolean isSimplePropertyValueOnly() {
//		return simplePropertyValueOnly;
//	}
//
//	public void setSimplePropertyValueOnly(boolean simplePropertyValueOnly) {
//		this.simplePropertyValueOnly = simplePropertyValueOnly;
//	}
//
//	public boolean isIgnorecollection() {
//		return ignorecollection;
//	}
//
//	public void setIgnorecollection(boolean ignorecollection) {
//		this.ignorecollection = ignorecollection;
//	}
//
//	public boolean isEscapeValue(Object value) {
//		return OutputUtils.isEscapeValue(value);
//	}
//
//	/**
//	 * 将一个EL表达式输出为JavaScript代码。
//	 */
//	protected void outputExpression(String expression, OutputContext context)
//			throws Exception {
//		Writer writer = context.getWriter();
//		if (expression.equals("this")) {
//			writer.write(expression);
//		} else {
//			writer.write(expression.replaceAll("this.",
//					"dorado.DataPath.create(\""));
//			writer.write("\").evaluate(this, true)");
//		}
//	}
//
//	public void output(Object object, OutputContext context) throws Exception {
//		if (object != null) {
//			outputData(object, context);
//		} else {
//			JsonBuilder json = context.getJsonBuilder();
//			json.value(null);
//		}
//	}
//
//	private void outputPage(DaoPage page, OutputContext context)
//			throws Exception {
//		JsonBuilder json = context.getJsonBuilder();
//		json.object();
//		json.key("$isWrapper").value(true);
//
//		Collection<?> entities = page.getResult();
//		if (entities != null) {
//			json.key("data");
//			json.array();
//			for (Object e : entities) {
//				outputData(e, context);
//			}
//			json.endArray();
//		}
//
//		json.key("pageSize").value(page.getPageSize());
//		json.key("pageNo").value(page.getPageIndex());
//		json.key("pageCount").value(page.getPageCount());
//		json.key("entityCount").value(page.getPageCount());
//		json.endObject();
//	}
//
//	/**
//	 * @param object
//	 * @param writer
//	 * @param context
//	 * @throws Exception
//	 */
//	protected void outputData(Object object, OutputContext context)
//			throws Exception {
//		JsonBuilder json = context.getJsonBuilder();
//		if (ReflectUtils.isSimpleValue(object)) {
//			if (object instanceof Date) {
//				Date d = (Date) object;
//				if (d instanceof Time || d instanceof Timestamp
//						|| d.getTime() % ONE_DAY != 0) {
//					json.value(DateUtils
//							.format(Constants.ISO_DATETIME_FORMAT1,
//									d));
//				} else {
//					json.value(DateUtils.format(
//							Constants.ISO_DATE_FORMAT, d));
//				}
//			} else {
//				json.value(object);
//			}
//		} else {
//			internalOutputData(object, context);
//		}
//	}
//	@SuppressWarnings("rawtypes")
//	private void internalOutputData(Object object, OutputContext context)
//			throws Exception {
//		JsonBuilder json = context.getJsonBuilder();
//		Stack<Object> dataObjectStack = context.getDataObjectStack();
//		if (dataObjectStack.contains(object)) {
//			Exception e = new IllegalArgumentException("对象循环引用:"+object.toString());
//			logger.error(e, e);
//			json.value(null);
//			return;
//		}
//		dataObjectStack.push(object);
//		try {
//			if (object instanceof Collection<?>) {
//				json.array();
//				for (Object e : (Collection<?>) object) {
//					outputData(e, context);
//				}
//				json.endArray();				
//			} else if (object instanceof DaoPage) {
//				outputPage((DaoPage) object, context);
//			} else {
//				outputEntity(object, context);
//			}
//		} finally {
//			dataObjectStack.pop();
//		}
//	}
//	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
//	protected void outputEntity(Object object, OutputContext context)
//			throws Exception {
//
//		JsonBuilder json = context.getJsonBuilder();
//
//		json.object();
//		Class<?> type = object.getClass();
//		Map beanMap;
//		if (object instanceof Map) {
//			beanMap = (Map) object;
//		} else if (object.getClass() == Object.class) {
//			beanMap = Collections.EMPTY_MAP;
////		}else if(object instanceof ObjectNode){
//			
//		}
//		else {
////			beanMap = BeanMap.create(object);
//			String[] excludes=new String[]{"parent"};
//			String[] includes=new String[]{"children"};
//			beanMap = ReflectUtils.getProperties(object,simplePropertyValueOnly,ignorecollection,includes,null);
//		}
//		for (String property : ((Map<String, ?>) beanMap).keySet()) {
//			if ("class".equals(property)) {
//				continue;
//			}
//			outputEntityProperty(context, beanMap, property,
//					ignoreEmptyProperty);
//		}
//		json.endObject();
//	}
//
//	protected void outputEntityProperty(OutputContext context,
//			Map entity, String property, boolean ignoreEmptyProperty)
//			throws Exception {
//		Object value = null;
//		value = entity.get(property);
////		if("children".equals(property)||"parent".equals(property)){
////			System.out.println(111);
////		}
//		if ((value != null&& !OutputUtils.isEscapeValue(value, OutputUtils.ESCAPE_VALUE) || !ignoreEmptyProperty)) {
//			JsonBuilder json = context.getJsonBuilder();
//			json.escapeableKey(property);
//			outputData(value, context);
//			json.endKey();
//		}
//	}
//}
