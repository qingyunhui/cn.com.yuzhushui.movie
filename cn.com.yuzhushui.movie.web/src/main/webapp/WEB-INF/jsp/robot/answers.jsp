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
    height: 50px;
    margin: auto;
    border-top: 0;
    font-size: 16px;
}
.buttons button{
	width: 50%;
    border: 1px solid #8c8787;
    height: 40px;
    margin: auto;
}
.button_open{
    background: rgba(205, 220, 57, 0.63);
}

</style>
<body class="mWrap" style="background:#f7f3f3">
	<header class="topHd">
		<a href="javascript:void(0);" onClick="history.back();return false;" class="back">返回</a>
		<a href="${path}app/appMain/myMain.htm" class="home">首页</a>
		<span>机器人</span>
	</header>
		<div class="results" id="results"></div>
		<input name="content" maxlength="30" size="30" id="content" class="content"/>
		<div class="buttons">
			<button class="btnSend button_open">发送</button>
		</div>
</body>
<script type="text/javascript">
		var start=false;
		$(function(){
			$("#results").append("<div>您好，我是机器小蜜。</div>");
			$(".btnSend").click(function(){
				if(start) return false;
				var value=$("#content").val();
				if(value==""){
					layer.msg("内容不能为空.");
					return false;
				}
				$("#results").append("<div style='background: rgba(7, 70, 255, 0.15);font-size: 19px;margin-top: 1px; margin-bottom: 1px;font-family: monospace;'>我问:"+value+"</div>");
				$.ajax({
		            type: "POST",
		            url: "${path}robot/robotAnswers.json",
					  data: {content:value},
					  beforeSend: function() { 
						  $("#content").val("");
						  startTimer();
						  $(".buttons button").removeClass("button_open")
					  },
					  error:function(){
						  $(".btnSend").html("发送");
						  start=false;
						  $(".buttons button").addClass("button_open")
					  },
		              success: function(result) {
		            	  var datas=result.data;
		            	  if(datas && datas.success_code==10000){
		            		  $("#results").append("<div style='background: rgba(101, 255, 7, 0.15);font-size: 16px;margin-top: 1px; margin-bottom: 1px;'>机器小蜜答:"+result.msg+"</div>");
		            	  }else{
		            		  $("#results").append("<div style='background: rgba(101, 255, 7, 0.15);font-size: 16px;margin-top: 1px; margin-bottom: 1px;'>机器小蜜答:"+result.msg+"</div>");
		            	  }
					  }
		        });
			});
			
			/**定时器**/
			var timer; //timer定时函数
			var second = 8;//8秒发送一次
			function startTimer() {
				start=true;
			    timer = window.setInterval(timerHandle, 1000); //启动计时器，1秒执行一次
			}
			//timer处理函数
			function timerHandle() {
			    if (second == 1) {
			        window.clearInterval(timer);//停止计时器
			        $(".btnSend").html("发送");
			        $(".buttons button").addClass("button_open")
			        second=8;
			        start=false;
			    } else {
			  	  second--;
			  	  $(".btnSend").html(second+"s");
			  	  $(".buttons button").removeClass("button_open")
			    }
			}
			
			function clearTimer(){
				window.clearInterval(timer);//停止计时器
				$(".btnSend").html("发送");
				$(".buttons button").addClass("button_open")
		        start=false;
			}
		});
	</script>
</html>