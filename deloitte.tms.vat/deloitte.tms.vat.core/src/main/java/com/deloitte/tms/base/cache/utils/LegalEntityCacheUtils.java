package com.deloitte.tms.base.cache.utils;

import java.util.ArrayList;
import java.util.Collection;
//import java.util.Enumeration;
import java.util.List;

import com.deloitte.tms.base.cache.model.LegalEntityNode;
import com.deloitte.tms.base.cache.model.OrgNode;
import com.deloitte.tms.base.cache.model.PrinterTerminalNode;
import com.deloitte.tms.base.cache.service.LegalEntityCacheService;
import com.deloitte.tms.base.cache.service.impl.LegalEntityCacheProvider;
import com.deloitte.tms.base.enums.PrintRangeEnums;
import com.deloitte.tms.pl.cache.CacheUtils;
import com.deloitte.tms.pl.core.commons.exception.BusinessException;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.deloitte.tms.pl.security.dao.IUserDao;

public class LegalEntityCacheUtils {
	
	static IUserDao userDao;
	
	public static IUserDao getDao(){
		if(userDao==null){
			userDao=SpringUtil.getBean(IUserDao.BEAN_ID);
		}
		return userDao;
	}
	
	/**
	 * 获取缓存的汇缴关系完整树
	 * @return
	 */
	public static LegalEntityNode getTopNode() {
		LegalEntityCacheProvider legalEntityCacheProvider=SpringUtil.getBean(LegalEntityCacheProvider.BEAN_ID);
		LegalEntityNode legalEntityNode=(LegalEntityNode) CacheUtils.getCacheObject(LegalEntityCacheService.LEGALENTITY_CACHE_ID);
		if(legalEntityNode==null){
			legalEntityNode=(LegalEntityNode) legalEntityCacheProvider.build(null);
			CacheUtils.putCacheObject(LegalEntityCacheService.LEGALENTITY_CACHE_ID,legalEntityNode);
		}
		return legalEntityNode;
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
		AssertHelper.notEmpty_assert(orgId,"机构id不能为空");
		return getLegalNodeByOrgId(orgId,getTopNode());
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
	 * 递归查找
	 * @param orgCode
	 * @param legalEntityNode
	 * @return
	 */
	private static LegalEntityNode getLegalNodeByOrgId(String orgId,LegalEntityNode legalEntityNode){
		if(legalEntityNode.getBizOrgNode()!=null&&orgId.equals(legalEntityNode.getBizOrgNode().getId())){
			return legalEntityNode;
		}
		Collection<OrgNode> childs=legalEntityNode.getPosterities().values();
		for(OrgNode child:childs){
			LegalEntityNode childLegalEntityNode=(LegalEntityNode)child;
			if(childLegalEntityNode.getBizOrgNode()!=null&&orgId.equals(childLegalEntityNode.getBizOrgNode().getId())){
				return childLegalEntityNode;
			}
		}
		return null;
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
	 * 获取纳税限额,如果不使用自身纳税识别号,则是计算得到的纳税实体的纳税限额
	 * @param orgCode
	 * @param invoiceType
	 * @return
	 */
	public static Long getInvoiceLimitAmountValueByOrgCode(String orgCode,String invoiceType){
		AssertHelper.notEmpty_assert(orgCode,"orgCode不能为空");
		AssertHelper.notEmpty_assert(invoiceType,"invoiceType不能为空");
		LegalEntityNode legalEntityNode=getLegalNodeByOrgCode(orgCode);
		AssertHelper.notEmpty_assert(legalEntityNode,"没有找到机构代码为:"+orgCode+"的纳税实体");
		return legalEntityNode.getInvoiceLimitAmountValue(invoiceType);
	}
	/**
	 * 获取纳税限额,如果不使用自身纳税识别号,则是计算得到的纳税实体的纳税限额
	 * @param LegalId
	 * @param invoiceType
	 * @return
	 */
	public static Long getInvoiceLimitAmountValueByLegalId(String LegalId,String invoiceType){
		AssertHelper.notEmpty_assert(LegalId,"LegalId不能为空");
		AssertHelper.notEmpty_assert(invoiceType,"invoiceType不能为空");
		LegalEntityNode legalEntityNode=(LegalEntityNode)getTopNode().getPosterities().get(LegalId);
		AssertHelper.notEmpty_assert(legalEntityNode,"没有找到纳税实体id为:"+LegalId+"的纳税实体");
		return legalEntityNode.getInvoiceLimitAmountValue(invoiceType);
//		return 100000l;
	}
	/**
	 * 获取自身纳税限额
	 * @param orgCode
	 * @param invoiceType 参见 InvoiceTypeEnums中的定义
	 * @return
	 */
	public static Long getSelfInvoiceLimitAmountValueByOrgCode(String orgCode,String invoiceType){
		AssertHelper.notEmpty_assert(orgCode,"orgCode不能为空");
		AssertHelper.notEmpty_assert(invoiceType,"invoiceType不能为空");
		LegalEntityNode legalEntityNode=getLegalNodeByOrgCode(orgCode);
		AssertHelper.notEmpty_assert(legalEntityNode,"没有找到机构代码为:"+orgCode+"的纳税实体");
		return legalEntityNode.getSelfInvoiceLimitAmountValue(invoiceType);
	}
	/**
	 * 获取自身纳税限额
	 * @param LegalId
	 * @param invoiceType 参见 InvoiceTypeEnums中的定义
	 * @return
	 */
	public static Long getSelfInvoiceLimitAmountValueByLegalId(String LegalId,String invoiceType){
		AssertHelper.notEmpty_assert(LegalId,"orgCode不能为空");
		AssertHelper.notEmpty_assert(invoiceType,"invoiceType不能为空");
//		LegalEntityNode legalEntityNode=(LegalEntityNode)getTopNode().getPosterities().get(LegalId);
//		AssertHelper.notEmpty_assert(legalEntityNode,"没有找到纳税实体Id为:"+LegalId+"的纳税实体");
//		return legalEntityNode.getSelfInvoiceLimitAmountValue(invoiceType);
		return 100000l;
	}
	/**
	 * 获取纳税实体所有的打印终端
	 * @param LegalId
	 * @return
	 */
	public static Collection<PrinterTerminalNode> geTerminalNodesByLegalId(String LegalId){
		AssertHelper.notEmpty_assert(LegalId,"orgCode不能为空");
		LegalEntityNode legalEntityNode=(LegalEntityNode)getTopNode().getPosterities().get(LegalId);
		return legalEntityNode.getPrinterTerminalNodes();
	}
	/**
	 * 获取纳税实体的打印终端
	 * @param LegalId
	 * @return
	 */
	public static PrinterTerminalNode geDefaultTerminalNodesByLegalId(String LegalId){
//		AssertHelper.notEmpty_assert(LegalId,"orgCode不能为空");
//		LegalEntityNode legalEntityNode=(LegalEntityNode)getTopNode().getPosterities().get(LegalId);
//		if(legalEntityNode.getPrinterTerminalNodes().size()>0){
//			return legalEntityNode.getPrinterTerminalNodes().iterator().next();
//		}
//		return null;
		PrinterTerminalNode tempNode=new PrinterTerminalNode("LE000-0000-0000-0000-0000-0000-00002","LE000-0000-0000-0000-0000-0000-00002");
		tempNode.setEquipmentId("LE000-0000-0000-0000-0000-0000-00002");
		tempNode.setLegalEntityId("LE000-0000-0000-0000-0000-0000-00002");
		tempNode.setName("LE000-0000-0000-0000-0000-0000-00002");
		return tempNode;
	}
	/**
	 * 获取缓存的汇缴关系完整树
	 * @return
	 */
	public static void refreshLegalEntityNode() {
		CacheUtils.putCacheObject(LegalEntityCacheService.LEGALENTITY_CACHE_ID,null);
	}
	/**
	 * 根据orgId和打印范围找纳税code实体集合
	 * @param orgId
	 * @param type
	 * @return
	 */
	public static List<String> legalEntityCodesByOrgId(String orgId,PrintRangeEnums type){
		AssertHelper.notEmpty_assert(orgId, "orgId不能为空");
		AssertHelper.notEmpty_assert(type, "范围不能为空");
		List<String> codes=new ArrayList<String>();
		if(PrintRangeEnums.all.equals(type)){
			/*LegalEntityNode node=PrintOrgCacheUtils.getTopNode();			
			for(OrgNode child:node.getPosterities().values()){
				if(child.getCode()!=null){
					codes.add(child.getCode());
				}
			}*/
			throw new BusinessException("不用使用全局查询");
		}
		if(PrintRangeEnums.current.equals(type)){
			LegalEntityNode node=getLegalNodeByOrgId(orgId);	
			AssertHelper.notEmpty_assert(node, "没有找到orgId:"+orgId+"对应的纳税实体");
			codes.add(node.getCode());
		}
		if(PrintRangeEnums.city.equals(type)){
			LegalEntityNode node=PrintOrgCacheUtils.getLegalNodeByOrgId(orgId);	
			AssertHelper.notEmpty_assert(node, "没有找到orgId:"+orgId+"对应的纳税实体");
			AssertHelper.notEmpty_assert(node.getPrintSiteNode(), "没有找到orgId:"+orgId+"对应的纳税实体的打印点");
			AssertHelper.notEmpty_assert(node.getPrintSiteNode().getLegalEntityNode(), "没有找到orgId:"+orgId+"对应的纳税实体的打印点的纳税实体");
			LegalEntityNode printEntityNode=PrintOrgCacheUtils.getLegalNodeByOrgId(orgId);
			codes.add(printEntityNode.getCode());
			for(OrgNode child:printEntityNode.getPosterities().values()){
				if(child.getCode()!=null){
					codes.add(child.getCode());
				}
			}
		}
		return codes;
	}
}
