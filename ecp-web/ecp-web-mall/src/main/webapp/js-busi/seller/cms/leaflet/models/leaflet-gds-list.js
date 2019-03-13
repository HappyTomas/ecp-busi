//页面初始化模块
$(function(){
	$.fn.serializeJson=function(){  
        var serializeObj={};  
        $(this.serializeArray()).each(function(){  
            serializeObj[this.name]=this.value;  
        });  
        return serializeObj;  
    };  
	
	
    var pInit = function(){
    	var init = function(){
    		var p = $("#searchForm").serializeJson();
    		var siteId = $("#siteId").val();
			(siteId || siteId==0)?siteId:"";
	    	//分页
	    	$('#pageControlbar').bPage({
	    	    url : GLOBAL.WEBROOT + '/seller/common/querygds?siteId='+siteId,
	    	    asyncLoad : true,
	    	    asyncTarget : '#leaflet-gds-div',
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