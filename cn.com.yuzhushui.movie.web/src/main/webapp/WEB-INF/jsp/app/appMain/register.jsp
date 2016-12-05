<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jsp/public/head.jsp"%>
    <title>register</title>
    <style type="text/css">
    	.ui-btn-lg-new {
			font-size: 14px;
		    height: 32px;
		    line-height: 32px;
		    display: block;
		    width: 30%;
		    border-radius: 10px;
		    float: right;
		    text-align: center;
		    margin-top:8px;
		    cursor:pointer;
		}
		
		.btn-disabled{
			background-image: -webkit-gradient(linear,left top,left bottom,color-stop(0.5,#BABFB6),to(#BABFB6));
		    border-color: #BABFB6;
		}
		
		.btn-enable{
			background-image: -webkit-gradient(linear,left top,left bottom,color-stop(0.5,#65C715),to(#65C715));
			border-color: #65C715;
		}
		
		.ui-btn-danger-new {
	    color: #fff;
	    -webkit-background-clip: padding-box;
	    background-clip: padding-box;
		}
		.ui-btn-danger-new {
	    color: #fff;
	    -webkit-background-clip: padding-box;
	    background-clip: padding-box;
		}
    	.main .item .txt-input-new {
	    background: none repeat scroll 0 0 #fff;
	    border: 1px solid #D1D1D1;
	    border-radius: 5px;
	    color: #252525;
	    font-size: 14px;
	    height: 44px;
	    width: 60%;
	}
	
	.main {
    padding-top: 5px;
    padding-bottom: 15px;
	}
    </style>
    <script>
		$(window).load(function() {
			$("#status").fadeOut();
			$("#preloader").delay(350).fadeOut("slow");
		});
	</script>
</head>
<body>
<div class="mobile">
  <!--页面加载 开始-->
  <div id="preloader">
    <div id="status"><p class="center-text"><span>拼命加载中···</span></p></div>
  </div>
  <!--页面加载 结束--> 
  <!--header 开始-->
  <header>
    <div class="header"> 
      <a class="new-a-back" href="${basePath}app/appMain/login.htm"> <span><img src="${basePath}images/iconfont-fanhui.png"></span> </a>
      <h2>微笑吧·注册</h2>
    </div>
  </header>
  <!--header 结束-->
  <div class="w main main-new">
  	<form id="frm_login" method="post" action="${path}app/appMain/doRegister.htm">
        <div class="item item-username">
          <input id="phoneOrEmail" class="txt-input txt-username" type="text" placeholder="请输入手机号/邮箱" value="" name="phoneOrEmail">
          <b class="input-close" style="display: none;"></b> 
        </div>
        <div class="item item-username">
            <input id="validateCode" name="validateCode" class="txt-input-new txt-captcha" type="text" placeholder="请输入验证码" maxlength="20">
            <span class="ui-btn-lg-new ui-btn-danger-new btn-enable" id="validateCode-btn">获取验证码</span>
            <b id="validateCodeclose" class="input-close" onClick="validateCodeclose();" style="display: block; margin-right:15px;"></b> 
        </div>
        <div class="item item-username">
          <input id="loginPassword" name="loginPassword" class="txt-input txt-username" type="password" placeholder="请输入密码">
          <b class="input-close" style="display: none;"></b> 
        </div>
        <div class="item item-username">
          <input id="validateLoginPassword" name="validateLoginPassword" class="txt-input txt-username" type="password" placeholder="确认密码">
          <b class="input-close" style="display: none;"></b> 
        </div>
        <div class="item item-captcha">
          <div class="err-tips"> 注册即视为同意 <a target="_blank" href="javascript:void(0);">用户服务协议</a> </div>
        </div>
        <div class="ui-btn-wrap"> <a class="ui-btn-lg ui-btn-primary" id="register-uiBtn" href="javascript:void(0);">注册</a> </div>
        <div class="ui-btn-wrap"> <a class="ui-btn-lg ui-btn-danger" href="${basePath}app/appMain/login.htm">已有账号？立即登录</a> </div>
    </form>
  </div>
</div>
</body>
</html>
<script type="text/javascript" >
	var emailOrPhone=/^((\(\d{3}\))|(\d{3}\-))?1(3|5|8)\d{9}|([a-z0-9A-Z]+[-\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\.)+[a-zA-Z]{2,}$/;
    $(function() {
		$(".input-close").hide();
		var timer;
		//获取验证码-btn
		$("#validateCode-btn").bind("click",getCode);
		function getCode(){
			if(!validatePhoneOrEmail(emailOrPhone)){return;};
			$.post("${path}app/appMain/validateCode.json",{phoneOrEmail:$("#phoneOrEmail").val()},function(data){
				//layer.tips(data.message,'#validateCode', {tips: 3});
				layer.msg(data.message, {shift: 6, time: 4000},function(){
	    			return;
	    		}); 
				if(data.status){
					clearTimeout(timer);
					djs(data.data.interval);
				}
			});
		}
		//倒计时用
		function djs(interval){
			timer=setTimeout(function(){djs(--interval)},1000);
			$("#validateCode-btn").attr("class","ui-btn-lg-new ui-btn-danger-new btn-disabled");
			$("#validateCode-btn").unbind("click");
			$("#validateCode-btn").html("重新获取("+interval+")");
			if(interval <0){
				$("#validateCode-btn").html("获取验证码");
				$("#validateCode-btn").attr("class","ui-btn-lg-new ui-btn-danger-new btn-enable");
				clearTimeout(timer);
				$("#validateCode-btn").bind("click",getCode);
			}
		}
		//注册-btn
		$("#register-uiBtn").click(function(){
			if(formCheck(emailOrPhone)){
				$("#frm_login").submit();
			}
		});
	});	

    function validatePhoneOrEmail(regex){
    	if($("#phoneOrEmail").val().trim().length==0){
			layer.tips('请输入手机号/邮箱!','#phoneOrEmail', {tips: 1});
			return false;
		}
		if(!regex.test($("#phoneOrEmail").val())){
			layer.tips('输入的格式不正确!','#phoneOrEmail', {tips: 1});
			return false;
		}
		return true;
    }
    
  	//验证
	function formCheck(regex){
		var validateCode=$("#validateCode").val();
		var loginPassword=$("#loginPassword").val();
		var validateLoginPassword=$("#validateLoginPassword").val();
		if(!validatePhoneOrEmail(regex)){
			return ;
		}
		if(!/^\d{6}$/.test(validateCode)){
			layer.tips('验证码应该为6位数字！','#validateCode-btn', {tips: 1});
			return false;
		}
		if(!/^.{4,16}$/.test(loginPassword)){
			layer.tips('密码长度应在6-16位之间！','#loginPassword', {tips: 1});
			return false;
		}
		if(!(validateLoginPassword == loginPassword)){
			layer.tips('输入的密码不一致！','#validateLoginPassword', {tips: 3});
			return false;
		}
		return true;
	}
</script> 