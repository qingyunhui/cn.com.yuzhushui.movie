<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="nav-footer">
	<ul>
		<li style="width:25%"><a href="${basePath}app/appMain/myMain.htm"><i class="icon-home"></i></a></li>
		<li style="width:25%"><a href="${basePath}app/appMain/goodsCategory.htm"><i class="icon-reorder"></i></a></li>
		<li style="width:25%"><a href="${basePath}app/appMain/shoppingCart.htm"><i class="icon-shopping-cart"></i></a></li>
		<li style="width:25%"><a href="${basePath}app/appMain/myself.htm"><i class="icon-info-sign"></i></a></li>
	</ul>
</div>
<script type="text/javascript">
  $(function(){
	  var index=${currIndex};
	  var liObjs=$("#nav-footer ul li");
	  $.each(liObjs, function(i,o) {    
		    if(index==i+1){
		    	$(this).attr("class","on");//addClass
		    }else{
		    	$(this).removeClass("on");
		    }
		});    
  });
</script>