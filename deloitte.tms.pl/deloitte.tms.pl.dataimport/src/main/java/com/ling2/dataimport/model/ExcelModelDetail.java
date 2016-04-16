package com.ling2.dataimport.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ling2.core.commons.constant.TablePreDef;

@Entity
@Table(name = TablePreDef.BASEPRE+"EXCEL_MODEL_DETAIL")
public class ExcelModelDetail implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID_", length = 60)
	private String id;
	@Column(name = "NAME_", length = 60)
	private String name;
	@Column(name = "EXCEL_MODEL_ID_", length = 60)
	private String excelModelId;
	@Column(name = "EXCEL_COLUMN_")
	private int excelColumn;
	@Column(name = "TABLE_COLUMN_", length = 60)
	private String tableColumn;
	@Column(name = "INTERCEPTOR_", length = 120)
	private String interceptor;
	@Column(name = "ISPROCESS")
	private boolean process=true;
	@Transient
	private ExcelModel excelModel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExcelModelId() {
		return excelModelId;
	}

	public void setExcelModelId(String excelModelId) {
		this.excelModelId = excelModelId;
	}

	public int getExcelColumn() {
		return excelColumn;
	}

	public void setExcelColumn(int excelColumn) {
		this.excelColumn = excelColumn;
	}

	public String getTableColumn() {
		return tableColumn;
	}

	public void setTableColumn(String tableColumn) {
		this.tableColumn = tableColumn;
	}

	public String getInterceptor() {
		return interceptor;
	}

	public void setInterceptor(String interceptor) {
		this.interceptor = interceptor;
	}

	public ExcelModel getExcelModel() {
		return excelModel;
	}

	public void setExcelModel(ExcelModel excelModel) {
		this.excelModel = excelModel;
	}

	public boolean isProcess() {
		return process;
	}

	public void setProcess(boolean process) {
		this.process = process;
	}

}
