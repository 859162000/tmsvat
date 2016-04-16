package com.deloitte.tms.vat.inf.taxinfo.model.invoiceserver;

import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSerializeProperty;

public class ServerResponseBase {	
	
	@AisinoSerializeProperty(serializeName="errorinfo")
	ServerErrorInfo errorinfo=new ServerErrorInfo();

	public ServerErrorInfo getErrorinfo() {
		return errorinfo;
	}

	public void setErrorinfo(ServerErrorInfo errorinfo) {
		this.errorinfo = errorinfo;
	}
	public void setSucess(){
		errorinfo.setCode("0000");
	}
	public boolean isSucess(){
		return errorinfo.isIssucess();
	}
	public void setFail(String code,//操作结果，0000 表示成功，其它表示失败
			String message){
		errorinfo.setCode(code);
		errorinfo.setMessage(message);
	}
	public String getCode() {
		return errorinfo.code;
	}

	public String getMessage() {
		return errorinfo.message;
	}
}
