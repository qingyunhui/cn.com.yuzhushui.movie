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

		<section class="mt-3"> 
		    
		</section>
		
	</div>
	<footer>
		<div class="mune">
	    	<img src="${path}image/myself/1.png">
	        <p>首页2</p>
	    </div>
		<div class="mune">
	    	<img src="${path}image/myself/2.png">
	        <p>商家2</p>
	    </div>
		<div class="mune">
	    	<img src="${path}image/myself/3.png">
	        <p>申请加盟2</p>
	    </div>
		<div class="mune">
	    	<img src="${path}image/myself/4.png">
	        <p>个人中心2</p>
	    </div>    
	</footer>

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