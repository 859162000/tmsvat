                <DataGrid id="dataGrid${childdeclarationName}">
                  <Property name="dataSet">dataSet${declarationName}</Property>
                  <Property name="dataPath">#.rel${childpropertyname}</Property>
                  <Property name="readOnly"><#if child.getEditType().toString()=="grid">false<#else>true</#if></Property>
                  <Property name="filterMode">serverSide</Property>
                  <Property name="showFilterBar">false</Property>
                  <Property name="selectionMode">multiRows</Property>
                  <Property name="highlightSelectedRow">false</Property>
                  <RowSelectorColumn/>
<#assign fields=child.getPojoFields()>
<#foreach field in fields>
<#if field.getIsSampleField()&&field.getIsNotBaseEntiry()>
                  <DataColumn>
                    <Property name="name">${field.getName()}</Property>
                    <Property name="property">${field.getName()}</Property>
                    <#if child.getEditType().toString()=="grid"&&field.getName()=="id"><Property name="readOnly">true</Property><#else></#if>
                	<#if field.getDataType()=="Date"><Property name="trigger">autoDateDropDown</Property><#else></#if>
                	<#if field.getDropdown()?exists><#assign dropdown=field.getDropdown()><Property name="trigger">dropDown${dropdown.relClass()}</Property></#if>
                  </DataColumn>
</#if>                   
</#foreach>      
                </DataGrid>