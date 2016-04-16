package com.deloitte.tms.pl.security.security;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @since 2013-1-31
 * @author Jacky.gao
 */
@Component("ling2.passwordEncoder")
public class UserShaPasswordEncoder extends ShaPasswordEncoder {
	public static final String BEAN_ID="ling2.passwordEncoder";

	public UserShaPasswordEncoder() {
		super();
	}

	public UserShaPasswordEncoder(int strength) {
		super(strength);
	}
	
}
