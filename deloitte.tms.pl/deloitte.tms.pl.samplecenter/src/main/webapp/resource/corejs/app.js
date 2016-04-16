/***
Metronic AngularJS App Main Script
***/
/* Metronic App */
var LingApp = angular.module("LingApp", [
    "ui.router", 
    "ui.bootstrap", 
    'ui.bootstrap.modal',
    "oc.lazyLoad",  
    "ngSanitize",
    'ngFileUpload',
    'ngTouch'
//    'treeControl',
//    'ng-context-menu'
//    ',ui.grid',
//    'ui.grid.pagination',
//    'ui.grid.edit',
//    'ui.grid.rowEdit',
//    'ui.grid.cellNav',
//    'ui.grid.selection',
//    'ui.grid.resizeColumns', 'ui.grid.pinning','ui.grid.moveColumns', 'ui.grid.exporter', 'ui.grid.importer', 'ui.grid.grouping'
]); 
/* Configure ocLazyLoader(refer: https://github.com/ocombe/ocLazyLoad) */
LingApp.config(['$ocLazyLoadProvider', function($ocLazyLoadProvider) {
    $ocLazyLoadProvider.config({
        // global configs go here
    });
}]);

//AngularJS v1.3.x workaround for old style controller declarition in HTML
LingApp.config(['$controllerProvider', function($controllerProvider) {
  // this option might be handy for migrating old apps, but please don't use it
  // in new ones!
  $controllerProvider.allowGlobals();
}]);

/********************************************
 END: BREAKING CHANGE in AngularJS v1.3.x:
*********************************************/

/* Setup global settings */
LingApp.factory('settings', ['$rootScope', function($rootScope) {
    // supported languages
    var settings = {
        layout: {
            pageSidebarClosed: false, // sidebar menu state
            pageContentWhite: true, // set page content layout
            pageBodySolid: false, // solid body color state
            pageAutoScrollOnLoad: 1000 // auto scroll to top on page load
        },
        assetsPath: '../assets',
        globalPath: '../assets/global',
        layoutPath: '../assets/layouts/layout',
    };

    $rootScope.settings = settings;

    return settings;
}]);

/*$httpProvider.interceptors的一个实现***********/

appLoading = null;

LingApp.factory('securityInterceptor', function ($rootScope) {
    appLoading = $rootScope.appLoading = {state: false};
    return {
        'request': function (config) {
            if (config.url.split('?')[0].endsWith(".ljson")) {
                $rootScope.appLoading.state = true;
            }
            return config;
        },
        'response': function (response) {
            if (response.config.url.split('?')[0].endsWith(".ljson")) {
                $rootScope.appLoading.state = false;
            }
            return response;
        },
        'requestError': function (rejection) {
            if (rejection.config.url.split('?')[0].endsWith(".ljson")) {
                $rootScope.appLoading.state = false;
            }
            return rejection;
        },
        'responseError': function (rejection) {
            if (rejection.config.url.split('?')[0].endsWith(".ljson")) {
                $rootScope.appLoading.state = false;
            }
            if (rejection.status == 401) {
                window.location.href = path + "pages/backgroundManage/login.html";
            } else {
                return rejection;
            }
        }
    };
});
LingApp.config(["$urlRouterProvider", "$stateProvider", "$httpProvider", function ($urlRouterProvider, $stateProvider, $httpProvider) {
    //$urlRouterProvider.otherwise("/main");

//    $httpProvider.defaults.transformRequest = function (data) {
//        if (data === undefined) {
//            return data;
//        }
//        return $.param(data);
//    };
 // Override $http service's default transformRequest  
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
    //$httpProvider.interceptors.push('securityInterceptor');
}]);
/* Setup App Main Controller */
LingApp.controller('AppController', ['$scope', '$rootScope', function($scope, $rootScope) {
    $scope.$on('$viewContentLoaded', function() {
        //App.initComponents(); // init core components
        //Layout.init(); //  Init entire layout(header, footer, sidebar, etc) on page load if the partials included in server side instead of loading with ng-include directive 
    });
}]);

/***
Layout Partials.
By default the partials are loaded through AngularJS ng-include directive. In case they loaded in server side(e.g: PHP include function) then below partial 
initialization can be disabled and Layout.init() should be called on page load complete as explained above.
***/

/* Setup Layout Part - Header */
LingApp.controller('HeaderController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initHeader(); // init header
    });
}]);

/* Setup Layout Part - Sidebar */
LingApp.controller('SidebarController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initSidebar(); // init sidebar
    });
}]);

