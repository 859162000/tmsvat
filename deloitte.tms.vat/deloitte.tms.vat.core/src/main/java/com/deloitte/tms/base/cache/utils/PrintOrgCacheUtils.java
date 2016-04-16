package com.deloitte.tms.base.cache.utils;

import java.util.Collection;

import com.deloitte.tms.base.cache.model.LegalEntityNode;
import com.deloitte.tms.base.cache.model.OrgNode;
import com.deloitte.tms.base.cache.model.PrintSiteNode;
import com.deloitte.tms.base.cache.model.PrinterTerminalNode;
import com.deloitte.tms.base.cache.service.LegalEntityCacheService;
import com.deloitte.tms.base.cache.service.impl.PrintTreeCacheProvider;
import com.deloitte.tms.pl.cache.CacheUtils;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class PrintOrgCacheUtils {
	
	/**
	 * 获取缓存的汇缴关系完整树
	 * @return
	 */
	public static LegalEntityNode getTopNode() {
		PrintTreeCacheProvider printTreeCacheProvider=SpringUtil.getBean(PrintTreeCacheProvider.BEAN_ID);
		LegalEntityNode legalEntityNode=(LegalEntityNode) CacheUtils.getCacheObject(LegalEntityCacheService.LEGALENTITY_CACHE_ID);
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
			if(childLegalEntityNode.getBizOrgNode()!=null&&orgId.equals(childLegalEntityNode.getBizOrgNode().getId())){
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
}
