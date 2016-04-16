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
	${declarationName}Service ${pojo.getDeclarationNameFirstLetterLower()}Service;
	@Resource(name="${pojo.getDeclarationNameFirstLetterLower()}Import")
	ImportFile importFile;
	
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
/***
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
**/	
	public Object execCommandSaveImport(Map<String, DataSet> dataSets, Object parameter) throws Exception {
		DataSet dsEntity = dataSets.get("dsEntity");
		Collection<${declarationName}> imports=dsEntity.getRecords();
		
		Map map = new HashMap();
		try {
			${pojo.getDeclarationNameFirstLetterLower()}Service.saveImport${declarationName}s(imports);
		} catch (Exception e) {
			map.put("message",e.getMessage());
		}
		return map;
	}

	// 导入
	public void loadSalDeductXls(DataSet dataSet) throws Exception {
		HttpDoradoContext context = (HttpDoradoContext) DoradoContext
				.getContext();
		HttpSession session = context.getRequest().getSession();
		// 获取模板
		String attribute = importFile.getTempName();
		Collection<?> pojos = (Collection<?>) session.getAttribute(attribute);
		session.removeAttribute(attribute);
		if (pojos != null) {
			for (Iterator iterator = pojos.iterator(); iterator.hasNext();) {
				Object r = (Object) iterator.next();
				if (!(r instanceof ${declarationName})) {
					break;
				}
				${declarationName} entity = (${declarationName}) r;
				dataSet.addRecord(entity);
			}
		}

	}
}
</#assign>
import java.util.List;
import java.util.Map;
import java.util.Iterator;
import java.util.Collection;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.marmot.view.DataSet;

import org.springframework.stereotype.Component;

import com.bocommlife.channel.core.commons.support.DaoPage;
import com.bocommlife.channel.core.utils.DatasetUtils;
import ${pojo.getPackageName()}.model.${declarationName};
import ${pojo.getPackageName()}.service.${declarationName}Service;
import com.bocommlife.channel.service.importation.ImportFile;
import com.bstek.dorado.common.DoradoContext;
import com.bstek.dorado.common.HttpDoradoContext;
${classbody}
