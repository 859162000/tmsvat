
angular.module('LingApp').controller('OrderCtroller', ['$scope', '$http', '$timeout', '$interval', 'uiGridConstants', 'uiGridGroupingConstants','$q',
                                                            function ($scope, $http, $timeout, $interval, uiGridConstants, uiGridGroupingConstants,$q) {

    $scope.main=true;
    $scope.add=false;
    $scope.edit=false;
    
    var orderpaginationOptions = {
    	pageNumber: 1,
    	pageSize: 25,
    	sort: null
    };
    
    /*Data---查询条件*/
    $scope.orderQuery = {
        //searchWord:''
    };
    	 
    $scope.ordergridOptions = {
    	paginationPageSizes: [25, 50, 75],
    	paginationPageSize: 25,
    	useExternalPagination: true,
    	useExternalSorting: true,
    	    
    	enableRowSelection: true,
    	enableSelectAll: true,
	    //selectionRowHeaderWidth: 35,
	    //rowHeight: 35,
	    showGridFooter:true,
	    enableFullRowSelection:true,
	    columnDefs: [
			{ name:'flag', displayName:'是否有效' ,enableCellEdit: true },
			{ name:'status', displayName:'状态' ,enableCellEdit: true },
			{ name:'relationUser', displayName:'关联用户' ,enableCellEdit: true },
			{ name:'nickName', displayName:'昵称' ,enableCellEdit: true },
			{ name:'ordercode', displayName:'订单编号' ,enableCellEdit: true },
			{ name:'totalPrice', displayName:'总价',type: 'number' ,enableCellEdit: true },
			{ name:'expressPrice', displayName:'快递费用',type: 'number' ,enableCellEdit: true },
			{ name:'payPrice', displayName:'支付价',type: 'number' ,enableCellEdit: true },
			{ name:'totalNum', displayName:'件数',type: 'number' ,enableCellEdit: true },
			{ name:'name', displayName:'名称' ,enableCellEdit: true },
			{ name:'showPic', displayName:'展示图片' ,enableCellEdit: true },
			{ name:'showPicUrl', displayName:'showPicUrl' ,enableCellEdit: true },
			{ name:'reciverName', displayName:'联系人' ,enableCellEdit: true },
			{ name:'phone', displayName:'联系电话' ,enableCellEdit: true },
			{ name:'region', displayName:'区域' ,enableCellEdit: true },
			{ name:'address', displayName:'详细地址' ,enableCellEdit: true },
			{ name:'zipCode', displayName:'邮编' ,enableCellEdit: true },
			{ name:'orderDate', displayName:'下单日期',type: 'date',cellFilter: 'date:"yyyy-MM-dd"' ,enableCellEdit: true },
			{ name:'expressType', displayName:'快递方式' ,enableCellEdit: true },
			{ name:'expressCode', displayName:'快递单号' ,enableCellEdit: true },
			{ name:'payType', displayName:'支付方式' ,enableCellEdit: true },
			{ name:'payCode', displayName:'支付编号' ,enableCellEdit: true },
			{ name:'userRemark', displayName:'用户备注' ,enableCellEdit: true },
			{ name:'managerRemark', displayName:'后台备注' ,enableCellEdit: true },
			{ name:'firstinsert', displayName:'firstinsert',type: 'date',cellFilter: 'date:"yyyy-MM-dd"' ,enableCellEdit: true },
			{ name:'lastmodified', displayName:'lastmodified',type: 'date',cellFilter: 'date:"yyyy-MM-dd"' ,enableCellEdit: true },
			{ name:'insertman', displayName:'insertman' ,enableCellEdit: true },
			{ name:'updateman', displayName:'updateman' ,enableCellEdit: true },
			{ name:'dataowner', displayName:'dataowner' ,enableCellEdit: true },
			{ name:'dataStatu', displayName:'dataStatu' ,enableCellEdit: true },
			{ name:'company', displayName:'company' ,enableCellEdit: true },
	      	/*{ name: 'username', enableCellEdit: false },
		    { name: 'name', displayName: 'Name (editable)' },
		    { name: 'gender' },
		    { name: 'age', displayName: 'Age' , type: 'number'},
		    { name: 'registered', displayName: 'Registered' , type: 'date', cellFilter: 'date:"yyyy-MM-dd"'},
		    { name: 'address', displayName: 'Address', type: 'object'},
		    { name: 'isActive', displayName: 'Active', type: 'boolean'}/*{ name: 'address.city', displayName: 'Address (even rows editable)',
		         cellEditableCondition: function($scope){
		         return $scope.rowRenderIndex%2
		    	}
		    }*/
	    ],
	    onRegisterApi: function(gridApi) {
	      $scope.gridApi = gridApi;
	      gridApi.rowEdit.on.saveRow($scope, $scope.saveRow);
	      $scope.gridApi.core.on.sortChanged($scope, function(grid, sortColumns) {
	        if (sortColumns.length == 0) {
	          orderpaginationOptions.sort = null;
	        } else {
	          orderpaginationOptions.sort = sortColumns[0].sort.direction;
	        }
	        $scope.searchOrder();
	      });
	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
	        orderpaginationOptions.pageNumber = newPage;
	        orderpaginationOptions.pageSize = pageSize;
	        $scope.searchOrder();
	      });
	      /*gridApi.selection.on.rowSelectionChanged($scope,function(row){
	          var msg = 'row selected ' + row.isSelected;
	          $log.log(msg);
	        });
	   
	        gridApi.selection.on.rowSelectionChangedBatch($scope,function(rows){
	          var msg = 'rows changed ' + rows.length;
	          $log.log(msg);
	        });*/
	    }
	  };
	  
	$scope.addOrder = function() {
	   var n = $scope.ordergridOptions.data.length + 1;
	   $scope.ordergridOptions.data.push({
	        "firstName": "New " + n,
	        "lastName": "Person " + n,
	        "company": "abc",
	        "employed": true,
	        "gender": "male"
		});
	};
	$scope.editOrder=function(){
	}
	$scope.removeSelectOrder=function(){
	}
	$scope.resetOrder=function(){
	}
	$scope.saveOrder=function(){
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
      
      $http.post(path + 'order/saveOrder.ljson', rowEntity).
        success(function (data, status, headers, config) {
            if(data.status == 0) {
                MessageBox.success("操作成功！");
                $scope.searchOrder(false);
            } else {
            	processErroCode(data);
            }
        }).
        error(function (data, status, headers, config) {
            MessageBox.danger(data.data);
        });
    };
    

    /**
     * 搜索
     * @param flag
     */
    $scope.searchOrder = function (flag) {
        if (!flag) {
            $scope.pageIndex = 1
        }
        $scope.orderQuery.pageIndex = orderpaginationOptions.pageNumber;
        $scope.orderQuery.pageSize = orderpaginationOptions.pageSize;
        var url=convertToUrlParam($scope.orderQuery);
        $http.get(path + 'order/getOrders.ljson?'+url).
            success(function (data, status, headers, config) {
                //console.log(data);
                if (data.status == 0) {
                    $scope.ordergridOptions.totalItems = data.data.recordCount;
                    //var firstRow = (orderpaginationOptions.pageNumber - 1) * orderpaginationOptions.pageSize;
                   /// $scope.ordergridOptions.data = data.data.result.slice(firstRow, firstRow + orderpaginationOptions.pageSize);
                    $scope.ordergridOptions.data = data.data.result;
                    $scope.isAllSelected = false;
                } else {
                	processErroCode(data);
                }
            }).
            error(function (data, status, headers, config) {
                    MessageBox.warning("获取失败");
            });
    };

    /*Method---删除*/
    $scope.deleteOrder = function (user) {
        confirm("确定删除选择用户?",function(){
            var deleteOrders = "";
            if(user != null) {
            	deleteOrders = user.username;
            } else {
                angular.forEach($scope.userInfos, function (data,index,array) {
                    if (data.isSelected == true) {
                    	deleteOrders=join(deleteOrders,data.username,",");
                    }
                });
            }
            if (deleteOrders == "") {
                alert("请选择删除对象");
            } else {
                $http.post(path + 'order/deleteOrders.ljson', {deleteOrders:deleteOrders}).
                    success(function (data, status, headers, config) {
                        if(data.status == 0) {
                            $scope.searchOrder();
                        }else{
                        	processErroCode(data);
                        }
                    }).
                    error(function (data, status, headers, config) {
                    });
            }
        },function(){
        	
        })
    };
    $scope.searchOrder();
}]);



