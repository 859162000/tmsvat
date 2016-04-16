package com.deloitte.tms.pl.business.service;

import java.util.List;

import com.deloitte.tms.pl.business.model.Business;
import com.deloitte.tms.pl.business.service.rule.model.IBusiness;
import com.deloitte.tms.pl.core.service.IService;

public interface IBusinessService extends IService {
	
	public Business loadRootBusiness();
	
	public Business loadRootBusinessByType(String type);
	
	public Business loadBusiness(Long businessId);
	
	public List<Business> findEnabledBusinesssByLable(String lable);
	
	public List<Business> findSubBusinesss(Long parentId);
	
	public void createBusiness(Long parentId, Business business);
	
	public void removeBusiness(Long parentId, Business business);
	
	public void updateBusiness(Business business);
	
	public void updateBusinessPosition(Business business, Long oldParentId, Long newParentId, int index);
	
	public IBusiness loadBusinessByCode(String code);
}
