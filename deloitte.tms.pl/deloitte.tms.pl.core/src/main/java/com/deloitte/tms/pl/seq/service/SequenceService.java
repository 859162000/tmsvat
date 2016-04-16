package com.deloitte.tms.pl.seq.service;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.service.IService;


public interface SequenceService extends IService {

	/**
	 * 获取指定类型的下个值
	 * 步进默认1
	 * 
	 * @param type
	 * @return
	 */
	public Long nextVal(String type) throws BusinessException;
	
	/**
	 * 获取指定类型的下个值,可自定义步进
	 * 
	 * @param type
	 * @return
	 */
	public Long nextVal(String type, Long increment) throws BusinessException;
}
