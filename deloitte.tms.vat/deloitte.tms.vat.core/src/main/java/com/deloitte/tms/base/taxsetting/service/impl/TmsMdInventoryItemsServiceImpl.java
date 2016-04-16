package com.deloitte.tms.base.taxsetting.service.impl;
// Generated by bo.wang with ling2.autoproject

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.BatchUtils;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.base.taxsetting.model.TmsMdInventoryItems;
import com.deloitte.tms.base.taxsetting.model.TmsMdInventoryItemsInParam;
import com.deloitte.tms.base.taxsetting.dao.TmsMdInventoryItemsDao;
import com.deloitte.tms.base.taxsetting.service.TmsMdInventoryItemsService;
/**
 * Home object for domain model class TmsMdInventoryItems.
 * @see com.deloitte.tms.base.taxsetting.model
 * @author Hibernate Tools
 */
@Component(TmsMdInventoryItemsService.BEAN_ID)
public class TmsMdInventoryItemsServiceImpl extends BaseService implements TmsMdInventoryItemsService{
	@Resource
	TmsMdInventoryItemsDao tmsMdInventoryItemsDao;

	@Override
	public IDao getDao() {
		return tmsMdInventoryItemsDao;
	}

	@Override
	public DaoPage findTmsMdInventoryItemsByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}			
		DaoPage daoPage= tmsMdInventoryItemsDao.findTmsMdInventoryItemsByParams(params, pageIndex, pageSize);
		daoPage.setResult(convertTmsMdInventoryItemsToInParam((List<TmsMdInventoryItems>) daoPage.getResult()));
		return daoPage;
	}
	@Override
	public List<TmsMdInventoryItemsInParam> findTmsMdInventoryItemsByParams(Map params) {
		if(params==null)
		{
			params=new HashMap();
		}
		List<TmsMdInventoryItems> temp= tmsMdInventoryItemsDao.findTmsMdInventoryItemsByParams(params);
		return convertTmsMdInventoryItemsToInParam(temp);
	}
	@Override
	public void saveTmsMdInventoryItemsDataListsMap(Map dataListsMap) {
		Collection<TmsMdInventoryItemsInParam> deleteList = BatchUtils.to(TmsMdInventoryItemsInParam.class).getDeleteEntities(dataListsMap);
		Collection<TmsMdInventoryItemsInParam> insertList =  BatchUtils.to(TmsMdInventoryItemsInParam.class).getInsertEntities(dataListsMap);
		Collection<TmsMdInventoryItemsInParam> updateList =  BatchUtils.to(TmsMdInventoryItemsInParam.class).getModifiedEntities(dataListsMap);
		if (updateList != null&&updateList.size() > 0) {
			for(TmsMdInventoryItemsInParam inParam: updateList){
				TmsMdInventoryItems entity=convertTmsMdInventoryItemsInParamToEntity(inParam);
				tmsMdInventoryItemsDao.update(entity);
			}		
//			tmsMdInventoryItemsDao.updateAll(updateList);
		}
		if (insertList != null&&insertList.size() > 0) {
			for(TmsMdInventoryItemsInParam inParam: insertList){
				TmsMdInventoryItems entity=convertTmsMdInventoryItemsInParamToEntity(inParam);
				tmsMdInventoryItemsDao.save(entity);
				inParam.setId(entity.getId());
			}
//			tmsMdInventoryItemsDao.saveAll(insertList);
		}
		if (deleteList != null&&deleteList.size() > 0) {
			for(TmsMdInventoryItemsInParam inParam:deleteList){
				TmsMdInventoryItems entity=new TmsMdInventoryItems();
				entity.setId(inParam.getId());
				tmsMdInventoryItemsDao.remove(entity);
			}
//			tmsMdInventoryItemsDao.removeAll(deleteList);
		}
		
	}
	private List<TmsMdInventoryItemsInParam> convertTmsMdInventoryItemsToInParam(List<TmsMdInventoryItems> models){
		List<TmsMdInventoryItemsInParam> result=new ArrayList<TmsMdInventoryItemsInParam>();
		for(TmsMdInventoryItems initiation:models){
			TmsMdInventoryItemsInParam inparam=convertTmsMdInventoryItemsToInParam(initiation);
			result.add(inparam);
		}
		return result;
	}
	public TmsMdInventoryItemsInParam convertTmsMdInventoryItemsToInParam(TmsMdInventoryItems model){
		TmsMdInventoryItemsInParam inparam=new TmsMdInventoryItemsInParam();
		ReflectUtils.copyProperties(model, inparam);
		if(model.getBaseOrg()!=null)
		{
		inparam.setOrgName(model.getBaseOrg().getOrgName());
		}
		if(model.getTmsMdInventoryCategories()!=null)
		{
		inparam.setInventoryCategoryName(model.getTmsMdInventoryCategories().getInventoryCategoryName());
		inparam.setInventoryCategoryCode(model.getTmsMdInventoryCategories().getInventoryCategoryCode());
		}
		if(model.getTaxItems()!=null)
		{
		inparam.setTaxItemsName(model.getTaxItems().getItemName());
		}
		return inparam;
	}
	public TmsMdInventoryItems convertTmsMdInventoryItemsInParamToEntity(TmsMdInventoryItemsInParam inParam){
		TmsMdInventoryItems entity=new TmsMdInventoryItems();
		ReflectUtils.copyProperties(inParam, entity);
		return entity;
	}
}
