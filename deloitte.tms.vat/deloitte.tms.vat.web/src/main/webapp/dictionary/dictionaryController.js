angular.module("DictionaryApp", ["LingApp"]).controller('DictionaryTree',
['$scope', '$http', '$timeout', '$interval', '$q','Ling',
function ($scope, $http, $timeout, $interval ,$q,Ling) {
	
	$scope.categories=[];
	$scope.currentCategory={};

   $scope.showSelected = function(sel) {
//	   if(sel&&sel.id&&$scope.currentCategory.id!=sel.id){
//		   saveCategory(function(){
			   $scope.currentCategory=sel;
//		   });
//	   }	   	   
   };    
   $scope.isselect=function(sel){
	   return $scope.currentCategory.id==sel.id;
   }
   $scope.loadTopCategory = function () {
       Ling.get('category/loadSysCategory.do',{},function(result){
    	   $scope.categories=result;
    	   selectfirst();
       })
   };
   $scope.loadChildCategory = function (category) {
	   loadCategory(category);
   };
   $scope.addRoot=function(){
	   var newRoot={level:1};
	   if($scope.categories==null){
		   $scope.categories=[];
	   }
	   $scope.categories.push(newRoot);
	   $scope.currentCategory=newRoot;
   }
   $scope.addChild=function(node){
	   if(node.loaded){
		   appendChild(node);		   
	   }else{
		   loadCategory(node,appendChild) 
	   }	   
   }
   $scope.saveCurrent=function(){
	   saveCategory();
   }
   $scope.removeCategory=function(node){
	   Ling.post('category/removeSysCategory.do',$scope.currentCategory,function(result){
		   $scope.currentCategory.id=result.id;
		   info("删除成功");
		   $scope.loadTopCategory();
	   });
   }
   $scope.loadTopCategory();

   var loadCategory=function(parentNode,onSucess){
	   if(parentNode.id){
		   parentNode.expand=!parentNode.expand;
		   if(parentNode.loaded){
			   
		   }else{
			   Ling.get('category/loadSysCategory.do',{parentId:parentNode.id},function(result){
				   parentNode.childs=result;			   
		    	   if(onSucess){
		    		   onSucess(parentNode);
		    	   }
		    	   parentNode.loaded=true;
		       })
		   }
	   }else{
		   warning("请先保存上级节点")
	   }	        
   }
   var appendChild=function(parentNode){
	   if(parentNode.id){
		   var newChild={
					level:parentNode.level+1,
					parentId:parentNode.id
			   };
		   if(parentNode.childs==null){
			   parentNode.childs=[];
		   }	   
		   parentNode.childs.push(newChild);
		   $scope.currentCategory=newChild;
		   parentNode.expand=true;
	   }else{
		   warning("请先保存上级节点")
	   }	   
   }
   var selectfirst=function(){
	   if($scope.categories.length>0){
		   $scope.currentCategory=$scope.categories[0];
	   }
   }
   var saveCategory=function(onSucess){
	   Ling.post('category/saveSysCategory.do',$scope.currentCategory,function(result){
		   $scope.currentCategory.id=result.id;
		   info("保存成功");
		   if(onSucess){
			   onSucess()
		   }
	   });
   }
}]);



