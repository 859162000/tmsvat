package com.deloitte.tms.base.cache.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;

import com.deloitte.tms.pl.core.commons.utils.AssertHelper;

/**
 * 纳税实体节点
 * @author bo.wang
 * @create 2016年3月24日 下午9:02:45 
 * @version 1.0.0
 */
public class LegalEntityNode extends OrgNode{
	/**
	 * 计算的纳税人识别号
	 */
	private String licenseNo;
	/**
	 * 计算得到的纳税人id
	 */
	private String licenseNoId;
	/**
	 * 当前纳税实体自身的纳税人识别号
	 */
	private String selfLicenseNo;
	/**
	 * 计算的纳税人识别号名称
	 */
	private String licenseName;	
	/**
	 * 当前纳税实体自身的纳税人识别号名称
	 */
	private String selfLicenseName;	
	/**
	 * 是否使用自身纳税人识别号
	 */
	boolean isSelfLicenseCode;
	/**
	 * 计算得到的纳税人识别号
	 */
	LicenseNoNode licenseNoNode;	
	/**
	 * 是否打印点
	 */
	boolean isPrintSite;
	/**
	 * 与isPrintSite同组,打印关系点上维护的上级
	 */
	String parentPrintSiteId;
	/**
	 * 计算得到的打印点
	 */
	PrintSiteNode printSiteNode;
	/**
	 * 计算得到的打印点打印点纳税人id
	 */
	String printSiteId;	

	/**
	 * 销方开户银行账号
	 */
	private String bankAccountNum;
	/**
	 * 销方开户银行
	 */
	private String bankBranchName;
	/**
	 * 销方地址
	 */
	private String registrationContactAddress;
	/**
	 * 销方地址联系电话
	 */
	private String registrationContactPhone;
	/**
	 * 发票票面限额值
	 */
	Map<String, Long> invoiceLimitAmountValueMap=new HashMap<String, Long>();	
	/**
	 * 自身发票票面限额值
	 */
	Map<String, Long> selfInvoiceLimitAmountValueMap=new HashMap<String, Long>();
	/**
	 * 对应的组织
	 */
	BizOrgNode bizOrgNode;
	
	/**
	 * 纳税实体下的打印终端
	 */
	Collection<PrinterTerminalNode> printerTerminalNodes=new ArrayList<PrinterTerminalNode>();
	
	public void addPrinterTerminalNode(PrinterTerminalNode printerTerminalNode){
		printerTerminalNode.setLegalEntityNode(this);
		printerTerminalNodes.add(printerTerminalNode);
	}
	public Long getInvoiceLimitAmountValue(String invoiceType){
		AssertHelper.notEmpty_assert(invoiceType,"没有找到发票类型不能为空");
		Long result= invoiceLimitAmountValueMap.get(invoiceType);
		AssertHelper.notEmpty_assert(result,"没有找到发票类型为:"+invoiceType+"的发票限额");
		return result;
	}
	public Long getSelfInvoiceLimitAmountValue(String invoiceType){
		AssertHelper.notEmpty_assert(invoiceType,"没有找到发票类型不能为空");
		Long result= selfInvoiceLimitAmountValueMap.get(invoiceType);
		AssertHelper.notEmpty_assert(result,"没有找到发票类型为:"+invoiceType+"的发票限额");
		return result;
	}
	public void addInvoiceLimitAmountValue(String invoiceType,Long value){
		AssertHelper.notEmpty_assert(invoiceType,"发票类型不能为空");
		selfInvoiceLimitAmountValueMap.put(invoiceType, value);
	}
	public LegalEntityNode(String id, String orgName) {
		super(id, orgName);
	}

	public boolean isPrintSite() {
		return isPrintSite;
	}

	public void setPrintSite(boolean isPrintSite) {
		this.isPrintSite = isPrintSite;
	}

	public PrintSiteNode getPrintSiteNode() {
		return printSiteNode;
	}

	public void setPrintSiteNode(PrintSiteNode printSiteNode) {
		this.printSiteNode = printSiteNode;
		this.printSiteId=printSiteNode.getId();
	}

	public boolean isSelfLicenseCode() {
		return isSelfLicenseCode;
	}

	public void setSelfLicenseCode(boolean isSelfLicenseCode) {
		this.isSelfLicenseCode = isSelfLicenseCode;
	}

	public LicenseNoNode getLicenseNoNode() {
		return licenseNoNode;
	}

