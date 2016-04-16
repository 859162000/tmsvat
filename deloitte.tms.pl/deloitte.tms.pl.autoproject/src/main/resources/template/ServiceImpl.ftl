package ${pojo.getPackageName()}.service.impl;
// Generated ${date} by Hibernate Tools ${version}

<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>/**
 * Home object for domain model class ${declarationName}.
 * @see ${pojo.getQualifiedDeclarationName()}
 * @author Hibernate Tools
 */
@Component(${declarationName}Service.BEAN_ID)
public class ${declarationName}ServiceImpl extends BaseService implements ${declarationName}Service{
	@Resource
	${declarationName}Dao ${pojo.getDeclarationNameFirstLetterLower()}Dao;

	@Override
	public IDao getDao() {
		return ${pojo.getDeclarationNameFirstLetterLower()}Dao;
	}

	@Override
	public DaoPage find${declarationName}ByParams(Map params, Integer pageIndex,
			Integer pageSize) {
		if(params==null)
		{
			params=new HashMap();
		}			
		return ${pojo.getDeclarationNameFirstLetterLower()}Dao.find${declarationName}ByParams(params, pageIndex, pageSize);
	}
	@Override
	public List find${declarationName}ByParams(Map params) {
		if(params==null)
		{
			params=new HashMap();
		}
		return ${pojo.getDeclarationNameFirstLetterLower()}Dao.find${declarationName}ByParams(params);
	}
	@Override
	public void save${declarationName}DataListsMap(Map dataListsMap) {
		Collection<${declarationName}> deleteList = BatchUtils.to(${declarationName}.class).getDeleteEntities(dataListsMap);
		Collection<${declarationName}> insertList =  BatchUtils.to(${declarationName}.class).getInsertEntities(dataListsMap);
		Collection<${declarationName}> updateList =  BatchUtils.to(${declarationName}.class).getModifiedEntities(dataListsMap);
		if ((updateList != null) && (updateList.size() > 0)) {
			${pojo.getDeclarationNameFirstLetterLower()}Dao.updateAll(updateList);
		}
		if ((insertList != null) && (insertList.size() > 0)) {
			${pojo.getDeclarationNameFirstLetterLower()}Dao.saveAll(insertList);
		}
		if ((deleteList != null) && (deleteList.size() > 0)) {
			${pojo.getDeclarationNameFirstLetterLower()}Dao.removeAll(deleteList);
		}
		
	}
}
</#assign>
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ling2.core.commons.support.DaoPage;
import com.ling2.core.commons.utils.BatchUtils;
import com.ling2.core.dao.IDao;
import com.ling2.core.service.impl.BaseService;
import ${pojo.getPackageName()}.model.${declarationName};
import ${pojo.getPackageName()}.dao.${declarationName}Dao;
import ${pojo.getPackageName()}.service.${declarationName}Service;
${classbody}
