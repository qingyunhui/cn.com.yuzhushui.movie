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
    <div class="header"> <a class="new-a-back" href="javascript:history.back();"> <span><img src="${path}image/login/iconfont-fanhui.png"></span> </a>
      <h2>一起微笑吧·注册</h2>
      </div>
  </header>
  <!--header 结束-->
  
  <div class="w main">
  	<form id="frm_login" method="post">
        <div class="item item-username">
          <input id="account" class="txt-input txt-username" type="text"  maxlength="15" size="15" placeholder="请输入账号" name="account">
          <b class="input-close" style="display: none;"></b> 
        </div>
        <div class="item item-password">
          <input id="password" class="txt-input txt-password ciphertext"  maxlength="15" size="15" type="password" placeholder="请输入密码" name="password" style="display: inline;">
        </div>
        <div class="item item-captcha">
          <div class="input-info">
            <input id="email" class="txt-input txt-captcha" type="text" name="email" placeholder="请输入邮箱号" autocomplete="off" maxlength="28" size="28">
            <b id="validateCodeclose" class="input-close" onClick="validateCodeclose();" style="display: block; margin-right:15px;"></b> <span id="captcha-imgs" class="codeBtns"> <input type="button" id="CodeBtn" class="ui-btn-lg ui-btn-primary" value="获取验证码" /> </span> </div>
          	<div class="item item-username">
          		<input id="code" class="txt-input txt-username" type="text"  maxlength="5" size="5" placeholder="请输入验证码" name="code">
        	</div>
          <div class="err-tips"> 注册即视为同意 <a target="_blank" href="javascript:void(0);">用户服务协议</a> </div>
        </div>
        <div class="ui-btn-wrap"> <a class="ui-btn-lg ui-btn-primary" id="registerBtn" href="javascript:void(0);">用户注册</a> </div>
        <div class="ui-btn-wrap"> <a class="ui-btn-lg ui-btn-danger" href="${path}app/appMain/login.htm">已有账号？立即登录</a> </div>
      </form>
  </div>
	
  <div class="copyright">Copyright © 2011-2016 www.smiles8.top 版权所有</div>
  
</div>
	<script type="text/javascript" >
		var start=false;
	    $(function() {
	    	//获取验证码-发送邮件
	    	$(".codeBtns").click(function(){
	    		if(start) return false;
				var email = $.trim($("#email").val());
				if(email == ''){
					layer.tips('请输入邮箱号','#email', {tips: 1});
					return false;
				}
				var params="?email="+email;
				//发送ajax请求
				$.ajax({
		              type: "POST",
		              url: "${path}app/appMain/getRegisterCode.json",
					  data: {email:email},
					  beforeSend: function() { startTimer(); },
					  error:function(){ $("#CodeBtn").val("获取验证码");start=false;},
		              success: function(result) {
		            	  var datas=result.data;
		            	  if(datas && datas.success_code==10000){
		            		  layer.msg(result.msg);
		            	  }else{
		            		  layer.msg(result.msg);
		            		  clearTimer();
		            	  }
					  }
		          });
	    	});
	    	
	    	//提交注册
	    	$("#registerBtn").click(function(){
	    		var email = $.trim($("#email").val());
	    		var account = $.trim($("#account").val());
	    		var password = $.trim($("#password").val());
	    		var code = $.trim($("#code").val());
	    		if(account == ''){
					layer.tips('请输入账号','#account', {tips: 1});
					return false;
				}
	    		if(password == ''){
					layer.tips('请输入密码','#password', {tips: 1});
					return false;
				}
	    		if(email == ''){
					layer.tips('请输入邮箱号','#email', {tips: 1});
					return false;
				}
	    		if(code == ''){
					layer.tips('请输入验证码','#code', {tips: 1});
					return false;
				}
	    		if(code.length<5){
	    			layer.tips('验证码输入有误','#code', {tips: 1});
					return false;
	    		}
	    		//发送ajax请求
				$.ajax({
		              type: "POST",
		              url: "${path}app/appMain/doRegister.json",
					  data: {email:email,account:account,password:password,code:code},
					  beforeSend: function() {  },
					  error:function(){ layer.msg("系统错误!");},
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
			var second = 60;//60秒发送一次
			function startTimer() {
				start=true;
			    timer = window.setInterval(timerHandle, 1000); //启动计时器，1秒执行一次
			}
			//timer处理函数
			function timerHandle() {
			    if (second == 1) {
			        window.clearInterval(timer);//停止计时器
			        $("#CodeBtn").val("获取验证码");
			        second=60;
			        start=false;
			    } else {
			  	  second--;
			  	  $("#CodeBtn").val(second+"s");
			    }
			}
			
			function clearTimer(){
				window.clearInterval(timer);//停止计时器
		        $("#CodeBtn").val("获取验证码");
		        start=false;
			}
	    	
		});	
	    
	    //========================================================//
	    
		//设置password字段的值	
		$('.txt-password').bind('input',function(){
			$('#password').val($(this).val());
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
