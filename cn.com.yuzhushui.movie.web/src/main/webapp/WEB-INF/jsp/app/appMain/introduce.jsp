<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn" class="no-js">
	<head>
		<meta http-equiv="Content-Type">
		<meta content="text/html; charset=utf-8">
		
		<meta charset="utf-8">
		<title>找的就是你</title>		
		<meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="format-detection" content="telephone=no">
		<meta name="format-detection" content="email=no">        
		<link rel="stylesheet" type="text/css" href="${path}css/introduce/reset.css" />
		<link rel="stylesheet" type="text/css" href="${path}css/introduce/index.css" />
		<link rel="stylesheet" type="text/css" href="${path}css/introduce/animations.css" />
        <link rel="stylesheet" type="text/css" href="${path}css/introduce/load.css" /> 
	</head>
	<body>
    <div id="loading">
     	<div class="spinner">
  			<div class="double-bounce1"></div>
  			<div class="double-bounce2"></div>
		</div>
	</div>
	<div id="content" style="display:none">
			<div class="page page-1-1 page-current">
				<div class="wrap">
					<img class="img_1 pt-page-moveFromTop" src="${path}image/introduce/t1-text.png" />
                    <img class="img_2 pt-page-moveCircle" src="${path}image/introduce/t1-yuan.png" />
					<img class="img_3 pt-page-moveIconUp" src="${path}image/introduce/icon_up.png" />
				</div>
			</div>
			<div class="page page-2-1 hide">
				<div class="wrap">
					<img class="img_1 hide pt-page-moveFromLeft" src="${path}image/introduce/t2-3.png" />
 					<img class="img_3 hide pt-page-moveCircle" src="${path}image/introduce/t2-2.png" />
                    <img class="img_6 pt-page-moveIconUp" src="${path}image/introduce/icon_up.png" />
 				</div>
			</div>
            <div class="page page-3-1 hide">
				<div class="wrap">
					<img class="img_1 hide pt-page-moveCircle" src="${path}image/introduce/t9-1.png" /> 
					<img class="img_2 pt-page-moveFromBottom hide" src="${path}image/introduce/t9-2.png" /> 
					<img class="img_6 hide pt-page-moveIconUp" src="${path}image/introduce/icon_up.png" /> 
				</div>
			</div>
             <div class="page page-4-1 hide">
				<div class="wrap">
					<img class="img_1 hide pt-page-moveCircle" src="${path}image/introduce/t10-1.png" /> 
					<div class="text">
                    	<h2 style="padding:5px; margin:0px">微笑吧科技有限公司</h2> 
				                        电话：<a href="javascript:void(0);">1866****640</a><br> 
				                        邮箱：<a href="mailto:280672161@qq.com">280672161@qq.com</a><br> 
                    </div>
                     <img onclick="shareDialog()" class="img_3 pt-page-moveFromBottom hide" src="${path}image/introduce/t10-3.png" />
				</div>
			</div> 
    </div>
	<div id="audiocontainer"></div>
	<div id="textsuper">
		<div id="textsub"><img id="fontimg" /></div>
    </div>
    <div id="share" onClick="closeDialog()">
    	<div class="shareImg"></div>
    </div>		
    <script type="text/javascript">
	</script>
    <script src="${path}js/introduce/zepto.min.js"></script>
	<script src="${path}js/introduce/touch.js"></script>
	<script src="${path}js/introduce/index.js"></script>
    <script type="text/javascript">
		document.onreadystatechange = loading; 
		function loading(){
			if(document.readyState == "complete")
			{ 
				$("#loading").hide();
				$("#content").show();
			}
		}
		function shareDialog(){
			location.href="${path}app/appMain/login.htm";
			 //$("#share").show();			
		}
		function closeDialog(){
			$("#share").hide();
		}
	</script>
  </body>
</html>