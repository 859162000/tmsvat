    <UpdateAction id="updateActionSaveAdd${declarationName}">
      <Property name="dataResolver">${pojoview}#updateModify${declarationName}</Property>
      <Property name="executingMessage">数据提交中...</Property>
      <Property name="successMessage">数据提交成功!</Property>
      <UpdateItem>
        <Property name="dataSet">dataSet${declarationName}</Property>
      </UpdateItem>
    </UpdateAction>