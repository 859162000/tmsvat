package com.deloitte.tms.pl.core.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.dao.IModelHistoryDAO;
import com.deloitte.tms.pl.core.model.impl.LingModelhistory;
import com.deloitte.tms.pl.core.service.IModelHistoryService;

public class ModelHistoryServiceImpl extends BaseService implements IModelHistoryService{
	// Logger log = new Logger(ModelHistoryServiceImpl.class.getName());
	IModelHistoryDAO modelHistoryDAO;

	public IModelHistoryDAO getModelHistoryDAO() {
		return modelHistoryDAO;
	}

	public void setModelHistoryDAO(IModelHistoryDAO modelHistoryDAO) {
		this.modelHistoryDAO = modelHistoryDAO;
	}

	
	public IDao getDao() {
		// TODO Auto-generated method stub
		return modelHistoryDAO;
	}

	
	public List<LingModelhistory> findModelhistories(Map params) {
		// TODO Auto-generated method stub
		return modelHistoryDAO.findModelhistories(params);
	}

	
	public Object findById(Class clazz,Serializable id) {
		// TODO Auto-generated method stub
		return modelHistoryDAO.get(clazz, id);
	}

	

	

}
