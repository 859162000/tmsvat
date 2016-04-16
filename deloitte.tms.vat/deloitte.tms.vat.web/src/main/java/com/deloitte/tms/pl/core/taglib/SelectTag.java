/**  
 * generate select tag
 * 功能详细描述
 * @author weijia
 * @create 2016年3月20日 下午8:59:07 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

package com.deloitte.tms.pl.core.taglib;

import javax.servlet.jsp.JspException;

public class SelectTag extends BaseInputTag {

	private String easyuiStyle;
	private String selectValue ;
	private String enabled;
	private String easyuiClass;
	private String dataOptions;
	private String missingMessage;
	public int doStartTag() throws JspException {
		try {
			setDataSourceName();
			StringBuffer sb = new StringBuffer();
			sb.append("<SELECT ");
			if (easyuiClass!=null)
			{
				sb.append(" class='"+easyuiClass+"'");
			}
			
			addInput(sb);
			if(easyuiStyle!=null){
				sb.append(" style='"+easyuiStyle+"'");
			}
			if (enabled!=null)
			{
				if (enabled.equals("0"))
				{
					sb.append(" disabled");
				}
			}
			if (dataOptions!=null)
			{
				sb.append(" data-options='"+dataOptions+"'");
			}
			if (missingMessage!=null)
			{
				sb.append(" missingMessage="+missingMessage);
			}
			sb.append(">");
			println(sb.toString());
			String tmp = getValue();
			if (tmp != null) {
//				selectValue = ValueUtil.parseValue((HttpServletRequest)pageContext.getRequest(),getValue());
				String item = tmp.substring(1, tmp.length());
				selectValue = (String)pageContext.getRequest().getAttribute(item);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}
	
	public int doEndTag() throws JspException {
		println("</SELECT >");
		return super.doEndTag();
	}
	
	public boolean isSelect(String value){
		if(value!=null&&selectValue!=null&&value.equals(selectValue)){
			return true;
		}
		return false;
	
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getDataOptions() {
		return dataOptions;
	}

	public void setDataOptions(String dataOptions) {
		this.dataOptions = dataOptions;
	}

	public String getMissingMessage() {
		return missingMessage;
	}

	public void setMissingMessage(String missingMessage) {
		this.missingMessage = missingMessage;
	}

	public String getEasyuiStyle() {
		return easyuiStyle;
	}

	public void setEasyuiStyle(String easyuiStyle) {
		this.easyuiStyle = easyuiStyle;
	}

	public String getEasyuiClass() {
		return easyuiClass;
	}

	public void setEasyuiClass(String easyuiClass) {
		this.easyuiClass = easyuiClass;
	}

}
