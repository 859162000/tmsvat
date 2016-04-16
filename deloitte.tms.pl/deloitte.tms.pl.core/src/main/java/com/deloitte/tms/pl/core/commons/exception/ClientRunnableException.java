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

package com.deloitte.tms.pl.core.commons.exception;


/**
 * @author Benny Bao (mailto:benny.bao@bstek.com)
 *@since if(ex.isSessionKickAway()){
			sb.append("dorado.MessageBox.alert(\""+(String) PropertiesUtils.get("ling2.ajaxSessionKickAwayMessage")+"\",function(){\n");//PropertiesUtils.get
			sb.append("window.open(\""+contextPath+(String) PropertiesUtils.get("ling2.formLoginUrl")+"\",\"_top\");\n");
			sb.append("})");			
		}else{
			sb.append("dorado.MessageBox.alert(\""+(String) PropertiesUtils.get("ling2.ajaxSessionExpireMessage")+"\",function(){\n");
			sb.append("window.open(\""+contextPath+(String) PropertiesUtils.get("ling2.formLoginUrl")+"\",\"_top\");\n");
			sb.append("})");			
		}
        throw new ClientRunnableException(sb.toString());
 */
public class ClientRunnableException extends RuntimeException {
	private static final long serialVersionUID = 3478313367942463176L;
	private String script;

	public ClientRunnableException(String script) {
		this.script = script;
	}

	public String getScript() {
		return script;
	}
}
