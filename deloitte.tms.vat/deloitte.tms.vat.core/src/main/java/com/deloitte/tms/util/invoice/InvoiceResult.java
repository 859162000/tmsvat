package com.deloitte.tms.util.invoice;

public class InvoiceResult {

	private boolean  success;
	
	private String description;

	public InvoiceResult() {
		super();
	}

	public InvoiceResult(boolean success, String description) {
		super();
		this.success = success;
		this.description = description;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
