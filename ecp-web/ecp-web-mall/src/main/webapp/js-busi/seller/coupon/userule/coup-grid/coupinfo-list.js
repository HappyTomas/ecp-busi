//页面初始化模块
$(function() {
	var pInit = function() {
		var init = function() {
			$("tr").click(function() {
				if($(this).find(":checkbox").attr("id")=="dt_row_all_check"){
					if($(this).find(":checkbox").attr("checked")){
						$(this).find(":checkbox").attr("checked", false);
						$(" tbody tr td input:checkbox").prop('checked', false);
					}else{
						$(this).find(":checkbox").attr("checked", true);
						$(" tbody tr td input:checkbox").prop('checked', true);
					}
				}else{
					if ($(this).find(":checkbox").attr("checked")) {
						$(this).find(":checkbox").attr("checked", false);
					}else{
						$(this).find(":checkbox").attr("checked", true);
					}
				}
			});
			$("input:checkbox").click(function() {
				if ($(this).attr("checked")){
					$(this).attr("checked", false);
				}else{
					$(this).attr("checked", true);
				}
			});
			//分页
			$('#pageControlbar').bPage(
					{
						url : GLOBAL.WEBROOT + '/seller/coupinfo/selectlist',
						asyncLoad : true,
						asyncTarget : '#coupListDiv',
						params:function(){
							return {
								siteId:$('#siteId').val(),
								shopId:$('#shopId').val(),
								coupTypeId :$('#coupTypeId').val(),
								coupName : $('#coupName').val(),
								inactiveTime : $('#inactiveTime').val(),
								activeTime : $('#activeTime').val(),
								status: $('#status').val()
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
			var coupList = new pInit();
			coupList.init();
		}
	});
});