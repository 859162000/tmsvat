package com.deloitte.tms.base.cache.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.version.party.model.organization.OrganizationNode;
import com.deloitte.tms.pl.version.party.model.organization.node.Node;

/**
 * 末端节点基类
 * @author bo.wang
 * @create 2016年3月24日 上午11:58:17 
 * @version 1.0.0
 */
public class LeafNode{
	
	@ModelProperty(comment="主键")
	String id;

	@ModelProperty(comment="定义名称")
	String name;
	
	@ModelProperty(comment="代码")
	String code;
	
	@ModelProperty(comment="类型",des="如果是相同数据存在一张表,用于表示数据代表什么类别的基础数据")
	String type;
	
	@ModelProperty(comment="数据分类",des="用于基础数据的分类")
	String dataType;	
	
	@ModelProperty(comment="描述")
	String des;
	
	@ModelProperty(comment="上级")
	String parentId;
	
	Node parent;
	
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
	protected Map<Serializable, LeafNode> posterities = new HashMap<Serializable, LeafNode>();
	
	public LeafNode(String id, String orgName) {
		super();
		this.name = orgName;
		this.id=id;
		this.posterities.put(id, this);
	}	
	/**
	 * 不允许不能完成posterities的初始化
	 */
	@SuppressWarnings("unused")
	private LeafNode() {
		
	}
	public Object clone() {
		LeafNode node = new LeafNode();
		node.setId(id);
		node.setCode(code);
		node.setName(name);
		node.setType(type);
		node.setDataType(dataType);
		node.setDes(des);
		node.setParentId(parentId);
		node.setFirstVersionId(firstVersionId);
		node.setVersion(version);
		node.setEffectDate(effectDate);
		node.setQuitDate(quitDate);
		node.setCopyRelations(copyRelations);
		node.setFlag(flag);
		node.posterities = new HashMap<Serializable, LeafNode>();
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
		return cloneNode;
	}
	public Map<Serializable, LeafNode> getPosterities() {
		return posterities;
	}

	public void setPosterities(Map<Serializable, LeafNode> posterities) {
		this.posterities = posterities;
	}	
	
	public String toString() {
		return "ID：" + id + " 名称：" + name + " 代码：" + code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	/**
	 * 设置父节点
	 * 
	 * @param node 组织父节点
	 */
	public void setParent(Node node) {
		this.parent = node;
	}
	
	/**
	 * 获取父节点
	 * 
	 * @return 父节点
	 */
	public Node getParent() {
		return parent;
	}
	
	
}
