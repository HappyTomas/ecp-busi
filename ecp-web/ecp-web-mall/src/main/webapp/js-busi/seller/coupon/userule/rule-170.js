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

var rule170={ 
	//查询数据
	qryData:function(){
		var _id=$('#id').val();
		//ajax请求
		$.eAjax({
			url : GLOBAL.WEBROOT + "/seller/coupcomm/170/"+_id,
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					if(returnInfo.useRuleValue){
						    var arr=new Array();
						     arr=returnInfo.useRuleValue.split(",");
							 $(".channelCls").find("input[type=checkbox]").each(function() {
									 for(var i=0;i<arr.length;i++){
										if ($(this).val() == arr[i]){
											  $(this).attr("checked",'checked');
										}
									 }
								});
								 
						 }
					}
				}
		});
	},
	//检查
	check:function(){
		return true;
	},
	//提醒信息
	getTips:function(){
		 var data=new Array();
         var c=false;
		 $(".channelCls").find("input[type=checkbox]:checked").each(function() {
			 c=true;
			 return false;
		 });
		 
		 if (c){
	    	 
	     }else{
	    	 data.push("渠道使用限制");
	     }
		 return data;
	},
	//保存
	getData:function(){
		  var data= ebcForm.formParams($("#useForm170"));
		  return data;
	}
}
