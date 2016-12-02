<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/public/head.jsp"%>
</head>
<body ondragstart="return false;" onselectstart="return false;" oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onbeforecopy="return false" oncopy=document.selection.empty() onselect=document.selection.empty()> 
<div id="page">
	<div class="banner">
		<ul>
			<li style="background-image: url('${basePath}image/4.jpg');">1</li>
			<li style="background-image: url('${basePath}image/3.jpg');">2</li>
			<li style="background-image: url('${basePath}image/1.jpg');">3</li>
			<li style="background-image: url('${basePath}image/2.jpg');">4</li>
		</ul>
	</div>
	<div id="content">
		<div class="box_exp info_light">
			<div class="info_integral">
				<span class="title"><i class="icon-bookmark-empty"></i>促销商品</span>
			</div>
			<div style="display: block; overflow: hidden; opacity: 1;">
				<div class="info_child">
					<ul>
						<li style="width:33%"><div class="line3"><a href="content.html"><i><img src="${basePath}image/7.jpg"></i><span>小熊真皮箱包</span><em>198.00</em></a></div></li>
						<li style="width:33%"><div class="line3"><a href="content.html"><i><img src="${basePath}image/5.jpg"></i><span>三星NOTE3</span><em>198.00</em></a></div></li>
						<li style="width:33%"><a href="content.html"><i><img src="${basePath}image/6.jpg"></i><span>索尼T808-9</span><em>198.00</em></a></li>
						<li style="width:33%"><div class="line3"><a href="content.html"><i><img src="${basePath}image/7.jpg"></i><span>小熊真皮箱包</span><em>198.00</em></a></div></li>
						<li style="width:33%"><div class="line3"><a href="content.html"><i><img src="${basePath}image/5.jpg"></i><span>三星NOTE3</span><em>198.00</em></a></div></li>
						<li style="width:33%"><a href="content.html"><i><img src="${basePath}image/6.jpg"></i><span>索尼T808-9</span><em>198.00</em></a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="box_exp">
			<ul>
				<li style="width:25%"><div class="line"><a href="class.html"><i class="icon-bell-alt yellow"></i><span>促销卖场</span></a></div></li>
				<li style="width:25%"><div class="line"><a href="class.html"><i class="icon-shopping-cart red"></i><span>包邮商品</span></a></div></li>
				<li style="width:25%"><div class="line"><a href="class.html"><i class="icon-time blue"></i><span>团购商城</span></a></div></li>
				<li style="width:25%"><div class="line2"><a href="class.html"><i class="icon-trophy pink"></i><span>品牌特卖</span></a></div></li>
				<li style="width:25%"><div class="line3"><a href="class.html"><i class="icon-truck greens"></i><span>订单查询</span></a></div></li>
				<li style="width:25%"><div class="line3"><a href="class.html"><i class="icon-mobile-phone blue"></i><span>手机充值</span></a></div></li>
				<li style="width:25%"><div class="line3"><a href="class.html"><i class="icon-gift yellow"></i><span>购买彩票</span></a></div></li>
				<li style="width:25%"><a href="consumption.html"><i class="icon-info-sign green"></i><span>意见反馈</span></a></li>
			</ul>
		</div>	
		
		<div style="padding:15px;"></div>
	</div>
	<!-- footer页 -->
	<c:set var="currIndex" value="4"></c:set>
	<%@ include file="/WEB-INF/jsp/public/footer.jsp"%>
</div>

<script>
$(document).ready(function() {
	
	var str='${basePath}';
	var path2 = "${path}";
	
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