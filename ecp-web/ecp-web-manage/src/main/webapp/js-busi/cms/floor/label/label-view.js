//新增修改floorlabel JS处理
$(function(){

	//返回按钮点击触发事件
	$('#btnReturn').click(function(){
		//跳转到floorlabel列表查询页面
		var floorId = $("#floorId").val();
		var searchParams = $("#searchParams").val();
		var floorSearchParams = $("#floorSearchParams").val();
		var params = {
			"floorId":floorId,
			"searchParams":searchParams,
			"floorSearchParams": floorSearchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'floorlabel/grid',
			"params" : params,
			"method" :"post"
		});
	});
	
});
