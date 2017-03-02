<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>账单申请</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta charset="utf-8">
<link href="${path}css/sysBills/m.common.css" rel="stylesheet" type="text/css" />
</head>

</head>
<body class="mWrap">
	<header class="topHd"><a onClick="history.back();return false;" href="javascript:void(0);" class="back">返回</a><a href="${path}app/appMain/myMain.htm" class="home">首页</a><span>账单申请</span></header>
	<section class="mContent">
		<article class="cttBg">
			<ul class="form">
				<li>
					<i class="ico icoJi">支</i>
					<span class="blue"><input type="text"  /></span>
				</li>
				<li>关键字：
					<select>
						<c:forEach var="result" items="${yzsTld:getEnumValues('cn.com.yuzhushui.movie.enums.SysBillsEnum$Keyword')}">
							<option value="${result.code}">${result.name}</option>
						</c:forEach>
					</select>
				</li>
				<li>借款人：${yzsTld:getSysUser().name}</li>
				<li>借款金额¥：<input type="text"  /></li>
				<li>出借人：<input type="text"  /></li>
				<li>交易类型：${yzsTld:getNameByCode('cn.com.yuzhushui.movie.enums.SysBillsEnum$TradeType',entity.tradeType)}</li>
				<li><span class="ftBig orange">¥${entity.money}</span>
				<a href="javascript:pageBack();" class="aBlue right">返回列表页</a></li>
			</ul>
		</article>
		<ul class="form pusht">
			<li><input type="button" class="btn" value="提交" onClick="window.location.href='goods-buying-29449218.html'" /></li>
		</ul>
	</section>
</body>
</html>