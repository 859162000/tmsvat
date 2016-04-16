<#foreach child in ones>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
<#assign childpojoclassname = "${child.getPackageName()}.model.${childdeclarationName}">  
<#assign childpropertyname = "${child.getDeclarationNameFirstLetterLower()}">      
<#assign fields=child.getPojoFields()>    
          <ControlTab>
            <Property name="caption"><#if child.getComment()?exists>${child.getComment()}<#else></#if></Property>
            <Panel>
              <Children>
<#include "ones/ones_toolbar.ftl"/>
<#include "ones/ones_autoform.ftl"/>
              </Children>
            </Panel>
          </ControlTab>
</#foreach>