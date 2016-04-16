package com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print;

import java.util.ArrayList;
import java.util.List;

import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSerializeProperty;
import com.deloitte.tms.vat.inf.taxinfo.model.ResponseBase;

public class PrintInvoiceResponse extends ResponseBase{
	
	@AisinoSerializeProperty(serializeName="err",itemSerializeName="refp")
	List<InvoicePrintResult> results=new ArrayList<InvoicePrintResult>();
	
	public void addResult(InvoicePrintResult result){
		results.add(result);
	}

	public List<InvoicePrintResult> getResults() {
		return results;
	}

	public void setResult(List<InvoicePrintResult> result) {
		this.results = result;
	}
	
}
