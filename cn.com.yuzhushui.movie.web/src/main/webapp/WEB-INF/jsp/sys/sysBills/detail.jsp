<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>账单详情</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta charset="utf-8">
<link href="${path}css/sysBills/m.common.css" rel="stylesheet" type="text/css" />
</head>

</head>
<body class="mWrap">
	<header class="topHd"><a onClick="history.back();return false;" href="javascript:void(0);" class="back">返回</a><a href="${path}app/appMain/myMain.htm" class="home">首页</a><span>账单详情</span></header>
	<section class="mContent">
		<article class="cttBg">
			<ul class="form">
				<li>
				  <i class="ico icoJi">${yzsTld:getNameByValue('cn.com.yuzhushui.movie.enums.SysBillsEnum$Keyword',entity.keyword)}</i>
				  <span class="blue">${entity.subject}</span>
				</li>
				<li>关键字：${yzsTld:getNameByValue('cn.com.yuzhushui.movie.enums.SysBillsEnum$Keyword',entity.keyword)}</li>
				<li>预支人：${entity.debtor}</li>
				<li>预支金额¥：${entity.money}</li>
				<li>预支对象：${entity.lender}</li>
				<li>状态：${yzsTld:getNameByValue('cn.com.yuzhushui.movie.enums.SysBillsEnum$Status',entity.status)}</li>
				<li>交易类型：${yzsTld:getNameByCode('cn.com.yuzhushui.movie.enums.SysBillsEnum$TradeType',entity.tradeType)}</li>
				<li>账单创建时间：<fmt:formatDate value='${entity.ctime}' type='date' pattern='yyyy-MM-dd HH:mm:ss'/></li>
				<li>账单创建人:${entity.creater}</li>
				<li><span class="ftBig orange">¥${entity.money}</span>
				<a href="${path}app/appMain/myMain.htm" class="aBlue right">返回列表页</a></li>
			</ul>
		</article>
		<ul class="form pusht" style="display:${entity.isShow}">
			<li><input type="button" class="btn" value="审核" onClick="window.location.href='${path}sys/sysBills/doAudit.htm?id=${entity.id}'" /></li>
		</ul>
	</section>
</body>
</html>