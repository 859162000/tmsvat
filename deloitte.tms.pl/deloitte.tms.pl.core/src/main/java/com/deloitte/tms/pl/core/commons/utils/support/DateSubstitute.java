package com.deloitte.tms.pl.core.commons.utils.support;


import java.util.ArrayList;
import java.util.List;

public class DateSubstitute {

	/** 日期SQL */
	private String dateSql;

	/** 日期参数 */
	private String dateParam;

	/** 被替代的片段 */
	private String dateReplace;

	public static List getSubstitute() {
		List list = new ArrayList();
		list.add(new DateSubstitute(".comDate = :comDate ", "comDate", ":comDate "));
		list.add(new DateSubstitute(".comDate =:comDate ", "comDate", ":comDate "));
		list.add(new DateSubstitute(".comDate= :comDate ", "comDate", ":comDate "));
		list.add(new DateSubstitute(".comDate=:comDate ", "comDate", ":comDate "));
		
		list.add(new DateSubstitute(".comdate = :comDate ", "comDate", ":comDate "));
		list.add(new DateSubstitute(".comdate =:comDate ", "comDate", ":comDate "));
		list.add(new DateSubstitute(".comdate= :comDate ", "comDate", ":comDate "));
		list.add(new DateSubstitute(".comdate=:comDate ", "comDate", ":comDate "));
		
		list.add(new DateSubstitute(".comdate = :comdate ", "comdate", ":comdate "));
		list.add(new DateSubstitute(".comdate =:comdate ", "comdate", ":comdate "));
		list.add(new DateSubstitute(".comdate= :comdate ", "comdate", ":comdate "));
		list.add(new DateSubstitute(".comdate=:comdate ", "comdate", ":comdate "));
		
		list.add(new DateSubstitute(".comDate = :date ", "date", ":date "));
		list.add(new DateSubstitute(".comDate =:date ", "date", ":date "));
		list.add(new DateSubstitute(".comDate= :date ", "date", ":date "));
		list.add(new DateSubstitute(".comDate=:date ", "date", ":date "));
		
		list.add(new DateSubstitute(".comdate = :date ", "date", ":date "));
		list.add(new DateSubstitute(".comdate =:date ", "date", ":date "));
		list.add(new DateSubstitute(".comdate= :date ", "date", ":date "));
		list.add(new DateSubstitute(".comdate=:date ", "date", ":date "));
		
		list.add(new DateSubstitute(".comDate in (:comDates) ", "comDates", ":comDates"));
		list.add(new DateSubstitute(".comDate in(:comDates) ", "comDates", ":comDates"));
		list.add(new DateSubstitute(".comDate in( :comDates) ", "comDates", ":comDates"));
		
		return list;
	}

	public DateSubstitute(String dateSql, String dateParam, String dateReplace) {
		super();
		this.dateSql = dateSql;
		this.dateParam = dateParam;
		this.dateReplace = dateReplace;
	}

	public String getDateReplace() {
		return dateReplace;
	}

	public void setDateReplace(String dateReplace) {
		this.dateReplace = dateReplace;
	}

	public String getDateSql() {
		return dateSql;
	}

	public void setDateSql(String dateSql) {
		this.dateSql = dateSql;
	}

	public String getDateParam() {
		return dateParam;
	}

	public void setDateParam(String dateParam) {
		this.dateParam = dateParam;
	}

}
