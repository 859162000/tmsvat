package com.deloitte.tms.base.cache.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.deloitte.tms.base.cache.model.BizOrgNode;
import com.deloitte.tms.base.cache.model.LegalEntityNode;
import com.deloitte.tms.base.cache.model.LicenseNoNode;
import com.deloitte.tms.base.cache.model.OrgNode;
import com.deloitte.tms.base.cache.model.PrintSiteNode;
import com.deloitte.tms.base.cache.model.PrinterTerminalNode;
import com.deloitte.tms.base.cache.service.CacheProvider;
import com.deloitte.tms.base.cache.utils.OrgCacheUtils;
import com.deloitte.tms.base.masterdata.constant.LegalTaxCategoryDef;
import com.deloitte.tms.base.masterdata.dao.LegalEntityDao;
import com.deloitte.tms.base.masterdata.model.LegalTaxCategory;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEnablePrint;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalEquipment;
import com.deloitte.tms.base.masterdata.model.TmsMdLegalInvoice;
import com.deloitte.tms.base.masterdata.model.TmsMdOrgLegalEntity;
import com.deloitte.tms.base.masterdata.model.TmsMdUsageLocalLegal;
import com.deloitte.tms.pl.version.party.model.organization.node.Node;

public abstract class LegalEntityBaseCacheProvider implements CacheProvider {
	
	@Resource
	LegalEntityDao legalEntityDao;
	/**
	 * 查询当前有效的纳税人实体数据
	 */
	List<TmsMdLegalEntity> legalEntities;
	/**
	 * 获取当前有效的组织和纳税主体关系
	 */
	List<TmsMdOrgLegalEntity> orgLegalEntities;
	
	/**
	 * 获取当前有效的纳税人限额
	 */
	List<TmsMdLegalInvoice> legalInvoices;
	
	/**
	 * 获取当前有效的是否采用自身纳税识别号
	 * @param taxCode
	 * @return
	 */
	List<TmsMdUsageLocalLegal> usageLocalLegals;
	/**
	 * 获取当前有效的纳税主体与打印终端关系
	 * @return
	 */
	List<TmsMdLegalEquipment> legalEquipments;
	/**
	 * 获取当前有效的是否打印点 纳税人主体与打印点关系 
	 * @return
	 */
	List<TmsMdLegalEnablePrint> legalEnablePrints;
	/**
	 * 查询当前有效的纳税主体与纳税主体与汇缴(税种)关系 组成回角关系树
	 * @param taxCode
	 */
	List<LegalTaxCategory> legalTaxCategories;

	/**
	 * 查询当前有效的纳税人实体数据
	 */
	List<TmsMdLegalEntity> getlegalEntities(){
		return legalEntityDao.getAllLegalEntities();
	}
	/**
	 * 获取当前有效的组织和纳税主体关系
	 */
	List<TmsMdOrgLegalEntity> getOrgLegalEntities(){
		return legalEntityDao.getAllOrgLegalEntities();
	}
	
	/**
	 * 获取当前有效的纳税人限额
	 */
	List<TmsMdLegalInvoice> getLegalInvoices(){
		return legalEntityDao.getAllLegalInvoice();
	}
	
	/**
	 * 获取当前有效的是否采用自身纳税识别号
	 * @param taxCode
	 * @return
	 */
	List<TmsMdUsageLocalLegal> getUsageLocalLegals(){
		return legalEntityDao.getAllTmsMdUsageLocalLegals();
	}
	/**
	 * 获取当前有效的纳税主体与打印终端关系
	 * @return
	 */
	List<TmsMdLegalEquipment> getLegalEquipments(){
		return legalEntityDao.getAllTmsMdLegalEquipments();
	}
	/**
	 * 获取当前有效的是否打印点 纳税人主体与打印点关系 
	 * @return
	 */
	List<TmsMdLegalEnablePrint> getLegalEnablePrints(){
		return legalEntityDao.getAllTmsMdLegalEnablePrints();
	}
	
