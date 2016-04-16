package com.deloitte.tms.base.cache.utils;

import java.util.Collection;
//import java.util.Enumeration;

import com.deloitte.tms.base.cache.model.BizOrgNode;
import com.deloitte.tms.base.cache.model.LegalEntityNode;
import com.deloitte.tms.base.cache.model.OrgNode;
import com.deloitte.tms.base.cache.service.OrgCacheService;
import com.deloitte.tms.base.cache.service.impl.OrgCacheProvider;
import com.deloitte.tms.pl.cache.CacheUtils;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.deloitte.tms.pl.version.party.model.organization.node.Node;

public class OrgCacheUtils{
	/**
	 * 获取缓存的业务单位完整树
	 * @return
	 */
	public static BizOrgNode getTopNode() {
//		applicationCache.putCacheObject(IrisDeptService.DEPT_CACHE_ID,null);
		OrgCacheProvider deptProvider=SpringUtil.getBean(OrgCacheProvider.BEAN_ID);
		BizOrgNode topDeptNode=(BizOrgNode) CacheUtils.getCacheObject(OrgCacheService.DEPT_CACHE_ID);
		if(topDeptNode==null){
			topDeptNode=(BizOrgNode) deptProvider.build(null);
			CacheUtils.putCacheObject(OrgCacheService.DEPT_CACHE_ID,topDeptNode);
		}
		return topDeptNode;
	}
	/**
	 * 根据orgId获取机构节点
	 * @param orgId
	 * @return
	 */
	public static BizOrgNode getNodeByOrgId(String orgId) {
		return (BizOrgNode) getTopNode().getPosterities().get(orgId);
	}
	/**
	 * 根据orgId获取上级机构节点
	 * @param orgId
	 * @return
	 */
	public static BizOrgNode getParentNodeByOrgId(String orgId) {
		return (BizOrgNode) getTopNode().getPosterities().get(orgId).getParent();
	}
	/**
	 * 根据orgId获取下级机构节点
	 * @param orgId
	 * @return
	 */
	public static Collection<Node> getChildNodeByOrgId(String orgId) {
		return getTopNode().getPosterities().get(orgId).getChildren();
	}
	/**
	 * 根据orgCode获取机构节点
	 * @param orgCode
	 * @return
	 */
	public static BizOrgNode getNodeByOrgCode(String orgCode) {
		AssertHelper.notEmpty_assert(orgCode,"机构代码不能为空");
		return getOrgNodeByOrgCode(orgCode,getTopNode());
	}
	/**
	 * 递归查找
	 * @param orgCode
	 * @param legalEntityNode
	 * @return
	 */
	private static BizOrgNode getOrgNodeByOrgCode(String orgCode,BizOrgNode orgNode){
		if(orgCode.equals(orgNode.getCode())){
			return orgNode;
		}
		Collection<OrgNode> childs=orgNode.getPosterities().values();
		for(OrgNode child:childs){
			if(orgCode.equals(child.getCode())){
				return (BizOrgNode) child;
			}
		}
		return null;
	}
	/**
	 * 根据orgCode获取上级机构节点
	 * @param orgCode
	 * @return
	 */
	public static BizOrgNode getParentNodeByOrgCode(String orgCode) {
		return (BizOrgNode) getNodeByOrgCode(orgCode).getParent();
	}
	/**
	 * 根据orgCode获取下级机构节点
	 * @param orgCode
	 * @return
	 */
	public static Collection<Node> getChildNodeByCode(String orgCode) {
		return getNodeByOrgCode(orgCode).getChildren();
	}
	public static void refreshBizOrgNode() {
		CacheUtils.putCacheObject(OrgCacheService.DEPT_CACHE_ID,null);
	}
}
