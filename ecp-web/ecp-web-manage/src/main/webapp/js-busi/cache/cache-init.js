$(function(){
	$(".pclear").on('click',function(){
		clearFun($(this).attr("p-type"),this);
	});
	
	var clearFun = function(paramType,el){
		$.eAjax({
			url : GLOBAL.WEBROOT + "/cache/clear/"+paramType,
			async : true,
			type : "post",
			dataType : "json",
			success: function(data){
				if(data.flag=="1"){
					$(el).siblings(".clear-msg").empty();
					$(el).siblings(".clear-msg").append("清理成功，共清理理缓存条目："+data.size);
				} else {
					$(el).siblings(".clear-msg").empty();
					$(el).siblings(".clear-msg").append("清理成功，共清理缓存条目："+data.msg);
				}
			}
		});
	};
	/**
	 * 针对商品信息缓存清楚做事件绑定
	 */
	$("#btn-clear-singleGds").click(function(){
		var $this = $(this);
		var gdsId = $.trim($("#gdsId").val());
		var number = /^([0-9]+)$/;
		if(gdsId == ""){
			eDialog.alert("请输入商品编码！");
			return;
		}
		if(!number.test(gdsId)){
			eDialog.alert("请输入正确的商品编码");
			return;
		}
		var param = {gdsId : gdsId};
		$.eAjax({
			url : GLOBAL.WEBROOT + "/cache/clearGds/",
			async : true,
			data : param,
			type : "post",
			dataType : "json",
			success: function(data){
				if(data.result=="success"){
					$this.siblings(".clear-msg").empty();
					$this.siblings(".clear-msg").append("清理成功，共清理缓存条目"+data.size);
				} else {
					$this.siblings(".clear-msg").empty();
					$this.siblings(".clear-msg").append("清理失败！"+data.msg);
				}
			}
		});
	});
	$("#btn-clear-allGds").click(function(){
		var $this = $(this);
		$.eAjax({
			url : GLOBAL.WEBROOT + "/cache/clearAllGds",
			async : true,
			type : "post",
			dataType : "json",
			success: function(data){
				if(data.result=="success"){
					$this.siblings(".clear-msg").empty();
					$this.siblings(".clear-msg").append("清理成功，共清理缓存条目"+data.size);
				} else {
					$this.siblings(".clear-msg").empty();
					$this.siblings(".clear-msg").append("清理失败！");
				}
			}
		});
	});
	
});