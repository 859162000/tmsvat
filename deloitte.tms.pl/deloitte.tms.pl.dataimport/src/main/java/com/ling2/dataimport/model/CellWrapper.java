package com.ling2.dataimport.model;

public class CellWrapper implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String name;

	/**
	 * 当前单元格在Excel中的列号
	 */
	private int column;

	/**
	 * 当前单元格具体值
	 */
	private Object value;
	/**
	 * 当前单元格对应的数据库表列表<br>
	 * 该值可选
	 */
	private String columnName;

	/**
	 * 是否通过验证
	 */
	private boolean valid;

	/**
	 * 是否是数据主键
	 */
	private boolean isPrimaryKey;

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public boolean getIsPrimaryKey() {
		return isPrimaryKey;
	}

	public void setIsPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
