<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="LingApp">
<head>
	<meta charset="UTF-8">
	<title>Basic Form - jQuery EasyUI Demo</title>

	<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
<!-- 	<link rel="stylesheet" type="text/css" href="themes/icon.css">
	<link rel="stylesheet" type="text/css" href="themes/demo.css">  -->
    <script type="text/javascript" src="resource/corejs/jquery.min.js"></script>
    <script type="text/javascript" src="resource/corejs/jquery.easyui.min.js"></script>
    
<!--[if lt IE 9]>
	<script src="assets/global/plugins/respond.min.js"></script>
	<script src="assets/global/plugins/excanvas.min.js"></script> 
	<![endif]-->
        <script src="assets/global/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="assets/global/plugins/ueditor/ueditor.config.js"></script>
		<script type="text/javascript" src="assets/global/plugins/ueditor/ueditor.all.min.js"></script>	
		<script src="assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>	
        <!-- END CORE JQUERY PLUGINS -->
        <!-- BEGIN CORE ANGULARJS PLUGINS -->
        <script src="assets/global/plugins/angularjs/angular.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/angularjs/angular-sanitize.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/angularjs/angular-touch.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/angularjs/plugins/angular-ui-router.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/angularjs/plugins/ocLazyLoad.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/angularjs/plugins/ui-bootstrap-tpls.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/angularjs/angular-animate.js"></script>
        <!-- <script src="assets/global/plugins/angularjs/plugins/angular-ui-uploader.min.js"></script>
        <script src="assets/global/plugins/angularjs/plugins/angular-file-upload/angular-file-upload.min.js"></script> -->
        <script src="assets/global/plugins/angularjs/plugins/ng-file-upload/ng-file-upload-shim.min.js"></script>
		<script src="assets/global/plugins/angularjs/plugins/ng-file-upload/ng-file-upload.min.js"></script>
		<script src="assets/global/plugins/angularjs/plugins/ui-grid/ui-grid.min.js" type="text/javascript"></script> 		
        <!-- END CORE ANGULARJS PLUGINS -->
        
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="assets/global/plugins/moment.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.js" type="text/javascript"></script>       
        <script src="assets/global/plugins/counterup/jquery.waypoints.min.js" type="text/javascript"></script>
        <script src="assets/global/plugins/counterup/jquery.counterup.min.js" type="text/javascript"></script>       
        <script src="assets/global/plugins/fullcalendar/fullcalendar.min.js" type="text/javascript"></script>   
        
        <!-- END PAGE LEVEL PLUGINS -->
        
        <!-- BEGIN APP LEVEL ANGULARJS SCRIPTS -->
        <script src="resource/corejs/config.js" type="text/javascript"></script>
        <script src="resource/corejs/ling3.js" type="text/javascript"></script>
        <script src="resource/corejs/app.js" type="text/javascript"></script>
        <script src="resource/corejs/directives.js" type="text/javascript"></script>
        <script src="demon.js" type="text/javascript"></script>
        <!-- END APP LEVEL ANGULARJS SCRIPTS -->
        
        <!-- BEGIN APP LEVEL JQUERY SCRIPTS -->
        <script src="assets/global/scripts/app.min.js" type="text/javascript"></script>
        <script src="assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
        <script src="assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
</head>
<body ng-controller="DemonCtroller">
    
        
	<div data-options="region:'north',border:false" style="height:100%;padding:10px">	  
	
		
		    
		    	<table>
		    		<tr>
		    			<td>Name:</td>
		    			<td><input type="text" name="name"  ng-model="queryParam.name"></input></td>
		    		    <td>Email:</td>
		    			<td><input type="text" name="email" data-options="validType:'email'" ng-model="queryParam.email"></input></td>	    		
		    			<td>Subject:</td>
		    			<td><input type="text" name="subject" ng-model="queryParam.subject"></input></td>	 
		    			<td>
		    			   <a class="easyui-linkbutton" ng-click="submitForm()">Submit</a>
	    	               <a class="easyui-linkbutton" ng-click="clearForm()">Clear</a> 
		    			</td>
		    			  		
		    		  </tr>	    		
		    	</table>
		    <
	   
	    
	<!-- </div> -->
	
	<div data-options="region:'center',title:'Center'" style="height:100%;padding:10px">
	  <table class="easyui-datagrid" style="width:100%;height:350px"
			data-options="singleSelect:true,collapsible:true,url:'datagrid_data1.json',method:'get'">
		<thead>
			<tr>
				<th data-options="field:'itemid',width:80,halign:'center'">Item ID</th>
				<th data-options="field:'productid',width:100,halign:'center'">Product</th>
				<th data-options="field:'listprice',width:80,align:'right',halign:'center'">List Price</th>
				<th data-options="field:'unitcost',width:80,align:'right',halign:'center'">Unit Cost</th>
				<th data-options="field:'attr1',width:250,halign:'center'">Attribute</th>
				<th data-options="field:'status',width:60,align:'center',halign:'center'">Status</th>
			</tr>
		</thead>
	</table>
	  
	
	</div>
	
	<script>
		function submitForm(){
			$('#ff').form('submit', {  
			    url:"",  
			    onSubmit: function(){  
			        //进行表单验证  
			        //如果返回false阻止提交  
			    },  
			    success:function(data){  
			        alert(data)  
			    }  
			}); 

		}
		function clearForm(){
			$('#ff').form('clear');
		}
	</script>
</body>
</html>