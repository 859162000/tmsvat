package com.deloitte.tms.pl.version.party.model.organization.node;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.version.party.model.organization.criteria.CriteriaFilter;
import com.deloitte.tms.pl.version.party.model.organization.filter.Filter;
import com.deloitte.tms.pl.version.party.model.organization.iterator.Iterator;


/**
 * 机构
 * 
 * @author dada
 */
public interface Node extends Cloneable {

	/**
	 * 在指定位置插入子节点
	 * 
	 * @param organization 组织子节点
	 * @param index 位置
	 */
	public void insert(Node node, int index);
	
	/**
	 * 插入子节点(默认最后位置)
	 * 
	 * @param organization
	 */
	public void add(Node node);
	
	/**
	 * 移除指定位置的子节点
	 * 
	 * @param index 位置
	 */
	public void remove(int index);
	
	/**
	 * 移除指定子节点
	 * 
	 * @param organization 组织子节点
	 */
	public void remove(Node organization);
	
	/**
	 * 移除所有子节点
	 * 
	 */
	public void removeAll();
	
	/**
	 * 从父节点中移除自己
	 * 
	 */
	public void removeFromParent();
	
	/**
	 * 设置父节点
	 * 
	 * @param organization 组织父节点
	 */
	public void setParent(Node organization);
	
	/**
	 * 获取父节点
	 * 
	 * @return 父节点
	 */
	public Node getParent();
	
	/**
	 * 获取指定位置的子节点
	 * 
	 * @param index
	 * @return
	 */
	public Node getChildAt(int index); 
	
    /**
     * 获取第一个子节点
     * 
     * @return
     */
    public Node getFirstChild();
	
	/**
	 * 查找某一子节点的位置
	 * 
	 * 如果该子节点不存在则返回-1
	 * 
	 * @param node
	 * @return
	 */
	public int getIndex(Node node);
	
	/**
	 * 是否叶子节点
	 * 
	 * @return
	 */
	public boolean isLeaf();
	
	/**
	 * 是否根节点
	 * 
	 * @return
	 */
	public boolean isRoot();
	
	/**
	 * 获取根节点
	 * 
	 * @return
	 */
	public Node getRoot();
	
	/**
	 * 获取层深
	 * 
	 * @return
	 */
	public int getLevel();
	
	/**
	 * 获取子节点数目
	 * 
	 * @return
	 */
	public int getChildCount();
	
	/**
	 * 获取相邻节点(右节点)
	 * 拥有同一个parent
	 * 
	 */
	public Node getNextSibling();
	
	/**
	 * 获取指定节点的相邻节点
	 * 
	 * @return
	 */
	public Node getChildAfter(Node node);
	
	/**
	 * 遍历子节点
	 * 
	 * @return
	 */
	public Enumeration<Node> children();
	
	/**
	 * 获取所有叶子节点
	 * 
	 * @return
	 */
	public List<Node> getLeaves();
	
	/**
	 * 遍历结构树
	 * 
	 * @return
	 */
	public Iterator iterator();
	
	/**
	 * 按照filter过滤结构树
	 * 
	 * @param filter 过滤器
	 * @return
	 */
	public Iterator iterator(Filter filter);
	
	/**
	 * 按照filter链过滤结构树
	 * 
	 * @param filterChain 过滤链 List<Filter>
	 * @return
	 */
	public Iterator iterator(List<Filter> filterChain);
	
	/**
	 * clone自己为根节点的树结构
	 * 
	 * @return
	 */
	public Node deepClone();
	
	/**
	 * clone按照filter过滤后的结构树
	 * 深层clone
	 * 
	 * @param filter 过滤器
	 * @return
	 */
	public Node deepClone(Filter filter);
	
	/**
	 * clone按照filter链过滤后得结构树
	 * 深层clone
	 * 
	 * @param filterChain 过滤链 List<Filter>
	 * @return
	 */
	public Node deepClone(List<Filter> filterChain);
	
	/**
	 * 查找匹配标准过滤器的节点
	 * 
	 * @param filter 标准过滤器
	 * @return 找到的第一个节点
	 */
	public Node find(CriteriaFilter filter);
	
	/**
	 * 查找所有匹配标准过滤器的节点
	 * 
	 * @param filter 表准过滤器
	 * @return 所有匹配的节点
	 */
	public List<Node> findAll(CriteriaFilter filter);
}
