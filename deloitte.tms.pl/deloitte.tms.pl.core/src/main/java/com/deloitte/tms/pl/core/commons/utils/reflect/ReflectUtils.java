package com.deloitte.tms.pl.core.commons.utils.reflect;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.DateUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.criteria.PropertyCriteria;
import com.deloitte.tms.pl.core.commons.utils.reflect.criteria.PropertyCriteriaFactory;
import com.deloitte.tms.pl.core.commons.utils.reflect.filter.PropertyFilter;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.pl.core.enums.SimpleClazz;

@SuppressWarnings("unchecked")
public class ReflectUtils {

	public final static String APP_MODEL_PKG_NAME = "com.ling2.core.model";
	protected static String[] ignoreProperties={"firstinsert","lastmodified","insertman","updateman","dataStatu","company","serialVersionUID"};
	
	public static void copyProperties(Object source, Object target) {
		PropertyCriteria criteria = PropertyCriteriaFactory.create();
		copyProperties(source, target, criteria);
	}

	public static void copyProperties(Object source, Object target,
			PropertyCriteria criteria) {
		Assert.notNull(source, "Source must not be null");
		Assert.notNull(target, "Target must not be null");
		Assert.notNull(criteria, "Criteria must not be null");

		Class<? extends Object> actualEditable = target.getClass();
		PropertyDescriptor[] targetPds = BeanUtils
				.getPropertyDescriptors(actualEditable);

		for (int i = 0; i < targetPds.length; i++) {
			PropertyDescriptor targetPd = targetPds[i];

			if (targetPd.getWriteMethod() != null) {
				PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(
						source.getClass(), targetPd.getName());
				if (sourcePd != null && sourcePd.getReadMethod() != null) {
					try {
						Method srcReadMethod = sourcePd.getReadMethod();
						Method tgtReadMethod = targetPd.getReadMethod();

						accessMethod(srcReadMethod);
						accessMethod(tgtReadMethod);

						Object sourceValue = srcReadMethod.invoke(source,
								new Object[0]);
						Object targetValue = tgtReadMethod.invoke(target,
								new Object[0]);

						boolean permit = isPermittedProperty(sourcePd,
								sourceValue, criteria);
						if (permit) {
							//字段是ling2包下的基础对象类,则复制,否则只复制简单对象类型
							if (isTracedProperty(sourcePd)) {
								copyProperties(sourceValue, targetValue,
										criteria);
							} else if (isSimpleProperty(sourcePd)) {//复制简单对象类型
								Method writeMethod = targetPd.getWriteMethod();
								accessMethod(writeMethod);
								try {
									writeMethod.invoke(target,
											new Object[] { sourceValue });
								} catch (Exception e) {
									
								}
							}

						}

					} catch (Throwable ex) {
						throw new FatalBeanException(
								"Could not copy properties from source to target",
								ex);
					}
				}
			}
		}
	}

	private static boolean isPermittedProperty(PropertyDescriptor pd,
			Object value, PropertyCriteria criteria) {
		boolean retval = true;
		List<PropertyFilter> propertyFilters = criteria.getFilters();
		for (PropertyFilter filter : propertyFilters) {
			if (!filter.isPermit(pd, value)) {
				retval = false;
				break;
			}
		}
		return retval;
	}

	private static void accessMethod(Method method) {
		if (!Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
			method.setAccessible(true);
		}
	}
	/**
	 * 字段是ling2包下的基础对象类,则复制,否则只复制简单对象类型
	 * @param propertyDescriptor
	 * @return
	 */
	private static boolean isTracedProperty(
			PropertyDescriptor propertyDescriptor) {
		boolean retval = false;

		Class<? extends Object> propertyType = propertyDescriptor
				.getPropertyType();

		Package pkg = propertyType.getPackage();

		if (pkg != null
				&& pkg.getName().length() >= APP_MODEL_PKG_NAME.length()) {
			String pkgName = pkg.getName().substring(0,
					APP_MODEL_PKG_NAME.length());
			if (APP_MODEL_PKG_NAME.equals(pkgName)) {
				retval = true;
			}
		}

		return retval;
	}

