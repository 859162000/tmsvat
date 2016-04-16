package com.ling2.dataimport.interceptor.impl;

import org.springframework.stereotype.Service;

import com.ling2.dataimport.exception.InterceptorException;
import com.ling2.dataimport.interceptor.ICellDataInterceptor;

@Service("requiredInterceptor")
public class RequiredInterceptor implements ICellDataInterceptor {
	public Object execute(Object cellValue) throws Exception {
		if (cellValue == null) {
			throw new InterceptorException("当前单元格内容不能为空！");
		}
		return cellValue;
	}

	public String getName() {
		return "格式化单元格不能为空";
	}

}
