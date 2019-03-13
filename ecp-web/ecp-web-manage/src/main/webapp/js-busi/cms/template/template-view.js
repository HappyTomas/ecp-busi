//新增修改template JS处理
$(function(){
	//返回按钮点击触发事件
	$('#btnReturn').click(function(){
		//跳转到site列表查询页面
		//window.location.href = $webroot+'template/grid';
		var searchParams = $("#searchParams").val();
		SearchObj.openPage({
			"url": $webroot+'template/grid',
			"params" :{"searchParams":(searchParams?searchParams:"")},
			"method" :"post"
		});
	});
});