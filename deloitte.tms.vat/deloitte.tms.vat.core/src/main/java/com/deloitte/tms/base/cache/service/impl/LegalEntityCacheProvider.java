package com.deloitte.tms.base.cache.service.impl;

import java.util.ArrayList;
import java.util.Collection;
//import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.base.cache.model.LegalEntityNode;
import com.deloitte.tms.base.cache.model.OrgNode;
import com.deloitte.tms.base.cache.service.CacheBusinessContext;
import com.deloitte.tms.base.masterdata.constant.LegalTaxCategoryDef;
import com.deloitte.tms.base.masterdata.dao.LegalEntityDao;
import com.deloitte.tms.base.masterdata.model.LegalTaxCategory;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEnablePrint;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEquipment;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalInvoice;
import com.deloitte.tms.base.masterdata.model.TmsMdOrgLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdUsageLocalLegal;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;

@Component(LegalEntityCacheProvider.BEAN_ID)
public class LegalEntityCacheProvider extends LegalEntityBaseCacheProvider{
	
	public static final String BEAN_ID="legalEntityCacheProvider";
	
	@Resource
	LegalEntityDao legalEntityDao;

	@Override
	public OrgNode build(CacheBusinessContext context) throws BusinessException {
		/**
		 * 查询当前有效的纳税人实体数据
		 */
		List<TmsMdLegalEntity> legalEntities=getlegalEntities();
		/**
		 * 查询当前有效的纳税主体与纳税主体与汇缴(税种)关系 组成回角关系树
		 * @param taxCode
		 */
		List<LegalTaxCategory> legalTaxCategories=getLegalTaxCategories();
		
		/**
		 * 获取当前有效的组织和纳税主体关系
		 */
		List<TmsMdOrgLegalEntity> orgLegalEntities=getOrgLegalEntities();
		
		/**
		 * 获取当前有效的纳税人限额
		 */
		List<TmsMdLegalInvoice> legalInvoices=getLegalInvoices();
		
		/**
		 * 获取当前有效的是否采用自身纳税识别号
		 * @param taxCode
		 * @return
		 */
		List<TmsMdUsageLocalLegal> usageLocalLegals= getUsageLocalLegals();
		/**
		 * 获取当前有效的纳税主体与打印终端关系
		 * @return
		 */
		List<TmsMdLegalEquipment> legalEquipments= getLegalEquipments();
		/**
		 * 获取当前有效的是否打印点 纳税人主体与打印点关系 
		 * @return
		 */
		List<TmsMdLegalEnablePrint> legalEnablePrints= getLegalEnablePrints();
		
		Map<String, Collection<TmsMdLegalEntity>> subordinateRelations = new HashMap<String, Collection<TmsMdLegalEntity>>();
		//组件纳税实体关系树
		LegalEntityNode rootlLegalEntityNode = buildLegalEntityTree(legalEntities,legalTaxCategories, subordinateRelations);
		//设置纳税实体关联的所属机构,和对应的BizOrgNode
		processLegalEntityAndOrgRelation(orgLegalEntities, rootlLegalEntityNode);
		//获取当前有效的纳税人限额
		processLegalInvoice(legalInvoices, rootlLegalEntityNode);
		//获取当前有效的是否采用自身纳税识别号
		processSelfLegalNumber(usageLocalLegals, rootlLegalEntityNode);
		//获取当前有效的纳税主体与打印终端关系
		processLegalEquipment(legalEquipments, rootlLegalEntityNode);
		//获取当前有效的是否打印点,和打印点事什么
		processPrintSite(legalEnablePrints, rootlLegalEntityNode);
		
		return rootlLegalEntityNode;
	}
	private LegalEntityNode buildLegalEntityTree(
			List<TmsMdLegalEntity> legalEntities,
			List<LegalTaxCategory> legalTaxCategories,
			Map<String, Collection<TmsMdLegalEntity>> subordinateRelations) {
		//上级id为null的一级数据放到root下
		LegalEntityNode root = assembleRoot();
		
		for (TmsMdLegalEntity legalEntity : legalEntities) {
			//从legalTaxCategories中获取父级id
			String superiorId = getsuperiorId(legalTaxCategories, legalEntity);
//			System.out.println(" 纳税实体id:"+legalEntity.getId()+"  parentId:"+superiorId);
			//根据上級ID把下属的机构都归在一个机构下
			if (subordinateRelations.keySet().contains(superiorId)) {
				Collection<TmsMdLegalEntity> subordinates = subordinateRelations.get(superiorId);
				subordinates.add(legalEntity);
			} else {
				Collection<TmsMdLegalEntity> subordinates = new ArrayList<TmsMdLegalEntity>();
				subordinates.add(legalEntity);
				subordinateRelations.put(superiorId, subordinates);
			}			
		}
		
		//构建纳税人实体树
		assembleTree(root, subordinateRelations);
		return root;
	}
	protected String  getsuperiorId(List<LegalTaxCategory> legalTaxCategories,TmsMdLegalEntity legalEntity) {
		AssertHelper.notEmpty_assert(legalEntity.getId(),"纳税实体主键不能为空");
		for(LegalTaxCategory legalTaxCategory:legalTaxCategories){
			AssertHelper.notEmpty_assert(legalTaxCategory.getLegalEntityId(),"纳税实体税种关系中纳税实体id不能为空");
			if(legalTaxCategory.getLegalEntityId().equals(legalEntity.getId())){
				return legalTaxCategory.getParentId();
//				return getLegalEntityId(legalTaxCategories,legalTaxCategory.getParentLegalTaxCategoryId());
			}
		}
		return null;
	}
}
