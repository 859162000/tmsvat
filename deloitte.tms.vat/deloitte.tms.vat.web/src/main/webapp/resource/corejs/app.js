/***
Metronic AngularJS App Main Script
***/
/* Metronic App */
var LingApp = angular.module("LingApp", [
"ui.router", 
"ui.bootstrap", 
'ui.bootstrap.modal',
"oc.lazyLoad",  
"ngSanitize"
]); 
LingApp.factory('securityInterceptor', function ($rootScope) {
    appLoading = $rootScope.appLoading = {state: false};
    return {
        'request': function (config) {
            if (config.url.split('?')[0].endsWith(".do")) {
                $rootScope.appLoading.state = true;
            }
            return config;
        },
        'response': function (response) {
            if (response.config.url.split('?')[0].endsWith(".do")) {
                $rootScope.appLoading.state = false;
            }
            return response;
        },
        'requestError': function (rejection) {
            if (rejection.config.url.split('?')[0].endsWith(".do")) {
                $rootScope.appLoading.state = false;
            }
            return rejection;
        },
        'responseError': function (rejection) {
            if (rejection.config.url.split('?')[0].endsWith(".do")) {
                $rootScope.appLoading.state = false;
            }
            if (rejection.status == 401) {
            	window.location.href=loginpath;
            } else {
                return rejection;
            }
        }
    };
});
LingApp.config(["$urlRouterProvider", "$stateProvider", "$httpProvider", function ($urlRouterProvider, $stateProvider, $httpProvider) {
    $httpProvider.defaults.transformRequest = [function(data)  
    {  
        /** 
         * The workhorse; converts an object to x-www-form-urlencoded serialization. 
         * @param {Object} obj 
         * @return {String} 
         */  
        var param = convertToUrlParam;  
        return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;  
    }]  
    $httpProvider.defaults.useXDomain = true; 
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8'; 
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';  
    $httpProvider.defaults.headers.common['Cache-Control'] = 'no-cache, no-store, must-revalidate';
    $httpProvider.defaults.headers.common['Pragma'] = 'no-cache';
    $httpProvider.defaults.headers.common['Expires'] = '0';
//    $httpProvider.interceptors.push('securityInterceptor');
}]);
angular.module('LingApp').factory('Ling', ['$http',function($http){
	return{
		get:function(url,params,onSucess,onFail){
			var query=convertToUrlParam(params);
			var querypath;
			if(url&&url.indexOf("?")>0){
				querypath=path+url+query;
			}else{
				querypath=path+url+'?'+query;
			}
			$http.get(querypath).
		    success(function (data, status, headers, config) {
		        //console.log(data);
		        if (data.status == 0) {
		        	onSucess(data.data);
		        } else {
		        	if(onFail){
		        		onFail(data)
		        	}else{
		        		processErroCode(data)
		        	}		        	
		        }
		    }).
		    error(function (data, status, headers, config) {
		            erro("操作失败:"+data);
		    });
		},
		post:function(url,entity,onSucess,onFail){
			$http.post(path + url, entity).
		    success(function (data, status, headers, config) {
		        if(data.status == 0) {
		        	onSucess(data.data);
		        } else {
		        	if(onFail){
		        		onFail(data)
		        	}else{
		        		processErroCode(data)
		        	}	
		        }
		    }).
		    error(function (data, status, headers, config) {
		    	erro("操作失败:"+data);
		    });
		}
	}	
}]);