package com.deloitte.tms.pl.workflow.console.rest.sync;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.console.rest.RestBase;
import com.deloitte.tms.pl.workflow.model.ProcessDefinition;
import com.deloitte.tms.pl.workflow.service.ProcessInterceptor;

/**
 * @author Jacky.gao
 * @since 2013年11月28日
 */
@Component
public class ProcessSyncInterceptor implements ProcessInterceptor,InitializingBean{
	@Resource
	private RestBase restBase;
	@Value("${uflo.clients}")
	private String clients;
	private List<String> clientUrls;
	public void deleteProcess(ProcessDefinition process) {
		String uri="/uflo/process/deletememory/id/"+process.getId()+"";
		doSync(uri);
	}
	public void updateProcess(ProcessDefinition process) {
		String uri="/uflo/process/updatememory/id/"+process.getId()+"";
		doSync(uri);
	}
	
	private void doSync(String uri){
		if(clientUrls==null || clientUrls.size()==0)return;
		for(String baseUrl:clientUrls){
			if(baseUrl.endsWith("dorado")){
				restBase.post(baseUrl+uri, null,null);		
			}else{
				if(baseUrl.endsWith("/")){
					restBase.post(baseUrl+"dorado"+uri, null,null);											
				}else{
					restBase.post(baseUrl+"/dorado"+uri, null,null);																
				}
			}
		}
	}
	
	public RestBase getRestBase() {
		return restBase;
	}
	public void setRestBase(RestBase restBase) {
		this.restBase = restBase;
	}
	public String getClients() {
		return clients;
	}
	public void setClients(String clients) {
		this.clients = clients;
	}
	public void afterPropertiesSet() throws Exception {
		if(StringUtils.isEmpty(clients))return;
		clientUrls=new ArrayList<String>();
		for(String url:clients.split(";")){
			if(StringUtils.isNotEmpty(url)){
				clientUrls.add(url);				
			}
		}
	}
}
