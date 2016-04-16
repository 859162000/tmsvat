package com.deloitte.tms.pl.core.service;

import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.core.model.impl.LingModelhistory;

public interface IModelHistoryService extends IService{
	List<LingModelhistory> findModelhistories(Map params);
}
