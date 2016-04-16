package com.deloitte.tms.pl.jsonresponse.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.cglib.beans.BeanMap;

import org.apache.commons.collections.keyvalue.MultiKey;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.deloitte.tms.pl.core.commons.exception.ClientRunnableException;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.DomUtils;
import com.deloitte.tms.pl.core.commons.utils.JsonUtils;
import com.deloitte.tms.pl.core.commons.utils.PropertiesUtils;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.jsonresponse.constant.Constants;
import com.deloitte.tms.pl.jsonresponse.constant.HttpConstants;
import com.deloitte.tms.pl.jsonresponse.output.EscapeMode;
import com.deloitte.tms.pl.jsonresponse.output.JsonBuilder;
import com.deloitte.tms.pl.jsonresponse.output.OutputContext;
import com.deloitte.tms.pl.jsonresponse.output.OutputUtils;
import com.deloitte.tms.pl.jsonresponse.output.Outputter;
import com.deloitte.tms.pl.jsonresponse.output.PropertyConfig;
import com.deloitte.tms.pl.jsonresponse.output.PropertyOutputter;
import com.deloitte.tms.pl.jsonresponse.output.VirtualPropertyOutputter;
import com.deloitte.tms.pl.jsonresponse.xml.XmlDocumentBuilder;
import com.deloitte.tms.pl.jsonresponse.xml.XmlEscapeWriter;
@Component(LingRPCController.BEAN_ID)
public class LingRPCController extends AbstractController {
	public static final String BEAN_ID="rpcController";
	private String contentType = HttpConstants.CONTENT_TYPE_TEXT;
	private String cacheControl;
	private String characterEncoding = Constants.DEFAULT_CHARSET;
	private boolean shouldCompress = true;
	private static final Log logger = LogFactory
			.getLog(LingRPCController.class);

	private static final String JAVASCRIPT_TOKEN = "javascript";
	private static final String XML_TOKEN = "xml";
	private static final int BUFFER_SIZE = 4096;
	private XmlDocumentBuilder xmlDocumentBuilder;
	private static ObjectMapper objectMapper;
	private EscapeMode escapeMode = EscapeMode.AUTO;
	private Map<String, PropertyConfig> propertieConfigs = new HashMap<String, PropertyConfig>();
	private static Map<Object, Method[]> methodCache = new Hashtable<Object, Method[]>();
	
//	private static final Object[] EMPTY_ARGS = new Object[0];
//	private static final String[] EMPTY_NAMES = new String[0];
//	private static final Class<?>[] EMPTY_TYPES = new Class[0];	
	@Resource
	private Outputter dataOutputter;
	@Resource
	private PropertyOutputter propertyOutputter;
//	private Outputter includeDataTypesOutputter;	
//	private boolean usePrototype = false;
//	private String prototype;
	
