//新增修改site JS处理
$(function(){
	//返回按钮点击触发事件
	$('#btnReturn').click(function(){
		//跳转到site列表查询页面
		//var url = $webroot+'site/grid';
		//window.history.go(-1);
		var searchParams = $("#searchParams").val();
		SearchObj.openPage({
			"url": $webroot+'pageColor/grid',
			"params" :{"searchParams":(searchParams?searchParams:"")},
			"method" :"post"
		});
	});
});