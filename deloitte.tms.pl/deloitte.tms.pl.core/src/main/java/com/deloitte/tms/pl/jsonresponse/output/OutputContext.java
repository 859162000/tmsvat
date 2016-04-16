/*
 * This file is part of Dorado 7.x (http://dorado7.bsdn.org).
 * 
 * Copyright (c) 2002-2012 BSTEK Corp. All rights reserved.
 * 
 * This file is dual-licensed under the AGPLv3 (http://www.gnu.org/licenses/agpl-3.0.html) 
 * and BSDN commercial (http://www.bsdn.org/licenses) licenses.
 * 
 * If you are unsure which license is appropriate for your use, please contact the sales department
 * at http://www.bstek.com/contact.
 */

package com.deloitte.tms.pl.jsonresponse.output;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

import com.deloitte.tms.pl.core.commons.utils.Assert;

/**
 * 输出器上下文。
 * 
 * @author Benny Bao (mailto:benny.bao@bstek.com)
 * @since Oct 6, 2008
 */
public class OutputContext {
	private StringBuilder writer;
	private Stack<JsonBuilder> jsonBuilders = new Stack<JsonBuilder>();
	private boolean usePrettyJson;
	private boolean shouldOutputDataTypes = true;
	private String outputtableDataTypeIdPrefix;
	private boolean shouldOutputEntityState;
	private boolean escapeable;
	private Set<String> loadedDataTypes;
	private Set<String> dependsPackages;
	private Set<Object> javaScriptContents;
	private Set<Object> styleSheetContents;
	private Stack<Object> dataObjectStack;
	private int calloutSN;
	private Integer loopNum=0;
	

	public Integer getLoopNum() {
		return loopNum;
	}

	public void setLoopNum(Integer loopNum) {
		this.loopNum = loopNum;
	}

	public OutputContext(StringBuilder writer) {
		this.writer = writer;
	}

	/**
	 * @return
	 */
	public StringBuilder getWriter() {
		return writer;
	}

	/**
	 * @return
	 */
	public JsonBuilder getJsonBuilder() {
		if (jsonBuilders.isEmpty()) {
			return createJsonBuilder();
		} else {
			return jsonBuilders.peek();
		}
	}

	public JsonBuilder createJsonBuilder() {
		JsonBuilder jsonBuilder = new JsonBuilder(getWriter(), true);
		if (usePrettyJson) {
			jsonBuilder.setPrettyFormat(true);
			if (!jsonBuilders.isEmpty()) {
				JsonBuilder parentJsonBuilder = jsonBuilders.peek();
				jsonBuilder.setLeadingTab(parentJsonBuilder.getLeadingTab());
			}
		}
		jsonBuilders.push(jsonBuilder);
		return jsonBuilder;
	}

	public void restoreJsonBuilder() {
		if (!jsonBuilders.isEmpty()) {
			jsonBuilders.pop();
		}
	}

	public boolean isUsePrettyJson() {
		return usePrettyJson;
	}

	public void setUsePrettyJson(boolean usePrettyJson) {
		this.usePrettyJson = usePrettyJson;
	}

	/**
	 * 是否需要向客户端输出DataType的信息。
	 */
	public boolean isShouldOutputDataTypes() {
		return shouldOutputDataTypes;
	}

	/**
	 * 设置是否需要向客户端输出DataType的信息。默认值为true。
	 */
	public void setShouldOutputDataTypes(boolean shouldOutputDataTypes) {
		this.shouldOutputDataTypes = shouldOutputDataTypes;
	}

	public String getOutputtableDataTypeIdPrefix() {
		return outputtableDataTypeIdPrefix;
	}

	public void setOutputtableDataTypeIdPrefix(
			String outputtableDataTypeIdPrefix) {
		this.outputtableDataTypeIdPrefix = outputtableDataTypeIdPrefix;
	}

	/**
	 * @return the shouldOutputEntityState
	 */
	public boolean isShouldOutputEntityState() {
		return shouldOutputEntityState;
	}

	/**
	 * @param shouldOutputEntityState
	 *            the shouldOutputEntityState to set
	 */
	public void setShouldOutputEntityState(boolean shouldOutputEntityState) {
		this.shouldOutputEntityState = shouldOutputEntityState;
	}

	public boolean isEscapeable() {
		return escapeable;
	}

	public void setEscapeable(boolean escapeable) {
		this.escapeable = escapeable;
	}

	public void setLoadedDataTypes(Collection<String> loadedDataTypes) {
		if (loadedDataTypes != null && !(loadedDataTypes instanceof Set<?>)) {
			Collection<String> collection = loadedDataTypes;
			loadedDataTypes = new HashSet<String>();
			for (String dataTypeName : collection) {
				loadedDataTypes.add(dataTypeName);
			}
		}
		this.loadedDataTypes = (Set<String>) loadedDataTypes;
	}

	public boolean isDataTypeLoaded(String dataTypeName) {
		return (loadedDataTypes != null) ? loadedDataTypes
				.contains(dataTypeName) : false;
	}

	/**
	 * 返回客户端依赖的资源包的集合。
	 */
	public Set<String> getDependsPackages() {
		return (Set<String>) Collections.unmodifiableSet(dependsPackages);
	}

	public Set<Object> getJavaScriptContents() {
		return javaScriptContents;
	}

	public void addJavaScriptContent(Object content) {
		Assert.notNull(content);
		if (javaScriptContents == null) {
			javaScriptContents = new LinkedHashSet<Object>();
		}
		javaScriptContents.add(content);
	}

	public Set<Object> getStyleSheetContents() {
		return styleSheetContents;
	}

	public void addStyleSheetContent(Object content) {
		Assert.notNull(content);
		if (styleSheetContents == null) {
			styleSheetContents = new LinkedHashSet<Object>();
		}
		styleSheetContents.add(content);
	}

	/**
	 * 用于放置对象递归引用导致输出过程死锁的堆栈。
	 */
	public Stack<Object> getDataObjectStack() {
		if (dataObjectStack == null) {
			dataObjectStack = new Stack<Object>();
		}
		return dataObjectStack;
	}

	public String getCalloutId() {
		return String.valueOf(++calloutSN);
	}
}
