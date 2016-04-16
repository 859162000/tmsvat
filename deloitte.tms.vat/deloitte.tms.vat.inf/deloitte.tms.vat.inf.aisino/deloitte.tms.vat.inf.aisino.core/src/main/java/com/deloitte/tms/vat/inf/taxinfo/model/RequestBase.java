package com.deloitte.tms.vat.inf.taxinfo.model;

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;

public abstract class RequestBase implements TaxRequest{	
	
	String ip="192.168.56.103";
	String port="8888";
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public void check(){
		AssertHelper.notEmpty_assert(ip, "ip不能为空");
		AssertHelper.notEmpty_assert(ip, "port不能为空");
	}
}
