package com.ling2.dataimport.widget;

import com.bstek.dorado.annotation.ClientObject;
import com.bstek.dorado.annotation.ClientProperty;
import com.bstek.dorado.annotation.IdeProperty;
import com.bstek.dorado.view.annotation.Widget;
import com.bstek.dorado.view.widget.action.Action;

@Widget(name = "ImportExcelAction", category = "BDF2", dependsPackage = "importexcel", autoGenerateId = false)
@ClientObject(prototype = "dorado.widget.ImportExcelAction", shortTypeName = "ImportExcelAction")
public class ImportExcelAction extends Action {

	private boolean async;
	private long timeout;
	private boolean batchable;

	private int startRow=0;
	private int endRow=0;
	
	private String excelModelId;

	private boolean showImportData;

	public ImportExcelAction() {
		this.setAsync(true);
		this.setBatchable(true);
		this.setShowImportData(true);

	}

	@ClientProperty(escapeValue = "true")
	public boolean isAsync() {
		return async;
	}

	public void setAsync(boolean async) {
		this.async = async;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	@ClientProperty(escapeValue = "true")
	public boolean isBatchable() {
		return batchable;
	}

	public void setBatchable(boolean batchable) {
		this.batchable = batchable;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	@ClientProperty(escapeValue = "true")
	public boolean isShowImportData() {
		return showImportData;
	}

	public void setShowImportData(boolean showImportData) {
		this.showImportData = showImportData;
	}
	
	@IdeProperty(highlight = 1)
	public String getExcelModelId() {
		return excelModelId;
	}

	public void setExcelModelId(String excelModelId) {
		this.excelModelId = excelModelId;
	}

}
