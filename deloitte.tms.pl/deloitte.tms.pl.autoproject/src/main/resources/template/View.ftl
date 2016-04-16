package ${pojo.getPackageName()}.view;
// Generated ${date} by Hibernate Tools ${version}

<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
/**
 * Home object for domain model class ${declarationName}.
 * @see ${pojo.getQualifiedDeclarationName()}
 * @author Hibernate Tools
 */
@Component("${pojo.getDeclarationNameFirstLetterLower()}View")
public class ${declarationName}View {
	@Resource
	I${declarationName}Service ${pojo.getDeclarationNameFirstLetterLower()}Service;
	@SuppressWarnings("unchecked")
	public void load${declarationName}Page(DataSet dataSet) throws Exception {
		Integer pageIndex = dataSet.getPageIndex();
		Integer pageSize = dataSet.getPageSize();
		if (pageSize == 0) {
			pageSize = 65525;
		}
		Map params=(Map) dataSet.getParameters();
		DaoPage result=${pojo.getDeclarationNameFirstLetterLower()}Service.find${declarationName}ByParams(params, pageIndex, pageSize);
		DatasetUtils.DaoPageToDoradoPage(dataSet, result);
	}
	@SuppressWarnings("unchecked")
	public void load${declarationName}List(DataSet dataSet) throws Exception {
		Map<String, Object> params = (Map<String, Object>) dataSet.getParameters();
		//String label = Converter.to(String.class).convert(params, "label");
		//String code = Converter.to(String.class).convert(params, "code");
		
		List result = ${pojo.getDeclarationNameFirstLetterLower()}Service.find${declarationName}ByParams(params);
		dataSet.addRecords(result);
	}
	@SuppressWarnings("unchecked")
	public Object execCommandCreate(Map<String, DataSet> dataSets, Object parameter) throws Exception {
		Map<String, Object> parameters = (Map<String, Object>) parameter;
		//Long parentId = Converter.to(Long.class).convert(parameters, "parentId");
		
		DataSet dsEntity = dataSets.get("dsEntity");
		Object entity = DatasetUtils.to(Object.class).getCurrentEntity(dsEntity);
		
		${pojo.getDeclarationNameFirstLetterLower()}Service.save(entity);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Object execCommandRemove(Map<String, DataSet> dataSets, Object parameter) throws Exception {
		DataSet dsEntity = dataSets.get("dsEntity");
		Object entity = DatasetUtils.to(Object.class).getCurrentEntity(dsEntity);
		
		${pojo.getDeclarationNameFirstLetterLower()}Service.remove(entity);
		return null;
	}
	
	public Object execCommandUpdate(Map<String, DataSet> dataSets, Object parameter) throws Exception {
		DataSet dsEntity = dataSets.get("dsEntity");
		Object entity = DatasetUtils.to(Object.class).getCurrentEntity(dsEntity);
		
		${pojo.getDeclarationNameFirstLetterLower()}Service.update(entity);
		return null;
	}
	public Object execCommandSave(Map<String, DataSet> dataSets, Object parameter) throws Exception {
		DataSet dsEntity = dataSets.get("dsEntity");
		Map dataListsMap=DatasetUtils.assembleDatasetMap(dsEntity);
		${pojo.getDeclarationNameFirstLetterLower()}Service.saveDataListsMap(dataListsMap);
		return null;
	}
}
</#assign>
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.marmot.view.DataSet;

import org.springframework.stereotype.Component;

import com.ling.core.commons.support.DaoPage;
import com.ling.core.commons.utils.Converter;
import com.ling.core.commons.utils.DatasetUtils;
import ${pojo.getPackageName()}.model.${declarationName};
import ${pojo.getPackageName()}.service.I${declarationName}Service;
${classbody}
