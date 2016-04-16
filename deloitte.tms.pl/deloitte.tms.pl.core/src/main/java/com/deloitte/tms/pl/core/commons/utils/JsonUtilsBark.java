package com.deloitte.tms.pl.core.commons.utils;
//package com.ling2.core.commons.utils;
//
//import java.io.InputStreamReader;
//import java.io.Reader;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.sql.Time;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Stack;
//import java.util.regex.Pattern;
//
//import javax.servlet.ServletInputStream;
//import javax.servlet.http.HttpServletRequest;
//
//import net.sf.cglib.proxy.MethodProxy;
//
//import org.codehaus.jackson.JsonNode;
//import org.codehaus.jackson.map.DeserializationConfig.Feature;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.codehaus.jackson.node.ArrayNode;
//import org.codehaus.jackson.node.BooleanNode;
//import org.codehaus.jackson.node.ContainerNode;
//import org.codehaus.jackson.node.NumericNode;
//import org.codehaus.jackson.node.ObjectNode;
//import org.codehaus.jackson.node.TextNode;
//import org.codehaus.jackson.node.ValueNode;
//
//import com.ling2.core.commons.support.DaoPage;
//import com.ling2.core.commons.utils.reflect.ReflectUtils;
//import com.ling2.jsonresponse.constant.Constants;
//import com.ling2.jsonresponse.constant.DataTypeDef;
//import com.ling2.jsonresponse.output.JsonBuilder;
//import com.ling2.jsonresponse.output.OutputUtils;
//
///**
// * 用于为Json数据处理提供辅助操作的工具类。
// * 
// * @author Benny Bao (mailto:benny.bao@bstek.com)
// * @since Nov 11, 2008
// */
//public final class JsonUtilsBark {
//	public static final String WRAPPER_PROPERTY = "$isWrapper";
//	public static final String DATA_PROPERTY = "data";
//	public static final String DATATYPE_PROPERTY = "$dataType";
//	public static final String STATE_PROPERTY = "$state";
//	public static final String ENTITY_ID_PROPERTY = "$entityId";
//	public static final String OLD_DATA_PROPERTY = "$oldData";
//
//	private static final char SYSTEM_PROPERTY_PREFIX = '$';
//	private static final int DEFAULT_DATE_PATTERN_LEN = 20;
//	private static final Pattern DEFAULT_DATE_PATTERN = Pattern
//			.compile("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z$");
//	
//	private static final int BUFFER_SIZE = 4096;
//
//	public static String getJsonDataFromRequestAsString(HttpServletRequest request) throws Exception
//	{
//		ServletInputStream in = request.getInputStream();
//		Reader reader = new InputStreamReader(in,Constants.DEFAULT_CHARSET);
//		StringBuffer buf = new StringBuffer();
//		char[] cs = new char[BUFFER_SIZE];
//		for (int n; (n = reader.read(cs)) != -1;) {
//			buf.append(new String(cs, 0, n));
//		}
//		String content=buf.toString();
//		FileUtils.safeClose(in);
//		FileUtils.safeClose(reader);
//		return content;
//	}
//	public static ObjectNode getJsonDataFromRequestAsObjectNode(HttpServletRequest request) throws Exception
//	{
//		String content=getJsonDataFromRequestAsString(request);
//		if(AssertHelper.empty(content)){
//			content="{}";
//		}
//		ObjectNode objectNode = (ObjectNode) getObjectMapper().readTree(content);
//		return objectNode;
//	}
//	private JsonUtilsBark() {
//	}
//	public static void outputEntity(Object object,JsonBuilder json)throws Exception{
//		Stack<String> repeatecheck=new Stack();
//		if (ReflectUtils.isSimpleValue(object)) {
//			outputSimpleObject(object,json);
//		}else if (object instanceof Collection<?>) {
//			outputCollection(object, json,false,false);
//		} else if (object instanceof Map) {
//			outputMap(object, json, false, false);
//		}
//		else if (object instanceof DaoPage) {
//			outputPage((DaoPage) object, json);
//		} else{
//			String classname=object.getClass().toString();
//			outputComplexObject(object, json,false,false);
//			repeatecheck.pop();
//		}
//	}
//	public static void outputEntityWithAllPath(Object object,JsonBuilder json)throws Exception{
//		if (ReflectUtils.isSimpleValue(object)) {
//			outputSimpleObject(object,json);
//		}else if (object instanceof Collection<?>) {
//			outputCollection(object, json,false,true);
//		}  else if (object instanceof Map) {
//			outputMap(object, json, false, true);
//		}else if (object instanceof DaoPage) {
//			outputPage((DaoPage) object, json);
//		} else{
//			outputComplexObject(object, json,false,true);
//		}
//	}
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	private static void outputComplexObject(Object object,JsonBuilder json,Boolean isSimplePropertyOnly,Boolean isAllPath)throws Exception{
//		Boolean ignoreEmptyProperty=true;
//		if(ReflectUtils.isSimpleValue(object)){
//			outputSimpleObject(object,json);
//		}if (object instanceof Collection<?>) {//二层需要数据中的数据类型,比如url的children
//			if(isAllPath){
//				outputCollection(object, json,isSimplePropertyOnly,isAllPath);//控制输出负责对象二级对象中的集合 这里不输出,不然数据量太大,如果需要这个数据,需要额外写方法
//			}
//		}else if (object instanceof DaoPage) {
//			outputPage((DaoPage) object, json);
//		}else{
//			json.object();
//			Map beanMap = ReflectUtils.getProperties(object,isSimplePropertyOnly,isSimplePropertyOnly,null,null);
//			for (String property : ((Map<String, ?>) beanMap).keySet()) {
//				if ("class".equals(property)) {
//					continue;
//				}
//				Object value= beanMap.get(property);
//				if ((value != null&& !OutputUtils.isEscapeValue(value, OutputUtils.ESCAPE_VALUE) || !ignoreEmptyProperty)){
////					json.escapeableKey(property);
//					System.out.println(json.getWriter().toString());
//					json.key(property);
//					if(ReflectUtils.isSimpleValue(value)){						
//						outputSimpleObject(value, json);						
//					}else if(!isSimplePropertyOnly){
//						outputComplexObject(value, json, isSimplePropertyOnly,isAllPath);
//					}
//					json.endKey();
//				}				
//			}
//			json.endObject();
//		}
//	}
//	private static void  outputSimpleObject(Object object,JsonBuilder json){
//		if (ReflectUtils.isSimpleValue(object)) {
//			if (object instanceof Date) {
//				Date d = (Date) object;
//				if (d instanceof Time || d instanceof Timestamp
//						|| d.getTime() % DateUtils.ONE_DAY != 0) {
//					json.value(DateUtils
//							.format(DateUtils.ISO_DATETIME_FORMAT1,
//									d));
//				} else {
//					json.value(DateUtils.format(
//							DateUtils.ISO_DATE_FORMAT, d));
//				}
//			} else {
//				json.value(object);
//			}
//		}
//	}
//	private static void outputCollection(Object object,JsonBuilder json,Boolean isSimplePropertyOnly,Boolean isAllPath)throws Exception{
//		json.array();
//		if (object instanceof Collection<?>) {
//			for(Object entity:(Collection<?>)object){
//				outputComplexObject(entity,json,isSimplePropertyOnly,isAllPath);
//			}
//		}
//		json.endArray();
//	}
//	private static void outputMap(Object object,JsonBuilder json,Boolean isSimplePropertyOnly,Boolean isAllPath)throws Exception{
//		Boolean ignoreEmptyProperty=true;
//		json.array();
//		if (object instanceof Map) {
//			Map map=(Map)object;
//			for (String property : ((Map<String, ?>) map).keySet()) {
//				if ("class".equals(property)) {
//					continue;
//				}
//				Object value= map.get(property);
//				if ((value != null&& !OutputUtils.isEscapeValue(value, OutputUtils.ESCAPE_VALUE) || !ignoreEmptyProperty)){
//					json.key(property);
//					if(ReflectUtils.isSimpleValue(value)){						
//						outputSimpleObject(value, json);						
//					}else if(!isSimplePropertyOnly){
//						outputComplexObject(value, json, isSimplePropertyOnly,isAllPath);
//					}
//					json.endKey();
//				}				
//			}
//		}
//		json.endArray();
//	}
//	private static void outputPage(DaoPage page, JsonBuilder json)
//			throws Exception {
//		json.object();
//		json.key(DataTypeDef.DATATYPE).value(DataTypeDef.DAOPAGE);
//
//		Collection<?> entities = page.getResult();
//		if (entities != null) {
//			json.key("result");
//			json.array();
//			for (Object e : entities) {
//				outputComplexObject(e, json,true,false);
//			}
//			json.endArray();
//		}
//
//		json.key("pageSize").value(page.getPageSize());
//		json.key("pageIndex").value(page.getPageIndex());
//		json.key("pageCount").value(page.getPageCount());
//		json.key("recordCount").value(page.getRecordCount());
//		json.endObject();
//	}
//	public static String getString(ObjectNode objectNode, String property,
//			String defaultValue) {
//		JsonNode propertyNode = objectNode.get(property);
//		return (propertyNode != null && !propertyNode.isNull()) ? propertyNode
//				.getTextValue() : defaultValue;
//	}
//
//	public static String getString(ObjectNode objectNode, String property) {
//		return getString(objectNode, property, null);
//	}
//
//	public static boolean getBoolean(ObjectNode objectNode, String property,
//			boolean defaultValue) {
//		JsonNode propertyNode = objectNode.get(property);
//		return (propertyNode != null && !propertyNode.isNull()) ? propertyNode
//				.asBoolean() : defaultValue;
//	}
//
//	public static boolean getBoolean(ObjectNode objectNode, String property) {
//		return getBoolean(objectNode, property, false);
//	}
//
//	public static int getInt(ObjectNode objectNode, String property,
//			int defaultValue) {
//		JsonNode propertyNode = objectNode.get(property);
//		return (propertyNode != null && !propertyNode.isNull()) ? propertyNode
//				.asInt() : defaultValue;
//	}
//
//	public static int getInt(ObjectNode objectNode, String property) {
//		return getInt(objectNode, property, 0);
//	}
//
//	public static long getLong(ObjectNode objectNode, String property,
//			long defaultValue) {
//		JsonNode propertyNode = objectNode.get(property);
//		return (propertyNode != null && !propertyNode.isNull()) ? propertyNode
//				.asLong() : defaultValue;
//	}
//
//	public static long getLong(ObjectNode objectNode, String property) {
//		return getLong(objectNode, property, 0);
//	}
//
//	public static float getFloat(ObjectNode objectNode, String property,
//			float defaultValue) {
//		JsonNode propertyNode = objectNode.get(property);
//		return (propertyNode != null && !propertyNode.isNull()) ? ((float) propertyNode
//				.asDouble()) : defaultValue;
//	}
//
//	public static float getFloat(ObjectNode objectNode, String property) {
//		return getFloat(objectNode, property, 0);
//	}
//
//	public static double getDouble(ObjectNode objectNode, String property,
//			double defaultValue) {
//		JsonNode propertyNode = objectNode.get(property);
//		return (propertyNode != null && !propertyNode.isNull()) ? propertyNode
//				.asDouble() : defaultValue;
//	}
//
//	public static double getDouble(ObjectNode objectNode, String property) {
//		return getDouble(objectNode, property, 0);
//	}
//
//	public static Date getDate(ObjectNode objectNode, String property,
//			Date defaultValue) {
//		JsonNode propertyNode = objectNode.get(property);
//		return (propertyNode != null && !propertyNode.isNull()) ? DateUtils
//				.toDate(propertyNode.getTextValue()) : defaultValue;
//	}
//
//	public static Date getDate(ObjectNode objectNode, String property) {
//		return getDate(objectNode, property, null);
//	}
//	public static Object toObject(JsonNode jsonNode,Class<?> targetType) throws Exception{
//		if (jsonNode == null || jsonNode.isNull()) {
//			return null;
//		}
//		if (jsonNode instanceof ObjectNode) {
//			return internalToJavaEntity((ObjectNode) jsonNode,targetType);
//		} else if (jsonNode instanceof ArrayNode) {
//			return internalToJavaCollection((ArrayNode) jsonNode,targetType);
//		} else {
//			return toJavaValue((ValueNode) jsonNode,targetType);
//		}
//	}
//	private static Object toJavaValue(ValueNode valueNode,Class<?> type) {
//		Object value = null;
//		if (valueNode != null) {
//			if (valueNode instanceof TextNode) {
//				value = ((TextNode) valueNode).getTextValue();
//			} else if (valueNode instanceof NumericNode) {
//				value = ((NumericNode) valueNode).getNumberValue();
//			} else if (valueNode instanceof BooleanNode) {
//				value = ((BooleanNode) valueNode).asBoolean();
//			} else {
//				value = valueNode.getTextValue();
//			}
//		}
//		value=ReflectUtils.parse(value, type);
//		return value;
//	}
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	private static Object internalToJavaEntity(ObjectNode objectNode,Class<?> targetType) throws Exception {
//		if (objectNode == null || objectNode.isNull()) {
//			return null;
//		}
//		String dataType=getString((ObjectNode)objectNode, DataTypeDef.DATATYPE);
//		if(DataTypeDef.DAOPAGE.equals(dataType)){
//			return toJavaDaoPageEntity(objectNode,targetType);
//		}
////		Constructor<?> constr = targetType.getConstructor();
////		if (constr == null) {
////			throw new NoSuchMethodException(
////					"Can not found a suitable constructor for ["
////							+ targetType + "].");
////		}
////		net.sf.cglib.proxy.Enhancer enhancer = new net.sf.cglib.proxy.Enhancer();
////		enhancer.setSuperclass(targetType);
////		Callback callback=new MethodInterceptor();
////		enhancer.setCallback(callback);
////		Object entity=enhancer.create();
////		Object entity=constr.newInstance();
//		Object entity=targetType.newInstance();
//		Iterator<Entry<String, JsonNode>> fields = objectNode.getFields();
//		while (fields.hasNext()) {
//			Entry<String, JsonNode> field = fields.next();
//			String property = field.getKey();
//			if (property.charAt(0) == SYSTEM_PROPERTY_PREFIX) {
//				continue;
//			}
//
//			Object value = null;
//			JsonNode jsonNode = field.getValue();
//			if (jsonNode != null) {
//				Class<?> type = null;
//				Field classfield=null;
//				try {
//					classfield = targetType.getDeclaredField(property);
//				} catch (Exception e) {
////					System.out.println(property+" not found");
//				}
//				if(classfield!=null){
//					type=classfield.getType();
//				}				
//				if (jsonNode instanceof ContainerNode) {
//					value = internalToJavaEntity((ObjectNode)jsonNode,type);
//				} else if (jsonNode instanceof ValueNode) {
//					value = toJavaValue((ValueNode) jsonNode,type);
//				} else {
//					throw new IllegalArgumentException(
//							"Value type mismatch. expect [JSON Value].");
//				}
//				if (type != null) {
////					if (!type.isInstance(value)) {
////						if (value instanceof String && type.isEnum()) {
////							if (StringUtils.isNotBlank((String) value)) {
////								value = Enum.valueOf(
////										(Class<? extends Enum>) type,
////										(String) value);
////							}
////						} else {
////							throw new IllegalArgumentException(
////									"Value type mismatch. expect [JSON Value].");
////						}
////					}
//				} else {
//					if (value instanceof String) { // 处理日期字符串
//						String str = (String) value;
//						if (str.length() == DEFAULT_DATE_PATTERN_LEN
//								&& DEFAULT_DATE_PATTERN.matcher(str).matches()) {
//							value = DateUtils
//									.parse(DateUtils.ISO_DATETIME_FORMAT1,str);
//						}
//					}
//				}
//			}
//			ReflectUtils.setProperty(entity, property,value);
//		}
//		return entity;
//	}
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	private static Object toJavaDaoPageEntity(ObjectNode objectNode,Class<?> targetType) throws Exception {
//		if (objectNode == null || objectNode.isNull()) {
//			return new DaoPage();
//		}
//		DaoPage daoPage=new DaoPage();
//		daoPage.setRecordCount(getInt(objectNode, "recordCount"));
//		daoPage.setPageIndex(getInt(objectNode, "pageIndex"));
//		daoPage.setPageSize(getInt(objectNode, "pageSize"));
//		
//		JsonNode resultNode=objectNode.get("result");
//		if(resultNode==null||resultNode.isNull()){
//			return daoPage;
//		}
//		List resultList=null;
//		if (resultNode instanceof ArrayNode) {
//			resultList= (List) internalToJavaCollection((ArrayNode) resultNode,targetType);
//		}
//		daoPage.setResult(resultList);
//		return daoPage;
//	}
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	private static Collection internalToJavaCollection(ArrayNode arrayNode,Class<?> targetType) throws Exception {
//		Collection collection = new ArrayList<Object>();;
//		Iterator<JsonNode> it = arrayNode.iterator();
//		while (it.hasNext()) {
//			JsonNode jsonNode = it.next();
//			if (jsonNode instanceof ObjectNode) {
//				ObjectNode objectNode = (ObjectNode) jsonNode;
//				collection.add(internalToJavaEntity(objectNode,targetType));
//			} else if (jsonNode instanceof ValueNode) {
//				collection.add(toJavaValue((ValueNode) jsonNode,targetType));
//			} else {
//				throw new IllegalArgumentException(
//						"Value type mismatch. expect [JSON Value].");
//			}
//		}
//		return collection;
//	}
////	public static String toJSON(Object object) throws Exception {
////		StringWriter value = new StringWriter();
////		JsonGenerator generator = new JsonFactory().createJsonGenerator(value);
////		getObjectMapper().writeValue(generator, object);
////		return value.toString();
////	}
////
////	public static <T> T fromJSON(String data, Class<T> clazz) throws Exception {
////		return getObjectMapper().readValue(data, clazz);
////	}
////
////	public static ObjectMapper getObjectMapper() {
////		ObjectMapper mapper = new ObjectMapper();
////		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
////		return mapper;
////	}
//	public static String toJSON(Object object) throws Exception {
//		StringBuilder stringBuilder=new StringBuilder();
//		JsonBuilder jsonBuilder=new JsonBuilder(stringBuilder);
//		outputEntity(object, jsonBuilder);
//		return stringBuilder.toString();
//	}
//
//	public static Object fromJSON(String data, Class clazz) throws Exception {
//		JsonNode objectNode = getObjectMapper().readTree(data);
//		return toObject(objectNode, clazz);
////		return getObjectMapper().readValue(data, clazz);
//	}
//
//	public static ObjectMapper getObjectMapper() {
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		return mapper;
//	}
//}
//class MethodInterceptor implements net.sf.cglib.proxy.MethodInterceptor
//{
//
//	@Override
//	public Object intercept(Object obj, Method method, Object[] args,
//			MethodProxy proxy) throws Throwable {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//}
