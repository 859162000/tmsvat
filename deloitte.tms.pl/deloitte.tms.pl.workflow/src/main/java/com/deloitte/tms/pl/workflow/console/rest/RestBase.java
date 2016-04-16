package com.deloitte.tms.pl.workflow.console.rest;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Jacky.gao
 * @since 2013年9月22日
 */
//@Component
public class RestBase {
	@Value("${uflo.restAccessBaseUrl}")
	private String baseUrl;
	@Value("${uflo.restAccessUsername}")
	String username;
	@Value("${uflo.restAccessPassword}")
	String password;
	private HttpHeaders headers;
	private RestTemplate template=new RestTemplate();
	private List<HttpMessageConverter<?>> converters;
	public RestBase(String username,String password){
		headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
			return;
		}
		headers.add("uflo.access.username", username);
		headers.add("uflo.access.password", password);
	}
	public <T> ResponseEntity<T>  post(String uri,Object obj,Class<T> responseClazz){
		if(obj==null){
			obj="blank";
		}
		HttpEntity<Object> entity=null;
		if(headers!=null){
			entity=new HttpEntity<Object>(obj,headers);
		}else{
			entity=new HttpEntity<Object>(obj);			
		}
		return template.postForEntity(baseUrl+uri,entity,responseClazz);
	}
	
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	public String getBaseUrl() {
		return baseUrl;
	}
	public List<HttpMessageConverter<?>> getConverters() {
		return converters;
	}
	public void setConverters(List<HttpMessageConverter<?>> converters) {
		this.converters = converters;
		template.setMessageConverters(converters);
	}
}
