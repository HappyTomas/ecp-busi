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

var rule160={ 
	//查询数据
	qryData:function(){
		var _id=$('#id').val();
		//ajax请求
		$.eAjax({
			url : GLOBAL.WEBROOT + "/seller/coupcomm/160/"+_id,
			success : function(returnInfo) {
				if(returnInfo.resultFlag=='ok'){
					if(returnInfo.useRuleValue){ 
						  $('#maxBuyCnt').val(returnInfo.useRuleValue);
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
		 var _maxBuyCnt=$('#maxBuyCnt').val();
		 if(_maxBuyCnt){
			 
		 }else{
			 data.push("使用数量限制");
		 }
		 return data;
	},
	//保存
	getData:function(){
		  var data= ebcForm.formParams($("#useForm160"));
		  return data;
	}
}
