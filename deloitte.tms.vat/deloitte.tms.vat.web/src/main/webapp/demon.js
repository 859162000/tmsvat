angular.module('LingApp').controller('DemonCtroller', ['$scope', '$http', 
                                                            function ($scope, $http) {
	
	$scope.queryParam={name:"111111111111"};
	
	$scope.submitForm=function(){
		var url=convertToUrlParam($scope.queryParam);
		console.log(url)
		$http.get(path + 'order/getOrders.ljson?'+url).
        success(function (data, status, headers, config) {
            //console.log(data);
            if (data.status == 0) {
                $scope.ordergridOptions.totalItems = data.data.recordCount;
                $scope.ordergridOptions.data = data.data.result;
            } else {
            	processErroCode(data);
            }
        }).
        error(function (data, status, headers, config) {
                MessageBox.warning("获取失败");
        });
	}
}]);
