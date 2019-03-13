//页面初始化模块
$(function(){
	// 页面业务逻辑处理内容
	var pInit = function(){
		var init = function(){
		    $('#saveaddr').click(function(){
		    	debugger;
				if(!$("#addrdetailfrom").valid()) return false;
				var val = ebcForm.formParams($("#addrdetailfrom")); 
				$(this).button('提交...');//设置加载
				$.eAjax({
					url : GLOBAL.WEBROOT + "/ord/saveaddr",
					data : val,
					datatype:'json',
					success : function(result) {
						if(result.resultFlag == 'ok'){
							$(this).button('reset');//按钮还原  
							bDialog.closeCurrent(result);
						}else{
							eDialog.error(result.msg);
						}  
					}
				});
			});
		     
		    $('#cancle').click(function(){
				bDialog.closeCurrent();
			});
		    
		};
		return {
			init : init
		};
		
	}
	
	pageConfig.config({
		// 指定需要加载的插件，名称请参考requirejs.common.js中定义的插件名称，注意大小写
		plugin : [],
		// 指定页面
		init : function(){
			var ppp = new pInit();
			ppp.init();
		}
	});
});