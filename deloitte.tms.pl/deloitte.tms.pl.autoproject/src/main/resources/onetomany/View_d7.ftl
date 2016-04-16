<#assign pojo=oneToManyPojo.getOne()>
<#assign many=oneToManyPojo.getMany()>
<#assign ones=oneToManyPojo.getOnes()>
<#assign pojoview=pojo.getDeclarationNameFirstLetterLower()+"View">
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign pojoclassname = "${pojo.getPackageName()}.model.${declarationName}">
<#assign realPackageAndClassPath = "${pojo.getRealPackageAndClassPath()}">
<#assign masterid = pojo.getDeclarationNameFirstLetterLower()+"Id">
<?xml version="1.0" encoding="UTF-8"?>
<ViewConfig>
  <Property name="metaData">
    <Property name="securityTag">${declarationName}.manager</Property>
  </Property>
<#include "view_d7/Model.ftl"/>
  <View>
<#include "view_d7/dataset.ftl"/>
<#include "view_d7/updateaction.ftl"/>
<#if many?size gt 0||ones?size gt 0>
    <SplitPanel>
      <Property name="position">200</Property>
      <Property name="direction">top</Property>
      <MainControl>
        <TabControl>
<#include "view_d7/manys.ftl"/>
<#include "view_d7/ones.ftl"/>       
        </TabControl>
      </MainControl>
      <SideControl>
        <Panel>
          <Property name="caption">主表数据维护</Property>
          <Children>
</#if>
<#assign fields=pojo.getPojoFields()>
<#include "view_d7/main/main_condition.ftl"/>
<#include "view_d7/main/main_toolbar.ftl"/>
<#include "view_d7/main/main_grid.ftl"/>
<#include "view_d7/main/main_dialog.ftl"/>
<#if many?size gt 0||ones?size gt 0>
          </Children>
        </Panel>
      </SideControl>
    </SplitPanel> 
</#if>
<#if pojo.getIsIntro()>
<#include "view_d7/main/main_intro.ftl"/>   
</#if>
<#include "view_d7/dropdown.ftl"/>  
  </View>
</ViewConfig>
