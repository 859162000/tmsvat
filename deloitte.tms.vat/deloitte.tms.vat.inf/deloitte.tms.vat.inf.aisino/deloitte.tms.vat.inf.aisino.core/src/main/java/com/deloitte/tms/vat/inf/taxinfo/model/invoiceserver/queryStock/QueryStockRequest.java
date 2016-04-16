package com.deloitte.tms.vat.inf.taxinfo.model.invoiceserver.queryStock;

import com.deloitte.tms.vat.inf.taxinfo.annotation.AisinoSerializeProperty;
import com.deloitte.tms.vat.inf.taxinfo.model.RequestBase;

public class QueryStockRequest extends RequestBase{
	
	@AisinoSerializeProperty(serializeName="NSRSBH",length=20,des="纳税人识别号")
	String nsrsbh;
	@AisinoSerializeProperty(serializeName="KPFWQH",length=10,des="开票服务器号",require=true)
	String kpfwqh;//0表示主服务器
	@AisinoSerializeProperty(serializeName="KPDH",length=10,des="开票点号",require=true)
	String kpdh;
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getKpfwqh() {
		return kpfwqh;
	}
	public void setKpfwqh(String kpfwqh) {
		this.kpfwqh = kpfwqh;
	}
	public String getKpdh() {
		return kpdh;
	}
	public void setKpdh(String kpdh) {
		this.kpdh = kpdh;
	}
	
	
}
