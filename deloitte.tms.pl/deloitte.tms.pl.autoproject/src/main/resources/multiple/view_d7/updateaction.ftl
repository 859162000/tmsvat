    <UpdateAction id="updateActionSave${declarationName}">
      <Property name="dataResolver">${pojoview}#save${declarationName}</Property>
      <Property name="executingMessage">数据提交中...</Property>
      <Property name="successMessage">数据提交成功!</Property>
      <UpdateItem>
        <Property name="dataSet">dataSet${declarationName}</Property>
      </UpdateItem>
    </UpdateAction>
<#foreach child in many>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
<#assign childpojoclassname = "${child.getPackageName()}.model.${childdeclarationName}">    
<#assign childpropertyname = "${child.getDeclarationNameFirstLetterLower()}s"> 
    <UpdateAction id="updateActionSave${childdeclarationName}">
      <Property name="dataResolver">${pojoview}#save${childdeclarationName}</Property>
      <Property name="executingMessage">数据提交中...</Property>
      <Property name="successMessage">数据提交成功!</Property>
      <UpdateItem>
        <Property name="dataSet">dataSet${declarationName}</Property>
        <Property name="dataPath">#.rel${childpropertyname}[#dirty]</Property>
      </UpdateItem>
    </UpdateAction>
</#foreach>
<#foreach child in ones>
<#assign childdeclarationName = child.importType(child.getDeclarationName())>
<#assign childpojoclassname = "${child.getPackageName()}.model.${childdeclarationName}">    
<#assign childpropertyname = "${child.getDeclarationNameFirstLetterLower()}"> 
    <UpdateAction id="updateActionSave${childdeclarationName}">
      <Property name="dataResolver">${pojoview}#save${childdeclarationName}</Property>
      <Property name="executingMessage">数据提交中...</Property>
      <Property name="successMessage">数据提交成功!</Property>
      <UpdateItem>
        <Property name="dataSet">dataSet${declarationName}</Property>
        <Property name="dataPath">#.rel${childpropertyname}[#current]</Property>
      </UpdateItem>
    </UpdateAction>
</#foreach>