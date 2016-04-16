package com.deloitte.tms.pl.dictionary.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.pl.dictionary.dao.DictionaryManagDao;
import com.deloitte.tms.pl.dictionary.model.impl.BaseCstegory;
@Component(value="dictionarymanagdaoimpl")
public class DictionaryManagDaoImpl extends BaseDao implements DictionaryManagDao {

	@Override
	public List<BaseCstegory> loadAllUrls() {
		
		return null;
	}

	@Override
	public void deleteDefaultUrlById(String uuid) {
			// TODO Auto-generated method stub
			StringBuffer query=new StringBuffer();
			Map values=new HashMap<String, Object>();
			query.append("delete from BaseCstegory  where id=:Id ");
			values.put("Id", uuid);
			executeHqlProduce(query.toString(), values);
		}


}
