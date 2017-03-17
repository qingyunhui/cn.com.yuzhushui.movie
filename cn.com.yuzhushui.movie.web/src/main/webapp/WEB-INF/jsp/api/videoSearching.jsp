<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/public/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>影视影讯检索</title>
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
		<span>影视影讯检索</span>
	</header>
		<div class="results" id="results" style="overflow:scroll;"></div>
		<div class="content"><input name="qname" style="width: 100%;height: 100%;" placeholder="输入要查询的影视" maxlength="20" size="20" id="qname"/></div>
		<div class="buttons" style="position:fixed;bottom:0.2rem; width: 100%;">
			<button class="btnQuery button_open">查询</button>
		</div>
</body>
<script type="text/javascript">
		var start=false;
		$(function(){
			$("#results").append("<div style='margin: 7px 9px;font-size: 15px;font-family: initial;font-weight: bold;'>您好，请输入要查询的影视名称。</div>");
			$(".btnQuery").click(function(){
				if(start) return false;
				var qname=$("#qname").val();
				if(qname==""){
					layer.msg("要查询的影视不能为空.");
					return false;
				}
				$("#results").append("<div style='background: rgba(7, 70, 255, 0.29);font-size: 19px;margin-top: 1px; margin-bottom: 4px;font-family: monospace;'>【影视:"+qname+"】</div>");
				$.ajax({
		            type: "POST",
		            url: "${path}api/getVideoSearching.json",
					  data: {qname:qname},
					  beforeSend: function() { 
						  $("#qname").val("");
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
		            		  var videoData=entity.searchingData;//影视信息
		            		  
		            		  var act=videoData.act;//主演列表
		            		  var area=videoData.area;//地区
		            		  var desc=videoData.desc;//描述
		            		  var dir=videoData.dir;//导演
		            		  var tag=videoData.tag;//标签
		            		  var title=videoData.title;//标题
		            		  var year=videoData.year;//年份
		            		  
		            		  $("#results").append("<div style='background: rgba(101, 255, 7, 0.51);font-size: 16px;margin-top: 4px; margin-bottom: 1px;'>"+act+"</div>");
		            		  $("#results").append("<div style='background: rgba(101, 255, 7, 0.51);font-size: 16px;margin-top: 4px; margin-bottom: 1px;'>"+area+"</div>");
		            		  $("#results").append("<div style='background: rgba(101, 255, 7, 0.51);font-size: 16px;margin-top: 4px; margin-bottom: 1px;'>"+desc+"</div>");
		            		  $("#results").append("<div style='background: rgba(101, 255, 7, 0.51);font-size: 16px;margin-top: 4px; margin-bottom: 1px;'>"+dir+"</div>");
		            		  $("#results").append("<div style='background: rgba(101, 255, 7, 0.51);font-size: 16px;margin-top: 4px; margin-bottom: 1px;'>"+tag+"</div>");
		            		  $("#results").append("<div style='background: rgba(101, 255, 7, 0.51);font-size: 16px;margin-top: 4px; margin-bottom: 1px;'>"+title+"</div>");
		            		  $("#results").append("<div style='background: rgba(101, 255, 7, 0.51);font-size: 16px;margin-top: 4px; margin-bottom: 1px;'>"+year+"</div>");
		            		  
		            		  var videoRecList=videoData.videoRecList;//相关影视
		            		  
							  var style='font-size: 16px;font-family: initial;color: #00d4cc;word-wrap: break-word; word-break: normal;display:block;';
							  
		            		  var lineStyle="height:1px; width:100%;background:#b9b5b5;overflow:hidden;";
		            		  
		            		  var spans="";
		            		    
		            		  $.each(videoRecList,function(index,obj){
		            			  spans+="<span style='"+style+"'>影视标题："+obj.title+"</span>";
		            			  spans+="<span style='"+style+"'>影视封面<img src='"+obj.cover+"' style='width: 75%;height: 50%;'></img></span>";
		            			  spans+="<span style='"+style+"'><a href='"+obj.detail_url+"' style='"+style+"'>影视播放连接"+obj.detail_url+"</a></span>";
		            			  if(index!=videoRecList.length-1){
		            				  spans+="<div style='"+lineStyle+"'></div>";
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