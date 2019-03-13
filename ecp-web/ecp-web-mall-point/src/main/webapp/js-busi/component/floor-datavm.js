/**
 * 与  楼层数据相关的模块； 自执行函数；
 */
$floorVMData = function(){
	/**
	 * 获取 楼层下的信息，如，页签、商品……；如果有符合要求的信息，则调用数据处理的回调函数(callback);否则直接返回；
	 * 入参：
	 * placeId : 内容位置Id；
	 * gdsSize : 位置展示商品个数
	 * tabSize : 页签个数
	 * placeWidth : 图片宽度
	 * placeHeight" : 图片长度
	 * status : 状态
	 * callback : 回调函数，对于数据处理的回调函数；
	 * @author jiangzh
	 */
	var getData = function(opts){
		$.eAjax({
			url : $webroot + '/homepage/qryFloorVM',
			data : {
				"menuType" :opts.menuType,
				"placeId" : opts.placeId,
				"gdsSize" : opts.gdsSize,
				"tabSize" : opts.tabSize,
				"placeWidth" : opts.placeWidth,
				"placeHeight" : opts.placeHeight,
				"status" : opts.status
			},
			async : true,
			type : "post",
			dataType : "html",
			success : function(data, textStatus) {
				if (data == null) {
					return;
				} else {
					if(opts.callback && $.isFunction(opts.callback)){
						opts.callback(data);
					}
				}
			}
		});
	};
	
	return {
		"getData" : getData
	};
}();