package com.deloitte.tms.pl.workflow.expr.impl;

import java.util.Map;

import org.apache.commons.jexl2.MapContext;

/**
 * @author Jacky.gao
 * @since 2013年8月15日
 */
public class ProcessMapContext extends MapContext {
	public Map<String,Object> getMap(){
		return map;
	}
}
