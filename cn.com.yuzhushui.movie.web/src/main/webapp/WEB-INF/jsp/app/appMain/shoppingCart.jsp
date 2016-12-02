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
		<a href="javascript:history.go(-1);"><img src="${basePath}image/bg_info3.jpg"></a>
	</div>
	<div id="content">
	
		<div class="info_head info_light">
			<div class="info_card">
				<a href="content.html">
				<i><img src="${basePath}image/8.jpg"></i>
				<h1>诺基亚 Lumia 520 超低价格，不要错过哦！wp8超值入门</h1>
				</a>
			</div>
			<div class="info_card_price">
				<a href="cart.html" class="left fb f14 red">￥2980.00</a>
				<a href="#" class="right"><em class="no">删</em></a>
			</div>
		</div>
		<div class="info_head info_light">
			<div class="info_card">
				<a href="content.html">
				<i><img src="${basePath}image/9.jpg"></i>
				<h1>诺基亚 Lumia 520 超低价格，不要错过哦！wp8超值入门</h1>
				</a>
			</div>
			<div class="info_card_price">
				<a href="cart.html" class="left fb f14 red">￥2980.00</a>
				<a href="#" class="right"><em class="no">删</em></a>
			</div>
		</div>
		<div class="info_head info_light">
			<div class="info_card">
				<a href="content.html">
				<i><img src="${basePath}image/10.jpg"></i>
				<h1>诺基亚 Lumia 520 超低价格，不要错过哦！wp8超值入门</h1>
				</a>
			</div>
			<div class="info_card_price">
				<a href="cart.html" class="left fb f14 red">￥2980.00</a>
				<a href="#" class="right"><em class="no">删</em></a>
			</div>
		</div>
		
		
		<div class="box_exp info_light">
			<div class="info_integral">
				<span class="titles"><i class="icon-bookmark-empty"></i>总计 - ￥1680元</span>
			</div>
		</div>
		
		<div style="margin:8px 0;"><a href="cart_buy.html" class="button button-block button-rounded button-caution button-large"><i class="icon-shopping-cart"></i>&nbsp;&nbsp;立即结算</a></div>
		
		
		<div style="padding:15px;"></div>
	</div>
	
	<!-- footer页 -->
	<c:set var="currIndex" value="3"></c:set>
	<%@ include file="/WEB-INF/jsp/public/footer.jsp"%>

</div>

</body>
</html>