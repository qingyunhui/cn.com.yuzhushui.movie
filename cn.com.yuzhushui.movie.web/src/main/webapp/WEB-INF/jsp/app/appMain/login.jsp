<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/public/head.jsp"%>
</head>
<body> 

	<h1>well come to by login page。</h1>

	<br/>
	
	<form action="${path}app/appMain/doLogin.htm" method="post">
		<input name="accounts" />
		<input name="passwords">
		<input type="submit">
	</form>
	
<script>

</script>
</body>
</html>