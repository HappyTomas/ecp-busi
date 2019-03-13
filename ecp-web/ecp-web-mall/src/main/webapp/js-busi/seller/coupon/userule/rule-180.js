$(function() {

	//页面业务逻辑处理内容
	var pageInit = function() {

		var init = function() {
			
		};

		return {
			init : init
		};
	};


	pageConfig.config({
		//指定需要加载的插件，名称请参考common中定义的插件名称，注意大小写
		plugin : [ 'bForm','bPage' ],
		//指定页面
		init : function() {
			var p = new pageInit();
			p.init();
		}
	});

});

var rule180={
	//检查
	check:function(){
		return true;
	},
	//提醒信息
	getTips:function(){
		 var data=new Array();
	     if (ebcForm.formParams($("#useForm180"))){
	    	 
	     }else{
	    	 data.push("免邮限制");
	     }
		 return data;
	},
	//保存
	getData:function(){
		  var data= ebcForm.formParams($("#useForm180"));
		  return data;
	}
}
