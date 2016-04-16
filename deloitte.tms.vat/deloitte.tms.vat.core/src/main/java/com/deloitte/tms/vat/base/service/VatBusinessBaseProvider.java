package com.deloitte.tms.vat.base.service;

import java.util.Collection;

import com.deloitte.tms.base.cache.model.BizOrgNode;
import com.deloitte.tms.base.cache.model.LegalEntityNode;
import com.deloitte.tms.base.cache.model.OrgNode;
import com.deloitte.tms.base.cache.model.PrintSiteNode;
import com.deloitte.tms.base.cache.model.PrinterTerminalNode;

/**
 * 
 * 增值税业务数据基础模块提供接口
 * 提供根据用户名找登录机构
 * 根据找角色找管理机构
 * 根据机构找纳税实体
 * 根据机构找打印终端
 * 根据纳税实体找打印终端
 * 根据机构找打印点
 * 根据纳税实体找打印点
 * 根据打印点找打印机
 * @author bo.wang
 * @create 2016年3月24日 上午11:28:22 
 * @version 1.0.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface VatBusinessBaseProvider {
	@Deprecated
	public Collection<OrgNode> findDataOrgsByUserRole(String role);
	/**
	 * 提供根据用户名找默认登录机构 
	 * @param userId
	 * @return
	 */
	BizOrgNode findDefaultBizOrgByUser(String userId);
	/**
	 * 根据机构找默认纳税实体
	 * @param orgCode 机构代码
	 * @return
	 */
	LegalEntityNode findDefaultLegalEntityByOrgCode(String orgCode);
	/**
	 * 根据机构找默认打印终端
	 * @param orgCode 机构代码
	 * @return
	 */
	Collection<PrinterTerminalNode> findPrinterTerminalByOrgCode(String orgCode);
	/**
	 * 根据纳税实体主键找默认打印终端 
	 * @param lgId 纳税实体表主键
	 * @return
	 */
	Collection<PrinterTerminalNode> findPrinterTerminalByLegalEntityId(String lgId);
	/**
	 * 根据机构代码找默认打印点
	 * @param orgCode 机构代码
	 * @return
	 */
	PrintSiteNode findDefaultPrintSiteNodeByOrgCode(String orgCode);
	/**
	 * 根据纳税实体表主键找默认打印点
	 * @param lgId 纳税实体表主键
	 * @return
	 */
	PrintSiteNode findDefaultPrintSiteNodeByLegalEntityId(String lgId);
	
	/**
	 * 获取机构的纳税人识别号
	 * 功能详细描述
	 * @param orgCode
	 * @return
	 */
	String findDefaultLicenseNoByLegalOrgCode(String orgCode);
	
	/**
	 * 获取纳税实体的纳税人识别号
	 * 功能详细描述
	 * @param lgId
	 * @return
	 */
	String findDefaultLicenseNoByLegalEntityId(String lgId);
}
