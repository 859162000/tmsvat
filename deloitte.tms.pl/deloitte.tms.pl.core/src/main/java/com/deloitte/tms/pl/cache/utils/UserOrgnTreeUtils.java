package com.deloitte.tms.pl.cache.utils;
//package com.ling2.cache.utils;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
//
//import com.ling2.channel.core.utils.constant.DivisionLevel;
//import com.ling2.context.utils.ContextUtils;
//import com.ling2.core.commons.exception.BusinessException;
//import com.ling2.core.commons.utils.CollectionUtils;
//import com.ling2.version.party.model.organization.DepartmentNode;
//import com.ling2.version.party.model.organization.DivisionNode;
//import com.ling2.version.party.model.organization.OrganizationNode;
//
///**
// * 当前用户组织机构信息工具类
// * @author zhanglin.jiang
// *
// */
//public class UserOrgnTreeUtils {
//
//	/**
//	 * 获得当前用户最顶层的组织机构
//	 * @return
//	 */
//	private static OrganizationNode getOrganizationNode() {
//		OrganizationNode topNode = OrgTreeCacheUtils.getOrganizationNode(ContextUtils.getOrgId());
//		if(topNode == null) {
//			throw new BusinessException("当前用户未设置组织机构权限!");
//		}
//		return topNode;
//	}
//	
//	/**
//	 * 验证当前用户是否具有该机构ID的权限,如果验证通过则返回该机构，验证失败则返回NULL
//	 * @param divId
//	 * @return
//	 */
//	public static OrganizationNode getVilidOrganizationNode(String divCode) {
//		OrganizationNode topNode = getOrganizationNode();
//		OrganizationNode node = (OrganizationNode)topNode.getPosterities().get(divId);
//		if(node != null) {
//			Set<Long> divIds = ContextUtils.getEvictDivIds();
//			if(CollectionUtils.isEmpty(divIds)) {
//				return node;
//			} else {
//				OrganizationNode parentNode = node;
//				if(topNode instanceof DivisionNode && parentNode instanceof DivisionNode) {
//					while(parentNode != null) {
//						String topLevel = ((DivisionNode)topNode).getDivLevel();
//						String parentLevel = ((DivisionNode)parentNode).getDivLevel();
//						if(DivisionLevel.compareDivLevel(topLevel, parentLevel) >= 0
//								&& divIds.contains(parentNode.getId())) {
//							return null;
//						}
//						parentNode = (OrganizationNode)parentNode.getParent();
//					}
//					return node;
//				}
//			}
//		}
//		return null;
//	}
//	
//	/**
//	 * 得到当前用户有效顶层机构对象
//	 * @return
//	 */
//	public static Division getTopDivision() {
//		OrganizationNode node = getOrganizationNode();
//		if(node != null && node instanceof DivisionNode) {
//			return assembleInfo((DivisionNode)node);
//		}
//		return null;
//	}
//	
//	/**
//	 * 得到当前用户有权限的顶层机构ID
//	 * @return
//	 */
//	public static Long getTopDivId() {
//		OrganizationNode node = getOrganizationNode();
//		if(node != null) {
//			return node.getId();
//		}
//		return null;
//	}
//	
//	/**
//	 * 得到指定机构对象的子机构集合(过滤用户权限)
//	 * @return
//	 */
//	public static List<Division> getValidChildDivisions(Long orgId) {
//		OrganizationNode node = getVilidOrganizationNode(orgId);
//		if(node == null) {
//			return null;
//		}
//		
//		List<Division> childDivisions = new ArrayList<Division>();
//		Set<Long> divIds = ContextUtils.getEvictDivIds();
//		Collection<?> childs = (Collection<?>)node.getChildren();
//		if(!CollectionUtils.isEmpty(childs)) {
//			for(Iterator<?> iter = childs.iterator(); iter.hasNext(); ) {
//				OrganizationNode childNode = (OrganizationNode)iter.next();
//				if(childNode != null && childNode instanceof DivisionNode) {
//					if(CollectionUtils.isEmpty(divIds) || !divIds.contains(childNode.getId())) {
//						String divCode = assembleInfo((DivisionNode)childNode);
//						childDivisions.add(division);
//					}
//				}
//			}
//		}
//		return childDivisions;
//	}
//	
//	/**
//	 * 得到指定机构对象的子机构集合(过滤用户权限)
//	 * @return
//	 */
//	public static List<Long> getValidChildDivids(Long orgId) {
//		OrganizationNode node = getVilidOrganizationNode(orgId);
//		if(node == null) {
//			return null;
//		}
//		
//		List<Long> childDivisions = new ArrayList<Long>();
//		Set<Long> divIds = ContextUtils.getEvictDivIds();
//		Collection<?> childs = (Collection<?>)node.getChildren();
//		if(!CollectionUtils.isEmpty(childs)) {
//			for(Iterator<?> iter = childs.iterator(); iter.hasNext(); ) {
//				OrganizationNode childNode = (OrganizationNode)iter.next();
//				if(childNode != null && childNode instanceof DivisionNode) {
//					if(CollectionUtils.isEmpty(divIds) || !divIds.contains(childNode.getId())) {
//						String divCode = assembleInfo((DivisionNode)childNode);
//						childDivisions.add(division.getId());
//					}
//				}
//			}
//		}
//		return childDivisions;
//	}
//	
//	/**
//	 * 得到指定机构ID的父机构ID(过滤用户权限)
//	 * @param divId
//	 * @return
//	 */
//	public static Long getParentDivId(String divCode) {
//		OrganizationNode node = getVilidOrganizationNode(divId);
//		if(node != null) {
//			OrganizationNode parentNode = (OrganizationNode)node.getParent();
//			if(parentNode != null) {
//				return parentNode.getId();
//			}
//		}
//		return null;
//	}
//	
//	/**
//	 * 取得机构或部门下级的部门集合,调用缓存部门树
//	 * @param orgId 机构或部门ID
//	 * @param bottomDivId orgId为机构ID时，bottomDivId为空；orgId为部门ID时，bottomDivId为该部门的底层机构ID
//	 * @return
//	 */
//	public static List<Department> getDepartmentsByParentId(Long orgId, Long bottomDivId) {
//		OrganizationNode node = null;
//		if(bottomDivId == null) {
//			node = getVilidOrganizationNode(orgId);
//			if(node == null) {
//				return null;
//			}
//		} else {
//			node = getVilidOrganizationNode(bottomDivId);
//			if(node == null) {
//				return null;
//			}
//		}
//		
//		OrganizationNode divDeptNode = null;
//		if(bottomDivId == null) {
//			if(node == null || node.getChildCount() != 0) {
//				return null;
//			}
//			divDeptNode = OrgTreeCacheUtils.getDivDeptNode(orgId);
//		} else {
//			divDeptNode = OrgTreeCacheUtils.getDivDeptNode(bottomDivId);
//		}
//		
//		if(divDeptNode != null) {
//			List<Department> childDepartments = new ArrayList<Department>();
//			String divCode = divDeptNode.getId();
//			OrganizationNode root = (OrganizationNode)divDeptNode.getPosterities().get(orgId);
//			Collection<?> childs = (Collection<?>)root.getChildren();
//			
//			if(!CollectionUtils.isEmpty(childs)) {
//				for(Iterator<?> iter = childs.iterator(); iter.hasNext(); ) {
//					OrganizationNode childNode = (OrganizationNode)iter.next();
//					if(childNode != null && childNode instanceof DepartmentNode) {
//						String deptCode = assembleInfo((DepartmentNode)childNode, divId);
//						childDepartments.add(department);
//					}
//				}
//			}
//			return childDepartments;
//		}
//		return null;
//	}
//	
//	/**
//	 * 返回当前用户有效的分公司ID集合
//	 * 如果顶层节点为总公司则返回有效的分公司集合；如果顶层节点为分公司，则仅返回该分公司ID；如果顶层节点为分公司以下级别，则返回空的集合
//	 * @return
//	 */
//	public static List<Long> getVilidFgsIds() {
//		OrganizationNode topNode = getOrganizationNode();
//		List<Long> rtnList = new ArrayList<Long>();
//		if(topNode instanceof DivisionNode) {
//			DivisionNode divNode = (DivisionNode)topNode;
//			if(DivisionLevel.HEADQUARTERS.equals(divNode.getDivLevel())) {
//				List<Long> fgsIds = getValidChildDivids(divNode.getId());
//				if(fgsIds != null) {
//					rtnList = fgsIds;
//				}
//			} else if(DivisionLevel.FILIALE.equals(divNode.getDivLevel())) {
//				rtnList.add(divNode.getId());
//			}
//		}
//		return rtnList;
//	}
//	
//	public static Division assembleInfo(DivisionNode node) {
//		String divCode = new Division();
//		division.setId(node.getId());
//		division.setDivName(node.getOrgName());
//		division.setDivLevel(node.getDivLevel());
//		division.setDivCode(node.getDivCode());
//		
//		return division;
//	}
//	
//	public static Department assembleInfo(DepartmentNode node, Long bottomDivId) {
//		Department dept = new Department();
//		dept.setId(node.getId());
//		dept.setDeptName(node.getOrgName());
//		dept.setDeptCode(node.getDeptCode());
//		dept.setDeptLevel(node.getDeptLevel());
//		dept.setChaType(node.getChaType());
//		dept.setBottomDivId(bottomDivId);
//
//		return dept;
//	}
//}
