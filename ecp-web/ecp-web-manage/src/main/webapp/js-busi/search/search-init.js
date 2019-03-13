$(function() {
	
	$(".pclear").on('click', function() {
				clearFun($(this).attr("_status"),$(this).attr("siteId"), this);
			});

	var clearFun = function(status,siteId, el) {
		if(status=="1"){
			eDialog.alert("索引配置缓存清空操作进行中...请勿重复点击操作！"); 
			return ;
		}
		$(el).attr("_status","1");
		
		$(el).siblings(".clear-msg").empty();
		$(el).siblings(".clear-msg")
				.append("索引配置缓存清空操作进行中，请稍后...");
		$.eAjax({
			url : GLOBAL.WEBROOT + "/search/clearCache",
			async : true,
			type : "post",
			data : {
				"siteId" : siteId
			},
			dataType : "json",
			success : function(data) {
				$(el).siblings(".clear-msg").empty();
				$(el).siblings(".clear-msg").append(data.message);
				$(el).attr("_status","0");
			}
		});
	};
	
	$(".pindex").on('click', function() {
				indexFun($(this).attr("_status"),$(this).attr("siteId"),$(this).attr("configId"), $(this).attr("operType"), this);
			});

	var indexFun = function(status,siteId,configId, operType, el) {
		/*if(status=="1"){
			eDialog.alert("索引操作进行中...请勿重复点击操作！"); 
			return ;
		}
		$(el).attr("_status","1");*/
		
		$(el).siblings(".clear-msg").empty();
		//$(el).siblings(".clear-msg")
		//		.append("索引操作进行中，请稍后...");
		$.ajax({
			url : GLOBAL.WEBROOT + "/search/" + operType,
			async : true,
			type : "post",
			data : {
				"siteId" : siteId,
				"configId" : configId
			},
			dataType : "json",
			success : function(data) {
				
				if (!data) {
					$(el).siblings(".clear-msg").empty();
					$(el).siblings(".clear-msg")
							.append("系统异常！返回对象为空！");
				} else {
					$(el).siblings(".clear-msg").empty();
					$(el).siblings(".clear-msg").append("返回信息："+data.values.message);
					/*$(el).siblings(".clear-msg").append("执行完毕！总数据条目："
							+ data.totallyCount + ",成功录入索引数据条目："
							+ data.indexCount + ",失败创建索引数据条目："
							+ data.failCount+ ",失败创建索引数据条目具体信息：【"
							+ data.failRecordList+"】,耗时："+data.timeCost+"s,错误信息："+data.failureMessage);*/
				}
				//$(el).attr("_status","0");
			}
		});
	};
});