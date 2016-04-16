<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isErrorPage="true"%>
<%
response.setStatus(HttpServletResponse.SC_OK); //这句也一定要写,不然IE不会跳转到该页面
String path=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body>
500 error
<div>系统执行发生错误，信息描述如下：</div>
<div>错误状态代码是：${pageContext.errorData.statusCode}</div>
<div>错误发生页面是：${pageContext.errorData.requestURI}</div>
<div>错误信息：${pageContext.exception}</div>
<div>系统已经记录下此信息,请<a href="<%=path%>">返回</a></div>
<!-- <div>
错误堆栈信息：<br/>
<c:forEach var="trace" items="${pageContext.exception.stackTrace}">
<p>${trace}</p>
</c:forEach> -->
</div>
</body>
</html>