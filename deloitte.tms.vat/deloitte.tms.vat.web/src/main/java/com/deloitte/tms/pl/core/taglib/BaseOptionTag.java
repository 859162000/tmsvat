/**  
 * generate option tag
 * 功能详细描述
 * @author weijia
 * @create 2016年3月20日 下午8:59:07 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
package com.deloitte.tms.pl.core.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class BaseOptionTag extends BodyTagSupport {

	public int doEndTag() throws JspException {
		return super.doEndTag();
	}

	public int doStartTag() throws JspException {
		return super.doStartTag();
	}
	
	protected void outOption(String value,String text){
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<OPTION VALUE='");
			if(value!=null){
				sb.append(value);
			}
			sb.append("' ");
			SelectTag tag = (SelectTag) findAncestorWithClass(this, Class.forName(TagUtil.SELECT_TAG_CLASS));
			if(tag.isSelect(value)){
				sb.append("SELECTED");
			}
			sb.append(">");
			if(text!=null){
				sb.append(text);
			}
			sb.append("</OPTION>");
			pageContext.getOut().println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
	

}
