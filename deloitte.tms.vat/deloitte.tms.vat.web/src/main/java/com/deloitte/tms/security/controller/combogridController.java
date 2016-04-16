package com.deloitte.tms.security.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.deloitte.tms.base.masterdata.model.ClientInParam;
import com.deloitte.tms.vat.controller.BaseController;
import com.deloitte.tms.vat.core.common.JsonDateValueProcessor;
import com.deloitte.tms.vat.model.Person;

@Controller
public class combogridController  extends BaseController{
	
	@RequestMapping(value = "/combogrid")
	public String indexPage() {
		return "security/combogrid";
	}
	
	 @RequestMapping("combogrid/search.do")
		public void ProcessRequest(HttpServletRequest request,HttpServletResponse response) throws IOException
	    {
	      

	        //页数
	        String PageNum = request.getParameter("page");
	        //每一页多少条数据
	        String Record = request.getParameter("rows");

	        //模拟数据
	        	
	        if("2".equals(PageNum)){
	        	List<Person> list = new ArrayList<Person>();
	        	Person person11 = new Person();
		        person11.setPersonId("11");
		        person11.setPersonName("张三");
		        person11.setGender("男");
		        list.add(person11);
		        JSONObject result=new JSONObject(); 
		        result.put("total", 11);
		        result.put("rows", list);
		        retJson(response, result);
	        }else {
	        	JSONObject result=new JSONObject(); 
		        result.put("total", 11);
	        	List<Person> list = MockData();
	        	result.put("rows", list);
	        	 retJson(response, result);
			}
	        
	        JSONObject result=new JSONObject();
			  List<ClientInParam> list = new ArrayList<ClientInParam>();
			 ClientInParam client1 = new ClientInParam();
	         client1.setClientDate(new Date());	
	         list.add(client1);
	         ClientInParam client2 = new ClientInParam();
	         client2.setClientDate(new Date());
	         list.add(client2);
			
			 JsonConfig jsonConfig = new JsonConfig();  
			 jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor()); 
			 Map<String, Object> map = new HashMap<String,Object>();
			 map.put("total", 1);
			 map.put("rows", list);
			 JSONArray jsonArray1 = JSONArray.fromObject(list,jsonConfig);
			 JSONArray jsonArray = JSONArray.fromObject(map, jsonConfig);
			 JSONObject jsonObject = JSONObject.fromObject(client1,jsonConfig);
			 result.put("total", 1);
			 result.put("rows",jsonArray1);
			 //JSONObject json = JSONObject.fromObject(list, jsonConfig); 
			 //result.put("rows", client);
			 retJson(response,result);	  
	     
			
			
	    }
		
		public List<Person> MockData()
	    {
	        List<Person> list = new ArrayList<Person>() ;
	        Person person1 = new Person();
	        person1.setPersonId("1");
	        person1.setPersonName("叶宇");
	        person1.setGender("女");
	        list.add(person1);
	        Person person2 = new Person();
	        person2.setPersonId("2");
	        person2.setPersonName("张兄家");
	        person2.setGender("男");
	        list.add(person2);
	        Person person3 = new Person();
	        person3.setPersonId("3");
	        person3.setPersonName("张洋");
	        person3.setGender("男");
	        list.add(person3);
	        Person person4 = new Person();
	        person4.setPersonId("4");
	        person4.setPersonName("洪自军");
	        person4.setGender("女");
	        list.add(person4);
	        Person person5 = new Person();
	        person5.setPersonId("5");
	        person5.setPersonName("lisi");
	        person5.setGender("男");
	        list.add(person5);
	        Person person6 = new Person();
	        person6.setPersonId("6");
	        person6.setPersonName("叶宇");
	        person6.setGender("女");
	        list.add(person6);
	        Person person7 = new Person();
	        person7.setPersonId("7");
	        person7.setPersonName("叶宇");
	        person7.setGender("女");
	        list.add(person7);
	        Person person8 = new Person();
	        person8.setPersonId("8");
	        person8.setPersonName("叶宇");
	        person8.setGender("女");
	        list.add(person8);
	        Person person9 = new Person();
	        person9.setPersonId("9");
	        person9.setPersonName("叶宇");
	        person9.setGender("女");
	        list.add(person9);
	        Person person10 = new Person();
	        person10.setPersonId("10");
	        person10.setPersonName("叶宇");
	        person10.setGender("女");
	        list.add(person10);
	        
	        return list;
	    }


}
