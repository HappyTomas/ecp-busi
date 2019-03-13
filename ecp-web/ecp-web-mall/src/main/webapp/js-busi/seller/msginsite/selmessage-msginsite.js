$(function(){
    var pInit = function(){
    	var init = function(){
    	    //分页
	    	$('#pageControlbar').bPage({
	    	    url : GLOBAL.WEBROOT + '/seller/msginsite/msglist',
	    	    asyncLoad : true,
	    	    asyncTarget : '#dataBody',
	    	});
	    	
		};
    	return {
    		init : init
    	};
	};    	
	pageConfig.config({
		//指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : ['bPage','bForm'],
		//指定页面
		init : function(){
			var scoreLit = new pInit();
			scoreLit.init();
		}
	});
});

//删除单个消息
function delmsg(msgInfoId){
	eDialog.confirm("您确认要删除吗？", {
		buttons : [{
			caption : '确认',
			callback : function(){
				$.eAjax({
					url : GLOBAL.WEBROOT + "/seller/msginsite/msgdel",
					data : {'msgInfoId':msgInfoId},
					datatype:'json',
					success : function(returnInfo) {
						if (returnInfo.resultFlag == 'ok') {
							
							$('#dataBody').load(GLOBAL.WEBROOT + '/seller/msginsite/msglist?v='+Math.random());
				    	    
						} else {
							eDialog.alert(returnInfo.resultMsg);
						}
					}
				});
				
			}
		},{
			caption : '取消',
			callback : $.noop
		}]
	});
};
