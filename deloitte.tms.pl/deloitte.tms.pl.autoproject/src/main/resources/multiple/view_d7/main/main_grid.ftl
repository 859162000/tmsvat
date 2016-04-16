            <DataGrid id="dataGrid${declarationName}">
              <ClientEvent name="onDoubleClick">this.edit${declarationName}();</ClientEvent>
              <Property name="dataSet">dataSet${declarationName}</Property>
              <Property name="readOnly"><#if pojo.getEditType().toString()=="grid">false<#else>true</#if></Property>
              <Property name="showFilterBar">false</Property>
              <Property name="filterMode">serverSide</Property>
		      <Property name="selectionMode">multiRows</Property>
		      <RowSelectorColumn/>
<#foreach field in fields>
<#if field.getIsSampleField()&&field.getIsNotBaseEntiry()>
              <DataColumn>
                <Property name="name">${field.getName()}</Property>
                <Property name="property">${field.getName()}</Property>
                <#if pojo.getEditType().toString()=="grid"&&field.getName()=="id"><Property name="readOnly">true</Property><#else></#if>
                <#if field.getDataType()=="Date"><Property name="trigger">autoDateDropDown</Property><#else></#if>
                <#if field.getDropdown()?exists><#assign dropdown=field.getDropdown()><Property name="trigger">dropDown${dropdown.relClass()}</Property></#if>
              </DataColumn>
</#if>              
</#foreach>
            </DataGrid>