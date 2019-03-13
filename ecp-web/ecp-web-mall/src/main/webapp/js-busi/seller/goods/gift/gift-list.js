//页面初始化模块
$(function() {
	var pInit = function() {
		var init = function() {
			var shopId = $("#shopId").val();
			var data = new Object();
			data.shopId = shopId;
		
 			// 分页
			$('#pageControlbar').bPage({
				url : GLOBAL.WEBROOT + '/seller/gift/gridlist',
				asyncLoad : true,
				asyncTarget : '#giftListDiv',
				params :function(){
					return data;
				}
			});


		            $('a[name=del]').live("click",function () {
		            	
		            	var giftId = $(this).attr("giftId");
		    			var shopId = $("#shopId").val();
		    			var param = {
		    					giftId : giftId,
		    					shopId : shopId
		    			};
		                eDialog.confirm("确定要删除信息吗？",{
		                    'title':"提示",
		                    'buttons':  [
		                        {caption: '确定', callback: function() { 
		                        	
		        						$.eAjax({
		        							url : GLOBAL.WEBROOT + "/seller/gift/giftremove",
		        							data : param,
		        							success : function(returnInfo) {
		        								if(returnInfo.ecpBaseResponseVO.resultFlag=='ok'){
		        									eDialog.success('删除成功！'); 
		        									 $('#giftSearchBtn').trigger('click');
		        								}
		        							}
		        						});
	
		                        }},
		                        {caption: '取消',callback : $.noop},
		                    ]
		                });
		            });


		            $('a[name=edit]').live("click",function () {
		            	var giftId = $(this).attr("giftId");
		            	window.location.href = GLOBAL.WEBROOT+'/seller/gift/giftedit?giftId='+giftId;
		            	
		            });

		};
		return {
			init : init
		};
	};
	pageConfig.config({
		// 指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : [ 'bPage' ],
		// 指定页面
		init : function() {
			var giftList = new pInit();
			giftList.init();
		}
	});
});
