package com.deloitte.tms.base.cache.service.impl;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.deloitte.tms.base.cache.model.BizOrgNode;
import com.deloitte.tms.base.cache.service.OrgPathProvider;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.deloitte.tms.pl.core.dao.IDao;
import com.deloitte.tms.pl.orgpath.model.OrgPath;
import com.deloitte.tms.pl.version.party.model.organization.node.Node;
import com.deloitte.tms.pl.version.party.model.organization.node.TreeNode;

@Component(value="orgPathProvider")
public class OrgPathProviderImpl implements OrgPathProvider{
	
	@Resource(name=IDao.BEAN_ID_SIMPLE)
	IDao dao;
	
	public void execRefreshOrgPath(){
		BizOrgNode topNode=buildOrgTree();
		List<OrgPath> orgPaths=new ArrayList<OrgPath>();
		buildOrgPahs(topNode,orgPaths);
		execClearOldData();
		saveOrgPaths(orgPaths);
	}
	/**
	 * 创建内存树结构
	 * @return
	 */
	private BizOrgNode buildOrgTree(){
		OrgCacheProvider deptProvider=SpringUtil.getBean(OrgCacheProvider.BEAN_ID);
		BizOrgNode topDeptNode=(BizOrgNode) deptProvider.build(null);
		return topDeptNode;
	}
	/**
	 * 生成orgpath集合
	 * @param topNode
	 * @return
	 */
	List<OrgPath> buildOrgPahs(BizOrgNode topNode,List<OrgPath> orgPaths){		
		Enumeration<Node> enumeration = topNode.children();
		while (enumeration.hasMoreElements()) {
			BizOrgNode childNode=(BizOrgNode)enumeration.nextElement();
			OrgPath orgPath=buildOrgPath(childNode);
			orgPaths.add(orgPath);
			buildOrgPahs(childNode,orgPaths);
		}
		return orgPaths;
	}
	/**
	 * 从BizOrgNode生成OrgPath
	 * @param bizOrgNode
	 * @return
	 */
	OrgPath buildOrgPath(BizOrgNode bizOrgNode){
		OrgPath orgPath=new OrgPath();
		Stack<TreeNode> stack = getTreeNodeStack((TreeNode) bizOrgNode);
		int orglevel = stack.size();
		orgPath.setOrglevelsort(orglevel);
		orgPath.setOrgid(bizOrgNode.getId());
		orgPath.setOrgcode(bizOrgNode.getCode());
		orgPath.setOrgname(bizOrgNode.getName());
		orgPath.setParentCode(bizOrgNode.getParentId());
		StringBuffer orgseq = new StringBuffer();
		BizOrgNode orgPathtemp;
		for (int i = 0; i < orglevel; i++) {
			orgPathtemp = (BizOrgNode) stack.pop();
			if (i == 0) {
				orgPath.setOrgid1(orgPathtemp.getId());
				orgPath.setOrgcode1(orgPathtemp.getCode());
				orgPath.setOrgname1(orgPathtemp.getName());
			} else if (i == 1) {
				orgPath.setOrgid2(orgPathtemp.getId());
				orgPath.setOrgcode2(orgPathtemp.getCode());
				orgPath.setOrgname2(orgPathtemp.getName());
			} else if (i == 2) {
				orgPath.setOrgid3(orgPathtemp.getId());
				orgPath.setOrgcode3(orgPathtemp.getCode());
				orgPath.setOrgname3(orgPathtemp.getName());
			} else if (i == 3) {
				orgPath.setOrgid4(orgPathtemp.getId());
				orgPath.setOrgcode4(orgPathtemp.getCode());
				orgPath.setOrgname4(orgPathtemp.getName());
			} else if (i == 4) {
				orgPath.setOrgid5(orgPathtemp.getId());
				orgPath.setOrgcode5(orgPathtemp.getCode());
				orgPath.setOrgname5(orgPathtemp.getName());
			}
			orgseq.append(orgPathtemp.getCode() + ".");
		}
		orgPath.setOrgseq(orgseq.toString());
		return orgPath;
	}
	/**
	 * 清除旧数据
	 */
	void execClearOldData(){
		dao.executeProduce("delete from "+OrgPath.TABLE, new HashMap());
	}
	/**
	 * 保存新数据
	 * @param orPaths
	 */
	void saveOrgPaths(List<OrgPath> orPaths){
		dao.saveAll(orPaths);
	}
	/**
	 * 组织orgseq
	 * @param treeNode
	 * @return
	 */
	String buildTreeSeq(TreeNode treeNode) {
		Stack<TreeNode> stack = getTreeNodeStack(treeNode);
		int orglevel = stack.size();
		StringBuffer orgseq = new StringBuffer();
		BizOrgNode nodetemp;
		for (int i = 0; i < orglevel; i++) {
			nodetemp = (BizOrgNode) stack.pop();
			orgseq.append(nodetemp.getCode() + ".");
		}
		return orgseq.toString();
	}
	/**
	 * 获取节点栈
	 * @param treeNode
	 * @return
	 */
	public Stack<TreeNode> getTreeNodeStack(TreeNode treeNode) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		if (treeNode != null) {
			stack.add(treeNode);
			TreeNode parent = (TreeNode) treeNode.getParent();
			while (parent != null&&parent.getId()!=null) {
				stack.add(parent);
				parent =(TreeNode) parent.getParent() ;
			}
		}
		return stack;
	}
}
