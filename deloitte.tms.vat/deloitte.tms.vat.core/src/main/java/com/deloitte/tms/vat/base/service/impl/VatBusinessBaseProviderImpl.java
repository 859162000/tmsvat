package com.deloitte.tms.vat.base.service.impl;

import java.util.Collection;

import com.deloitte.tms.base.cache.model.BizOrgNode;
import com.deloitte.tms.base.cache.model.LegalEntityNode;
import com.deloitte.tms.base.cache.model.OrgNode;
import com.deloitte.tms.base.cache.model.PrintSiteNode;
import com.deloitte.tms.base.cache.model.PrinterTerminalNode;
import com.deloitte.tms.base.cache.utils.LegalEntityCacheUtils;
import com.deloitte.tms.base.cache.utils.OrgCacheUtils;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.security.model.impl.DefaultUser;
import com.deloitte.tms.pl.security.service.IUserService;
import com.deloitte.tms.vat.base.service.VatBusinessBaseProvider;

public class VatBusinessBaseProviderImpl implements VatBusinessBaseProvider {
	IUserService userService;

	@Override
	public Collection<OrgNode> findDataOrgsByUserRole(String role) {
		return null;
	}

	@Override
	public BizOrgNode findDefaultBizOrgByUser(String userId) {
		AssertHelper.notEmpty_assert(userId, "用户id不能为空");
		DefaultUser user=userService.getDefaultUserByUserId(userId);
		AssertHelper.notEmpty_assert(user, "用户id对应用户没找到");
		return (BizOrgNode) OrgCacheUtils.getNodeByOrgCode(user.getBizOrgCode());
	}

	@Override
	public LegalEntityNode findDefaultLegalEntityByOrgCode(String orgCode) {
		AssertHelper.notEmpty_assert(orgCode, "机构代码不能为空");
		//前提必须是纳税实体和组织机构一一对应
		return LegalEntityCacheUtils.getLegalNodeByOrgCode(orgCode);
	}

	@Override
	public Collection<PrinterTerminalNode> findPrinterTerminalByOrgCode(String orgCode) {
		AssertHelper.notEmpty_assert(orgCode, "机构代码不能为空");
		//前提必须是纳税实体和组织机构一一对应
		LegalEntityNode legalEntityNode=LegalEntityCacheUtils.getLegalNodeByOrgCode(orgCode);
		AssertHelper.notEmpty_assert(legalEntityNode, "对应机构的纳税实体没没找到");
		return legalEntityNode.getPrinterTerminalNodes();
	}

	@Override
	public Collection<PrinterTerminalNode> findPrinterTerminalByLegalEntityId(
			String lgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrintSiteNode findDefaultPrintSiteNodeByOrgCode(String orgCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrintSiteNode findDefaultPrintSiteNodeByLegalEntityId(String lgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findDefaultLicenseNoByLegalOrgCode(String orgCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findDefaultLicenseNoByLegalEntityId(String lgId) {
		// TODO Auto-generated method stub
		return null;
	}
}
