<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/public/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>天气气象信息查询</title>
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
		<span>天气气象信息查询</span>
	</header>
	  <div style="position: absolute;top: 3.9em;bottom: 40px;width: 100%;">
		<div class="results" id="results" style="overflow:scroll;height: 90%;"></div>
		<div class="content" style="height: 8%;"><input name="location" style="width: 100%;height: 100%;" placeholder="输入要查询的城市" maxlength="20" size="20" id="location"/></div>
	  </div>
		<div class="buttons" style="position:fixed;bottom:0.2rem; width: 100%;">
			<button class="btnQuery button_open">查询</button>
		</div>
</body>
<script type="text/javascript">
		var start=false;
		$(function(){
			$("#results").append("<div style='margin: 7px 9px;font-size: 15px;font-family: initial;font-weight: bold;'>请输入要查询的城市名称。</div>");
			$("#location").select();
			$(".btnQuery").click(function(){
				if(start) return false;
				var location=$("#location").val();
				if(location==""){
					layer.msg("要查询的城市不能为空.");
					return false;
				}
				$("#results").append("<div style='background: rgba(7, 70, 255, 0.29);font-size: 19px;margin-top: 1px; margin-bottom: 4px;font-family: monospace;'>【城市:"+location+"】</div>");
				$.ajax({
		            type: "POST",
		            url: "${path}api/getWeather.json",
					  data: {location:location},
					  beforeSend: function() { 
						  $("#location").val("");
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
		            		  var date=entity.date;//当前查询的日期
		            		  $("#results").append("<div style='background: rgba(101, 255, 7, 0.51);font-size: 16px;margin-top: 4px; margin-bottom: 1px;'>日期："+date+"</div>");
		            		  var spans="";
		            		  var city=entity.city;//城市气象数据
		            		  var currentCity=city.currentCity;//当前城市
		            		  $("#results").append("<div style='background: rgba(101, 255, 7, 0.51);font-size: 16px;margin-top: 4px; margin-bottom: 1px;'>当前城市："+currentCity+"</div>");
		            		  var pm25=city.pm25;//pm指数
		            		  $("#results").append("<div style='background: rgba(101, 255, 7, 0.51);font-size: 16px;margin-top: 4px; margin-bottom: 1px;'>PM指数："+pm25+"</div>");
		            		  
		            		  var indexs=city.indexs;//指数[{xx},{xx}...];
		            		  var wealtherDates=city.wealtherDates;//未来几天的气象信息[{xx},{xx}...]
		            		  
							  var style='font-size: 16px;font-family: initial;color: #00d4cc;word-wrap: break-word; word-break: normal;display:block;';
							  
							  var wealtherStyle='font-size: 16px;font-family: initial;color: #0bd400;word-wrap: break-word; word-break: normal;display:block;';;
		            		  
		            		  var lineStyle="height:1px; width:100%;background:#b9b5b5;overflow:hidden;";
		            		  
		            		  var imgStyle="margin-top: 0.15rem;margin-right: 0.5rem;";
		            		  
		            		  var spanIndexs="";
		            		  $.each(indexs,function(index,obj){
		            			  spanIndexs+="<span style='"+style+"'>"+obj.tipt+":"+obj.zs+"</span>";
		            			  spanIndexs+="<span style='"+style+"'>"+obj.des+"</span>";
		            			  if(index!=indexs.length-1){
		            				  spanIndexs+="<div style='"+lineStyle+"'></div>";
		            			  }
		            		  });
		            		  
		            		  var spanWealthers="";
		            		  $.each(wealtherDates,function(index,obj){
		            			  spanWealthers+="<span style='"+wealtherStyle+"'>"+obj.date+"～"+obj.temperature+"</span>";
		            			  spanWealthers+="<span style='"+wealtherStyle+"'>"+obj.weather+"～"+obj.wind+"</span>";
		            			  var dayPictureUrl=obj.dayPictureUrl;
		            			  var nightPictureUrl=obj.nightPictureUrl;
		            			  if (dayPictureUrl != undefined){ 
		            				  spanWealthers+="<span style='"+imgStyle+"'><img src='"+dayPictureUrl+"'></img></span>";
		            			  }
		            			  if (nightPictureUrl != undefined){ 
		            				  spanWealthers+="<span style='"+imgStyle+"'><img src='"+nightPictureUrl+"'></img></span>";
		            			  }
		            			  if(index!=wealtherDates.length-1){
		            				  spanWealthers+="<div style='"+lineStyle+"'></div>";
		            			  }
		            		  });
		            		  
		            		  $("#results").append("<div style='border: 1px solid #8BC34A;'>"+spanIndexs+"</div>");
		            		  
		            		  $("#results").append("<div style='border: 1px solid #8BC34A;border-top: 0;'>"+spanWealthers+"</div>");
		            		  
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