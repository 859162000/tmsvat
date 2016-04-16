<#assign pojo=oneToManyPojo.getOne()>
<#assign many=oneToManyPojo.getMany()>
<#assign ones=oneToManyPojo.getOnes()>
<#assign pojoview=pojo.getDeclarationNameFirstLetterLower()+"View">
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign pojoclassname = "${pojo.getPackageName()}.model.${declarationName}">
<#assign realPackageAndClassPath = "${pojo.getRealPackageAndClassPath()}">
<#assign masterid = pojo.getDeclarationNameFirstLetterLower()+"Id">
<#assign fields=pojo.getPojoFields()>
<#foreach field in fields>
	<#if field.getIsSampleField()>
		<#if field.getComment()?exists>
\#dataType${declarationName}Param.#${field.getName()}=${field.getName()}
			<#else>
\#dataType${declarationName}Param.#${field.getName()}=${field.getName()}
		</#if>
	</#if>
</#foreach>
<#foreach field in fields>
	<#if field.getIsSampleField()>
		<#if field.getComment()?exists>
\#dataTypeQuery${declarationName}.#${field.getName()}=${field.getName()}
			<#else>
\#dataType${declarationName}Param.#${field.getName()}=${field.getName()}
		</#if>
	</#if>
</#foreach>
<#foreach child in many>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
<#assign childpojoclassname = "${child.getPackageName()}.model.${childdeclarationName}">
<#assign childrealPackageAndClassPath = "${child.getRealPackageAndClassPath()}">
<#assign fields=child.getPojoFields()>
	<#foreach field in fields>
		<#if field.getIsSampleField()>
			<#if field.getComment()?exists>
\#dataType${childdeclarationName}Param.#${field.getName()}=${field.getName()}
				<#else>
\#dataType${childdeclarationName}Param.#${field.getName()}=${field.getName()}
			</#if>
		</#if>      
	</#foreach>
</#foreach>
<#foreach child in ones>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
<#assign childpojoclassname = "${child.getPackageName()}.model.${childdeclarationName}">
<#assign childrealPackageAndClassPath = "${child.getRealPackageAndClassPath()}">
<#assign fields=child.getPojoFields()>
	<#foreach field in fields>
		<#if field.getIsSampleField()>
		     <#if field.getComment()?exists>
\#dataType${childdeclarationName}Param.#${field.getName()}=${field.getName()}
				<#else>
\#dataType${childdeclarationName}Param.#${field.getName()}=${field.getName()}
			</#if>
		</#if>      
	</#foreach>
</#foreach>