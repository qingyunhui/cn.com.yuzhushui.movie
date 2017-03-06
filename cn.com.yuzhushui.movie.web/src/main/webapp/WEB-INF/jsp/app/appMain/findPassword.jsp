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
<script type="text/javascript">
$(document).ready(function(){
	$("form").submit(function(e){
	  	var accounts = $.trim($("#account").val());
		var email = $.trim($("#email").val());
		if(accounts == ''){
			layer.tips('请输入账号','#account', {tips: 1});
			return false;
		}else if(email == ''){
			layer.tips('请输入注册时的邮箱','#email', {tips: 1});
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
      <h2>找回密码</h2>
      </div>
  </header>
  <!--header 结束-->
  
  <div class="w main">
  	<form id="frm_login" method="post" action="${path}app/appMain/getCode.htm">
  		<div class="item item-username">
          <input id="account" class="txt-input txt-username" type="text" placeholder="请输入您的账号" name="account">
          <b class="input-close" style="display: none;"></b> 
        </div>
        <div class="item item-username">
          <input id="email" class="txt-input-email" type="text" placeholder="请输入注册时的邮箱" name="email">
          <b><input name="" type="submit" value="获取验证码"  class="ui-btn-lg-emailBtn ui-btn-primary" /></b> 
        </div>
      </form>
  </div>
  <div class="copyright">Copyright © 2011-2016 www.smiles8.top 版权所有.</div>
</div>
<script type="text/javascript" >
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
