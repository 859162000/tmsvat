package com.deloitte.tms.pl.dictionary.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.pl.dictionary.dao.DictionaryManagDao;
import com.deloitte.tms.pl.dictionary.model.impl.BaseCstegory;
import com.deloitte.tms.pl.dictionary.service.DicitionsryManagService;
@Service(value="dictionarymanagserviceimpl")
public class DictionaryManagServiceImpl extends BaseService implements DicitionsryManagService{
	@Resource(name="dictionarymanagdaoimpl")
	DictionaryManagDao   dictionarymanagdaoimpl;
	@Override
	public IDao getDao() {
		// TODO Auto-generated method stub
		return dictionarymanagdaoimpl;
	}
	@Override
	public List<BaseCstegory> loadAllUrls() {
		return dictionarymanagdaoimpl.find("from BaseCstegory where 1=1");
	}
	@Override
	public void deleteDefaultUrlById(String uuid) {
		dictionarymanagdaoimpl.deleteDefaultUrlById(uuid);
	}

	
}
