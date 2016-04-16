package com.deloitte.tms.pl.version.party.model.organization.node;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Vector;

import org.springframework.util.Assert;

import com.deloitte.tms.pl.core.commons.annotation.ModelProperty;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.enums.ErrorCode;
import com.deloitte.tms.pl.version.party.model.ExtraRelationData;
import com.deloitte.tms.pl.version.party.model.organization.criteria.CriteriaFilter;
import com.deloitte.tms.pl.version.party.model.organization.filter.Filter;
import com.deloitte.tms.pl.version.party.model.organization.iterator.Iterator;
import com.deloitte.tms.pl.version.party.model.organization.iterator.TreeIterator;

/**
 * 组织的实现
 * 
 * @author dada
 *
 */
public class TreeNode implements Node {
	
	@ModelProperty(comment="主键")
	protected String id;

	@ModelProperty(comment="定义名称")	
	protected String name;

	private Node parent;
	
	private Vector<Node> children = new Vector<Node>();

	protected List<ExtraRelationData> extraRelationData=new ArrayList<ExtraRelationData>();	
	
	/** 机构辖属全局机构(含本机构) **/
	protected Map<Serializable, TreeNode> posterities = new HashMap<Serializable, TreeNode>();
	
	/**
	 * 不允许不能完成posterities的初始化
	 */
	@SuppressWarnings("unused")
	private TreeNode() {
		
	}
	
