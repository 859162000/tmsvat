<#assign pojo=oneToManyPojo.getOne()>
<#assign many=oneToManyPojo.getMany()>
<#assign ones=oneToManyPojo.getOnes()>
<#assign pojoview=pojo.getDeclarationNameFirstLetterLower()+"View">
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign pojoclassname = "${pojo.getPackageName()}.model.${declarationName}">
<#assign realPackageAndClassPath = "${pojo.getRealPackageAndClassPath()}">
<#assign masterid = pojo.getDeclarationNameFirstLetterLower()+"Id">
	<bean parent="dorado.assembledComponentTypeRegister">
		<property name="name" value="${declarationName}SingleSelect" />
		<property name="category" value="ling.iris" />
		<property name="src" value="${pojo.getPackageName()}.view.component.${declarationName}SingleSelect#DropDown${declarationName}SingleSelect" />
		<d:virtual-property name="dataProvider" avialableAt="both" />
		<d:virtual-property name="parameter" avialableAt="both" />
	</bean>
	
	public static Iris${declarationName}Node getTopIrisAreaNode() {
//		applicationCache.putCacheObject(IrisProductService.BUSINESSFLOW_CACHE_ID,null);
		ApplicationCache applicationCache=getApplicationCache();
		IrisProvider businessFlowProvider=SpringUtil.getBean(IrisProductProvider.BEAN_ID);
		Iris${declarationName}Node top${declarationName}=(Iris${declarationName}Node) getApplicationCache().getCacheObject(${declarationName}Service.${declarationName?upper_case}_CACHE_ID);
		if(top${declarationName}==null){
			top${declarationName}=(Iris${declarationName}Node) businessFlowProvider.build(null);
			applicationCache.putCacheObject(${declarationName}Service.${declarationName?upper_case}_CACHE_ID,top${declarationName});
		}
		return top${declarationName};
	}
	
  <DataType name="$dataType${declarationName}Select" parent="$dataTypeIrisDefineBase">
    <Property name="creationType">${realPackageAndClassPath}Param</Property>
    <PropertyDef name="id">
      <Property name="dataType">String</Property>
      <Property name="label">ID</Property>
    </PropertyDef>
    <PropertyDef name="desc">
      <Property name="dataType">String</Property>
    </PropertyDef>
    <PropertyDef name="tag">
      <Property></Property>
    </PropertyDef>
    <PropertyDef name="virtual">
      <Property name="dataType">Boolean</Property>
    </PropertyDef>
    <PropertyDef name="parentName">
      <Property></Property>
    </PropertyDef>
    <PropertyDef name="checked">
      <Property name="dataType">boolean</Property>
    </PropertyDef>
    <PropertyDef name="name">
      <Property></Property>
      <Property name="required">true</Property>
    </PropertyDef>
    <PropertyDef name="code">
      <Property></Property>
      <Property name="required">true</Property>
    </PropertyDef>
    <PropertyDef name="parentId">
      <Property></Property>
    </PropertyDef>
    <PropertyDef name="dataType"/>
    <PropertyDef name="hot">
      <Property name="dataType">boolean</Property>
    </PropertyDef>
    <PropertyDef name="rel${declarationName}s">
      <Property name="dataType">[SELF]</Property>
    </PropertyDef>
  </DataType>