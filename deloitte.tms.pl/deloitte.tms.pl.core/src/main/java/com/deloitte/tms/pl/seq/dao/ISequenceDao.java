package com.deloitte.tms.pl.seq.dao;

import java.util.List;

import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.seq.model.Sequence;

public interface ISequenceDao extends IDao {

	/**
	 * 获取指定类型的Sequence
	 * 
	 * @param type
	 * @return
	 */
	public List<Sequence> exeSequenceBy(String type,Long increment,int tryCount);

	
}
