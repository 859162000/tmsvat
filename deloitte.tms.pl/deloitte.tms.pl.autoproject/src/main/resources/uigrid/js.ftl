<#assign pojo=oneToManyPojo.getOne()>
<#assign many=oneToManyPojo.getMany()>
<#assign ones=oneToManyPojo.getOnes()>
<#assign fields=pojo.getPojoFields()>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign declarationNameFirstLetterLower = pojo.importType(pojo.getDeclarationNameFirstLetterLower())>

angular.module('LingApp').controller('${declarationName}Ctroller',
['$scope', '$http', '$timeout', '$interval', 'uiGridConstants', 'uiGridGroupingConstants','$q','Ling',
function ($scope, $http, $timeout, $interval, uiGridConstants, uiGridGroupingConstants,$q,Ling) {

    $scope.main=true;
    $scope.add=false;
    $scope.edit=false;
    
    $scope.${declarationNameFirstLetterLower}Selections=[];
    $scope.current${declarationName}={};
    var ${declarationNameFirstLetterLower}paginationOptions = {
    	pageNumber: 1,
    	pageSize: 25,
    	sort: null
    };
    
    /*Data---查询条件*/
    $scope.${declarationNameFirstLetterLower}Query = {
        //searchWord:''
    };
    	 
    $scope.${declarationNameFirstLetterLower}Grid = {
    	paginationPageSizes: [25, 50, 75],
    	paginationPageSize: 25,
    	useExternalPagination: true,
    	useExternalSorting: true,
    	    
    	enableRowSelection: true,
    	enableSelectAll: true,
	    //selectionRowHeaderWidth: 35,
	    //rowHeight: 35,
	    showGridFooter:true,
	    columnDefs: [
<#foreach field in fields>
<#if field.getIsSampleField()&&field.getIsNotBaseEntiry()>
	<#if field.getName()=="id">
			{ name:'${field.getName()}', width:120,displayName:'<#if field.getComment()?exists>${field.getComment()}<#else>${field.getName()}</#if>', enableCellEdit: false },
	<#else>
			{ name:'${field.getName()}', width:120,displayName:'<#if field.getComment()?exists>${field.getComment()}<#else>${field.getName()}</#if>'<#if field.getHtmlType()?exists>,type: '${field.getHtmlType()}'<#if field.getHtmlType()=='date'>,cellFilter: 'date:"yyyy-MM-dd"'<#else></#if><#else></#if> ,enableCellEdit: true },
	</#if>
</#if>                             
</#foreach>         
	    ],
	    onRegisterApi: function(gridApi) {
	      $scope.gridApi = gridApi;
	      gridApi.rowEdit.on.saveRow($scope, $scope.saveRow);
	      $scope.gridApi.core.on.sortChanged($scope, function(grid, sortColumns) {
	        if (sortColumns.length == 0) {
	          ${declarationNameFirstLetterLower}paginationOptions.sort = null;
	        } else {
	          ${declarationNameFirstLetterLower}paginationOptions.sort = sortColumns[0].sort.direction;
	        }
	        $scope.search${declarationName}();
	      });
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	        ${declarationNameFirstLetterLower}paginationOptions.pageNumber = newPage;
	        ${declarationNameFirstLetterLower}paginationOptions.pageSize = pageSize;
	        $scope.search${declarationName}();
	      });
	      gridApi.selection.on.rowSelectionChanged($scope,function(row){
	         $scope.${declarationNameFirstLetterLower}Selections = gridApi.selection.getSelectedRows();
	         $scope.current${declarationName}=row.entity;
	      });	   
	      gridApi.selection.on.rowSelectionChangedBatch($scope,function(rows){
	    	 $scope.${declarationNameFirstLetterLower}Selections = gridApi.selection.getSelectedRows();
	      });
	    }
	  };
	  
	$scope.add${declarationName} = function() {
	   var n = $scope.${declarationNameFirstLetterLower}Grid.data.length + 1;
	   $scope.${declarationNameFirstLetterLower}Grid.data.push({
	        "firstName": "New " + n,
	        "lastName": "Person " + n,
	        "company": "abc",
	        "employed": true,
	        "gender": "male"
		});
	};
	$scope.edit${declarationName}=function(){
	}
	$scope.removeSelect${declarationName}=function(){
	}
	$scope.reset${declarationName}=function(){
	}
	$scope.save${declarationName}=function(){
	}
	$scope.saveRow = function(rowEntity) {
      // create a fake promise - normally you'd use the promise returned by $http or $resource
      var promise = $q.defer();
      $scope.gridApi.rowEdit.setSavePromise( rowEntity, promise.promise );
   		/*if (rowEntity.gender === 'male' ){
          promise.reject();
        } else {
          promise.resolve();
        };*/
      Ling.post('${declarationNameFirstLetterLower}/save${declarationName}.ljson',rowEntity,function(result){
		MessageBox.success("操作成功！");
		$scope.search${declarationName}(false);
      });
    };
    

    /**
     * 搜索
     * @param flag
     */
    $scope.search${declarationName} = function (flag) {
        if (!flag) {
            $scope.pageIndex = 1
        }
        $scope.${declarationNameFirstLetterLower}Query.pageIndex = ${declarationNameFirstLetterLower}paginationOptions.pageNumber;
        $scope.${declarationNameFirstLetterLower}Query.pageSize = ${declarationNameFirstLetterLower}paginationOptions.pageSize;
        Ling.get('${declarationNameFirstLetterLower}/get${declarationName}s.ljson',$scope.${declarationNameFirstLetterLower}Query,function(result){
        	$scope.${declarationNameFirstLetterLower}Grid.totalItems = result.recordCount;
	        $scope.${declarationNameFirstLetterLower}Grid.data = result.result;
	        $scope.${declarationNameFirstLetterLower}Selections=[];
	        $scope.current${declarationName}={};
        })
    };
	/*Method---删除*/
    $scope.remove${declarationName} = function (${declarationNameFirstLetterLower}) {
        confirm("确定删除选择用户?",function(){
            Ling.post('${declarationNameFirstLetterLower}/remove${declarationName}s.ljson',{remove${declarationName}s:${declarationNameFirstLetterLower}.id},function(result){
            		$scope.search${declarationName}();
            });
        })
    };
    /*Method---删除*/
    $scope.removeSelect${declarationName}s = function () {
        confirm("确定删除选择用户?",function(){
            var ${declarationNameFirstLetterLower}Keys = "";
            angular.forEach($scope.${declarationNameFirstLetterLower}Selections, function (data,index,array) {
                ${declarationNameFirstLetterLower}Keys=join(${declarationNameFirstLetterLower}Keys,data.id,",");
            });
            if (${declarationNameFirstLetterLower}Keys == "") {
                alert("请选择删除对象");
            } else {
            	Ling.post('${declarationNameFirstLetterLower}/remove${declarationName}s.ljson',{'${declarationNameFirstLetterLower}Keys':${declarationNameFirstLetterLower}Keys},function(result){
            		$scope.search${declarationName}();
            	});
            }
        },function(){
        	
        })
    };
    $scope.search${declarationName}();
}]);



