
angular.module('LingApp').controller('OrderCtroller',
['$scope', '$http', '$timeout', '$interval', '$q','Ling',
function ($scope, $http, $timeout, $interval ,$q,Ling) {

    $scope.mainOrder=true;
    $scope.addOrder=false;
    $scope.editOrder=false;
    
    /*Data---查询条件*/
    $scope.orderQuery = {
       pageIndex:1,
       pageSize:10,
       maxSize:5,
       recordCount:0
    };
    $scope.orderColumnDefs=[
			{ name:'relationUser', lable:'关联用户' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'nickName', lable:'昵称' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'ordercode', lable:'订单编号' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'totalPrice', lable:'总价',type: 'number' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'expressPrice', lable:'快递费用',type: 'number' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'payPrice', lable:'支付价',type: 'number' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'totalNum', lable:'件数',type: 'number' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'name', lable:'名称' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'showPic', lable:'展示图片' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'showPicUrl', lable:'showPicUrl' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'reciverName', lable:'联系人' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'phone', lable:'联系电话' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'region', lable:'区域' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'address', lable:'详细地址' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'zipCode', lable:'邮编' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'orderDate', lable:'下单日期',type: 'date',cellFilter: 'date:"yyyy-MM-dd"' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'expressType', lable:'快递方式' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'expressCode', lable:'快递单号' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'payType', lable:'支付方式' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'payCode', lable:'支付编号' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'userRemark', lable:'用户备注' ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}},
			{ name:'managerRemark', lable:'后台备注' ,enableCellEdit: true
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
    $scope.orderItems=[];
    $scope.currentOrder={};
    	 
	$scope.addOrder = function() {
	 	$scope.mainOrder=false;
	    $scope.addOrder=true;
	    $scope.editOrder=false;
	    $scope.currentOrder={};
	};
	$scope.editOrder=function(entity){
		$scope.mainOrder=false;
	    $scope.addOrder=false;
	    $scope.editOrder=true;
	    angular.extend($scope.currentOrder, entity);
	}
	$scope.resetOrder=function(){
	}
	$scope.saveOrder=function(entity){
	 Ling.post('order/saveOrder.ljson',entity,function(result){
		MessageBox.success("操作成功！");
		$scope.searchOrder(false);
      });
	}
	$scope.saveOrderAll=function(){
	}
    

    /**
     * 搜索
     * @param flag
     */
    $scope.searchOrder = function (flag) {
        if (!flag) {
            $scope.pageIndex = 1
        }
        Ling.get('order/getOrders.ljson',$scope.orderQuery,function(result){
        	$scope.orderQuery.recordCount = result.recordCount;
	        $scope.orderItems = result.result;
	        $scope.orderSelections=[];
	        $scope.currentOrder={};
        })
    };
	/*Method---删除*/
    $scope.removeOrder = function (order) {
        confirm("确定删除选择用户?",function(){
            Ling.post('order/removeOrders.ljson',{removeOrders:order.id},function(result){
            		$scope.searchOrder();
            });
        })
    };
    /*Method---删除*/
    $scope.removeSelectOrders = function () {
        confirm("确定删除选择用户?",function(){
            var orderKeys = "";
            angular.forEach($scope.orderItems, function (data,index,array) {
            	if(data.selected){
                	orderKeys=join(orderKeys,data.id,",");
                }
            });
            if (orderKeys == "") {
                alert("请选择删除对象");
            } else {
            	Ling.post('order/removeOrders.ljson',{'orderKeys':orderKeys},function(result){
            		$scope.searchOrder();
            	});
            }
        },function(){
        	
        })
    };
    $scope.searchOrder();
}]);



