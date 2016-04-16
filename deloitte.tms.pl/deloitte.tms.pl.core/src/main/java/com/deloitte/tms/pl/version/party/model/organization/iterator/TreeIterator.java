package com.deloitte.tms.pl.version.party.model.organization.iterator;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Stack;

import org.springframework.util.Assert;

import com.deloitte.tms.pl.version.party.model.organization.filter.Filter;
import com.deloitte.tms.pl.version.party.model.organization.node.Node;

public class TreeIterator implements Iterator {
	
	private List<Filter> filterChain;
	
	private Stack<Node> stack;
	
	public TreeIterator(Node node) {
		super();
		stack = new Stack<Node>();
		stack.push(node);
		this.filterChain = null;
	}
	
	public TreeIterator(Node node, Filter filter) {
		super();
		Assert.notNull(node);
		Assert.notNull(filter);
		stack = new Stack<Node>();
		stack.push(node);
		this.filterChain = new ArrayList<Filter>();
		this.filterChain.add(filter);
	}
	
	public TreeIterator(Node node, List<Filter> filterChain) {
		super();
		Assert.notNull(node);
		Assert.notEmpty(filterChain);
		stack = new Stack<Node>();
		stack.push(node);
		this.filterChain = filterChain;
	}
	
	public boolean hasNext() {
		while(stack.size() > 0) {
			Node node = (Node) stack.peek();
			if (isPermitted(node)) {
				return true;
			}
			stack.pop();
			Enumeration<Node> children = node.children();
			while(children.hasMoreElements()) {
				stack.push(children.nextElement());
			}
			
		}
		return false;
	}
	
	public Node next() {
		Node retval = (Node) stack.pop();
		Enumeration<Node> children = retval.children();
		while(children.hasMoreElements()) {
			stack.push(children.nextElement());
		}
		return retval;
	}

	private boolean isPermitted(Node node) {
		if (filterChain == null) {
			return true;
		}
		for (java.util.Iterator<Filter> iter = filterChain.iterator(); iter.hasNext();) {
			Filter filter = (Filter) iter.next();
			if (!filter.isPermitted(node)) {
				return false;
			}
		}
		return true;
	}

	public void remove() {
		
	}

}
