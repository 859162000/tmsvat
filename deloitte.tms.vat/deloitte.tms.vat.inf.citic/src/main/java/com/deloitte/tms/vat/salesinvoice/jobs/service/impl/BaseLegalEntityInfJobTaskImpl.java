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

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

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
import com.deloitte.tms.base.masterdata.model.TmsMdOrgLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdUsageLocalLegal;
import com.deloitte.tms.base.masterdata.service.BaseOrgService;
import com.deloitte.tms.base.masterdata.service.TmsMdLegalEnablePrintService;
import com.deloitte.tms.base.masterdata.service.TmsMdLegalTaxCategoryService;
import com.deloitte.tms.pl.core.commons.constant.TableColnumDef;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.StringUtils;
import com.deloitte.tms.vat.core.common.IdGenerator;
import com.deloitte.tms.vat.salesinvoice.common.StringPool;
import com.deloitte.tms.vat.salesinvoice.jobs.dao.BaseLegalEntityInfDao;
import com.deloitte.tms.vat.salesinvoice.jobs.model.BaseLegalEntityInf;
import com.deloitte.tms.vat.salesinvoice.jobs.service.BaseLegalEntityInfJobTask;

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

	private final static Logger log = Logger.getLogger(BaseLegalEntityInfJobTaskImpl.class);
	
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
	
	@Override
	public int executeBaseLegalEntityInfDatas(
			List<BaseLegalEntityInf> batchBaseLegalEntityInfs,
			Map<String, TmsMdLegalEntity> legalEntityMap,
			Map<String, BaseOrg> orgMap,
			Map<String , TmsMdOrgLegalEntity> tmsMdOrgLegalEntityMap_LegalEntity,
			Map<String, TmsMdLegalEnablePrint> legalEnablePrintMap,
			Map<String, TmsMdUsageLocalLegal> usageLocalLegalMap) {
		Long totalsapstart = System.currentTimeMillis();
		log.info("********************************************beg batch process TmsMdLegalEntity,Org ******************** ");
		for (BaseLegalEntityInf baseLegalEntityInf : batchBaseLegalEntityInfs) {
			try {//隔离,防止对别的数据影响		
				//处理基础数据
				TmsMdLegalEntity parentLegalEntity = processTmsMdLegalEntityParent(legalEntityMap, baseLegalEntityInf);
				TmsMdLegalEntity legalEntity = processTmsMdLegalEntityChild(legalEntityMap, baseLegalEntityInf);
				BaseOrg parentBaseOrg = processBaseOrgParent(orgMap, baseLegalEntityInf);
				BaseOrg baseOrg = processBaseOrgChild(orgMap, baseLegalEntityInf, parentBaseOrg);
			} catch (Exception e) {				
				log.error("process BaseLegalEntityInf name:"+baseLegalEntityInf.getLegalEntityName()+ "erro info:"+e.getMessage());
				e.printStackTrace();
			}
		}
		log.info("********************************************end batch process TmsMdLegalEntity,Org ******************** ");
		log.info("********************************************beg batch process new tmsMdOrgLegalEntity ******************** ");
		//处理纳税实体和机构关系
		for(TmsMdLegalEntity legalEntity:legalEntityMap.values()){
			try {//隔离,防止对别的数据影响	
				TmsMdOrgLegalEntity tmsMdOrgLegalEntity=tmsMdOrgLegalEntityMap_LegalEntity.get(legalEntity.getId());
				if(tmsMdOrgLegalEntity==null){
					tmsMdOrgLegalEntity=new TmsMdOrgLegalEntity();
					tmsMdOrgLegalEntity.setId(IdGenerator.getUUID());
					tmsMdOrgLegalEntity.setLegalEntityId(legalEntity.getId());
					tmsMdOrgLegalEntity.setLegalEntityType(legalEntity.getLegalEntityType());
					tmsMdOrgLegalEntity.setEnabledFlag(TableColnumDef.ENABLE_EFFECT);
					BaseOrg org=orgMap.get(legalEntity.getLegalEntityCode());
					AssertHelper.notEmpty_assert(org, "没有找到对应的机构,orgcode:"+legalEntity.getLegalEntityCode());
					tmsMdOrgLegalEntity.setOrgId(org.getId());
					baseLegalEntityInfDao.save(tmsMdOrgLegalEntity);
					tmsMdOrgLegalEntityMap_LegalEntity.put(legalEntity.getId(),tmsMdOrgLegalEntity);
				}
			} catch (Exception e) {				
				log.error("process tmsMdOrgLegalEntity code:"+legalEntity.getId()+ "erro info:"+e.getMessage());
				e.printStackTrace();
			}
		}
		
		//删除久关系
//		baseLegalEntityInfDao.removeAll(allTmsMdOrgLegalEntity);
		log.info("********************************************end batch process new tmsMdOrgLegalEntity ******************** ");
		log.info("********************************************beg batch process new TmsMdLegalEnablePrint,TmsMdUsageLocalLegal ******************** ");
		
		int sucessnum=0;
		int errocount=0;
		for (BaseLegalEntityInf baseLegalEntityInf : batchBaseLegalEntityInfs) {
			try {//隔离,防止对别的数据影响					
				//仅仅新数据需要处理这些关系
				TmsMdLegalEntity legalEntity=legalEntityMap.get(baseLegalEntityInf.getLegalEntityId());
				AssertHelper.notEmpty_assert(legalEntity, "没找到相应实体:"+baseLegalEntityInf.getLegalEntityId());
				TmsMdLegalEnablePrint legalEnablePrint=legalEnablePrintMap.get(legalEntity.getId());
				TmsMdUsageLocalLegal usageLocalLegal=usageLocalLegalMap.get(legalEntity.getId());
				
				TmsMdLegalEntity parentEntity=null;
				if(StringUtils.trim(baseLegalEntityInf.getParentId())!=null){
					parentEntity=legalEntityMap.get(StringUtils.trim(baseLegalEntityInf.getParentId()));
					AssertHelper.notEmpty_assert(legalEntity, "没找到相应实体:"+StringUtils.trim(baseLegalEntityInf.getParentId()));
				}
				String parentEntityId=parentEntity==null?null:parentEntity.getId();
				//父节点就是自己,设置父节点为null
				if(parentEntityId!=null&&parentEntityId.equals(legalEntity.getId())){
					parentEntityId=null;
				}
				//处理纳税实体上的是否打印点
				if(parentEntityId==null){
					legalEntity.setIsEnablePrint(StringPool.IS_PRINT_ENABLED);
				}else{
					legalEntity.setIsEnablePrint(StringPool.IS_PRINT_UNENABLED);
				}

				//处理打印关系
				if(legalEnablePrint==null){
					legalEnablePrint = new TmsMdLegalEnablePrint();
					legalEnablePrint.setLegalEntityId(legalEntity.getId());
					legalEnablePrint.setParentId(parentEntityId);
					legalEnablePrint.setIsEnablePrint(parentEntityId==null);
					legalEnablePrint.setEnabledFlag(true);
					tmsMdLegalEnablePrintDao.save(legalEnablePrint);
					legalEnablePrintMap.put(legalEntity.getId(), legalEnablePrint);
				}else{
					legalEnablePrint.setIsEnablePrint(parentEntityId==null);
					legalEnablePrint.setParentId(parentEntityId);
					baseLegalEntityInfDao.update(legalEnablePrint);
				}
				//处理是否使用自身纳税人识别号
				if(usageLocalLegal==null){
					usageLocalLegal=new TmsMdUsageLocalLegal();
					usageLocalLegal.setLegalEntityId(legalEntity.getId());
					usageLocalLegal.setDes(legalEntity.getLegalEntityCode());
					usageLocalLegal.setEnabledFlag(true);
					usageLocalLegal.setIsUsageLocalRegNo(parentEntityId==null);
					usageLocalLegal.setParentId(parentEntityId);
					tmsMdUsageLocalLegalDao.save(usageLocalLegal);
					usageLocalLegalMap.put(legalEntity.getId(), usageLocalLegal);
				}else{
					usageLocalLegal.setIsUsageLocalRegNo(parentEntityId==null);
					usageLocalLegal.setParentId(parentEntityId);
					baseLegalEntityInfDao.update(legalEnablePrint);
				}			
				
				baseLegalEntityInf.setInterfaceTrxFlag(StringPool.FINISH);
				baseLegalEntityInfDao.update(baseLegalEntityInf);
				sucessnum++;
			} catch (Exception e) {				
				baseLegalEntityInf.setInterfaceTrxFlag(StringPool.ERRO);
				baseLegalEntityInfDao.update(baseLegalEntityInf);			
				errocount++;
				log.error("process BaseLegalEntityInf name:"+baseLegalEntityInf.getLegalEntityName()+ "erro info:"+e.getMessage());
				e.printStackTrace();
			}
		}
		log.info("sucess read BaseLegalEntityInf:"+sucessnum);
		log.info("fail read BaseLegalEntityInf:"+errocount);
		log.info("costs: " + (System.currentTimeMillis() - totalsapstart) + " ms");
		log.info("********************************************end batch process BaseLegalEntityInfJobTaskImpl ******************** ");
		return sucessnum;
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
	
	private BaseOrg processBaseOrgParent(Map<String, BaseOrg> orgMap, BaseLegalEntityInf baseLegalEntityInf) {
		String legalEntityCode = StringUtils.trim(baseLegalEntityInf.getParentId());
		if(legalEntityCode!=null){
			BaseOrg baseOrg = orgMap.get(legalEntityCode);
			if(baseOrg==null) {
				baseOrg = new BaseOrg();
				baseOrg.setOrgCode(legalEntityCode);
				baseOrg.setOrgName(legalEntityCode);
				baseOrgDao.save(baseOrg);
				orgMap.put(legalEntityCode,baseOrg);
			}		
			return baseOrg;
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
	
	private BaseOrg processBaseOrgChild(Map<String, BaseOrg> orgMap,BaseLegalEntityInf baseLegalEntityInf, BaseOrg baseOrgParent) {

		String legalEntityCode = StringUtils.trim(baseLegalEntityInf.getLegalEntityId());// 机构号
		if(legalEntityCode!=null){
			String parentId = StringUtils.trim(baseOrgParent.getId());
			//父子相同,上级id为null
			parentId=legalEntityCode.equals(baseOrgParent.getOrgCode())?null:baseOrgParent.getId();
			BaseOrg baseOrg = orgMap.get(legalEntityCode);
			
			if(baseOrg==null) {
				baseOrg = new BaseOrg();
				baseOrg.setOrgCode(legalEntityCode);
				baseOrg.setOrgName(legalEntityCode);
				baseOrg.setParentId(parentId);
				baseOrgDao.save(baseOrg);
				orgMap.put(legalEntityCode,baseOrg);
			} else {
				baseOrg.setParentId(parentId);
				baseOrgDao.update(baseOrg);
			}
			
			return baseOrg;
		}
		return null;
	}
	/** 
	 *〈一句话功能简述〉 
	 *  这里有问题.父的纳税实体类别有问题
	 * @param allTmsMdLegalEntity
	 * @param baseLegalEntityInf
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */	
	private TmsMdLegalEntity processTmsMdLegalEntityParent(Map<String, TmsMdLegalEntity> legalEntityMap, BaseLegalEntityInf baseLegalEntityInf) {
		String parentId = StringUtils.trim(baseLegalEntityInf.getParentId());// 机构号
		if(AssertHelper.notEmpty(parentId)){
			TmsMdLegalEntity tmsMdLegalEntityParent = legalEntityMap.get(parentId);
			if(tmsMdLegalEntityParent==null){
				tmsMdLegalEntityParent = new TmsMdLegalEntity();
				tmsMdLegalEntityParent.setIsEnablePrint(StringPool.IS_PRINT_ENABLED);
				tmsMdLegalEntityParent.setLegalEntityCode(parentId);
				tmsMdLegalEntityParent.setLegalEntityName(parentId);
				tmsMdLegalEntityDao.save(tmsMdLegalEntityParent);
				legalEntityMap.put(parentId,tmsMdLegalEntityParent);
			}else{
				
			}
			return tmsMdLegalEntityParent;
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
	private TmsMdLegalEntity processTmsMdLegalEntityChild(
			Map<String, TmsMdLegalEntity> legalEntityMap
			, BaseLegalEntityInf baseLegalEntityInf) {
		
		String legalEntityCode = StringUtils.trim(baseLegalEntityInf.getLegalEntityId());// 机构号
		if(AssertHelper.notEmpty(legalEntityCode)){
			TmsMdLegalEntity tmsMdLegalEntityChild = legalEntityMap.get(legalEntityCode);
			if(tmsMdLegalEntityChild==null){
				tmsMdLegalEntityChild = new TmsMdLegalEntity();
				tmsMdLegalEntityChild.setIsEnablePrint(StringPool.IS_PRINT_UNENABLED);
				tmsMdLegalEntityChild.setLegalEntityCode(legalEntityCode);
				tmsMdLegalEntityChild.setLegalEntityName(legalEntityCode);
				tmsMdLegalEntityDao.save(tmsMdLegalEntityChild);
				legalEntityMap.put(legalEntityCode,tmsMdLegalEntityChild);
			} else {//补全父节点先处理的问题
				
			}
			return tmsMdLegalEntityChild;
		}
		return null;
	}
}
