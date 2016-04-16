package com.deloitte.tms.pl.core.filter;

import javax.servlet.http.*;
/** 
 * @作者： [张阿强]<br>
 * @版本： [V1.0.0, 2011-1-19]<br>
 * @描述： <br>
 */
public class CJSCFilterRequestWrapper extends HttpServletRequestWrapper {
	public CJSCFilterRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	public String getRemoteUser() {
		return (String) getSession().getAttribute(CJSCFilter.CAS_FILTER_USER);
	}
}
