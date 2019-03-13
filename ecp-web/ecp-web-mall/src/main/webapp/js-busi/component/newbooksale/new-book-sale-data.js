/**
 * 新书预售数据获取； 自执行函数；
 */
$newBookSaleData = function(){
	/**
	 * 获取新书预售数据  的信息，则调用数据处理的回调函数(callback);否则直接返回；
	 * callback : 回调函数，对于数据处理的回调函数；
	 * @author zhanbh
	 */
	var getData = function(opts){
		$.eAjax({
			url : $webroot + '/newbook/newbooksalelist',
			data : {
				"gdsSize" : opts.gdsSize,
				"placeSize" : opts.placeSize,
				"menuType" : opts.menuType,
				"placeWidth" : opts.placeWidth,
				"placeHeight" : opts.placeHeight,
				"versionType" : opts.versionType
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