package com.deloitte.tms.pl.workflow.process.node.calendar;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Jacky.gao
 * @since 2013年9月18日
 */
@Component(BusinessCalendar.BEAN_ID)
public class BusinessCalendar {
	public static final String BEAN_ID="uflo.businessCalendar";
	@Value("${uflo.businessDayHours}")
	private int businessDayHours;
	
	public Date calEndDate(Calendar startDate,Calendar endDate,org.quartz.Calendar baseCalendar){
		int holidayMinutes=calHolidayMinutes(startDate,endDate,baseCalendar);
		if(holidayMinutes==0){
			return endDate.getTime();
		}
		startDate.setTime(endDate.getTime());
		endDate.add(Calendar.MINUTE, holidayMinutes);
		return calEndDate(startDate,endDate,baseCalendar);
	}

	private int calHolidayMinutes(Calendar startDate,Calendar endDate,org.quartz.Calendar baseCalendar) {
		if(startDate.getTimeInMillis()==endDate.getTimeInMillis()){
			return 0;
		}
		int count=0;
		while(startDate.getTimeInMillis()<endDate.getTimeInMillis()){
			if(!baseCalendar.isTimeIncluded(startDate.getTime().getTime())){
				count++;
			}
			startDate.add(Calendar.MINUTE, 1);
		}
		return count;
	}

	public int getBusinessDayHours() {
		return businessDayHours;
	}

	public void setBusinessDayHours(int businessDayHours) {
		this.businessDayHours = businessDayHours;
	}
}
