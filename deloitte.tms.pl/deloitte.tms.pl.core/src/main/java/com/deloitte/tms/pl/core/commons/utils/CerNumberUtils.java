package com.deloitte.tms.pl.core.commons.utils;

/**
 * 身份证15/17位转换查询
 * 
 * @author 周栋 2012-8-15
 */
public class CerNumberUtils {

	public static final String cerTypeCode = "UI1";

	private String cerNum15;

	private String cerNum17;

	public String getCerNum15() {
		return cerNum15;
	}

	public void setCerNum15(String cerNum15) {
		this.cerNum15 = cerNum15;
	}

	public String getCerNum17() {
		return cerNum17;
	}

	public void setCerNum17(String cerNum17) {
		this.cerNum17 = cerNum17;
	}

	/**
	 * 格式化证件号码
	 * @author 周栋
	 * 2012-8-15
	 * @param cerNumber
	 * @return
	 */
	public static CerNumberUtils getCerNumber(String cerNumber) {
		CerNumberUtils cerUtils = new CerNumberUtils();
		if (cerNumber.length() == 15) {
			cerUtils.setCerNum15(cerNumber);
			cerUtils.setCerNum17(cerNumber.substring(0, 6) + "19" + cerNumber.substring(6));
		} else {
			cerUtils.setCerNum17(cerNumber);
			cerUtils.setCerNum15(cerNumber.substring(0, 6) + cerNumber.substring(8, 17));
		}
		return cerUtils;
	}
	
}
