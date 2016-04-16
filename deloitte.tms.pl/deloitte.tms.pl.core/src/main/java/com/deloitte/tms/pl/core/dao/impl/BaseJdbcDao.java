package com.deloitte.tms.pl.core.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.deloitte.tms.pl.core.dao.ISqlDao;


public class BaseJdbcDao extends JdbcDaoSupport implements ISqlDao {
	
	public void delete(String deleteString) {
		getJdbcTemplate().execute(deleteString);
	}
	
	public void update(String string) {
		getJdbcTemplate().execute(string);
	}
	
	public List find(final String queryString) throws DataAccessException {
		List rows = getJdbcTemplate().queryForList(queryString); 	
		return rows;
	}
}
