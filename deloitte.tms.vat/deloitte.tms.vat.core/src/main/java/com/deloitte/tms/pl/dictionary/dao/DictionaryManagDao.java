package com.deloitte.tms.pl.dictionary.dao;

import java.util.List;

import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.dictionary.model.impl.BaseCstegory;

public interface DictionaryManagDao extends IDao {

	List<BaseCstegory> loadAllUrls();

	void deleteDefaultUrlById(String uuid);

}
