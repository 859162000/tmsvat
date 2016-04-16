package com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue;

import java.util.ArrayList;
import java.util.List;

import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSHSerializeProperty;
import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSerializeProperty;
import com.deloitte.tms.vat.inf.taxinfo.model.ResponseBase;

public class InvoiceIssueResponse extends ResponseBase{
	
	@AisinoSerializeProperty(serializeName="err",itemSerializeName="refp")
	@AisinoSHSerializeProperty(serializeName="sendbackinv",itemSerializeName="sendbackinvrecord")
	List<InvoiceIssueResult> results=new ArrayList<InvoiceIssueResult>();
	
	public void addResult(InvoiceIssueResult result){
		results.add(result);
	}

	public List<InvoiceIssueResult> getResults() {
		return results;
	}

	public void setResults(List<InvoiceIssueResult> results) {
		this.results = results;
	}

}
