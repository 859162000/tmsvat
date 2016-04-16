<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign fields=pojo.getPojoFields()>
<?xml version="1.0" encoding="UTF-8"?>

<Map>
	<Objects>
<#foreach field in fields>
<#if field.getIsSampleField()>
        <Object class="${pojo.getPackageName()}.model.${declarationName}"
			property="${field.getName()}" <#if field.getDataType()=="Long">type="java.lang.Long"</#if><#if field.getDataType()=="Double">type="java.lang.Double"</#if><#if field.getDataType()=="Date">type="java.util.Date" pattern="yyyy-MM-dd"</#if> name="${pojo.getDeclarationNameFirstLetterLower()}" />
</#if>
</#foreach>
	</Objects>

	<Relations>
<#foreach field in fields>
<#if field.getIsSampleField()>
       <Relation name="${pojo.getDeclarationNameFirstLetterLower()}" property="${field.getName()}" column="0" />
</#if>
</#foreach>	
	</Relations>

</Map>
