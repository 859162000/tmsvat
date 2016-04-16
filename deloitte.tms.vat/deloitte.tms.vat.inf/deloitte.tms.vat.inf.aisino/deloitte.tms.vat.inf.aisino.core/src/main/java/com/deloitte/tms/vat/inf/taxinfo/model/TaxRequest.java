package com.deloitte.tms.vat.inf.taxinfo.model;

public interface TaxRequest {
	public String getIp();
	public void setIp(String ip);
	public String getPort() ;
	public void setPort(String port);
	public void check();
}
