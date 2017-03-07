<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
	.main .item .txt-input-email{
		background: none repeat scroll 0 0 #fff;
	    border: 1px solid #D1D1D1;
	    border-radius: 5px;
	    color: #252525;
	    font-size: 14px;
	    height: 44px;
	    width: 65%;
	}
	.ui-btn-lg-emailBtn {
	    cursor: pointer;
	    border-radius: 5px;
	    font-size: 14px;
	    height: 40px;
	    line-height: 40px;
	    width: 28%;
	    text-align: center;
	    text-decoration: none;
	    float:right;
	}
	.codeDisplay{
		background:#c8c7cc;
	}
</style>

<%@ include file="/WEB-INF/jsp/public/head.jsp"%>
<script>
$(window).load(function() {
	$("#status").fadeOut();
	$("#preloader").delay(350).fadeOut("slow");
	//
	var mesg = '${messages}';
	if(mesg != ''){
		layer.msg(mesg);
	}
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
    <div class="header"> <a class="new-a-back" href="javascript:history.back();"> <span><img src="${path}image/login/iconfont-fanhui.png"></span> </a>
      <h2>找回密码</h2>
      </div>
  </header>
  <!--header 结束-->
  
  <div class="w main">
  	<form id="frm_login" method="post" action="javascript:void(0);">
  		<div class="item item-username">
          <input id="account" class="txt-input txt-username" type="text" placeholder="请输入您的账号" name="account">
          <b class="input-close" style="display: none;"></b> 
        </div>
        <div class="item item-username">
          <input id="email" class="txt-input-email" type="text" placeholder="请输入注册时的邮箱" name="email">
          <b><input name="" type="button" id="CodeBtn" value="获取验证码"  class="ui-btn-lg-emailBtn ui-btn-primary" /></b> 
        </div>
      </form>
  </div>
  <div class="copyright">Copyright © 2011-2016 www.smiles8.top 版权所有.</div>
</div>
<script type="text/javascript" >
	var start=false;
	$(document).ready(function(){
		$("#CodeBtn").click(function(){
			if(start) return false;
			var accounts = $.trim($("#account").val());
			var email = $.trim($("#email").val());
			if(accounts == ''){
				layer.tips('请输入账号','#account', {tips: 1});
				return false;
			}else if(email == ''){
				layer.tips('请输入注册时的邮箱','#email', {tips: 1});
				return false;
			}
			var params="?account="+accounts;
			//发送ajax请求
			$.ajax({
	              type: "POST",
	              url: "${path}app/appMain/getCode.json",
				  data: {account:accounts,email:email},
				  beforeSend: function() { startTimer(); },
				  error:function(){ $(".getyzm").html("获取验证码");},
	              success: function(result) {
	            	  var datas=result.data;
	            	  if(datas && datas.success_code==10000){
	            		  //发送成功，跳转到修改密码页面。
	            		  layer.alert(result.msg, {
		            		  skin: 'layui-layer-molv' //样式类名
		            		  ,closeBtn: 0
		            		}, function(index){
		            			layer.close(index);
		            			location.href='${path}'+datas.url+params;
		            	   });
	            	  }else if(datas && datas.success_code==20000){
	            		  //5分钟前，已发送过一封邮件
	            		  layer.confirm(result.msg, function(index){
	            			  location.href='${path}'+datas.url+params;
							  layer.close(index);
						  }); 
	            	  }else{
	            		  layer.msg(result.msg);
	            	  }
				  }
	          });
		});
		/**定时器**/
		var timer; //timer定时函数
		var second = 60;//60秒发送一次
		function startTimer() {
			start=true;
			$("#Codebtn").addClass(".codeDisplay");
		    timer = window.setInterval(timerHandle, 1000); //启动计时器，1秒执行一次
		}
		//timer处理函数
		function timerHandle() {
		    if (second == 1) {
		        window.clearInterval(timer);//停止计时器
		        $("#CodeBtn").val("获取验证码");
		        second=60;
		        start=false;
		        $("#Codebtn").removeClass(".codeDisplay");
		    } else {
		  	  second--;
		  	  $("#CodeBtn").val(second+"s");
		    }
		}
	});
	
	//监控用户输入
	$(":input").bind('input propertychange', function() {
		if($(this).val()!=""){
			$(this).siblings(".input-close").show();
		}else{
			$(this).siblings(".input-close").hide();
		}
    });
</script> 
</body>
</html>
