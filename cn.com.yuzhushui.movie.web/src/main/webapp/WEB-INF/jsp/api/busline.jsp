<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/public/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>全国公交及路径规划查询</title>
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
		<span>全国公交及路径规划查询</span>
	</header>
		<div class="results" id="results" style="overflow:scroll;"></div>
		<div class="content"><input name="city" style="width: 100%;height: 100%;" placeholder="输入要查询的城市" maxlength="20" size="20" id="city"/></div>
		<div class="content"><input name="bus" style="width: 100%;height: 100%;" placeholder="输入要查询的公交" maxlength="10" size="10" id="bus"/></div>
		<div class="buttons" style="position:fixed;bottom:0.2rem; width: 100%;">
			<button class="btnQuery button_open">查询</button>
		</div>
</body>
<script type="text/javascript">
		var start=false;
		$(function(){
			$("#results").append("<div style='margin: 7px 9px;font-size: 15px;font-family: initial;font-weight: bold;'>您好，请输入要查询的城市。</div>");
			$(".btnQuery").click(function(){
				if(start) return false;
				var city=$("#city").val();
				var bus=$("#bus").val();
				if(city==""){
					layer.msg("要查询的城市不能为空.");
					return false;
				}
				if(bus==""){
					layer.msg("要查询的公交不能为空.");
					return false;
				}
				$("#results").append("<div style='background: rgba(7, 70, 255, 0.29);font-size: 19px;margin-top: 1px; margin-bottom: 4px;font-family: monospace;'>【城市:"+city+"，公交:"+bus+"】</div>");
				$.ajax({
		            type: "POST",
		            url: "${path}api/getBusline.json",
					  data: {city:city,bus:bus},
					  beforeSend: function() { 
						  $("#city").val("");
						  $("#bus").val("");
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
		            		  var buslineDatas=entity.buslineDatas;
		            		  var busline=buslineDatas[0];
		            		  
		            		  var name=busline.name;//１５６路（翠苑四区－民心路新业路口）
		            		  var company=busline.company;//杭州市公共交通集团有限公司
		            		  var total_price=busline.total_price;//2.000000
		            		  
		            		  $("#results").append("<div style='background: rgba(101, 255, 7, 0.51);font-size: 16px;margin-top: 4px; margin-bottom: 1px;'>公交名称："+name+"</div>");
		            		  $("#results").append("<div style='background: rgba(101, 255, 7, 0.51);font-size: 16px;margin-top: 4px; margin-bottom: 1px;'>公司名称："+company+"</div>");
		            		  $("#results").append("<div style='background: rgba(101, 255, 7, 0.51);font-size: 16px;margin-top: 4px; margin-bottom: 1px;'>票价："+total_price+"</div>");
		            		  
		            		  var stationdesList=busline.stationdesList;//站点列表
		            		  
		            		  var spans="";
		            		  
		            		  var style='font-size: 16px;font-family: initial;color: #00d4cc;';
		            		  
		            		  var direction='font-size: 18px;font-family: initial;color: rgba(255, 0, 0, 0.48);';
		            		  
		            		  $.each(stationdesList,function(index,obj){
		            			  if(index==stationdesList.length-1){
		            				  spans+="<span style='"+style+"'>"+obj.name+"</span>";
		            			  }else{
		            				  spans+="<span style='"+style+"'>"+obj.name+"</span>";
		            				  spans+="<span style='"+direction+"'>↔</span>"
		            			  }
		            		  });
		            		  
		            		  $("#results").append("<div style='border: 1px solid #8BC34A;'>"+spans+"</div>");
		            		  
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