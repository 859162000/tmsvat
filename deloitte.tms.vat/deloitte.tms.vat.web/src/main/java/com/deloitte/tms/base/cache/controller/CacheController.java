package com.deloitte.tms.base.cache.controller;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.tms.base.cache.model.LegalEntityNode;
import com.deloitte.tms.base.cache.service.OrgPathProvider;
import com.deloitte.tms.base.cache.utils.LegalEntityCacheUtils;
import com.deloitte.tms.base.enums.PrintRangeEnums;
import com.deloitte.tms.pl.core.commons.utils.FileUtils;
import com.deloitte.tms.pl.job.task.JobTest;
import com.deloitte.tms.pl.version.party.model.organization.node.Node;
import com.deloitte.tms.pl.workflow.service.ProcessService;

@Component
@RequestMapping("cache")
public class CacheController {
	@Resource
	private ProcessService processService;
	
	@Resource
	OrgPathProvider orgPathProvider;
	@Resource(name=RefreshCacheJob.BEAN_ID)
	JobTest refreshCacheJob;
	
	@ResponseBody
	@RequestMapping(value = "/testCache")
	public void testCache(){
//		TreeNode topnodeNode=OrgCacheUtils.getTopNode();
//		displayNode(topnodeNode);
		LegalEntityNode legalEntityNode=LegalEntityCacheUtils.getTopNode();
		displayNode(legalEntityNode);
	}
	private static void displayNode(Node treeNode){
		System.out.println(treeNode.toString());
		Enumeration<Node> enumeration = treeNode.children();
		while (enumeration.hasMoreElements()) {
			displayNode(enumeration.nextElement());
		}
	}
	@ResponseBody
	@RequestMapping(value = "/refreshCacheJob")
	public void refreshCacheJob(){
		refreshCacheJob.execute();
	}
	@ResponseBody
	@RequestMapping(value = "/refreshOrgPath")
	public void refreshOrgPath(){
		orgPathProvider.execRefreshOrgPath();
	}
	@ResponseBody
	@RequestMapping(value = "/publishWorkflow")
	public void publishWorkflow(){
		InputStream inputStream=FileUtils.getFileInputStreamByClassLocation(CacheController.class,"process.uflo.xml");
		processService.deployProcess(inputStream);
	}
	@ResponseBody
	@RequestMapping(value = "/testOrg1")
	public List<String> testOrg1(String orgId,String type){
		PrintRangeEnums printRangeEnums=null;
		if("1".equals(type)){
			printRangeEnums=PrintRangeEnums.all;
		}
		if("2".equals(type)){
			printRangeEnums=PrintRangeEnums.current;
		}
		if("3".equals(type)){
			printRangeEnums=PrintRangeEnums.city;
		}
		return LegalEntityCacheUtils.legalEntityCodesByOrgId(orgId, printRangeEnums);
	}
}
