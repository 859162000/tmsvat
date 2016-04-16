package com.deloitte.tms.vat.inf.taxinfo.aisino.enums;

public enum AISINOErrorEnums {
	
	console_Exception("-1"), //控制台异常信息
	console_ConnectException("-2"), //连接控制台异常
	console_IpPortException("-3"),//控制台ip或端口号为空
	sucess("0");//操作成功	
	
	private String value;

	private AISINOErrorEnums(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
