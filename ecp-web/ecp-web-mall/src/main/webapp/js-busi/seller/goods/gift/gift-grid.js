//页面初始化模块
$(function() {
	var pInit = function() {
		var init = function() {
	
			// //绑定提交按钮事件
			 $('#giftSearchBtn').unbind("click").click(function(){
				 
				 if($("input[name='giftId']")){
						var gdsId=$("input[name='giftId']").val();
						if(gdsId && gdsId!=""){
							var r = /^[0-9]*$/;
							if(!r.test(gdsId)){
								eDialog.alert('赠品编码只能是整数！');
								return false;
							}
						}
					}
				 
					var shopId = $("#shopId").val();

					var  p = ebcForm.formParams($('#giftForm'));
					
				 var url = GLOBAL.WEBROOT + '/seller/gift/gridlist?shopId=' + shopId;
			
				 
					$('#giftListDiv').load(url,p);
			 });
				
			 $("#giftAddBtn").live("click",function(){
		        	var shopId = $("#shopId").val();
		        	window.location.href =  GLOBAL.WEBROOT +  "/seller/gift/giftadd?shopId=" + shopId;
		        	
		        });
			 	$("#giftResetBtn").unbind("click").click(function() {
							$("#giftForm")[0].reset();
						});

		};
		return {
			init : init
		};
	};
	pageConfig.config({
		// 指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : [ ],
		// 指定页面
		init : function() {
			var giftList = new pInit();
			giftList.init();
		}
	});
});
