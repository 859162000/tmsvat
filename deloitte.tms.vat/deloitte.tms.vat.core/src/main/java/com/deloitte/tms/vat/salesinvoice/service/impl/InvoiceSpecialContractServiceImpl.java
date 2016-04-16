package com.deloitte.tms.vat.salesinvoice.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.tms.base.cache.model.BizOrgNode;
import com.deloitte.tms.base.cache.utils.OrgCacheUtils;
import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.base.masterdata.model.CustomerInParam;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.service.CustomerService;
import com.deloitte.tms.base.taxsetting.model.TmsMdContract;
import com.deloitte.tms.base.taxsetting.model.TmsMdContractInParam;
import com.deloitte.tms.base.taxsetting.model.TmsMdInventoryCategories;
import com.deloitte.tms.base.taxsetting.model.TmsMdInventoryCategoriesInParam;
import com.deloitte.tms.base.taxsetting.model.TmsMdInventoryItems;
import com.deloitte.tms.base.taxsetting.model.TmsMdInventoryItemsInParam;
import com.deloitte.tms.base.taxsetting.model.TmsMdProject;
import com.deloitte.tms.pl.cache.utils.DictionaryCacheUtils;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.vat.salesinvoice.dao.InvoiceSpecialContractDao;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqH;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqHInParam;
import com.deloitte.tms.vat.salesinvoice.model.InvoiceReqL;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceSpecialContractService;

