
$hotShopData = function(){

	var getData = function(opts){
		$.eAjax({
			url : $webroot + '/homepage/queryhotshop',
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