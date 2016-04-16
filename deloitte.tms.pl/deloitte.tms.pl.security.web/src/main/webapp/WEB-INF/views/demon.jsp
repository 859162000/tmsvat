<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>Basic Form - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="themes/icon.css">
	<link rel="stylesheet" type="text/css" href="themes/demo.css"> 
    <script type="text/javascript" src="resource/corejs/jquery.min.js"></script>
    <script type="text/javascript" src="resource/corejs/jquery.easyui.min.js"></script>
</head>
<body >
    
        
	<div data-options="region:'north',border:false" style="height:100%;padding:10px">	  
	
		
		    <form id="ff" method="post">
		    	<table>
		    		<tr>
		    			<td>Name:</td>
		    			<td><input class="easyui-textbox" type="text" name="name" ></input></td>
		    		    <td>Email:</td>
		    			<td><input class="easyui-textbox" type="text" name="email" data-options="validType:'email'"></input></td>	    		
		    			<td>Subject:</td>
		    			<td><input class="easyui-textbox" type="text" name="subject" ></input></td>	 
		    			<td>
		    			   <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">Submit</a>
	    	               <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">Clear</a> 
		    			</td>
		    			  		
		    		  </tr>	    		
		    	</table>
		    </form>
	   
	    
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
			    url:"order/getOrders.ljson",  
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