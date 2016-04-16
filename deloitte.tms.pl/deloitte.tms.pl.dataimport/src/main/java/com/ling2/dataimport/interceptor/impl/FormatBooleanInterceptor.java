package com.ling2.dataimport.interceptor.impl;

import org.springframework.stereotype.Service;


@Service("formatBooleanInterceptor")
public class FormatBooleanInterceptor extends RequiredInterceptor {
	public Object execute(Object cellValue) throws Exception {
		super.execute(cellValue);
		if (cellValue instanceof Boolean) {
			return (Boolean) cellValue ? "Y" : "N";
		}
		return cellValue;
	}

	public String getName() {
		return "格式化布尔类型[true=Y,false=N]";
	}

}
