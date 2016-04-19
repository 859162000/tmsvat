package com.deloitte.tms.base.cache.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.deloitte.tms.base.cache.model.LegalEntityNode;
import com.deloitte.tms.base.cache.model.OrgNode;
import com.deloitte.tms.base.cache.model.PrintSiteNode;
import com.deloitte.tms.base.cache.model.PrinterTerminalNode;
import com.deloitte.tms.base.cache.service.LegalEntityCacheService;
import com.deloitte.tms.base.cache.service.impl.PrintTreeCacheProvider;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEnablePrint;
import com.deloitte.tms.pl.cache.CacheUtils;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.deloitte.tms.pl.core.dao.impl.BaseDao;
import com.deloitte.tms.pl.version.party.model.organization.node.Node;

public class PrintOrgCacheUtils {
	
	/**
	 * 获取缓存的汇缴关系完整树
	 * @return
	 */
	public static LegalEntityNode getTopNode() {
		PrintTreeCacheProvider printTreeCacheProvider=SpringUtil.getBean(PrintTreeCacheProvider.BEAN_ID);
		LegalEntityNode legalEntityNode=(LegalEntityNode) CacheUtils.getCacheObject(LegalEntityCacheService.PRINTTREE_CACHE_ID);
		if(legalEntityNode==null){
			legalEntityNode=(LegalEntityNode) printTreeCacheProvider.build(null);
			CacheUtils.putCacheObject(LegalEntityCacheService.PRINTTREE_CACHE_ID,legalEntityNode);
		}
		return legalEntityNode;
	}
	
