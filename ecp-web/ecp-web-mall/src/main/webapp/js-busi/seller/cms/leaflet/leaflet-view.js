$(function(){
	leaflet_show.init();
});

leaflet_show = {
	init : function(){//初始化
		
		//$("#siteId").addAttr("disabled","disabled");
		//$("#templateId").addAttr("disabled","disabled");
		//$("#linkType").addAttr("disabled","disabled");
		
		$('#btnReturn').click(function(){
			var searchParams = $("#searchParams").val();
			SearchObj.openPage({
				"url": $webroot+'/seller/leaflet',
				"params" :{"searchParams":(searchParams?searchParams:"")},
				"method" :"post"
			});
		});
	}
}