<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/public/head.jsp"%>
<script>
$(window).load(function() {
	$("#status").fadeOut();
	$("#preloader").delay(350).fadeOut("slow");
})
</script>
</head>
<script type="text/javascript">
$(document).ready(function(){
	$("form").submit(function(e){
	  	var accounts = $.trim($("#accounts").val());
		var passwords = $.trim($("#passwords").val());
		if(accounts == ''){
			layer.tips('请输入用户名/邮箱/手机号码','#accounts', {tips: 1});
			return false;
		}else if(passwords == ''){
			layer.tips('请输入登录密码','#passwords', {tips: 1});
			return false;
		}
	});
});
</script>
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
      <h2>一起微笑吧·登录</h2>
      </div>
  </header>
  <!--header 结束-->
  
  <div class="w main">
  	<form id="frm_login" method="post" action="${path}app/appMain/doLogin.htm">
        <div class="item item-username">
          <input id="accounts" class="txt-input txt-username" type="text" placeholder="请输入用户名/邮箱/手机号" value="admin" name="accounts">
          <b class="input-close" style="display: none;"></b> </div>
        <div class="item item-password">
          <input id="passwords" class="txt-input txt-password ciphertext" type="password" placeholder="请输入密码" value="111111" name="passwords" style="display: inline;">
          <input id="ptext" class="txt-input txt-password plaintext" type="text" placeholder="请输入密码" style="display: none;" name="ptext">
          <b class="tp-btn btn-off"></b>
        </div>
        <div class="item item-login-option">
        	<div class="aoutlogin">
                <label class="ui-checkbox ui-checkbox-s">
                    <input type="checkbox" name="checkbox" checked/>一个月内免登录
                </label>
            </div>
            <span class="retrieve-password"> <a class="" href="getpwd_email.html"> 找回密码</a> </span>
        </div>
        <div class="ui-btn-wrap"><input name="" type="submit" value="用户登录"  class="ui-btn-lg ui-btn-primary" /> </div>
        <div class="ui-btn-wrap"> <a class="ui-btn-lg ui-btn-danger" href="${path}app/appMain/register.htm">没有账号？立即注册</a> </div>
    <div class="item item-login-other">
          <dl>
            <dt>其它登录方式</dt>
            <dd> <a class="qq" href="#"> <span><img alt="" src="${path}image/login/login_qq.png" width="40" height="40"></span> </a> </dd>
          </dl>
        </div>
      </form>
  </div>
	
  <div class="m_user w">
  	<a href="getpwd_email.html">找回密码</a>
    <a href="${path}app/appMain/register.htm">注册</a>
  </div>
  <div class="copyright">Copyright © 2011-2016 www.smiles8.top 版权所有.</div>
</div>
<script type="text/javascript" >
    $(function() {
		$(".input-close").hide();
		displayPwd();
		displayClearBtn();
		setTimeout(displayClearBtn, 200 ); //延迟显示,应对浏览器记住密码
	});	

	//是否显示清除按钮
	function displayClearBtn(){
		if(document.getElementById("accounts").value != ''){
			$("#accounts").siblings(".input-close").show();
		}
		if(document.getElementById("passwords").value != ''){
			$(".ciphertext").siblings(".input-close").show();
		}
		if($("#codeLevel").val()!="" && $("#codeLevel").val()!='0'){
			if($("#validateCode").val()!=""){
				$("#validateCode").siblings(".input-close").show();
			}
		}
	}

	//清除input内容
    $('.input-close').click(function(e){  
		$(e.target).parent().find(":input").val("");
		$(e.target).hide();
		$($(e.target).parent().find(":input")).each(function(i){
			if(this.id=="ptext" || this.id=="passwords"){
				$("#passwords").val('');
				$("#ptext").val('');
			}
         });
    });  
	
	//设置passwords字段的值	
	$('.txt-password').bind('input',function(){
		$('#passwords').val($(this).val());
	});
	
	//显隐密码切换
	function displayPwd(){
    	$(".tp-btn").toggle(
          function(){
            $(this).addClass("btn-on");
			var textInput = $(this).siblings(".plaintext");
    		var pwdInput = $(this).siblings(".ciphertext");
			pwdInput.hide();
			textInput.val(pwdInput.val()).show().focusEnd();
          },
          function(){
		  	$(this).removeClass("btn-on");
		  	var textInput = $(this).siblings(".plaintext ");
    		var pwdInput = $(this).siblings(".ciphertext");
            textInput.hide();
			pwdInput.val(textInput.val()).show().focusEnd();
          }
    	);
	}

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
