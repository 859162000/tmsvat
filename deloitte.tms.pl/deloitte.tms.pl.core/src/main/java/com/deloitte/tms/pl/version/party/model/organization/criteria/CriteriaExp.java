package com.deloitte.tms.pl.version.party.model.organization.criteria;

import java.util.ArrayList;
import java.util.Collection;

import com.deloitte.tms.pl.version.party.model.organization.criteria.expression.ClassExpression;
import com.deloitte.tms.pl.version.party.model.organization.criteria.expression.EmptyExpression;
import com.deloitte.tms.pl.version.party.model.organization.criteria.expression.EqExpression;
import com.deloitte.tms.pl.version.party.model.organization.criteria.expression.Expression;
import com.deloitte.tms.pl.version.party.model.organization.criteria.expression.InExpression;
import com.deloitte.tms.pl.version.party.model.organization.criteria.expression.LikeExpression;
import com.deloitte.tms.pl.version.party.model.organization.criteria.expression.NeExpression;
import com.deloitte.tms.pl.version.party.model.organization.criteria.expression.NotInExpression;
import com.deloitte.tms.pl.version.party.model.organization.criteria.expression.NullExpression;
import com.deloitte.tms.pl.version.party.model.organization.criteria.expression.OrExpression;
import com.deloitte.tms.pl.version.party.model.organization.criteria.expression.support.MatchMode;

public class CriteriaExp {
	
	public static EqExpression eq(String propertyName, Object value) {
		return new EqExpression(propertyName, value);
	}
	
	public static NeExpression ne(String propertyName, String value) {
		return new NeExpression(propertyName, value);
	}
	
	public static LikeExpression like(String propertyName, String value) {
		return new LikeExpression(propertyName, value, MatchMode.ANYWHERE);
	}
	
	public static LikeExpression like(String propertyName, String value, MatchMode matchMode) {
		return new LikeExpression(propertyName, value, matchMode);
	}
	
	public static EmptyExpression isEmpty(String propertyName) {
		return new EmptyExpression(propertyName, true);
	}
	
	public static EmptyExpression notEmpty(String propertyName) {
		return new EmptyExpression(propertyName, false);
	}
	
	public static NullExpression isNull(String propertyName) {
		return new NullExpression(propertyName, true);
	}
	
	public static NullExpression notNull(String propertyName) {
		return new NullExpression(propertyName, false);
	}
	
	public static InExpression in(String propertyName, Object[] values) {
		Collection<Object> c = new ArrayList<Object>();
		for (int i = 0; i < values.length; i++) {
			c.add(values[i]);
		}
		return new InExpression(propertyName, c);
	}
	
	public static InExpression in(String propertyName, Collection<Object> values) {
		return new InExpression(propertyName, values);
	}
	
	public static NotInExpression notIn(String propertyName, Object[] values) {
		Collection<Object> c = new ArrayList<Object>();
		for (int i = 0; i < values.length; i++) {
			c.add(values[i]);
		}
		return new NotInExpression(propertyName, c);
	}
	
	public static NotInExpression notIn(String propertyName, Collection<Object> values) {
		return new NotInExpression(propertyName, values);
	}
	
	public static OrExpression or() {
		return new OrExpression(new ArrayList<Expression>());
	}
	
	@SuppressWarnings("rawtypes")
	public static ClassExpression ce(Class clazz) {
		return new ClassExpression(clazz);
	}
	
}
