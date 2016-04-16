<#foreach child in many>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
<#assign childpojoclassname = "${child.getPackageName()}.model.${childdeclarationName}">  
<#assign childpropertyname = "${child.getDeclarationNameFirstLetterLower()}s">          
          <ControlTab>
            <Property name="caption"><#if child.getComment()?exists>${child.getComment()}</#if></Property>
            <Panel>
              <Children>              
	            <IFrame id="iFrame${childdeclarationName}">
	            </IFrame>
              </Children>
            </Panel>
          </ControlTab>
</#foreach>