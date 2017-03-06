<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/public/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>资金池</title>
<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta charset="utf-8">
<link href="${path}css/sysBills/m.common.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.totalPool{
		margin-top: 0.5rem;
		margin-bottom:0.5rem;
   		font-size: 15px;
	    font-weight: bold;
	    color: #333;
	}
	.totalPool span{
		color:red;
		font-size: 16px;
	}
	.totalPay{
		margin-top: 0.5rem;
		margin-bottom:0.5rem;
   		font-size: 15px;
	    font-weight: bold;
	    color: #333;
	}
	.totalPay span{
		color:red;
		font-size: 16px;
	}
	.payInfos{
		text-align: center;
	    font-size: 1.6em;
	    margin-bottom: 0.5em;
	    color: rgb(16, 216, 41);
	}
	.baike369{width:100%;height:1px;border-bottom:1px dashed #000;margin-top:0.2rem;margin-bottom:0.2rem;}
	.totalSurplus{
		margin-top: 0.5rem;
		margin-bottom:0.5rem;
   		font-size: 16px;
	    font-weight: bold;
	    color: #060606;
	}
	.totalSurplus span{
		color:red;
		font-size: 18px;
	}
</style>
</head>

</head>
<body class="mWrap">
	<!-- 页头 -->
	<header class="topHd"><a onClick="history.back();return false;" href="javascript:void(0);" class="back">返回</a><a href="${path}app/appMain/myMain.htm" class="home">首页</a><span>资金池</span></header>
	<section class="mContent">
		<ul class="proList clearfix" id="dataUl">
		  <c:forEach var="result" items="${entitys}" >
		  	<li>
				<a href="javascript:void(0);"><p class="tt">资金池剩余额度¥:${result.gold}</p></a>
				<p>
					<a href="javascript:void(0);"><ins class="sbtn right">充值时间:<fmt:formatDate value='${result.ctime}' type='date' pattern='yyyy-MM-dd HH:mm:ss'/></ins></a>
					<a href="javascript:void(0);"><span class="price">¥${result.gold}</span><br>${result.comments}</a>
				</p>
			</li>
		  </c:forEach>
		 <!--  -->
		 <div class="totalPool">资金池历史充值总金额:<span>${totalGold}</span>元人民币</div>
		 
		 <div class="baike369"></div>
		 
		 <div class="payInfos">截止今天，支出历史记录如下:</div>
		 
		 <c:forEach var="result" items="${sysBillsList}" >
		  	<li>
				<span>
					<fmt:formatDate value='${result.ctime}' type='date' pattern='yyyy-MM-dd HH:mm:ss'/> &nbsp;
					${yzsTld:getNameByValue('cn.com.yuzhushui.movie.enums.SysBillsEnum$Keyword',result.keyword)} &nbsp;
					${result.money}元人民币
				</span>
			</li>
		  </c:forEach>
		  
		  <div class="totalPay">历史支出总金额:<span>${totalMoney}</span>元人民币</div>
		  
		  <div class="baike369"></div>
		  
		  <div class="totalSurplus">剩余可用余额(可预支金额):<span>${totalSurplus}</span>元人民币</div>
		  
		<div id="dataappend"></div>
		</ul>
			
	</section>
<script type="text/javascript">
	$(function(){
		var mesg = $.trim(${messages});
		if(mesg != ''){
			layer.open({
				  content: mesg
				  ,style: 'background-color:#09C1FF; color:#fff; border:none;' //自定风格
				  ,time: 3
				});
		}
	})
</script>
</body>
</html>