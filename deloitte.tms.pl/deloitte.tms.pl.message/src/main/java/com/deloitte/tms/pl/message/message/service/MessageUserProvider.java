package com.deloitte.tms.pl.message.message.service;

import java.util.List;

import com.deloitte.tms.pl.security.model.SecurityUser;

public interface MessageUserProvider {
	List<SecurityUser> getUserList();
}
