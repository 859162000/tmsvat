package com.deloitte.tms.pl.core.commons.utils;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.deloitte.tms.pl.core.commons.support.DaoPage;

public abstract class HibernateDaoUtils {

	private static final int STRING = 0;

	private static final int BOOLEAN = 3;

	private static final int LONG = 5;

	private static final int DOUBLE = 6;

	private static final int DATE = 7;

	public static DaoPage getPage(HibernateTemplate hibernateTemplate,
			DetachedCriteria detachedCriteria, int startIndex, int maxResult) {		
		DaoPage page = new DaoPage();
		detachedCriteria.setProjection(Projections.rowCount());
		List list = hibernateTemplate.findByCriteria(detachedCriteria);
		if (!list.isEmpty()) {
			page.setRecordCount(((Integer) list.get(0)).intValue());
		}
		detachedCriteria.setProjection(null);

		List categories = hibernateTemplate.findByCriteria(detachedCriteria,
				startIndex, maxResult);
		page.setResult(categories);
		return page;
	}

	public static DaoPage getOrderedPage(HibernateTemplate hibernateTemplate,
			DetachedCriteria detachedCriteria, List orders, int startIndex,
			int maxResult) {
		DaoPage page = new DaoPage();
		detachedCriteria.setProjection(Projections.rowCount());
		List list = hibernateTemplate.findByCriteria(detachedCriteria);
		if (!list.isEmpty()) {
			page.setRecordCount(((Integer) list.get(0)).intValue());
		}
		detachedCriteria.setProjection(null);

		for (Iterator iter = orders.iterator(); iter.hasNext();) {
			Order order = (Order) iter.next();
			detachedCriteria.addOrder(order);
		}

		List categories = hibernateTemplate.findByCriteria(detachedCriteria,
				startIndex, maxResult);
		page.setResult(categories);
		return page;
	}

	public static SimpleExpression createStringCriteria(String property,
			String filterExpression) {
		if (StringUtils.isEmpty(filterExpression)) {
			return null;
		}

		SimpleExpression expression = null;
		if (filterExpression.startsWith("=")) {
			expression = Restrictions.eq(property, string2FilterValue(
					filterExpression.substring(1), STRING));
		}
		else {
			expression = Restrictions.like(property, filterExpression,
					MatchMode.ANYWHERE);
		}
		return expression;
	}

	private static Object string2FilterValue(String s, int type) {
		Object value = null;
		switch (type) {
		case BOOLEAN:
			value = Boolean.valueOf(VariantHelper.parseBoolean(s));
			break;
		case LONG:
			value = new Long(VariantHelper.parseLong(s));
			break;
		case DOUBLE:
			value = new Double(VariantHelper.parseDouble(s));
			break;
		case DATE:
			value = VariantHelper.parseDate(s);
			break;
		default:
			value = s;
			break;
		}
		return value;
	}

	public static SimpleExpression createBooleanCriteria(String property,
			String filterExpression) {
		if (StringUtils.isEmpty(filterExpression)) {
			return null;
		}
		return Restrictions.eq(property, string2FilterValue(filterExpression,
				BOOLEAN));
	}

	private static SimpleExpression createCriteria(String property,
			String filterExpression, int type) {
		if (StringUtils.isEmpty(filterExpression)) {
			return null;
		}

		SimpleExpression expression = null;
		if (filterExpression.startsWith(">")) {
			expression = Restrictions.gt(property, string2FilterValue(
					filterExpression.substring(1), type));
		}
		else if (filterExpression.startsWith(">=")) {
			expression = Restrictions.ge(property, string2FilterValue(
					filterExpression.substring(2), type));
		}
		else if (filterExpression.startsWith("<")) {
			expression = Restrictions.lt(property, string2FilterValue(
					filterExpression.substring(1), type));
		}
		else if (filterExpression.startsWith("<=")) {
			expression = Restrictions.le(property, string2FilterValue(
					filterExpression.substring(2), type));
		}
		else if (filterExpression.startsWith("=")) {
			expression = Restrictions.eq(property, string2FilterValue(
					filterExpression.substring(1), type));
		}
		else {
			expression = Restrictions.eq(property, string2FilterValue(
					filterExpression, type));
		}
		return expression;
	}

	public static SimpleExpression createLongCriteria(String property,
			String filterExpression) {
		return createCriteria(property, filterExpression, LONG);
	}

	public static SimpleExpression createDoubleCriteria(String property,
			String filterExpression) {
		return createCriteria(property, filterExpression, DOUBLE);
	}

	public static SimpleExpression createDateCriteria(String property,
			String filterExpression) {
		return createCriteria(property, filterExpression, DATE);
	}
}
