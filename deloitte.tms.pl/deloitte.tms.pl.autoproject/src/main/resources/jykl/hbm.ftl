<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign fields=pojo.getPojoFields()>
<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN" "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
<hibernate-mapping auto-import="true">
  <class name="${pojo.getPackageName()}.model.${declarationName}" table="TBL_${declarationName}">
    <id name="id">
      <generator class="sequence">
        <param name="sequence">SEQ_${declarationName}</param>
      </generator>
    </id>
<#foreach field in fields>
<#if field.getIsSampleField()&&${field.getName()!="id"}>
        <property name="${field.getName()}" column="${field.getName()}" />
</#if>
</#foreach>	
    
    <property name="firstInsert" not-null="false">
      <column name="FIRSTINSERT"></column>
    </property>
    <property name="lastModified" not-null="false">
      <column name="LASTMODIFIED"></column>
    </property>
    <property name="updateMan" not-null="false">
      <column name="UPDATEMAN"></column>
    </property>
    <property name="insertMan" not-null="false">
      <column name="INSERTMAN"></column>
    </property>    
  </class>
  
  
</hibernate-mapping>