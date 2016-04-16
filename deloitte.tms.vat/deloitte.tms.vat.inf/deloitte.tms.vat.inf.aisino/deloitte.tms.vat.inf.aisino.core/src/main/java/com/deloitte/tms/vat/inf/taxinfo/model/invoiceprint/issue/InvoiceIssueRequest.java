package com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue;

import java.util.ArrayList;
import java.util.List;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSHSerializeProperty;
import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSerializeProperty;
import com.deloitte.tms.vat.inf.taxinfo.model.RequestBase;

public class InvoiceIssueRequest extends RequestBase{
	
	@AisinoSerializeProperty(itemSerializeName="record",outSize=true)
	@AisinoSHSerializeProperty(serializeName="issueinv",itemSerializeName="invrecord",outSize=true)
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

	@Override
	public void check() {
		super.check();
		if(getRecord().size()==0){
			throw new BusinessException("请求数据不能为空");
		}
		for(InvoiceIssue issue:getRecord()){
			AssertHelper.notEmpty_assert(issue, "开具内容不能为空");
			AssertHelper.notEmpty_assert(issue.getHead(), "开具头信息不能为空");
			AssertHelper.notEmpty_assert(issue.getDetails(), "开具明细信息不能为空");
			for(InvoiceIssueDetail detail:issue.getDetails()){
				AssertHelper.notEmpty_assert(detail, "开具明细信息不能为空");
				AssertHelper.notEmpty_assert(detail.getInvoiceAmount(), "开票金额不能为空");
				AssertHelper.notEmpty_assert(detail.getTaxRate(), "税率不能为空");
				AssertHelper.notEmpty_assert(detail.getIsTax(), "含税标志不能为空");
				AssertHelper.notEmpty_assert(detail.getCommodityName(), "商品名称不能为空");
				AssertHelper.notEmpty_assert(detail.getTaxAmount(), "税额不能为空");
			}			
		}
	}	
	
}
