<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/public/head.jsp"%>
</head>
<body ondragstart="return false;" onselectstart="return false;" oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onbeforecopy="return false" oncopy=document.selection.empty() onselect=document.selection.empty()> 
<div id="page">
	<div class="banners">
		<a href="javascript:history.go(-1);"><img src="${basePath}image/bg_info1.jpg"></a>
	</div>
	<div id="content">
		<div class="box_exp">
			<ul>
				<li style="width:25%"><div class="line"><a href="class.html"><i class="icon-bell-alt yellow"></i><span>促销卖场</span></a></div></li>
				<li style="width:25%"><div class="line"><a href="class.html"><i class="icon-shopping-cart red"></i><span>包邮商品</span></a></div></li>
				<li style="width:25%"><div class="line"><a href="class.html"><i class="icon-time blue"></i><span>团购商城</span></a></div></li>
				<li style="width:25%"><div class="line2"><a href="class.html"><i class="icon-trophy pink"></i><span>品牌特卖</span></a></div></li>
				<li style="width:25%"><div class="line3"><a href="class.html"><i class="icon-truck greens"></i><span>订单查询</span></a></div></li>
				<li style="width:25%"><div class="line3"><a href="class.html"><i class="icon-mobile-phone blue"></i><span>手机充值</span></a></div></li>
				<li style="width:25%"><div class="line3"><a href="class.html"><i class="icon-gift yellow"></i><span>购买彩票</span></a></div></li>
				<li style="width:25%"><a href="class.html"><i class="icon-info-sign green"></i><span>意见反馈</span></a></li>
			</ul>
		</div>	

		<div class="info_head info_light">
			<div class="info_card">
				<a href="class_1.html">
				<i><img src="${basePath}image/8.jpg"></i>
				<h1>服装鞋帽</h1>
				<span class="title">女装/男装/内衣</span>
				</a>
			</div>
			<div class="info_card">
				<a href="class_1.html">
				<i><img src="${basePath}image/9.jpg"></i>
				<h1>手机</h1>
				<span class="title">手机通讯/运营商/手机配件</span>
			</div>
			<div class="info_card">
				<a href="class_1.html">
				<i><img src="${basePath}image/10.jpg"></i>
				<h1>数码</h1>
				<span class="title">摄影摄像/数码配件/外设产品</span>
				</a>
			</div>
			<div class="info_card">
			
			
				<a href="class_1.html">
				<i><img src="${basePath}image/11.jpg"></i>
				<h1>电脑办公</h1>
				<span class="title">电脑整机/电脑配件/外设产品</span>
				</a>
			</div>
			<div class="info_card">
				<a href="class_1.html">
				<i><img src="${basePath}image/12.jpg"></i>
				<h1>护理化妆</h1>
				<span class="title">面部护理/身体护理/口腔护理</span>
				</a>
			</div>
			<div class="info_card">
			<a href="class_1.html">
				<i><img src="${basePath}image/13.jpg"></i>
				<h1>图书</h1>
				<span class="title">电子书/英文原版书/小说</span>
				</a>
			</div>
			<div class="info_card">
				<a href="class_1.html">
				<i><img src="${basePath}image/14.jpg"></i>
				<h1>家用电器</h1>
				<span class="title">大家电/生活电器/厨房电器</span>
				</a>
			</div>
		</div>
		
		<div style="padding:15px;"></div>
	</div>
	<!-- footer页 -->
	<c:set var="currIndex" value="2"></c:set>
	<%@ include file="/WEB-INF/jsp/public/footer.jsp"%>
</div>

<script>
$(document).ready(function() {
	$('.banner').unslider({
		arrows: true,
		fluid: true,
		dots: true
	});

	$("#content").accordion({
		alwaysOpen: false,
		autoheight: false,
		header: '.info_integral',
		clearStyle: true
	});
});
</script>
</body>
</html>