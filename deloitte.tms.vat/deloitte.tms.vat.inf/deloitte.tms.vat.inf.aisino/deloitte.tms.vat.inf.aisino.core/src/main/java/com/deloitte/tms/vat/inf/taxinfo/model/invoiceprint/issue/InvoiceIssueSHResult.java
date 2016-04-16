package com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue;

import java.util.ArrayList;
import java.util.List;

import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSHReturnSerializeProperty;

//@AisinoSerializeProperty(serializeName="refp")

public class InvoiceIssueSHResult {
	
	@AisinoSHReturnSerializeProperty(serializeName="sendbackinv",itemSerializeName="sendbackinvrecord")
	List<InvoiceIssue> record=new ArrayList<InvoiceIssue>();
	
	public void addInvoicePrintHead(InvoiceIssue head){
		record.add(head);
	}

	public List<InvoiceIssue> getRecord() {
		return record;
	}

	public void setRecord(List<InvoiceIssue> record) {
		this.record = record;
	}
}
