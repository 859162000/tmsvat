package com.deloitte.tms.pl.dictionary.service;

import java.util.List;

import com.deloitte.tms.pl.dictionary.model.DictionaryEntity;

public interface DictionaryGenerator {
	List<DictionaryEntity> getDictionaryEntities();
	String getDictType();
}
