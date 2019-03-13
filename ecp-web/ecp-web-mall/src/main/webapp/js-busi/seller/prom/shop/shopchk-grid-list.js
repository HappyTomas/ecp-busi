//页面初始化模块
$(function() {
	var pInit = function() {
		var init = function() {
			//分页
			$('#pageControlbar').bPage(
					{url : GLOBAL.WEBROOT+ '/seller/shopchk/gridlist',
						asyncLoad : true,
						asyncTarget : '#shopCheckListDiv',
						params:function(){
							return {
								//promTheme : $('input[name="promTheme"]').val(),
								//showStartTime :$('input[name="showStartTime"]').val(),
								//showEndTime : $('input[name="showEndTime"]').val(),
								promTypeCode : $('#promTypeCode').val(),
								status :$('#status').val(),
								startTime : $('#startTime').val(),
								endTime : $('#endTime').val(),
								shopId  : $('#shopId').val(),
								siteId  :$('#siteId').val()
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