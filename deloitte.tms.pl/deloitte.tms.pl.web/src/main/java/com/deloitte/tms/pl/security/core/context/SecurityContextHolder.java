package com.deloitte.tms.pl.security.core.context;

import java.util.Collection;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deloitte.tms.pl.core.commons.constant.ContextDef;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.commons.utils.SpringUtil;
import com.deloitte.tms.pl.core.context.BaseContextHolder;
import com.deloitte.tms.pl.orgpath.model.OrgPath;
import com.deloitte.tms.pl.security.dao.IDeptDao;
import com.deloitte.tms.pl.security.dao.IRoleDao;
import com.deloitte.tms.pl.security.model.SecurityUser;
import com.deloitte.tms.pl.security.model.impl.DefaultDept;
import com.deloitte.tms.pl.security.model.impl.DefaultRole;
//@Service(IContextHolder.BEAN_ID)
public class SecurityContextHolder extends BaseContextHolder{
	@Resource
	IRoleDao roleDao;
	@Resource
	IDeptDao deptDao;
	@SuppressWarnings("unchecked")
	@Override
	public void initUserDetail(SecurityUser user,HttpServletRequest request,HttpServletResponse response) {
		roleDao=SpringUtil.getBean(IRoleDao.BEAN_ID);
		deptDao=SpringUtil.getBean(IDeptDao.BEAN_ID);
		Collection<DefaultRole>roles=roleDao.loadRolesByUserName(user.getUsername());
		super.initUserDetail(user, request, response);
		Map userCache=user.getCachedProperties();
		userCache.put(ContextDef.ROLES,roles);
		userCache.put(ContextDef.ROLES_STR,buildRoles(roles));
		DefaultDept defaultDept=deptDao.findUserDeptByUsername(user.getUsername());
		
		if(defaultDept!=null){			
//			AssertHelper.notEmpty_assert(orgPath, "用户所属机构orgpath没有对应数据,登录失败");
			userCache.put(ContextDef.DIVISIONCODE,defaultDept.getOrgCode());
			userCache.put(ContextDef.DIVISIONID,defaultDept.getId());
			userCache.put(ContextDef.DIVISIONNAME,defaultDept.getOrgName());
			
			OrgPath orgPath=deptDao.findOrgPathByOrgId(defaultDept.getId());
			if(orgPath!=null){
				userCache.put(ContextDef.ORGPATH,orgPath);
				userCache.put(ContextDef.DATA_OWNER_CODE,orgPath.getOrgcode2()==null?orgPath.getOrgcode1():orgPath.getOrgcode2());		
			}			
		}
	}
//		AssertHelper.notEmpty_assert(defaultDept, "用户所属机构没有配置,登录失败");
		
	protected String buildRoles(Collection<DefaultRole> roles){
		String roleString="";
		int index=0;
		for(DefaultRole role:roles){
			if(index>0){
				roleString=roleString+",";
			}
			roleString=roleString+role.getName();
			index++;
		}
		return roleString;
	}
}
