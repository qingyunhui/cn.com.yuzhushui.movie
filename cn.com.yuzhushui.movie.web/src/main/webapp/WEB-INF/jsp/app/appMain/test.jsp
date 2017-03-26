<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- <%@ include file="/WEB-INF/jsp/public/head.jsp"%> --%>
<script type="text/javascript" src="${path}js/jquery-1.10.2.min.js"></script>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=1.0" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link href="${path}css/myself/style.css" rel="stylesheet" type="text/css" />
</head>
<body>

<header>
	<div class="rt-bk">
		<i class="bk"></i>
		<p>返回</p>
	</div>
	<div class="top-name"><p>个人中心</p></div>
</header>

	<form action="${path}sys/sysAttachment/upload.htm" method="post" enctype="multipart/form-data">
		<input type="file" name="attachments" />
		<input type="hidden" name="classify" value="photo_album">
		<input type="submit" value="提交">
	</form>

</body>
</html>