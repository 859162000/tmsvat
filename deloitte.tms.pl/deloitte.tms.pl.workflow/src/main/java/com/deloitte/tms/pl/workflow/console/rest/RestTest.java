package com.deloitte.tms.pl.workflow.console.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.deloitte.tms.pl.workflow.console.rest.model.CompleteTaskInfo;

public class RestTest {
	public static void main(String[] args) {
		RestTemplate template=new RestTemplate();
		String url="http://localhost:8080/uflo-test/dorado/uflo/rest/complete/task";
		CompleteTaskInfo info=new CompleteTaskInfo();
		info.setTaskId(60007);
		Map<String,Object> variables=new HashMap<String,Object>();
		variables.put("aaa", 111);
		variables.put("bbb", "dsfadsaf");
		info.setVariables(variables);
		template.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
		template.getMessageConverters().add(new StringHttpMessageConverter());
		HttpEntity<CompleteTaskInfo> entity=new HttpEntity<CompleteTaskInfo>(info);
		template.postForEntity(url, entity, CompleteTaskInfo.class);
	}
}
