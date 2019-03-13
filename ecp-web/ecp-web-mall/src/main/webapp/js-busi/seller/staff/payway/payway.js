/**
*支付通道
*wangbh
**/
//页面初始化模块
$(function(){
	
	$.paySeache = function(){
		var val = ebcForm.formParams($("#searchForm"));
  		//$("#dataList").load(GLOBAL.WEBROOT + "/seller/payway/list");
  		$.eAjax({
  			url : GLOBAL.WEBROOT + "/seller/payway/list",
  			data : val,
  			async : true,
			type : "post",
  			dataType:'html',
  			success : function(returnInfo) {
  				$('#dataList').html(returnInfo);
  			}
  		});
	}
	
	
  var pInit = function(){
  	var init = function(){
  		
  		$.paySeache();
		};
		var band = function(){
			
			$('.sbtn-blue').on('click',function(){
				var list = new pInit();
				list.init();
			});
			
			$('#add').live('click',function(){
				var shopId = $('#shopId').val();
				window.location.href= GLOBAL.WEBROOT + "/seller/payway/addpage"+'/'+shopId;
			});
			
		};
  	return {
  		init : init,
  		band : band
  	};
	};    	
	pageConfig.config({
		//指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : ['bPage'],
		//指定页面
		init : function(){
			var list = new pInit();
			list.init();
			list.band();
		}
	});
});
