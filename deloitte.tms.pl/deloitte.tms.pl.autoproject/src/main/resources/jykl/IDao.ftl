package ${pojo.getPackageName()}.dao;
// Generated ${date} by Hibernate Tools ${version}

<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>/**
 * Home object for domain model class ${declarationName}.
 * @see ${pojo.getQualifiedDeclarationName()}
 * @author Hibernate Tools
 */
public interface ${declarationName}Dao extends IDao<${declarationName}>{
	public static final String BEAN_ID="${pojo.getDeclarationNameFirstLetterLower()}Dao";
	public DaoPage find${declarationName}ByParams(Map params, Integer pageIndex,Integer pageSize);
	public List find${declarationName}ByParams(Map params);
}
</#assign>
import java.util.List;
import java.util.Map;

import com.bocommlife.channel.core.commons.IDao;
import com.bocommlife.channel.core.commons.support.DaoPage;
import ${pojo.getModelPackageName()}.${declarationName};
${classbody}
