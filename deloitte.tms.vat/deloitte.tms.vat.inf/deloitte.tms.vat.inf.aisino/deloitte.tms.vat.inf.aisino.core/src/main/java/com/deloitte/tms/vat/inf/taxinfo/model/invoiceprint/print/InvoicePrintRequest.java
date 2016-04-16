package com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print;

import java.util.ArrayList;
import java.util.List;

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSHSerializeProperty;
import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSerializeProperty;
import com.deloitte.tms.vat.inf.taxinfo.model.RequestBase;

public class InvoicePrintRequest extends RequestBase{
	
	@AisinoSerializeProperty(itemSerializeName="record",outSize=true)
	@AisinoSHSerializeProperty(serializeName="printinv",itemSerializeName="key",outSize=true)
	List<InvoicePrint> records=new ArrayList<InvoicePrint>();
	
	public void addInvoicePrint(InvoicePrint print){
		records.add(print);
	}

	public List<InvoicePrint> getRecords() {
		return records;
	}

	public void setRecords(List<InvoicePrint> records) {
		this.records = records;
	}

	@Override
	public void check() {
		super.check();
		AssertHelper.notEmpty_assert(getRecords(), "打印内容不能为空");
		for(InvoicePrint print:getRecords()){
			AssertHelper.notEmpty_assert(print.getInvoiceCode(), "发票代码不能为空");
			AssertHelper.notEmpty_assert(print.getInvoiceNo(), "发票号码不能为空");
			AssertHelper.notEmpty_assert(print.getInvoiceType(), "发票类型不能为空");
		}
	}
	
}
