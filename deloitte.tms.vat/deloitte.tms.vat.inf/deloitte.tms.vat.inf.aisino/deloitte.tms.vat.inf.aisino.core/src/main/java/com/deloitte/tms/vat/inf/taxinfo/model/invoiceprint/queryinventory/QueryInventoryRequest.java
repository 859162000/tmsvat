package com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.queryinventory;

import java.util.ArrayList;
import java.util.List;

import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSerializeProperty;
import com.deloitte.tms.vat.inf.taxinfo.model.RequestBase;

public class QueryInventoryRequest extends RequestBase{
	
	@AisinoSerializeProperty(itemSerializeName="record",outSize=true)
	List<QueryInventory> record=new ArrayList<QueryInventory>();
	
	public void addQueryInventory(QueryInventory print){
		record.add(print);
	}
}