	public void setLicenseNoNode(LicenseNoNode licenseNoNode) {
		this.licenseNoNode =licenseNoNode;
		this.licenseNoNode.legalEntityNode=this;
		this.licenseNoId=licenseNoNode.getId();
	}

	public Collection<PrinterTerminalNode> getPrinterTerminalNodes() {
		return printerTerminalNodes;
	}

	public void setPrinterTerminalNodes(
			Collection<PrinterTerminalNode> printerTerminalNodes) {
		this.printerTerminalNodes = printerTerminalNodes;
	}

	public BizOrgNode getBizOrgNode() {
		return bizOrgNode;
	}

	public void setBizOrgNode(BizOrgNode bizOrgNode) {
		this.bizOrgNode = bizOrgNode;
	}

	public String getLicenseName() {
		return licenseName;
	}

	public void setLicenseName(String licenseName) {
		this.licenseName = licenseName;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	
	public Map<String, Long> getInvoiceLimitAmountValueMap() {
		return invoiceLimitAmountValueMap;
	}

	public void setInvoiceLimitAmountValueMap(
			Map<String, Long> invoiceLimitAmountValueMap) {
		this.invoiceLimitAmountValueMap = invoiceLimitAmountValueMap;
	}

	public Map<String, Long> getSelfInvoiceLimitAmountValueMap() {
		return selfInvoiceLimitAmountValueMap;
	}

	public void setSelfInvoiceLimitAmountValueMap(
			Map<String, Long> selfInvoiceLimitAmountValueMap) {
		this.selfInvoiceLimitAmountValueMap = selfInvoiceLimitAmountValueMap;
	}

	public String toString(){
		String warp="";
		for(int i=0;i<getLevel();i++){
			warp=" "+warp;
		}
		StringBuilder result=new StringBuilder();
		result.append(warp+"ID：" + id + " 名称：" + name + " 代码：" + code);
		if(getLicenseNoId()!=null){
			result.append("\n"+warp+"------纳税人识别号:"+getLicenseNo()+" 纳税人id:"+getLicenseNoId()+"\n");
		}
		if(getPrintSiteId()!=null){
			result.append("\n"+warp+"------打印点id:"+getPrintSiteId()+"\n");
		}
//		if(legalEntityNode.getPrinterTerminalNodes().size()>0){//显示打印终端
//			for(PrinterTerminalNode printerTerminalNode:legalEntityNode.getPrinterTerminalNodes()){
//				result.append(printerTerminalNode.toString());
//				Enumeration<Node> enumeration = printerTerminalNode.children();
//				while (enumeration.hasMoreElements()) {
//					displayNode(enumeration.nextElement());
//				}
//			}
//		}
		return result.toString();
	}

	public String getSelfLicenseNo() {
		return selfLicenseNo;
	}

	public void setSelfLicenseNo(String selfLicenseNo) {
		this.selfLicenseNo = selfLicenseNo;
	}

	public String getSelfLicenseName() {
		return selfLicenseName;
	}

	public void setSelfLicenseName(String selfLicenseName) {
		this.selfLicenseName = selfLicenseName;
	}

	public String getLicenseNoId() {
		return licenseNoId;
	}

	public void setLicenseNoId(String licenseNoId) {
		this.licenseNoId = licenseNoId;
	}

	public String getPrintSiteId() {
		return printSiteId;
	}

	public void setPrintSiteId(String printSiteId) {
		this.printSiteId = printSiteId;
	}
	public String getBankAccountNum() {
		return bankAccountNum;
	}
	public void setBankAccountNum(String bankAccountNum) {
		this.bankAccountNum = bankAccountNum;
	}
	public String getBankBranchName() {
		return bankBranchName;
	}
	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}
	public String getRegistrationContactAddress() {
		return registrationContactAddress;
	}
	public void setRegistrationContactAddress(String registrationContactAddress) {
		this.registrationContactAddress = registrationContactAddress;
	}
	public String getRegistrationContactPhone() {
		return registrationContactPhone;
	}
	public void setRegistrationContactPhone(String registrationContactPhone) {
		this.registrationContactPhone = registrationContactPhone;
	}
	public String getParentPrintSiteId() {
		return parentPrintSiteId;
	}
	public void setParentPrintSiteId(String parentPrintSiteId) {
		this.parentPrintSiteId = parentPrintSiteId;
	}

}
