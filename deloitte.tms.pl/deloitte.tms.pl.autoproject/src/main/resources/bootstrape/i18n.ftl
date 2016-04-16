<#assign pojo=oneToManyPojo.getOne()>
<#assign many=oneToManyPojo.getMany()>
<#assign ones=oneToManyPojo.getOnes()>
<#assign fields=pojo.getFilterdPojoFields()>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign declarationNameFirstLetterLower = pojo.importType(pojo.getDeclarationNameFirstLetterLower())>
<#list fields as field>
${declarationName}.${field.getName()}=${field.getName()}
</#list> 

<#foreach child in many>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>	
<#assign fields=child.getFilterdPojoFields()>
	<#foreach field in fields>
${childdeclarationName}.${field.getName()}=${field.getName()}
	</#foreach>
</#foreach>

<#foreach child in ones>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
<#assign fields=child.getFilterdPojoFields()>
	<#foreach field in fields>
${childdeclarationName}.${field.getName()}=${field.getName()}
	</#foreach>
</#foreach>