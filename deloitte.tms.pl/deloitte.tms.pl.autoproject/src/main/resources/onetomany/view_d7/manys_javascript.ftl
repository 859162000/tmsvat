<#foreach child in many>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
<#assign childpojoclassname = "${child.getPackageName()}.model.${childdeclarationName}">  
<#assign childpropertyname = "${child.getDeclarationNameFirstLetterLower()}s">          
<#include "manys/maneys_javascript.ftl"/>
</#foreach>