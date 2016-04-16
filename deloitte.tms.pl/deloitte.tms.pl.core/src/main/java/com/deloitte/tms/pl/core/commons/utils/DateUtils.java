package com.deloitte.tms.pl.core.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

import org.apache.commons.lang.math.NumberUtils;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.exception.DataConvertException;
import com.deloitte.tms.pl.core.model.impl.CommisionMonth;

/**
 * 日期工具类
 * 
 * @author dada
 */
public class DateUtils {
	public static final Long ONE_DAY = 1000L * 60 * 60 * 24;
	public static final String ISO_DATE_FORMAT = "yyyy-MM-dd";
	public static final String ISO_DATETIME_FORMAT1 = "yyyy-MM-dd HH:mm:ss";
	public static final String ISO_DATETIME_FORMAT2 = "yyyy-MM-dd HH:mm:ss.SSS";
	private static String DATE_FORMAT_4 = "HH:mm:ss";

	private static int DATE_FORMAT_LEN = ISO_DATE_FORMAT.length();
	private static int DATE_FORMAT_1_LEN = ISO_DATETIME_FORMAT1.length();
	private static int DATE_FORMAT_2_LEN = ISO_DATETIME_FORMAT2.length();
	private static int DATE_FORMAT_4_LEN = DATE_FORMAT_4.length();
	/**
	 * <pre>
	 *   根据指定的日期格式格式化指定的日期
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *   String time = &quot;20080404&quot;;
	 *   Date date = DateUtils.parseTime(time, &quot;yyyyMMdd&quot;);
	 *   
	 *   例如时间为20080404，
	 *   则转化后为2008年的4月4日。
	 *   
	 * </pre>
	 * 
	 * @param time
	 * @param pattern
	 * @return
	 * @author dada
	 */
	public static Date parseTime(String time, String pattern) {
		try {
			return new SimpleDateFormat(pattern).parse(time);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * <pre>
	 *   根据指定的日期格式格式化指定的日期
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *   Date date = new Date();
	 *   String str = DateUtils.formatTime(date, &quot;yyyy-MM-dd&quot;);
	 *   
	 *   例如时间为2008年4月4日，
	 *   则转化后为2008-04-04。
	 *   
	 *   返回字符串对象为任意指定格式的字符串。
	 * </pre>
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 * @author dada
	 */
	public static String formatTime(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat(pattern).format(date);
	}

	public static String formatTime(Date date) {
		return formatTime(date, "yyyy-MM-dd");
	}
	// 取得月底时间
	public static String formatMonthEndTime(Date date) {
		Calendar d = Calendar.getInstance();
		d.setTime(date);
		d.set(d.get(Calendar.YEAR), d.get(Calendar.MONTH)+1, 1);
		d.add(Calendar.DAY_OF_MONTH, -1);
		return formatTime(d.getTime(), "yyyy-MM-dd");
	}

	public static Date formatMonthEndDate(Date date) {
		if (date == null) {
			date = new Date();
		}
		Calendar d = Calendar.getInstance();
		d.setTime(date);
		d.set(d.get(Calendar.YEAR), d.get(Calendar.MONTH)+1, 1);
		d.add(Calendar.DAY_OF_MONTH, -1);
		return formatDate(d.getTime(), "yyyy-MM-dd");
	}
	public static Date formatMonthEndDate(String date, String pattern) {
		Date dateMonth = parse(date,pattern);
		Calendar d = Calendar.getInstance();
		d.setTime(dateMonth);
		d.set(d.get(Calendar.YEAR), d.get(Calendar.MONTH)+1, 1);
		d.add(Calendar.DAY_OF_MONTH, -1);
		return formatDate(d.getTime(), "yyyy-MM-dd");
	}
	
	/**
	 * 则返回日期的前【参数month】个月的日期
	 * @param date
	 * @return
	 * @author ya.zhao
	 */
	public static Date getDateByMonth(Date date, int month) {
		Calendar d = Calendar.getInstance();
		d.setTime(date);
		d.set(d.get(Calendar.YEAR), d.get(Calendar.MONTH), 1);
		d.add(Calendar.DAY_OF_MONTH, -month);
		return d.getTime();
	}
		
	/**
	 * 去掉日期的时、分、秒，毫秒如果没有指定日期，则返回当前日期
	 * 
	 * @param date
	 * @return
	 * @author ya.zhao
	 */
	public static Date getSimpleDateNoNanos(Date date) {
		if (date == null) {
			date = new Date();
		}
		return formatDate(date);
	}
	/**
	 * <pre>
	 *   根据指定的日期格式格式化现在的时间
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *   String str = DateUtils.formatCurrTime(&quot;yyyy-MM-dd&quot;);
	 *   
	 *   例如现在为2008年4月4日，
	 *   则转化后为2008-04-04。
	 *   
	 *   与formatTime()方法不同的是此方法返回当前事件的格式化字符串。
	 * </pre>
	 * 
	 * @param pattern
	 * @return
	 * @author dada
	 */
	public static String formatCurrTime(String pattern) {
		Date date = new Date();
		return formatTime(date, pattern);
	}

	/**
	 * <pre>
	 *   格式化时间为0分0秒
	 *   
	 *   此方法常用在处理yyyy-MM-dd HH格式的时间上，
	 *   使用此方法可以将时间的分和秒都抹去。
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *   Date date = new Date();
	 *   Date new = DateUtils.parseDate(date);
	 *   
	 *   例如传入时间是2008年4月4日4时44分44秒，
	 *   转化后为2008年4月4日4时0分0秒.
	 *   
	 * </pre>
	 * 
	 * @return
	 * @author dada
	 */
	public static Date parseDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		Calendar retval = Calendar.getInstance();
		retval.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		return retval.getTime();
	}
	
	/**
	 * <pre>
	 *   返回该月可能的最大日期。
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *   Date date = new Date();
	 *   Date new = DateUtils.actualMaximumDate(date);
	 *   
	 *   例如传入的日期为2008年4月1日，
	 *   则返回的日期是2008年4月30日。
	 *   
	 * </pre>
	 * 
	 * @param date
	 * @return
	 * @author dada
	 */
	public static Date actualMaximumDate(Date date) {
		Calendar calendar = calendar(date);
		int actualMaximumDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, actualMaximumDay);
		return DateUtils.formatDate(calendar.getTime());
	}

