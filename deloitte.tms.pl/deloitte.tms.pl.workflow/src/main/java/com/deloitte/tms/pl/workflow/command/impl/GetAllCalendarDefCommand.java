package com.deloitte.tms.pl.workflow.command.impl;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.calendar.CalendarDef;
import com.deloitte.tms.pl.workflow.utils.EnvironmentUtils;

/**
 * @author Jacky.gao
 * @since 2013年9月23日
 */
public class GetAllCalendarDefCommand implements Command<Collection<CalendarDef>> {
	@SuppressWarnings("unchecked")
	public Collection<CalendarDef> execute(Context context) {
		Criteria criteria=context.getSession().createCriteria(CalendarDef.class);
		String categoryId=EnvironmentUtils.getEnvironment().getCategoryId();
		if(StringUtils.isNotEmpty(categoryId)){
			criteria.add(Restrictions.eq("categoryId", categoryId));
		}
		return criteria.list();
	}
}
