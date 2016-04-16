    <DataSet id="dataSet${declarationName}">
      <Property name="dataType">[dataType${declarationName}Param]</Property>
      <Property name="dataProvider">${pojoview}#load${declarationName}</Property>
      <Property name="pageSize">10</Property>
    </DataSet>
    <DataSet id="dataSetQuery${declarationName}">
      <Property name="dataType">dataTypeQuery${declarationName}</Property>
    </DataSet>