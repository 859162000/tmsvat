package com.deloitte.tms.pl.security.controller.impl;
//package com.ling2.security.controller.impl;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Set;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.dom4j.Document;
//import org.dom4j.DocumentHelper;
//import org.dom4j.Element;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import com.ling2.core.commons.utils.PropertiesUtils;
//import com.ling2.core.controller.IController;
//@Component
//public class PropertiesController implements IController {
//
//	private boolean disabled = false;
//
//	private String url;
//
//	public String getUrl() {
//		return StringUtils.hasText(url) ? url : "/properties.list";
//	}
//
//	public void execute(HttpServletRequest request, HttpServletResponse response)
//			throws IOException, ServletException {
//		Set<String> keySet = PropertiesUtils.getStore().keySet();
//		Document dom = DocumentHelper.createDocument();
//		Element properties = dom.addElement("properties");
//		Element categoryElement = properties.addElement("category");
//
//		Element nameElement = categoryElement.addElement("name");
//
//		nameElement.setText("BDF2");
//
//		for (String entry : keySet) {
//			Element propertyElement = categoryElement.addElement("property");
//
//			Element element = propertyElement.addElement("key");
//			element.setText(entry);
//			element = propertyElement.addElement("value");
//			element.setText(PropertiesUtils.getStore().getString(entry));
//			element = propertyElement.addElement("comment");
//		}
//
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/xml;charset=utf8");
//		PrintWriter pw = response.getWriter();
//		pw.print(dom.asXML());
//
//	}
//
//	public boolean anonymousAccess() {
//		return true;
//	}
//
//	public boolean isDisabled() {
//		return disabled;
//	}
//
//	public void setDisabled(boolean disabled) {
//		this.disabled = disabled;
//	}
//
//	public void setUrl(String url) {
//		this.url = url;
//	}
//
//}
