//页面初始化模块
$(function(){
	//页面业务逻辑处理内容
	var pInit = function(){

    	var init = function(){
    		/*选项卡事件注册*/
    		var tabs = {
    				"acctBalance" : GLOBAL.WEBROOT + '/buyeracct/tab/balance?t='+new Date().getTime(),
    				"acctDetail" : GLOBAL.WEBROOT + '/buyeracct/tab/detail?t='+new Date().getTime()
    		};
    		$("#tabHost li").on("click", function(event){
    			var self = $(this);
    			var aLink = self.find("a");
    			self.siblings().find("a").removeClass("active");
    			aLink.addClass("active");
    			
    			$("#tabContent").load(tabs[aLink.attr("id")]);
    		});
    	};
    	return {
    		init : init
    	};
	};
	pageConfig.config({
		//指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : ['bForm','bPage'],
		//指定页面
		init : function(){
			var p = new pInit();
			p.init();
			//$("#tabContent").load(tabs[aLink.attr("id")]);
		}
	});
});