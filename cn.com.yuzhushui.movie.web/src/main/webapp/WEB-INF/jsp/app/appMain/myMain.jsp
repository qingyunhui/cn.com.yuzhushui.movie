<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/public/tagLibrary.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的首页</title>

<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<meta charset="utf-8">
<link href="${path}css/myMain/style.css" rel="stylesheet" type="text/css" />
<link href="${path}css/myMain/iscroll.css" rel="stylesheet" type="text/css" />
<style>
.banner img {width: 100%;}
.footer {
    position: fixed;
    bottom: 0px;
    left: 0px;
    margin-top: 0px;
    width: 100%;
    height: 3.0rem;
    background: url(../../image/myMain/footer/projection.png) repeat-x;
    padding-top: 0.5rem;
}

.footer .mune {
    float: left;
    width: 25%;
    height: 0.7rem;
    text-align: center;
}
.mune img {
    width: 2.0rem;
    height: 2.0rem;
}
.mune p {
    font-size: 0.22rem;
}
body .mainmenu li.UL_Li_CLSS{
	float: left;
    margin-left: 2.5%;
    margin-top: 5.5%;
    width: 95%;
    border-radius: 3px;
    overflow: hidden;
}
body .mainmenu li.UL_Li_CLSS a span{
	display: block;
    height: 35px;
    line-height: 35px;
    color: #999;
    font-size: 16px;
}

body .mainmenu li.balance0 a span{
	 display: block;
     height: 35px;
     line-height: 35px;
     color: #080000;
     font-size: 20px;
}
body .mainmenu li.balance1 a span{
	display: block;
    height: 35px;
    line-height: 35px;
    color: #e88888;
    font-size: 18px;
}
body .mainmenu li.balance2 a span{
	display: block;
    height: 35px;
    line-height: 35px;
    color: #40ad45;
    font-size: 18px;
}
body .mainmenu li.balance3 a span{
	display: block;
    height: 35px;
    line-height: 35px;
    color: red;
    font-size: 18px;
}

body .mainmenu li.subject a span{
	background:#eee;
}
body .mainmenu li.query a span{
	background:#fc5366;
	color:#151515;
}
body .mainmenu li.audit a span{
	background:#8c67df;
	color:#151515;
}
body .mainmenu li.robot a span{
	background:#678ce1;
	color:#151515;
}
body .mainmenu li.api a span{
	background:#14c760;
	color:#151515;
}
body .mainmenu li.pool a span{
	background:#84d018;
	color:#151515;
}

</style>
</head>

<script type="text/javascript" src="${path}js/myMain/iscroll.js"></script>
<script type="text/javascript">
var myScroll;
function loaded(){
	myScroll = new iScroll('wrapper', {
		snap: true,
		momentum: false,
		hScrollbar: false,
		onScrollEnd: function () {
			document.querySelector('#indicator > li.active').className = '';
			document.querySelector('#indicator > li:nth-child(' + (this.currPageX+1) + ')').className = 'active';
		}
	});
}
document.addEventListener('DOMContentLoaded', loaded, false);
</script>

</head>

<body>
<!--music-->
<style>
.btn_music{display:inline-block;width:35px;height:35px;background:url('${path}image/myMain/play.png') no-repeat center center;background-size:100% auto;position:absolute;z-index:100;left:15px;top:20px;}
.btn_music.on{background-image:url("${path}image/myMain/stop.png");}
</style>

<script type="text/javascript" src="${path}js/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
var playbox = (function(){
	//author:eric_wu
	var _playbox = function(){
		var that = this;
		that.box = null;
		that.player = null;
		that.src = null;
		that.on = false;
		//
		that.autoPlayFix = {
			on: true,
			//evtName: ("ontouchstart" in window)?"touchend":"click"
			evtName: ("ontouchstart" in window)?"touchstart":"mouseover"
			
		}
	}
	
	_playbox.prototype = {
		init: function(box_ele){
			this.box = "string" === typeof(box_ele)?document.getElementById(box_ele):box_ele;
			this.player = this.box.querySelectorAll("audio")[0];
			this.src = this.player.src;
			this.init = function(){return this;}
			this.autoPlayEvt(true);
			return this;
		},
		play: function(){
			if(this.autoPlayFix.on){
				this.autoPlayFix.on = false;
				this.autoPlayEvt(false);
			}
			this.on = !this.on;
			if(true == this.on){
				this.player.src = this.src;
				this.player.play();
			}else{
				this.player.pause();
				this.player.src = null;
			}
			if("function" == typeof(this.play_fn)){
				this.play_fn.call(this);
			}
		},
		handleEvent: function(evt){
			this.play();
		},
		autoPlayEvt: function(important){
			if(important || this.autoPlayFix.on){
				document.body.addEventListener(this.autoPlayFix.evtName, this, false);
			}else{
				document.body.removeEventListener(this.autoPlayFix.evtName, this, false);
			}
		}
	}

	return new _playbox();
	
})();