/* Setup Layout Part - Quick Sidebar */
LingApp.controller('QuickSidebarController', ['$scope', function($scope) {    
    $scope.$on('$includeContentLoaded', function() {
       setTimeout(function(){
            QuickSidebar.init(); // init quick sidebar        
        }, 2000)
    });
}]);

/* Setup Layout Part - Theme Panel */
LingApp.controller('ThemePanelController', ['$scope', function($scope) {    
    $scope.$on('$includeContentLoaded', function() {
        Demo.init(); // init theme panel
    });
}]);

/* Setup Layout Part - Footer */
LingApp.controller('FooterController', ['$scope', function($scope) {
    $scope.$on('$includeContentLoaded', function() {
        Layout.initFooter(); // init footer
    });
}]);



/* Init global settings and run the app */
LingApp.run(["$rootScope", "settings", "$state", function($rootScope, settings, $state) {
    $rootScope.$state = $state; // state to be accessed from view
    $rootScope.$settings = settings; // state to be accessed from view
}]);

angular.module('LingApp')

// Angular File Upload module does not include this directive
// Only for example


/**
* The ng-thumb directive
* @author: nerv
* @version: 0.1.2, 2014-01-09
*/
.directive('ngThumb', ['$window', function($window) {
    var helper = {
        support: !!($window.FileReader && $window.CanvasRenderingContext2D),
        isFile: function(item) {
            return angular.isObject(item) && item instanceof $window.File;
        },
        isImage: function(file) {
            var type =  '|' + file.type.slice(file.type.lastIndexOf('/') + 1) + '|';
            return '|jpg|png|jpeg|bmp|gif|'.indexOf(type) !== -1;
        }
    };

    return {
        restrict: 'A',
        template: '<canvas/>',
        link: function(scope, element, attributes) {
            if (!helper.support) return;

            var params = scope.$eval(attributes.ngThumb);

            if (!helper.isFile(params.file)) return;
            if (!helper.isImage(params.file)) return;

            var canvas = element.find('canvas');
            var reader = new FileReader();

            reader.onload = onLoadFile;
            reader.readAsDataURL(params.file);

            function onLoadFile(event) {
                var img = new Image();
                img.onload = onLoadImage;
                img.src = event.target.result;
            }

            function onLoadImage() {
                var width = params.width || this.width / this.height * params.height;
                var height = params.height || this.height / this.width * params.width;
                canvas.attr({ width: width, height: height });
                canvas[0].getContext('2d').drawImage(this, 0, 0, width, height);
            }
        }
    };
}]);

/* Setup Rounting For All Pages */
LingApp.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
    // Redirect any unmatched url
    $urlRouterProvider.otherwise("/test.html");  
    
    $stateProvider
	    .state('userManager', {
	        url: "/userManager.html",
	        templateUrl: "security/views/userManager.html",            
	        data: {pageTitle: '用户信息管理'},
	        resolve: {
	            deps: ['$ocLazyLoad', function($ocLazyLoad) {
	                return $ocLazyLoad.load({
	                    name: 'LingApp',
	                    insertBefore: '#ng_load_plugins_before', // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
	                    files: [                            
	                           // "../resource/css/artogether-admin.css",
	                            '../security/js/UserManagerController.js'
	                    ] 
	                });
	            }]
	        }
	    }).state('order', {
	        url: "/order.html",
	        templateUrl: "order/views/order.html",            
	        data: {pageTitle: '用户信息管理'},
	        resolve: {
	            deps: ['$ocLazyLoad', function($ocLazyLoad) {
	                return $ocLazyLoad.load({
	                    name: 'LingApp',
	                    insertBefore: '#ng_load_plugins_before', // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
	                    files: [                            
	                           // "../resource/css/artogether-admin.css",
	                            '../order/js/OrderController.js'
	                    ] 
	                });
	            }]
	        }
	    }).state('test', {
	        url: "/test.html",
	        templateUrl: "order/views/test.html",            
	        data: {pageTitle: '用户信息管理'},
	        resolve: {
	            deps: ['$ocLazyLoad', function($ocLazyLoad) {
	                return $ocLazyLoad.load({
	                    name: 'LingApp',
	                    insertBefore: '#ng_load_plugins_before', // load the above css files before a LINK element with this ID. Dynamic CSS files must be loaded between core and theme css files
	                    files: [                            
	                           // "../resource/css/artogether-admin.css",
	                            '../order/js/CategoryTreeController.js'
	                    ] 
	                });
	            }]
	        }
	    })		    	    
}]);