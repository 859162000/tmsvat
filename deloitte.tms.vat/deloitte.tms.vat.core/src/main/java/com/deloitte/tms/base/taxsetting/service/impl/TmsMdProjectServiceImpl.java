package com.deloitte.tms.base.taxsetting.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.base.taxsetting.dao.TmsMdProjectDao;
import com.deloitte.tms.base.taxsetting.dao.TmsMdProjectDao;
import com.deloitte.tms.base.taxsetting.model.TaxRuleAndRateInParam;
import com.deloitte.tms.base.taxsetting.model.TmsMdProject;
import com.deloitte.tms.base.taxsetting.model.TmsMdProjectInParam;
import com.deloitte.tms.base.taxsetting.model.TmsMdProjectInParam;
import com.deloitte.tms.base.taxsetting.service.TmsMdProjectService;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;

@Component(TmsMdProjectService.BEAN_ID)
public class TmsMdProjectServiceImpl extends BaseService implements TmsMdProjectService {
	@Resource
	private TmsMdProjectDao tmsMdProjectDao;

	@Override
	public IDao getDao() {
		return tmsMdProjectDao;
	}

	public DaoPage findTmsMdProjectByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if (params == null) {
			params = new HashMap();
		}
		DaoPage daoPage = tmsMdProjectDao.findTmsMdProjectByParams(params,
				pageIndex, pageSize);

		return daoPage;
	}

	public List<TaxRuleAndRateInParam> findTmsMdProjectByParams(Map params) {
		if (params == null) {
			params = new HashMap();
		}

		return null;
	}


	public TmsMdProject convertTmsMdProjectInParamToEntity(TmsMdProjectInParam inParam){
		TmsMdProject entity=new TmsMdProject();
		ReflectUtils.copyProperties(inParam, entity);
		return entity;
	}

	@Override
	public void removeTmsMdProjects(String projectId) {
		tmsMdProjectDao.removeTmsMdProjects(projectId);;
	}

}
