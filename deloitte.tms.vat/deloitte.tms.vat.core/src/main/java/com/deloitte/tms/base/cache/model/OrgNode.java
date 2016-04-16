package com.deloitte.tms.base.cache.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.version.party.model.organization.OrganizationNode;
import com.deloitte.tms.pl.version.party.model.organization.node.Node;
import com.deloitte.tms.pl.version.party.model.organization.node.TreeNode;

/**
 * 组织节点基类
 * @author bo.wang
 * @create 2016年3月24日 上午11:58:17 
 * @version 1.0.0
 */
public class OrgNode extends TreeNode {
	
	@ModelProperty(comment="代码")
	String code;
	
	@ModelProperty(comment="类型",des="如果是相同数据存在一张表,用于表示数据代表什么类别的基础数据")
	String type;
	
	@ModelProperty(comment="数据分类",des="用于基础数据的分类")
	String dataType;	
	
	@ModelProperty(comment="分类",des="用于区别数据的层级,第一层为1")
	Integer dataLevel;
	
	@ModelProperty(comment="数据序列")
	String dataSeq;
	
	@ModelProperty(comment="描述")
	String des;
	
	@ModelProperty(comment="上级")
	String parentId;	
	
	@ModelProperty(comment="第一个版本ID")
	Long firstVersionId;
	
	@ModelProperty(comment="版本号")
	String version;
	
	@ModelProperty(comment="复制关联")
	String copyRelations;
	
	@ModelProperty(comment="有效期起")
	Date effectDate;
	
	@ModelProperty(comment="有效期止")
	Date quitDate;
	
	@ModelProperty(comment="是否有效")
	String flag;
	
	/** 机构辖属全局机构(含本机构) **/
	protected Map<Serializable, OrgNode> posterities = new HashMap<Serializable, OrgNode>();
	
	public OrgNode(String id, String orgName) {
		super(id,orgName);
	}	
	public void add(Node node) {
		super.add(node);
		OrgNode temp = this;
		OrgNode deptnode = (OrgNode) node;
		do {
			temp.getPosterities().put(deptnode.getId(), deptnode);
		} while ((temp = (OrgNode) temp.getParent()) != null);
	}
	public Object clone() {
		OrgNode node = (OrgNode) super.clone();
		node.setId(id);
		node.setCode(code);
		node.setName(name);
		node.setType(type);
		node.setDataType(dataType);
		node.setDataLevel(dataLevel);
		node.setDataSeq(dataSeq);
		node.setDes(des);
		node.setParentId(parentId);
		node.setFirstVersionId(firstVersionId);
		node.setVersion(version);
		node.setEffectDate(effectDate);
		node.setQuitDate(quitDate);
		node.setCopyRelations(copyRelations);
		node.setFlag(flag);
		node.posterities = new HashMap<Serializable, OrgNode>();
		node.posterities.put(id, node);
		return node;
	}
	/**
	 * clone自己为根节点的树结构
	 * 
	 * @return
	 * @throws CloneNotSupportedException
	 */
	public Node deepClone() {
		OrganizationNode cloneNode;
		cloneNode = (OrganizationNode) this.clone();
		Enumeration<Node> enumeration = children();
		while (enumeration.hasMoreElements()) {
			OrganizationNode child = (OrganizationNode) enumeration
					.nextElement();
			cloneNode.add(child.deepClone());
		}
		return cloneNode;
	}
	public Map<Serializable, OrgNode> getPosterities() {
		return posterities;
	}

	public void setPosterities(Map<Serializable, OrgNode> posterities) {
		this.posterities = posterities;
	}	
	
	public String toString() {
		String warp="";
		for(int i=0;i<getLevel();i++){
			warp=" "+warp;
		}
		return warp+"ID：" + id + " 名称：" + name + " 代码：" + code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Integer getDataLevel() {
		return dataLevel;
	}

	public void setDataLevel(Integer dataLevel) {
		this.dataLevel = dataLevel;
	}

	public String getDataSeq() {
		return dataSeq;
	}

	public void setDataSeq(String dataSeq) {
		this.dataSeq = dataSeq;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Long getFirstVersionId() {
		return firstVersionId;
	}

	public void setFirstVersionId(Long firstVersionId) {
		this.firstVersionId = firstVersionId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCopyRelations() {
		return copyRelations;
	}

	public void setCopyRelations(String copyRelations) {
		this.copyRelations = copyRelations;
	}

	public Date getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}

	public Date getQuitDate() {
		return quitDate;
	}

	public void setQuitDate(Date quitDate) {
		this.quitDate = quitDate;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}
