package com.deloitte.tms.pl.seq.dao.impl;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;

import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.pl.seq.dao.ISequenceDao;
import com.deloitte.tms.pl.seq.model.Sequence;

@SuppressWarnings("unchecked")
public class SequenceDaoImpl extends BaseDao implements ISequenceDao {

	/**
	 * @see com.ling2.core.dao.etl.SequenceDao#findSequenceBy(java.lang.String)
	 * @author dada
	 */
	public List<Sequence> exeSequenceBy(final String type,Long increment,int tryCount) {
		final StringBuffer queryString = new StringBuffer();

		queryString.append("select sequence")
				   .append(" from Sequence sequence")
		           .append(" where sequence.type = :type");
		try{
			Session session=getSessionFactory().getCurrentSession();
			Query query = session.createQuery(queryString.toString());
			query.setLockMode("sequence", LockMode.UPGRADE_NOWAIT);
			query.setParameter("type", type);
			return query.list();
		}catch (Exception e) {
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			return exeSequenceBy(type,increment,tryCount);
		}
	}
}
