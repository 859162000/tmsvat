package com.deloitte.tms.pl.core.commons.utils;

import com.deloitte.tms.pl.core.commons.exception.BusinessException;


public class IdentityCardHelper {
	public final static int CARE_15_LENGTH = 15;

	public final static int CARE_18_LENGTH = 18;

	private static int[] w = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

	private static char[] verifyCode = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };

	/**
	 * 将15位身份证号转换成18位，对100岁老人不适用
	 * 
	 * @param idCardNumber
	 * @return
	 */
	public static String from15To18Length(String idCardNumber) {
		String cardNumber = new String(idCardNumber);
		if (cardNumber.length() == CARE_15_LENGTH) {
			cardNumber = cardNumber.substring(0, 6) + "19" + cardNumber.substring(6);
			int values = 0;
			for (int i = 0; i < cardNumber.length(); i++) {
				char c = cardNumber.charAt(i);
				int n = new Integer(c).intValue();
				values += n * w[i];
			}
			values = values % 11;
			cardNumber = cardNumber + verifyCode[values];
			return cardNumber;
		} else if (cardNumber.length() == CARE_18_LENGTH) {
			return cardNumber;
		}
		throw new BusinessException("身份证[" + idCardNumber + "]位数不正确");
	}

}
