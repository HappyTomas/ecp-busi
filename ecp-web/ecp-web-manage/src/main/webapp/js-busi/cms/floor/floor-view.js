//新增修改floor JS处理
$(function(){
	//返回按钮点击触发事件
	$('#btnReturn').click(function(){
		//跳转到floor列表查询页面
		//window.location.href = $webroot+'floor/grid';
		var searchParams = $("#searchParams").val();
		SearchObj.openPage({
			"url": $webroot+'floor/grid',
			"params" :{"searchParams":(searchParams?searchParams:"")},
			"method" :"post"
		});
	});
});
