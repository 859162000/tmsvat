package com.deloitte.tms.vat.inf.taxinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.vat.base.enums.InvoicePrintStatusEnums;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssue;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueResponse;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.issue.InvoiceIssueResult;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.InvoicePrintRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.InvoicePrintResult;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceprint.print.PrintInvoiceResponse;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceserver.distributeInvoice.DistributeInvoiceRequest;
import com.deloitte.tms.vat.inf.taxinfo.model.invoiceserver.distributeInvoice.DistributeInvoiceResponse;
import com.deloitte.tms.vat.inf.taxinfo.service.ProcessTaxInterface;
import com.deloitte.tms.vat.salesinvoice.model.InvoicePrintPoolH;
import com.deloitte.tms.vat.salesinvoice.model.InvoicePrintPoolHInParam;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceAllocService;
import com.deloitte.tms.vat.salesinvoice.service.InvoicePrintPoolHService;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceSyncProvider;

@Component(value=ProcessTaxInterface.BEAN_ID)
public class ProcessTaxInterfaceImpl implements ProcessTaxInterface{
	
	@Resource 
	InvoiceSyncProvider invoiceSyncProvider;
	
	@Resource
	InvoicePrintPoolHService invoicePrintPoolHService;

	@Resource
	InvoiceAllocService invoiceAllocService;
	
	@Override
	public void processIssueInvoiceResponse(InvoiceIssueRequest request,InvoiceIssueResponse response) {
		AssertHelper.notEmpty_assert(response, "返回数据不能为空");
		
		List<InvoiceIssue> invoiceIssueList=request.getRecord();
		List<InvoiceIssueResult> invoiceIssueResultList=response.getResults();
		for(int i=0;i<invoiceIssueList.size();i++){
			InvoiceIssue invoiceIssue=invoiceIssueList.get(i);
			InvoiceIssueResult invoiceIssueResult=invoiceIssueResultList.get(i);
			InvoicePrintPoolH entity=(InvoicePrintPoolH)invoicePrintPoolHService.get(InvoicePrintPoolH.class,invoiceIssue.getKey());
			if(invoiceIssueResult.isIssucess()){//开具成功
				entity.setInvoicePrintStatus(InvoicePrintStatusEnums.TOBEPRINT.getValue());
				entity.setInvoicePrintDate(invoicePrintPoolHService.getDatabaseServerDate());
				entity.setInvoicePrintBy(invoiceIssue.getHead().getDrawer());
				entity.setInvoiceCode(invoiceIssueResult.getInvoiceCode());
				entity.setInvoiceNumber(invoiceIssueResult.getInvoiceNo());
				invoicePrintPoolHService.update(entity);
				invoiceSyncProvider.changeInvoiceStatu(invoiceIssueResult.getInvoiceCode(), invoiceIssueResult.getInvoiceNo(),"3");
				/*try {
					invoiceSyncProvider.changeInvoiceStatu(invoiceIssueResult.getInvoiceCode(), invoiceIssueResult.getInvoiceNo());
				} catch (Exception e) {
					// TODO: handle exception
				}*/
			}else{//开具失败,异步处理,不抛出异常
				
			}
		}
		
		/*for(InvoiceIssueResult result:response.getResults()){
			if(result.isIssucess()){//开具成功
				
			}else{//开具失败,异步处理,不抛出异常
				
			}
		}*/
	}

	@Override
	public void processPrintInvoiceResponse(InvoicePrintRequest request,PrintInvoiceResponse response) {
		AssertHelper.notEmpty_assert(response, "返回数据不能为空");
		for(InvoicePrintResult result:response.getResults()){
			InvoicePrintPoolHInParam inParam=invoiceSyncProvider.getInvoicePrintPoolH(result.getInvoiceCode(), result.getInvoiceNo());
			InvoicePrintPoolH entity = (InvoicePrintPoolH)invoicePrintPoolHService.get(InvoicePrintPoolH.class, inParam.getId());
			if(result.isIssucess()){//打印成功
				entity.setInvoicePrintStatus(InvoicePrintStatusEnums.PRINTED.getValue());
				invoicePrintPoolHService.update(entity);
			}else{//打印失败,异步处理,不抛出异常
				entity.setInvoicePrintStatus(InvoicePrintStatusEnums.PRINTFAIL.getValue());
				invoicePrintPoolHService.update(entity);
			}
		}
	}

	@Override
	public void processDistributeInvoiceResponse(DistributeInvoiceRequest request,
			DistributeInvoiceResponse response) {
		AssertHelper.notEmpty_assert(response, "返回数据不能为空");
		AssertHelper.notEmpty_assert(response.getErrorinfo(), "返回数据不能为空");
		if(response.getErrorinfo().isIssucess()){//成功
			String id = request.getKey();
			invoiceAllocService.auditInvoiceAllocById(id);
		}else{//失败,不抛异常
		}
	}

}
