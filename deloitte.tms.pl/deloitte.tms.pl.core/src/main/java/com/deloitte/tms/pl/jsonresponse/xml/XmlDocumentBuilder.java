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

package com.deloitte.tms.pl.jsonresponse.xml;

import org.springframework.core.io.InputStreamResource;
import org.w3c.dom.Document;

/**
 * XML读取工具类的通用接口。
 * <p>
 * 读取将给定的{@link com.bstek.dorado.core.io.Resource}中的XML资源解析为
 * {@link org.w3c.dom.Document}。
 * </p>
 * @author Benny Bao (mailto:benny.bao@bstek.com)
 * @since Feb 15, 2007
 * @see com.bstek.dorado.core.io.Resource
 * @see org.w3c.dom.Document
 */
public interface XmlDocumentBuilder {
	public static final String BEAN_ID="xmlDocumentBuilder";

	/**
	 * 创建一个空的XMLDocument对象。
	 * @return XML的Document对象。
	 * @throws Exception
	 */
	Document newDocument() throws Exception;

	/**
	 * 解析resource中的XML资源并返回DOM对象。
	 * @param resource 资源描述对象。
	 * @return XML的Document对象。
	 * @throws Exception
	 */
	Document loadDocument(InputStreamResource resource) throws Exception;

	/**
	 * 解析resource中的XML资源并返回DOM对象。
	 * @param resource 资源描述对象。
	 * @param charset 字符集。
	 * @return XML的Document对象。
	 * @throws Exception
	 */
	Document loadDocument(InputStreamResource resource, String charset) throws Exception;
}
