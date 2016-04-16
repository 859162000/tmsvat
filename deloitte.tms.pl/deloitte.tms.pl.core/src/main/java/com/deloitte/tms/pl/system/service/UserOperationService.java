package com.deloitte.tms.pl.system.service;
// Generated by bo.wang with ling2.autoproject

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.pl.system.dao.UserOperationDao;
import com.deloitte.tms.pl.system.model.UserOperation;
import com.deloitte.tms.pl.system.model.UserOperationInParam;
/**
 * Home object for domain model class UserOperation.
 * @see com.deloitte.tms.pl.system.model
 * @author Hibernate Tools
 */
public interface UserOperationService extends IService{
	public static final String BEAN_ID="userOperationService";
	
	public DaoPage findUserOperationByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<UserOperationInParam> findUserOperationByParams(Map params);
	
	public void saveUserOperationDataListsMap(Map dataListsMap);
	
	public UserOperationInParam convertUserOperationToInParam(UserOperation model);
	
	public UserOperation convertUserOperationInParamToEntity(UserOperationInParam inParam);
	
}
