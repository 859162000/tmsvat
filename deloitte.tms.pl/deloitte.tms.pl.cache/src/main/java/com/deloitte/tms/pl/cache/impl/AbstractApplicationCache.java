package com.deloitte.tms.pl.cache.impl;

import com.deloitte.tms.pl.cache.ApplicationCache;

public abstract class AbstractApplicationCache implements ApplicationCache{
	
	private String buildKey(String key){
		return key+"_"+APPNAME;
	}
	
}
