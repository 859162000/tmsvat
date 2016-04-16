package com.deloitte.tms.pl.workflow.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import com.deloitte.tms.pl.workflow.process.assign.Entity;
import com.deloitte.tms.pl.workflow.process.assign.PageQuery;
import com.deloitte.tms.pl.workflow.service.IdentityService;

/**
 * @author Jacky.gao
 * @since 2013年8月13日
 */
@Component(IdentityService.BEAN_ID)
public class DefaultIdentityService implements IdentityService {

	public Collection<String> getUsersByGroup(String group) {
		List<String> users=new ArrayList<String>();
		for(int i=1;i<5;i++){
			users.add("user-"+group+"-"+i);
		}
		return users;
	}

	public Collection<String> getUsersByPosition(String position) {
		List<String> users=new ArrayList<String>();
		for(int i=1;i<5;i++){
			users.add("user-"+position+"-"+i);
		}
		return users;
	}

	public Collection<String> getUsersByDept(String dept) {
		List<String> users=new ArrayList<String>();
		for(int i=1;i<5;i++){
			users.add("user-"+dept+"-"+i);
		}
		return users;
	}

	public Collection<String> getUsersByDeptAndPosition(String dept,String position) {
		List<String> users=new ArrayList<String>();
		for(int i=1;i<5;i++){
			users.add("user-"+dept+"-"+position+"-"+i);
		}
		return users;
	}

	public void userPageQuery(PageQuery<Entity> page) {
		page.setRecordCount(400);
		int index=page.getPageIndex();
		int size=page.getPageSize();
		List<Entity> entitys=new ArrayList<Entity>();
		for(int i=(index-1)*size;i<(index*size);i++){
			Entity entity=new Entity("user"+i,"测试用户"+i);
			entitys.add(entity);
		}
		page.setResult(entitys);
	}

	public void deptPageQuery(PageQuery<Entity> page,String parentId) {
		parentId=(parentId==null?"":parentId);
		page.setRecordCount(400);
		int index=page.getPageIndex();
		int size=10;
		List<Entity> entitys=new ArrayList<Entity>();
		for(int i=(index-1)*size;i<(index*size);i++){
			Entity entity=new Entity("dept"+parentId+i,"测试部门"+i);
			if(i>4){
				entity.setChosen(false);
			}
			entitys.add(entity);
			
		}
		page.setResult(entitys);
	}

	public void positionPageQuery(PageQuery<Entity> query,String parentId) {
		
	}

	public void groupPageQuery(PageQuery<Entity> query,String parentId) {
		
	}

}
