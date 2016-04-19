package com.deloitte.tms.vat.salesinvoice.jobs.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.data.annotation.Reference;
import org.springframework.stereotype.Service;

import com.deloitte.tms.base.masterdata.model.BaseOrg;
import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.base.masterdata.model.TmsMdOrgLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdTaxTrxType;
import com.deloitte.tms.base.taxsetting.model.TmsMdContract;
import com.deloitte.tms.base.taxsetting.model.TmsMdInventoryItems;
import com.deloitte.tms.base.taxsetting.model.TmsMdProject;
import com.deloitte.tms.base.taxsetting.model.TmsMdTrxAffirmSetting;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.vat.core.common.IdGenerator;
import com.deloitte.tms.vat.salesinvoice.jobs.dao.TmsCrvatInvReqBatchesJobDao;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsCrvatInvReqBatInf;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsCrvatInvReqHImpl;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsCrvatInvReqHInf;
import com.deloitte.tms.vat.salesinvoice.jobs.service.TmsCrvatInvReqBatchesJobService;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceTrxPool;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvReqBatchesH;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvReqBatchesL;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvReqBatchesLInParam;
import com.deloitte.tms.vat.trnsctrecog.model.TmsCrvatSsTrxAll;

@Service
public class TmsCrvatInvReqBatchesJobServiceImpl extends BaseService implements
		TmsCrvatInvReqBatchesJobService {
	@Resource
	TmsCrvatInvReqBatchesJobDao tmsCrvatInvReqBatchesJobDaoImpl;

	@Override
	public IDao getDao() {
		return tmsCrvatInvReqBatchesJobDaoImpl;
	}

	/**
	 * 取得长江证券数据
	 */
	@Override
	public Map<String,List<TmsCrvatInvReqBatchesLInParam>> analyzeTmsCrvatInvReqBatchesParam() {
		
		List<String>  listTmsCrvatInvReqBatInf = 	tmsCrvatInvReqBatchesJobDaoImpl.getCrvatInvoiceReqNumber();
		Map<String,List<TmsCrvatInvReqBatchesLInParam>> map = new HashMap<String,List<TmsCrvatInvReqBatchesLInParam>>();
		for(String attribute1:listTmsCrvatInvReqBatInf){
			if(AssertHelper.notEmpty(attribute1)){
			List<TmsCrvatInvReqBatInf> list = tmsCrvatInvReqBatchesJobDaoImpl.analyzeTmsCrvatInvReqBatchesParam(attribute1);
			
			List<TmsCrvatInvReqBatchesLInParam> listTmsCrvatInvReqBatchesLInParam  = findTmsCrvatInvReqBatchesHInParam(list);
			 map.put(attribute1, listTmsCrvatInvReqBatchesLInParam);
			}
		}
		return map;
		
	}

	/**
	 * 对解析数据进行处理
	 */
	public List<TmsCrvatInvReqBatchesLInParam> findTmsCrvatInvReqBatchesHInParam(
			List<TmsCrvatInvReqBatInf> list) {
		List<TmsCrvatInvReqBatchesLInParam> listTmsCrvatInvReqBatchesLInParam = new ArrayList<TmsCrvatInvReqBatchesLInParam>();// 保存查找数据集合
		
		BigDecimal invoiceAmounts = new BigDecimal(0);
		int i = 0;
		for(TmsCrvatInvReqBatInf tmsCrvatInvReqBatInf:list){//循环明细
		TmsCrvatInvReqBatchesLInParam tmsCrvatInvReqBatchesLInParam = new TmsCrvatInvReqBatchesLInParam();//明细数据封装对象
		StringBuffer stb = new StringBuffer();//保存错误信息
		
		//-----------------客户信息------------------//SOURCE_CUSTOMER_NUMBER	来源客户编码
		String customerNumber = tmsCrvatInvReqBatInf.getSourceCustomerNumber();// 客户编号
		// 根据客户编号查询客户信息
		List<Customer> listCustomer = tmsCrvatInvReqBatchesJobDaoImpl
				.findCustomer(customerNumber, null);

		if (listCustomer == null || listCustomer.size() == 0  ) {
			stb.append("客户信息不存在-");
		} else {
			Customer customer = listCustomer.get(0);
			tmsCrvatInvReqBatchesLInParam.setCustomerId(customer.getId());// 购方id
		}
        
		//-----------------合同信息---------------------------//	SOURCE_CONTRACT_NUMBER	来源合同编码
		String contractNumber = tmsCrvatInvReqBatInf.getSourceContractNumber();// 合同编号

		if (AssertHelper.notEmpty(contractNumber)) {
			// 根据合同编号查询合同信息
			List<TmsMdContract> listTmsMdContract = tmsCrvatInvReqBatchesJobDaoImpl
					.findTmsMdContract(contractNumber, null);

			if (listTmsMdContract == null || listTmsMdContract.size() == 0  ) {
				stb.append("合同不存在-");
			} else {
				TmsMdContract tmsMdContract = listTmsMdContract.get(0);// 合同model对象
				tmsCrvatInvReqBatchesLInParam.setContractId(tmsMdContract
						.getId());
			}
		}
        
		//---------------项目信息-------------------//SOURCE_PROJECT_NUMBER	来源项目编码
		String projectNumber = tmsCrvatInvReqBatInf.getSourceProjectNumber();// 项目编号
		if (AssertHelper.notEmpty(projectNumber)) {
			// 根据项目编号查询项目信息
			List<TmsMdProject> listTmsMdProject = tmsCrvatInvReqBatchesJobDaoImpl
					.findTmsMdProject(projectNumber, null);
			if (listTmsMdProject == null || listTmsMdProject.size() == 0  ) {
				stb.append("项目不存在-");
			} else {

				TmsMdProject tmsMdProject = listTmsMdProject.get(0);// 项目model对象
				tmsCrvatInvReqBatchesLInParam
						.setProjectId(tmsMdProject.getId());// 项目编id
			}
		}
        
		//---------------涉税交易类型-------------------------//SOURCE_TAX_TYPE_CODE	收入类型代码//SOURCE_TAX_TYPE_NAME收入类型名称
		String taxTrxTypeName = tmsCrvatInvReqBatInf.getSourceTaxTypeCode();// 涉税交易类型
		// 根据收入类型名称查询涉税交易类型信息
		List<TmsMdTaxTrxType> listTmsMdTaxTrxType = tmsCrvatInvReqBatchesJobDaoImpl
				.findTmsMdTaxTrxType(taxTrxTypeName, null);
		if (listTmsMdTaxTrxType == null || listTmsMdTaxTrxType.size() == 0  ) {
			stb.append("涉税交易类型不存在-");
		} else {
			TmsMdTaxTrxType tmsMdTaxTrxType = listTmsMdTaxTrxType.get(0);
			tmsCrvatInvReqBatchesLInParam.setTaxTrxTypeId(tmsMdTaxTrxType
					.getId());// 涉税交易类型id
			
			//----------------组织信息----------------------
			String orgcode = tmsCrvatInvReqBatInf.getSourceOrgCode();//SOURCE_ORG_CODE	来源组织CODE
			List<BaseOrg> listBaseOrg = tmsCrvatInvReqBatchesJobDaoImpl
					.getBaseOrg(orgcode);
			if (listBaseOrg == null || listBaseOrg.size() == 0  ) {
				stb.append("组织不存在-");
			} else {
				BaseOrg baseOrg = listBaseOrg.get(0);
				tmsCrvatInvReqBatchesLInParam.setOrgId(baseOrg.getId());// 组织id
				TmsMdOrgLegalEntity legalEntityID = tmsCrvatInvReqBatchesJobDaoImpl.getRegistrationNumber(baseOrg.getId());
			          
				if(legalEntityID.getId()!=null&&!"".equals(legalEntityID.getId())){
					tmsCrvatInvReqBatchesLInParam.setLegalEntityId(legalEntityID.getLegalEntityId());// 涉税交易认定规则id
				}else{
					stb.append("查询不到纳税人信息-");
				}
				//---------------根据组织id以及涉税交易id查找涉税交易认定规则------------------------
				List<TmsMdTrxAffirmSetting> tmsMdTrxAffirmSetting = tmsCrvatInvReqBatchesJobDaoImpl
						.findTmsMdTrxAffirmSetting(tmsMdTaxTrxType.getId(),
								baseOrg.getId());
				if (tmsMdTrxAffirmSetting == null || tmsMdTrxAffirmSetting.size() == 0) {
					stb.append("涉税交易认定规则不存在-");
				} else {
					TmsMdTrxAffirmSetting tms = tmsMdTrxAffirmSetting.get(0);// 涉税交易认定规则
					tmsCrvatInvReqBatchesLInParam.setTrxAffirmSettingId(tms
							.getId());// 涉税交易认定规则id
					
					//判断涉税交易认定规则中税率是否存在
					if (tms.getTaxRate() != null|| !"".equals(tms.getTaxRate())) {
						tmsCrvatInvReqBatchesLInParam.setTaxRate(tms.getTaxRate());// 税率
					}
					//是否含税
					Boolean tt = tms.getIsTax();
					String tem = null;
					if (tt) {
						tem = "1";
					} else {
						tem = "0";
					}
					tmsCrvatInvReqBatchesLInParam.setIsTax(tem);// 是否含税(Y/N)
					// 得到商品及服务对象
					TmsMdInventoryItems tmsMdInventoryItems = tms
							.getTmsMdInventoryItems();
					//涉税交易认定规则中不存在税率从商品及服务编码中得到
					if (tms.getTaxRate() == null || "".equals(tms.getTaxRate())) {
						tmsCrvatInvReqBatchesLInParam.setTaxRate(tmsMdInventoryItems.getTaxRate());// 税率
					}
					tmsCrvatInvReqBatchesLInParam
							.setInventoryItemId(tmsMdInventoryItems.getId());// 商品及服务id
				}
			}
		}
		BigDecimal amout = null;
		if(tmsCrvatInvReqBatInf.getInvoiceAmount()==null||tmsCrvatInvReqBatInf.getInvoiceAmount()==0){
			stb.append("金额不合法-");
			amout = new BigDecimal(0);
		}else{
			amout = new BigDecimal(
					tmsCrvatInvReqBatInf.getInvoiceAmount());
		}
		invoiceAmounts = invoiceAmounts.add(amout);
		tmsCrvatInvReqBatchesLInParam.setInvoiceAmounts(invoiceAmounts);
		tmsCrvatInvReqBatchesLInParam.setInvoiceReqDate(tmsCrvatInvReqBatInf.getInvoiceReqDate());
		Long number = 1L;
		tmsCrvatInvReqBatchesLInParam.setInventoryItemQty(number);// 数量
		tmsCrvatInvReqBatchesLInParam.setInvoiceAmount(amout);// invoice_amount	本次开票总金额
		tmsCrvatInvReqBatchesLInParam.setApprovalDate(tmsCrvatInvReqBatInf.getApprovalDate());//approval_date	最后审批日期
		tmsCrvatInvReqBatchesLInParam.setIsReceipts(tmsCrvatInvReqBatInf.getIsReceipts());//is_receipts	是否已收款
		tmsCrvatInvReqBatchesLInParam.setCreatedBy(tmsCrvatInvReqBatInf.getSourceInvoiceReqBy());//SOURCE_INVOICE_REQ_BY	申请人
		tmsCrvatInvReqBatchesLInParam.setApprovalBy(tmsCrvatInvReqBatInf.getSourceApprovalBy());//source_approval_by	来源最后审批人
		String tips = stb.toString();
		
		if(tips.length()==0&&"".equals(tips)&&tips!=null){
			tmsCrvatInvReqBatchesLInParam.setAttribute4("true");
		}else{
			i++;
			tmsCrvatInvReqBatchesLInParam.setAttribute4("false");
		}
	
		tmsCrvatInvReqBatchesLInParam.setAttribute5(stb.toString());
		listTmsCrvatInvReqBatchesLInParam.add(tmsCrvatInvReqBatchesLInParam);
		listTmsCrvatInvReqBatchesLInParam.get(0).setTips(i);
		}
		
		return listTmsCrvatInvReqBatchesLInParam;
	}
   /**
    * 查询柜台开票接口数据
    */
	@Override
	public List<TmsCrvatInvReqHImpl> analyzeTmsCrvatInvReqHInf() {
			List<TmsCrvatInvReqHInf> list = tmsCrvatInvReqBatchesJobDaoImpl.analyzeTmsCrvatInvReqHInfParam();
			List<TmsCrvatInvReqHImpl> map =  findTmsCrvatInvReqBatInf(list);
			//List<TmsCrvatInvReqHImpl> TmsCrvatInvReqHImpl  = findTmsCrvatInvReqBatchesHInParam(list);
			// map.put(attribute1, TmsCrvatInvReqHImpl);
		return map;
	}
  /**
   * 解析柜台开票申请数据
   * @param list
   * @return
   */
	public List<TmsCrvatInvReqHImpl>   findTmsCrvatInvReqBatInf(List<TmsCrvatInvReqHInf> list) {
		List<TmsCrvatInvReqHImpl> map = new ArrayList<TmsCrvatInvReqHImpl>();
		TmsCrvatInvReqHImpl tmsCrvatInvReqHImpl = new TmsCrvatInvReqHImpl();
		for(TmsCrvatInvReqHInf tmsCrvatInvReqHInf:list){
			StringBuffer stb = new StringBuffer();//保存错误信息
			String customerAccNum = tmsCrvatInvReqHInf.getSourceCustomerAccNum();//客户资金账号
			String orgCode = tmsCrvatInvReqHInf.getSourceOrgCode();//来源组织code
			 Date startDate = tmsCrvatInvReqHInf.getStartDate();//交易起始日期
			 Date endTrxDate = tmsCrvatInvReqHInf.getEndTrxDate();//交易结束日期
 			
			
			String orgId = null;
			List<BaseOrg> listBaseOrg = tmsCrvatInvReqBatchesJobDaoImpl.getBaseOrg(orgCode);
			if (listBaseOrg.size() == 0 || listBaseOrg == null) {
				stb.append("组织不存在--");
			} else {
				BaseOrg baseOrg = listBaseOrg.get(0);
				orgId = baseOrg.getId();//组织id
			}
			 if(customerAccNum==null||"".equals(customerAccNum)){
				 stb.append("客户资金账户不存在--");
			 }else{
				 //查询客户信息
				 
				 
				 List<InvoiceTrxPool>  listInvoiceTrxPool = tmsCrvatInvReqBatchesJobDaoImpl.getInvoiceTrxPool(customerAccNum,orgId,startDate,endTrxDate);
				for(InvoiceTrxPool invoiceTrxPool : listInvoiceTrxPool){
					ReflectUtils.copyProperties(invoiceTrxPool, tmsCrvatInvReqHImpl);
					tmsCrvatInvReqHImpl.setCrvatTrxPoolId(invoiceTrxPool.getId());//销项税开票交易池ID
					tmsCrvatInvReqHImpl.setId("");
					map.add(tmsCrvatInvReqHImpl);
				}
				 
			 }
			 
			
			
			
		}
		return map;
		
	}
	
	
	
	
	
}
