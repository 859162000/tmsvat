<#assign pojo=oneToManyPojo.getOne()>
<#assign many=oneToManyPojo.getMany()>
<#assign ones=oneToManyPojo.getOnes()>
<#assign fields=pojo.getFilterdPojoFields()>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign declarationNameFirstLetterLower = pojo.importType(pojo.getDeclarationNameFirstLetterLower())>
<#list fields as field>
${declarationName}.${field.getName()}=<#if field.getComment()?exists>${field.getASCIIComment()}<#else>${field.getName()}</#if>
</#list> 

<#foreach child in many>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>	
<#assign fields=child.getFilterdPojoFields()>
	<#foreach field in fields>
${childdeclarationName}.${field.getName()}=<#if field.getComment()?exists>${field.getASCIIComment()}<#else>${field.getName()}</#if>
	</#foreach>
</#foreach>

<#foreach child in ones>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
<#assign fields=child.getFilterdPojoFields()>
	<#foreach field in fields>
${childdeclarationName}.${field.getName()}=<#if field.getComment()?exists>${field.getASCIIComment()}<#else>${field.getName()}</#if>
	</#foreach>
</#foreach>