playbox.play_fn = function(){
	this.box.className = this.on?"btn_music on":"btn_music";
}
</script>

<script type="text/javascript">
$(document).ready(function(){
	playbox.init("playbox");
});
</script>

<span id="playbox" class="btn_music" onclick="playbox.init(this).play();"><audio id="audio" loop src="${path}image/myMain/4.mp3"></audio></span>

<div class="banner">

	<div id="wrapper">
		<div id="scroller">
			<ul id="thelist">
				<li><p>幻灯片01</p><a href="javascript:void(0)"><img src="${path}image/myMain/3.jpg" /></a></li>
				<li><p>幻灯片02</p><a href="javascript:void(0)"><img src="${path}image/myMain/4.jpg" /></a></li>
				<li><p>幻灯片03</p><a href="javascript:void(0)"><img src="${path}image/myMain/2.jpg" /></a></li>
				<li><p>幻灯片04</p><a href="javascript:void(0)"><img src="${path}image/myMain/1.gif" /></a></li>
			</ul>
		</div>
	</div>

	<div id="nav">
		<ul id="indicator">
			<li class="active" ></li>
			<li></li>
			<li></li>
			<li></li>
		</ul>
	</div>
	
</div>

<ul class="mainmenu">
	<li class="UL_Li_CLSS subject"><a><span>欢迎光临，${yzsTld:getSysUser().name}</span></a></li>
	<li class="UL_Li_CLSS balance${struct.capitalPool.value}"><a><span>${totalBalance}</span></a></li>
	<li class="UL_Li_CLSS query"><a href="${path}sys/sysBills/list.htm"><span>查看我的账单</span></a></li>
	<li class="UL_Li_CLSS audit"><a href="${path}sys/sysBills/add.htm"><span>申请账单</span></a></li>
	<li class="UL_Li_CLSS pool"><a href="${path}sys/sysFundPool/list.htm"><span>资金池剩余额度</span></a></li>
	<li class="UL_Li_CLSS robot"><a href="${path}robot/answers.htm"><span>机器人</span></a></li>
	<li class="UL_Li_CLSS api"><a href="${path}api/show.htm"><span>API</span></a></li>
	<%-- <li><a href="javascript:void(0);" ><b><img src="${path}image/myMain/tb01.png" /></b><span>关于我们</span></a></li>
	<li><a href="javascript:void(0);" ><b><img src="${path}image/myMain/tb02.png" /></b><span>新闻中心</span></a></li>
	<li><a href="javascript:void(0);" ><b><img src="${path}image/myMain/tb03.png" /></b><span>产品展示</span></a></li>
	<li><a href="javascript:void(0);" ><b><img src="${path}image/myMain/tb04.png" /></b><span>成功案例</span></a></li>
	<li><a href="javascript:void(0);" ><b><img src="${path}image/myMain/tb05.png" /></b><span>下载中心</span></a></li>
	<li><a href="javascript:void(0);" ><b><img src="${path}image/myMain/tb06.png" /></b><span>团队介绍</span></a></li>
	<li><a href="javascript:void(0);" ><b><img src="${path}image/myMain/tb06.png" /></b><span>人才招聘</span></a></li>
	<li><a href="javascript:void(0);" ><b><img src="${path}image/myMain/tb07.png" /></b><span>联系我们</span></a></li>
	<li><a href="javascript:void(0);" ><b><img src="${path}image/myMain/tb08.png" /></b><span>在线留言</span></a></li>     	  --%>
</ul>


<script type="text/javascript">
var count = document.getElementById("thelist").getElementsByTagName("img").length;	

var count2 = document.getElementsByClassName("menuimg").length;
for(i=0;i<count;i++){
	document.getElementById("thelist").getElementsByTagName("img").item(i).style.cssText = " width:"+document.body.clientWidth+"px";
}
document.getElementById("scroller").style.cssText = " width:"+document.body.clientWidth*count+"px";

setInterval(function(){
	myScroll.scrollToPage('next', 0,400,count);
},3500 );

window.onresize = function(){ 
	for(i=0;i<count;i++){
		document.getElementById("thelist").getElementsByTagName("img").item(i).style.cssText = " width:"+document.body.clientWidth+"px";
	}
	document.getElementById("scroller").style.cssText = " width:"+document.body.clientWidth*count+"px";
} 
</script>

<c:set var="currIndex" value="0"></c:set>
<%@ include file="/WEB-INF/jsp/public/footer.jsp"%>
</body>

</html>