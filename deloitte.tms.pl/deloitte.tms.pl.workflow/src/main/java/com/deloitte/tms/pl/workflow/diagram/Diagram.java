package com.deloitte.tms.pl.workflow.diagram;

/**
 * @author Jacky.gao
 * @since 2013年9月8日
 */
public abstract class Diagram implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private String label;
	private String fontColor="50, 50, 50";
	private int fontSize;
	private int borderWidth;
	private String borderColor;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getBorderWidth() {
		return borderWidth;
	}
	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
	}
	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
	public String getFontColor() {
		return fontColor;
	}
	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}
	public int getFontSize() {
		return fontSize;
	}
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
}
