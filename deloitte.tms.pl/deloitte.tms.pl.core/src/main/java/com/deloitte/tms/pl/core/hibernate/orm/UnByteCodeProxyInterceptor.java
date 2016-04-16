package com.deloitte.tms.pl.core.hibernate.orm;

import java.lang.reflect.Method;

import javassist.util.proxy.ProxyFactory;

import org.hibernate.EmptyInterceptor;
import org.springframework.stereotype.Component;

/**
 * @author Benny Bao (mailto:benny.bao@bstek.com)
 * @since 2010-11-26
 */
@Component(UnByteCodeProxyInterceptor.BEAN_ID)
public class UnByteCodeProxyInterceptor extends EmptyInterceptor {
	public static final String BEAN_ID="ling2.unByteCodeProxyInterceptor";
	private static boolean extraEnhancersInited;
	private static Method springCglibIsEnhancerMethod;
	private static final long serialVersionUID = -6422637558312349795L;
	@Override
	public String getEntityName(Object object) {
		if (object != null) {
			Class<?> cl = getProxyTargetType(object);
			return cl.getName();
		} else {
			return null;
		}
	}
	public static Class<?> getProxyTargetType(Object bean) {
		Class<?> cl = bean.getClass();
		while (isProxy(cl)) {
			cl = cl.getSuperclass();
		}
		return cl;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean isProxy(Class<?> cl) {
		boolean b = net.sf.cglib.proxy.Enhancer.isEnhanced(cl)
				|| ProxyFactory.isProxyClass(cl);
		if (!b) {
			if (!extraEnhancersInited) {
				extraEnhancersInited = true;
				try {
					Class enhancerType = forName("org.springframework.cglib.proxy.Enhancer");
					springCglibIsEnhancerMethod = enhancerType.getMethod(
							"isEnhanced", new Class[] { Class.class });
				} catch (Exception e) {
					// do nothing
				}
			}

			if (springCglibIsEnhancerMethod != null) {
				try {
					b = (Boolean) springCglibIsEnhancerMethod.invoke(null,
							new Object[] { cl });
				} catch (Exception e) {
					// do nothing
				}
			}
		}
		return b;
	}
	@SuppressWarnings("rawtypes")
	public static Class forName(String className) throws ClassNotFoundException {
		try {
			return org.apache.commons.lang.ClassUtils.getClass(className);
		} catch (Error e) {
			throw new ClassNotFoundException(e.getMessage(), e);
		}
	}
}
