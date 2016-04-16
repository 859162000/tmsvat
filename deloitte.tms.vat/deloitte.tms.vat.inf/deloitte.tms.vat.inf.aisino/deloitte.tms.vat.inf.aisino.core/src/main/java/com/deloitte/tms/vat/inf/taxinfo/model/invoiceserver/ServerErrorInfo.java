package com.deloitte.tms.vat.inf.taxinfo.model.invoiceserver;

import com.deloitte.tms.vat.inf.taxinfo.aisino.constant.AisinoResponseCodeDef;
import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSerializeProperty;

public class ServerErrorInfo {
	
	@AisinoSerializeProperty(serializeName="Code")
	String code;//操作结果，0000 表示成功，其它表示失败
	@AisinoSerializeProperty(serializeName="Message")
	String message;//失败时的异常信息
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isIssucess() {
		return AisinoResponseCodeDef.PRINT_SERVER_SUCESS.equals(code);
	}
	
}
