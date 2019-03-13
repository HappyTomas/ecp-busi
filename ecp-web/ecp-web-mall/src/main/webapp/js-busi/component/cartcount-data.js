/**
 * 获取购物车数量
 */
$CartCountData = function(){

	var getData = function(opts){
		$.eAjax({
			url : $webroot + '/order/getcartcount',
			async : true,
			type : "post",
			dataType : "json",
			success : function(data) {
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