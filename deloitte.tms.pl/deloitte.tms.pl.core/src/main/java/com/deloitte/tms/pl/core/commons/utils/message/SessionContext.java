package com.deloitte.tms.pl.core.commons.utils.message;

import javax.servlet.http.HttpSession;

public class SessionContext {

	private static ThreadLocal<HttpSession> SESSION = new ThreadLocal<HttpSession>();

	public static final void setAttribute(String key, Object val) {
		SESSION.get().setAttribute(key, val);
	}

	public static final Object getAttribute(String key) {
		return SESSION.get().getAttribute(key);
	}

	public static final void setSession(HttpSession session) {
		SESSION.set(session);

	}
	public static final HttpSession getSession() {
		return SESSION.get();
	}

}
