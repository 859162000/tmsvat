
angular.module('LingApp').controller('InvoiceAbolishCtroller',
['$scope', '$http', '$timeout', '$interval', '$q','Ling',
function ($scope, $http, $timeout, $interval ,$q,Ling) {

    $scope.mainInvoiceAbolish=true;
    $scope.addInvoiceAbolish=false;
    $scope.editInvoiceAbolish=false;
    
    /*Data---查询条件*/
    $scope.invoiceAbolishQuery = {
       pageIndex:1,
       pageSize:10,
       maxSize:5,
       recordCount:0
    };
    $scope.invoiceAbolishColumnDefs=[
			{ name:'outtaxPrintHid', lable:'打印单ID' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'storeId', lable:'发票ID' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'abolishType', lable:'作废类型' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'abolishDate', lable:'作废时间' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'sDate', lable:'开始时间' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'eDate', lable:'结束时间' ,enableCellEdit: true
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
    $scope.invoiceAbolishItems=[];
    $scope.currentInvoiceAbolish={};
    	 
	$scope.addInvoiceAbolish = function() {
	 	$scope.mainInvoiceAbolish=false;
	    $scope.addInvoiceAbolish=true;
	    $scope.editInvoiceAbolish=false;
	    $scope.currentInvoiceAbolish={};
	};
	$scope.editInvoiceAbolish=function(entity){
		$scope.mainInvoiceAbolish=false;
	    $scope.addInvoiceAbolish=false;
	    $scope.editInvoiceAbolish=true;
	    angular.extend($scope.currentInvoiceAbolish, entity);
	}
	$scope.resetInvoiceAbolish=function(){
	}
	$scope.saveInvoiceAbolish=function(entity){
	 Ling.post('invoiceAbolish/saveInvoiceAbolish.ljson',entity,function(result){
		MessageBox.success("操作成功！");
		$scope.searchInvoiceAbolish(false);
      });
	}
	$scope.saveInvoiceAbolishAll=function(){
	}
    

    /**
     * 搜索
     * @param flag
     */
    $scope.searchInvoiceAbolish = function (flag) {
        if (!flag) {
            $scope.pageIndex = 1
        }
        Ling.get('invoiceAbolish/getInvoiceAbolishs.ljson',$scope.invoiceAbolishQuery,function(result){
        	$scope.invoiceAbolishQuery.recordCount = result.recordCount;
	        $scope.invoiceAbolishItems = result.result;
	        $scope.invoiceAbolishSelections=[];
	        $scope.currentInvoiceAbolish={};
        })
    };
	/*Method---删除*/
    $scope.removeInvoiceAbolish = function (invoiceAbolish) {
        confirm("确定删除选择用户?",function(){
            Ling.post('invoiceAbolish/removeInvoiceAbolishs.ljson',{removeInvoiceAbolishs:invoiceAbolish.id},function(result){
            		$scope.searchInvoiceAbolish();
            });
        })
    };
    /*Method---删除*/
    $scope.removeSelectInvoiceAbolishs = function () {
        confirm("确定删除选择用户?",function(){
            var invoiceAbolishKeys = "";
            angular.forEach($scope.invoiceAbolishItems, function (data,index,array) {
            	if(data.selected){
                	invoiceAbolishKeys=join(invoiceAbolishKeys,data.id,",");
                }
            });
            if (invoiceAbolishKeys == "") {
                alert("请选择删除对象");
            } else {
            	Ling.post('invoiceAbolish/removeInvoiceAbolishs.ljson',{'invoiceAbolishKeys':invoiceAbolishKeys},function(result){
            		$scope.searchInvoiceAbolish();
            	});
            }
        },function(){
        	
        })
    };
    $scope.searchInvoiceAbolish();
}]);



