$(function(){
	placecategory_view.init();
});

placecategory_view = {
		init : function(){//初始化
			$('#btnReturn').click(function(){
				var searchParams = $("#searchParams").val();
				SearchObj.openPage({
					"url": $webroot+'placecategory/grid',
					"params" :{"searchParams":(searchParams?searchParams:"")},
					"method" :"post"
				});
			});
		}
}