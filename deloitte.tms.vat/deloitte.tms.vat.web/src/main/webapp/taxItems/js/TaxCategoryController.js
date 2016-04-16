
angular.module('LingApp').controller('TaxCategoryCtroller',
['$scope', '$http', '$timeout', '$interval', '$q','Ling',
function ($scope, $http, $timeout, $interval ,$q,Ling) {

    $scope.mainTaxCategory=true;
    $scope.addTaxCategory=false;
    $scope.editTaxCategory=false;
    
    /*Data---查询条件*/
    $scope.taxCategoryQuery = {
       pageIndex:1,
       pageSize:10,
       maxSize:5,
       recordCount:0
    };
    $scope.taxCategoryColumnDefs=[
			{ name:'categoryCode', lable:'税种编号' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'categoryName', lable:'税种名称' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'orgId', lable:'所属组织' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'categoryState', lable:'国地税' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'categoryType', lable:'税种编号' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'isUsed', lable:'是否启用' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'comments', lable:'税种编号' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'sDate', lable:'税种编号' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'eDate', lable:'税种编号' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'creationDate', lable:'税种编号' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'createdBy', lable:'创建人' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'createDate', lable:'创建日期/时间',type: 'date',cellFilter: 'date:"yyyy-MM-dd"' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'modifiedBy', lable:'修改人' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'modifiedDate', lable:'修改日期',type: 'date',cellFilter: 'date:"yyyy-MM-dd"' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'flag', lable:'是否有效' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'status', lable:'状态' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'dataowner', lable:'数据分区键' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'companyId', lable:'数据归属' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}}
	    ]
    $scope.taxCategoryItems=[];
    $scope.currentTaxCategory={};
    	 
	$scope.addTaxCategory = function() {
	 	$scope.mainTaxCategory=false;
	    $scope.addTaxCategory=true;
	    $scope.editTaxCategory=false;
	    $scope.currentTaxCategory={};
	};
	$scope.editTaxCategory=function(entity){
		$scope.mainTaxCategory=false;
	    $scope.addTaxCategory=false;
	    $scope.editTaxCategory=true;
	    angular.extend($scope.currentTaxCategory, entity);
	}
	$scope.resetTaxCategory=function(){
	}
	$scope.saveTaxCategory=function(entity){
	 Ling.post('taxCategory/saveTaxCategory.ljson',entity,function(result){
		MessageBox.success("操作成功！");
		$scope.searchTaxCategory(false);
      });
	}
	$scope.saveTaxCategoryAll=function(){
	}
    

    /**
     * 搜索
     * @param flag
     */
    $scope.searchTaxCategory = function (flag) {
        if (!flag) {
            $scope.pageIndex = 1
        }
        Ling.get('taxCategory/getTaxCategorys.ljson',$scope.taxCategoryQuery,function(result){
        	$scope.taxCategoryQuery.recordCount = result.recordCount;
	        $scope.taxCategoryItems = result.result;
	        $scope.taxCategorySelections=[];
	        $scope.currentTaxCategory={};
        })
    };
	/*Method---删除*/
    $scope.removeTaxCategory = function (taxCategory) {
        confirm("确定删除选择用户?",function(){
            Ling.post('taxCategory/removeTaxCategorys.ljson',{removeTaxCategorys:taxCategory.id},function(result){
            		$scope.searchTaxCategory();
            });
        })
    };
    /*Method---删除*/
    $scope.removeSelectTaxCategorys = function () {
        confirm("确定删除选择用户?",function(){
            var taxCategoryKeys = "";
            angular.forEach($scope.taxCategoryItems, function (data,index,array) {
            	if(data.selected){
                	taxCategoryKeys=join(taxCategoryKeys,data.id,",");
                }
            });
            if (taxCategoryKeys == "") {
                alert("请选择删除对象");
            } else {
            	Ling.post('taxCategory/removeTaxCategorys.ljson',{'taxCategoryKeys':taxCategoryKeys},function(result){
            		$scope.searchTaxCategory();
            	});
            }
        },function(){
        	
        })
    };
    $scope.searchTaxCategory();
}]);



