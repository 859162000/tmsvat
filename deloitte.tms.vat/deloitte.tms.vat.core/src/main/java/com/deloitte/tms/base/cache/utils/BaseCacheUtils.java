package com.deloitte.tms.base.cache.utils;

import java.util.Collection;

import com.deloitte.tms.base.cache.model.BizOrgNode;
import com.deloitte.tms.base.cache.model.OrgNode;
import com.deloitte.tms.pl.version.party.model.organization.node.Node;

public abstract class BaseCacheUtils {
	/**
	 * 获取缓存的业务单位完整树
	 * @return
	 */
	public static OrgNode getTopNode(){
		return null;
	}
	
	public static OrgNode getNode(String orgCode) {
		return (BizOrgNode) getTopNode().getPosterities().get(orgCode);
	}
	public static OrgNode getParentNode(String orgCode) {
		return (BizOrgNode) getTopNode().getPosterities().get(orgCode).getParent();
	}
	public static Collection<Node> getChildNode(String orgCode) {
		return getTopNode().getPosterities().get(orgCode).getChildren();
	}
}
