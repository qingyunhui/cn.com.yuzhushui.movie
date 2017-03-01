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

<div id="_centent">
<header>
	<div class="rt-bk">
		<i class="bk"></i>
		<p>返回</p>
	</div>
	<div class="top-name"><p>个人中心</p></div>
</header>

<div class="head">
	<div class="head-img">
    	<img src="${path}image/myself/head-img.png">
    </div>
    <div class="head-dsb">
    	<p class="dsb-name">--凌乱</p>
        <p class="dsb-id">ID  1271543621</p>
    </div>
</div>

<div class="nav">
	<ul>
    	<li>
        	<i class="idt"></i>
            <p>订单</p>
        </li>
    	<li class="pt-line">
        	<i class="clt"></i>
            <p>收藏</p>
        </li>
    	<li>
        	<i class="rcm"></i>
            <p>推荐</p>
        </li>
    </ul>
</div>

<section class="mt-1"> 
    <div class="ps-lt">
        <div class="lt-dsb">
            <p>修改个人资料</p>
            <i class="arr-right"></i>
        </div>
        <div class="lt-dsb cl-bb">
            <p>修改密码</p>
            <i class="arr-right"></i>
        </div>
    </div>
</section>

<section class="mt-2"> 
    <div class="ps-lt">
        <div class="lt-dsb cl-bb">
            <p>声音推送通知</p>
            <i class="check-on"></i>
        </div>
    </div>
</section>

<section class="mt-3"> 
    <div class="ps-lt">
        <div class="lt-dsb">
            <p>猜你喜欢</p>
            <i class="arr-right"></i>
        </div>
    </div>
    <div class="ps-lt">
        <div class="lt-dsb">
            <p>附近热门</p>
            <i class="arr-right"></i>
        </div>
    </div>
    <div class="ps-lt">
        <div class="lt-dsb">
            <p>推荐商家</p>
            <i class="arr-right"></i>
        </div>
    </div>
    <div class="ps-lt">
        <div class="lt-dsb cl-bb">
            <p>设置</p>
            <i class="arr-right"></i>
        </div>
    </div>
</section>

<div class="jg"></div>
</div>

<c:set var="currIndex" value="4"></c:set>
<%@ include file="/WEB-INF/jsp/public/footer.jsp"%>

<script>
	(function (doc, win) {
	  var docEl = doc.documentElement,
		resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
		recalc = function () {
		  var clientWidth = docEl.clientWidth;
		  if (!clientWidth) return;
		  docEl.style.fontSize = 100 * (clientWidth / 750) + 'px';
		};

	  if (!doc.addEventListener) return;
	  win.addEventListener(resizeEvt, recalc, false);
	  doc.addEventListener('DOMContentLoaded', recalc, false);
	})(document, window);
</script>
<script type="text/javascript">
	$('.check-on').click(function(){
		$(this).toggleClass('check-off');
		})
</script>
</body>
</html>