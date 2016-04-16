angular.module("InvoicePreApp", ["LingApp"]).controller('DictionaryTree',
['$scope', '$http', '$timeout', '$interval', '$q','Ling',
function ($scope, $http, $timeout, $interval ,$q,Ling) {
	
	var taskId=getUrlParam("taskId");
	
	$scope.pass=function(){
		testtask('pass')
	}
	$scope.back=function(){
		testtask('back')
	}
	testtask=function(type){
		Ling.post('toDoTask/testTask.do',{type:type,taskId:taskId},function(result){
			   info("执行成功");
		});
	}
}]);



