<#assign pojo=oneToManyPojo.getOne()>
<#assign many=oneToManyPojo.getMany()>
<#assign ones=oneToManyPojo.getOnes()>
<#assign fields=pojo.getPojoFields()>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>
<#assign declarationNameFirstLetterLower = pojo.importType(pojo.getDeclarationNameFirstLetterLower())>

angular.module('LingApp').controller('${declarationName}Ctroller',
['$scope', '$http', '$timeout', '$interval', '$q','Ling',
function ($scope, $http, $timeout, $interval ,$q,Ling) {

    $scope.main${declarationName}=true;
    $scope.add${declarationName}=false;
    $scope.edit${declarationName}=false;
    
    /*Data---查询条件*/
    $scope.${declarationNameFirstLetterLower}Query = {
       pageIndex:1,
       pageSize:10,
       maxSize:5,
       recordCount:0
    };
    $scope.${declarationNameFirstLetterLower}ColumnDefs=[
<#list fields as field>
<#if field.getIsSampleField()&&field.getIsNotBaseEntiry()>
	<#if field.getName()=="id">
			{ name:'${field.getName()}', width:'120px',lable:'<#if field.getComment()?exists>${field.getComment()}<#else>${field.getName()}</#if>', enableCellEdit: false },
	<#else>
			{ name:'${field.getName()}', lable:'<#if field.getComment()?exists>${field.getComment()}<#else>${field.getName()}</#if>'<#if field.getHtmlType()?exists>,type: '${field.getHtmlType()}'<#if field.getHtmlType()=='date'>,cellFilter: 'date:"yyyy-MM-dd"'<#else></#if><#else></#if> ,enableCellEdit: true
			,style:{overflow: 'hidden',width:'120px'}}<#if field_has_next>,</#if>
	</#if>
</#if>                             
</#list>       
	    ]
    $scope.${declarationNameFirstLetterLower}Items=[];
    $scope.current${declarationName}={};
    	 
	$scope.add${declarationName} = function() {
	 	$scope.main${declarationName}=false;
	    $scope.add${declarationName}=true;
	    $scope.edit${declarationName}=false;
	    $scope.current${declarationName}={};
	};
	$scope.edit${declarationName}=function(entity){
		$scope.main${declarationName}=false;
	    $scope.add${declarationName}=false;
	    $scope.edit${declarationName}=true;
	    angular.extend($scope.current${declarationName}, entity);
	}
	$scope.reset${declarationName}=function(){
	}
	$scope.save${declarationName}=function(entity){
	 Ling.post('${declarationNameFirstLetterLower}/save${declarationName}.ljson',entity,function(result){
		MessageBox.success("操作成功！");
		$scope.search${declarationName}(false);
      });
	}
	$scope.save${declarationName}All=function(){
	}
    

    /**
     * 搜索
     * @param flag
     */
    $scope.search${declarationName} = function (flag) {
        if (!flag) {
            $scope.pageIndex = 1
        }
        Ling.get('${declarationNameFirstLetterLower}/get${declarationName}s.ljson',$scope.${declarationNameFirstLetterLower}Query,function(result){
        	$scope.${declarationNameFirstLetterLower}Query.recordCount = result.recordCount;
	        $scope.${declarationNameFirstLetterLower}Items = result.result;
	        $scope.${declarationNameFirstLetterLower}Selections=[];
	        $scope.current${declarationName}={};
        })
    };
	/*Method---删除*/
    $scope.remove${declarationName} = function (${declarationNameFirstLetterLower}) {
        confirm("确定删除选择用户?",function(){
            Ling.post('${declarationNameFirstLetterLower}/remove${declarationName}s.ljson',{remove${declarationName}s:${declarationNameFirstLetterLower}.id},function(result){
            		$scope.search${declarationName}();
            });
        })
    };
    /*Method---删除*/
    $scope.removeSelect${declarationName}s = function () {
        confirm("确定删除选择用户?",function(){
            var ${declarationNameFirstLetterLower}Keys = "";
            angular.forEach($scope.${declarationNameFirstLetterLower}Items, function (data,index,array) {
            	if(data.selected){
                	${declarationNameFirstLetterLower}Keys=join(${declarationNameFirstLetterLower}Keys,data.id,",");
                }
            });
            if (${declarationNameFirstLetterLower}Keys == "") {
                alert("请选择删除对象");
            } else {
            	Ling.post('${declarationNameFirstLetterLower}/remove${declarationName}s.ljson',{'${declarationNameFirstLetterLower}Keys':${declarationNameFirstLetterLower}Keys},function(result){
            		$scope.search${declarationName}();
            	});
            }
        },function(){
        	
        })
    };
    $scope.search${declarationName}();
}]);



