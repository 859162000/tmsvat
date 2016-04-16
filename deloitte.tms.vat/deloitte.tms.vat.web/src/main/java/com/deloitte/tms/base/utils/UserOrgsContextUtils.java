package com.deloitte.tms.base.utils;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.deloitte.tms.base.cache.model.BizOrgNode;
import com.deloitte.tms.base.cache.utils.OrgCacheUtils;
import com.deloitte.tms.pl.core.commons.utils.AssertHelper;
import com.deloitte.tms.pl.core.context.utils.ContextUtils;
import com.deloitte.tms.pl.orgpath.model.OrgPath;

public class UserOrgsContextUtils {
	
	public static String getUserOrgStrByHql(){
		return getUserOrgStrByHql(null, null, null, 1);
	}
	public static String getUserOrgStrBySql(){
		return getUserOrgStrBySql(null, null, null, 1);
	}
	public static String getUserOrgStrByHql(String orgId, Date date) {
		return getUserOrgStrByHql(orgId, null, date, 1);
	}
	public static String getUserOrgStrBySql(String orgId, Date date) {
		return getUserOrgStrBySql(orgId, null, date, 1);
	}
	/**
	 * 
	 * @param orgId
	 * @param userId
	 *            User标识
	 * @param groupId
	 *            组标识
	 * @param date
	 *            生效日期
	 * @param isDivision
	 *            1为机构,0为部门
	 * @return
	 */
	private static String getUserOrgStrByHql(String orgId, String userId,Date date, int isDivision) {
//		if(date==null)
//		{
//			date=new Date();
//		}
		if(orgId==null)
		{
			orgId=ContextUtils.getCurrentOrgId();
		}
//		String compareDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		AssertHelper.notEmpty_assert(orgId, "机构代码不能为空");
		BizOrgNode bizOrgNode=OrgCacheUtils.getNodeByOrgCode(orgId);
		AssertHelper.notEmpty_assert(bizOrgNode, "机构代码"+orgId+"没有找到相应orgPath数据");
		StringBuffer query = new StringBuffer(
				" select orgcode from OrgPath u where u.orgseq  like ");
		query.append(" '"+bizOrgNode.getDataSeq() + "%' ");
//		query.append(" and (u.effectDate is not null or u.effectDate <=to_date('")
//				.append(compareDate).append("', 'yyyy-mm-dd')) ");
//		query.append(" and (u.quitDate is null or u.quitDate > to_date('")
//				.append(compareDate).append("', 'yyyy-mm-dd')) ");
		return query.toString();
	}
	private static String getUserOrgStrBySql(String orgId, String userId,Date date, int isDivision) {
		return convertUserDeptStrToSql(getUserOrgStrByHql(orgId,userId,date,isDivision));
	}
	/**
	 * 转换查询getUserDeptStr的HQL为SQL
	 * 
	 * @param userDeptStr
	 * @return
	 * @author dada
	 */
	public static String convertUserDeptStrToSql(String userDeptStr) {
		String retval = userDeptStr;
		retval = StringUtils.replace(retval, "OrgPath", OrgPath.TABLE);
		retval = StringUtils.replace(retval, "u.division", "u.isdivision");
		retval = StringUtils.replace(retval, "false", "0");
		retval = StringUtils.replace(retval, "true", "1");
		return retval;
	}
}
