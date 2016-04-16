package com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print;

import java.util.ArrayList;
import java.util.List;

import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSerializeProperty;
import com.deloitte.tms.vat.inf.taxinfo.model.ResponseBase;

public class InvoicePrintResponse extends ResponseBase{
	
	@AisinoSerializeProperty(serializeName="err",itemSerializeName="refp")
	List<InvoicePrintResult> err=new ArrayList<InvoicePrintResult>();
	
}
