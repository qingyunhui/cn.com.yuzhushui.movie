<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>个人列表</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta charset="utf-8">
<link href="${path}css/sysBills/m.common.css" rel="stylesheet" type="text/css" />
</head>

</head>
<body class="mWrap">
	<!-- 页头 -->
	<header class="topHd"><a onClick="history.back();return false;" href="javascript:void(0);" class="back">返回</a><a href="${path}app/appMain/myMain.htm" class="home">首页</a><span>我的账单</span></header>
	<section class="mContent">
		<ul class="proList clearfix" id="dataUl">
			
		  <c:forEach var="result" items="${entitys}" >
		  	<li>
				<a href="goods-29449218.html"><p class="tt"><i class="ico icoJi">${result.keyword}</i>${result.subject}</p></a>
				<p>
					<a href="goods-29449218.html"><ins class="sbtn right">购买${result.keyword}</ins></a>
					<a href="goods-29449218.html"><span class="price">¥${result.money}</span><br>${result.content}</a>
				</p>
			</li>
		  </c:forEach>
		
			
		<div id="dataappend"></div>
		</ul>
			
			<div class="moreList" id="goodsload">+点击加载后20条+</div>
		
	</section>

</body>
</html>