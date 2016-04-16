package com.deloitte.tms.pl.dictionary.service;

import java.util.List;

import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.pl.dictionary.model.DictionaryEntity;

public interface DictionaryService extends IService {

	public List<DictionaryEntity> loadDictionaryEntities(String categoryCode);
	public void reloadDictionary();
	public DictionaryEntity getDictionaryEntity(String code);
}