	private static boolean isSimpleProperty(
			PropertyDescriptor propertyDescriptor) {
		boolean retval = false;

		Class<? extends Object> propertyType = propertyDescriptor
				.getPropertyType();

		for (SimpleClazz clazz : SimpleClazz.values()) {
			if (propertyType == clazz.getClazz()) {
				retval = true;
				break;
			}
		}

		return retval;
	}
	public static Boolean isSimpleField(Field field)
	{
		boolean retval = false;

		Class<? extends Object> propertyType = field.getType();

		for (SimpleClazz clazz : SimpleClazz.values()) {
			if (propertyType == clazz.getClazz()) {
				retval = true;
				break;
			}
		}
		if("boolean".equals(field.getType().toString())
				||"int".equals(field.getType().toString())
				||"long".equals(field.getType().toString())
				){
			retval = true;
		}
		return retval;
	}

	public static Class guessEntityClass(Class clazz) {
		Type type = clazz.getGenericSuperclass();
		Class retval = null;
		if (type instanceof ParameterizedType) {
			Type[] params = ((ParameterizedType) type).getActualTypeArguments();
			retval = (Class) params[0];
		}
		return retval;
	}

	public static Object getPropertyValue(Object object, String propertyName) {
		Class<? extends Object> clazz = object.getClass();
		try {
			PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(clazz,
					propertyName);
			if(pd!=null){
				Method readMethod = pd.getReadMethod();
				accessMethod(readMethod);
				return readMethod.invoke(object, new Object[0]);
			}else {
				return null;
			}
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	public static Object getPropertyValueWithPath(Object object,
			String propertyName) {
		if(object==null)
		{
			return null;
		}
		String tempsString = propertyName;
		Class<?> objClass = object.getClass();
		Object value = null;
		Integer index = tempsString.indexOf(".");
		try {
			if (index < 0) {
				String getMethodName = "get"
						+ toFirstLetterUpperCase(propertyName);
				value = objClass.getMethod(getMethodName).invoke(object);

			} else {
				String front = propertyName.substring(0, index);
				String endString = propertyName.substring(index + 1,
						propertyName.length());
				String getMethodName = "get" + toFirstLetterUpperCase(front);
				value = objClass.getMethod(getMethodName).invoke(object);
				value = getPropertyValueWithPath(value, endString);
			}
		} catch (Exception e) {
			//throw new BusinessException("字段名无效");
		}
		return value;
	}

	/**
	 * Copy obj to desc.
	 * 
	 */
	public static Map<String, Object> getProperties(Object obj) {
		return getProperties(obj,false,false);
	}
	/**
	 * 
	 * @param obj
	 * @param simplePropertyValueOnly 是否只输出简单对象
	 * @param ignorecollection 如果输出非简单对象,是否输出集合对象
	 * @return
	 */
	public static Map<String, Object> getProperties(Object obj,Boolean simplePropertyValueOnly,Boolean ignorecollection) {
		return getProperties(obj, simplePropertyValueOnly, ignorecollection, null, null);	
	}
	public static Map<String, Object> getProperties(Object obj,Boolean simplePropertyValueOnly,Boolean ignorecollection,String[] includesProperties, String[] excludesProperties) {
		Map<String, Object> values = new HashMap<String, Object>();
		Class<?> objClass = obj.getClass();
		Collection<Field> fields = getAllFileds(objClass);
		if(includesProperties==null)
		{
			includesProperties=new String[]{};
		}
		if(excludesProperties==null){
			excludesProperties=new String[]{};
		}
		excludesProperties=addStrings(excludesProperties, ignoreProperties);
		try {
			for (Field field:fields) {
				Class<?> c=field.getType();
				String fieldNmae=field.getName();
				if(isContainOfString(includesProperties,fieldNmae)){//在包含集合中,必须输出
					buildFieldMap(values,obj,field);
				}else{
					if(!isContainOfString(excludesProperties, fieldNmae)){//在排除集合中,直接忽略
						if(simplePropertyValueOnly){//只输出简单类型					
							if(isSimpleType(c)){
								buildFieldMap(values,obj,field);
							}
						}else{
							if(ignorecollection){//忽略集合类型
								if(!isCollectionType(c)){
									buildFieldMap(values,obj,field);
								}
							}else{//都输出
								buildFieldMap(values,obj,field);
							}
						}
					}					
				}				
				
			}
		} catch (Exception e) {

		}
		return values;
	}
	private static String[] addStrings(String[] strings1,String[] strings2){
		String[] newStrings=new String[strings1.length+strings2.length];
		int i=0;
		for(String string:strings1){
			newStrings[i]=string;
			i++;
		}
		for(String string:strings2){
			newStrings[i]=string;
			i++;
		}
		return newStrings;
	}
	private static Boolean isContainOfString(String[] strings,String string) {
		for(String temp:strings){
			if(temp!=null&&temp.equals(string)){
				return true;
			}
		}
		return false;
	}
	
	private static void buildFieldMap(Map<String, Object> values,Object obj,Field field) {
		try {
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			/**
			 * get方法优先,否则用反射默认方法
			 */
			Object value=getPropertyValue(obj, field.getName());
			if(value==null){
				value=field.get(obj);
			}
			if(Hibernate.isInitialized(value)){
				values.put(field.getName(), value);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	public static Object setProperties(Object obj, Map<String, Object> values) {
		Class<?> descClass = obj.getClass();
		Field[] fields = descClass.getDeclaredFields();
		try {
			for (String key : values.keySet()) {
				String setMethodName = "set" + toFirstLetterUpperCase(key);
				Object value = values.get(key);
				try {
					descClass.getMethod(setMethodName, value.getClass())
							.invoke(obj, value);
				} catch (Exception e) {
					// if(value.getClass().)
					// 这里需要处理int,long,boolean等的大小写情况
					e.printStackTrace();
				}
			}

		} catch (Exception e) {

		}
		return obj;
	}
	public static Object setProperty(Object obj, String property,Object value) {
		Class<?> descClass = obj.getClass();
		Field[] fields = descClass.getDeclaredFields();
		try {
			for (Field field:fields) {
				if(!field.isAccessible()){
					field.setAccessible(true);
				}
				if(field.getName().equals(property)){
					field.set(obj, value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	public static Object setPropertiesWithAutoParse(Object obj, Map<String, Object> values){
		Object value=null;
			for (String key : values.keySet()) {
				value = values.get(key);
				try {
					PropertyUtils.setProperty(obj, key, parse(obj,value,key));
				} catch (IllegalAccessException e) {
					throw new BusinessException("列"+key+":导入属性配置有误");
				} catch (InvocationTargetException e) {
					throw new BusinessException("列"+key+":导入属性配置有误");
				} catch (NoSuchMethodException e) {
					throw new BusinessException("列"+key+":导入属性配置有误");
				}
			}
		
		return obj;
	}
	public static List<Map<String, Object>> convertListMap(List datas)
	{
		List<Map<String, Object>> results=new ArrayList<Map<String,Object>>();
		for(Object obj:datas)
		{
			Class<?> objClass = obj.getClass();
			Field[] fields = objClass.getDeclaredFields();
			Map<String, Object> record=new HashMap<String, Object>();
			try {
				for (int i = 0; i < fields.length; i++) {
					String name = fields[i].getName();
					String getMethodName = "get" + toFirstLetterUpperCase(name);
					try {
						Object value = objClass.getMethod(getMethodName)
								.invoke(obj);
						record.put(toFirstLetterLowerCase(name), value);
					} catch (Exception e) {
					}
				}
			} catch (Exception e) {

			}
			results.add(record);
		}
		return results;
	}
	public static Object parse(Object value,Class<?> type){
		if(type==null){//映射的对象比传入的对象字段少会导致type为空
			return null;
		}
		String className=type.getName();
		if("float".equals(className))
		{
			if(value==null){
				return 0;
			}else{
				return (float)Float.parseFloat(value.toString());
			}
		}
		if("int".equals(className))
		{
			if(value==null){
				return 0;
			}else{
				return (int)Integer.parseInt(value.toString());
			}
		}
		if("double".equals(className))
		{
			if(value==null){
				return 0;
			}else{
				return (double)Double.parseDouble(value.toString());
			}
		}
		if(value==null)
		{
			return null;
		}				
		if("java.lang.Boolean".equals(className))
		{
			try {
				return parseBoolean(value);
			} catch (Exception e) {
				throw new BusinessException(value.toString()+":转换为boolean失败");
			}
		}
		if("boolean".equals(className))
		{
			try {
				return (boolean)parseBoolean(value);
			} catch (Exception e) {
				throw new BusinessException(value.toString()+":转换为boolean失败");
			}
		}
		if("java.lang.Integer".equals(className)||"int".equals(className))
		{
			try {
				return Double.valueOf(value.toString()).intValue();
			} catch (Exception e) {
				throw new BusinessException(value.toString()+":数字转换失败");
			}
		}
		if("java.lang.Long".equals(className))
		{
			String valueString=value.toString();
			if(valueString.endsWith(".0"))
			{
				int length=valueString.length();
				valueString=valueString.substring(0, length-2);
			}
			try {
				return Long.parseLong(valueString);
			} catch (Exception e) {
				throw new BusinessException(value.toString()+":转换为数字失败");
			}
		}
		if("java.lang.String".equals(className))
		{
			DecimalFormat df = new DecimalFormat("#"); 
			String valueString=null;
			if(value instanceof Integer)
			{
				valueString=df.format(value);
			}
			if(value instanceof Double)
			{
				valueString=df.format(value);
			}
			if(value instanceof Long)
			{
				valueString=df.format(value);
			}
			if(valueString==null) {
				valueString=value.toString();
			}
			if(valueString.endsWith(".0"))
			{
				int length=valueString.length();
				valueString=valueString.substring(0, length-2);
			}
			return valueString;
		}
		if("java.lang.Double".equals(className))
		{
			try {
				return Double.parseDouble(value.toString());
			} catch (Exception e) {
				throw new BusinessException(value.toString()+":转换为数字失败");
			}
		}
		if("java.util.Date".equals(className))
		{
			if(value instanceof Date)
			{
				return value;
			}
			else if(value instanceof String)
			{
				return DateUtils.convertText2Date(value.toString());
				
			}
		}
		return null;		
	}
	public static Object parse(Object object,Object value,String property)
	{
		try {
			Class<?> type=PropertyUtils.getPropertyType(object, property);
			return parse(value, type);
		} catch (NumberFormatException e) {
			throw new BusinessException("列"+property+":导入属性配置有误");
		} catch (IllegalAccessException e) {
			throw new BusinessException("列"+property+":导入属性配置有误");
		} catch (InvocationTargetException e) {
			throw new BusinessException("列"+property+":导入属性配置有误");
		} catch (NoSuchMethodException e) {
			throw new BusinessException("列"+property+":导入属性配置有误");
		}catch (NullPointerException e) {
			throw new BusinessException("列"+property+":导入属性配置有误");
		}
	}
	public static Boolean parseBoolean(Object value)
	{
		String valueString=value.toString();
		if("1".equals(valueString))
		{
			return true;
		}
		if("1.0".equals(valueString))
		{
			return true;
		}
		if("0".equals(valueString))
		{
			return false;
		}
		if("0.0".equals(valueString))
		{
			return false;
		}
		if("Y".equals(valueString))
		{
			return true;
		}
		if("y".equals(valueString))
		{
			return true;
		}
		if("Yes".equals(valueString))
		{
			return true;
		}
		if("YES".equals(valueString))
		{
			return true;
		}
		if("是".equals(valueString))
		{
			return true;
		}
		if("true".equals(valueString))
		{
			return true;
		}
		if("True".equals(valueString))
		{
			return true;
		}
		if("TRUE".equals(valueString))
		{
			return true;
		}
		if("N".equals(valueString))
		{
			return true;
		}
		if("n".equals(valueString))
		{
			return true;
		}
		if("no".equals(valueString))
		{
			return true;
		}
		if("NO".equals(valueString))
		{
			return true;
		}
		if("否".equals(valueString))
		{
			return true;
		}
		if("false".equals(valueString))
		{
			return true;
		}
		if("False".equals(valueString))
		{
			return true;
		}
		if("FALSE".equals(valueString))
		{
			return true;
		}
		if("".equals(valueString))
		{
			return false;
		}
		return Boolean.parseBoolean(value.toString());
	}
	public static String toFirstLetterUpperCase(String str) {
		if (str == null || str.length() < 2) {
			return str;
		}
		String firstLetter = str.substring(0, 1).toUpperCase();
		return firstLetter + str.substring(1, str.length());
	}

	public static String toFirstLetterLowerCase(String str) {
		if (str == null || str.length() < 2) {
			return str;
		}
		String firstLetter = str.substring(0, 1).toLowerCase();
		return firstLetter + str.substring(1, str.length());
	}

//	public static void main(String[] args) {
//		// Map params = new HashMap<String, String>();
//		// params.put("userName", "102010cncger");
//		// params.put("password", "111111");
//		// params.put("realName", "wang bo");
//		// params.put("type", "userregister");
//		// params.put("CLASSNAME",
//		// "com.cncger.security.client.model.CncgerUser");
//		// com.cncger.security.client.model.CncgerUser
//		// temp=(com.cncger.security.client.model.CncgerUser)BaseService.mapToObject(params);
//		// System.out.print(temp.getUserName());
//
////		Long personId = new Person();
////		Long channelRoleId = new ChannelRole();
////		channelRole.setAccount("111");
////		person.setChannelRole(channelRole);
////		ReflectUtils.getPropertyValueWithPath(person, "channelRole.account");
//		
//		TestModel testModel=new TestModel();
//		
//		try {
//			//PropertyUtils.setProperty(bean, name, value)
//			System.out.print(PropertyUtils.getPropertyType(testModel, "booleantest").getName());
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

	/**
	 * 根据propertyName返回数值
	 * 
	 * @param object
	 *            需要得到参数的object
	 * @param propertyName
	 *            属性名
	 * @return 属性值
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static Object getFieldContent(Object object, String propertyName)
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Class clazz = object.getClass();
		Field field = clazz.getDeclaredField(propertyName);
		if (!field.isAccessible()) {
			field.setAccessible(true);
		}
		Object fieldContent = field.get(object);
		return fieldContent;
	}

	/**
	 * 把传入对象的非空对象的值赋给持久化对象
	 * 
	 * @param persistentObject
	 *            持久化对象
	 * @param newObject
	 *            传入的对象(一般为dataset中获取的)
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static void replaceNullProperty(Object persistentObject,
			Object newObject) throws IllegalArgumentException,
			IllegalAccessException {
		Class clazz = persistentObject.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			if (!fields[i].isAccessible()) {
				fields[i].setAccessible(true);
			}
			Object fieldContent = fields[i].get(newObject);
			if (fieldContent != null
					&& !Modifier.isFinal(fields[i].getModifiers())) {
				if (fields[i].getType() == String.class
						|| fields[i].getType() == Integer.class
						|| fields[i].getType() == Long.class
						|| fields[i].getType() == Double.class
						|| fields[i].getType() == Date.class
						|| fields[i].getType() == Timestamp.class
						|| fields[i].getType() == Boolean.class) {
					fields[i].set(persistentObject, fieldContent);
				}
			}
		}
	}

	/**
	 * class中是否存在指定的方法
	 * 
	 * @param clazz
	 *            CLASS类型
	 * @param methodName
	 *            方法名
	 * @return
	 */
	public static boolean isContainMethod(Class clazz, String methodName) {
		Method[] methods = clazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals(methodName)) {
				return true;
			}
		}
		return false;
	}
	public static String getShortClassName(Field field)
	{
		Class<? extends Object> propertyType = field.getType();
		String className=propertyType.getName();
		Integer lastdotpoint=className.lastIndexOf(".");
		className=className.substring(lastdotpoint+1, className.length());
		return className;
	}
	/**
	 * 判断传入的数据是否是简单的数值。
	 */
	public static boolean isSimpleValue(Object data) {
		boolean b = (data == null || data instanceof String
				|| data.getClass().isPrimitive() || data instanceof Boolean
				|| data instanceof Number || data.getClass().isEnum()
				|| data instanceof Date || data instanceof Character);
		if (!b && data.getClass().isArray()) {
			b = isSimpleType(data.getClass().getComponentType());
		}
		return b;
	}

	/**
	 * 判断传入的数据是否是简单的数值类型。
	 */
	public static boolean isSimpleType(Class<?> cl) {
		boolean b = (String.class.equals(cl) || cl.isPrimitive()
				|| Boolean.class.equals(cl)
				|| Number.class.isAssignableFrom(cl) || cl.isEnum()
				|| Date.class.isAssignableFrom(cl) || Character.class
				.isAssignableFrom(cl));
		if (!b && cl.isArray()) {
			b = isSimpleType(cl.getComponentType());
		}
		return b;
	}
	/**
	 * 判断传入的数据是否是简单的数值类型。
	 */
	public static boolean isCollectionType(Class<?> cl) {
		if(isSimpleType(cl)){
			return false;
		}
		if(cl.isArray()||cl==Collections.class||cl==Set.class||cl==Map.class||cl==HashMap.class||cl==List.class||cl==ArrayList.class){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * 获取所有的filed,父类在后
	 * @param c
	 * @return
	 */
	public static Collection<Field> getAllFileds(Class c){
		return getAllFileds(c,false);
	}
	/**
	 * 
	 * 获取所有的filed
	 * @param c
	 * @param supertop 父类字段在前
	 * @return
	 */
	public static Collection<Field> getAllFileds(Class c,boolean supertop){
		List<Field> results=new ArrayList<Field>();
		if(supertop){
			if(c.getSuperclass()!=null){
				results.addAll(getAllFileds(c.getSuperclass()));
			}				
		}
		for(Field field:c.getDeclaredFields()){
			results.add(field);
		}
		if(!supertop){
			if(c.getSuperclass()!=null){
				results.addAll(getAllFileds(c.getSuperclass()));
			}
		}
		return results;
	}
	public static Class getParameterizedType(Field field){
		if (field.getType().getName().equals("java.util.List")) {
			Type fc = field.getGenericType(); // 关键的地方，如果是List类型，得到其Generic的类型
			if (fc == null)
				return null;
			if (fc instanceof ParameterizedType) // 【3】如果是泛型参数的类型
			{
				ParameterizedType pt = (ParameterizedType) fc;
				Class genericClazz = (Class) pt.getActualTypeArguments()[0]; // 【4】
				return genericClazz;
			}
		}
		return null;
	}
	public static void main(String[] args) {
//		HashMap test=new HashMap();
//		System.out.println(isCollectionType(test.getClass()));
	}
	
}
