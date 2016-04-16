package com.deloitte.tms.vat.salesinvoice.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

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
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.vat.salesinvoice.dao.InvoiceSpecialContractBathDao;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvReqBatchesL;
import com.deloitte.tms.vat.salesinvoice.model.TmsCrvatInvReqBatchesLInParam;
@Component
public class InvoiceSpecialContractBathDaoImpl  extends BaseDao<TmsCrvatInvReqBatchesLInParam> implements
		InvoiceSpecialContractBathDao {
	/**
	 * 根据客户编号查询客户信息
	 */
	@Override
	public List<Customer> findCustomer(String string,String string1) {
		StringBuffer query=new StringBuffer();
		Map<String,Object> values=new HashMap<String,Object>();
		List<Customer> list = null;
		if(AssertHelper.notEmpty(string))
		{
			query.append(" from Customer where 1=1 ");
			query.append(" and customerNumber=:customerNumber");
			values.put("customerNumber", string);
			list =  findBy(query, values);
		}
		if(AssertHelper.notEmpty(string1)){
			query.append(" from Customer where 1=1 ");
			query.append(" and id=:id");
			values.put("id", string1);
			list =  findBy(query, values);
		}
		
		return list;
	}
/**
 * 根据合同编号查询合同信息
 */
	@Override
	public List<TmsMdContract> findTmsMdContract(String string,String string1) {
		StringBuffer query=new StringBuffer();
		Map<String,Object> values=new HashMap<String,Object>();
		List<TmsMdContract> list = null;
		if(AssertHelper.notEmpty(string))
		{
			query.append(" from TmsMdContract where 1=1 ");
			query.append(" and contractNumber=:contractNumber");
			values.put("contractNumber", string);
			list =  findBy(query, values);
		}
		if(AssertHelper.notEmpty(string1))
		{
			query.append(" from TmsMdContract where 1=1 ");
			query.append(" and id=:id");
			values.put("id", string1);
			list =  findBy(query, values);
		}
		return list;
	}
	/**
	 * 根据项目编号查询项目信息
	 */
	@Override
	public List<TmsMdProject> findTmsMdProject(String string,String string1) {
		StringBuffer query=new StringBuffer();
		Map<String,Object> values=new HashMap<String,Object>();
		List<TmsMdProject> list = null;
		if(AssertHelper.notEmpty(string))
		{
			query.append(" from TmsMdProject where 1=1 ");
			query.append(" and projectNumber=:projectNumber");
			values.put("projectNumber", string);
			list =  findBy(query, values);
		}
		if(AssertHelper.notEmpty(string1))
		{
			query.append(" from TmsMdProject where 1=1 ");
			query.append(" and id=:id");
			values.put("id", string);
			list =  findBy(query, values);
		}
		
		return list;
	}
	/**
	 * 根据收入类型名称查询涉税交易类型信息
	 */
	@Override
	public List<TmsMdTaxTrxType> findTmsMdTaxTrxType(String string,String string1) {
		StringBuffer query=new StringBuffer();
		Map<String,Object> values=new HashMap<String,Object>();
		List<TmsMdTaxTrxType> list = null;
		if(AssertHelper.notEmpty(string))
		{
			query.append(" from TmsMdTaxTrxType where 1=1 ");
			query.append(" and taxTrxTypeCode=:taxTrxTypeCode");
			values.put("taxTrxTypeCode", string);
			list =  findBy(query, values);
		}
		if(AssertHelper.notEmpty(string1))
		{
			query.append(" from TmsMdTaxTrxType where 1=1 ");
			query.append(" and id=:id");
			values.put("id", string1);
			list =  findBy(query, values);
		}
		
		return list;
	}
	
	/**
	 * 根据涉水交易类型id查找涉水交易认定规则信息
	 */
	@Override
	public List<TmsMdTrxAffirmSetting> findTmsMdTrxAffirmSetting(String id,
			String orgid) {
		
		List<TmsMdTrxAffirmSetting> list = null;
		if(AssertHelper.notEmpty(id))
		{StringBuffer query=new StringBuffer();
		Map<String,Object> values=new HashMap<String,Object>();
			query.append(" from TmsMdTrxAffirmSetting where 1=1 ");
			query.append(" and taxTrxTypeId=:taxTrxTypeId");
			query.append(" and orgId=:orgId");
			values.put("taxTrxTypeId", id);
			values.put("orgId", orgid);
			list =  findBy(query, values);
		}
		if(list==null||list.size()==0){
			StringBuffer query=new StringBuffer();
			Map<String,Object> values=new HashMap<String,Object>();
			query.append(" from TmsMdTrxAffirmSetting where 1=1 ");
			query.append(" and taxTrxTypeId=:taxTrxTypeId");
			query.append(" and globalOrLocalOgrType=:globalOrLocalOgrType");
			values.put("taxTrxTypeId", id);
			values.put("globalOrLocalOgrType", "ALL_ORG");
			list =  findBy(query, values);
		}
		
		return list;
	}
	/**
	 * 纳税主体映射信息
	 */
	@Override
	public TmsMdOrgLegalEntity getRegistrationNumber(String orgId) {
		List<TmsMdOrgLegalEntity> list = null;
		if(AssertHelper.notEmpty(orgId))
		{StringBuffer query=new StringBuffer();
		Map<String,Object> values=new HashMap<String,Object>();
			query.append(" from TmsMdOrgLegalEntity where 1=1 ");
			query.append(" and orgId=:orgId");
			values.put("orgId", orgId);
			list =  findBy(query, values);
		}
		if(list==null||list.size()==0){
			return new TmsMdOrgLegalEntity();
		}
		return list.get(0);
	}
	/**
	 * 纳税主体信息
	 */
	@Override
	public List<TmsMdLegalEntity> getListTmsMdLegalEntity(String legalEntityId) {
		List<TmsMdLegalEntity> list = null;
		if(AssertHelper.notEmpty(legalEntityId))
		{StringBuffer query=new StringBuffer();
		Map<String,Object> values=new HashMap<String,Object>();
			query.append(" from TmsMdLegalEntity where 1=1 ");
			query.append(" and id=:legalEntityId");
			values.put("legalEntityId", legalEntityId);
			list =  findBy(query, values);
		}
		if(list==null||list.size()==0){
			return new ArrayList<TmsMdLegalEntity>();
		}
		return list;
	}
	
	/**
	 * 打印与纳税组织关系
	 */
	@Override
	public TmsMdLegalEquipment getTmsMdEquipment(String legalEntityId) {
		List<TmsMdLegalEquipment> list = null;
		if(AssertHelper.notEmpty(legalEntityId))
		{StringBuffer query=new StringBuffer();
		Map<String,Object> values=new HashMap<String,Object>();
			query.append(" from TmsMdLegalEquipment where 1=1 ");
			query.append(" and legalEntityId=:legalEntityId");
			values.put("legalEntityId", legalEntityId);
			list =  findBy(query, values);
		}
		if(list==null||list.size()==0){
			return new TmsMdLegalEquipment();
		}
		return list.get(0);
	}
	
	/**
	 * 打印终端信息
	 */
	@Override
	public TmsMdEquipment getTmsMEquipment(String equipmentId) {
		List<TmsMdEquipment> list = null;
		if(AssertHelper.notEmpty(equipmentId))
		{StringBuffer query=new StringBuffer();
		Map<String,Object> values=new HashMap<String,Object>();
			query.append(" from TmsMdEquipment where 1=1 ");
			query.append(" and id=:equipmentId");
			values.put("equipmentId", equipmentId);
			list =  findBy(query, values);
		}
		if(list==null||list.size()==0){
			return new TmsMdEquipment();
		}
		return list.get(0);
	}
	/**
	 * 查询组织
	 */
	@Override
	public BaseOrg getOrg(String orgId) {
		List<BaseOrg> list = null;
		if(AssertHelper.notEmpty(orgId))
		{StringBuffer query=new StringBuffer();
		Map<String,Object> values=new HashMap<String,Object>();
			query.append(" from BaseOrg where 1=1 ");
			query.append(" and id=:orgId");
			values.put("orgId", orgId);
			list =  findBy(query, values);
		}
		if(list==null||list.size()==0){
			return new BaseOrg();
		}
		return list.get(0);
	}
	/**
	 * 特殊申请单批量数据查询
	 */
	@Override
	public DaoPage findInvoiceReqAll(Map<String, Object> map,Integer pageNumber, Integer pageSize) {
		String invoiceReqStartDate = (String) map.get("invoiceReqStartDate");//申请日期开始
		String invoiceReqendDate = (String) map.get("invoiceReqendDate");//申请日期结束
		String status = (String) map.get("status");//申请状态
		String crvatInvoiceReqNumber = (String) map.get("crvatInvoiceReqNumber");//申请单号
		SimpleDateFormat  sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer query=new StringBuffer();
		query.append(" from TmsCrvatInvReqBatchesH where 1=1 ");
		Map<String,Object> values=new HashMap<String,Object>();
		if(AssertHelper.notEmpty(status))
		{
			query.append(" and status=:status");
			values.put("status", status);
		}
		if(AssertHelper.notEmpty(crvatInvoiceReqNumber))
		{
			query.append(" and crvatInvoiceReqNumber=:crvatInvoiceReqNumber");
			values.put("crvatInvoiceReqNumber", crvatInvoiceReqNumber);
		}
		if(AssertHelper.notEmpty(invoiceReqStartDate))
		{  Date stra = null;
			try {
			stra = sim.parse(invoiceReqStartDate);
		} catch (ParseException e) {
		}
			query.append(" and invoiceReqDate >=:invoiceReqStartDate");
			values.put("invoiceReqStartDate", invoiceReqStartDate);
		}
		if(AssertHelper.notEmpty(invoiceReqendDate))
		{
			 Date end = null;
				try {
				end = sim.parse(invoiceReqendDate);
			} catch (ParseException e) {
			}
			query.append(" and invoiceReqDate <=:invoiceReqendDate");
			values.put("invoiceReqendDate", invoiceReqendDate);
		}		
		
		
		query.append("and flag = :flag");
		values.put("flag", "1");
		return this.pageBy(query, values, pageNumber, pageSize);
	
	}
	
	/**
	 * 查询申请单下明细
	 */
	@Override
	public DaoPage findTmsCrvatInvReqBatchesL(Map<String, Object> map,
			int pageNumber, int pageSize) {
		String crvatInvReqBatchesHId = (String) map.get("appuseruuid");
		StringBuffer query=new StringBuffer();
		query.append(" from TmsCrvatInvReqBatchesL where 1=1 ");
		Map<String,Object> values=new HashMap<String,Object>();
		if(AssertHelper.notEmpty(crvatInvReqBatchesHId))
		{
			query.append(" and crvatInvReqBatchesHId=:crvatInvReqBatchesHId");
			values.put("crvatInvReqBatchesHId", crvatInvReqBatchesHId);
		}
		
		return this.pageBy(query, values, pageNumber, pageSize);
	}
	/**
	 * 根据涉税交易规则id
	 */
	@Override
	public List<TmsMdTrxAffirmSetting> findTmsMdTrxAffirmSetting(
			String trxAffirmSettingId) {
		List<TmsMdTrxAffirmSetting> list = null;
		if(AssertHelper.notEmpty(trxAffirmSettingId))
		{StringBuffer query=new StringBuffer();
		Map<String,Object> values=new HashMap<String,Object>();
			query.append(" from TmsMdTrxAffirmSetting where 1=1 ");
			query.append(" and id=:id");
			values.put("id", trxAffirmSettingId);
			list =  findBy(query, values);
		}
		return list;
	}
	
	/**
	 * 根据头id查询交易明细
	 */
	@Override
	public List<TmsCrvatInvReqBatchesL> findByTmsCrvatInvReqBatchesLId(String id) {
		List<TmsCrvatInvReqBatchesL> list = null;
		if(AssertHelper.notEmpty(id))
		{StringBuffer query=new StringBuffer();
		Map<String,Object> values=new HashMap<String,Object>();
			query.append(" from TmsCrvatInvReqBatchesL where 1=1 ");
			query.append(" and crvatInvReqBatchesHId=:crvatInvReqBatchesHId");
			values.put("crvatInvReqBatchesHId", id);
			list =  findBy(query, values);
		}
		return list;
	}
	
	

}