@Service
public class InvoiceSpecialContractServiceImpl extends BaseService implements
		InvoiceSpecialContractService {
	
	@Resource
	CustomerService customerService;
	@Resource
	InvoiceSpecialContractDao invoiceSpecialContractDaoImpl;
	
	@Override
	public IDao getDao() {
		return  invoiceSpecialContractDaoImpl;
	}
	/**
	 * 申请单数据查询
	 */
	
	@Override
	public DaoPage findInvoiceReqAll(Map<String, Object> map, Integer pageIndex,
			Integer pageSize) {
		if(map==null)
		{
			map=new HashMap();
		}
		DaoPage daoPage=this.findInvoiceReqHByParams(map, pageIndex, pageSize);
		return daoPage;
	}
	
    /**
     * 申请单数据查询
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
	@Override
	public DaoPage findInvoiceReqHByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}
		if(AssertHelper.isOrNotEmpty_assert(params.get("customerNumber"))){
			Map<String,Object>map=new HashMap<String, Object>();
			map.put("customerNumber", params.get("customerNumber"));
			List<CustomerInParam>list=customerService.findCustomerByParams(map);
			String ids="";
			if(list.size()>0){
				ids=list.get(0).getId();
			}
			params.put("customerId", ids);
		}
		DaoPage daoPage= invoiceSpecialContractDaoImpl.findInvoiceReqHByParams(params, pageIndex, pageSize);
		daoPage.setResult(convertInvoiceReqHToInParam((List<InvoiceReqH>) daoPage.getResult()));
		return daoPage;
	}
	
	/**
	 * 申请单数据查询
	 * @param models
	 * @return
	 */
	private List<InvoiceReqHInParam> convertInvoiceReqHToInParam(List<InvoiceReqH> models){
		List<InvoiceReqHInParam> result=new ArrayList<InvoiceReqHInParam>();
		for(InvoiceReqH initiation:models){
			String codeValue = initiation.getInvoiceReqType();//申请开票类型(柜台/特殊/自动)
			if("2".equals(codeValue)||"4".equals(codeValue)){
				InvoiceReqHInParam inparam=convertInvoiceReqHToInParam(initiation);
				result.add(inparam);
			}
		}
		return result;
	}
	
	/**
	 * 申请单数据查询结果往前台model赋值
	 * @param model
	 * @return
	 */
	public InvoiceReqHInParam convertInvoiceReqHToInParam(InvoiceReqH model){
		InvoiceReqHInParam inparam=new InvoiceReqHInParam();
		ReflectUtils.copyProperties(model, inparam);
		String codeValue = model.getCustRegistrationCode();//购方纳税识别号类型
		if(AssertHelper.isOrNotEmpty_assert(codeValue)){//
			String valueName = DictionaryCacheUtils.getCodeName("VAT_CUSTOMER_DISC_OPTION", codeValue); 
			inparam.setCustRegistrationCode(valueName);
		}if(AssertHelper.isOrNotEmpty_assert(model.getCustomer())){//客户关系
			inparam.setCustomerNumber(model.getCustomer().getCustomerNumber());//购方编码
			inparam.setCustomerName(model.getCustomer().getCustomerName());//购方名称
			inparam.setCustRegistrationAddress(model.getCustomer().getCustRegistrationAddress());//购方纳税人地址
			inparam.setCustDepositBankName(model.getCustomer().getCustDepositBankName());//购方开户银行名称
			inparam.setCustDepositBankAccountNum(model.getCustomer().getCustDepositBankAccountNum());//购方开户账号
			inparam.setContactName(model.getCustomer().getContactName());////购方纳税联系人
			
		}if(AssertHelper.isOrNotEmpty_assert(model.getStatus())){//状态
			inparam.setStatus(DictionaryCacheUtils.getCodeName("VAT_CR_INVOICE_APPFORM_STATUS",model.getStatus()));
			inparam.setPageStatus(model.getStatus());//页面展现的状态值
			inparam.setReqStatus(model.getStatus());//申请单状态
		}if(AssertHelper.isOrNotEmpty_assert(model.getOrgId())){//组织id
			BizOrgNode node=OrgCacheUtils.getNodeByOrgId(model.getOrgId());//查询组织信息
			if(node!=null){
				inparam.setOrgName(node.getName());//组织名称
				inparam.setOrgCode(node.getCode());	//组织code	
			}
			
		}
		if(AssertHelper.isOrNotEmpty_assert(model.getInvoiceReqType())){//申请开票类型(柜台/特殊/自动)
			String valueName = DictionaryCacheUtils.getCodeName("VAT_CR_INVOICE_REQ_TYPE", model.getInvoiceReqType()); 
			inparam.setInvoiceReqType(valueName);
		}
		if(AssertHelper.isOrNotEmpty_assert(model.getLegalEntityId())){
			TmsMdLegalEntity entity=(TmsMdLegalEntity) this.get(TmsMdLegalEntity.class, model.getLegalEntityId());
			inparam.setLegalEntityCode(entity.getLegalEntityCode());
			inparam.setLegalEntityName(entity.getLegalEntityName());
		}
		if(AssertHelper.isOrNotEmpty_assert(model.getInvoiceReqLs())){
			List<InvoiceReqL>list=(List<InvoiceReqL>) model.getInvoiceReqLs();
			BigDecimal amount=BigDecimal.valueOf(0.00);
			for (int i = 0; i < list.size(); i++) {
				amount=amount.add(list.get(i).getInvoiceAmount());
			}
			inparam.setReqAmount(amount.toString());
			inparam.setAcctdAmountCr("0");
		}
		return inparam;
	}
    /**
     * 根据填写购方编码，购方证件号码查询
     * @param map
     * @return
     */
	@Override
	public Customer getCustomerParam(Map<String, Object> map) {
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		query.append(" from Customer where 1=1 ");
		Object custRegistrationNumber=map.get("custRegistrationNumber");
		Object customerNumber=map.get("customerNumber");
		String custRegistrationCode=map.get("custRegistrationCode").toString();
		//String code = dictionaryService.
		if(AssertHelper.isOrNotEmpty_assert(custRegistrationCode)){
			query.append(" and custRegistrationCode=:custRegistrationCode");
			values.put("custRegistrationCode", custRegistrationCode);
		}
		if(AssertHelper.isOrNotEmpty_assert(customerNumber)){
			query.append(" and customerNumber=:customerNumber");
			values.put("customerNumber", customerNumber);
		}if(AssertHelper.isOrNotEmpty_assert(custRegistrationNumber)){
			query.append(" and custRegistrationNumber=:custRegistrationNumber");
			values.put("custRegistrationNumber", custRegistrationNumber);
		}
		List<Customer>list= customerService.findBy(query, values);
		if(values.size()>0){
			if(list.size()>0){
				return list.get(0);
			}else{
				return new Customer();
			}
		}
		else{
			return new Customer();
		}
	}
    
	/**
	 * 合同信息查询
	 */
	public DaoPage findTmsMdContractByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if (params == null) {
			params = new HashMap();
		}
		DaoPage daoPage= invoiceSpecialContractDaoImpl.findTmsMdContractByParams(params, pageIndex, pageSize);
		daoPage.setResult(convertTmsMdContractByParams((List<TmsMdContract>) daoPage.getResult()));
		return daoPage;
	}
	
	/** 合同信息中项目信息级联查询
	 * @param models
	 * @return
	 */
	private List<TmsMdContractInParam> convertTmsMdContractByParams(List<TmsMdContract> models){
		List<TmsMdContractInParam> result=new ArrayList<TmsMdContractInParam>();
		for(TmsMdContract initiation:models){//取得查询对象
			TmsMdContractInParam inparam=convertTmsMdContractByParams(initiation);//查询项目信息
			
			List<TmsMdProject> listTmsMdProject = inparam.getList();//得到项目查询结果集合
			
			if(listTmsMdProject.size()!=0&&listTmsMdProject!=null){//对项目数据查询结果进行处理
				for(TmsMdProject tmsmdproject:listTmsMdProject){
					
					TmsMdContractInParam inparamnew=new TmsMdContractInParam();//项目及合同关联前台类
					ReflectUtils.copyProperties(inparam, inparamnew);
					inparamnew.setList(null);
					inparamnew.setProjectNumber(tmsmdproject.getProjectNumber());//项目编号
					inparamnew.setProjectName(tmsmdproject.getProjectName());//项目名称
					inparamnew.setProjectAmount(tmsmdproject.getProjectAmount());//项目金额
					result.add(inparamnew);
				}
				
			}else{
				result.add(inparam);
			}
		
		}
		return result;
	}
	
	/**
	 * 合同信息中项目信息级联查询
	 * @param model
	 * @return
	 */
	public TmsMdContractInParam convertTmsMdContractByParams(TmsMdContract model){

		TmsMdContractInParam inparam=new TmsMdContractInParam();//项目及合同关联前台类
		ReflectUtils.copyProperties(model, inparam);
		String contractId = model.getId();//合同id
		if(AssertHelper.isOrNotEmpty_assert(contractId)){//判断合同id
			List<TmsMdProject> list = invoiceSpecialContractDaoImpl.findTmsMdProjectByParams(contractId);
			
		      inparam.setList(list);
		}
		return inparam;
	}
	
	

	/**
	 * 商品及服务编码查询
	 */
	@Override
	public DaoPage findTmsMdInventoryItemsByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}			
		DaoPage daoPage= invoiceSpecialContractDaoImpl.findTmsMdInventoryItemsByParams(params, pageIndex, pageSize);
		daoPage.setResult(convertTmsMdInventoryItemsToInParam((List<TmsMdInventoryItems>) daoPage.getResult()));
		return daoPage;
	}
	/**
	 * 商品及服务编码查询
	 */
	private List<TmsMdInventoryItemsInParam> convertTmsMdInventoryItemsToInParam(List<TmsMdInventoryItems> models){
		List<TmsMdInventoryItemsInParam> result=new ArrayList<TmsMdInventoryItemsInParam>();
		for(TmsMdInventoryItems initiation:models){
			TmsMdInventoryItemsInParam inparam=convertTmsMdInventoryItemsToInParam(initiation);
			result.add(inparam);
		}
		return result;
	}
	/**
	 * 商品及服务编码查询
	 */
	public TmsMdInventoryItemsInParam convertTmsMdInventoryItemsToInParam(TmsMdInventoryItems model){
		TmsMdInventoryItemsInParam inparam=new TmsMdInventoryItemsInParam();
		ReflectUtils.copyProperties(model, inparam);
		return inparam;
	}
}
