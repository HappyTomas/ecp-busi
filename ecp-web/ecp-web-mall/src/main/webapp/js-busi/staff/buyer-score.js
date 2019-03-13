//页面初始化模块
$(function(){
	//页面业务逻辑处理内容
	var pInit = function(){

    	var init = function(){
    		/*选项卡事件注册*/
    		//积分来源
            $('#scoreSource').click(function(){
            	//div的load事件，无法拦截，所以采用这种方式，校验用户是否登录
            	$.eAjax({
					url : GLOBAL.WEBROOT + "/buyerscore/checklogin?v="+Math.random(),
					datatype:'json',
					success : function(returnInfo) {
						if (returnInfo.resultFlag == 'ok') {
							$('#scoreSource').attr("class","active");
			            	$('#scoreExchange').attr("class","");
			            	$('#scoreRule').attr("class","");
			            	$('#tabContentDiv').load(GLOBAL.WEBROOT + '/buyerscore/sourcetab?v='+Math.random());
						} else {
							window.location.href = GLOBAL.WEBROOT + '/buyerscore/index';
						}
					}
				});
            	
            });
	    	
	    	//积分消费
            $('#scoreExchange').click(function(){
            	$.eAjax({
					url : GLOBAL.WEBROOT + "/buyerscore/checklogin?v="+Math.random(),
					datatype:'json',
					success : function(returnInfo) {
						if (returnInfo.resultFlag == 'ok') {
							$('#scoreExchange').attr("class","active");
			            	$('#scoreSource').attr("class","");
			            	$('#scoreRule').attr("class","");
			                $('#tabContentDiv').load(GLOBAL.WEBROOT + '/buyerscore/exchangetab?v='+Math.random());
						} else {
							window.location.href = GLOBAL.WEBROOT + '/buyerscore/index';
						}
					}
				});
            });
            //积分规则
            $('#scoreRule').click(function(){
            	$.eAjax({
					url : GLOBAL.WEBROOT + "/buyerscore/checklogin?v="+Math.random(),
					datatype:'json',
					success : function(returnInfo) {
						if (returnInfo.resultFlag == 'ok') {
							$('#scoreRule').attr("class","active");
			            	$('#scoreSource').attr("class","");
			            	$('#scoreExchange').attr("class","");
			            	$('#tabContentDiv').load(GLOBAL.WEBROOT + '/buyerscore/scorerule');
						} else {
							window.location.href = GLOBAL.WEBROOT + '/buyerscore/index';
						}
					}
				});
            	
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
		}
	});
});