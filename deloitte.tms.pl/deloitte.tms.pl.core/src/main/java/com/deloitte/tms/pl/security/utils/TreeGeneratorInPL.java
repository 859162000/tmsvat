package com.deloitte.tms.pl.security.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class TreeGeneratorInPL {
	
	public static List<FunctionTreeNodeInPL> buildTree(List<FunctionTreeNodeInPL> treeNodes) {
		List<FunctionTreeNodeInPL> results = new ArrayList<FunctionTreeNodeInPL>();

		Map<String, FunctionTreeNodeInPL> aidMap = new LinkedHashMap<String, FunctionTreeNodeInPL>();
		for (FunctionTreeNodeInPL node : treeNodes) {
			aidMap.put(node.getId(), node);
		}
		treeNodes = null;

		Set<Entry<String, FunctionTreeNodeInPL>> entrySet = aidMap.entrySet();
		for (Entry<String, FunctionTreeNodeInPL> entry : entrySet) {
			String pid = entry.getValue().getPid();
			FunctionTreeNodeInPL node = aidMap.get(pid);
			if (node == null) {
				results.add(entry.getValue());
			} else {
				List<FunctionTreeNodeInPL> children = node.getChildren();
				if (children == null) {
					children = new ArrayList<FunctionTreeNodeInPL>();
					node.setChildren(children);
					node.setState("CLOSED");
				}
				children.add(entry.getValue());
			}
			
		}
		aidMap = null;

		return results;
	}
	
	
    public static List<MenuNodeInPL> buildMenu(List<MenuNodeInPL> menuNodes){
    	List<MenuNodeInPL> results = new ArrayList<MenuNodeInPL>();
    	Map<String, MenuNodeInPL> aidMap = new LinkedHashMap<String, MenuNodeInPL>();
		for (MenuNodeInPL node : menuNodes) {
			aidMap.put(node.getMenuid(), node);
		}
		Set<Entry<String, MenuNodeInPL>> entrySet = aidMap.entrySet();
		for (Entry<String, MenuNodeInPL> entry : entrySet) {
			String pid = entry.getValue().getPmenuid();
			MenuNodeInPL node = aidMap.get(pid);
			if (node == null) {
				results.add(entry.getValue());
			} else {
				List<MenuNodeInPL> children = node.getMenus();
				if (children == null) {
					children = new ArrayList<MenuNodeInPL>();
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
	public static List<FunctionTreeNodeInPL> buildTreeUseLegalEntityId(List<FunctionTreeNodeInPL> treeNodes) {
		List<FunctionTreeNodeInPL> results = new ArrayList<FunctionTreeNodeInPL>();

		Map<String, FunctionTreeNodeInPL> aidMap = new LinkedHashMap<String, FunctionTreeNodeInPL>();
		for (FunctionTreeNodeInPL node : treeNodes) {
			//aidMap.put(node.getId(), node);
			
			aidMap.put((String)node.getAttributes().get("legalEntityId"), node);
		}
		treeNodes = null;

		Set<Entry<String, FunctionTreeNodeInPL>> entrySet = aidMap.entrySet();
		for (Entry<String, FunctionTreeNodeInPL> entry : entrySet) {
			String pid = entry.getValue().getPid();
			FunctionTreeNodeInPL node = aidMap.get(pid);
			if (node == null) {
				results.add(entry.getValue());
			} else {
				List<FunctionTreeNodeInPL> children = node.getChildren();
				if (children == null) {
					children = new ArrayList<FunctionTreeNodeInPL>();
					node.setChildren(children);
					node.setState("CLOSED");
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
	public static List<FunctionTreeNodeInPL> buildTreeUseKey(List<FunctionTreeNodeInPL> treeNodes, String key ) {
		List<FunctionTreeNodeInPL> results = new ArrayList<FunctionTreeNodeInPL>();

		Map<String, FunctionTreeNodeInPL> aidMap = new LinkedHashMap<String, FunctionTreeNodeInPL>();
		for (FunctionTreeNodeInPL node : treeNodes) {
			aidMap.put(node.getId(), node);
			
			//aidMap.put((String)node.getAttributes().get(key), node);
		}
		treeNodes = null;

		Set<Entry<String, FunctionTreeNodeInPL>> entrySet = aidMap.entrySet();
		for (Entry<String, FunctionTreeNodeInPL> entry : entrySet) {
			String pid = entry.getValue().getPid();
			FunctionTreeNodeInPL node = aidMap.get(pid);
			if (node == null) {
				results.add(entry.getValue());
			} else {
				List<FunctionTreeNodeInPL> children = node.getChildren();
				if (children == null) {
					children = new ArrayList<FunctionTreeNodeInPL>();
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
