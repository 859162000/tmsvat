package com.ling2.dataimport.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImportResult {
	Map parameter=new HashMap();
	List datas=new ArrayList();
	public Map getParameter() {
		return parameter;
	}
	public void setParameter(Map parameter) {
		this.parameter = parameter;
	}
	public List getDatas() {
		return datas;
	}
	public void setDatas(List datas) {
		this.datas = datas;
	}
	
	
}
