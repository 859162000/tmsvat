package ${pojo.getPackageName()}.service;
// Generated ${date} by Hibernate Tools ${version}

<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>/**
 * Home object for domain model class ${declarationName}.
 * @see ${pojo.getQualifiedDeclarationName()}
 * @author Hibernate Tools
 */
public interface ${declarationName}Service extends IService{
	public static final String BEAN_ID="${pojo.getDeclarationNameFirstLetterLower()}Service";
	
	public DaoPage find${declarationName}ByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List find${declarationName}ByParams(Map params);
	
	public void save${declarationName}DataListsMap(Map dataListsMap);
}
</#assign>
import java.util.List;
import java.util.Map;

import com.ling2.core.commons.support.DaoPage;
import com.ling2.core.service.IService;
import ${pojo.getPackageName()}.model.${declarationName};
import ${pojo.getPackageName()}.dao.${declarationName}Dao;
${classbody}
