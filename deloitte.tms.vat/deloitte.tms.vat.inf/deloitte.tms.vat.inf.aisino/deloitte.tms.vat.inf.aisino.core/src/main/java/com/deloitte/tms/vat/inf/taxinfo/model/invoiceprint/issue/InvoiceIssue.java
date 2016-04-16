package com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue;

import java.util.ArrayList;
import java.util.List;

import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSHReturnSerializeProperty;
import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSHSerializeProperty;
import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSerializeProperty;

public class InvoiceIssue {
	
	String key;
	
	@AisinoSerializeProperty(require=true,serializeName="fp")
	@AisinoSHSerializeProperty(serializeName="invrecordhead")
	@AisinoSHReturnSerializeProperty(serializeName="sendbackinvrecordhead")
	InvoiceIssueHead head;
	
	@AisinoSerializeProperty(serializeName="group",itemSerializeName="fpmx")
	@AisinoSHSerializeProperty(itemSerializeName="invrecorditem",outSize=true)
	@AisinoSHReturnSerializeProperty(itemSerializeName="sendbackinvrecorditem")
	List<InvoiceIssueDetail> details=new ArrayList<InvoiceIssueDetail>();
	
	public void addDetail(InvoiceIssueDetail detail){
		details.add(detail);
	}

	public InvoiceIssueHead getHead() {
		return head;
	}

	public void setHead(InvoiceIssueHead invoiceHead) {
		this.head = invoiceHead;
	}

	public List<InvoiceIssueDetail> getDetails() {
		return details;
	}

	public void setDetails(List<InvoiceIssueDetail> details) {
		this.details = details;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
