package com.deloitte.tms.pl.core.commons.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

/**
 * 对HQL进行加工处理的工具类。
 * 
 * @author dada
 */
public class HqlUtils {

	/**
	 * <pre>
	 * 去除HQL语句前的select部分，用来生成查询总记录条数的HQL语句。
	 * 
	 * <strong>程序范例：</strong>
	 * String queryCountString = "select count(*) " + HqlUtils.removeSelect(queryString);
	 * 
	 * </pre>
	 * @param queryString
	 * @return
	 * @author dada
	 */
	public static String removeSelect(String queryString) {
		Assert.notNull(queryString);
		queryString = removeFetch(queryString);
		int beginPos = queryString.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " the hql : " + queryString + " must has a keyword 'from'");
		return queryString.substring(beginPos);
	}
	public static String assembleHql(StringBuffer queryString, List conditions) {
		Assert.notNull(queryString);
		Assert.notNull(conditions);
		for (int i = 0; i < conditions.size(); i++) {
			String condition = (String) conditions.get(i);
			if (i == 0) {
				queryString.append(" where ");
				queryString.append(condition);
			} else {
				if (condition.trim().substring(0, 5).equals("order")) {
					queryString.append(condition);
				} else if (condition.trim().substring(0, 5).equals("group")) {
					queryString.append(condition);
				} else {
					queryString.append(" and ");
					queryString.append(condition);
				}
			}
		}
		return queryString.toString();
	}
	/**
	 * <pre>
	 * 去除HQL语句后的order by部分
	 * 
	 * <strong>程序范例：</strong>
	 * queryCountString = HqlUtils.removeOrders(queryCountString);
	 * 
	 * </pre>
	 * @param queryString
	 * @return
	 * @author dada
	 */
	public static String removeOrders(String queryString) {
		Pattern pattern = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(queryString);
		StringBuffer stringBuffer = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(stringBuffer, "");
		}
		matcher.appendTail(stringBuffer);
		return stringBuffer.toString();
	}

	/**
	 * <pre>
	 * 去除HQL语句内的fetch部分
	 * 
	 * <strong>程序范例：</strong>
	 * queryString = removeFetch(queryString);
	 * 
	 * </pre>
	 * @param queryString
	 * @return
	 * @author dada
	 */
	public static String removeFetch(String queryString) {
		Assert.notNull(queryString);
		return StringUtils.remove(queryString, " fetch");
	}
	
}
