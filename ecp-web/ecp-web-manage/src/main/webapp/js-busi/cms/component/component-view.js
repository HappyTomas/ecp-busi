$(function(){
	$('#btnReturn').click(function(){
		var searchParams = $("#searchParams").val();
		SearchObj.openPage({
			"url":$webroot+'component/grid',
			"params":{"searchParams":(searchParams?searchParams:"")},
			"method":"post"
		});
	});
});
