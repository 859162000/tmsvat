<#assign pojo=oneToManyPojo.getOne()>
<#assign many=oneToManyPojo.getMany()>
<#assign ones=oneToManyPojo.getOnes()>
<#assign pojoview=pojo.getDeclarationNameFirstLetterLower()+"View">
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign pojoclassname = "${pojo.getPackageName()}.model.${declarationName}">
<#assign realPackageAndClassPath = "${pojo.getRealPackageAndClassPath()}">
<#assign masterid = pojo.getDeclarationNameFirstLetterLower()+"Id">
<#assign fields=pojo.getPojoFields()>
<?xml version="1.0" encoding="UTF-8"?>
<ViewConfig>
  <Property name="metaData">
    <Property name="securityTag">${declarationName}.manager</Property>
  </Property>
<#include "view_d7/Model.ftl"/>
  <View>
<#include "view_d7/dataset_query.ftl"/>
<#include "view_d7/updateaction_query.ftl"/>
    <Container>
      <FieldSet>
        <Property name="caption">查询条件</Property>
        <Buttons/>
        <Children>
<#include "view_d7/main/main_condition.ftl"/>
        </Children>
      </FieldSet>
      <FieldSet>
        <Property name="caption">可用操作</Property>
        <Buttons/>
        <Children>
<#include "view_d7/main/main_toolbar.ftl"/>
        </Children>
      </FieldSet>
      <FieldSet>
        <Property name="caption"><#if pojo.getComment()?exists>${pojo.getComment()}</#if></Property>
        <Buttons/>
        <Children>
<#include "view_d7/main/main_grid.ftl"/>
        </Children>
      </FieldSet>
    </Container>
<#include "view_d7/main/main_dialog_query.ftl"/> 
<#if pojo.getIsIntro()>
<#include "view_d7/main/main_intro.ftl"/>   
</#if>
<#include "view_d7/dropdown.ftl"/>  
  </View>
</ViewConfig>
