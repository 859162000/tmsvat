/**    
 * Copyright (C),Deloitte
 * @FileName: BaseLegalEntityInfJobTaskImpl.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.jobs.task.impl  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月26日 下午8:06:29  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.deloitte.tms.vat.salesinvoice.jobs.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.deloitte.tms.base.cache.service.OrgPathProvider;
import com.deloitte.tms.base.masterdata.dao.BaseOrgDao;
import com.deloitte.tms.base.masterdata.dao.TmsMdLegalEnablePrintDao;
import com.deloitte.tms.base.masterdata.dao.TmsMdLegalEntityDao;
import com.deloitte.tms.base.masterdata.dao.TmsMdLegalTaxCategoryDao;
import com.deloitte.tms.base.masterdata.dao.TmsMdOrgLegalEntityDao;
import com.deloitte.tms.base.masterdata.dao.TmsMdUsageLocalLegalDao;
import com.deloitte.tms.base.masterdata.model.BaseOrg;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEnablePrint;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalTaxCategory;
import com.deloitte.tms.base.masterdata.model.TmsMdOrgLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdUsageLocalLegal;
import com.deloitte.tms.base.masterdata.service.BaseOrgService;
import com.deloitte.tms.base.masterdata.service.TmsMdLegalEnablePrintService;
import com.deloitte.tms.base.masterdata.service.TmsMdLegalEntityService;
import com.deloitte.tms.base.masterdata.service.TmsMdLegalTaxCategoryService;
import com.deloitte.tms.base.masterdata.service.TmsMdOrgLegalEntityService;
import com.deloitte.tms.pl.core.commons.utils.StringUtils;
import com.deloitte.tms.vat.core.common.IdGenerator;
import com.deloitte.tms.vat.salesinvoice.common.StringPool;
import com.deloitte.tms.vat.salesinvoice.jobs.dao.BaseLegalEntityInfDao;
import com.deloitte.tms.vat.salesinvoice.jobs.model.BaseLegalEntityInf;
import com.deloitte.tms.vat.salesinvoice.jobs.model.TmsCrvatTrxInf;
import com.deloitte.tms.vat.salesinvoice.jobs.service.BaseLegalEntityInfJobTask;
import com.deloitte.tms.vat.salesinvoice.jobs.service.BaseLegalEntityInfService;
import com.deloitte.tms.vat.salesinvoice.jobs.service.TrxFileProcessJobTask;

/**
 * 〈一句话功能简述〉 功能详细描述
 * 
 * @author stonshi
 * @create 2016年3月26日 下午8:06:29
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component(BaseLegalEntityInfJobTask.BEAN_ID)
public class BaseLegalEntityInfJobTaskImpl implements BaseLegalEntityInfJobTask {

	@Resource
	private BaseLegalEntityInfDao baseLegalEntityInfDao;

	@Resource
	private TmsMdLegalEntityDao tmsMdLegalEntityDao;

	@Resource
	private TmsMdOrgLegalEntityDao tmsMdOrgLegalEntityDao;

	@Resource
	private TmsMdLegalEnablePrintService tmsMdLegalEnablePrintService;

	@Resource
	private TmsMdLegalEnablePrintDao tmsMdLegalEnablePrintDao;

	@Resource
	private TmsMdLegalTaxCategoryDao tmsMdLegalTaxCategoryDao;

	@Resource
	private TmsMdLegalTaxCategoryService tmsMdLegalTaxCategoryService;

	@Resource
	private BaseOrgDao baseOrgDao;

	@Resource
	OrgPathProvider orgPathProvider;

	@Resource
	private BaseOrgService baseOrgService;
	
	@Resource
	private TmsMdUsageLocalLegalDao tmsMdUsageLocalLegalDao;

	
	/**   
	 * @param map
	 * @return  
	 * @see com.deloitte.tms.vat.salesinvoice.jobs.service.BaseLegalEntityInfJobTask#executeBaseLegalEntityInfData(java.util.Map)  
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public int executeBaseLegalEntityInfDatas(Map<String, Object> map) {
		
		int sucessnum=0;
		
		List<BaseLegalEntityInf> batchBaseLegalEntityInf = (List<BaseLegalEntityInf>) map.get("batchBaseLegalEntityInf");
		
		for(BaseLegalEntityInf baseLegalEntityInf:batchBaseLegalEntityInf){
			if(executeBaseLegalEntityInfData(baseLegalEntityInf,map)){
				sucessnum++;
			}
		}
		return sucessnum;
		
	}


	
	/** 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param baseLegalEntityInf
	 * @param map
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	@SuppressWarnings("unchecked")
	private boolean executeBaseLegalEntityInfData(BaseLegalEntityInf baseLegalEntityInf, Map<String, Object> map) {
		
		List<TmsMdLegalEntity> allTmsMdLegalEntity = (List<TmsMdLegalEntity>) map.get("allTmsMdLegalEntity");
		List<TmsMdLegalEnablePrint> allTmsMdLegalEnablePrint = (List<TmsMdLegalEnablePrint>) map.get("allTmsMdLegalEnablePrint");
		List<BaseOrg> allBaseOrg = (List<BaseOrg>) map.get("allBaseOrg");
		List<TmsMdUsageLocalLegal> allTmsMdUsageLocalLegal = (List<TmsMdUsageLocalLegal>) map.get("allTmsMdUsageLocalLegal");
		List<TmsMdOrgLegalEntity> allTmsMdOrgLegalEntity = (List<TmsMdOrgLegalEntity>) map.get("allTmsMdOrgLegalEntity");
		
		try {
			
			TmsMdLegalEntity tmsMdLegalEntityChild = processTmsMdLegalEntityChild(allTmsMdLegalEntity, baseLegalEntityInf);
			TmsMdLegalEntity tmsMdLegalEntityParent = processTmsMdLegalEntityParent(allTmsMdLegalEntity, baseLegalEntityInf);
			//取得父子TmsMdLegalEntity的uuid
			String childLegalEntityId = tmsMdLegalEntityChild.getId();
			String parentLegalEntityId = tmsMdLegalEntityParent.getId();
			//处理打印关系
			Map<String, String> mapPrint = new HashMap<String, String>();
			mapPrint.put("childLegalEntityId", childLegalEntityId);
			mapPrint.put("parentLegalEntityId", parentLegalEntityId);
			
			processTmsMdLegalEnablePrintChild(allTmsMdLegalEnablePrint, mapPrint);
			processTmsMdLegalEnablePrintParent(allTmsMdLegalEnablePrint, mapPrint);
			BaseOrg baseOrgParent = processBaseOrgParent(allBaseOrg, baseLegalEntityInf);
			BaseOrg baseOrgChild = processBaseOrgChild(allBaseOrg, baseLegalEntityInf, baseOrgParent);
			processTmsMdUsageLocalLegal(allTmsMdUsageLocalLegal, tmsMdLegalEntityChild, tmsMdLegalEntityParent);
			processTmsMdOrgLegalEntity(allTmsMdOrgLegalEntity, baseLegalEntityInf, tmsMdLegalEntityChild, baseOrgChild);
			
			baseLegalEntityInf.setInterfaceTrxFlag(StringPool.FINISH);
			baseLegalEntityInfDao.update(baseLegalEntityInf);
			
			return true;
		} catch (Exception e) {
			
			baseLegalEntityInf.setInterfaceTrxFlag(StringPool.ERRO);
			baseLegalEntityInfDao.update(baseLegalEntityInf);
			//log.error("处理数据"+tmsCrvatTrxInf.toString()+"出错,原因:"+e.getMessage()+" 堆栈信息如下:");
			e.printStackTrace();
			return false;
		}
		
	}

	
	
	/** 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param allTmsMdLegalEnablePrint
	 * @param mapPrint
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	private void processTmsMdLegalEnablePrintParent(List<TmsMdLegalEnablePrint> allTmsMdLegalEnablePrint, Map<String, String> mapPrint) {
		// TODO Auto-generated method stub
		TmsMdLegalEnablePrint tmsMdLegalEnablePrintParent = getTmsMdLegalEnablePrintParent(allTmsMdLegalEnablePrint,mapPrint);
		String legalEntityId = mapPrint.get("parentLegalEntityId");
		if(tmsMdLegalEnablePrintParent == null) {
			tmsMdLegalEnablePrintParent = new TmsMdLegalEnablePrint();
			tmsMdLegalEnablePrintParent.setLegalEntityId(legalEntityId);
			tmsMdLegalEnablePrintParent.setParentId(null);
			tmsMdLegalEnablePrintParent.setIsEnablePrint(true);
			tmsMdLegalEnablePrintParent.setEnabledFlag(true);
			tmsMdLegalEnablePrintDao.save(tmsMdLegalEnablePrintParent);
			allTmsMdLegalEnablePrint.add(tmsMdLegalEnablePrintParent);
		} 
	}



	/** 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param allTmsMdUsageLocalLegal
	 * @param tmsMdLegalEntityChild
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	private void processTmsMdUsageLocalLegal(List<TmsMdUsageLocalLegal> allTmsMdUsageLocalLegal, TmsMdLegalEntity tmsMdLegalEntityChild,TmsMdLegalEntity tmsMdLegalEntityParent) {
		String legalEntityId = tmsMdLegalEntityChild.getId();
		String legalEntityCode = tmsMdLegalEntityChild.getLegalEntityCode();
		String parentId = tmsMdLegalEntityParent.getId();
		TmsMdUsageLocalLegal tmsMdUsageLocalLegal = getTmsMdUsageLocalLegal(allTmsMdUsageLocalLegal, legalEntityId);
		if(tmsMdUsageLocalLegal==null) {
			tmsMdUsageLocalLegal=new TmsMdUsageLocalLegal();
			tmsMdUsageLocalLegal.setLegalEntityId(legalEntityId);
			tmsMdUsageLocalLegal.setDes(legalEntityCode);
			tmsMdUsageLocalLegal.setEnabledFlag(true);
			tmsMdUsageLocalLegal.setIsUsageLocalRegNo(false);
			tmsMdUsageLocalLegalDao.save(tmsMdUsageLocalLegal);
		}
	}



	
	/** 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param allTmsMdUsageLocalLegal
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	private TmsMdUsageLocalLegal getTmsMdUsageLocalLegal(List<TmsMdUsageLocalLegal> allTmsMdUsageLocalLegal,String legalEntityId) {
		for(TmsMdUsageLocalLegal tmsMdUsageLocalLegal:allTmsMdUsageLocalLegal) {
			if(legalEntityId.equals(tmsMdUsageLocalLegal.getLegalEntityId())) {
				return tmsMdUsageLocalLegal;
			} 
		}
		return null;
	}



	/** 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param allTmsMdOrgLegalEntity
	 * @param tmsMdLegalEntityChild
	 * @param baseOrgChild
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	private void processTmsMdOrgLegalEntity(List<TmsMdOrgLegalEntity> allTmsMdOrgLegalEntity,BaseLegalEntityInf baseLegalEntityInf,TmsMdLegalEntity tmsMdLegalEntityChild, BaseOrg baseOrgChild) {

		String legalEntityId = tmsMdLegalEntityChild.getId();
		String legalEntityType = baseLegalEntityInf.getLegalEntityType();
		String orgId = baseOrgChild.getId();
		
		TmsMdOrgLegalEntity tmsMdOrgLegalEntity = getTmsMdOrgLegalEntity(allTmsMdOrgLegalEntity,legalEntityId,orgId);
		
		if(tmsMdOrgLegalEntity==null) {
			tmsMdOrgLegalEntity = new TmsMdOrgLegalEntity();
			tmsMdOrgLegalEntity.setLegalEntityId(legalEntityId);
			tmsMdOrgLegalEntity.setOrgId(baseOrgChild.getId());
			tmsMdOrgLegalEntity.setLegalEntityType(legalEntityType);
			tmsMdOrgLegalEntity.setEnabledFlag("Y");
			tmsMdOrgLegalEntity.setId(IdGenerator.getUUID());
			tmsMdOrgLegalEntityDao.saveOrUpdate(tmsMdOrgLegalEntity);
		}
	}

	
	/** 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param allTmsMdOrgLegalEntity
	 * @param legalEntityId
	 * @param orgId
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	private TmsMdOrgLegalEntity getTmsMdOrgLegalEntity(List<TmsMdOrgLegalEntity> allTmsMdOrgLegalEntity, String legalEntityId, String orgId) {
		for(TmsMdOrgLegalEntity tmsMdOrgLegalEntity:allTmsMdOrgLegalEntity) {
			if(legalEntityId.equals(tmsMdOrgLegalEntity.getLegalEntityId())&&orgId.equals(tmsMdOrgLegalEntity.getOrgId())) {
				return tmsMdOrgLegalEntity;
			} 
		}
		return null;
	}



	/** 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param allBaseOrg
	 * @param baseLegalEntityInf
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	private BaseOrg processBaseOrgParent(List<BaseOrg> allBaseOrg, BaseLegalEntityInf baseLegalEntityInf) {
		String legalEntityCode = baseLegalEntityInf.getParentId();
		BaseOrg baseOrg = getBaseOrgParent(allBaseOrg,legalEntityCode);

		if(baseOrg==null) {
			baseOrg = new BaseOrg();
			baseOrg.setOrgCode(legalEntityCode);
			baseOrg.setOrgName(legalEntityCode);
			baseOrgDao.save(baseOrg);
		}
		
		return baseOrg;
	}


	/** 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param allBaseOrg
	 * @param legalEntityCode
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	private BaseOrg getBaseOrgParent(List<BaseOrg> allBaseOrg, String legalEntityCode) {
		for(BaseOrg baseOrg:allBaseOrg) {
			if(legalEntityCode.equals(baseOrg.getOrgCode())){
				return baseOrg;
			}
		}
		return null;
	}

	/** 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param allBaseOrg
	 * @param baseOrgParent 
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	private BaseOrg processBaseOrgChild(List<BaseOrg> allBaseOrg,BaseLegalEntityInf baseLegalEntityInf, BaseOrg baseOrgParent) {

		String legalEntityCode = baseLegalEntityInf.getLegalEntityId();// 机构号
		String parentId = baseOrgParent.getId();
		BaseOrg baseOrg = getBaseOrgChild(allBaseOrg,legalEntityCode,parentId);
		
		if(baseOrg==null) {
			baseOrg = new BaseOrg();
			baseOrg.setOrgCode(legalEntityCode);
			baseOrg.setOrgName(legalEntityCode);
			baseOrg.setParentId(parentId);
			baseOrgDao.save(baseOrg);
		} else {
			baseOrg.setParentId(parentId);
			baseOrgDao.update(baseOrg);
		}
		
		return baseOrg;
	}
	
	/** 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param allBaseOrg
	 * @param legalEntityCode
	 * @param parentId 
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	private BaseOrg getBaseOrgChild(List<BaseOrg> allBaseOrg, String legalEntityCode, String parentId) {
		for(BaseOrg baseOrg:allBaseOrg) {
			if(legalEntityCode.equals(baseOrg.getOrgCode())&&parentId.equals(baseOrg.getParentId())){
				return baseOrg;
			}
		}
		return null;
	}



	/** 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param allTmsMdLegalEntity
	 * @param baseLegalEntityInf
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	private TmsMdLegalEntity processTmsMdLegalEntityParent(List<TmsMdLegalEntity> allTmsMdLegalEntity, BaseLegalEntityInf baseLegalEntityInf) {
		String parentId = StringUtils.trim(baseLegalEntityInf.getParentId());// 机构号
		
		TmsMdLegalEntity tmsMdLegalEntityParent = getTmsMdLegalEntity(allTmsMdLegalEntity,parentId);
		String legalEntityLevel = baseLegalEntityInf.getLegalEntityLevel();// 实法人实体层级
		
		if(tmsMdLegalEntityParent==null){
			tmsMdLegalEntityParent = new TmsMdLegalEntity();
			tmsMdLegalEntityParent.setIsEnablePrint(StringPool.IS_PRINT_ENABLED);
			tmsMdLegalEntityParent.setLegalEntityCode(parentId);
			tmsMdLegalEntityParent.setLegalEntityName(parentId);
			tmsMdLegalEntityParent.setLegalEntityType(legalEntityLevel);
			tmsMdLegalEntityParent.setParentId(null);
			tmsMdLegalEntityDao.save(tmsMdLegalEntityParent);
			allTmsMdLegalEntity.add(tmsMdLegalEntityParent);
		}
	
		return tmsMdLegalEntityParent;
	}



	/** 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param allTmsMdLegalEnablePrint
	 * @param baseLegalEntityInf
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	private TmsMdLegalEnablePrint processTmsMdLegalEnablePrintChild(List<TmsMdLegalEnablePrint> allTmsMdLegalEnablePrint, Map<String,String> mapPrint) {
		TmsMdLegalEnablePrint tmsMdLegalEnablePrintChild = getTmsMdLegalEnablePrintChild(allTmsMdLegalEnablePrint,mapPrint);
		String legalEntityId = mapPrint.get("childLegalEntityId");
		String parentId = mapPrint.get("parentLegalEntityId");
		if(tmsMdLegalEnablePrintChild == null) {
			tmsMdLegalEnablePrintChild = new TmsMdLegalEnablePrint();
			tmsMdLegalEnablePrintChild.setLegalEntityId(legalEntityId);
			tmsMdLegalEnablePrintChild.setParentId(parentId);
			tmsMdLegalEnablePrintChild.setIsEnablePrint(false);
			tmsMdLegalEnablePrintChild.setEnabledFlag(true);
			tmsMdLegalEnablePrintDao.save(tmsMdLegalEnablePrintChild);
			allTmsMdLegalEnablePrint.add(tmsMdLegalEnablePrintChild);
		} else {
			tmsMdLegalEnablePrintChild.setParentId(parentId);
			tmsMdLegalEnablePrintDao.update(tmsMdLegalEnablePrintChild);
		}
		return tmsMdLegalEnablePrintChild;
	}
	
	
	
	/** 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param allTmsMdLegalEnablePrint
	 * @param mapPrint
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	private TmsMdLegalEnablePrint getTmsMdLegalEnablePrintParent(List<TmsMdLegalEnablePrint> allTmsMdLegalEnablePrint, Map<String, String> mapPrint) {

		String legalEntityId = mapPrint.get("parentLegalEntityId");
		
		for(TmsMdLegalEnablePrint tmsMdLegalEnablePrint:allTmsMdLegalEnablePrint){
			if(legalEntityId.equals(tmsMdLegalEnablePrint.getLegalEntityId())){
				return tmsMdLegalEnablePrint;
			}
		}
		return null;
	}



	/** 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param allTmsMdLegalEnablePrint
	 * @param legalEntityId
	 * @param parentId
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	private TmsMdLegalEnablePrint getTmsMdLegalEnablePrintChild(List<TmsMdLegalEnablePrint> allTmsMdLegalEnablePrint,Map<String,String> mapPrint) {
		
		String legalEntityId = mapPrint.get("childLegalEntityId");
		
		for(TmsMdLegalEnablePrint tmsMdLegalEnablePrint:allTmsMdLegalEnablePrint){
			if(legalEntityId.equals(tmsMdLegalEnablePrint.getLegalEntityId())){
				return tmsMdLegalEnablePrint;
			}
		}
		return null;
	}

	/** 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param allTmsMdLegalEntity
	 * @param baseLegalEntityInf 
	 * @return 
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	private TmsMdLegalEntity processTmsMdLegalEntityChild(List<TmsMdLegalEntity> allTmsMdLegalEntity, BaseLegalEntityInf baseLegalEntityInf) {
		
		String legalEntityCode = baseLegalEntityInf.getLegalEntityId();// 机构号
		String legalEntityLevel = baseLegalEntityInf.getLegalEntityLevel();// 实法人实体层级
		String parentId = StringUtils.trim(baseLegalEntityInf.getParentId());
		
		TmsMdLegalEntity tmsMdLegalEntityChild = getTmsMdLegalEntity(allTmsMdLegalEntity,legalEntityCode);
		
		if(tmsMdLegalEntityChild==null){
			tmsMdLegalEntityChild = new TmsMdLegalEntity();
			tmsMdLegalEntityChild.setIsEnablePrint(StringPool.IS_PRINT_UNENABLED);
			tmsMdLegalEntityChild.setLegalEntityCode(legalEntityCode);
			tmsMdLegalEntityChild.setLegalEntityName(legalEntityCode);
			tmsMdLegalEntityChild.setLegalEntityType(legalEntityLevel);
			tmsMdLegalEntityChild.setParentId(parentId);
			tmsMdLegalEntityDao.save(tmsMdLegalEntityChild);
			allTmsMdLegalEntity.add(tmsMdLegalEntityChild);
		} else {
			tmsMdLegalEntityChild.setParentId(parentId);
			tmsMdLegalEntityDao.update(tmsMdLegalEntityChild);
		}
	
		return tmsMdLegalEntityChild;
	}
	
	/** 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param allTmsMdLegalEntity
	 * @param legalEntityCode
	 * @param parentId 
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	
	private TmsMdLegalEntity getTmsMdLegalEntity(List<TmsMdLegalEntity> allTmsMdLegalEntity, String legalEntityCode) {
		for(TmsMdLegalEntity tmsMdLegalEntity:allTmsMdLegalEntity){
			if(legalEntityCode.equals(tmsMdLegalEntity.getLegalEntityCode())){
				return tmsMdLegalEntity;
			}
		}
		return null;
	}

}
