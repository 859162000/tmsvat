
angular.module('LingApp').controller('ItemsCtroller',
['$scope', '$http', '$timeout', '$interval', '$q','Ling',
function ($scope, $http, $timeout, $interval ,$q,Ling) {

    $scope.mainItems=true;
    $scope.addItems=false;
    $scope.editItems=false;
    
    /*Data---查询条件*/
    $scope.itemsQuery = {
       pageIndex:1,
       pageSize:10,
       maxSize:5,
       recordCount:0
    };
    $scope.itemsColumnDefs=[
			{ name:'categoryId', lable:'税种ID' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'itemCode', lable:'税目代码' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'ordercode', lable:'税目名称' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'orgId', lable:'所属组织' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'itemTaxRules', lable:'计税规则' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'isUsed', lable:'Y 启用 N 不启用' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'comments', lable:'备注' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'creationDate', lable:'创建日期',type: 'date',cellFilter: 'date:"yyyy-MM-dd"' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'createdBy', lable:'创建者' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'adaptIndustryId', lable:'适用行业' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'sDate', lable:'开始日期',type: 'date',cellFilter: 'date:"yyyy-MM-dd"' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'eDate', lable:'结束日期',type: 'date',cellFilter: 'date:"yyyy-MM-dd"' ,enableCellEdit: true
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
    $scope.itemsItems=[];
    $scope.currentItems={};
    	 
	$scope.addItems = function() {
	 	$scope.mainItems=false;
	    $scope.addItems=true;
	    $scope.editItems=false;
	    $scope.currentItems={};
	};
	$scope.editItems=function(entity){
		$scope.mainItems=false;
	    $scope.addItems=false;
	    $scope.editItems=true;
	    angular.extend($scope.currentItems, entity);
	}
	$scope.resetItems=function(){
	}
	$scope.saveItems=function(entity){
	 Ling.post('items/saveItems.ljson',entity,function(result){
		MessageBox.success("操作成功！");
		$scope.searchItems(false);
      });
	}
	$scope.saveItemsAll=function(){
	}
    

    /**
     * 搜索
     * @param flag
     */
    $scope.searchItems = function (flag) {
        if (!flag) {
            $scope.pageIndex = 1
        }
        Ling.get('items/getItemss.ljson',$scope.itemsQuery,function(result){
        	$scope.itemsQuery.recordCount = result.recordCount;
	        $scope.itemsItems = result.result;
	        $scope.itemsSelections=[];
	        $scope.currentItems={};
        })
    };
	/*Method---删除*/
    $scope.removeItems = function (items) {
        confirm("确定删除选择用户?",function(){
            Ling.post('items/removeItemss.ljson',{removeItemss:items.id},function(result){
            		$scope.searchItems();
            });
        })
    };
    /*Method---删除*/
    $scope.removeSelectItemss = function () {
        confirm("确定删除选择用户?",function(){
            var itemsKeys = "";
            angular.forEach($scope.itemsItems, function (data,index,array) {
            	if(data.selected){
                	itemsKeys=join(itemsKeys,data.id,",");
                }
            });
            if (itemsKeys == "") {
                alert("请选择删除对象");
            } else {
            	Ling.post('items/removeItemss.ljson',{'itemsKeys':itemsKeys},function(result){
            		$scope.searchItems();
            	});
            }
        },function(){
        	
        })
    };
    $scope.searchItems();
}]);



