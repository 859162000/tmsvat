package com.deloitte.tms.base.cache.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.base.cache.model.BizOrgNode;
import com.deloitte.tms.base.cache.service.CacheBusinessContext;
import com.deloitte.tms.base.cache.service.CacheProvider;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.security.dao.IDeptDao;
import com.deloitte.tms.pl.security.model.SecurityDept;
import com.deloitte.tms.pl.security.model.impl.DefaultDept;

@Component(OrgCacheProvider.BEAN_ID)
public class OrgCacheProvider implements CacheProvider {
	public static final String BEAN_ID="orgCacheProvider";
	@Resource
	IDeptDao deptDao;
	@Override
	public BizOrgNode build(CacheBusinessContext context)
			throws BusinessException {
		List<SecurityDept> depts=deptDao.findAllDepts();
		Map<String, Collection<DefaultDept>> subordinateRelations = new HashMap<String, Collection<DefaultDept>>();
		//上级id为null的一级数据放到root下
		BizOrgNode root = assembleRoot();
		for (SecurityDept dept : depts) {
			DefaultDept defaultDept=(DefaultDept)dept;
			String superiorId = defaultDept.getParentId();
			//根据上級ID把下属的机构都归在一个机构下
			if (subordinateRelations.keySet().contains(superiorId)) {
				Collection<DefaultDept> subordinates = subordinateRelations.get(superiorId);
				subordinates.add(defaultDept);
			} else {
				Collection<DefaultDept> subordinates = new ArrayList<DefaultDept>();
				subordinates.add(defaultDept);
				subordinateRelations.put(superiorId, subordinates);
			}			
		}
		
		//构建机构树,此时构建的就是一棵以当前考核机构为根，向下伸展的机构树
		//包含了当前考核机构和下属所有分支机构的树，分支和分支之间保留了上下级关系。
		assembleTree(root, subordinateRelations);
		return root;
	}
	protected void assembleTree(BizOrgNode superior, Map<String, Collection<DefaultDept>> subordinateRelations) {
		String superiorId = superior.getId();
		Collection<DefaultDept> subordinates = subordinateRelations.get(superiorId);

		if (subordinates == null || subordinates.isEmpty()) {
			return;
		}
		
		for (DefaultDept defaultDept : subordinates) {
			BizOrgNode subordinate = assemble(defaultDept);
			subordinate.setDataLevel(superior.getDataLevel()+1);
			superior.add(subordinate);
			assembleTree(subordinate, subordinateRelations);
		}
	}
	
	protected BizOrgNode assemble(DefaultDept dept) {
		BizOrgNode node=new BizOrgNode(dept.getId(),dept.getOrgName());
		ReflectUtils.copyProperties(dept, node);
		node.setCode(dept.getOrgCode());
		return node;
	}
	protected BizOrgNode assembleRoot() {
		BizOrgNode node=new BizOrgNode(null,"Root");
		node.setCode("1");
		node.setDataLevel(1);
		return node;
	}
}
