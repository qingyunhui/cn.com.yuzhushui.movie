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
	<script type="text/javascript">
		$(function(){
			var mesg = '${messages}';
			if(mesg != ''){
				layer.msg(mesg,{time:2500});
			} 
		})
	</script>
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
		isShow('${isShow}');
	});
	
	function isShow(isShow){
		 if('true'==isShow){
			 layer.open({
			        type: 1
			        ,title: false //不显示标题栏
			        ,closeBtn: false
			        ,area: '300px;'
			        ,shade: 0.8
			        ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
			        ,btn: ['返回主页', '取消']
			        ,moveType: 1 //拖拽模式，0或者1
			        ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">你知道吗？亲！<br>您目前还有账单处于未审核中的哦，请联系相关人员对账单审核通过后，你方才能够继续操作哦！<br>你可以选择回到主页面，也可以选择继续停留在这里。<br><br> wish you good luck. </div>'
			        ,success: function(layero){
			          var btn = layero.find('.layui-layer-btn');
			          btn.css('text-align', 'center');
			          btn.find('.layui-layer-btn0').attr({
			            href: '${path}app/appMain/myMain.htm'
			          });
			        }
			      });
		 }
	}
</script>
</body>
</html>