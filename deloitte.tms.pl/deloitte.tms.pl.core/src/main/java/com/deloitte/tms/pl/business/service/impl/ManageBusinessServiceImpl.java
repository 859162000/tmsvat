package com.deloitte.tms.pl.business.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.business.dao.IBusinessDao;
import com.deloitte.tms.pl.business.model.Business;
import com.deloitte.tms.pl.business.service.IBusinessService;
import com.deloitte.tms.pl.business.service.rule.model.IBusiness;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
@Component("businessService")
public class ManageBusinessServiceImpl extends BaseService implements IBusinessService {
@Resource
private IBusinessDao businessDao;
	
	public void setBusinessDao(IBusinessDao businessDao) {
		this.businessDao = businessDao;
	}

	public Business loadRootBusiness() {
		String type = IBusiness.ROOT;
		return loadRootBusinessByType(type);
	}
	public Business loadRootBusinessByType(String type) {
		
		List<Business> rootBusinesss = businessDao.findEnabledBusinesssByType(type);
		if (rootBusinesss.isEmpty() || rootBusinesss.size() > 1) {
			throw new BusinessException("跟逻辑数据异常，请检查基础数据");
		}
		Business rootBusiness = rootBusinesss.get(0);
		return rootBusiness;
	}
	public Business loadBusiness(Long businessId) {
		return (Business) businessDao.get(Business.class, businessId);
	}

	public List<Business> findSubBusinesss(Long parentId) {
		List<Business> subBusinesss =businessDao.findSubBusinesss(parentId);
		return subBusinesss;
	}
	public List<Business> findEnabledBusinesssByLable(String lable)
	{
		return businessDao.findEnabledBusinesssByLable(lable);
	}
	public void createBusiness(Long parentId, Business business) {
		Business parent = loadBusiness(parentId);
		
		business.setEnabled(Boolean.TRUE);
		business.setCode(UUID.randomUUID().toString());
		parent.addSubBusiness(business);
		businessDao.save(business);
		businessDao.update(parent);
		
		createBusinessResource(business);
	}
	
	private void createBusinessResource(Business business) {
//		BusinessResource businessResource = new BusinessResource();
//		businessResource.setResourceName(business.getLabel());
//		businessResource.setEnabled(Boolean.TRUE);
//		businessResource.setType(Resource.MENU);
//		businessResource.setBusiness(business);
//		
//		businessDao.update(businessResource);
	}

	public void removeBusiness(Long parentId, Business business) {
		Business parent = loadBusiness(parentId);
		Business entity = loadBusiness((Long) business.getId());
		parent.removeSubBusiness(entity);
		
		businessDao.update(parent);
		
		removeSubBusiness(entity);
	}
	
	private void removeSubBusiness(Business business) {
		business.setEnabled(Boolean.FALSE);
		businessDao.update(business);
		removeBusinessResource(business);
		List<Business> subBusinesss = business.getSubBusinesss();
		for (Business subBusiness : subBusinesss) {
			removeSubBusiness(subBusiness);
		}
	}
	
	private void removeBusinessResource(Business business) {
//		Long businessId = business.getId();
//		List<BusinessResource> resources = resourceDao.findBusinessResourcesByBusinessId(businessId);
//		for (BusinessResource resource : resources) {
//			resource.setEnabled(Boolean.FALSE);
//			businessDao.update(resource);
//		}
	}

	public void updateBusiness(Business business) {
		Long businessId = (Long) business.getId();
		Business entity = loadBusiness(businessId);
		entity.setLabel(business.getLabel());
		entity.setDescription(business.getDescription());
		entity.setIcon(business.getIcon());
//		entity.setTarget(business.getTarget());
		entity.setCode(business.getCode());
		entity.setPath(business.getPath());
		businessDao.update(entity);
	}

	public void updateBusinessPosition(Business business, Long oldParentId,
			Long newParentId, int index) {
		Business oldParent = loadBusiness(oldParentId);
		Business entity = loadBusiness((Long) business.getId());
		oldParent.removeSubBusiness(entity);
		businessDao.update(oldParent);
		Business newParent = loadBusiness(newParentId);
		newParent.addSubBusiness(entity, index);
		businessDao.update(newParent);
	}
	@Override
	public IBusiness loadBusinessByCode(String code) {
		return businessDao.loadBusinessByCode(code);
	}

	public IDao getDao() {
		return businessDao;
	}	

}
