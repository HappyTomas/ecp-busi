//新增修改place JS处理
$(function(){
	//返回按钮点击触发事件
	$('#btnReturn').click(function(){
		//跳转到列表查询页面
		//window.location.href = $webroot+'place/grid';
		var searchParams = $("#searchParams").val();
		SearchObj.openPage({
			"url": $webroot+'place/grid',
			"params" :{"searchParams":(searchParams?searchParams:"")},
			"method" :"post"
		});
	});
});