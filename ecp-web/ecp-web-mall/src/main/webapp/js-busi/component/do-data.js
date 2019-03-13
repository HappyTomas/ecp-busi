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
			//url : $webroot + '/advertise/qryAdvertiseList',
			data : {
				"placeId" : opts.placeId,
				"shopId" : opts.shopId,
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
			},
			error :  function(){
				if(opts.callback && $.isFunction(opts.callback)){
					opts.callback(null);
				}
			}
		});
	};
	
	return {
		"getData" : getData
	};
}();
/**
 * 与  楼层数据相关的模块； 自执行函数；
 */
$floorGdsData = function(){
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
			url : $webroot + '/homepage/qryFloorList',
			data : {
				"placeId" : opts.placeId,
				"gdsSize" : opts.gdsSize,
				"tabSize" : opts.tabSize,
				"placeWidth" : opts.placeWidth,
				"placeHeight" : opts.placeHeight,
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
				"adPlaceId" : opts.adPlaceId,
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
/**
 * 与快报数据相关的模块； 自执行函数；
 */
$InfoData = function(){
	/**
	 * 根据内容位置，获取信息；如果有符合要求的信息，则调用数据处理的回调函数(callback);否则直接返回；
	 * 入参：
	 * placeId : 内容位置Id；
	 * callback : 回调函数，对于数据处理的回调函数；
	 * @author jiangzh
	 */
	var getData = function(opts){
		$.eAjax({
			url : $webroot + '/info/infolistplug',
			data : {
				"placeId" : opts.placeId,
				"placeSize" : opts.placeSize,
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
			},
			error :  function(){
				opts.callback(null);
			}
		});
	};
	
	return {
		"getData" : getData
	};
}();
/**
 * 与  新书速递   相关的模块； 自执行函数；
 */
$newBooksGdsData = function(){
	/**
	 * 获取 当前时间一年内新出的书；如果有符合要求的信息，则调用数据处理的回调函数(callback);否则直接返回；
	 * 入参：
	 * gdsSize : 位置展示商品个数
	 * placeWidth : 图片宽度
	 * placeHeight" : 图片长度
	 * status : 状态   非必需   无则默认为上架商品
	 * callback : 回调函数，对于数据处理的回调函数；
	 * @author zhanbh
	 */
	var getData = function(opts){
		$.eAjax({
			url : $webroot + '/newbook/newbookexpress',
			data : {
				"propId" : opts.propId,
				"gdsSize" : opts.gdsSize,
				"tabSize" : opts.tabSize,
				"placeWidth" : opts.placeWidth,
				"placeHeight" : opts.placeHeight,
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
 * 与  楼层数据相关的模块； 自执行函数；
 */
$recommendData = function(){
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
			url : $webroot + '/homepage/queryRecommend',
			data : {
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
/**
 * 与  商品分类   相关的模块； 自执行函数；
 */
$sidebarCatgData = function(){
	/**
	 * 获取当前页面的全部分类菜单目录  的信息，则调用数据处理的回调函数(callback);否则直接返回；
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
 * 与  促销楼层  相关的模块； 自执行函数；
 */
$promFloorData = function(){
	/**
	 * 获取促销楼层的信息，则调用数据处理的回调函数(callback);否则直接返回；
	 * callback : 回调函数，对于数据处理的回调函数；
	 * @author zhanbh
	 */
	var getData = function(opts){
		$.eAjax({
			url : $webroot + '/prom/qryPromFloorVM',
			data : {
				"placeId" : opts.placeId,
				"coupSize" : opts.coupSize,
				"gdsSize" : opts.gdsSize,
				"tabSize" : opts.tabSize,
				"placeWidth" : opts.placeWidth,
				"placeHeight" : opts.placeHeight,
				"status" : opts.status,
				"floorType" : opts.floorType,
				"returnUrl" : opts.returnUrl
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
				"page":opts.page
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
/**
 * 首页底部链接vm数据；
 */
var $hLinksDataVM = function(){
	/**
	 * 获取首页底部链接vm数据，则调用数据处理的回调函数(callback);
	 * callback : 回调函数，对于数据处理的回调函数；
	 * @author zhanbh
	 */
	var getData = function(opts){
		$.eAjax({
			url : $webroot + '/link/gethlinkvm',
			data : {},
			async : true,
			type : "get",
			dataType : "html",
			success : function(data, textStatus) {
				if(opts.callback && $.isFunction(opts.callback)){
					opts.callback(data);
				}
			},
			error:function(data){
				if(opts.callback && $.isFunction(opts.callback)){
					opts.callback(null);
				}
			}
		});
	};
	
	return {
		"getData" : getData
	};
}();