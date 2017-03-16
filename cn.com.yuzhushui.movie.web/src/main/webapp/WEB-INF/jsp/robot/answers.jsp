<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/public/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>机器人</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta charset="utf-8">
<link href="${path}css/sysBills/m.common.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.results{
    width: 100%;
    border: 1px solid #8c8787;
    height: 250px;
    margin: auto;
}
.content{
    width: 100%;
    border: 1px solid #8c8787;
    height: 90px;
    margin: auto;
    border-top: 0;
}
.buttons button{
	width: 50%;
    border: 1px solid #8c8787;
    height: 40px;
    margin: auto;
}
</style>
<body class="mWrap">
	<header class="topHd">
		<a href="javascript:void(0);" onClick="history.back();return false;" class="back">返回</a>
		<a href="${path}app/appMain/myMain.htm" class="home">首页</a>
		<span>机器人</span>
	</header>
	<form method="post" action="">
		<div class="results" id="results"></div>
		<input name="content" maxlength="30" size="30" id="content" class="content"/>
		<div class="buttons">
			<button class="btnSend">发送</button>
		</div>
	</form>
</body>
<script type="text/javascript">
		$(function(){
			$("#results").append("<div>您好，我是机器小蜜。</div>");
			$(".btnSend").click(function(){
				var value=$("#content").val();
				if(value==""){
					layer.msg("内容不能为空.");
					return false;
				}
				$("#results").append("<div>我问:"+value+"</div>");
				
				$.ajax({
		            type: "POST",
		            url: "${path}robot/robotAnswers.json",
					  data: {content:value},
					  beforeSend: function() {  },
					  error:function(){ layer.msg("系统错误.");},
		              success: function(result) {
		            	  var datas=result.data;
		            	  if(datas && datas.success_code==10000){
		            		  $("#results").append("<div>机器小蜜答:"+result.msg+"</div>");
		            	  }else{
		            		  $("#results").append("<div>机器小蜜答:"+result.msg+"</div>");
		            	  }
					  }
		        });
				
			});
		});
	</script>
</html>