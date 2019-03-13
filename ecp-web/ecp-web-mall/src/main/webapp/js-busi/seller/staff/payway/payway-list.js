$(function(){
	
	$('.stop').each(function(){
		$(this).live('click',function(){
			var payWay = $(this).attr('payway');
			var shopId = $('#shopId').val();
			$.eAjax({
	  			url : GLOBAL.WEBROOT + "/seller/payway/edit",
	  			data : {useFlag:'0',payWay:payWay,shopId:shopId},
	  			async : true,
				type : "post",
	  			dataType:'json',
	  			success : function(returnInfo) {
	  				if(returnInfo.resultFlag=='ok'){
	  					eDialog.alert(returnInfo.resultMsg,function(){
	  						$.paySeache();
	  					});
	  				}
	  			}
	  		});
		});
	});
	
	$('.start').each(function(){
		$(this).live('click',function(){
			var payWay = $(this).attr('payway');
			var shopId = $('#shopId').val();
			$.eAjax({
	  			url : GLOBAL.WEBROOT + "/seller/payway/edit",
	  			data : {useFlag:'1',payWay:payWay,shopId:shopId},
	  			async : true,
				type : "post",
	  			dataType:'json',
	  			success : function(returnInfo) {
	  				if(returnInfo.resultFlag=='ok'){
	  					eDialog.alert(returnInfo.resultMsg,function(){
	  						$.paySeache();
	  					});
	  				}
	  			}
	  		});
		});
	});
	//分页
	$('#pageControlbar').bPage({
	    url : GLOBAL.WEBROOT + "/seller/payway/list?shopId="+$('#shopId').val()+'&payWay='+$('#payWay').val(),
	    asyncLoad : true,
	    asyncTarget : '#dataList',
	    params : {
	    	
	    }
	});
	
	$('.edit').each(function(){
		$(this).live('click',function(){
			var payWay = $(this).attr('payway');
			var shopId = $('#shopId').val();
			window.location.href= GLOBAL.WEBROOT + "/seller/payway/editpage/"+shopId+"-"+payWay;
		});
	});
	
});