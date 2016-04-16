/**  
 * BaseInputTag
 * 功能详细描述
 * @author weijia
 * @create 2016年3月20日 下午8:59:07 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
package com.deloitte.tms.pl.core.taglib;

import java.util.Random;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class BaseInputTag extends BodyTagSupport {

	private String dataSource;
	private String name;
	private String rewriteable;
	private String value;
	private String cssStyle;
	private int inputId = 0;
	private String id;
	
	private static Random rnd=new Random(System.currentTimeMillis());

	/**
	 * 
	 */
	public BaseInputTag() {
		super();
		inputId = rnd.nextInt(1000000)+1000000;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {

		return super.doEndTag();
	}

	/**
	 * @return
	 */
	protected StringBuffer addInput(StringBuffer input) throws JspException{
		input.append(" name='");
		input.append(name);
		input.append("' ");

		input.append(" id='");
		if(id==null||id.trim().length()<=0){
			input.append(inputId);
		}
		else{
			input.append(id);
		}
		input.append("' ");

		if (cssStyle != null && cssStyle.trim().length() > 0) {
			input.append(" class='");
			input.append(cssStyle);
			input.append("' ");
		}
		return input;
	}

	protected void println(String str) {
		try {
			pageContext.getOut().println(str);
		} catch (Exception e) {

		}

	}

	protected void setDataSourceName() {
		if (dataSource != null && dataSource.trim().length() > 0) {
			name = dataSource + "." + name;
		}
		if (value == null) {
			value = "";
		}
	}
	public String getElementId() {
		if(id!=null){
			return id.trim();
		}
		return inputId+"";
	}

	/**
	 * @return
	 */
	public String getCssStyle() {
		return cssStyle;
	}

	/**
	 * @return
	 */
	public String getDataSource() {
		return dataSource;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public String getRewriteable() {
		return rewriteable;
	}

	/**
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param string
	 */
	public void setCssStyle(String string) {
		cssStyle = string;
	}

	/**
	 * @param string
	 */
	public void setDataSource(String string) {
		dataSource = string;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		name = string;
	}

	/**
	 * @param string
	 */
	public void setRewriteable(String string) {
		rewriteable = string;
	}

	/**
	 * @param string
	 */
	public void setValue(String string) {
		value = string;
	}

	/**
	 * @return
	 */


	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param string
	 */
	public void setId(String string) {
		id = string;
	}

}
