package com.deloitte.tms.pl.version.party.model.organization.criteria.expression;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ognl.Ognl;
import ognl.OgnlException;

import com.deloitte.tms.pl.version.party.model.organization.criteria.expression.support.ExpressionUtils;
import com.deloitte.tms.pl.version.party.model.organization.filter.Filter;

public class EmptyExpression implements Expression {

	private final String propertyName;
	
	private final boolean mode;

	public EmptyExpression(final String propertyName, boolean mode) {
		super();
		this.propertyName = propertyName;
		this.mode = mode;
	}

	public Filter convertToFilter() {
		return new Filter() {

			@SuppressWarnings("unchecked")
			public boolean isPermitted(Object obj) {
				Object parse;
				try {
					parse = Ognl.parseExpression(propertyName);
					Object queryObject = Ognl.getValue(parse, obj);
					Class clazz = queryObject.getClass();
					if (ExpressionUtils.isContainEmptyMethod(clazz)) {
						Method method = ExpressionUtils.getEmptyMethod(clazz);
						Object retval = method.invoke(queryObject);
						if (retval instanceof Boolean) {
							return ((Boolean) retval).booleanValue() && mode;
						}
					}
					return !(queryObject == null);
				} catch (OgnlException e) {
					throw new RuntimeException("[Ognl failed]", e);
				} catch (IllegalArgumentException e) {
					throw new RuntimeException("[Reflect failed]- Ooooops! plz check your code", e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException("[Reflect failed] - Ooooops! plz check your code", e);
				} catch (InvocationTargetException e) {
					throw new RuntimeException("[Reflect failed]- Ooooops! plz check your code", e);
				}
			}
			
		};
	}
	
}
