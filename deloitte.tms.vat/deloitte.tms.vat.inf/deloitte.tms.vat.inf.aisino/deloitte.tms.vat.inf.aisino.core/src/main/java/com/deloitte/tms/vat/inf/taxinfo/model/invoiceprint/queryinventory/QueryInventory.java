package com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.queryinventory;

import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSerializeProperty;


public class QueryInventory {
	
	@AisinoSerializeProperty(serializeName="FPZL",length=2,des="发票种类",require=true)
	String fpzl;

	public String getFpzl() {
		return fpzl;
	}

	public void setFpzl(String fpzl) {
		this.fpzl = fpzl;
	}	
	
}
