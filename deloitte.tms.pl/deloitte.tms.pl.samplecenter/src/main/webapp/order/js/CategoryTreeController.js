
angular.module('LingApp').controller('CategoryTree',
['$scope', '$http', '$timeout', '$interval', '$q','Ling',
function ($scope, $http, $timeout, $interval ,$q,Ling) {

	$scope.treedata=[
      {label: "Documents", type: "folder", children: [
          {label: "a picture", type: "file"},
          {label: "another picture", type: "file"},
          {label: "a doc", type: "file"},
          {label: "a file", type: "file"},
          {label: "a movie", type: "file"}
      ]},
      {label: "My Computer", type: "folder", children: [
          {label: "email", type: "file"},
          {label: "home", type: "file"}
      ]},
      {label: "trash", type: "trash"}

   ];
   $scope.showSelected = function(sel) {
      $scope.selectedNode = sel;
      //console.log(sel)
   };

}]);



