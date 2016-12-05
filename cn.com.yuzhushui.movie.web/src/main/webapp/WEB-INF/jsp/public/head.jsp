<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<meta charset="utf-8">
<meta name="viewport"  content="width=device-width, initial-scale=1">
<%-- <link rel="stylesheet" type="text/css" href="${path}css/style.css">
<link rel="stylesheet" type="text/css" href="${path}css/buttons.css"/>
<link rel="stylesheet" type="text/css" href="${path}css/font-awesome.min.css"> --%>
<link href="${path}css/login/frozen.css" rel="stylesheet" type="text/css"/>
<link href="${path}css/login/public.css" rel="stylesheet" type="text/css" />
<link href="${path}css/login/login.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${path}js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${path}js/layer/layer.js"></script>
<script type="text/javascript">
	/**
	 * 这里声明一些全局JS变量， 方便JS中引用
	 */
	var path = "${path}";          //path:项目名称比如:( /movie/ )结构..
	var basePath ="${basePath}";  //basePath:形如:(http://localhost:8080/movie/)结构..
	var messages="${messages}";   //后台返回的消息
</script>