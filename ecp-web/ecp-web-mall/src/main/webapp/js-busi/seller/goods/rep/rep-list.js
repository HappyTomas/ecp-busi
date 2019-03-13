//页面初始化模块
$(function(){
    var pInit = function(){
    	var init = function(){
    		var p = {};
    		p.shopId = $("select[name=shopId]").val();
	    	//分页
	    	$('#pageControlbar').bPage({
	    	    url : GLOBAL.WEBROOT + '/seller/goods/stockrep/listRep',
	    	    asyncLoad : true,
	    	    asyncTarget : '#listDiv',
	    	    params : function(){return p;}
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
			var repList = new pInit();
			repList.init();
		}
	});
});
