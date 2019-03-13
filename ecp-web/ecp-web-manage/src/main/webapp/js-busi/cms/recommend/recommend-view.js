$(function(){
	recommend_view.init();
});

recommend_view = {
		init : function(){//初始化
			$('#btnReturn').click(function(){
				var searchParams = $("#searchParams").val();
				SearchObj.openPage({
					"url": $webroot+'recommend/grid',
					"params" :{"searchParams":(searchParams?searchParams:"")},
					"method" :"post"
				});
			});
		}
}