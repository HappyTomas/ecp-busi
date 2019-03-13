$(function(){
	placechannel_view.init();
});

placechannel_view = {
		init : function(){//初始化
			$('#btnReturn').click(function(){
				var searchParams = $("#searchParams").val();
				SearchObj.openPage({
					"url": $webroot+'placechannel/grid',
					"params" :{"searchParams":(searchParams?searchParams:"")},
					"method" :"post"
				});
			});
		}

		
}