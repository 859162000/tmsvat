package com.deloitte.tms.base.cache.service.impl;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.base.cache.model.BizOrgNode;
import com.deloitte.tms.base.cache.service.CacheProvider;
import com.deloitte.tms.base.cache.service.OrgCacheService;
import com.deloitte.tms.base.cache.utils.OrgCacheUtils;
import com.deloitte.tms.pl.cache.ApplicationCache;
import com.deloitte.tms.pl.core.commons.constant.TableColnumDef;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.reflect.ReflectUtils;
import com.deloitte.tms.pl.security.dao.IDeptDao;
import com.deloitte.tms.pl.security.model.SecurityPosition;
import com.deloitte.tms.pl.security.model.SecurityRole;
import com.deloitte.tms.pl.security.model.impl.DefaultDeptInParam;
import com.deloitte.tms.pl.version.party.model.organization.node.Node;

@Component
public class OrgCacheServiceImpl implements OrgCacheService{
	@Resource
	IDeptDao deptDao;
	
	@Resource
	ApplicationCache applicationCache;
	
	@Resource(name=OrgCacheProvider.BEAN_ID)
	CacheProvider deptProvider;

	@Override
	public List<DefaultDeptInParam> getDeptCacheTree() {
		return buildDept(null);
	}
	@Override
	public List<DefaultDeptInParam> getEffectDeptCacheTree() {
		return buildDept(TableColnumDef.FLAG_EFFECT);
	}
	public DefaultDeptInParam getDeptById(String deptId){
		AssertHelper.notEmpty_assert(deptId, "机构id不能为空");
		BizOrgNode topDeptNode = (BizOrgNode) OrgCacheUtils.getTopNode();
		BizOrgNode deptNode=(BizOrgNode) topDeptNode.getPosterities().get(deptId);
		if(deptNode==null){
			throw new BusinessException("机构没有找到");
		}
		return assemble(topDeptNode, deptNode);
	}
	@Override
	public void flushDeptCacheTree() {
		applicationCache.putCacheObject(DEPT_CACHE_ID,null);	
	}
	@Override
	public List<SecurityRole> getRoleCacheTree() {
		return null;
	}

	@Override
	public List<SecurityPosition> getPositionCacheTree() {
		return null;
	}
	/**
	 * 从deptnode构建结果
	 * @param flag 是否启用
	 * @return
	 */
	private List<DefaultDeptInParam> buildDept(String flag){
		List<DefaultDeptInParam> results=buildDeptsByParentId(null,flag);
		return results;
	}
	/**
	 * 从deptnode构建结果
	 * @param parentId 起点
	 * @param flag 是否启用
	 * @return
	 */
	private List<DefaultDeptInParam> buildDeptsByParentId(String parentId,String flag) {
		BizOrgNode topDeptNode = (BizOrgNode) OrgCacheUtils.getTopNode();
		List<DefaultDeptInParam> results=new ArrayList<DefaultDeptInParam>();
		BizOrgNode deptNode=(BizOrgNode) topDeptNode.getPosterities().get(parentId);
		Enumeration<Node> childsnode=deptNode.children();
		while (childsnode.hasMoreElements()) {
			BizOrgNode childNode = (BizOrgNode) childsnode.nextElement();
			if(flag==null||(AssertHelper.notEmpty(flag)&&flag.equals(childNode.getFlag()))){//flag为空或者flag相等的加入树
				DefaultDeptInParam defaultDeptInParam = assemble(topDeptNode,
						childNode);			
				List<DefaultDeptInParam> childs;
				if(childNode.children().hasMoreElements()){
					childs=buildDeptsByParentId(childNode.getId(),flag);	
				}else{
					childs=new ArrayList<DefaultDeptInParam>();
				}
				defaultDeptInParam.setRelDepts(childs);	
				results.add(defaultDeptInParam);
			}			
		}
		return results;
	}
	/**
	 * 将IrisDeptNode转换为DefaultDeptInParam
	 * @param topDeptNode
	 * @param childNode
	 * @return
	 */
	private DefaultDeptInParam assemble(BizOrgNode topDeptNode,
			BizOrgNode childNode) {
		DefaultDeptInParam defaultDeptInParam=new DefaultDeptInParam();
		ReflectUtils.copyProperties(childNode, defaultDeptInParam);
		if(AssertHelper.notEmpty(defaultDeptInParam.getParentId())){
			BizOrgNode parent=(BizOrgNode) topDeptNode.getPosterities().get(defaultDeptInParam.getParentId());
			defaultDeptInParam.setParentName(parent.getName());
		}
		return defaultDeptInParam;
	}
	
}
