package com.deloitte.tms.vat.context;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Service;

import com.deloitte.tms.base.cache.model.LegalEntityNode;
import com.deloitte.tms.base.cache.utils.LegalEntityCacheUtils;
import com.deloitte.tms.pl.core.commons.constant.ContextDef;
import com.deloitte.tms.pl.core.commons.constant.LoginConstantDef;
import com.deloitte.tms.pl.core.commons.utils.CookieUtils;
import com.deloitte.tms.pl.core.context.ContextHolder;
import com.deloitte.tms.pl.core.context.IContextHolder;
import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.security.core.context.SecurityContextHolder;
import com.deloitte.tms.pl.security.model.SecurityUser;
@Service(IContextHolder.BEAN_ID)
public class VatCenterContextHolder extends SecurityContextHolder{
	@Override
	public void initUserDetail(SecurityUser user,HttpServletRequest request,HttpServletResponse response) {
		try {
			super.initUserDetail(user, request, response);
			Map userCache=user.getCachedProperties();
			//这里还不能直接从contextutils获取数据
			LegalEntityNode legalEntityNode=LegalEntityCacheUtils.getLegalNodeByOrgCode((String) userCache.get(ContextDef.DIVISIONCODE));
			if(legalEntityNode!=null){
				userCache.put(VatContextDef.CURRENT_LEGAL_ENTITY_ID,legalEntityNode.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			CookieUtils.addCookie(ContextHolder.getRequest(),ContextHolder.getResponse(),LoginConstantDef.LOGIN_ERROKEY,e.getMessage());
			throw new AuthenticationServiceException("用户基本信息初始化失败:"+e.getMessage());
		}		
	}
}
