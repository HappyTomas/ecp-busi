$(function(){
	advertise_show.init();
});

advertise_show = {
	init : function(){//初始化
		$('#btnReturn').click(function(){
			var searchParams = $("#searchParams").val();
			SearchObj.openPage({
				"url": $webroot+'cms/weixh/grid',
				"params" :{"searchParams":(searchParams?searchParams:"")},
				"method" :"post"
			});
		});
	}
}