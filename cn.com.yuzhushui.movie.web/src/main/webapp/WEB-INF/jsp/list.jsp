<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/public/head.jsp"%>
</head>
<body>
<h1>this is ok!</h1>
<script type="text/javascript">
	$(function(){
		var path='${path}';
		var basePath='${basePath}';
		console.log("path:"+path);
	})
</script>
</body>
</html>