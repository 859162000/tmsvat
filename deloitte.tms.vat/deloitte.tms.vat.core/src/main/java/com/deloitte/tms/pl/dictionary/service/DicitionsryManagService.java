package com.deloitte.tms.pl.dictionary.service;

import java.util.List;

import com.deloitte.tms.pl.core.service.IService;
import com.deloitte.tms.pl.dictionary.model.impl.BaseCstegory;

public interface DicitionsryManagService extends  IService{

	List<BaseCstegory> loadAllUrls();

	void deleteDefaultUrlById(String uuid);

}
