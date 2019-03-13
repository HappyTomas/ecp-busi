//新增修改页面信息JS处理
$(function(){
	//返回按钮点击触发事件
	$('#btnReturn').click(function(){
		//跳转到页面信息列表查询页面
		var searchParams = $("#searchParams").val();
		SearchObj.openPage({
			"url":$webroot+'floortemplate/grid',
			"params":{"searchParams":(searchParams?searchParams:"")},
			"method":"post"
		});
	});
});