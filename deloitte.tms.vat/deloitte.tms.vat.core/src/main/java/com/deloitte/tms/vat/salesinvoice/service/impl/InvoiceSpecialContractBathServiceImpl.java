package com.deloitte.tms.vat.salesinvoice.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.deloitte.tms.base.masterdata.model.BaseOrg;
import com.deloitte.tms.base.masterdata.model.Customer;
import com.deloitte.tms.base.masterdata.model.TmsMdEquipment;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEquipment;
import com.deloitte.tms.base.masterdata.model.TmsMdOrgLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdTaxTrxType;
import com.deloitte.tms.base.taxsetting.model.TmsMdContract;
import com.deloitte.tms.base.taxsetting.model.TmsMdInventoryItems;
import com.deloitte.tms.base.taxsetting.model.TmsMdProject;
import com.deloitte.tms.base.taxsetting.model.TmsMdTrxAffirmSetting;
import com.deloitte.tms.pl.cache.utils.DictionaryCacheUtils;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.vat.salesinvoice.dao.InvoiceSpecialContractBathDao;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvReqBatchesH;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvReqBatchesL;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvReqBatchesLInParam;
import com.deloitte.tms.vat.salesinvoice.service.InvoiceSpecialContractBathService;
@Service
public class InvoiceSpecialContractBathServiceImpl extends BaseService implements
		InvoiceSpecialContractBathService {
	@Resource
	InvoiceSpecialContractBathDao invoiceSpecialContractBathDaoImpl;
	@Override
	public IDao getDao() {
		return invoiceSpecialContractBathDaoImpl;
	}
    /**
     * 对解析数据进行处理
     */
	public DaoPage findTmsCrvatInvReqBatchesHInParam(List<String[]> list) {
		List<TmsCrvatInvReqBatchesLInParam>  listTmsCrvatInvReqBatchesLInParam = new ArrayList<TmsCrvatInvReqBatchesLInParam>();
		BigDecimal invoiceAmounts = new BigDecimal(0);
		int i = 1;
		for(String[] dataExcel:list){
			i++;
			TmsCrvatInvReqBatchesLInParam tmsCrvatInvReqBatchesLInParam = new TmsCrvatInvReqBatchesLInParam();
			
			String customerNumber = dataExcel[0];//客户编号
			//根据客户编号查询客户信息
			List<Customer> listCustomer = invoiceSpecialContractBathDaoImpl.findCustomer(customerNumber,null);
		
			if(listCustomer.size()==0||listCustomer==null){
				 String erro = "请检查第"+i+"行,"+"第"+1+"列客户编号";//提示信息
				 List<String[]> msg = new ArrayList<String[]>();//数据集合
				 msg.add(new String[]{erro});//添加提示信息
				return new DaoPage(list.size(),-1,10,msg);
			}
			Customer customer = listCustomer.get(0);
			tmsCrvatInvReqBatchesLInParam.setCustomerId(customer.getId());//购方id
			tmsCrvatInvReqBatchesLInParam.setCustomerNumber(customer.getCustomerNumber());//购方编码
			tmsCrvatInvReqBatchesLInParam.setCustomerName(customer.getCustomerName());//购方名称
			tmsCrvatInvReqBatchesLInParam.setCustRegistrationNumber(customer.getCustRegistrationNumber());//购方纳税人识别号
			tmsCrvatInvReqBatchesLInParam.setCustLegalEntityType(DictionaryCacheUtils.getCodeName("BASE_LEGAL_ENTITY_TYPE",customer.getCustLegalEntityType()));//购方纳税人类型_枚举值
			tmsCrvatInvReqBatchesLInParam.setCustRegistrationAddress(customer.getCustRegistrationAddress());//购方纳税人地址
			tmsCrvatInvReqBatchesLInParam.setContactPhone(customer.getContactPhone());//购方纳税人联系电话 
			tmsCrvatInvReqBatchesLInParam.setCustDepositBankAccountNum(customer.getCustDepositBankAccountNum());//购方开户账号
			tmsCrvatInvReqBatchesLInParam.setCustDepositBankName(customer.getCustDepositBankName());//购方开户银行名称
			
			String contractNumber = dataExcel[1];//合同编号
			if(AssertHelper.notEmpty(contractNumber)){
			//根据合同编号查询合同信息
			List<TmsMdContract> listTmsMdContract = invoiceSpecialContractBathDaoImpl.findTmsMdContract(contractNumber,null);
			
			if(listTmsMdContract.size()==0||listTmsMdContract==null){
				 String erro = "请检查第"+i+"行,"+"第"+2+"列合同编号";//提示信息
				 List<String[]> msg = new ArrayList<String[]>();//数据集合
				 msg.add(new String[]{erro});//添加提示信息
				return new DaoPage(list.size(),-1,10,msg);
			}
			
			TmsMdContract tmsMdContract = listTmsMdContract.get(0);//合同model对象
			tmsCrvatInvReqBatchesLInParam.setContractId(tmsMdContract.getId());//合同id
			tmsCrvatInvReqBatchesLInParam.setContractNumber(tmsMdContract.getContractNumber());//合同编号
			tmsCrvatInvReqBatchesLInParam.setContractName(tmsMdContract.getContractName());//合同名称
			
			}
			
			String projectNumber = dataExcel[2];//项目编号
			if(AssertHelper.notEmpty(projectNumber)){
			//根据项目编号查询项目信息
			List<TmsMdProject> listTmsMdProject = invoiceSpecialContractBathDaoImpl.findTmsMdProject(projectNumber,null);
			if(listTmsMdProject.size()==0||listTmsMdProject==null){
				 String erro = "请检查第"+i+"行,"+"第"+3+"列项目编号";//提示信息
				 List<String[]> msg = new ArrayList<String[]>();//数据集合
				 msg.add(new String[]{erro});//添加提示信息
				return new DaoPage(list.size(),-1,10,msg);
			}
			
			if(listTmsMdProject.size()!=0){
			TmsMdProject tmsMdProject = listTmsMdProject.get(0);//项目model对象
			tmsCrvatInvReqBatchesLInParam.setProjectId(tmsMdProject.getId());//项目编id
			tmsCrvatInvReqBatchesLInParam.setProjectNumber(tmsMdProject.getProjectNumber());//项目编码
			tmsCrvatInvReqBatchesLInParam.setProjectName(tmsMdProject.getProjectName());//项目名称
			}
			
			}
			
			String taxTrxTypeName = dataExcel[3];//涉税交易类型
			
			//根据收入类型名称查询涉税交易类型信息
			List<TmsMdTaxTrxType> listTmsMdTaxTrxType = invoiceSpecialContractBathDaoImpl.findTmsMdTaxTrxType(taxTrxTypeName,null);
			
			if(listTmsMdTaxTrxType.size()==0||listTmsMdTaxTrxType==null){
				 String erro = "请检查第"+i+"行,"+"第"+4+"列涉税交易类型";//提示信息
				 List<String[]> msg = new ArrayList<String[]>();//数据集合
				 msg.add(new String[]{erro});//添加提示信息
				return new DaoPage(list.size(),-1,10,msg);
			}
			
			TmsMdTaxTrxType tmsMdTaxTrxType = listTmsMdTaxTrxType.get(0);
            
			tmsCrvatInvReqBatchesLInParam.setTaxTrxTypeId(tmsMdTaxTrxType.getId());//涉税交易类型id
			tmsCrvatInvReqBatchesLInParam.setTaxTrxTypeCode(tmsMdTaxTrxType.getTaxTrxTypeCode());//涉税交易类型编码
			tmsCrvatInvReqBatchesLInParam.setTaxTrxTypeName(tmsMdTaxTrxType.getTaxTrxTypeName());//涉税交易类型_枚举值
			
			//根据涉水交易类型id查找涉水交易认定规则信息
            String orgid = "2FFAF852FE4B557AE053877D10ACBC082FFA";
			List<TmsMdTrxAffirmSetting> tmsMdTrxAffirmSetting = invoiceSpecialContractBathDaoImpl.findTmsMdTrxAffirmSetting(tmsMdTaxTrxType.getId(),orgid);
			if(tmsMdTrxAffirmSetting.size()==0||tmsMdTrxAffirmSetting==null){
				 String erro = "请检查第"+i+"行,"+"第"+4+"列涉税交易类型";//提示信息
				 List<String[]> msg = new ArrayList<String[]>();//数据集合
				 msg.add(new String[]{erro});//添加提示信息
				return new DaoPage(list.size(),-1,10,msg);
			}
			TmsMdTrxAffirmSetting tms = tmsMdTrxAffirmSetting.get(0);//涉税交易认定规则
			
			tmsCrvatInvReqBatchesLInParam.setTrxAffirmSettingId(tms.getId());
			tmsCrvatInvReqBatchesLInParam.setGlobalOrLocalOgrType(tms.getGlobalOrLocalOgrType());//全局/组织类型_枚举值(全局/组织)
			Double doub = tms.getTaxRate();
			System.out.println(doub);
			if(tms.getTaxRate()!=null||!"".equals(tms.getTaxRate())){
			tmsCrvatInvReqBatchesLInParam.setTaxRate(tms.getTaxRate());//税率
			}
			Boolean tt = tms.getIsTax();
			String tem = null;
			if(tt){
				tem="是";
			}else{
				tem="否";
			}
			tmsCrvatInvReqBatchesLInParam.setIsTax(tem);//是否含税(Y/N)
			
			String invoiceCategories = DictionaryCacheUtils.getCodeName("VAT_CR_INVOICE_TYPE",tms.getInvoiceCategories());
			tmsCrvatInvReqBatchesLInParam.setInvoiceCategories(invoiceCategories);//开票规则类型_枚举值(专票/普票/不可开票)
			//得到商品及服务对象
			TmsMdInventoryItems  tmsMdInventoryItems = tms.getTmsMdInventoryItems();
		
			
			if(tms.getTaxRate()==null||"".equals(tms.getTaxRate())){
				tmsCrvatInvReqBatchesLInParam.setTaxRate(tmsMdInventoryItems.getTaxRate());//税率
				}
			
			tmsCrvatInvReqBatchesLInParam.setInventoryItemId(tmsMdInventoryItems.getId());//商品及服务id
			tmsCrvatInvReqBatchesLInParam.setInventoryItemNumber(tmsMdInventoryItems.getInventoryItemNumber());//商品及服务编码
			tmsCrvatInvReqBatchesLInParam.setInventoryItemDescripton(tmsMdInventoryItems.getInventoryItemDescripton());//商品及服务名称
			Long num = Long.parseLong(dataExcel[4]);
			BigDecimal amout = new BigDecimal(dataExcel[5]);
			invoiceAmounts = invoiceAmounts.add(amout);
			tmsCrvatInvReqBatchesLInParam.setInventoryItemQty(num);
			tmsCrvatInvReqBatchesLInParam.setInvoiceAmount(amout);
			tmsCrvatInvReqBatchesLInParam.setInvoiceAmounts(invoiceAmounts);
			listTmsCrvatInvReqBatchesLInParam.add(tmsCrvatInvReqBatchesLInParam);
		}
		DaoPage daoPage = new DaoPage(list.size(),1,10,listTmsCrvatInvReqBatchesLInParam);
		return daoPage;
	}
	/**
	 * 销方纳税信息
	 */
	@Override
	public TmsMdLegalEntity getRegistrationNumber(String validNo) {
		TmsMdOrgLegalEntity list = invoiceSpecialContractBathDaoImpl.getRegistrationNumber(validNo);
		String legalEntityId = list.getLegalEntityId();
		TmsMdLegalEntity tmsMdLegal= null;
		List<TmsMdLegalEntity> listTmsMdLegalEntity = invoiceSpecialContractBathDaoImpl.getListTmsMdLegalEntity(legalEntityId);
		for(TmsMdLegalEntity tmsMdLegalEntity:listTmsMdLegalEntity){
			String print = tmsMdLegalEntity.getIsEnablePrint();
			if("1".equals(print)){
				String id = tmsMdLegalEntity.getId();
				tmsMdLegal = invoiceSpecialContractBathDaoImpl.getListTmsMdLegalEntity(id).get(0);
				
			}else{
				tmsMdLegal = tmsMdLegalEntity;
			}
		}
		return tmsMdLegal;
	}
	
	/**
	 * 打印终端信息查询
	 */
	@Override
	public TmsMdEquipment getTmsMdEquipment(String legalEntityId) {
		TmsMdLegalEquipment tmsMdLegalEquipment = invoiceSpecialContractBathDaoImpl.getTmsMdEquipment(legalEntityId);
		String equipmentId = tmsMdLegalEquipment.getEquipmentId();
		TmsMdEquipment  tmsMdEquipment = invoiceSpecialContractBathDaoImpl.getTmsMEquipment(equipmentId);
		System.out.println(tmsMdEquipment);
		return tmsMdEquipment;
	}
	/**
	 * 查询组织信息
	 */
	@Override
	public BaseOrg getOrg(String orgId) {
		BaseOrg baseOrg = invoiceSpecialContractBathDaoImpl.getOrg(orgId);
		return baseOrg;
	}
	
	/**
	 * 特殊申请单批量数据查询
	 * @param map
	 * @param response
	 */
	@Override
	public DaoPage findInvoiceReqAll(Map<String, Object> map,
			Integer pageNumber, Integer pageSize) {
		DaoPage daoPage = invoiceSpecialContractBathDaoImpl.findInvoiceReqAll(map,pageNumber, pageSize);
		List<TmsCrvatInvReqBatchesH> list = (List<TmsCrvatInvReqBatchesH>) daoPage.getResult();
		for(TmsCrvatInvReqBatchesH tm:list){
			tm.setStatus(DictionaryCacheUtils.getCodeName("VAT_CR_INVOICE_APPFORM_STATUS",tm.getStatus()));
			if("true".equals(tm.getAttribute4())){
				tm.setAttribute4("成功");
			}else{
				tm.setAttribute4("异常");
			}
			if("0".equals(tm.getIsReceipts())){
				tm.setIsReceipts("否");
			}else{
				tm.setIsReceipts("是");
			}
		}
		
		
		return daoPage;
	}
	/**
	 * 编辑申请单信息查询
	 */
	@Override
	public DaoPage findTmsCrvatInvReqBatchesLInParam(Map<String, Object> map,int pageNumber, int pageSize) {
		//查询申请单下明细
		DaoPage daoPage = invoiceSpecialContractBathDaoImpl.findTmsCrvatInvReqBatchesL(map,pageNumber,pageSize);
		List<TmsCrvatInvReqBatchesL> listTmsCrvatInvReqBatchesL = (List<TmsCrvatInvReqBatchesL>) daoPage.getResult();
		
		List<TmsCrvatInvReqBatchesLInParam>  listTmsCrvatInvReqBatchesLInParam = new ArrayList<TmsCrvatInvReqBatchesLInParam>();
		BigDecimal invoiceAmounts = new BigDecimal(0);
		
		for(TmsCrvatInvReqBatchesL tmsCrvatInvReqBatchesL:listTmsCrvatInvReqBatchesL){
         TmsCrvatInvReqBatchesLInParam tmsCrvatInvReqBatchesLInParam = new TmsCrvatInvReqBatchesLInParam();
     	ReflectUtils.copyProperties(tmsCrvatInvReqBatchesL, tmsCrvatInvReqBatchesLInParam);
     	
     	if("true".equals(tmsCrvatInvReqBatchesLInParam.getAttribute4())){
     		tmsCrvatInvReqBatchesLInParam.setAttribute4("成功");
		}else{
			tmsCrvatInvReqBatchesLInParam.setAttribute4("异常");
		}
		if("0".equals(tmsCrvatInvReqBatchesLInParam.getIsReceipts())){
			tmsCrvatInvReqBatchesLInParam.setIsReceipts("否");
		}else{
			tmsCrvatInvReqBatchesLInParam.setIsReceipts("是");
		}
     	
			String customerId = tmsCrvatInvReqBatchesL.getCustomerId();//客户id
			//根据客户编号查询客户信息
			List<Customer> listCustomer = invoiceSpecialContractBathDaoImpl.findCustomer(null,customerId);
			if(listCustomer!=null&&listCustomer.size()!=0){
			Customer customer = listCustomer.get(0);
			tmsCrvatInvReqBatchesLInParam.setCustomerId(customer.getId());//购方id
			tmsCrvatInvReqBatchesLInParam.setCustomerNumber(customer.getCustomerNumber());//购方编码
			tmsCrvatInvReqBatchesLInParam.setCustomerName(customer.getCustomerName());//购方名称
			tmsCrvatInvReqBatchesLInParam.setCustRegistrationNumber(customer.getCustRegistrationNumber());//购方纳税人识别号
			tmsCrvatInvReqBatchesLInParam.setCustLegalEntityType(DictionaryCacheUtils.getCodeName("BASE_LEGAL_ENTITY_TYPE",customer.getCustLegalEntityType()));//购方纳税人类型_枚举值
			tmsCrvatInvReqBatchesLInParam.setCustRegistrationAddress(customer.getCustRegistrationAddress());//购方纳税人地址
			tmsCrvatInvReqBatchesLInParam.setContactPhone(customer.getContactPhone());//购方纳税人联系电话 
			tmsCrvatInvReqBatchesLInParam.setCustDepositBankAccountNum(customer.getCustDepositBankAccountNum());//购方开户账号
			tmsCrvatInvReqBatchesLInParam.setCustDepositBankName(customer.getCustDepositBankName());//购方开户银行名称
			}
			String contractId = tmsCrvatInvReqBatchesL.getContractId();//合同编号
			if(AssertHelper.notEmpty(contractId)){
			//根据合同编号查询合同信息
			List<TmsMdContract> listTmsMdContract = invoiceSpecialContractBathDaoImpl.findTmsMdContract(null,contractId);
			TmsMdContract tmsMdContract = listTmsMdContract.get(0);//合同model对象
			tmsCrvatInvReqBatchesLInParam.setContractId(tmsMdContract.getId());//合同id
			tmsCrvatInvReqBatchesLInParam.setContractNumber(tmsMdContract.getContractNumber());//合同编号
			tmsCrvatInvReqBatchesLInParam.setContractName(tmsMdContract.getContractName());//合同名称
			
			}
			
			String projectId = tmsCrvatInvReqBatchesL.getProjectId();//项目编号
			if(AssertHelper.notEmpty(projectId)){
			//根据项目编号查询项目信息
			List<TmsMdProject> listTmsMdProject = invoiceSpecialContractBathDaoImpl.findTmsMdProject(null,projectId);
			if(listTmsMdProject.size()!=0){
			TmsMdProject tmsMdProject = listTmsMdProject.get(0);//项目model对象
			tmsCrvatInvReqBatchesLInParam.setProjectId(tmsMdProject.getId());//项目编id
			tmsCrvatInvReqBatchesLInParam.setProjectNumber(tmsMdProject.getProjectNumber());//项目编码
			tmsCrvatInvReqBatchesLInParam.setProjectName(tmsMdProject.getProjectName());//项目名称
			}
			
			}
			
			String taxTrxTypeId = tmsCrvatInvReqBatchesL.getTaxTrxTypeId();//涉税交易类型
			
			//根据收入类型名称查询涉税交易类型信息
			List<TmsMdTaxTrxType> listTmsMdTaxTrxType = invoiceSpecialContractBathDaoImpl.findTmsMdTaxTrxType(null,taxTrxTypeId);
			if(listTmsMdTaxTrxType!=null&&listTmsMdTaxTrxType.size()!=0){
			TmsMdTaxTrxType tmsMdTaxTrxType = listTmsMdTaxTrxType.get(0);
            
			tmsCrvatInvReqBatchesLInParam.setTaxTrxTypeId(tmsMdTaxTrxType.getId());//涉税交易类型id
			tmsCrvatInvReqBatchesLInParam.setTaxTrxTypeCode(tmsMdTaxTrxType.getTaxTrxTypeCode());//涉税交易类型编码
			tmsCrvatInvReqBatchesLInParam.setTaxTrxTypeName(tmsMdTaxTrxType.getTaxTrxTypeName());//涉税交易类型_枚举值
			
			//根据涉水交易类型id查找涉水交易认定规则信息
			String trxAffirmSettingId = tmsCrvatInvReqBatchesL.getTrxAffirmSettingId();//涉税交易类型
			List<TmsMdTrxAffirmSetting> tmsMdTrxAffirmSetting = invoiceSpecialContractBathDaoImpl.findTmsMdTrxAffirmSetting(trxAffirmSettingId);
			TmsMdTrxAffirmSetting tms = tmsMdTrxAffirmSetting.get(0);//涉税交易认定规则
			
			tmsCrvatInvReqBatchesLInParam.setTrxAffirmSettingId(tms.getId());//涉税交易认定规则id
			tmsCrvatInvReqBatchesLInParam.setGlobalOrLocalOgrType(tms.getGlobalOrLocalOgrType());//全局/组织类型_枚举值(全局/组织)
			tmsCrvatInvReqBatchesLInParam.setTaxRate(tmsCrvatInvReqBatchesL.getTaxRate());//税率
			if("0".equals(tmsCrvatInvReqBatchesL.getIsTax())){
			tmsCrvatInvReqBatchesLInParam.setIsTax("否");//是否含税(Y/N)
			}else{
				tmsCrvatInvReqBatchesLInParam.setIsTax("是");//是否含税(Y/N)
			}
			String invoiceCategories = DictionaryCacheUtils.getCodeName("VAT_CR_INVOICE_TYPE",tms.getInvoiceCategories());
			tmsCrvatInvReqBatchesLInParam.setInvoiceCategories(invoiceCategories);//开票规则类型_枚举值(专票/普票/不可开票)
			//得到商品及服务对象
			TmsMdInventoryItems  tmsMdInventoryItems = tms.getTmsMdInventoryItems();
			tmsCrvatInvReqBatchesLInParam.setInventoryItemId(tmsMdInventoryItems.getId());//商品及服务id
			tmsCrvatInvReqBatchesLInParam.setInventoryItemNumber(tmsMdInventoryItems.getInventoryItemNumber());//商品及服务编码
			tmsCrvatInvReqBatchesLInParam.setInventoryItemDescripton(tmsMdInventoryItems.getInventoryItemDescripton());//商品及服务名称
			Long num = tmsCrvatInvReqBatchesL.getInventoryItemQty();
			BigDecimal amout = tmsCrvatInvReqBatchesL.getInvoiceAmount();
			invoiceAmounts = invoiceAmounts.add(amout);
			tmsCrvatInvReqBatchesLInParam.setInventoryItemQty(num);
			tmsCrvatInvReqBatchesLInParam.setInvoiceAmount(amout);
			tmsCrvatInvReqBatchesLInParam.setInvoiceAmounts(invoiceAmounts);
			
			}
			listTmsCrvatInvReqBatchesLInParam.add(tmsCrvatInvReqBatchesLInParam);
		}
		return new DaoPage(daoPage.getTotal(),1,10,listTmsCrvatInvReqBatchesLInParam);
	}
	/**
	 * 删除发票信息
	 */
	@Override
	public int removeInvoiceReqHs(String ids) {
		
		
		TmsCrvatInvReqBatchesL tmsCrvatInvReqBatchesL = new TmsCrvatInvReqBatchesL();
		TmsCrvatInvReqBatchesH tmsCrvatInvReqBatchesH = new TmsCrvatInvReqBatchesH();
		String [] idArray = ids.split(",");
		for(String id:idArray){
			List<TmsCrvatInvReqBatchesL> list = invoiceSpecialContractBathDaoImpl.findByTmsCrvatInvReqBatchesLId(id);	
			invoiceSpecialContractBathDaoImpl.removeAll(list);
		    tmsCrvatInvReqBatchesH.setId(id);
		    invoiceSpecialContractBathDaoImpl.removeById(TmsCrvatInvReqBatchesH.class, id);
		}
		return 0;
	}
	
	
	/**
	 * 提交申请单
	 */
	@Override
	public int submitFromPage(String ids) {
		String [] idArray = ids.split(",");
		int count = 0;
		for(String id:idArray){ 
			count = invoiceSpecialContractBathDaoImpl.submitFromPage(id);
			count++;
		}
		return count;
	}
     

	
	
	
	
	
	
}
