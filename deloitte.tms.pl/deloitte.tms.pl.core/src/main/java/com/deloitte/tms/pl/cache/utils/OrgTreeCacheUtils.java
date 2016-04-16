package com.deloitte.tms.pl.cache.utils;
//package com.ling2.cache.utils;
//
//import java.util.Map;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Component;
//
//import com.ling2.cache.provider.OrgTreeCacheProvider;
//import com.ling2.channel.core.utils.constant.DivisionLevel;
//import com.ling2.core.context.utils.ContextUtils;
//import com.ling2.version.party.model.organization.DivisionNode;
//import com.ling2.version.party.model.organization.OrganizationNode;
//
///**
// * 提供访问系统缓存架构树的工具类(不控制用户权限,请谨慎使用！)
// * @author zhanglin.jiang
// *
// */
//@Component("orgTreeCacheUtils")
//public class OrgTreeCacheUtils {
//
//	private static OrgTreeCacheProvider s_orgnTreeCacheProvider;
//	private OrgTreeCacheProvider orgnTreeCacheProvider;
//	@Resource
//	public void setOrgnTreeCacheProvider(OrgTreeCacheProvider orgnTreeCacheProvider) {
//		this.orgnTreeCacheProvider = orgnTreeCacheProvider;
//	}
//	@PostConstruct
//    protected void init() { 
//		s_orgnTreeCacheProvider = orgnTreeCacheProvider;
//    } 
//	
//	/**
//	 * 取得组织机构树的指定机构节点
//	 * @param divId
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public static OrganizationNode getOrganizationNode(String divCode) {
//		OrganizationNode organizationNode = s_orgnTreeCacheProvider.getOrganization();
//		Map<String, OrganizationNode> nodesMap = (Map<String, OrganizationNode>)organizationNode.getPosterities();
//		return nodesMap.get(divCode);
//	}
//	
//	/**
//	 * 取得指定底层机构节点下的子树（包括当前机构节点及以下所有部门节点的一棵树） 机构缓存,部门不缓存
//	 * @param divId
//	 * @return
//	 */
//	public static OrganizationNode getDivDeptNode(String divCode) {
//		return s_orgnTreeCacheProvider.getDepartmentByDivId(divCode, ContextUtils.getChaType());
//	}
//	
//	/**
//	 * 返回总公司或分公司ID（如果参数divId为总公司ID，则返回总公司ID；否则返回分公司ID）
//	 * @param divId
//	 * @return
//	 */
//	public static String getDataownerByDivCode(String divCode) {
//		if(ContextUtils.getTopDivCode().equals(divCode)) {
//			return ContextUtils.getTopDivCode();
//		}
//		
//		OrganizationNode organizationNode = getOrganizationNode(divCode);
//		if(organizationNode != null && organizationNode instanceof DivisionNode) {
//			DivisionNode divNode = (DivisionNode)organizationNode;
//			String divLevel = divNode.getDivLevel();
//			if(DivisionLevel.FILIALE.equals(divLevel)) {
//				return divNode.getOrgCode();
//			}
//			
//			OrganizationNode parentNode = divNode;
//			while(parentNode != null && parentNode.getParent() != null) {
//				parentNode = (OrganizationNode)parentNode.getParent();
//				if(parentNode instanceof DivisionNode) {
//					divLevel = ((DivisionNode)parentNode).getDivLevel();
//				}
//				if(DivisionLevel.FILIALE.equals(divLevel)) {
//					return parentNode.getOrgCode();
//				}
//			}
//		}
//		
//		return null;
//	}
//	
//	public static void removeCache() {
//		s_orgnTreeCacheProvider.removeCache();
//	}
//}