	/**
	 * 查询当前有效的纳税主体与纳税主体与汇缴(税种)关系 组成回角关系树
	 * @param taxCode
	 */
	List<LegalTaxCategory> getLegalTaxCategories(){
		return legalEntityDao.getAllLegalTaxCategories(LegalTaxCategoryDef.VAT);
	}
	
	
	protected void processPrintSite(
			List<TmsMdLegalEnablePrint> legalEnablePrints,
			LegalEntityNode rootlLegalEntityNode) {
		//获取当前有效的是否打印点
		for(TmsMdLegalEnablePrint legalEnablePrint:legalEnablePrints){
			if(legalEnablePrint.getLegalEntityId()!=null){
//				AssertHelper.notEmpty_assert(legalEnablePrint.getLegalEntityId(),"纳税实体主键不能为空");
				LegalEntityNode legalEntityNode=(LegalEntityNode) rootlLegalEntityNode.getPosterities().get(legalEnablePrint.getLegalEntityId());
				if(legalEntityNode!=null){
//					AssertHelper.notEmpty_assert(legalEntityNode,"纳税实体没有找到 id:"+legalEnablePrint.getLegalEntityId());
					//为适应中信和长证逻辑,纳税实体上的是否打印点优先级比关系上的打印点优先级高
					//又不用了
//					if(!legalEntityNode.isPrintSite()){
						legalEntityNode.setPrintSite(legalEnablePrint.getIsEnablePrint());	
						legalEntityNode.setParentPrintSiteId(legalEnablePrint.getParentId());
//					}	
				}
				
			}
					
//			System.out.println(legalEntityNode.getId()+" is printSite?"+legalEntityNode.isPrintSite());
		}
		Collection<OrgNode> allLegalEntityNodes=rootlLegalEntityNode.getPosterities().values();		
		//将打印点挂上纳税人实体 
		for(Node node:allLegalEntityNodes){
			LegalEntityNode legalEntityNode=(LegalEntityNode)node;
			buildPrintSiteNode(legalEntityNode,rootlLegalEntityNode);
		}
	}
	protected void processLegalEquipment(
			List<TmsMdLegalEquipment> legalEquipments,
			LegalEntityNode rootlLegalEntityNode) {
		//获取当前有效的纳税主体与打印终端关系
		PrinterTerminalNode rootTerminalNode=buildEquipmentTree(legalEquipments, rootlLegalEntityNode);
		for(Node node:rootTerminalNode.getChildren()){//循环打印终端第二层,挂到纳税实体下面
			PrinterTerminalNode printerTerminalNode=(PrinterTerminalNode)node;
//			AssertHelper.notEmpty_assert(printerTerminalNode.getLegalEntityId(),"打印服务器/终端的纳税实体主键不能为空");
			if(printerTerminalNode.getLegalEntityId()!=null){
				LegalEntityNode legalEntityNode=(LegalEntityNode) rootlLegalEntityNode.getPosterities().get(printerTerminalNode.getLegalEntityId());
				if(legalEntityNode!=null){
//					AssertHelper.notEmpty_assert(legalEntityNode,"纳税实体没有找到 id:"+printerTerminalNode.getLegalEntityId());
					legalEntityNode.addPrinterTerminalNode(printerTerminalNode);
				}				
			}			
		}
	}
	protected void processSelfLegalNumber(
			List<TmsMdUsageLocalLegal> usageLocalLegals,
			LegalEntityNode rootlLegalEntityNode) {
		//获取当前有效的是否采用自身纳税识别号
		for(TmsMdUsageLocalLegal usageLocalLegal:usageLocalLegals){
//			System.out.println(usageLocalLegal.getId()+" is isSelfLicenseCode?"+usageLocalLegal.getIsUsageLocalRegNo());
//			AssertHelper.notEmpty_assert(usageLocalLegal.getLegalEntityId(),"纳税实体主键不能为空");
			if(usageLocalLegal.getLegalEntityId()!=null){
				LegalEntityNode legalEntityNode=(LegalEntityNode) rootlLegalEntityNode.getPosterities().get(usageLocalLegal.getLegalEntityId());
				if(legalEntityNode!=null){
					legalEntityNode.setSelfLicenseCode(usageLocalLegal.getIsUsageLocalRegNo());	
				}else{
	//				AssertHelper.notEmpty_assert(legalEntityNode,"纳税实体没有找到 id:"+usageLocalLegal.getLegalEntityId());
				}
			}
		}
		//根据是否采用纳税人识别号属性将每个纳税实体挂上LicenseNoNode	
		for(Node legalEntityNode:rootlLegalEntityNode.getPosterities().values()){
			buildLicenseNoNode((LegalEntityNode)legalEntityNode,rootlLegalEntityNode);
		}
	}
	protected void processLegalInvoice(List<TmsMdLegalInvoice> legalInvoices,
			LegalEntityNode rootlLegalEntityNode) {
		//获取当前有效的纳税人限额
		for(TmsMdLegalInvoice legalInvoice:legalInvoices){
//			AssertHelper.notEmpty_assert(legalInvoice.getLegalEntityId(),"纳税实体主键不能为空");
			if(legalInvoice!=null){
				LegalEntityNode legalEntityNode=(LegalEntityNode) rootlLegalEntityNode.getPosterities().get(legalInvoice.getLegalEntityId());
//				AssertHelper.notEmpty_assert(legalEntityNode,"纳税实体没有找到 id:"+legalInvoice.getLegalEntityId());
				if(legalEntityNode!=null){
					legalEntityNode.addInvoiceLimitAmountValue(legalInvoice.getInvoiceCategory(),legalInvoice.getInvoiceLimitAmountValue());
				}
			}
			
		}
	}
	protected void processLegalEntityAndOrgRelation(
			List<TmsMdOrgLegalEntity> orgLegalEntities,
			LegalEntityNode rootlLegalEntityNode) {
		//设置纳税实体关联的所属机构,和对应的BizOrgNode
		for(TmsMdOrgLegalEntity tmsMdOrgLegalEntity:orgLegalEntities){
//			AssertHelper.notEmpty_assert(tmsMdOrgLegalEntity.getLegalEntityId(),"纳税实体主键不能为空");
//			AssertHelper.notEmpty_assert(tmsMdOrgLegalEntity.getBizOrgCode(),"纳税实体关联的机构数据不能为空");
			if(tmsMdOrgLegalEntity.getLegalEntityId()!=null&&tmsMdOrgLegalEntity.getBizOrgCode()!=null){
				LegalEntityNode legalEntityNode=(LegalEntityNode) rootlLegalEntityNode.getPosterities().get(tmsMdOrgLegalEntity.getLegalEntityId());
				//AssertHelper.notEmpty_assert(legalEntityNode,"纳税实体没有找到 id:"+tmsMdOrgLegalEntity.getLegalEntityId());
				if(legalEntityNode!=null){
					legalEntityNode.setBizOrgNode((BizOrgNode)OrgCacheUtils.getNodeByOrgId(tmsMdOrgLegalEntity.getOrgId()));
				}
			}
		}
	}
	/**
	 * 计算纳税人识别号
	 * @param legalEntityNode
	 * @param rootLegalEntity
	 */
	protected void buildLicenseNoNode(LegalEntityNode legalEntityNode,LegalEntityNode rootLegalEntity){
		if(legalEntityNode.getId()==null){
			return;
		}
//		System.out.println(legalEntityNode.getId()+" is isSelfLicenseCode?"+legalEntityNode.isSelfLicenseCode());
		//处理当前节点
		if(legalEntityNode.isSelfLicenseCode()){//使用自己纳税人识别号
			if(legalEntityNode.getLicenseNoNode()==null){//没有就生成节点
				LicenseNoNode licenseNoNode = assembleLicenseNoNode(legalEntityNode);
				legalEntityNode.setLicenseNoNode(licenseNoNode);
				legalEntityNode.setInvoiceLimitAmountValueMap(legalEntityNode.getSelfInvoiceLimitAmountValueMap());
			}			
		}else{//从上下线上找到是打印点的纳税实体
			LegalEntityNode parentPrintLegal=getParentLicenseCodeLegal(legalEntityNode);
			if(parentPrintLegal.getLicenseNoNode()==null){//没有就生成节点
				LicenseNoNode licenseNoNode = assembleLicenseNoNode(parentPrintLegal);
				parentPrintLegal.setLicenseNoNode(licenseNoNode);
				parentPrintLegal.setInvoiceLimitAmountValueMap(parentPrintLegal.getSelfInvoiceLimitAmountValueMap());
			}	
			legalEntityNode.setLicenseNoNode(parentPrintLegal.getLicenseNoNode());
		}
	}
	protected LicenseNoNode assembleLicenseNoNode(LegalEntityNode parentLegalEntityNode) {
		LicenseNoNode licenseNoNode=new LicenseNoNode(parentLegalEntityNode.getId(), parentLegalEntityNode.getName());
		return licenseNoNode;
	}
	/**
	 * 处理纳税实体的打印关系点
	 * @param legalEntityNode 当前要处理的纳税实体
	 * @param rootlLegalEntityNode 纳税实体的根节点,必须要由此找到所有的纳税实体
	 */
	protected void buildPrintSiteNode(LegalEntityNode legalEntityNode,LegalEntityNode rootlLegalEntityNode){
		if(legalEntityNode.getId()==null){//到根节点不再处理
			return;
		}
//		if("700000".equals(legalEntityNode.getCode())){
//			System.out.println(11);
//		}
		//处理当前节点
		if(legalEntityNode.isPrintSite()){//自己就是打印点			
			if(legalEntityNode.getPrintSiteNode()==null){//没有就生成节点
				PrintSiteNode printSiteNode = assemblePrintSiteNode(legalEntityNode);
				legalEntityNode.setPrintSiteNode(printSiteNode);
			}			
		}else{//从上下线上找到是打印点的纳税实体
			LegalEntityNode parentPrintLegal=getParentPrintLegal(legalEntityNode);
			if(parentPrintLegal.getPrintSiteNode()==null){//没有就生成节点
				PrintSiteNode printSiteNode = assemblePrintSiteNode(parentPrintLegal);
				parentPrintLegal.setPrintSiteNode(printSiteNode);
			}	
			legalEntityNode.setPrintSiteNode(parentPrintLegal.getPrintSiteNode());
		}
	}
	LegalEntityNode getParentPrintLegal(LegalEntityNode legalEntityNode){
		if(legalEntityNode.getId()==null){//顶节点就返回
			return legalEntityNode;
		}
		if(legalEntityNode.isPrintSite()){
			return legalEntityNode;
		}else{
			return getParentPrintLegal((LegalEntityNode) legalEntityNode.getParent());
		}
	}
	LegalEntityNode getParentLicenseCodeLegal(LegalEntityNode legalEntityNode){
		if(legalEntityNode.getId()==null){//顶节点就返回
			return legalEntityNode;
		}
		if(legalEntityNode.isSelfLicenseCode()){
			return legalEntityNode;
		}else{
			return getParentPrintLegal((LegalEntityNode) legalEntityNode.getParent());
		}
	}
//	else if(parentLegalEntityNode!=null){
//		if(parentLegalEntityNode.isPrintSite()){//父级是打印点
//			if(parentLegalEntityNode.getPrintSiteNode()==null){//父级没有节点就生成
//				PrintSiteNode printSiteNode = assemblePrintSiteNode(parentLegalEntityNode);
//				parentLegalEntityNode.setPrintSiteNode(printSiteNode);
//				legalEntityNode.setPrintSiteNode(printSiteNode);
//			}else {//直接用父级的打印点
//				legalEntityNode.setPrintSiteNode(parentLegalEntityNode.getPrintSiteNode());
//			}
//		}else{
////			buildPrintSiteNode(legalEntityNode,(LegalEntityNode)parentLegalEntityNode.getParent());
//		}
//		
//	}else{
////		throw new BusinessException("已到根节点,但还是没有找到使用的打印点");
//	}
	protected PrintSiteNode assemblePrintSiteNode(LegalEntityNode parentLegalEntityNode) {
		PrintSiteNode printSiteNode=new PrintSiteNode(parentLegalEntityNode.getId(), parentLegalEntityNode.getName());
		return printSiteNode;
	}
	/**
	 *  
	 * @param legalEquipments
	 * @param root
	 * @return
	 */
	protected PrinterTerminalNode buildEquipmentTree(List<TmsMdLegalEquipment> legalEquipments,
			LegalEntityNode root) {
		PrinterTerminalNode equipmentroot = assembleEquipmentRoot();
		Map<String, Collection<TmsMdLegalEquipment>> equipmentsubordinateRelations = new HashMap<String, Collection<TmsMdLegalEquipment>>();
		for(TmsMdLegalEquipment legalEquipment:legalEquipments){
			//从legalTaxCategories中获取父级id
			String superiorId = legalEquipment.getParentId();
			//根据上級ID把下属的机构都归在一个机构下
			if (equipmentsubordinateRelations.keySet().contains(superiorId)) {
				Collection<TmsMdLegalEquipment> subordinates = equipmentsubordinateRelations.get(superiorId);
				subordinates.add(legalEquipment);
			} else {
				Collection<TmsMdLegalEquipment> subordinates = new ArrayList<TmsMdLegalEquipment>();
				subordinates.add(legalEquipment);
				equipmentsubordinateRelations.put(superiorId, subordinates);
			}	
		}
		assembleEquipmentTree(equipmentroot, equipmentsubordinateRelations);
		return equipmentroot;
	}
	public String getLegalEntityId(List<LegalTaxCategory> legalTaxCategories,String id){
		for(LegalTaxCategory legalTaxCategory:legalTaxCategories){
			if(legalTaxCategory.getId().equals(id)){
				return legalTaxCategory.getLegalEntityId();
			}
		}
		return null;
	}
	protected void assembleTree(LegalEntityNode superior, Map<String, Collection<TmsMdLegalEntity>> subordinateRelations) {
		String superiorId = superior.getId();
		Collection<TmsMdLegalEntity> subordinates = subordinateRelations.get(superiorId);

		if (subordinates == null || subordinates.isEmpty()) {
			return;
		}
		
		for (TmsMdLegalEntity TmsMdLegalEntity : subordinates) {
			LegalEntityNode subordinate = assemble(TmsMdLegalEntity);
			subordinate.setDataLevel(superior.getDataLevel()+1);
			superior.add(subordinate);
			assembleTree(subordinate, subordinateRelations);
		}
	}
	protected void assembleEquipmentTree(PrinterTerminalNode superior, Map<String, Collection<TmsMdLegalEquipment>> subordinateRelations) {
		String superiorId = superior.getId();
		Collection<TmsMdLegalEquipment> subordinates = subordinateRelations.get(superiorId);

		if (subordinates == null || subordinates.isEmpty()) {
			return;
		}
		
		for (TmsMdLegalEquipment TmsMdLegalEntity : subordinates) {
			PrinterTerminalNode subordinate = assembleEquipment(TmsMdLegalEntity);
			superior.add(subordinate);
			assembleEquipmentTree(subordinate, subordinateRelations);
		}
	}
	protected LegalEntityNode assemble(TmsMdLegalEntity legalEntity) {
		LegalEntityNode node=new LegalEntityNode(legalEntity.getId(),legalEntity.getLegalEntityName());
		node.setCode(legalEntity.getLegalEntityCode());
		node.setType(legalEntity.getLegalEntityType());
		node.setSelfLicenseName(legalEntity.getLicenseName());
		node.setSelfLicenseNo(legalEntity.getLicenseNo());
		node.setBankAccountNum(legalEntity.getBankAccountNum());
		node.setBankBranchName(legalEntity.getBankBranchName());
		node.setRegistrationContactAddress(legalEntity.getRegistrationContactAddress());
		node.setRegistrationContactPhone(legalEntity.getRegistrationContactPhone());
		node.setPrintSite("1".equals(legalEntity.getIsEnablePrint()));
		return node;
	}
	protected PrinterTerminalNode assembleEquipment(TmsMdLegalEquipment legalEquipment) {
		PrinterTerminalNode node=new PrinterTerminalNode(legalEquipment.getEquipmentId(),legalEquipment.getEquipmentId());
		node.setLegalEntityId(legalEquipment.getLegalEntityId());
		node.setEquipmentId(legalEquipment.getEquipmentId());
		return node;
	}
	protected LegalEntityNode assembleRoot() {
		LegalEntityNode legalEntityNode=new LegalEntityNode(null,"Root");
		legalEntityNode.setDataLevel(1);
		legalEntityNode.setBizOrgNode((BizOrgNode)OrgCacheUtils.getTopNode());
		legalEntityNode.addInvoiceLimitAmountValue("1",10000000l);
		legalEntityNode.addInvoiceLimitAmountValue("2",20000000l);
		
		legalEntityNode.setSelfLicenseCode(true);	
		LicenseNoNode licenseNoNode = assembleLicenseNoNode(legalEntityNode);
		legalEntityNode.setLicenseNoNode(licenseNoNode);
		legalEntityNode.setInvoiceLimitAmountValueMap(legalEntityNode.getSelfInvoiceLimitAmountValueMap());
		
		legalEntityNode.setPrintSite(true);	
		PrintSiteNode printSiteNode = assemblePrintSiteNode(legalEntityNode);
		legalEntityNode.setPrintSiteNode(printSiteNode);;
		
		return legalEntityNode;
	}
	protected PrinterTerminalNode assembleEquipmentRoot() {
		PrinterTerminalNode node=new PrinterTerminalNode(null,"Root");
		return node;
	}
//	protected static void displayNode(Node treeNode){
//		System.out.println(treeNode.toString());
//		LegalEntityNode legalEntityNode=(LegalEntityNode)treeNode;
//		Collection<LegalEntityNode> enumeration = legalEntityNode.get;
//		while (enumeration.hasMoreElements()) {
//			displayNode(enumeration.nextElement());
//		}
//	}
}
