package com.deloitte.tms.pl.workflow.console.command;

import com.deloitte.tms.pl.workflow.command.Command;
import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.calendar.CalendarDef;
import com.deloitte.tms.pl.workflow.utils.EnvironmentUtils;

/**
 * 
 * @author Jake.Wang@bstek.com
 * @since Sep 23, 2013
 * 
 */
public class SaveCalendarDefCommand implements Command<CalendarDef> {
	private CalendarDef def;

	public SaveCalendarDefCommand(CalendarDef def) {
		this.def = def;
	}

	public CalendarDef execute(Context context) {
		String categoryId=EnvironmentUtils.getEnvironment().getCategoryId();
		this.def.setCategoryId(categoryId);
		context.getSession().saveOrUpdate(this.def);
		return this.def;
	}
}