	/**
	 * 
	 *获取打印关系节点
	 * 递归往上找
	 * @param legalId
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public static PrintSiteNode getPrintNodeByLegalId(String legalId){
//		if("0".equals(legalId)){
//			return new PrintSiteNode("0","总公司");
//		}else if("1".equals(legalId)){
//			return new PrintSiteNode("1","分公司");
//		}
		LegalEntityNode currentLegalEntityNode=getLegalNodeByLegalId(legalId);
		PrintSiteNode printOrgNode=getLegalNodeByLegalId(legalId).getPrintSiteNode();
		if(printOrgNode==null&&currentLegalEntityNode!=null){
			LegalEntityNode parentLegalEntityNode=(LegalEntityNode) currentLegalEntityNode.getParent();
			if(parentLegalEntityNode!=null&&parentLegalEntityNode.getId()!=null){
				return new PrintSiteNode(parentLegalEntityNode.getId(), parentLegalEntityNode.getName()); 
			}else {
				return new PrintSiteNode(currentLegalEntityNode.getId(), currentLegalEntityNode.getName()); 
			}
		}else if(printOrgNode.getId()==null){
			LegalEntityNode parentLegalEntityNode=(LegalEntityNode) currentLegalEntityNode.getParent();
			if(parentLegalEntityNode!=null&&parentLegalEntityNode.getId()!=null){
				return new PrintSiteNode(parentLegalEntityNode.getId(), parentLegalEntityNode.getName()); 
			}else {
				return new PrintSiteNode(currentLegalEntityNode.getId(), currentLegalEntityNode.getName()); 
			}
		}
		return printOrgNode;
	}
	/**
	 * 获取legalId的汇缴关系节点
	 * 递归往上找
	 * @param legalId
	 * @return
	 */
	public static LegalEntityNode getLegalNodeByLegalId(String legalId){
		AssertHelper.notEmpty_assert(legalId,"legalId不能为空");
		return (LegalEntityNode)getTopNode().getPosterities().get(legalId);
	}
	/**
	 * 获取orgCode的汇缴关系节点
	 * 递归往上找
	 * @param orgCode
	 * @return
	 */
	public static LegalEntityNode getLegalNodeByOrgCode(String orgCode){
		/*AssertHelper.notEmpty_assert(orgCode,"机构代码不能为空");*/
		return getLegalNodeByOrgCode(orgCode,getTopNode());
	}
	/**
	 * 获取orgCode的汇缴关系节点
	 * 递归往上找
	 * @param orgCode
	 * @return
	 */
	public static LegalEntityNode getLegalNodeByOrgId(String orgId){
		Collection<OrgNode> childs=getTopNode().getPosterities().values();
		for(OrgNode child:childs){
			LegalEntityNode childLegalEntityNode=(LegalEntityNode)child;
			if(childLegalEntityNode.getBizOrgNode()!=null&&orgId.equals(childLegalEntityNode.getBizOrgNode().getId())){
				return childLegalEntityNode;
			}
		}
		return null;
	}
	/**
	 * 
	 *获取打印关系节点
	 * 递归往上找
	 * @param legalId
	 * @return
	 * @see [相关类/方法]（可选）
	 * @since [产品/模块版本] （可选）
	 */
	public static PrintSiteNode getPrintNodeByOrgId(String orgId){
		Collection<OrgNode> childs=getTopNode().getPosterities().values();
		for(OrgNode child:childs){
			LegalEntityNode childLegalEntityNode=(LegalEntityNode)child;
			if(childLegalEntityNode.getBizOrgNode()!=null&&orgId.equals(childLegalEntityNode.getBizOrgNode().getId().trim())){
				return childLegalEntityNode.getPrintSiteNode();
			}
		}
		return null;
	}
	public static Collection<PrinterTerminalNode> getPrinterTerminalNodes(String legalId) {
		return getPrintNodeByLegalId(legalId).getPrinterTerminalNodes();
	}
	public static  PrinterTerminalNode getDefaultPrinterTerminalNodes(String legalId) {
		return getPrintNodeByLegalId(legalId).getDefaultPrinterTerminalNode();
	}
	/**
	 * 递归查找
	 * @param orgCode
	 * @param legalEntityNode
	 * @return
	 */
	private static LegalEntityNode getLegalNodeByOrgCode(String orgCode,LegalEntityNode legalEntityNode){
		AssertHelper.notEmpty_assert(orgCode, "登录用户的机构代码不能为空");
		if(legalEntityNode.getBizOrgNode()!=null&&orgCode.equals(legalEntityNode.getBizOrgNode().getCode())){
			return legalEntityNode;
		}
		Collection<OrgNode> childs=legalEntityNode.getPosterities().values();
		for(OrgNode child:childs){
			LegalEntityNode childLegalEntityNode=(LegalEntityNode)child;
			if(childLegalEntityNode.getBizOrgNode()!=null&&orgCode.equals(childLegalEntityNode.getBizOrgNode().getCode())){
				return childLegalEntityNode;
			}
		}
		return null;
	}
	/**
	 * 获取缓存的汇缴关系完整树
	 * @return
	 */
	public static void refreshPrintOrgNode() {
		CacheUtils.putCacheObject(LegalEntityCacheService.PRINTTREE_CACHE_ID,null);
	}
	/**
	 * 获取缓存的汇缴关系完整树
	 * @return
	 */
	public static List<String> getOrgIdsByCity(String orgId) {
		AssertHelper.notEmpty_assert(orgId, "机构id:不能为空");
		PrintSiteNode printSiteNode=getPrintNodeByOrgId(orgId);
		AssertHelper.notEmpty_assert(printSiteNode, "机构id:"+orgId+"没有找到相应的打印点");
		LegalEntityNode legalEntityNode=printSiteNode.getLegalEntityNode();
		AssertHelper.notEmpty_assert(legalEntityNode, "数据错误,打印点没有找到相应的纳税实体");
		List<String> orgIds=new ArrayList<String>();
		getOrgIdsByType(legalEntityNode, orgIds);
		return orgIds;
	}
	/**
	 * 获取缓存的汇缴关系完整树
	 * @return
	 */
	public static List<String> getLegalIdsByCity(String orgId) {
		AssertHelper.notEmpty_assert(orgId, "机构id:不能为空");
		PrintSiteNode printSiteNode=getPrintNodeByOrgId(orgId);
		AssertHelper.notEmpty_assert(printSiteNode, "机构id:"+orgId+"没有找到相应的打印点");
		LegalEntityNode legalEntityNode=printSiteNode.getLegalEntityNode();
		AssertHelper.notEmpty_assert(legalEntityNode, "数据错误,打印点没有找到相应的纳税实体");
		List<String> LegalIds=new ArrayList<String>();
		getLegalIdsByType(legalEntityNode, LegalIds);
		return LegalIds;
	}
	/**
	 * 获取缓存的汇缴关系完整树
	 * @return
	 */
	public static List<String> getOrgCodesByCity(String orgId) {
		AssertHelper.notEmpty_assert(orgId, "机构id:不能为空");
		PrintSiteNode printSiteNode=getPrintNodeByOrgId(orgId);
		AssertHelper.notEmpty_assert(printSiteNode, "机构id:"+orgId+"没有找到相应的打印点");
		LegalEntityNode legalEntityNode=printSiteNode.getLegalEntityNode();
		AssertHelper.notEmpty_assert(legalEntityNode, "数据错误,打印点没有找到相应的纳税实体");
		List<String> orgCodes=new ArrayList<String>();
		getOrgCodesByType(legalEntityNode, orgCodes);
		return orgCodes;
	}
	/**
	 * 获取同城的客户银行账号,供交易池查找相应的交易流水
	 * @return
	 */
	public static List<String> getCustomerBankAccountsByCustomerIdAndOrgId(String orgId,String customerId) {
		AssertHelper.notEmpty_assert(orgId, "机构id:不能为空");
		AssertHelper.notEmpty_assert(customerId, "客户id:不能为空");
		List<String> orgCodes=getOrgCodesByCity(orgId);
		List<String> bankAccounts=new ArrayList<String>();
		if(orgCodes.size()==0){
			return bankAccounts;
		}else{
			BaseDao dao=SpringUtil.getBean(BaseDao.BEAN_ID_BASE);
			Map<String, Object> values=new HashMap<String, Object>();
			String query="select distinct custBankAccountNum from CustBankAccount where customerId=:customerId and custBankOrgCode in :orgCodes";
			values.put("orgCodes", orgCodes);
			values.put("customerId", customerId);
			List<String> resultList= dao.findBy(query, values);
			return resultList;
		}
	}
	private static void getOrgIdsByType(LegalEntityNode legalEntityNode,
			List<String> orgIds) {
		AssertHelper.notEmpty_assert(legalEntityNode.getBizOrgNode(), "数据错误,纳税实体没有找到相应的打印点");
		String orgId=legalEntityNode.getBizOrgNode().getId();
		if(!orgIds.contains(orgId)){
			orgIds.add(orgId);
		}
		Enumeration<Node> enumeration = legalEntityNode.children();
		while (enumeration.hasMoreElements()) {
			LegalEntityNode node=(LegalEntityNode) enumeration.nextElement();
			//如果下级不是打印点就添加否者这条线就断掉
			if(!node.isPrintSite()){
				getOrgIdsByType(node,orgIds);
			}			
		}
	}
	private static void getLegalIdsByType(LegalEntityNode legalEntityNode,
			List<String> LegalIds) {
		AssertHelper.notEmpty_assert(legalEntityNode.getBizOrgNode(), "数据错误,纳税实体没有找到相应的打印点");
		String orgId=legalEntityNode.getId();
		if(!LegalIds.contains(orgId)){
			LegalIds.add(orgId);
		}
		Enumeration<Node> enumeration = legalEntityNode.children();
		while (enumeration.hasMoreElements()) {
			LegalEntityNode node=(LegalEntityNode) enumeration.nextElement();
			//如果下级不是打印点就添加否者这条线就断掉
			if(!node.isPrintSite()){
				getLegalIdsByType(node,LegalIds);
			}			
		}
	}
	private static void getOrgCodesByType(LegalEntityNode legalEntityNode,
			List<String> orgCodes) {
		AssertHelper.notEmpty_assert(legalEntityNode.getBizOrgNode(), "数据错误,纳税实体没有找到相应的打印点");
		String orgCode=legalEntityNode.getCode();
		if(!orgCodes.contains(orgCode)){
			orgCodes.add(orgCode);
		}
		Enumeration<Node> enumeration = legalEntityNode.children();
		while (enumeration.hasMoreElements()) {
			LegalEntityNode node=(LegalEntityNode) enumeration.nextElement();
			//如果下级不是打印点就添加否者这条线就断掉
			if(!node.isPrintSite()){
				getOrgCodesByType(node,orgCodes);
			}			
		}
	}
	/**
	 * 
	 * @param legalId 机构id
	 * @return
	 */
	public static String getUserPrintLegalStrByHql(String legalId) {
		AssertHelper.notEmpty_assert(legalId, "纳税主体id不能为空");
		LegalEntityNode legalEntityNode=PrintOrgCacheUtils.getLegalNodeByLegalId(legalId);
		AssertHelper.notEmpty_assert(legalEntityNode, "纳税主体id:"+legalId+"没有找到相应LegalEntityNode数据");
		StringBuffer query = new StringBuffer();
		query.append(" select distinct print.legalEntityId from TmsMdLegalEnablePrint print ");
		query.append(" where (print.legalEntityId='"+legalId+"' or print.parentId='"+legalId+"') ");
		return query.toString();
	}
	public static String getUserPrintLegalStrBySql(String orgId) {
		return convertUserPrintLegalStrToSql(getUserPrintLegalStrByHql(orgId));
	}
	/**
	 * 转换查询getUserDeptStr的HQL为SQL
	 * @param userDeptStr
	 * @return
	 * @author dada
	 */
	public static String convertUserPrintLegalStrToSql(String userDeptStr) {
		String retval = userDeptStr;
		retval = StringUtils.replace(retval, "TmsMdLegalEnablePrint", TmsMdLegalEnablePrint.TABLE);
		retval = StringUtils.replace(retval, "print.legalEntityId", "print.LEGAL_ENTITY_ID");
		retval = StringUtils.replace(retval, "print.parentId", "print.PARENT_ID");
		return retval;
	}
}
