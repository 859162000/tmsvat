package com.deloitte.tms.pl.security.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.deloitte.tms.pl.security.model.impl.UserDept;



public class FunctionTreeNodeInPL {
	
	public static final  String  equSperator="									关联的设备:";
	
	public FunctionTreeNodeInPL() {
	}

	private String id;

	private String text;

	private boolean checked;

	private String pid;

	private Map<String, Object> attributes;

	private String target;

	private String state;
	
	private String iconCls;
	
	private String action;
	
	private String viewId;
	
	private String index;
	
	private String navigationFlag;
	
	private String strTarget;
	
	private String orgCode;
	
	private String description;
	
	private Integer virtual;
	
	public Integer getVirtual() {
		return virtual;
	}

	public void setVirtual(Integer virtual) {
		this.virtual = virtual;
	}

	private String bizOrgCode;
	
	
	private String orgType;
	
	public String getBizOrgCode() {
		return bizOrgCode;
	}

	public void setBizOrgCode(String bizOrgCode) {
		this.bizOrgCode = bizOrgCode;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	/**  
	 * 返回 orgCode 的值   
	 * @return orgCode  
	 */
	
	public String getOrgCode() {
		return orgCode;
	}

	/**  
	 * 设置 orgCode 的值  
	 * @param orgCode
	 */
	
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getStrTarget() {
		return strTarget;
	}

	public void setStrTarget(String strTarget) {
		this.strTarget = strTarget;
	}

	public String getNavigationFlag() {
		return navigationFlag;
	}

	public void setNavigationFlag(String navigationFlag) {
		this.navigationFlag = navigationFlag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	private List<FunctionTreeNodeInPL> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<FunctionTreeNodeInPL> getChildren() {
		return children;
	}

	public void setChildren(List<FunctionTreeNodeInPL> children) {
		this.children = children;
	}
	
	

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	

	public String getViewId() {
		return viewId;
	}

	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}





	@Override
	public String toString() {
		return "TreeNode [id=" + id + ", text=" + text + ", checked=" + checked
				+ ", pid=" + pid + ", attributes=" + attributes + ", target="
				+ target + ", state=" + state + ", iconCls=" + iconCls
				+ ", action=" + action + ", viewId=" + viewId + ", index="
				+ index + ", navigationFlag=" + navigationFlag + ", strTarget="
				+ strTarget + ", orgCode=" + orgCode + ", description="
				+ description + ", virtual=" + virtual + ", bizOrgCode="
				+ bizOrgCode + ", orgType=" + orgType + ", children="
				+ children + "]";
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attributes == null) ? 0 : attributes.hashCode());
		result = prime * result + (checked ? 1231 : 1237);
		result = prime * result
				+ ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pid == null) ? 0 : pid.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FunctionTreeNodeInPL other = (FunctionTreeNodeInPL) obj;
		if (attributes == null) {
			if (other.attributes != null)
				return false;
		} else if (!attributes.equals(other.attributes))
			return false;
		if (checked != other.checked)
			return false;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pid == null) {
			if (other.pid != null)
				return false;
		} else if (!pid.equals(other.pid))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	// 兄弟节点横向排序
	public void sortChildren() {
		if (children!=null && children.size() != 0) {
			// 对本层节点进行排序（可根据不同的排序属性，传入不同的比较器，这里传入ID比较器）
			Collections.sort(children, new Comparator() {

				@Override
				public int compare(Object o1, Object o2) {
					int j1 = Integer.parseInt(((FunctionTreeNodeInPL) o1).index);
					int j2 = Integer.parseInt(((FunctionTreeNodeInPL) o2).index);
					return (j1 < j2 ? -1 : (j1 == j2 ? 0 : 1));

				}
			});
			// 对每个节点的下一层节点进行排序
			for (Iterator it = children.iterator(); it.hasNext();) {
				((FunctionTreeNodeInPL) it.next()).sortChildren();
			}
		}
	}
	
	/**
	 * 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param modelList
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public static List<FunctionTreeNodeInPL> generalConvertTreeNodeList(
			List modelList) {
		
		System.out.println("convertTreeNodeList starting: ");
		
		List<FunctionTreeNodeInPL> nodes = null;

		if (modelList != null) {
			nodes = new ArrayList<FunctionTreeNodeInPL>();
			for (Object modelOne : modelList) {
				FunctionTreeNodeInPL node = generalConvertTreeNode(modelOne);
				if (node != null) {
					nodes.add(node);
				}
			}
		}

		return nodes;
	}
	
	/**
	 * 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param cla
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 * @author tigchen
	 */
	public static FunctionTreeNodeInPL generalConvertTreeNode(Object cla) {
		
		FunctionTreeNodeInPL node = null;
		try{
		System.out.println("convertTreeNode starting: ");
		
		if (cla != null) {
			node = new FunctionTreeNodeInPL();
			
			UserDept legEqu=null;
			System.out.println("---------TmsMdLegalEquipment.class.getName().equals(   cla.getClass().getName() )"+  UserDept.class.getName()  + " : "+ cla.getClass().getName()  );
			
			if(cla instanceof UserDept){
				System.out.println("good ------------------convertTreeNode starting: ");
				
				legEqu = (UserDept)cla;
				
			}else{
				System.out.println("TreeNode > generalConvertTreeNode now no support "+cla.getClass());
				//to-do for other class's instance will write a common convert methord
				return null;
			}
			
		
			
			node.setId(legEqu.getId());
			//node.setPid(legEqu.getParentId());
			
			StringBuffer nameWithPrint=new StringBuffer();
			//nameWithPrint.append(legEqu.getLegalEntityName() ).append(equSperator).append(legEqu.getEquipmentName());
			
			
			//node.setText("default");
			node.setText(nameWithPrint.toString());// put into attribute1 ?
			
			
			 Map<String, Object> map = new HashMap<String, Object>();
			 
			 	map.put("legalEquName", node.getText());
				//map.put("legalName", legEqu.getLegalEntityName());
				//map.put("equName", legEqu.getEquipmentName());
				map.put("newLegalId", "");
				map.put("newLegalName", "");
				map.put("newEquId", "");
				map.put("newEquName", "");
				
				/*
				 * var attributes1=legalName + spe + equName + spe + newLegalId + spe + newLegalName + spe + newEquId + spe + newEquName;
				 */

				node.setAttributes(map);


			 
			

	/*		node.setId(cla.getId());
			node.setChecked(false);
			node.setText(cla.getOrgName());
			node.setDescription(cla.getDes());
			node.setOrgCode(cla.getOrgCode());	
		    node.setPid(cla.getParentId());
		    
		    node.setVirtual(cla.getVirtual());
		    node.setBizOrgCode(cla.getBizOrgCode());
		    node.setOrgType(cla.getOrgType());*/
		    

		    
		    System.out.println("convertTreeNode: "+node);

			

		}else{
			
			System.out.println("--generalConvertTreeNode-------- get null");
		}
		
		return node;
		
	}catch(Exception e){
		
		e.printStackTrace();
		
		System.out.println("----generalConvertTreeNode-------------"+e.getMessage());
		
		return node;
	}
		
		
		
	}

	
	
	/**
	 * 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param modelList
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 * @author tigchen
	 */
	public static List<FunctionTreeNodeInPL> generalConvertTreeNodeList4InPara(
			List modelList) {
		
		System.out.println("convertTreeNodeList starting: ");
		
		List<FunctionTreeNodeInPL> nodes = null;

		if (modelList != null) {
			nodes = new ArrayList<FunctionTreeNodeInPL>();
			for (Object modelOne : modelList) {
				FunctionTreeNodeInPL node = generalConvertTreeNode4InPara(modelOne);
				if (node != null) {
					nodes.add(node);
				}
			}
		}

		return nodes;
	}
	
	
	/**
	 * 
	 *〈一句话功能简述〉 
	 * 功能详细描述
	 * @param cla
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 * @author tigchen
	 */
	public static FunctionTreeNodeInPL generalConvertTreeNode4InPara(Object cla) {
		
		FunctionTreeNodeInPL node = null;
		try{
		System.out.println("convertTreeNode starting: ");
		
		if (cla != null) {
			node = new FunctionTreeNodeInPL();
				
			UserDept legEqu=null;
			
			if(cla instanceof UserDept){
				
				legEqu = (UserDept)cla;
				
			}else{
				System.out.println("TreeNode > generalConvertTreeNode now no support "+cla.getClass());
				//to-do for other class's instance will write a common convert methord
				return null;
			}
			
		
			
		//	node.setId(legEqu.getTmsMdLegalEnablePrint().getId());
			//node.setPid(legEqu.getTmsMdLegalEnablePrint().getParentId());
			
			//StringBuffer nameWithPrint=new StringBuffer();
			//nameWithPrint.append(legEqu.getLegalEntityName() ).append(equSperator).append(legEqu.getEquipmentName());
			
			
			
		//	node.setText(legEqu.getLegalEntityName());// put into attribute1 ?
			
			
			 Map<String, Object> map = new HashMap<String, Object>();
			 
			/* 	map.put("des", legEqu.getTmsMdLegalEnablePrint().getDes());
				map.put("isEnablePrint", legEqu.getTmsMdLegalEnablePrint().getIsEnablePrint());
				map.put("enabledFlag", legEqu.getTmsMdLegalEnablePrint().getEnabledFlag());
				map.put("effectDate", legEqu.getTmsMdLegalEnablePrint().getEffectDate());
				map.put("quitDate", legEqu.getTmsMdLegalEnablePrint().getQuitDate());
				map.put("status", legEqu.getTmsMdLegalEnablePrint().getStatus());
				map.put("legalName", legEqu.getLegalEntityName());//可能node.setText显示不同的东西
				
				map.put("enabledFlag", legEqu.getTmsMdLegalEnablePrint().getEnabledFlag());
			*/
				node.setAttributes(map);


			 
	

		    
		    System.out.println("convertTreeNode: "+node);

			

		}else{
			
			System.out.println("--generalConvertTreeNode-------- get null");
		}
		
		
		
	}catch(Exception e){
		
		e.printStackTrace();
		
		System.out.println("----generalConvertTreeNode-------------"+e.getMessage());
		
		
	}
		
		return node;
		
		
		
	}
	
	
}



