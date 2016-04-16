package com.deloitte.tms.vat.salesinvoice.service.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.constant.TableColnumDef;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.vat.salesinvoice.dao.InvoicePrintPoolHDao;
import com.deloitte.tms.vat.salesinvoice.dao.InvoicePrintPoolLDao;
import com.deloitte.tms.vat.salesinvoice.dao.InvoiceTrxDao;
import com.deloitte.tms.vat.salesinvoice.model.InvoicePrintPoolH;
import com.deloitte.tms.vat.salesinvoice.model.InvoicePrintPoolHInParam;
import com.deloitte.tms.vat.salesinvoice.model.InvoicePrintPoolL;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqL;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxD;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPool;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceSyncProvider;

@Component
public class InvoiceSyncProviderImpl implements InvoiceSyncProvider {

	@Resource
	InvoiceTrxDao invoiceTrxDao;
	@Resource
	InvoicePrintPoolHDao invoicePrintPoolHDao;
	@Resource 
	InvoicePrintPoolLDao invoicePrintPoolLDao;
	/**
	 * zhou liang 调用 jia wei 改变发票库存状态
	 * @param invoiceCode
	 * @param invoiceNo
	 * @return  
	 * @see com.deloitte.tms.vat.salesinvoice.service.InvoiceSyncProvider#changeInvoiceStatu(java.lang.String, java.lang.String)
	 */
	@Override
	public InvoiceTrxD changeInvoiceStatu(String invoiceCode, String invoiceNo, String status) {
		InvoiceTrxD invoiceTrxD=invoiceTrxDao.getInvoice(invoiceCode, invoiceNo);
		//移除异常抛出
		if(AssertHelper.notEmpty(invoiceTrxD)){
			invoiceTrxD.setIsUsage(TableColnumDef.ENABLE_EFFECT);
			invoiceTrxD.setStatus(status);
			invoiceTrxDao.update(invoiceTrxD);
		}
		/*if(invoiceTrxD==null){
			throw new BusinessException("相关发票库存信息没找到");
		}*/
		
		return invoiceTrxD;
	}
	/**
	 *  jia wei 调用 zhou liang 发票状态
	 * @param invoiceCode
	 * @param invoiceNo
	 * @return  
	 * @see com.deloitte.tms.vat.salesinvoice.service.InvoiceSyncProvider#exeAbolishInvoiceStatu(java.lang.String, java.lang.String)
	 */
	@Override
	public Boolean exeAbolishInvoiceStatu(String invoiceNumber, String invoiceCode) {
		InvoicePrintPoolH invoicePrintPoolH=invoicePrintPoolHDao.getInvoicePrintPool(invoiceNumber, invoiceCode);
		if(invoicePrintPoolH==null){
			throw new BusinessException("相关发票信息没找到");
		}
		//设置发票为作废
		invoicePrintPoolHDao.update(invoicePrintPoolH);
		return true;
	}
	@Override
	public InvoicePrintPoolHInParam getInvoicePrintPoolH(String invoiceCode,String invoiceNumber) {
		InvoicePrintPoolHInParam result=new InvoicePrintPoolHInParam();
		InvoicePrintPoolH invoicePrintPoolH=invoicePrintPoolHDao.getInvoicePrintPool(invoiceCode, invoiceNumber);
		ReflectUtils.copyProperties(invoicePrintPoolH, result);
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("invoicePrintPoolHId", invoicePrintPoolH.getId());
		
		List<InvoicePrintPoolL> tempList=invoicePrintPoolLDao.findInvoicePrintPoolLByParams(map);
		BigDecimal vatAmount=new BigDecimal(0);
		BigDecimal invoiceAmount=new BigDecimal(0);
		BigDecimal acctdAmountCR=new BigDecimal(0);
		
		for(InvoicePrintPoolL InvoicePrintPoolL:tempList){
			if(AssertHelper.notEmpty(InvoicePrintPoolL.getInvoiceAmount())){
				invoiceAmount=invoiceAmount.add(InvoicePrintPoolL.getInvoiceAmount());
			}
			if(AssertHelper.notEmpty(InvoicePrintPoolL.getVatAmount())){
				vatAmount=vatAmount.add(InvoicePrintPoolL.getVatAmount());
			}
			if(AssertHelper.notEmpty(InvoicePrintPoolL.getAcctdAmountCR())){
				acctdAmountCR=acctdAmountCR.add(InvoicePrintPoolL.getAcctdAmountCR());
			}
		}
		result.setVatAmount(vatAmount);
		result.setInvoiceAmount(invoiceAmount);
		result.setAcctdAmountCR(acctdAmountCR);
		
		/*if(invoicePrintPoolH==null){
			throw new BusinessException("相关发票信息没找到");
		}*/
		return result;
	}
	/**
	 * 获取申请单行表可开票金额
	 * 申请单行id
	 * @return
	 */
	public BigDecimal getUserfulAmountByInvoiceReqL(String invoiceReqLId){
		AssertHelper.notEmpty_assert(invoiceReqLId, "申请单行id不能为空");
		InvoiceReqL invoiceReqL=(InvoiceReqL) invoiceTrxDao.get(InvoiceReqL.class, invoiceReqLId);
		AssertHelper.notEmpty_assert(invoiceReqL, "申请单行id数据没有找到:"+invoiceReqLId);
		String crvatTrxPoolId=invoiceReqL.getCrvatTrxPoolId();
		AssertHelper.notEmpty_assert(crvatTrxPoolId, "交易流水不能为空");
		return getUserfulAmountByTrxPoolId(crvatTrxPoolId);
		
	}
	/**
	 * 获取申请单行表可开票金额
	 * 交易流水id
	 * @return
	 */
	public BigDecimal getUserfulAmountByTrxPoolId(String crvatTrxPoolId){
		AssertHelper.notEmpty_assert(crvatTrxPoolId, "交易流水不能为空");
		InvoiceTrxPool crvatTrxPool=(InvoiceTrxPool) invoiceTrxDao.get(InvoiceTrxPool.class, crvatTrxPoolId);
		AssertHelper.notEmpty_assert(crvatTrxPool, "交易流水id错误,没有找到相关数据:"+crvatTrxPoolId);
		/*AssertHelper.notEmpty_assert(crvatTrxPool, "开票金额不能为空");
		AssertHelper.notEmpty_assert(crvatTrxPool, "开票币种不能为空");
		return new BigDecimal(0);*/
		/*****2016-04-02 Guan, Gavin Shi Yong 告诉我,直接取人民币交易金额EXCHANGE_AMOUNT就好了****************/
		if(crvatTrxPool.getExchangeAmount()==null){
			//throw new BusinessException("人民币交易金额不能为空");
			return BigDecimal.valueOf(0.00);
		}
		return crvatTrxPool.getExchangeAmount();
		/************************************************************************/		
	}
	/**
	 * 获取申请单行表已开票金额
	 * invoiceReqLId 行id
	 * @return
	 */
	public BigDecimal getUsedAmount(String invoiceReqLId){
		return new BigDecimal(0);		
	}
	/**
	 * 获取发票列表
	 */
	@Override
	public List<InvoiceTrxD> getInvoiceTrxDList(String invoiceCode,
			String invoiceNumber) {
		Map<String, String> map=new HashMap<String, String>();
		List<InvoiceTrxD> return_list=invoiceTrxDao.findInvoiceTrxDByParams(map);
		
		return return_list;
	}
}
