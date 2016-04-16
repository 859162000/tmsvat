package com.deloitte.tms.pl.security.datainit;

import org.hibernate.Session;


/**
 * @author Jacky.gao
 * @since 2013-3-20
 */
public interface MenuGenerator {
	void generate(Session session,String parentMenuId);
}
