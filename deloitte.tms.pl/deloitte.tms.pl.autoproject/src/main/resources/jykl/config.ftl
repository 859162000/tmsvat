<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign fields=pojo.getPojoFields()>
	<bean id="${pojo.getDeclarationNameFirstLetterLower()}Import"
		class="com.bocommlife.channel.service.importation.ImportFileImpl">
		<property name="assemble" ref="excelAssembleRelation" />
		<property name="relation" ref="xmlRelationMap" />
		<property name="resolver" ref="resolver" />
		<property name="xmlFilePath">
			<value>classpath*:imports/${pojo.getDeclarationNameFirstLetterLower()}Import.xml</value>
		</property>
	</bean>