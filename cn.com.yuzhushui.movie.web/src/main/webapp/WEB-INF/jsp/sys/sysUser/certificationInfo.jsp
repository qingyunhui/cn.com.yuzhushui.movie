<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人信息</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta charset="utf-8">
<link href="${path}css/sysBills/m.common.css" rel="stylesheet" type="text/css" />
</head>
<style>
	.form li{
		    margin-bottom: 1.5em;
	}
	.cttBg .form {
	    	font-size: 1.7em;
	}
</style>
</head>
<body class="mWrap">
	<header class="topHd"><a onClick="history.back();return false;" href="javascript:void(0);" class="back">返回</a><a href="${path}app/appMain/myMain.htm" class="home">首页</a><span>个人信息</span></header>
	<section class="mContent">
		<article class="cttBg">
			<ul class="form">
				<li>邮箱：${entity.email}</li>
				<li>姓名：${entity.name}</li>
				<li>年龄：${entity.age}</li>
				<li>性别：${yzsTld:getNameByValue('qing.yun.hui.common.enums.Sex',entity.sex)}</li>
				<li>地区：${entity.area}</li>
				<li>注册时间：<fmt:formatDate value='${entity.ctime}' type='date' pattern='yyyy-MM-dd HH:mm:ss'/></li>
				<li>手机号:${entity.mobilephone}</li>
				<li>身份证号:${entity.idcard}</li>
				<li>认证状态:${yzsTld:getNameByValue('cn.com.yuzhushui.movie.enums.SysUserEnum$State',entity.state)}</li>
				<a href="${path}app/appMain/myMain.htm" class="aBlue right">返回列表页</a></li>
			</ul>
		</article>
	</section>
</body>
</html>