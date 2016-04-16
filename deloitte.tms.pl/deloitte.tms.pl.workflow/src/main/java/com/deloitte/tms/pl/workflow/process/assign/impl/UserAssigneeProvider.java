package com.deloitte.tms.pl.workflow.process.assign.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
 * @since 2013年8月17日
 */
@Component(UserAssigneeProvider.BEAN_ID)
public class UserAssigneeProvider extends AbstractAssigneeProvider{
	public static final String BEAN_ID="uflo.userAssigneeProvider";
	@Resource
	private IdentityService identityService;
	@Value("${uflo.disabledUserAssigneeProvider}")
	private boolean disabledUserAssigneeProvider;
	public boolean isTree() {
		return false;
	}
	public String getName() {
		return "指定用户";
	}
	public void queryEntities(PageQuery<Entity> pageQuery, String parentId) {
		identityService.userPageQuery(pageQuery);
	}
	
	public Collection<String> getUsers(String entityId,Context context,ProcessInstance processInstance) {
		List<String> users=new ArrayList<String>();
		users.add(entityId);
		return users;
	}

	public boolean disable() {
		return disabledUserAssigneeProvider;
	}
	
	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}
	public boolean isDisabledUserAssigneeProvider() {
		return disabledUserAssigneeProvider;
	}
	public void setDisabledUserAssigneeProvider(boolean disabledUserAssigneeProvider) {
		this.disabledUserAssigneeProvider = disabledUserAssigneeProvider;
	}
}
