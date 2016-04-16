<#assign pojo=oneToManyPojo.getOne()>
<#assign many=oneToManyPojo.getMany()>
<#assign ones=oneToManyPojo.getOnes()>
package ${pojo.getPackageName()}.model;
// Generated by bo.wang with ${version}

<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
/**
 * Home object for domain model class ${declarationName}.
 * @see ${pojo.getQualifiedDeclarationName()}
 * @author ling2.autoproject Tools
 */
public class ${declarationName}Param extends ${declarationName} {

}