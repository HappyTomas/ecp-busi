/**
 * 商品详情里最近浏览的商品。自执行函数；
 */
$gdsRecentlyProduct = function(){
	/**
	 * 根据需要展示的商品数量来获取最近浏览的商品数量，则调用数据处理的回调函数(callback);否则直接返回；
	 */
	var getData = function(opts){
		$.eAjax({
			url : GLOBAL.WEBROOT + '/gdsdetail/queryrecentlyproduct',
			data : {
				"gdsSize" : opts.gdsSize,
				"gdsId" : opts.gdsId
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