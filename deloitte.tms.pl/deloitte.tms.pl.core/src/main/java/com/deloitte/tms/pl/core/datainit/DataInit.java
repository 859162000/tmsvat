package com.deloitte.tms.pl.core.datainit;

import org.hibernate.Session;

public interface DataInit {
	/**
	 * 删除旧数据
	 * @param session
	 */
	void clearData(Session session);
	/**
	 * 插入新数据
	 * @param session
	 */
	void genData(Session session);
}
