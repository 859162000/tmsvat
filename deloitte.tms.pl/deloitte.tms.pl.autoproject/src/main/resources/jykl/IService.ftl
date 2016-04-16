package ${pojo.getPackageName()}.service;
// Generated ${date} by Hibernate Tools ${version}

<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>/**
 * Home object for domain model class ${declarationName}.
 * @see ${pojo.getQualifiedDeclarationName()}
 * @author Hibernate Tools
 */
public interface ${declarationName}Service extends Service{
	public static final String BEAN_ID="${pojo.getDeclarationNameFirstLetterLower()}Service";
	
	public DaoPage find${declarationName}ByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List find${declarationName}ByParams(Map params);
	
	public void save${declarationName}DataListsMap(Map dataListsMap);
	
	public void saveImport${declarationName}s(Collection<${declarationName}> datas);
}
</#assign>
import java.util.List;
import java.util.Map;
import java.util.Collection;

import com.bocommlife.channel.core.commons.Service;
import com.bocommlife.channel.core.commons.support.DaoPage;
import ${pojo.getPackageName()}.model.${declarationName};
import ${pojo.getPackageName()}.dao.${declarationName}Dao;
${classbody}
