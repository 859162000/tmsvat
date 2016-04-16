package com.deloitte.tms.pl.workflow.process.assign.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.env.Context;
import com.deloitte.tms.pl.workflow.model.ProcessInstance;
import com.deloitte.tms.pl.workflow.process.assign.Entity;
import com.deloitte.tms.pl.workflow.process.assign.PageQuery;
import com.deloitte.tms.pl.workflow.service.IdentityService;

/**
 * @author Jacky.gao
 * @since 2013年8月20日
 */
@Component(DeptAssigneeProvider.BEAN_ID)
public class DeptAssigneeProvider extends AbstractAssigneeProvider {
	public static final String BEAN_ID="uflo.deptAssigneeProvider";
	@Resource
	private IdentityService identityService;
	@Value("${uflo.disabledDeptAssigneeProvider}")
	private boolean disabledDeptAssigneeProvider;
	public boolean isTree() {
		return true;
	}
	
	public String getName() {
		return "指定部门";
	}
	
	public void queryEntities(PageQuery<Entity> pageQuery, String parentId) {
		identityService.deptPageQuery(pageQuery,parentId);
	}
	
	public Collection<String> getUsers(String entityId,Context context,ProcessInstance processInstance) {
		return identityService.getUsersByDept(entityId);
	}

	public boolean disable() {
		return disabledDeptAssigneeProvider;
	}
	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}

	public boolean isDisabledDeptAssigneeProvider() {
		return disabledDeptAssigneeProvider;
	}

	public void setDisabledDeptAssigneeProvider(boolean disabledDeptAssigneeProvider) {
		this.disabledDeptAssigneeProvider = disabledDeptAssigneeProvider;
	}
}
