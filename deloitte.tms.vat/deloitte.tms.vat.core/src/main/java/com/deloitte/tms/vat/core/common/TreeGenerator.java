package com.deloitte.tms.vat.core.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class TreeGenerator {
	
	public static List<FunctionTreeNode> buildTree(List<FunctionTreeNode> treeNodes) {
		List<FunctionTreeNode> results = new ArrayList<FunctionTreeNode>();

		Map<String, FunctionTreeNode> aidMap = new LinkedHashMap<String, FunctionTreeNode>();
		for (FunctionTreeNode node : treeNodes) {
			aidMap.put(node.getId(), node);
		}
		treeNodes = null;

		Set<Entry<String, FunctionTreeNode>> entrySet = aidMap.entrySet();
		for (Entry<String, FunctionTreeNode> entry : entrySet) {
			String pid = entry.getValue().getPid();
			FunctionTreeNode node = aidMap.get(pid);
			if (node == null) {
				results.add(entry.getValue());
			} else {
				List<FunctionTreeNode> children = node.getChildren();
				if (children == null) {
					children = new ArrayList<FunctionTreeNode>();
					node.setChildren(children);
					node.setState("CLOSED");
				}
				children.add(entry.getValue());
			}
			
		}
		aidMap = null;

		return results;
	}
	
	
    public static List<MenuNode> buildMenu(List<MenuNode> menuNodes){
    	List<MenuNode> results = new ArrayList<MenuNode>();
    	Map<String, MenuNode> aidMap = new LinkedHashMap<String, MenuNode>();
		for (MenuNode node : menuNodes) {
			aidMap.put(node.getMenuid(), node);
		}
		Set<Entry<String, MenuNode>> entrySet = aidMap.entrySet();
		for (Entry<String, MenuNode> entry : entrySet) {
			String pid = entry.getValue().getPmenuid();
			MenuNode node = aidMap.get(pid);
			if (node == null) {
				results.add(entry.getValue());
			} else {
				List<MenuNode> children = node.getMenus();
				if (children == null) {
					children = new ArrayList<MenuNode>();
					node.setMenus(children);					
				}
				children.add(entry.getValue());
			}
			
		}
		aidMap = null;
		
    	return results;
	}
    
    
    /**
     * @author tigchen
     *using legalEntityId as parentId, need previous in generalConvertTreeNode4InPara set legalEntityId into attributes
     * 功能详细描述
     * @param treeNodes
     * @return
     * @see [相关类/方法]（可选）
     * @since [产品/模块版本] （可选）
     */
	public static List<FunctionTreeNode> buildTreeUseLegalEntityId(List<FunctionTreeNode> treeNodes) {
		List<FunctionTreeNode> results = new ArrayList<FunctionTreeNode>();

		Map<String, FunctionTreeNode> aidMap = new LinkedHashMap<String, FunctionTreeNode>();
		for (FunctionTreeNode node : treeNodes) {
			//aidMap.put(node.getId(), node);
			
			aidMap.put((String)node.getAttributes().get("legalEntityId"), node);
		}
		treeNodes = null;

		Set<Entry<String, FunctionTreeNode>> entrySet = aidMap.entrySet();
		for (Entry<String, FunctionTreeNode> entry : entrySet) {
			String pid = entry.getValue().getPid();
			FunctionTreeNode node = aidMap.get(pid);
			if (node == null) {
				results.add(entry.getValue());
			} else {
				List<FunctionTreeNode> children = node.getChildren();
				if (children == null) {
					children = new ArrayList<FunctionTreeNode>();
					node.setChildren(children);
					node.setState("CLOSED");
				}
				children.add(entry.getValue());
			}
			
		}
		aidMap = null;

		return results;
	}


}
