package com.deloitte.tms.vat.inf.taxinfo.aisino.enums;

/*
 * 发票种类
 * @author bo.wang
 * @create 2016年4月9日 下午3:14:27
 */
public enum FPZLEnums {
	zp("0"), //0 专票
	pp("2"); //2普票
	//11货运票 
	//12机动车票
	//51 电子发票
	private String value;

	private FPZLEnums(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
