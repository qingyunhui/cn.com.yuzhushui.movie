<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
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
      <h2>修改密码</h2>
      </div>
  </header>
  <!--header 结束-->
  
  <div class="w main">
  	<form id="frm_login" method="post" action="javascript:void(0);">
  		<div class="item item-username">
          <input id="account" class="txt-input txt-username" type="text" maxlength="10" value="${account}" placeholder="请输入要修改的账号" name="account">
        </div>
        <div class="item item-username">
          <input id="code" class="txt-input txt-username" type="text" maxlength="5" placeholder="请输入5位数的验证码" name="code">
        </div>
        <div class="item item-password">
        	<input id="passwords" class="txt-input txt-password ciphertext" maxlength="12" type="password" placeholder="请输入密码" name="passwords" style="display: inline;">
        </div>
        <div class="item item-password">
        	<input id="confirmPasswords" class="txt-input txt-password ciphertext" maxlength="12" type="password" placeholder="输入确认密码" name="confirmPasswords" style="display: inline;">
        </div>
        <div class="ui-btn-wrap"><input name="" type="submit" value="提交"  class="ui-btn-lg ui-btn-primary" /> </div>
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
	            		  layer.alert(result.msg, {
		            		  skin: 'layui-layer-molv' //样式类名
		            		  ,closeBtn: 0
		            		}, function(index){
		            			layer.close(index);
		            			location.href='${path}'+datas.url;
		            		});
	            	  }else{
	            		  layer.msg(result.msg);
	            	  }
				  }
	          });
		});
	});
</script> 
</body>
</html>
