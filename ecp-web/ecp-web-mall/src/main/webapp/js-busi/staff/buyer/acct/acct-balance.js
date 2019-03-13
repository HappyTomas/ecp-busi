//页面初始化模块
$(function(){
	//页面业务逻辑处理内容
	var pInit = function(){

    	var init = function(){
    		var shopId = $("#shopId").val();
    		//分页
	    	$('#pageControlbar').bPage({
	    	    url : GLOBAL.WEBROOT + '/buyeracct/tab/balancelist?shopId='+shopId,
	    	    asyncLoad : true,
	    	    asyncTarget : '#pageMainBox',
	    	    params : {
	    	    }
	    	});
	    	//绑定提交按钮事件
	    	$('#queryCommitBtn').unbind('click');
	    	$('#queryCommitBtn').click(function(){
	    		var shopId = $("#shopId").val();
	    		$('#pageMainBox').load(GLOBAL.WEBROOT + '/buyeracct/tab/balancelist', { shopId: shopId});
	    	});
    	};
    	return {
    		init : init
    	};
	};
	pageConfig.config({
		//指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : ['bPage'],
		//指定页面
		init : function(){
			var p = new pInit();
			p.init();
		}
	});
});