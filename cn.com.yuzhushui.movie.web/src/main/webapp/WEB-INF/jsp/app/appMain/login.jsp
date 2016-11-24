<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/public/head.jsp"%>
</head>
<body>
<br/>
<h1>=========这是登陆页=========</h1>
<br/>
<a href="${path}app/appMain/login.htm">======单击进行登陆======</a>
<br/>
<form action="${path}app/appMain/doLogin.htm" method="post">
	<input name="accounts" />
	<input name="passwords">
	<input type="submit">
</form>
<script type="text/javascript">
	$(function(){
	
	})
</script>
</body>
</html>