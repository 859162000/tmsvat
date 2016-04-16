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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.utils.JsonUtils;

/**
 * 用于向客户端输出JSON数据的输出器。
 * 
 * @author Benny Bao (mailto:benny.bao@bstek.com)
 * @since Oct 6, 2008
 */
@Component(DataOutputter.BEA_ID)
public class DataOutputter implements Outputter, PropertyOutputter {
	public static final String BEA_ID="dataOutputter";
	private static final Log logger = LogFactory.getLog(DataOutputter.class);
	private static final Long ONE_DAY = 1000L * 60 * 60 * 24;

	private boolean evaluateExpression = true;
	private boolean ignoreEmptyProperty=true;
	/**
	 * 决定是否输出非简单属性
	 */
	private boolean simplePropertyValueOnly=false;
	/**
	 * 决定是否输出非简单属性的时候输出集合属性
	 */
	private boolean ignorecollection=true;

	public boolean isEvaluateExpression() {
		return evaluateExpression;
	}

	public void setEvaluateExpression(boolean evaluateExpression) {
		this.evaluateExpression = evaluateExpression;
	}

	public boolean isIgnoreEmptyProperty() {
		return ignoreEmptyProperty;
	}

	public void setIgnoreEmptyProperty(boolean ignoreEmptyProperty) {
		this.ignoreEmptyProperty = ignoreEmptyProperty;
	}

	public boolean isSimplePropertyValueOnly() {
		return simplePropertyValueOnly;
	}

	public void setSimplePropertyValueOnly(boolean simplePropertyValueOnly) {
		this.simplePropertyValueOnly = simplePropertyValueOnly;
	}

	public boolean isIgnorecollection() {
		return ignorecollection;
	}

	public void setIgnorecollection(boolean ignorecollection) {
		this.ignorecollection = ignorecollection;
	}

	public boolean isEscapeValue(Object value) {
		return OutputUtils.isEscapeValue(value);
	}
	
	@Override
	public void output(Object object, OutputContext context) throws Exception {
		if (object != null) {
			outputData(object, context);
		} else {
			JsonBuilder json = context.getJsonBuilder();
			json.value(null);
		}
		
	}
	/**
	 * @param object
	 * @param writer
	 * @param context
	 * @throws Exception
	 */
	protected void outputData(Object object, OutputContext context)
			throws Exception {
		JsonUtils.outputEntity(object, context.getJsonBuilder(),new String[]{},new String[]{});
	}
}
