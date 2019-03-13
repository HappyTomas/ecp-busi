//页面初始化模块
$(function() {
	var pInit = function() {
		var init = function() {
			$("tr").click(function() {
				if ($(this).find(":radio").attr("checked")) {
					$(this).find(":radio").attr("checked", false);
				}else{
					$(this).find(":radio").attr("checked", true);
				}
			});
			$("input:radio").click(function() {
				if ($(this).attr("checked")){
					$(this).attr("checked", false);
				}else{
					$(this).attr("checked", true);
				}
			});
			//分页
			$('#pageControlbar').bPage(
					{
						url : GLOBAL.WEBROOT+ '/seller/coupontype/typelist',
						asyncLoad : true,
						asyncTarget : '#couponListDiv',
						params:function(){
							return {
								coupTypeName : $('#coupTypeName').val(),
								status : $('#status').val(),
								 typeLimit : $('#typeLimit').val()
							};
						}
					});

		};
		return {
			init : init
		};
	};
	pageConfig.config({
		//指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : [ 'bPage' ],
		//指定页面
		init : function() {
			var groupList = new pInit();
			groupList.init();
		}
	});
});