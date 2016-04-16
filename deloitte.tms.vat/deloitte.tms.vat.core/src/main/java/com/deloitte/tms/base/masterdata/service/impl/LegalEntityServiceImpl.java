package com.deloitte.tms.base.masterdata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.base.masterdata.dao.LegalEntityDao;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.service.LegalEntityService;
import com.deloitte.tms.pl.core.commons.support.DaoPage;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;

/**
 * @author tigchen
 */
@Component(LegalEntityService.BEAN_ID)
public class LegalEntityServiceImpl extends BaseService implements LegalEntityService {

	@Resource
	LegalEntityDao legalEntityDao;
	
	
	@Override
	public DaoPage loadPageTaxOrg(Class cla, int pageIndex, int pageSize) {
		
		
		try{
			
			if(legalEntityDao == null){
				System.out.println("legalEntityDao is null..........");
				return null;
			}else{
				
			}
			
			return this.legalEntityDao.loadPageTaxOrg(cla, pageIndex, pageSize);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public DaoPage loadPageTaxDepts(Class cla, Map params,int pageIndex,int pageSize) {
		return legalEntityDao.loadPageTaxDepts(cla, params, pageIndex, pageSize);
	}
	@Override
	public void saveTaxOrgWithNeedInfo(TmsMdLegalEntity entity) {
		
		convertTaxOrg(entity);
		
		entity.setFlag("1");
		this.save(entity);
	}
	

	@Override
	public void updateTaxOrgWithNeedInfo(TmsMdLegalEntity entity) {
		
		convertTaxOrg(entity);
		entity.setFlag("1");
		this.update(entity);
	}
	
	
	public void convertTaxOrg(TmsMdLegalEntity org){
		System.out.println("-----------convertTaxOrg");
		
		if(AssertHelper.empty(org)){
			return;
		}
		
		if(AssertHelper.empty(org.getIsEnablePrint())){
			org.setIsEnablePrint("0");
		}
		
		if(AssertHelper.empty(  org.getIsPaymentCollect()  )){
			org.setIsPaymentCollect("0");
		}
		
		if(AssertHelper.empty(  org.getIsVat()  )){
			org.setIsVat("0");
		}
		
	}


	@Override
	public IDao getDao() {
		// TODO Auto-generated method stub
		return this.legalEntityDao;
	}


	@Override
	public void delById(ArrayList<String> ids, Class cla) {
		// TODO Auto-generated method stub
		this.legalEntityDao.delByIdsUseFlag(ids, cla);
	}
	
	@Override
	public List<TmsMdLegalEntity> listAll(){
		
		//ArrayList<TmsMdLegalEntity> result = new ArrayList<TmsMdLegalEntity>();
		return this.legalEntityDao.getAllLegalEntities();
	}

	@Override
	public List<TmsMdLegalEntity> searchLegal(HashMap<String, Object> para) {
		// TODO Auto-generated method stub
		return legalEntityDao.searchLegal(para);
		
	}
	
	

	
}
