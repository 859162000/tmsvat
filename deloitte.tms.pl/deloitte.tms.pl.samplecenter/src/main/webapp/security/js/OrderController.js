angular.module('LingApp').controller('UserManageCtroller', ['$scope', '$http', '$timeout', '$interval', 'uiGridConstants', 'uiGridGroupingConstants',
                                                            function ($scope, $http, $timeout, $interval, uiGridConstants, uiGridGroupingConstants) {

    $scope.main=true;
    $scope.add=false;
    $scope.edit=false;
    
    var paginationOptions = {
    	    pageNumber: 1,
    	    pageSize: 25,
    	    sort: null
    	  };
    	 
    	  $scope.gridOptions = {
    	    paginationPageSizes: [25, 50, 75],
    	    paginationPageSize: 25,
    	    useExternalPagination: true,
    	    useExternalSorting: true,
    	    
    	    enableRowSelection: true,
    	    enableSelectAll: true,
    	    selectionRowHeaderWidth: 35,
    	    rowHeight: 35,
    	    showGridFooter:true,
    	    enableFullRowSelection:true,
    	    columnDefs: [
    	      { name: 'username', enableCellEdit: false },
			    { name: 'name', displayName: 'Name (editable)' },
			    { name: 'gender' },
			    { name: 'age', displayName: 'Age' , type: 'number'},
			    { name: 'registered', displayName: 'Registered' , type: 'date', cellFilter: 'date:"yyyy-MM-dd"'},
			    { name: 'address', displayName: 'Address', type: 'object'},
			    { name: 'address.city', displayName: 'Address (even rows editable)',
			         cellEditableCondition: function($scope){
			         return $scope.rowRenderIndex%2
			         }
			    },
			    { name: 'isActive', displayName: 'Active', type: 'boolean'}
    	    ],
    	    onRegisterApi: function(gridApi) {
    	      $scope.gridApi = gridApi;
    	      gridApi.rowEdit.on.saveRow($scope, $scope.saveRow);
    	      $scope.gridApi.core.on.sortChanged($scope, function(grid, sortColumns) {
    	        if (sortColumns.length == 0) {
    	          paginationOptions.sort = null;
    	        } else {
    	          paginationOptions.sort = sortColumns[0].sort.direction;
    	        }
    	        $scope.searchUser();
    	      });
    	      gridApi.pagination.on.paginationChanged($scope, function (newPage, pageSize) {
    	        paginationOptions.pageNumber = newPage;
    	        paginationOptions.pageSize = pageSize;
    	        $scope.searchUser();
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
   
    $scope.saveRow = function( rowEntity ) {
      // create a fake promise - normally you'd use the promise returned by $http or $resource
      var promise = $q.defer();
      $scope.gridApi.rowEdit.setSavePromise( rowEntity, promise.promise );
   
      // fake a delay of 3 seconds whilst the save occurs, return error if gender is "male"
      $interval( function() {
        if (rowEntity.gender === 'male' ){
          promise.reject();
        } else {
          promise.resolve();
        }
      }, 3000, 1);
    };
      $scope.addData = function() {
        var n = $scope.gridOptions.data.length + 1;
        $scope.gridOptions.data.push({
                    "firstName": "New " + n,
                    "lastName": "Person " + n,
                    "company": "abc",
                    "employed": true,
                    "gender": "male"
                  });
      };
     //var count = grid.selection.selectedCount
      $scope.removeFirstRow = function() {
        //if($scope.gridOptions.data.length > 0){
           $scope.gridOptions.data.splice(0,1);
        //}
      };
     
      $scope.reset = function () {
        data1 = angular.copy(origdata1);
        data2 = angular.copy(origdata2);
     
        $scope.gridOptions.data = data1;
        $scope.gridOptions.columnDefs = columnDefs1;
      }
//    $scope.gridOptions.onRegisterApi = function(gridApi){
//      //set gridApi on scope
//      $scope.gridApi = gridApi;
//      //gridApi.rowEdit.on.saveRow($scope, $scope.saveRow);
//    };

    /**
     * 获取角色分类信息
     */
    $scope.getRole = function () {
        $http.get(path + 'admin/getAllEnableRoles.ljson').
            success(function (data, status, headers, config) {
                if (0 == data.status && data.data != null) {
                    $scope.roles = data.data;
                    //console.log(data);
                }else{
                	processErroCode(data);
                }
            }).
            error(function (data, status, headers, config) {
                console.log(data.data);
            });
    };

    /*Data---查询条件*/
    $scope.searchModel = {
        searchWord:''
    };

    /**
     * 搜索
     * @param flag
     */
    $scope.searchUser = function (flag) {
        if (!flag) {
            $scope.pageIndex = 1
        }
        $scope.searchModel.pageIndex = paginationOptions.pageNumber;
        $scope.searchModel.pageSize = paginationOptions.pageSize;
        var url=convertToUrlParam($scope.searchModel);
        $http.get(path + 'admin/getUsers.ljson?'+url).
            success(function (data, status, headers, config) {
                //console.log(data);
                if (data.status == 0) {
                    $scope.gridOptions.totalItems = data.data.recordCount;
                    //var firstRow = (paginationOptions.pageNumber - 1) * paginationOptions.pageSize;
                   /// $scope.gridOptions.data = data.data.result.slice(firstRow, firstRow + paginationOptions.pageSize);
                    $scope.gridOptions.data = data.data.result;
                    $scope.isAllSelected = false;
                } else {
                	processErroCode(data);
                }
            }).
            error(function (data, status, headers, config) {
                    MessageBox.warning("获取失败");
            });
    };
    /**
     * 获取用户数据
     * @type {Array}
     */
    $scope.userInfos = [
        /*{
         userId: "1",
         nickName: "小王",
         role: "系统管理员",
         registrationTime: "2015-10-15 17:00"
         }*/
    ];

    /**
     * 新建用户
     * @type {Array}
     */
    $scope.newUser = {
        mobile:'',
        nickname:'',
        realName:'',
        roles:[],
        remark:'',
        organization:'',
        skill:'',
        workDate:''
    };
    /**
     * 点击新增用户的同时生成昵称
     */
    $scope.addUserDialog = function () {
        resetNewUser();
        var characters="0123456789";
        var value = '',index;
        for(var j=1;j<=4;j++){
            index = parseInt(9*Math.random());
            value = value + characters.charAt(index);
        }
        $scope.newUser.nickname = "用户" + value;
    };

    /**
     * 重置用户信息
     */
    function resetNewUser () {
        angular.forEach($scope.roles, function(value,key){
            value.checked = false;
        });
        $scope.newUser = {
            mobile:'',
            nickname:'',
            realName:'',
            roles:[],
            remark:'',
            organization:'',
            skill:'',
            workDate:''
        };
    }
    /**
     * 用户新增
     */
    $scope.addUser = function () {
        var tel = $scope.newUser.mobile; //获取手机号
        var telReg = !!tel.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);

        angular.forEach($scope.roles, function(value,key){
            if(value.checked == true && $scope.newUser.roles.indexOf(value)<0){
                $scope.newUser.roles.push(value);
            }
        });
        
        if(telReg == false){
            MessageBox.danger("请输入正确的手机号！");
            return;
        }

        //alert(JSON.stringify($scope.newUser));
        $http.post(path + 'admin/saveNewUser.ljson', $scope.newUser).
            success(function (data, status, headers, config) {
                //console.log(data);
                if(data.status == 0) {
                    $scope.userInfos.push(data.data);
                    $scope.searchUser(false);
                    resetNewUser();
                } else {
                	processErroCode(data);
                }
            }).
            error(function (data, status, headers, config) {
                console.log(data);
            });
    };

    $scope.selectedUser = {};
    $scope.editorUser = function (selectedUser) {
        $scope.selectedUser = {};
        selectedUser.workDate = new Date(selectedUser.workDate == null ? new Date() : selectedUser.workDate);
        angular.extend($scope.selectedUser, selectedUser);
        console.log($scope.selectedUser);
        angular.forEach($scope.roles,function(value,key){
            value.checked = false;
            angular.forEach($scope.selectedUser.roles,function(sValue,sKey){
                if(value.name == sValue.name) {
                    value.checked = true;
                }
            });
        });
    };

    $scope.saveEditorUser = function () {
        //alert(JSON.stringify($scope.roles));
        $scope.selectedUser.roles = [];
        angular.forEach($scope.roles,function(value,key){
            if(value.checked == true) {
                $scope.selectedUser.roles.push(value);
            }
        });
        //alert(JSON.stringify($scope.selectedUser.roles));
        $http.post(path + 'admin/updateUser.ljson', $scope.selectedUser).
            success(function (data, status, headers, config) {
                if(data.status == 0) {
                    MessageBox.success("操作成功！");
                    $scope.searchUser(false);
                    $scope.selectedUser = [];
                } else {
                	processErroCode(data);
                }
            }).
            error(function (data, status, headers, config) {
                MessageBox.danger(data.data);
            });
    };

    /*Method---全选*/
    $scope.isAllSelected = false;
    $scope.selectedAll = function (isAllSelected) {
        angular.forEach($scope.userInfos, function (value, key) {
            value.isSelected = isAllSelected;
        });
    };

    /*Method---单选*/
    $scope.selectedUser = function (user) {
        $scope.isAllSelected = false;
        user.isSelected = !user.isSelected;
    };

    /*Method---删除*/
    $scope.deleteUser = function (user) {
        confirm("确定删除选择用户?",function(){
            var deleteUsers = "";
            if(user != null) {
            	deleteUsers = user.username;
            } else {
                angular.forEach($scope.userInfos, function (data,index,array) {
                    if (data.isSelected == true) {
                    	deleteUsers=join(deleteUsers,data.username,",");
                    }
                });
            }
            if (deleteUsers == "") {
                alert("请选择删除对象");
            } else {
                $http.post(path + 'admin/deleteUsers.ljson', {deleteUsers:deleteUsers}).
                    success(function (data, status, headers, config) {
                        if(data.status == 0) {
                            $scope.searchUser();
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
    //$scope.getRole();
    $scope.searchUser();
}]);



