<#foreach child in ones>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
<#assign childpojoclassname = "${child.getPackageName()}.model.${childdeclarationName}">  
<#assign childpropertyname = "${child.getDeclarationNameFirstLetterLower()}">      
<#assign fields=child.getPojoFields()>    
</#foreach>