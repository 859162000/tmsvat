<#assign pojo=oneToManyPojo.getOne()>
<#assign many=oneToManyPojo.getMany()>
<#assign ones=oneToManyPojo.getOnes()>
package ${pojo.getPackageName()}.service;
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign declarationName_firstLower = pojo.getDeclarationNameFirstLetterLower()>

import java.util.List;
import java.util.Map;

import com.ling2.core.commons.support.DaoPage;
import com.ling2.core.service.IService;
import ${pojo.getPackageName()}.model.${declarationName};
import ${pojo.getPackageName()}.model.${declarationName}Param;
import ${pojo.getPackageName()}.dao.${declarationName}Dao;
<#foreach child in ones>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>	
import ${child.getPackageName()}.model.${childdeclarationName};
import ${child.getPackageName()}.model.${childdeclarationName}Param;
</#foreach>
<#foreach child in many>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>	
import ${child.getPackageName()}.model.${childdeclarationName};
import ${child.getPackageName()}.model.${childdeclarationName}Param;
</#foreach>

<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
/**
 * Home object for domain model class ${declarationName}.
 * @see ${pojo.getQualifiedDeclarationName()}
 * @author Hibernate Tools
 */
public interface ${declarationName}Service extends IService{
	public static final String BEAN_ID="${pojo.getDeclarationNameFirstLetterLower()}Service";
	public static final String ${declarationName?upper_case}_CACHE_ID = "ling2.${declarationName?upper_case}_CACHE_ID";
	
	public DaoPage find${declarationName}ByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<${declarationName}Param> find${declarationName}ByParams(Map params);
	
	public void save${declarationName}DataListsMap(Map dataListsMap);
	
	public ${declarationName}Param convert${declarationName}ToParam(${declarationName} model);
	
	public ${declarationName} convert${declarationName}ParamToEntity(${declarationName}Param ${declarationName_firstLower}Param);
	
	/**
	 * 获取机构树缓存
	 * @return
	 */
	public List<${declarationName}Param> get${declarationName}CacheTree();
	/**
	 * 获取生效的机构树缓存
	 * @return
	 */
	public List<${declarationName}Param> getEffect${declarationName}CacheTree();
	/**
	 * 通过部门id从缓存树中获取业务单位
	 */
	public ${declarationName}Param get${declarationName}ById(String ${declarationName}Id);
	/**
	 * 刷新业务单位的缓存树
	 */
	public void flush${declarationName}CacheTree();
	/**
	 * 刷新缓存树
	 */
	public void save${declarationName}Node(${declarationName}Param ${declarationName_firstLower}Param);
	
<#foreach child in many>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>	
	public DaoPage find${childdeclarationName}ByParams(Map params, Integer pageIndex,Integer pageSize);
	
	public List<${childdeclarationName}Param> find${childdeclarationName}ByParams(Map params);
	
	public void save${childdeclarationName}DataListsMap(Map dataListsMap);
	
	public ${childdeclarationName}Param convert${childdeclarationName}ToParam(${childdeclarationName} model);
	
	public ${childdeclarationName} convert${childdeclarationName}ParamToEntity(${childdeclarationName}Param param);
	
</#foreach>
<#foreach child in ones>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>	
	public ${childdeclarationName} find${childdeclarationName}(Map params);
</#foreach>
}
