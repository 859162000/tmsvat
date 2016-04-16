package ${pojo.getPackageName()}.dao.impl;
// Generated ${date} by Hibernate Tools ${version}

<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>/**
 * Home object for domain model class ${declarationName}.
 * @see ${pojo.getQualifiedDeclarationName()}
 * @author Hibernate Tools
 */
@Component(${declarationName}Dao.BEAN_ID)
public class ${declarationName}DaoImpl extends BaseDao<${declarationName}> implements ${declarationName}Dao{
	public DaoPage find${declarationName}ByParams(Map params, Integer pageIndex,Integer pageSize)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		query.append(" from ${declarationName} where 1=1 ");
		Object value=params.get("policyNo");
		if(value!=null&&!"".equals(value))
		{
			query.append(" and policyNo=:policyNo");
			values.put("policyNo", value);
		}
		value=params.get("status");
		if(value!=null)
		{
			query.append(" and status=:status");
			values.put("status", value);
		}
		return pageBy(query, values, pageIndex, pageSize);
	}
	public List find${declarationName}ByParams(Map params)
	{
		StringBuffer query=new StringBuffer();
		Map values=new HashMap();
		query.append(" from ${declarationName} where 1=1 ");
		Object value=params.get("policyNo");
		if(value!=null&&!"".equals(value))
		{
			query.append(" and policyNo=:policyNo");
			values.put("policyNo", value);
		}
		value=params.get("status");
		if(value!=null)
		{
			query.append(" and status=:status");
			values.put("status", value);
		}
		return findBy(query, values);
	}
}
</#assign>
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.bocommlife.channel.core.commons.BaseDao;
import com.bocommlife.channel.core.commons.support.DaoPage;
import ${pojo.getModelPackageName()}.${declarationName};
import ${pojo.getPackageName()}.dao.${declarationName}Dao;
${classbody}
