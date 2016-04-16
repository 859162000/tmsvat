
angular.module('LingApp').controller('InvoiceTrxHCtroller',
['$scope', '$http', '$timeout', '$interval', '$q','Ling',
function ($scope, $http, $timeout, $interval ,$q,Ling) {

    $scope.mainInvoiceTrxH=true;
    $scope.addInvoiceTrxH=false;
    $scope.editInvoiceTrxH=false;
    
    /*Data---查询条件*/
    $scope.invoiceTrxHQuery = {
       pageIndex:1,
       pageSize:10,
       maxSize:5,
       recordCount:0
    };
    $scope.invoiceTrxHColumnDefs=[
			{ name:'crvatInvoiceTrxNumber', lable:'请领单编码' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'description', lable:'请领单名称' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'invoiceTrxDate', lable:'请领单日期' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'legalEntityId', lable:'纳税主体' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'legalEntityCode', lable:'纳税人主体代码' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'legalEntityName', lable:'纳税人名称' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'departmentId', lable:'税务机关ID' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'departmentCode', lable:'税务机关代码_枚举值' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'departmentName', lable:'税务机关代码名称' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'equipmentId', lable:'终端设备ID' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'equipmentCode', lable:'终端设备编号' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'equipmentName', lable:'终端设备名称' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'approvalBy', lable:'审领人' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'approvalOrgId', lable:'审领部门' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'invoiceStatus', lable:'开票状态' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'orgId', lable:'组织ID' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'approvalStatus', lable:'审批流状态' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'wfTaskId', lable:'工作流ID' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'approvalDesc', lable:'审批流状态描述' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'startDate', lable:'有效日期' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'endDate', lable:'失效日期' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'createdBy', lable:'创建人' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'createDate', lable:'创建日期/时间',type: 'date',cellFilter: 'date:"yyyy-MM-dd"' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'modifiedBy', lable:'修改人' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'modifiedDate', lable:'修改日期',type: 'date',cellFilter: 'date:"yyyy-MM-dd"' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'status', lable:'状态' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'bizOrgCode', lable:'数据分区键' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'dataowner', lable:'数据分区键' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'companyId', lable:'数据归属' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'archiveBaseDate', lable:'归档基准日期',type: 'date',cellFilter: 'date:"yyyy-MM-dd"' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'versionId', lable:'记录版本号，每次修改记录时加1',type: 'number' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'flag', lable:'删除标记' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'deleteBy', lable:'删除人' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'deleteDate', lable:'删除时间',type: 'date',cellFilter: 'date:"yyyy-MM-dd"' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'attribute1', lable:'ATTRIBUTE1' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'attribute2', lable:'ATTRIBUTE2' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'attribute3', lable:'ATTRIBUTE3' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'attribute4', lable:'ATTRIBUTE4' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'attribute5', lable:'ATTRIBUTE5' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}}
	    ]
    $scope.invoiceTrxHItems=[];
    $scope.currentInvoiceTrxH={};
    	 
	$scope.addInvoiceTrxH = function() {
	 	$scope.mainInvoiceTrxH=false;
	    $scope.addInvoiceTrxH=true;
	    $scope.editInvoiceTrxH=false;
	    $scope.currentInvoiceTrxH={};
	};
	$scope.editInvoiceTrxH=function(entity){
		$scope.mainInvoiceTrxH=false;
	    $scope.addInvoiceTrxH=false;
	    $scope.editInvoiceTrxH=true;
	    angular.extend($scope.currentInvoiceTrxH, entity);
	}
	$scope.resetInvoiceTrxH=function(){
	}
	$scope.saveInvoiceTrxH=function(entity){
	 Ling.post('invoiceTrxH/saveInvoiceTrxH.ljson',entity,function(result){
		MessageBox.success("操作成功！");
		$scope.searchInvoiceTrxH(false);
      });
	}
	$scope.saveInvoiceTrxHAll=function(){
	}
    

    /**
     * 搜索
     * @param flag
     */
    $scope.searchInvoiceTrxH = function (flag) {
        if (!flag) {
            $scope.pageIndex = 1
        }
        Ling.get('invoiceTrxH/getInvoiceTrxHs.ljson',$scope.invoiceTrxHQuery,function(result){
        	$scope.invoiceTrxHQuery.recordCount = result.recordCount;
	        $scope.invoiceTrxHItems = result.result;
	        $scope.invoiceTrxHSelections=[];
	        $scope.currentInvoiceTrxH={};
        })
    };
	/*Method---删除*/
    $scope.removeInvoiceTrxH = function (invoiceTrxH) {
        confirm("确定删除选择用户?",function(){
            Ling.post('invoiceTrxH/removeInvoiceTrxHs.ljson',{removeInvoiceTrxHs:invoiceTrxH.id},function(result){
            		$scope.searchInvoiceTrxH();
            });
        })
    };
    /*Method---删除*/
    $scope.removeSelectInvoiceTrxHs = function () {
        confirm("确定删除选择用户?",function(){
            var invoiceTrxHKeys = "";
            angular.forEach($scope.invoiceTrxHItems, function (data,index,array) {
            	if(data.selected){
                	invoiceTrxHKeys=join(invoiceTrxHKeys,data.id,",");
                }
            });
            if (invoiceTrxHKeys == "") {
                alert("请选择删除对象");
            } else {
            	Ling.post('invoiceTrxH/removeInvoiceTrxHs.ljson',{'invoiceTrxHKeys':invoiceTrxHKeys},function(result){
            		$scope.searchInvoiceTrxH();
            	});
            }
        },function(){
        	
        })
    };
    $scope.searchInvoiceTrxH();
}]);



