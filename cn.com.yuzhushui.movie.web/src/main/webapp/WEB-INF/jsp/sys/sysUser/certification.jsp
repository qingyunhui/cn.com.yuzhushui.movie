<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
	.main .item-captcha #captcha-imgs{
		border-left: 1px solid #d7d7d7;
	    padding-left: 5px;
	    position: absolute;
	    right: 0;
	    top: 2px;
	}
	.buttons button{
		width: 100%;
	    border: 1px solid #8c8787;
	    height: 40px;
	    margin: auto;
	}
	.button_open{
	    background: #299fec;
	}
</style>
<%@ include file="/WEB-INF/jsp/public/head.jsp"%>
<script>
$(window).load(function() {
	$("#status").fadeOut();
	$("#preloader").delay(350).fadeOut("slow");
})
</script>
</head>

<body>
<div class="mobile">
	<!--页面加载 开始-->
  <div id="preloader">
    <div id="status">
      <p class="center-text"><span>拼命加载中···</span></p>
    </div>
  </div>
  <!--页面加载 结束--> 
  <!--header 开始-->
  <header>
    <div class="header"> <a class="new-a-back" href="${path}app/appMain/myMain.htm"> <span><img src="${path}image/login/iconfont-fanhui.png"></span> </a>
      <h2>个人信息认证</h2>
      </div>
  </header>
  <!--header 结束-->
  
  <div class="w main">
 		<div class="item item-username">
         <input class="txt-input txt-username" type="text" value="${empty entity.mobilephone?entity.name:entity.mobilephone}" readonly="readonly">
         <b class="input-close" style="display: none;"></b> 
       </div>
 		<div class="item item-username">
         <input class="txt-input txt-username" type="text" value="${entity.email}" readonly="readonly">
         <b class="input-close" style="display: none;"></b> 
       </div>
       <div class="item item-username">
         <input id="name" class="txt-input txt-username" type="text"  maxlength="5" size="5" placeholder="请输入姓名" name="name">
         <b class="input-close" style="display: none;"></b> 
       </div>
       <div class="item item-username">
         <input id="IDCard" class="txt-input txt-username" type="text"  maxlength="19" size="19" placeholder="请输入身份证号" name="IDCard">
         <b class="input-close" style="display: none;"></b> 
       </div>
       <div class="buttons" id="certificationDiv" style="width: 100%;">
       	<button class="btnQuery button_open" id="certificationBtn">个人信息认证</button>
       </div>
  </div>
	
</div>
	<script type="text/javascript">
		var start=false;
		$(function(){
			$("#certificationBtn").click(function(){
				if(start) return false;
				var name=$("#name").val();
				if(name==""){
					layer.tips('要认证的姓名不能为空.','#name', {tips: 1});
					return false;
				}
				var IDCard=$("#IDCard").val();
				if(IDCard==""){
					layer.tips("要认证的身份证不能为空.","#IDCard",{tips:1});
					return false;
				}
				$.ajax({
		            type: "POST",
		            url: "${path}sys/sysUser/doCertification.json",
					  data: {name:name,IDCard:IDCard},
					  beforeSend: function() { 
						  startTimer();
						  $("#certificationBtn").removeClass("button_open")
					  },
					  error:function(){
						  $("#certificationBtn").html("查询");
						  start=false;
						  $("#certificationBtn").addClass("button_open")
					  },
		              success: function(result) {
		            	  var datas=result.data;
		            	  if(datas && datas.success_code==10000){
		            		  layer.msg(result.msg);
		            		  location.href='${path}'+datas.url;
		            	  }else{
		            		  layer.msg(result.msg);
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
			        $("#certificationBtn").html("查询");
			        $("#certificationBtn").addClass("button_open")
			        second=10;
			        start=false;
			    } else {
			  	  second--;
			  	  $("#certificationBtn").html(second+"s");
			  	  $("#certificationBtn").removeClass("button_open")
			    }
			}
			
			function clearTimer(){
				window.clearInterval(timer);//停止计时器
				$("#certificationBtn").html("查询");
				$("#certificationBtn").addClass("button_open")
		        start=false;
			}
		});
	</script>
</body>
</html>
