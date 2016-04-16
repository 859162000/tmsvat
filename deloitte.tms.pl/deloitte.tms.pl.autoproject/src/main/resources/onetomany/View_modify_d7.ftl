<#assign pojo=oneToManyPojo.getOne()>
<#assign many=oneToManyPojo.getMany()>
<#assign ones=oneToManyPojo.getOnes()>
<#assign pojoview=pojo.getDeclarationNameFirstLetterLower()+"View">
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign pojoclassname = "${pojo.getPackageName()}.${pojo.getClassName()}">
<#assign realPackageAndClassPath = "${pojo.getRealPackageAndClassPath()}">
<#assign masterid = pojo.getDeclarationNameFirstLetterLower()+"Id">
<?xml version="1.0" encoding="UTF-8"?>
<ViewConfig>
  <Property name="metaData">
    <Property name="securityTag">${declarationName}.manager</Property>
  </Property>
<#include "view_d7/Model.ftl"/>
  <View>
<#include "view_d7/dataset_modify.ftl"/>
<#include "view_d7/updateaction_modify.ftl"/>
    <TabControl id="tabControl${declarationName}">
      <ControlTab>
      	<Property name="name">main</Property>
        <Property name="caption"><#if pojo.getComment()?exists>${pojo.getComment()}</#if></Property>
        <Panel>
          <Buttons/>
          <Children>
<#assign fields=pojo.getPojoFields()>
<#include "view_d7/main/main_toolbar_add.ftl"/>
<#include "view_d7/main/main_add.ftl"/>        
          </Children>
          <Tools/>
        </Panel>
      </ControlTab>          
<#include "view_d7/manys.ftl"/>
<#include "view_d7/ones.ftl"/>       
    </TabControl>
<#if pojo.getIsIntro()>
<#include "view_d7/main/main_intro.ftl"/>   
</#if>
<#include "view_d7/dropdown.ftl"/>  
  </View>
</ViewConfig>
