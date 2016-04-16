package com.deloitte.tms.base.taxsetting.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.base.taxsetting.dao.TmsMdContractDao;
import com.deloitte.tms.base.taxsetting.model.TaxRuleAndRateInParam;
import com.deloitte.tms.base.taxsetting.model.TmsMdContract;
import com.deloitte.tms.base.taxsetting.model.TmsMdContractInParam;
import com.deloitte.tms.base.taxsetting.service.TmsMdContractService;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;

@Component(TmsMdContractService.BEAN_ID)
public class TmsMdContractServiceImpl extends BaseService implements
		TmsMdContractService {
	@Resource
	private TmsMdContractDao tmsMdContractDao;

	@Override
	public IDao getDao() {
		return tmsMdContractDao;
	}

	public DaoPage findTmsMdContractByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if (params == null) {
			params = new HashMap();
		}
		DaoPage daoPage = tmsMdContractDao.findTmsMdContractByParams(params,
				pageIndex, pageSize);

		return daoPage;
	}

	public List<TaxRuleAndRateInParam> findTmsMdContractByParams(Map params) {
		if (params == null) {
			params = new HashMap();
		}

		return null;
	}


	@Override
	public TmsMdContract getContractById(String id) {
		return tmsMdContractDao.getContractById(id);
	}

	@Override
	public void deleteContractById(String id) {
		tmsMdContractDao.removeTmsMdContractById(id);
	}

	public TmsMdContract convertTmsMdContractInParamToEntity(TmsMdContractInParam inParam){
		TmsMdContract entity=new TmsMdContract();
		ReflectUtils.copyProperties(inParam, entity);
		return entity;
	}

	@Override
	public void removeTmsMdContracts(String contractId) {
		tmsMdContractDao.removeTmsMdContractById(contractId);
	}

}
