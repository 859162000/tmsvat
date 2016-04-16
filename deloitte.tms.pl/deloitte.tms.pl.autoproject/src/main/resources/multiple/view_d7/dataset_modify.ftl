    <DataSet id="dataSet${declarationName}">
      <Property name="dataType">dataType${declarationName}InParam</Property>
      <Property name="dataProvider">${pojoview}#loadModify${declarationName}</Property>
      <Property name="loadMode">lazy</Property>
      <Property name="parameter">
        <Entity>
          <Property name="id">${r"${param[&quot;id&quot;]}"}</Property>
        </Entity>
      </Property>
    </DataSet>