	private static Object getCacheKey(Class<?> cl, String methodName) {
		return new MultiKey(cl, methodName);
	}
	/**
	 * 返回要输出的POJO属性的Map集合。
	 */
	public Map<String, PropertyConfig> getPropertieConfigs() {
		return propertieConfigs;
	}
	public boolean isShouldCompress() {
		return shouldCompress;
	}
	private XmlDocumentBuilder getXmlDocumentBuilder()
			throws Exception {
		if (xmlDocumentBuilder == null) {
			xmlDocumentBuilder = (XmlDocumentBuilder)  
					SpringUtil.getBean("xmlDocumentBuilder");
		}
		return xmlDocumentBuilder;
	}
	public static ObjectMapper getObjectMapper() {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		return objectMapper;
	}
	@Override
	protected final ModelAndView handleRequestInternal(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			return doHandleRequest(request, response);
		} finally {
			
		}
	}
	protected final ModelAndView doHandleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (contentType != null) {
			response.setContentType(contentType);
		}
		if (characterEncoding != null) {
			response.setCharacterEncoding(characterEncoding);
		}
		if (cacheControl != null) {
			response.addHeader(HttpConstants.CACHE_CONTROL, cacheControl);
			if (HttpConstants.NO_CACHE.equals(cacheControl)) {
				response.addHeader("Pragma", "no-cache");
				response.addHeader("Expires", "0");
			}
		}
		execute(request, response);
		return null;
	}	
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmlEscapeWriter writer = new XmlEscapeWriter(getWriter(request,
				response));
		ServletInputStream in = request.getInputStream();
		try {
			String contentType = request.getContentType();
			if (contentType != null && (contentType.contains(JAVASCRIPT_TOKEN)||contentType.contains(HttpConstants.AJAX_TYPE_TEXT))||contentType.contains(HttpConstants.AJAX_TYPE_JSON)) {
				Reader reader = new InputStreamReader(in,
						Constants.DEFAULT_CHARSET);
				StringBuffer buf = new StringBuffer();
				char[] cs = new char[BUFFER_SIZE];
				for (int n; (n = reader.read(cs)) != -1;) {
					buf.append(new String(cs, 0, n));
				}
				String content=buf.toString();
				System.out.println("in: "+content);
				if(AssertHelper.empty(content)){
					content="{}";
				}
				ObjectNode objectNode = (ObjectNode) getObjectMapper().readTree(content);
				processTask(writer, objectNode);
			} else if (contentType != null && contentType.contains(XML_TOKEN)) {
				Document document = getXmlDocumentBuilder()
						.loadDocument(
								new InputStreamResource(in, request
										.getRequestURI()));

				writer.append("<?xml version=\"1.0\" encoding=\""
						+ Constants.DEFAULT_CHARSET + "\"?>\n");
				writer.append("<result>\n");

				Writer escapeWriter = new XmlEscapeWriter(writer);
				for (Element element : DomUtils.getChildElements(document
						.getDocumentElement())) {
					writer.append("<request>\n");
					writer.append("<response type=\"json\"><![CDATA[\n");
					writer.setEscapeEnabled(true);
					
					String textContent = DomUtils.getTextContent(element);
					System.out.println("in: "+textContent);
					ObjectNode objectNode = (ObjectNode)getObjectMapper().readTree(textContent);
					try {
						processTask(escapeWriter, objectNode);
						writer.setEscapeEnabled(false);
						
						writer.append("\n]]></response>\n");
					} catch (Exception e) {
						Throwable t = e;
						while (t.getCause() != null) {
							t = t.getCause();
						}
						writer.setEscapeEnabled(false);

						writer.append("\n]]></response>\n");
						if (t instanceof ClientRunnableException) {
							writer.append("<exception type=\"runnable\"><![CDATA[");
							writer.setEscapeEnabled(true);
							writer.append(((ClientRunnableException) t)
									.getScript());
						} else {
							writer.append("<exception><![CDATA[\n");
							writer.setEscapeEnabled(true);
							outputException(writer, e);
						}
						writer.setEscapeEnabled(false);
						writer.append("\n]]></exception>\n");
						logger.error(e, e);
					}
					writer.append("</request>\n");
				}

				writer.append("</result>");
			}
		} catch (Exception e) {
			in.close();

			Throwable t = e;
			while (t.getCause() != null) {
				t = t.getCause();
			}

			if (t instanceof ClientRunnableException) {
				response.setContentType("text/runnable");
				writer.write(((ClientRunnableException) t).getScript());
			} else {
				response.setContentType("text/dorado-exception");
				outputException(writer, e);
			}

			logger.error(e, e);
		} finally {
			writer.flush();
			writer.close();
		}
	}
	/**
	 * @param writer
	 * @param jsonNode
	 * @param context
	 * @throws Exception
	 */
	protected void processTask(Writer writer, ObjectNode objectNode) throws Exception {
		doExecute(writer, objectNode);
	}
	protected void doExecute(Writer writer, ObjectNode objectNode) throws Exception{
		String serviceAlias = JsonUtils.getString(objectNode, "service");
		JsonNode data=objectNode.get("data");
		AssertHelper.notEmpty_assert(serviceAlias,"请求的服务不能为空");

		String serviceName = serviceAlias;
		if (serviceName == null) {
			throw new IllegalArgumentException("Invalid ServiceAlias ["
					+ serviceAlias + "].");
		}
		
		String beanId="";
		String methodName="";
		
		String beanAndMethod[]=serviceAlias.split("#");
		if(beanAndMethod.length!=2){
			throw new IllegalArgumentException("Erro ExposedService ["
					+ serviceName + "]. like Bean#Method");
		}
		beanId=beanAndMethod[0];
		methodName=beanAndMethod[1];
		if (AssertHelper.empty(methodName)||AssertHelper.empty(beanId)) {
			throw new IllegalArgumentException("Unknown ExposedService ["
					+ serviceName + "].");
		}
		
		Object serviceBean = SpringUtil.getBean(beanId);
		Method[] methods = getMethodsByName(
				serviceBean.getClass(), methodName);
		if (methods.length == 0) {
			throw new NoSuchMethodException("Method [" + methodName
					+ "] not found in [" + beanId + "].");
		}
		int i = 0;
		Object returnValue = null;
		boolean methodInvoked = false;
		Exception[] exceptions = new Exception[4];
		for(Method method:methods){			
			try {
				returnValue = method.invoke(serviceBean,data);
				methodInvoked = true;
			}catch (IllegalArgumentException e) {//参数不匹配异常,只记录,不处理
				exceptions[i++] = e;
			} catch (IllegalAccessException e) {//方法访问权限异常,只记录,不处理
				exceptions[i++] = e;
			}
		}
//		Object parameter = jsonToJavaObject(objectNode.get("parameter"),false);
//		MetaData sysParameter = (MetaData) jsonToJavaObject(objectNode.get("sysParameter"),false);
//
//		if (sysParameter != null && !sysParameter.isEmpty()) {
//			parameter = new ParameterWrapper(parameter, sysParameter);
//		}
//
//
//		Object returnValue = null;
////
//		boolean methodInvoked = false;
//		MethodAutoMatchingException[] exceptions = new MethodAutoMatchingException[4];
//		int i = 0;
//		try {
//			try {
//				returnValue = invokeByParameterName(serviceBean, methods,
//						parameter, false);
//				methodInvoked = true;
//			} catch (MoreThanOneMethodsMatchsException e) {
//				throw e;
//			} catch (MethodAutoMatchingException e) {
//				exceptions[i++] = e;
//			} catch (AbortException e) {
//				// do nothing
//			}
//
//			if (!methodInvoked) {
//				try {
//					returnValue = invokeByParameterName(serviceBean, methods,
//							parameter, true);
//					methodInvoked = true;
//				} catch (MoreThanOneMethodsMatchsException e) {
//					throw e;
//				} catch (MethodAutoMatchingException e) {
//					exceptions[i++] = e;
//				} catch (AbortException e) {
//					// do nothing
//				}
//			}
//
//			if (!methodInvoked) {
//				try {
//					returnValue = invokeByParameterType(serviceBean, methods,
//							parameter, false);
//					methodInvoked = true;
//				} catch (MoreThanOneMethodsMatchsException e) {
//					throw e;
//				} catch (MethodAutoMatchingException e) {
//					exceptions[i++] = e;
//				} catch (AbortException e) {
//					// do nothing
//				}
//			}
//
//			if (!methodInvoked) {
//				try {
//					returnValue = invokeByParameterType(serviceBean, methods,
//							parameter, true);
//					methodInvoked = true;
//				} catch (MoreThanOneMethodsMatchsException e) {
//					throw e;
//				} catch (MethodAutoMatchingException e) {
//					exceptions[i++] = e;
//				} catch (AbortException e) {
//					// do nothing
//				}
//			}
//		} catch (MethodAutoMatchingException e) {
//			exceptions[i++] = e;
//		}

		if (methodInvoked) {
			StringBuilder stringBuilder=new StringBuilder();
			OutputContext outputContext = new OutputContext(stringBuilder);
			outputContext.setUsePrettyJson(PropertiesUtils
					.getBoolean("view.outputPrettyJson"));
			outputObject(returnValue, outputContext);
			String content=stringBuilder.toString();
			System.out.println("out: "+content);
			writer.append(content);
		} else {
			for (Exception e : exceptions) {
				if (e == null) {
					break;
				}
				logger.error(e.getMessage());
			}
			throw new IllegalArgumentException(serviceAlias+"noMatchingMethodError");
		}
	}
	/**
	 * 返回给定的类中匹配某一方法名的所有方法。
	 * 
	 * @param cl
	 *            被查找的类。
	 * @param methodName
	 *            方法名。
	 * @return 找到的方法。
	 */
	public static Method[] getMethodsByName(Class<?> cl, String methodName) {
		Object cacheKey = getCacheKey(cl, methodName);
		Method[] methods = methodCache.get(cacheKey);
		if (methods == null) {
			List<Method> methodList = new ArrayList<Method>();
			Method[] allMethods = cl.getMethods();
			for (Method method : allMethods) {
				if (method.getName().equals(methodName)) {
					methodList.add(method);
				}
			}

			methods = new Method[methodList.size()];
			methodList.toArray(methods);
			methodCache.put(cacheKey, methods);
		}
		return methods;
	}
	/**
	 * 将一个Java的POJO对象输出成为JSON对象。
	 */
	protected void outputObject(Object object, OutputContext context)
			throws IOException, Exception {
		dataOutputter.output(object, context);
	}
	/**
	 * 输出一个Java的POJO对象中在{@link #getConfigProperties()}配置过的各个POJO属性。
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void outputObjectProperties(Object object, OutputContext context)
			throws Exception {
		Map beanMap;
		if (object instanceof Map) {
			beanMap = (Map) object;
		} else if (object.getClass() == Object.class) {
			beanMap = Collections.EMPTY_MAP;
		} else {
			beanMap = BeanMap.create(object);
		}

		Map<String, PropertyConfig> propertiesConfigs = getPropertieConfigs();
		PropertyConfig defaultPropertyConfig = propertiesConfigs.get("*");
		PropertyOutputter defaultPropertyOutputter = null;
		if (defaultPropertyConfig != null && !defaultPropertyConfig.isIgnored()) {
			defaultPropertyOutputter = (PropertyOutputter) defaultPropertyConfig
					.getOutputter();
		}
		if(defaultPropertyOutputter==null){
			defaultPropertyOutputter=propertyOutputter;
		}
		JsonBuilder json = context.getJsonBuilder();
		for (String property : ((Map<String, ?>) beanMap).keySet()) {
			if ("class".equals(property)) {
				continue;
			}

			PropertyConfig propertyConfig = propertiesConfigs.get(property);
			if (propertyConfig != null) {
				if (propertyConfig.isIgnored()) {
					continue;
				}

				Object outputter = propertyConfig.getOutputter();
				if (outputter != null
						&& outputter instanceof VirtualPropertyOutputter) {
					continue;
				}

				if (!propertyConfig.isEvaluateExpression()) {
//					OutputableExpressionUtils.disableOutputableExpression();
				}
				Object value = null;
				try {
					value = beanMap.get(property);
//					if (OutputableExpressionUtils.getSkipedExpression() != null) {
//						value = OutputableExpressionUtils.getSkipedExpression();
//					}
				} finally {
//					if (!propertyConfig.isEvaluateExpression()) {
//						OutputableExpressionUtils.enableOutputableExpression();
//					}
				}

				Object escapeValue = propertyConfig.getEscapeValue();
				boolean hasEscapeValue = (PropertyConfig.NONE_VALUE != escapeValue);
				if (hasEscapeValue) {
					if (OutputUtils.isEscapeValue(value, escapeValue)) {
						continue;
					}
				}

				PropertyOutputter propertyOutputter = (PropertyOutputter) outputter;
				if (propertyOutputter == null) {
					propertyOutputter = defaultPropertyOutputter;
				}
				if (propertyOutputter != null
						&& (hasEscapeValue || !propertyOutputter
								.isEscapeValue(value))) {
					json.escapeableKey(property);
					propertyOutputter.output(value, context);
					json.endKey();
				}
			} else {
				Object value = beanMap.get(property);
				if (defaultPropertyOutputter != null && value != null
						&& ReflectUtils.isSimpleValue(value)
						&& !defaultPropertyOutputter.isEscapeValue(value)) {
					json.escapeableKey(property);
					defaultPropertyOutputter.output(value, context);
					json.endKey();
				}
			}
		}

		for (Map.Entry<String, PropertyConfig> entry : propertiesConfigs
				.entrySet()) {
			Object propertyOutputter = entry.getValue().getOutputter();
			if (propertyOutputter != null
					&& propertyOutputter instanceof VirtualPropertyOutputter) {
				String property = entry.getKey();
				((VirtualPropertyOutputter) propertyOutputter).output(object,
						property, context);
			}
		}
	}
	protected boolean isEscapeable(OutputContext context) {
		if (EscapeMode.YES.equals(escapeMode)) {
			return true;
		} else if (EscapeMode.YES.equals(escapeMode)) {
			return false;
		} else {
			return context.isEscapeable();
		}
	}
	protected PrintWriter getWriter(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		OutputStream out = getOutputStream(request, response);
		return new PrintWriter(new OutputStreamWriter(out, characterEncoding));
	}
	/**
	 * 返回Response的输出流。
	 * 
	 * @throws IOException
	 */
	protected OutputStream getOutputStream(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int encodingType = 0;
		String encoding = request.getHeader(HttpConstants.ACCEPT_ENCODING);
		if (encoding != null) {
			encodingType = (encoding.indexOf(HttpConstants.GZIP) >= 0) ? 1
					: (encoding.indexOf(HttpConstants.COMPRESS) >= 0 ? 2 : 0);
		}

		OutputStream out = response.getOutputStream();
		if (encodingType > 0 && isShouldCompress()) {
			try {
				if (encodingType == 1) {
					response.setHeader(HttpConstants.CONTENT_ENCODING,
							HttpConstants.GZIP);
					out = new GZIPOutputStream(out);
				} else if (encodingType == 2) {
					response.setHeader(HttpConstants.CONTENT_ENCODING,
							HttpConstants.COMPRESS);
					out = new ZipOutputStream(out);
				}
			} catch (IOException e) {
				// do nothing
			}
		}
		return new BufferedOutputStream(out);
	}
	/**
	 * @param jsonBuilder
	 * @param e
	 */
	protected void outputException(Writer writer,Throwable throwable) {
		StringBuilder stringBuilder=new StringBuilder();
		JsonBuilder jsonBuilder=new JsonBuilder(stringBuilder);
		while (throwable.getCause() != null) {
			throwable = throwable.getCause();
		}

		String message = throwable.getMessage();
		if (message == null) {
			message = throwable.getClass().getSimpleName();
		}

		try {
			jsonBuilder.object(); //此行在部分情况下会报错，与出错之前JSONBuilder的输出状态相关
			jsonBuilder.key("exceptionType").value("JavaException")
					.key("message").value(message).key("stackTrace");
			jsonBuilder.array();
			StackTraceElement[] stackTrace = throwable.getStackTrace();
			for (StackTraceElement stackTraceElement : stackTrace) {
				jsonBuilder.value(stackTraceElement.getClassName() + '.'
						+ stackTraceElement.getMethodName() + '('
						+ stackTraceElement.getFileName() + ':'
						+ stackTraceElement.getLineNumber() + ')');
			}
			jsonBuilder.endArray();
			jsonBuilder.endObject();
			String content=stringBuilder.toString();
			System.out.println(" exception: "+content);
			writer.append(content);
		} catch (Exception e) {
			// ignore e!!!
			throwable.printStackTrace();
		}
	}
}