	public TreeNode(String id, String orgName) {
		super();
		this.name = orgName;
		this.id=id;
		this.posterities.put(id, this);
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

	static public final Enumeration<Node> EMPTY_ENUMERATION
	= new Enumeration<Node>() {
	    public boolean hasMoreElements() { return false; }
	    public Node nextElement() {
		throw new NoSuchElementException("No more elements");
	    }
    };

	/**
	 * 获取附加类型数据
	 * 
	 * @param relCode
	 * @author dada
	 */
	public Collection<ExtraRelationData> findExtraRelationDatas(String relCode) {
		Collection<ExtraRelationData> retval = new ArrayList<ExtraRelationData>();
		for (ExtraRelationData relation : extraRelationData) {
			if (relation.getRelCode().trim().equalsIgnoreCase(relCode)) {
				retval.add(relation);
			}
		}
		return retval;
	}
	/**
	 * 获取一条附加数据
	 * 
	 * @param relCode
	 * @author dada
	 */
	public Object findExtraRelationData(String relCode) {
		Collection<ExtraRelationData> extraRelationDatas= findExtraRelationDatas(relCode);
		if(extraRelationDatas.size()==1)
		{
			return extraRelationDatas.iterator().next().getExtraData();
		}else {
			if(extraRelationDatas.size()>1)
			{
				throw new BusinessException(ErrorCode.DATA, this +" 存在多条同类型" + relCode);
			}else {
				return null;
			}
		}
	}
	public void addExtraRelationData(ExtraRelationData relation) throws BusinessException {
		String relCode=relation.getRelCode();
		Object oldData=findExtraRelationData(relCode);
		if(oldData==null)
		{
			extraRelationData.add(relation);
		}
		else {				
			throw new BusinessException(ErrorCode.DATA, this +" 存在多条同类型" + relation.getRelCode());
		}
	}
	/**
	 * 在指定位置插入子节点
	 * 
	 * @param node 组织子节点
	 * @param index 位置
	 */
	public void insert(Node node, int index) {
		Assert.notNull(node);
		if (isNodeAncestor(node)) {
			throw new IllegalArgumentException("[Assertion failed] - this argument is an ancestor");
		}
		
		Node oldParent = node.getParent();
		if (oldParent != null) {
			oldParent.remove(node);
		}
		node.setParent(this);
		if (children == null) {
			children = new Vector<Node>();
		    }
		children.insertElementAt(node, index);
	}
	
	/**
	 * 插入子节点(默认最后位置)
	 * 
	 * @param node
	 */
	public void add(Node node) {
		if(node != null && node.getParent() == this) {
			insert(node, getChildCount() - 1);
		} else {
			insert(node, getChildCount());
		}
	}
	
	/**
	 * 移除指定位置的子节点
	 * 
	 * @param index 位置
	 */
	public void remove(int index) {
		Node child = (Node)getChildAt(index);
		children.removeElementAt(index);
		child.setParent(null);
	}
	
	/**
	 * 移除指定子节点
	 * 
	 * @param node 组织子节点
	 */
	public void remove(Node node) {
		Assert.notNull(node);
		if (!isNodeChild(node)) {
			throw new IllegalArgumentException("[Assertion failed] - this argument is not a child");
		}
		remove(getIndex(node));
	}
	
	/**
	 * 移除所有子节点
	 * 
	 */
	public void removeAll() {
		for (int i = 0; i < children.size(); i++) {
			remove(i);
		}
	}
	
	/**
	 * 从父节点中移除自己
	 * 
	 */
	public void removeFromParent() {
		Node parent = (Node)getParent();
		if (parent != null) {
		    parent.remove(this);
		}
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
	
	/**
	 * 获取指定位置的子节点
	 * 
	 * @param index
	 * @return
	 */
	public Node getChildAt(int index) {
		if (children == null) {
		    throw new ArrayIndexOutOfBoundsException("[Assertion failed] - node has no children");
		}
		return (Node)children.elementAt(index);
	}
	
    /**
     * 获取第一个子节点
     * 
     * @return
     */
    public Node getFirstChild() {
    	if (getChildCount() == 0) {
    		throw new NoSuchElementException("[Assertion failed] - node has no children");
    	}
    	return getChildAt(0);
    }
    
    /**
     * 返回所有子节点
     * 
     * @return
     */
    public Collection<Node> getChildren() {
    	return children;
    }
	
	/**
	 * 查找某一子节点的位置
	 * 
	 * 如果该子节点不存在则返回-1
	 * 
	 * @param node
	 * @return
	 */
	public int getIndex(Node node) {
		Assert.notNull(node);
		
		if (!isNodeChild(node)) {
		    return -1;
		}
		return children.indexOf(node);
	}
	
	/**
	 * 是否叶子节点
	 * 
	 * @return
	 */
	public boolean isLeaf() {
		return (getChildCount() == 0);
	}
	
	/**
	 * 是否根节点
	 * 
	 * @return
	 */
	public boolean isRoot() {
		return (getParent() == null);
	}
	
	/**
	 * 获取根节点
	 * 
	 */
	public Node getRoot() {
		Node ancestor = this;
		Node previous;
		do {
		    previous = ancestor;
		    ancestor = ancestor.getParent();
		} while (ancestor != null);

		return previous;
	}
	
	/**
	 * 获取层深
	 * 
	 * @return
	 */
	public int getLevel() {
		Node ancestor = this;
		int levels = 0;
		while((ancestor = ancestor.getParent()) != null){
		    levels++;
		}
		return levels;
	}
	
	/**
	 * 获取子节点数目
	 * 
	 * @return
	 */
	public int getChildCount() {
		if (children == null) {
		    return 0;
		} else {
		    return children.size();
		}
	}
	
	/**
	 * 获取指定节点的相邻节点
	 * 
	 * @return
	 */
	public Node getChildAfter(Node node) {
		Assert.notNull(node);
		
		int index = getIndex(node);

		if (index == -1) {
		    throw new IllegalArgumentException("[Assertion failed] - node is not a child");
		}

		if (index < getChildCount() - 1) {
		    return getChildAt(index + 1);
		} else {
		    return null;
		}
	}
	
	/**
	 * 获取相邻节点(右节点)
	 * 
	 */
	public Node getNextSibling() {
		Node retval;
		
		if (isRoot()) {
			retval = null;
		} else {
			retval = parent.getChildAfter(this);
		}
		
		if (retval != null && !isNodeSibling(retval)) {
		    throw new Error("[Assertion failed] - child of parent is not a sibling");
		}
		
		return retval;
	}
	
	/**
	 * 遍历子节点
	 * 
	 * @return
	 */
	public Enumeration<Node> children() {
		if (children == null) {
		    return EMPTY_ENUMERATION;
		} else {
		    return children.elements();
		}
	}
	
	/** 获取所有祖先节点 */
	public List<Node> ancestors() {
		Node current = this;
		Node parent;
		List<Node> retval = new ArrayList<Node>();
		do {
			parent = current.getParent();
			current = parent;
			if (current != null) {
				retval.add(current);
			}
		} while (current != null);
		return retval;
	}
	
	/**
	 * 获取所有叶子节点
	 * 
	 * @return List<Node>
	 */
	public List<Node> getLeaves() {
		List<Node> retval = new ArrayList<Node>();
		Iterator iterator = iterator(new Filter() {

			public boolean isPermitted(Object obj) {
				boolean retval = false;
				
				Node node = (Node) obj;
				if (node.isLeaf()) {
					retval = true;
				}
				return retval;
			}
			
		});
		
		for (; iterator	.hasNext();) {
			Node node = (Node) iterator.next();
			retval.add(node);
		}
		return retval;
	}
	
	/**
	 * 遍历结构树
	 * 
	 * @return
	 */
	public Iterator iterator() {
		return new TreeIterator(this);
	}
	
	/**
	 * 按照filter过滤结构树
	 * 
	 * @param filter 过滤器
	 * @return
	 */
	public Iterator iterator(Filter filter) {
		return new TreeIterator(this, filter);
	}
	
	/**
	 * 按照filter链过滤结构树
	 * 
	 * @param filterChain 过滤链 List<TreeFilter>
	 * @return
	 */
	public Iterator iterator(List<Filter> filterChain) {
		return new TreeIterator(this, filterChain);
	}
	
	/**
	 * shadowClone
	 * @throws CloneNotSupportedException 
	 * 
	 */
	public Object clone() {
		TreeNode node = null;
		try {
			node = (TreeNode) super.clone();
			node.children = new Vector<Node>();
			node.parent = null;
			return node;
		} catch (Exception e) {
			throw new Error(e.toString());
		}
		
	}
	
	/**
	 * clone自己为根节点的树结构
	 * 
	 * @return
	 * @throws CloneNotSupportedException 
	 */
	public Node deepClone() {
		Node cloneNode;
		cloneNode = (Node) this.clone();
		Enumeration<Node> enumeration = children();
		while (enumeration.hasMoreElements()) {
			Node child = (Node) enumeration.nextElement();
			cloneNode.add(child.deepClone());
		}
		return cloneNode;
	}
	
	/**
	 * clone按照filter过滤后的结构树
	 * 深层clone
	 * 
	 * @param filter 过滤器
	 * @return
	 */
	public Node deepClone(Filter filter) {
		Node cloneNode;
		cloneNode = (Node) this.clone();
		Enumeration<Node> enumeration = children();
		while (enumeration.hasMoreElements()) {
			Node child = (Node) enumeration.nextElement();
			if (filter.isPermitted(child)) {
				cloneNode.add(child.deepClone(filter));
			}
		}
		return cloneNode;
	}
	
	/**
	 * clone按照filter链过滤后得结构树
	 * 深层clone
	 * 
	 * @param filterChain 过滤链 List<TreeFilter>
	 * @return
	 */
	public Node deepClone(List filterChain) {
		Node cloneNode;
		cloneNode = (Node) this.clone();
		Enumeration<Node> enumeration = children();
		while (enumeration.hasMoreElements()) {
			Node child = (Node) enumeration.nextElement();
			boolean token = true;
			for (java.util.Iterator iter = filterChain.iterator(); iter.hasNext();) {
				Filter filter = (Filter) iter.next();
				if (!filter.isPermitted(child)) {
					token = false;
					break;
				}
			}
			if (token) {
				cloneNode.add(child.deepClone(filterChain));
			}
		}
		return cloneNode;
		
	}
	
	/**
	 * clone按照filter链过滤后得结构树
	 * 深层clone
	 * 
	 * @param filterChain 过滤链 List<TreeFilter>
	 * @return
	 */
	public Node deepClone(CriteriaFilter filter) {
		List<Filter> filterChain = filter.getFilterChain();
		return deepClone(filterChain);
	}
	
	/**
	 * 查找匹配标准过滤器的节点
	 * 
	 * @param filter 标准过滤器
	 * @return 找到的第一个节点
	 */
	public Node find(CriteriaFilter filter) {
		Node retval = null;
		
		List<Filter> chainFilter = filter.getFilterChain();
		Iterator iterator = iterator(chainFilter);
		for (; iterator.hasNext();) {
			Node node = (Node) iterator.next();
			retval = node;
		}
		return retval;
	}
	
	/**
	 * 查找所有匹配标准过滤器的节点
	 * 
	 * @param filter 表准过滤器
	 * @return 所有匹配的节点
	 */
	public List<Node> findAll(CriteriaFilter filter) {
		List<Node> retval = new ArrayList<Node>();
		List<Filter> chainFilter = filter.getFilterChain();
		Iterator iterator = iterator(chainFilter);
		for (; iterator.hasNext();) {
			Node node = (Node) iterator.next();
			retval.add(node);
		}
		return retval;
	}

	/**
	 * 判断是否本节点的祖先节点
	 * 
	 * @param node
	 * @return
	 */
	protected boolean isNodeAncestor(Node node) {
		boolean retval = false;
		
		if (node == null) {
			retval = false;
		}
		Node ancestor = this;
		do {
			if (ancestor == node) {
				retval = true;
			}
		} while ((ancestor = ancestor.getParent()) != null);
		return retval;
	}
	
	/**
	 * 判断是否本节点子节点
	 * 
	 * @param node
	 * @return
	 */
	protected boolean isNodeChild(Node node) {
		boolean retval;
		
		if (node == null) {
			retval = false;
		}
		if (getChildCount() == 0) {
			retval = false;
		} else {
			retval = (node.getParent() == this);
		}
		return retval;
	}
	
	/**
	 * 判断是否有共同的父节点
	 * 
	 * @param node
	 * @return
	 */
	protected boolean isNodeSibling(Node node) {
    	boolean retval;

    	if (node == null) {
    		retval = false;
    	} else if (node == this) {
    		retval = true;
    	} else {
    	    Node parent = getParent();
    	    retval = (parent != null && parent == node.getParent());

    	    if (retval && !(((TreeNode)getParent()).isNodeChild(node))) {
    	    	throw new Error("[Assertion failed] - sibling has different parent");
    	    }
    	}
    	return retval;
    }

}
