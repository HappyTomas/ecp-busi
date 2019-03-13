//页面初始化模块
$(function() {
	var pInit = function() {
		var init = function() {
			//分页
			var promTypeCode = $('#promTypeCode').val();//由于需要注意冒泡顺序的问题
			var load_url = GLOBAL.WEBROOT+ '/seller/gdsprom/importGdsList?promTypeCode='+promTypeCode;
			$('#pageControlbar').bPage(
					{
						url : load_url,
						asyncLoad : true,
						asyncTarget : '#divDataGridTableTemp',
						params:function(){
						  	return {
					  		     promId:$('#promId').val()
	    	                    	}
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