package com.deloitte.tms.pl.seq.service.impl;

import java.util.List;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.core.service.impl.BaseService;
import com.deloitte.tms.pl.seq.dao.ISequenceDao;
import com.deloitte.tms.pl.seq.model.Sequence;
import com.deloitte.tms.pl.seq.service.SequenceService;

public class SequenceServiceImpl extends BaseService implements SequenceService {

	private ISequenceDao sequenceDao;	

	public ISequenceDao getSequenceDao() {
		return sequenceDao;
	}

	public void setSequenceDao(ISequenceDao sequenceDao) {
		this.sequenceDao = sequenceDao;
	}

	/**
	 * @see com.deloitte.tms.pl.seq.service.SequenceService#nextVal(java.lang.String)
	 * @author dada
	 */
	public synchronized Long nextVal(String type) throws BusinessException {
		return nextVal(type, new Long(1));
	}

	/**
	 * @see com.deloitte.tms.pl.seq.service.SequenceService#nextVal(java.lang.String, java.lang.Long)
	 * @author dada
	 */
	public synchronized Long nextVal(String type, Long increment) throws BusinessException {
//		Long value = spExecuteJdbcDao.executeGetSequence(type, increment,1);
		List<Sequence> s = sequenceDao.exeSequenceBy(type, increment, 0);
		if(s == null || s.size() != 1){
			throw new BusinessException("sequence数据异常！");
		}
		return s.get(0).getValue();
	}

	@Override
	public IDao getDao() {
		return sequenceDao;
	}

}
