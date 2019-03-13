/**
 * 与广告数据相关的模块； 自执行函数；
 */
$AdData = function(){
	/**
	 * 根据内容位置，获取广告；如果有符合要求的广告信息，则调用数据处理的回调函数(callback);否则直接返回；
	 * 入参：
	 * placeId : 内容位置Id；
	 * callback : 回调函数，对于数据处理的回调函数；
	 * @author jiangzh
	 */
	var getData = function(opts){
		$.eAjax({
			url : $webroot + '/leaflet/qryLeafletList',
			data : {
				"placeId" : opts.placeId,
				"placeSize" : opts.placeSize,
				"placeWidth":opts.placeWidth,
				"placeHeight":opts.placeHeight,
				"status" : opts.status
			},
			async : true,
			type : "post",
			dataType : "json",
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
/**
 * 与  商品分类   相关的模块； 自执行函数；
 */
$sidebarCatgData = function(){
	/**
	 * 获取当前也买你的全部分类菜单目录  的信息，则调用数据处理的回调函数(callback);否则直接返回；
	 * callback : 回调函数，对于数据处理的回调函数；
	 * @author zhanbh
	 */
	var getData = function(opts){
		$.eAjax({
			url : $webroot + '/category/qryCatgList',
			data : {
				"placeId" : opts.placeId,
				"placeHeight" : opts.placeHeight,
				"menuType" : opts.menuType
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
			},
			error:function(data){
//				alert(data);
			}
		});
	};
	
	return {
		"getData" : getData
	};
}();

/**
* 导航栏目；
*/
$channelData = function(){
	/**
	 * 获取栏目的信息，则调用数据处理的回调函数(callback);否则直接返回；
	 * callback : 回调函数，对于数据处理的回调函数；
	 * @author zhanbh
	 */
	var getData = function(opts){
		$.eAjax({
			url : $webroot + '/channel/qrychannel',
			data : {
				"path":opts.path,
				"navType":opts.navType
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
			},
			error:function(data){
				opts.callback(data);
			}
		});
	};
	
	return {
		"getData" : getData
	};
}();