	/**
	 * <pre>
	 *   返回该月可能的最小日期。
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *   Date date = new Date();
	 *   Date new = DateUtils.actualMinimumDate(date);
	 *   
	 *   例如传入的日期为2008年4月20日，
	 *   则返回的日期是2008年4月1日。
	 *   
	 * </pre>
	 * 
	 * @param date
	 * @return
	 * @author dada
	 */
	public static Date actualMinimumDate(Date date) {
		Calendar calendar = calendar(date);
		int actualMininumDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, actualMininumDay);
		return DateUtils.formatDate(calendar.getTime());
	}

	/**
	 * <pre>
	 *   返回指定月数前最大的日期。
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *   Date date = new Date();
	 *   Date new = DateUtils.actualMinimumDate(date, 1);
	 *   
	 *   例如传入的日期为2008年4月20日，
	 *   则返回的日期时2008年3月31日。
	 *   
	 * </pre>
	 * 
	 * @param date
	 * @param month
	 * @return
	 * @author dada
	 */
	public static Date actualMaximumDate(Date date, int month) {
		Calendar calendar = calendar(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - month);
		int actualMininumDay = calendar
				.getActualMaximum((Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.DAY_OF_MONTH, actualMininumDay);
		return DateUtils.formatDate(calendar.getTime());
	}

	/**
	 * <pre>
	 *   返回指定月数前最小的日期。
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *   Date date = new Date();
	 *   Date new = DateUtils.actualMinimumDate(date, 1);
	 *   
	 *   例如传入的日期为2008年4月20日，
	 *   则返回的日期时2008年3月1日。
	 *   
	 * </pre>
	 * 
	 * @param date
	 * @param month
	 * @return
	 * @author dada
	 */
	public static Date actualMinimumDate(Date date, int month) {
		Calendar calendar = calendar(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - month);
		int actualMininumDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, actualMininumDay);
		return DateUtils.formatDate(calendar.getTime());
	}

	/**
	 * <pre>
	 *   返回给定月的上一个月(默认为上一个月的第一天)
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *   Date date = new Date();
	 *   Date new = DateUtils.lastMonth(date);
	 *   
	 *   例如传入的日期为2008年4月20日，
	 *   则返回的日期时2008年3月1日。
	 *   
	 * </pre>
	 * 
	 * @param date
	 * @return
	 * @author dada
	 */
	public static Date lastMonth(Date date) {
		return actualMinimumDate(date, 1);
	}

	/**
	 * <pre>
	 *   返回给定月的下一个月(默认为下一个月的最后一天)
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *   Date date = new Date();
	 *   Date new = DateUtils.nextMonth(date);
	 *   
	 *   例如传入的日期为2008年4月20日，
	 *   则返回的日期时2008年5月31日。
	 *   
	 * </pre>
	 * 
	 * @param date
	 * @return
	 * @author dada
	 */
	public static Date nextMonth(Date date) {
		return actualMaximumDate(date, -1);
	}

	/**
	 * 获取制定日期的月数差
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 * @author dada
	 */
	public static int monthBetween(Date startDate, Date endDate) {
		int months = 0;
		Calendar startDay = calendar(startDate);
		Calendar endDay = calendar(endDate);

		int startYear = startDay.get(Calendar.YEAR);
		int startMonth = startDay.get(Calendar.MONTH);

		int endYear = endDay.get(Calendar.YEAR);
		int endMonth = endDay.get(Calendar.MONTH);

		months = (endYear - startYear) * 12 + (endMonth - startMonth);
		return months;
	}
	public static int daysBetween(Date begdate,Date endDate){  
		begdate=formatDate(begdate); 
		endDate=formatDate(endDate); 
        Calendar cal = Calendar.getInstance();    
        cal.setTime(begdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(endDate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
       return Integer.parseInt(String.valueOf(between_days));     
    }  
	/**
	 * 判断日期是否季度末
	 * 
	 * @param date
	 * @return
	 * @author dada
	 */
	public static boolean seasonEnd(Date date) {
		Calendar calendar = calendar(date);

		int month = calendar.get(Calendar.MONTH);
		return (month + 1) % 3 == 0;
	}

	/**
	 * 判断日期是否半年末
	 * 
	 * @param date
	 * @return
	 * @author dada
	 */
	public static boolean halfYearEnd(Date date) {
		Calendar calendar = calendar(date);

		int month = calendar.get(Calendar.MONTH);
		return (month + 1) % 6 == 0;
	}

	/**
	 * 判断日期是否年末
	 * 
	 * @param date
	 * @return
	 * @author dada
	 */
	public static boolean yearEnd(Date date) {
		Calendar calendar = calendar(date);

		int month = calendar.get(Calendar.MONTH);
		return (month + 1) % 12 == 0;
	}
	public static Date getFirstDayOfYear(Date date) {
		return parse(getYear(date)+"-01-01", "yyyy-MM-dd");
	}
	public static Date getLastDayOfYear(Date date) {
		return getLastDay(parse(getYear(date)+"-12", "yyyy-MM"));
	}
	/**
	 * <pre>
	 *   将指定日期转换为相应的Calendar对象
	 *   
	 *   &lt;strong&gt;程序范例：&lt;/strong&gt;
	 *   Date date = new Date();
	 *   Calendar calendar = DateUtils.calendar(date);
	 *   
	 * </pre>
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar calendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 格式化日期，去除时分秒，按指定格式处理日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date formatDate(Date date, String pattern) {
		Date formatDate = null;
		if (date != null) {
			String dateStr = formatTime(date);
			formatDate = parseTime(dateStr, pattern);
		}
		return formatDate;
	}

	/**
	 * 格式化日期，去除时分秒，只保留yyyy-MM-dd格式
	 * 
	 * @param date传入日期
	 * @return
	 */
	public static Date formatDate(Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}
	/**
	 * 格式化日期，去除时分秒，只保留yyyy-MM格式
	 * 
	 * @param date传入日期
	 * @return
	 */
	public static Date formatDateMonth(Date date) {
		return formatDate(date, "yyyy-MM");
	}
	private static String defaultDatePattern = "yyyy-MM-dd";

	/**
	 * 返回一个指定日期的Calendar实例
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar getCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 返回当天的Calendar实例
	 * 
	 * @return
	 */
	public static Calendar getCurrentCalendar() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * 返回当前日期
	 * 
	 * @return date 当前日期
	 */
	public static Date getCurrentTime() {
		return new Date();
	}

	/**
	 * 按照默认格式返回当前日期
	 * 
	 * @return date 当前日期
	 */
	public static String getCurrentTimeByDefaultPattern() {
		return new SimpleDateFormat(defaultDatePattern).format(new Date());
	}

	/**
	 * 返回指定日期的默认格式字符串输出
	 * 
	 * @param date
	 *            指定日期
	 * @return
	 */
	public static String getTimeByDefaultPattern(Date date) {
		return new SimpleDateFormat(defaultDatePattern).format(date);
	}

	/**
	 * 返回指定日期的默认格式字符串输出
	 * 
	 * @param date
	 *            指定日期
	 * @return
	 */
	public static String getTimeByCustomPattern(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 按照自定义格式返回当前日期
	 * 
	 * @return date 当前日期
	 */
	public static String getCurrentTimeByCustomPattern(String pattern) {
		return new SimpleDateFormat(pattern).format(new Date());
	}

	/**
	 * 判断日期是否属于自然季度的末尾月
	 * 
	 * @param date
	 *            日期
	 * @return
	 */
	public static boolean isEndOfSeason(Date date) {
		Calendar calendar = getCalendar(date);
		int month = calendar.get(Calendar.MONTH);
		if (month % 3 == 2) {
			return true;
		}
		return false;
	}

	/**
	 * 返回月
	 * 
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar calendar = getCalendar(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 返回指定日期+1
	 * 
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar calendar = getCalendar(date);
		return calendar.get(Calendar.DATE) + 1;
	}
	

	/**
	 * 新加的方法，返回指定日期当天
	 * 
	 * @param date
	 * @return
	 */
	public static int getNeyDay(Date date) {
		Calendar calendar = getCalendar(date);
		return calendar.get(Calendar.DATE);
	}

	/**
	 * 返回年
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar calendar = getCalendar(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 返回某月的天数
	 * 
	 * @return
	 */
	public static int getMonthLength(Date date) {
		Calendar calendar = getCalendar(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回该月可能的最大日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getActualMaximumDate(Date date) {
		Calendar calendar = getCalendar(date);
		int actualMaximumDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, actualMaximumDay);
		return calendar.getTime();
	}

	public static String getDateSQL(Date date) {
		return "to_date('" + getTimeByCustomPattern(date, "yyyyMMdd") + "','yyyyMMdd') ";
	}

	public static String getDateSetSQL(List dates) {

		StringBuffer sb = new StringBuffer();
		for (Iterator it = dates.iterator(); it.hasNext();) {
			Date date = (Date) it.next();

			sb.append(getDateSQL(date));
			sb.append(",");
		}

		int end = sb.length() - 1;

		return sb.substring(0, end);
	}

	/**
	 * 获得date前month个月的月底
	 * 
	 * @param date
	 * @param month
	 * @return
	 */

	public static Date getActualMaximumDate(Date date, int month) {
		Calendar calendar = getCalendar(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - month);
		int actualMaximumDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, actualMaximumDay);
		return calendar.getTime();

	}

	/**
	 * 返回该月可能的最小日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getActualMinimumDate(Date date) {
		Calendar calendar = getCalendar(date);
		int actualMininumDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, actualMininumDay);
		return calendar.getTime();
	}

	/**
	 * 获得date前month个月的1日
	 * 
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date getActualMinimumDate(Date date, int month) {
		Calendar calendar = getCalendar(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - month);
		int actualMininumDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, actualMininumDay);
		return calendar.getTime();
	}

	/**
	 * 获得date当月的指定的某一天
	 * 
	 * @param date
	 * @param indexDay
	 * @return
	 */
	public static Date getSpecifyDate(Date date, int indexDay) {
		Calendar calendar = getCalendar(date);
		int actualMininumDay = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, actualMininumDay + indexDay - 1);
		return calendar.getTime();
	}
	
	/**
	 * 获取几天以后的日期
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getSomeDaysLater(Date date ,int days){
		Calendar calendar = getCalendar(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}
	
	/**
	 * 获得 某个月后(前) 的特定某一天
	 * @param date
	 * @param intervalMonth
	 * @param indexDay
	 * @return
	 */
	public static Date getSomeDate(Date date ,int months,int indexDay){
		Calendar calendar = getCalendar(date);
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, (month + months));
		
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		if(indexDay > maxDay){
			indexDay = maxDay;
		}
		
		calendar.set(Calendar.DAY_OF_MONTH, indexDay);
		return calendar.getTime();
	}

	/**
	 * 将object转换为date
	 * 
	 * @param object
	 * @return
	 */
	public static Date convertToDate(Object object) {
		if (object instanceof Date) {
			return (Date) object;
		} else if (object instanceof String) {
			Long temp = StringUtils.convertToLong((String) object);
			if (temp != null) {
				return new Date(temp.longValue());
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 将日期转换成YearMonth <br>
	 * pattern : yyyyMM
	 * 
	 * @param date
	 */
	public static String getYearMonth(Date date) {
		return new SimpleDateFormat("yyyyMM").format(date);
	}

	/**
	 * 获取上个月的日期对象(上个月1号)
	 * 
	 * @param date
	 */
	public static Date getPriorMonthDate(Date date) {
		Calendar calendar = getCalendar(date);
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		Calendar newCalendar = Calendar.getInstance();
		if (month == 0) {
			year--;
			month = 11;
		} else {
			month--;
		}
		newCalendar.set(year, month, 1);
		return newCalendar.getTime();
	}

	/**
	 * @param 获取传过来的月份的上一个季度末的日期
	 * @param date
	 * @return Date
	 */
	public static Date getpriorMonthDateByMonth(Date date) {
		Calendar calendar = getCalendar(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		Calendar newCalendar = Calendar.getInstance();
		if (month == 4 || month == 5 || month == 6) {
			newCalendar.set(year, 3, 0);
			return newCalendar.getTime();
		} else if (month == 7 || month == 8 || month == 9) {
			newCalendar.set(year, 6, 0);
			return newCalendar.getTime();
		} else if (month == 10 || month == 11 || month == 12) {
			newCalendar.set(year, 9, 0);
			return newCalendar.getTime();
		} else {
			year--;
			newCalendar.set(year, 12, 0);
			return newCalendar.getTime();
		}
	}

	public static Date getNextSeason(Date date) {
		Calendar calendar = getCalendar(date);
		Calendar newCalendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);

		int newMonth = month + 4;

		newCalendar.set(year, newMonth, 0);
		Date nextSeasonFirstDay = newCalendar.getTime();
		return getActualMaximumDate(nextSeasonFirstDay);
	}

	public static Date getNextYear(Date date) {
		Calendar calendar = getCalendar(date);
		Calendar newCalendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DATE);

		int newyear = year + 1;

		newCalendar.set(newyear, month, day);
		Date nextYearDay = newCalendar.getTime();
		getSimpleDate(nextYearDay);
		return nextYearDay;
	}

	/**
	 * jun.cao 绩效津贴中结算月的算法
	 * 
	 * @param date
	 * @return date
	 */
	public static Date getPerforMancebonusMonthDateByMonth(Date date) {
		Calendar calendar = getCalendar(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		Calendar newCalendar = Calendar.getInstance();
		if (month == 3 || month == 4 || month == 5) {
			newCalendar.set(year, 3, 0);
			return newCalendar.getTime();
		} else if (month == 6 || month == 7 || month == 8) {
			newCalendar.set(year, 6, 0);
			return newCalendar.getTime();
		} else if (month == 9 || month == 10 || month == 11) {
			newCalendar.set(year, 9, 0);
			return newCalendar.getTime();
		} else {
			year--;
			newCalendar.set(year, 12, 0);
			return newCalendar.getTime();
		}

	}

	/**
	 * 去掉日期的时、分、秒，如果没有指定日期，则返回当前日期
	 * 
	 * @param date
	 * @return
	 * @author ya.zhao
	 */
	public static Date getSimpleDate(Date date) {
		if (date == null) {
			date = new Date();
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date getLastMonth(Date date) {
		Calendar calendar = getCalendar(date);
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static Date getNextMonth(Date date) {
		Calendar calendar = getCalendar(date);
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month + 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return getActualMaximumDate(calendar.getTime());
	}

	public static Date parse(String str) throws ParseException {
		return parse(str, defaultDatePattern);
	}

	public static Date parse(String str, String pattern) {
		try {
			return new SimpleDateFormat(pattern).parse(str);
		} catch (ParseException e) {
			throw new RuntimeException("日期格式转换错误", e);
		}
	}

	/**
	 * 获得前months月的最后一天
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date parse(Date date, int months) {
		String str = getTimeByCustomPattern(date, "yyyy-MM");
		Date d = parse(str, "yyyy-MM");
		Calendar calendar = getCalendar(d);
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, (month - months));
		return calendar.getTime();
	}

	/**
	 * 获得前months月的最后一天
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date getLastDay(Date date, int months) {
		Calendar calendar = getCalendar(date);
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, (month - months));
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return getActualMaximumDate(calendar.getTime());
	}

	/**
	 * 获取前N个月的最的一天数组
	 * 
	 * @param comDate
	 * @param months
	 * @return
	 */
	public static Date[] getDateArray(Date comDate, int months) {
		Date[] comDates = new Date[months];
		for (int i = 0; i < months; i++) {
			comDates[i] = getLastDay(comDate, i);
		}
		return comDates;
	}

	/**
	 * 获得前months月的最后一天
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date getFirstDay(Date date, int months) {
		Calendar calendar = getCalendar(date);
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, (month - months));
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		return getActualMinimumDate(calendar.getTime());

	}

	/**
	 * 两个月间隔的月份
	 * 
	 * @param beginDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return
	 */
	public static int getInterval(Date beginDate, Date endDate) {
		int b = getMonth(beginDate);
		int e = getMonth(endDate);
		int by = getYear(beginDate);
		int ey = getYear(endDate);
		return e - b + 12 * (ey - by);
	}

	public static long getDayInterval(Date beginDate, Date endDate) {
		String beginStr = getTimeByCustomPattern(beginDate, defaultDatePattern);
		String endStr = getTimeByCustomPattern(endDate, defaultDatePattern);
		long begin = parse(beginStr, defaultDatePattern).getTime();
		long end = parse(endStr, defaultDatePattern).getTime();
		long days = (end - begin) / (long) (1000 * 3600 * 24);
		if (days < 0) {
			days = 0;
		}
		return days;
	}

	/**
	 * 返回前一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getPreviousDay(Date date) {
		Calendar calendar = getCalendar(date);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DATE, day - 1);
		return calendar.getTime();
	}

	public static boolean isEndQuarter(Date date) {
		boolean retval = false;
		Calendar calendar = getCalendar(date);
		int month = calendar.get(Calendar.MONTH);
		if ((month + 1) % 3 == 0) {
			retval = true;
		}
		return retval;
	}

	public static boolean isMidYear(Date date) {
		boolean retval = false;
		Calendar calendar = getCalendar(date);
		int month = calendar.get(Calendar.MONTH);
		if ((month + 1) % 6 == 0) {
			retval = true;
		}
		return retval;
	}

	public static boolean isEndYear(Date date) {
		boolean retval = false;
		Calendar calendar = getCalendar(date);
		int month = calendar.get(Calendar.MONTH);
		if ((month + 1) % 12 == 0) {
			retval = true;
		}
		return retval;
	}

	public static boolean isStartYear(Date date) {
		boolean retval = false;
		Calendar calendar = getCalendar(date);
		int month = calendar.get(Calendar.MONTH);
		if (month == 0) {
			retval = true;
		}
		return retval;
	}

	/**
	 * 日期型转换为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String convertToString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	/**
	 * java.util.date 转换为 java.sql.date
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Date convertToSqlDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * 
	 * @return
	 */
	public static String getCurrentTimeByFullPattern() {
		return new SimpleDateFormat(DateUtils.getDateTimePattern()).format(new Date());
	}

	public static String getDateTimePattern() {
		return defaultDatePattern + " HH:mm:ss.SSS";
	}

	/**
	 * Long形字符串转化日期
	 * 
	 * @param exeDate
	 * @return
	 */
	public static String convertToDateString(Object exeDate) {
		String exeDateStr = "";
		if (exeDate != null && exeDate instanceof String) {
			long dataValue = Long.parseLong((String) exeDate);
			Date date = new Date(dataValue);
			exeDateStr = DateUtils.getTimeByCustomPattern(date, "yyyy-MM-dd");
		}
		return exeDateStr;
	}

	/**
	 * 获得当前月到前yearSize年的月份
	 * 
	 * @param yearSize
	 *            年数
	 * @return 月份的集合
	 */
	public static List getAllMonthsEndToday(int yearSize) {
		String pattern = "yyyy-MM";
		List monthsList = new ArrayList();
		int i = 1;
		Date priorDate = new Date();

		while (i <= yearSize * 12) {
			CommisionMonth cm = new CommisionMonth();
			cm.setCommisionMonth(DateUtils.getTimeByCustomPattern(priorDate, pattern));
			monthsList.add(cm);
			priorDate = DateUtils.getPriorMonthDate(priorDate);
			i++;
		}
		return monthsList;
	}

	/**
	 * 获得指定日期的前days天日期
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getPriorDay(Date date, int days) {
		long curTime = date.getTime();
		long priorTime = curTime - 1000 * 60 * 60 * 24 * days;
		return new Date(priorTime);
	}

	/**
	 * 获得指定日期的前hours小时日期
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getPriorHour(Date date, int hours) {
		long curTime = date.getTime();
		long priorTime = curTime - 1000 * 60 * 60 * hours;
		return new Date(priorTime);
	}

	/**
	 * 返回指定日期的下一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
		return calendar.getTime();
	}

	/**
	 * 返回指定日期的前一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 1);
		return calendar.getTime();
	}

	/**
	 * 获得当月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfThisMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 获得上月的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfLastMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 返回当月的最后一天
	 * 
	 * @param strDate
	 *            格式（'YYYY-MM'）
	 * @return
	 */
	public static Date getLastDayOfThisMonth(String strDate) throws BusinessException {

		try {
			Date date = new SimpleDateFormat("yyyy-MM").parse(strDate.trim());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.MONTH, (calendar.get(Calendar.MONTH) + 1));

			calendar.set(Calendar.DAY_OF_YEAR, (calendar.get(Calendar.DAY_OF_YEAR) - 1));
			return calendar.getTime();
		} catch (ParseException e) {
			throw new BusinessException("日期格式错误！");
		}

	}

	/**
	 * 返回当月的最后一天
	 * 
	 * @param strDate
	 *            格式（'YYYY-MM'）
	 * @return
	 */
	public static Date getLastDayOfThisMonth(Date date) throws BusinessException {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, (calendar.get(Calendar.MONTH) + 1));

		calendar.set(Calendar.DAY_OF_YEAR, (calendar.get(Calendar.DAY_OF_YEAR) - 1));
		return calendar.getTime();

	}

	/**
	 * 判断日期是否为下半月（16日以后）
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isSecondHalfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if (day > 15) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获得前months月的最后一天
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date getMaxDate(Date date, int months) {
		String str = getTimeByCustomPattern(date, "yyyy-MM");
		Date d = parse(str, "yyyy-MM");
		Calendar calendar = getCalendar(d);
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, (month - months));
		return getActualMaximumDate(calendar.getTime());
	}

	/**
	 * 获得后months月的日期 kaka.gulin | 2009-03-31
	 */
	public static Date getMonths(Date date, int months) {
		Calendar calendar = getCalendar(date);
		Calendar newCalendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);

		int newMonth = month + months;

		newCalendar.set(year, newMonth, 0);
		Date nextSeasonFirstDay = newCalendar.getTime();
		return getActualMaximumDate(nextSeasonFirstDay);
	}

	/**
	 * 获得MONTHS月后的最大日期 kaka.gulin | 2009-04-07
	 */
	public static Date getNextMonthByNewWay(Date date, int months) {
		Calendar calendar = getCalendar(date);
		int month = calendar.get(Calendar.MONTH);
		calendar.set(Calendar.MONTH, month + months);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return getActualMaximumDate(calendar.getTime());
	}

	/**
	 * 根据起始日期,结束日期获取这个时间段中所有的日期
	 * 
	 * @param startDate
	 *            起始月
	 * @param endDate
	 *            结束月
	 * @return 这个时间段中每个月最大天的时间数组
	 */
	public static List getBetweenMonths(Date startDate, Date endDate) {
		if (startDate == null) {
			ExceptionHelper.throwBusinessException("起始时间不能为空!");
		}

		if (endDate == null) {
			ExceptionHelper.throwBusinessException("截止时间不能为空!");
		}
		if (startDate.getTime() > endDate.getTime()) {
			ExceptionHelper.throwBusinessException("起始时间不能大于结束时间!");
		}

		startDate = getActualMaximumDate(startDate);
		endDate = getActualMaximumDate(endDate);

		List months = new ArrayList();

		Date currentDate = startDate;
		while (currentDate.getTime() <= endDate.getTime()) {
			months.add(getActualMaximumDate(currentDate));
			currentDate = getNextMonth(currentDate);
		}

		return months;
	}

	public static List getBetweenMonthsExceptStartDate(Float months, Date date) {
		Date startDate = getMaxDate(date, months.intValue() - 1);
		return getBetweenMonths(startDate, date);
	}

	public static void main(String[] args) {
//		Date date = getSomeDaysLater(new Date(), 4);
//		Date date0 = getSomeDaysLater(new Date(), -4);
//		String date1= getTimeByDefaultPattern(date);
//		String date2 = getTimeByDefaultPattern(date0);
//		System.out.println(date1);
//		System.out.println(date2);
//		String[] gridColnums=new String[]{"201601201601","201601201602","201602201602"};
//		String[] gridColnums=new String[]{"201601201601"};
//		String[] gridColnums=new String[]{"201601201601","201601201602"};
//		String pre_payrolldate=null;
//		String pre_colnum=null;
//		for(int i=0;i<gridColnums.length;i++){		
//			String colnum_str=gridColnums[i];
//			String payrolldate=colnum_str.substring(0, 6);
//			String processdate=colnum_str.substring(6, 12);
//			if(pre_payrolldate!=null&&!payrolldate.equals(pre_payrolldate)){
//				System.out.println(pre_colnum);
//			}else {
//				
//			}
//			pre_payrolldate=payrolldate;
//			pre_colnum=colnum_str;
//			if(i==gridColnums.length-1){//最后一个归属期等于,上一个就没添加,不等于,上一个已经添加,所以最后一个必定添加
//				System.out.println(colnum_str);
//			}
//		}
		//System.out.println(System.currentTimeMillis());; 
	}
	public static Date getPrevMonth(Date date) {
		Calendar calendar = getCalendar(date);
		calendar.add(Calendar.DAY_OF_MONTH, -calendar.get(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}
	/**
	 * 获取日期差返回日期List
	 * @author 周栋
	 * 2012-6-11
	 * @param begDate 起始月
	 * @param endDate 终止月
	 * @return
	 */
	public static List getDate(Date begDate, Date endDate) {
		int betweenMonth = monthBetween(begDate, endDate);
		int begMonth = getMonth(begDate);
		int years = getYear(begDate);//年
		String dates = "";
		String months = "";
		int month = 0; //月
		int year = 12;//计算月份用 年
		List result = new ArrayList();
		for (int i = begMonth; i <= begMonth + betweenMonth; i++) {
			month = i;//从当前月开始
			if (i > 12) {
				month = i - year;//大于12,重置为1,起始年加1
				if (month == 1) {
					years += 1;
				}
				if (month >= 12) {
					year += 12;//增加1年,为计算月份用
				}
			}
			if (month < 10) {
				months = "-0" + month;
			} else {
				months = "-" + month;
			}
			dates = years + months + "-01";
			result.add(DateUtils.actualMaximumDate(DateUtils.parse(dates, "yyyy-MM-dd")));
		}
		return result;
	}
	/** 
	  *   得到给定日期的第几月的日期 
	  *   @author 李敏   2012-8-20
	  *   @param  date 给定日期
	  *   @param  iMonth 月 数
	  *   @return 
	  */ 
	public static Date getDateAfterMonth(Date date, int iMonth) {
		Date dToday = date;
		Calendar todayCalendar = Calendar.getInstance();
		todayCalendar.setTime(dToday);
		todayCalendar.add(Calendar.MONTH, iMonth);
		return todayCalendar.getTime();
	}
	/**
	 * 获得MONTHS月后的最大日期 kaka.gulin | 2009-04-07
	 */
	public static Date addMonth(Date date, int months) {
		Calendar calendar = getCalendar(date);
		calendar.add(Calendar.MONTH, months);
		return getActualMaximumDate(calendar.getTime());
	}
	public static Date addDay(Date date, int day) {
		Calendar calendar = getCalendar(date);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}
	public static Date addYear(Date date, int year) {
		Calendar calendar = getCalendar(date);
		calendar.add(Calendar.YEAR, year);
		return calendar.getTime();
	}
	/**
	 * json来源的日期格式化
	 */
	public static Date toDate(Object value) {
		if (value == null) {
			return null;
		} else {
			if (value instanceof Date) {
				return (Date) value;
			} else if (value instanceof String) {
				return convertText2Date((String) value);
			} else if (value instanceof Long) {
				return new Date(((Long) value).longValue());
			} else {
				throw new DataConvertException(value.getClass(), Date.class);
			}
		}
	}
	/**
	 * 尝试将一段文本转换成日期对象。
	 * 
	 * @param text
	 *            文本
	 * @return 转换得到的日期对象
	 * @throws ValueConvertException
	 * @throws NumberFormatException
	 */
	public static Date convertText2Date(String text) throws DataConvertException,
			NumberFormatException {
		if (NumberUtils.isNumber(text)) {
			long time = Long.parseLong(text);
			return new Date(time);
		} else {
			try {
				Date date = null;
				int len = text.length();
				if (len == DATE_FORMAT_LEN) {
					date = DateUtils
							.parse(text,ISO_DATE_FORMAT);
				} else if (len == DATE_FORMAT_2_LEN) {
					date = DateUtils
							.parse(text,ISO_DATETIME_FORMAT2);
				} else if (len == DATE_FORMAT_1_LEN) {
					date = DateUtils
							.parse(text,ISO_DATETIME_FORMAT1);
				} else if (len == DATE_FORMAT_4_LEN) {
					date = DateUtils.parse(text,DATE_FORMAT_4);
				}
				if (date == null) {
					date = DateUtils.parse(text);
				}
				return date;
			} catch (ParseException ex) {
				throw new DataConvertException(text, Date.class);
			}
		}
	}
	private static final TimeZone GMT = TimeZone.getTimeZone("GMT");

	private DateUtils() {
	}

	public static TimeZone getGMTTimeZone() {
		return GMT;
	}

	private static TimeZone getDefaultTimeZone() {
//		return (Configure.getBoolean("core.useGMTTimeZone")) ? GMT : null;
		return GMT;
	}

	public static String format(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		TimeZone timeZone = getDefaultTimeZone();
		if (timeZone != null) {
			sdf.setTimeZone(timeZone);
		}
		return sdf.format(date);
	}

	public static String format(String format, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
//		TimeZone timeZone = getDefaultTimeZone();
//		if (timeZone != null) {
//			sdf.setTimeZone(timeZone);
//		}
		return sdf.format(date);
	}
}
