package com.deloitte.tms.pl.cache.provider;
//package com.ling2.cache.provider;
//
//import java.util.Date;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Component;
//
//import com.ling2.core.context.utils.ContextUtils;
//import com.ling2.orgpath.service.IOrganizationBuilderService;
//import com.ling2.version.party.model.organization.DivisionNode;
//import com.ling2.version.party.model.organization.OrganizationNode;
//
///**
// * 组织机构树缓存（启动加载，永久驻留内存）
// * @author zhanglin.jiang
// *
// */
//@Component("orgTreeCacheProvider")
//public class OrgTreeCacheProvider {
//	private Log log = LogFactory.getLog(this.getClass());
//	
//	private OrganizationNode organization;
//	
//	@Resource
//	private IOrganizationBuilderService organizationBuilderService;
//	
//	/**
//	 * 构建组织机构树
//	 * @PostConstruct 相当于<Bean>里的init-method方法，会在初始化对象后自动调用
//	 * @return
//	 */
//	@PostConstruct
//	protected void buildOrganizationTree() {
//		log.info("begin build org tree");
//		Date date = new Date();
//		DivisionNode organization = (DivisionNode) organizationBuilderService.buildDivision(ContextUtils.getTopDivCode(), date);
////		organizationBuilderService.buildDepartment(organization, SystemConst.FP_CHATYPE, date);
//		log.info("[Time Consume] - " + (System.currentTimeMillis() - date.getTime()));
//		this.organization = organization;
//	}
//	
//	public OrganizationNode getOrganization() {
//		return organization;
//	}
//	
//	@Cacheable("orgsCache")
//	public synchronized OrganizationNode getDepartmentByDivId(String divCode, String chaType) {
//		DivisionNode divNode = new DivisionNode(divCode, "虚拟部门顶层节点");
//		Date date = new Date();
//
//		OrganizationNode divDepartments = organizationBuilderService.buildDepartmentByBottomDivNode(divNode, 
//				chaType, date);
//		return divDepartments;
//	}
//	
//	/**
//	 * 移除部门树的缓存
//	 */
//	@CacheEvict(value = "orgsCache", allEntries = true)
//	public synchronized void removeCache() {
//	}
//}
