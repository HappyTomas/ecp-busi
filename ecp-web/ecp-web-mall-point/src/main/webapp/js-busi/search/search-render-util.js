//搜索结果列表渲染
$gdsListRefresh=function(){
	
	var refresh = function(opts){
		
		//刷新分页栏和列表
		$('#pageControlbar').bPageRefresh({

			asyncLoad : true,
			asyncTarget : '#pageMainBox',
			async : false ,
			pageNumber:opts.pageNumber,
			params : function(){
		    	return {
					"keyword":$('#searchingTxt').val(),
					'category':$filterCategory.getCategoryId(),
					'field':'score',
					'sort':$('.score-sort').attr('sort'),
					'rangeType':$('.score-range').attr('_type'),
					'adid':$('#_adid').val()
			    };
		    }
		});
		try {
			var total=$('#searchTotal').val();
			$('#numFound').html(total);
		} catch (e) {
			
		}
		
	}
	
	return {
		"refresh" : refresh
	};

}();

//获取过滤分类
$filterCategory=function(){
	
	var getCategoryId = function(){
		
		//先判断是否是分类搜索
		if($('#searchCategory').val()){
			return $('#searchCategory').val();
		}
		
		return "";
	}
	
	return {
		"getCategoryId" : getCategoryId
	};
	
}();

