package com.deloitte.tms.vat.inf.taxinfo.aisino.service;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.deloitte.tms.pl.core.commons.utils.XmlUtils;
import com.deloitte.tms.vat.inf.taxinfo.model.TaxRequest;
import com.deloitte.tms.vat.inf.taxinfo.service.impl.BaseTaxObjectSerializeImpl;

public class AisinoServerTaxObjectSerializeImpl extends BaseTaxObjectSerializeImpl{
	@Override
	public void check(Object object) {
		
	}
	@Override
	public Object responseToObject(String xml,Class type) {
		Document document = XmlUtils.getDocument(xml); 
		Element rootElement=XmlUtils.getRootElement(document, "kpserver");
		Object result=parseComplexObject(rootElement,type);
		return result;
	}
	@Override
	public String outObject(Object object) {
		check(object);
		StringBuilder result=new StringBuilder();
		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		result.append("<Input>");
		
		if(object instanceof TaxRequest){
			TaxRequest request=(TaxRequest)object;	
			result.append("<IP>"+request.getIp()+"</IP>");
			result.append("<PORT>"+request.getPort()+"</PORT>");
		}else {
			result.append("<IP/>");
			result.append("<PORT/>");
		}
		outComplexObject(object,result);
		result.append("</Input>");
		return result.toString();
	}
}
