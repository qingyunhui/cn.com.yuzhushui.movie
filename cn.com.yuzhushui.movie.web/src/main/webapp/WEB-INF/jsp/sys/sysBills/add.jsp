<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/public/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>账单申请</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta charset="utf-8">
<link href="${path}css/sysBills/m.common.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.form .inputs {
	    width: 75%;
	    height: 2.8em;
	    border: 0.07em solid #ababab;
	    padding: 0 0.8em;
	    font-size: 0.98em;
	    color: #333;
	    box-sizing: border-box;
	    -moz-box-sizing: border-box;
	    -webkit-box-sizing: border-box;
	    float:right;
	}
	.form .input_span{
		width: 20%;
		float:left;
		height: 2.8em;
		line-height: 2.8em;
	}
	.form .textarea{
		width: 75%;
	    height: 5em;
	    border: 0.07em solid #ababab;
	    padding: 0 0.8em;
	    font-size: 0.98em;
	    color: #333;
	    box-sizing: border-box;
	    -moz-box-sizing: border-box;
	    -webkit-box-sizing: border-box;
	    float:right;
	}
	textarea{ resize:none;}
</style>
</head>
</head>
<body class="mWrap">
	<header class="topHd">
		<a href="javascript:void(0);" onClick="history.back();return false;" class="back">返回</a>
		<a href="${path}app/appMain/myMain.htm" class="home">首页</a>
		<span>账单申请</span>
	</header>
	<form id="addForm" method="post" action="${path}sys/sysBills/doAdd.htm">
		<section class="mContent">
			<ul class="form">
				<li>
					<p>
					  <span class="input_span">关键字</span>
					  <select class="inputs" id="keyword" name="keyword">
						<c:forEach var="result" items="${yzsTld:getEnumValues('cn.com.yuzhushui.movie.enums.SysBillsEnum$Keyword')}">
							<option value="${result.value}">${result.name}</option>
						</c:forEach>
					  </select>
					</p>
				</li>
				<li>
					<p>
					  <span class="input_span">主题</span>
					  <input type="text" name="subject" id="subject" class="inputs"/>
					</p>
				</li>
				<li>
					<p>
					  <span class="input_span">预支人</span>
					  <input type="text" class="inputs" name="debtor" readonly="readonly" value="${yzsTld:getSysUser().name}"/>
					</p>
				</li>
				<li>
					<p>
					  <span class="input_span">预支金额</span>
					  <input type="number" id="money" maxlength="5" oninput="if(value.length>5)value=value.slice(0,5)"  name="money" class="inputs"  />
					</p>
				</li>
				<li>
					<p>
					  <span class="input_span">预支对方</span>
					  <input type="text" name="lender" class="inputs" readonly="readonly" value="${debtorUser.name}" />
					</p>
				</li>
				<li>
					<p>
					  <span class="input_span">交易类型</span>
					  <select name="tradeType" class="inputs">
						<c:forEach var="result" items="${yzsTld:getEnumValues('cn.com.yuzhushui.movie.enums.SysBillsEnum$TradeType')}">
							<option value="${result.value}">${result.name}</option>
						</c:forEach>
					  </select>
					</p>
				</li>
				<li>
					<p>
					  <span class="input_span">说明</span>
					  <textarea name="content" id="content" rows="10" class="textarea"></textarea>
					</p>
				</li>
				<li class="btnArea">
					<input type="submit" class="btn" id="hlkOK" value="申&nbsp;&nbsp;请" />
				</li>
			</ul>
		</section>
	</form>
<script type="text/javascript">
	$(document).ready(function(){
		$("#addForm").submit(function(e){
		  	var subject = $.trim($("#subject").val());
			var money = $.trim($("#money").val());
			var content = $.trim($("#content").val());
			if(subject == ''){
				layer.tips('请输入主题','#subject', {tips: 1});
				return false;
			}else if(money == ''){
				layer.tips('请输入预支金额','#money', {tips: 1});
				return false;
			}else if(content==''){
				layer.tips('请输入说明','#content', {tips: 1});
				return false;
			}
		});
	});
</script>
</body>
</html>