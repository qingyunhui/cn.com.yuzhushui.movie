<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>API</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta charset="utf-8">
<link href="${path}css/myMain/style.css" rel="stylesheet" type="text/css" />
<link href="${path}css/sysBills/m.common.css" rel="stylesheet" type="text/css" />
<style>
.banner img {width: 100%;}
.footer {
    position: fixed;
    bottom: 0px;
    left: 0px;
    margin-top: 0px;
    width: 100%;
    height: 3.0rem;
    background: url(../image/myMain/footer/projection.png) repeat-x;
    padding-top: 0.5rem;
}

.footer .mune {
    float: left;
    width: 25%;
    height: 0.7rem;
    text-align: center;
}
.mune img {
    width: 2.0rem;
    height: 2.0rem;
}
.mune p {
    font-size: 0.22rem;
}
body .mainmenu li.UL_Li_CLSS{
	float: left;
    margin-left: 2.5%;
    margin-top: 5.5%;
    width: 95%;
    border-radius: 3px;
    overflow: hidden;
}
body .mainmenu li.UL_Li_CLSS a span{
	display: block;
    height: 35px;
    line-height: 35px;
    color: #999;
    font-size: 16px;
}

body .mainmenu li.balance0 a span{
	 display: block;
     height: 35px;
     line-height: 35px;
     color: #080000;
     font-size: 20px;
}
body .mainmenu li.balance1 a span{
	display: block;
    height: 35px;
    line-height: 35px;
    color: #e88888;
    font-size: 18px;
}
body .mainmenu li.balance2 a span{
	display: block;
    height: 35px;
    line-height: 35px;
    color: #40ad45;
    font-size: 18px;
}
body .mainmenu li.balance3 a span{
	display: block;
    height: 35px;
    line-height: 35px;
    color: red;
    font-size: 18px;
}

body .mainmenu li.subject a span{
	background:#eee;
}
body .mainmenu li.query a span{
	background:#fc5366;
	color:#151515;
}
body .mainmenu li.audit a span{
	background:#8c67df;
	color:#151515;
}
body .mainmenu li.robot a span{
	background:#678ce1;
	color:#151515;
}
body .mainmenu li.api a span{
	background:#14c760;
	color:#151515;
}
body .mainmenu li.pool a span{
	background:#84d018;
	color:#151515;
}
</style>
<script type="text/javascript" src="${path}js/jquery-1.10.2.min.js"></script>
</head>
<body class="mWrap" style="background:#f7f3f3">
<header class="topHd">
	<a href="javascript:void(0);" onClick="history.back();return false;" class="back">返回</a>
	<a href="${path}app/appMain/myMain.htm" class="home">首页</a>
	<span>API</span>
</header>
<ul class="mainmenu" style="margin-top: 15px;">
	<li><a href="${path}api/idCard.htm" ><b><img src="${path}image/myMain/tb01.png" /></b><span>身份证信息查询</span></a></li>
	<li><a href="${path}api/callerIdTelephone.htm" ><b><img src="${path}image/myMain/tb02.png" /></b><span>手机固话来电显示</span></a></li>
	<li><a href="${path}api/mobile.htm" ><b><img src="${path}image/myMain/tb03.png" /></b><span>手机号归属地</span></a></li>
	<li><a href="javascript:void(0);" ><b><img src="${path}image/myMain/tb04.png" /></b><span>全国公交及路径规划</span></a></li>
	<li><a href="javascript:void(0);" ><b><img src="${path}image/myMain/tb05.png" /></b><span>长途汽车信息</span></a></li>
	<li><a href="javascript:void(0);" ><b><img src="${path}image/myMain/tb06.png" /></b><span>影视影讯检索</span></a></li>
	<li><a href="javascript:void(0);" ><b><img src="${path}image/myMain/tb06.png" /></b><span>暂无...</span></a></li>
	<li><a href="javascript:void(0);" ><b><img src="${path}image/myMain/tb07.png" /></b><span>暂无...</span></a></li>
	<li><a href="javascript:void(0);" ><b><img src="${path}image/myMain/tb08.png" /></b><span>暂无...</span></a></li>     	 
</ul>
<c:set var="currIndex" value="0"></c:set>
<%@ include file="/WEB-INF/jsp/public/footer.jsp"%>
</body>
</html>