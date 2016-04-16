/**    
 * Copyright (C),Deloitte
 * @FileName: JobDateFormatUtils.java  
 * @Package: com.deloitte.tms.vat.salesinvoice.fileutils  
 * @Description: //模块目的、功能描述  
 * @Author stonshi  
 * @Date 2016年3月22日 下午11:35:10  
 * @History: //修改记录
 * 〈author〉      〈time〉      〈version〉       〈desc〉
 * 修改人姓名            修改时间            版本号              描述   
 */

package com.deloitte.tms.vat.salesinvoice.fileutils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 〈一句话功能简述〉 功能详细描述
 * 
 * @author stonshi
 * @create 2016年3月22日 下午11:35:10
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

public class JobDateUtils {

	public static boolean isValidDate(String dateStr, String pattern) {

		SimpleDateFormat format = new SimpleDateFormat(pattern);
		boolean convertSuccess = true;

		try {
			// 设置lenient为false.
			// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(dateStr);
		} catch (Exception e) {
			convertSuccess = false;
		}
		return convertSuccess;
	}

	public static Date dateParse(String inputDate) {
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date resultDate = null;
		try {
			resultDate = format.parse(inputDate);
		} catch (ParseException e) {

		}
		return resultDate;
	}

	public static String date2String(Date date) {

		return new SimpleDateFormat(("yyyy-MM-dd")).format(date);

	}

	public static String date2YYYMMDDString(Date date) {

		return new SimpleDateFormat(("yyyyMMdd")).format(date);

	}

	public static Date dateStampParse(String inputDateStamp) {
		Date date = new Date(inputDateStamp);
		DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		String formateDate = df.format(date);
		Date resultDate = null;
		try {
			resultDate = df.parse(formateDate);
		} catch (ParseException e) {

		}
		return resultDate;
	}

	public static String genNameByCurrentTime() {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar calendar = Calendar.getInstance();
		String fileName = df.format(calendar.getTime());
		return fileName;

	}

	public static boolean isRunDayOfMonth(int dayToRun) {
		Calendar calendar = Calendar.getInstance();
		int dayOfMonth = Integer.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
		if (dayOfMonth == dayToRun) {
			return true;
		} else {
			return false;
		}
	}

	public static String genTimeStamp() {
		Timestamp stamp = new Timestamp(System.currentTimeMillis());
		String stampStr = stamp.toString();
		return stampStr + "000";
	}
}
