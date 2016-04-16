package com.deloitte.tms.base.masterdata.service.impl;
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
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntityInParam;
import com.deloitte.tms.base.masterdata.dao.TmsMdLegalEntityDao;
import com.deloitte.tms.base.masterdata.service.TmsMdLegalEntityService;
/**
 * Home object for domain model class TmsMdLegalEntity.
 * @see com.deloitte.tms.base.masterdata.model
 * @author Hibernate Tools
 */
@Component(TmsMdLegalEntityService.BEAN_ID)
public class TmsMdLegalEntityServiceImpl extends BaseService implements TmsMdLegalEntityService{
	@Resource
	TmsMdLegalEntityDao tmsMdLegalEntityDao;

	@Override
	public IDao getDao() {
		return tmsMdLegalEntityDao;
	}

	@Override
	public DaoPage findTmsMdLegalEntityByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}			
		DaoPage daoPage= tmsMdLegalEntityDao.findTmsMdLegalEntityByParams(params, pageIndex, pageSize);
		daoPage.setResult(convertTmsMdLegalEntityToInParam((List<TmsMdLegalEntity>) daoPage.getResult()));
		return daoPage;
	}
	@Override
	public List<TmsMdLegalEntityInParam> findTmsMdLegalEntityByParams(Map params) {
		if(params==null)
		{
			params=new HashMap();
		}
		List<TmsMdLegalEntity> temp= tmsMdLegalEntityDao.findTmsMdLegalEntityByParams(params);
		return convertTmsMdLegalEntityToInParam(temp);
	}
	@Override
	public void saveTmsMdLegalEntityDataListsMap(Map dataListsMap) {
		Collection<TmsMdLegalEntityInParam> deleteList = BatchUtils.to(TmsMdLegalEntityInParam.class).getDeleteEntities(dataListsMap);
		Collection<TmsMdLegalEntityInParam> insertList =  BatchUtils.to(TmsMdLegalEntityInParam.class).getInsertEntities(dataListsMap);
		Collection<TmsMdLegalEntityInParam> updateList =  BatchUtils.to(TmsMdLegalEntityInParam.class).getModifiedEntities(dataListsMap);
		if (updateList != null&&updateList.size() > 0) {
			for(TmsMdLegalEntityInParam inParam: updateList){
				TmsMdLegalEntity entity=convertTmsMdLegalEntityInParamToEntity(inParam);
				tmsMdLegalEntityDao.update(entity);
			}		
//			tmsMdLegalEntityDao.updateAll(updateList);
		}
		if (insertList != null&&insertList.size() > 0) {
			for(TmsMdLegalEntityInParam inParam: insertList){
				TmsMdLegalEntity entity=convertTmsMdLegalEntityInParamToEntity(inParam);
				tmsMdLegalEntityDao.save(entity);
				inParam.setId(entity.getId());
			}
//			tmsMdLegalEntityDao.saveAll(insertList);
		}
		if (deleteList != null&&deleteList.size() > 0) {
			for(TmsMdLegalEntityInParam inParam:deleteList){
				TmsMdLegalEntity entity=new TmsMdLegalEntity();
				entity.setId(inParam.getId());
				tmsMdLegalEntityDao.remove(entity);
			}
//			tmsMdLegalEntityDao.removeAll(deleteList);
		}
		
	}
	private List<TmsMdLegalEntityInParam> convertTmsMdLegalEntityToInParam(List<TmsMdLegalEntity> models){
		List<TmsMdLegalEntityInParam> result=new ArrayList<TmsMdLegalEntityInParam>();
		for(TmsMdLegalEntity initiation:models){
			TmsMdLegalEntityInParam inparam=convertTmsMdLegalEntityToInParam(initiation);
			inparam.setLegalEntityId(initiation.getId());
			result.add(inparam);
		}
		return result;
	}
	public TmsMdLegalEntityInParam convertTmsMdLegalEntityToInParam(TmsMdLegalEntity model){
		TmsMdLegalEntityInParam inparam=new TmsMdLegalEntityInParam();
		ReflectUtils.copyProperties(model, inparam);
		inparam.setLegalEntityId(model.getId());
		return inparam;
	}
	public TmsMdLegalEntity convertTmsMdLegalEntityInParamToEntity(TmsMdLegalEntityInParam inParam){
		TmsMdLegalEntity entity=new TmsMdLegalEntity();
		ReflectUtils.copyProperties(inParam, entity);
		return entity;
	}
}
