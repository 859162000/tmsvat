package com.deloitte.tms.base.taxsetting.service;

import java.util.Map;

import com.deloitte.tms.base.taxsetting.model.TmsMdProject;
import com.deloitte.tms.base.taxsetting.model.TmsMdProjectInParam;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.service.IService;

public interface TmsMdProjectService extends IService{
	public static final String BEAN_ID="tmsMdProjectService";
	
	public DaoPage findTmsMdProjectByParams(Map params, Integer pageIndex,Integer pageSize);

	public TmsMdProject convertTmsMdProjectInParamToEntity(TmsMdProjectInParam inParam);

	public void removeTmsMdProjects(String projectId);
	
}

