<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="footer">
	<div class="mune">
    	<img src="${path}image/myMain/footer/1.png">
        <p>首页</p>
    </div>
	<div class="mune">
    	<img src="${path}image/myMain/footer/2.png">
        <p>商家</p>
    </div>
	<div class="mune">
    	<img src="${path}image/myMain/footer/3.png">
        <p>申请加盟</p>
    </div>
	<div class="mune">
    	<img src="${path}image/myMain/footer/4.png">
        <p>个人中心</p>
    </div>    
</div>
<script type="text/javascript">
  $(function(){
	  var index=${currIndex};
	  var objs=$(".footer .mune");
	  
	  //background: rgba(32, 226, 58, 0.77)
	  
	  $.each(objs, function(i,o) {    
		    var curImg=$(this).find("img");
	    	var curP=$(this).find("p");
		    if(index==i+1){
		    	$(curP).css("color","#20e23a");//addClass
		    	$(curImg).css("background","#14c760");
		    }else{
		    	$(curP).css("background-color");
		    }
		});    
  });
</script>