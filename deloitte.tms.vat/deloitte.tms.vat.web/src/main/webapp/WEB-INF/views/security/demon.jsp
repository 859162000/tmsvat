<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<!-- <title>Basic Form - jQuery EasyUI Demo</title>   
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.4/demo.css">
	<script type="text/javascript" src="jquery-easyui-1.4.4/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4.4/jquery-1.8.0.js"></script> 
	<script type="text/javascript" src="resource/corejs/dateformat.js"></script>  -->
	
	<%@ include file="/common/global.jsp" %> 
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
	  <table class="easyui-datagrid" id="dg"  style="width:100%;height:350px"
			data-options="singleSelect:true,collapsible:true,method:'get'">
		<thead>
			<tr>
				<th data-options="field:'userId',width:80,halign:'center'">Item ID</th>
				<th data-options="field:'userName',width:100,halign:'center'">Product</th>
				<th data-options="field:'phoneNo',width:80,align:'right',halign:'center'">List Price</th>				
			</tr>
		</thead>
	</table>
	  
	
	</div>
	
	<script>
		function submitForm(){
			$('#ff').form('submit', {  
			    url:"order/getOrders.do",  
			    onSubmit: function(){  
			        //进行表单验证  
			        //如果返回false阻止提交  
			    },  
			    success:function(data){  
			        alert(data) 
			        var result = $.parseJSON(data);	
			        alert(result);
			        alert(result.status);
			        
			        $('#dg').datagrid('loadData', {   
			        	"total": result.status ,   
				        "rows": result.data 
			         });   

/* 
				       $("#dg").datagrid('loadData', data);		       
				       $("#dg").datagrid("loaded");  */
			    }  
			}); 

		}
		function clearForm(){
			$('#ff').form('clear');
		}
	</script>
</body>
</html>