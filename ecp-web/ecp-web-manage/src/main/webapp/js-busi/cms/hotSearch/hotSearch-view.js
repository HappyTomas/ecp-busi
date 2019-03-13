$(function(){
	hotSearch_show.init();
});

hotSearch_show = {
	init : function(){//初始化
		$('#btnReturn').click(function(){
			var searchParams = $("#searchParams").val();
			SearchObj.openPage({
				"url": $webroot+'hotSearch/grid',
				"params" :{"searchParams":(searchParams?searchParams:"")},
				"method" :"post"
			});
		});
	}
}