//新增修改floortab JS处理
$(function(){
	
	//返回按钮点击触发事件
	$('#btnReturn').click(function(){
		//跳转到floor列表查询页面
		var floorId = $("#floorId").val();
		//var url = $webroot+'floortab/grid?floorId='+floor_floorId;
		var searchParams = $("#searchParams").val();
		var floorSearchParams = $("#floorSearchParams").val();
		var params = {
			"floorId":floorId,
			"searchParams":searchParams,
			"floorSearchParams":floorSearchParams
		}
		SearchObj.openPage({
			"url" : $webroot+'floortab/grid',
			"params" : params,
			"method" :"post"
		});
	});
	
});
