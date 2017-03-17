<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/public/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>手机归属地</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta charset="utf-8">
<link href="${path}css/sysBills/m.common.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.results{
    width: 99%;
    border: 1px solid #8c8787;
    margin: auto;
    height:300px;
}
.content{
    width: 99%;
    border: 1px solid #8c8787;
    margin: auto;
    border-top: 0;
    font-size: 16px;
    height:50px; 
}
.buttons button{
	width: 100%;
    border: 1px solid #8c8787;
    height: 40px;
    margin: auto;
}
.button_open{
    background: #f60;
}
body{height:100%; overflow:hidden; margin:0px; padding:0px;}
</style>
<body class="mWrap" style="background:#f7f3f3">
	<header class="topHd">
		<a href="javascript:void(0);" onClick="history.back();return false;" class="back">返回</a>
		<a href="${path}app/appMain/myMain.htm" class="home">首页</a>
		<span>手机归属地</span>
	</header>
	  <div style="position: absolute;top: 3.9em;bottom: 40px;width: 100%;">
		<div class="results" id="results" style="overflow:scroll;height: 90%;"></div>
		<div class="content" style="height: 8%;"><input name="content" style="width: 100%;height: 100%;" placeholder="输入要查询的手机号" maxlength="30" size="30" id="content"/></div>
	  </div>
		<div class="buttons" style="position:fixed;bottom:0.2rem; width: 100%;">
			<button class="btnQuery button_open">查询</button>
		</div>
</body>
<script type="text/javascript">
		var start=false;
		$(function(){
			$("#results").append("<div style='margin: 7px 9px;font-size: 15px;font-family: initial;font-weight: bold;'>请输入要查询的手机号。</div>");
			$("#content").select();
			$(".btnQuery").click(function(){
				if(start) return false;
				var value=$("#content").val();
				if(value==""){
					layer.msg("要查询的手机号不能为空.");
					return false;
				}
				$("#results").append("<div style='background: rgba(7, 70, 255, 0.29);font-size: 19px;margin-top: 1px; margin-bottom: 4px;font-family: monospace;'>手机号:"+value+"</div>");
				$.ajax({
		            type: "POST",
		            url: "${path}api/getMobile.json",
					  data: {mobile:value},
					  beforeSend: function() { 
						  $("#content").val("");
						  startTimer();
						  $(".buttons button").removeClass("button_open")
					  },
					  error:function(){
						  $(".btnQuery").html("查询");
						  start=false;
						  $(".buttons button").addClass("button_open")
					  },
		              success: function(result) {
		            	  var datas=result.data;
		            	  if(datas && datas.success_code==10000){
		            		  var entity=datas.entity;
		            		  $("#results").append("<div style='background: rgba(101, 255, 7, 0.51);font-size: 16px;margin-top: 4px; margin-bottom: 1px;'>所属省份:"+entity.province+"</div>");
		            		  $("#results").append("<div style='background: rgba(101, 255, 7, 0.51);font-size: 16px;margin-top: 4px; margin-bottom: 1px;'>所属城市:"+entity.city+"</div>");
		            		  $("#results").append("<div style='background: rgba(101, 255, 7, 0.51);font-size: 16px;margin-top: 4px; margin-bottom: 1px;'>所属区号:"+entity.areacode+"</div>");
		            		  $("#results").append("<div style='background: rgba(101, 255, 7, 0.51);font-size: 16px;margin-top: 4px; margin-bottom: 1px;'>所属运营商:"+entity.company+"</div>");
		            		  $("#results").append("<div style='background: rgba(101, 255, 7, 0.51);font-size: 16px;margin-top: 4px; margin-bottom: 1px;'>卡类型:"+entity.card+"</div>");
		            	  }else{
		            		  $("#results").append("<div style='background: rgba(255, 7, 74, 0.68);font-size: 16px;margin-top: 4px; margin-bottom: 1px;'>"+result.msg+"</div>");
		            	  }
					  }
		        });
			});
			
			/**定时器**/
			var timer; //timer定时函数
			var second = 10;//10秒查询一次
			function startTimer() {
				start=true;
			    timer = window.setInterval(timerHandle, 1000); //启动计时器，1秒执行一次
			}
			//timer处理函数
			function timerHandle() {
			    if (second == 1) {
			        window.clearInterval(timer);//停止计时器
			        $(".btnQuery").html("查询");
			        $(".buttons button").addClass("button_open")
			        second=10;
			        start=false;
			    } else {
			  	  second--;
			  	  $(".btnQuery").html(second+"s");
			  	  $(".buttons button").removeClass("button_open")
			    }
			}
			
			function clearTimer(){
				window.clearInterval(timer);//停止计时器
				$(".btnQuery").html("查询");
				$(".buttons button").addClass("button_open")
		        start=false;
			}
		});
	</script>
</html>